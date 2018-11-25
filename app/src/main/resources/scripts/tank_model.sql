CREATE TYPE type_fuel AS ENUM ('BENZIN', 'DIZEL');

CREATE TABLE engine (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  fuel type_fuel NOT NULL,
  power INTEGER NOT NULL CHECK (
    power > 0
  ),
  weight INTEGER NOT NULL CHECK (
    weight > 0
  )
);

CREATE TYPE type_model AS ENUM ('H', 'M', 'L');

CREATE TABLE model (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  type type_model NOT NULL,
  max_speed_forward DOUBLE PRECISION NOT NULL CHECK (
    max_speed_forward > 0),
  max_speed_backward DOUBLE PRECISION NOT NULL CHECK (
    max_speed_backward > 0),
  armor VARCHAR(255) NOT NULL
);


CREATE TABLE model_engine (
  id_model INTEGER REFERENCES model ON DELETE RESTRICT,
  id_engine INTEGER REFERENCES engine ON DELETE RESTRICT,
  PRIMARY KEY (id_model, id_engine)
);

CREATE TABLE chassis (
  id SERIAL PRIMARY KEY,
  id_model INTEGER REFERENCES model ON DELETE RESTRICT,
  title VARCHAR(255) NOT NULL,
  carring DOUBLE PRECISION CHECK (
    carring >= 0
  ),
  turn_speed DOUBLE PRECISION CHECK (
    turn_speed > 0
  ),
  weight DOUBLE PRECISION CHECK (
    weight > 0
  )
);

CREATE TABLE tower (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  armor VARCHAR(255) NOT NULL,
  turn_speed DOUBLE PRECISION NOT NULL CHECK (
    turn_speed >= 0
  ),
  view_radius DOUBLE PRECISION NOT NULL CHECK (
    view_radius >=0
  ),
  weight DOUBLE PRECISION NOT NULL CHECK (
    weight > 0
  )
);

CREATE TABLE chassis_tower (
  id_chassis INTEGER REFERENCES chassis ON DELETE RESTRICT,
  id_tower INTEGER REFERENCES tower ON DELETE RESTRICT,
  PRIMARY KEY (id_chassis, id_tower)
);


CREATE TABLE weapon (
  id SERIAL PRIMARY KEY ,
  title VARCHAR(255) NOT NULL ,
  callibr INTEGER NOT NULL CHECK (
    callibr > 0
  ),
  weight DOUBLE PRECISION NOT NULL CHECK (
    weight > 0
  )
);

CREATE TYPE type_ammunition AS ENUM ('ARMOR_PIERCING', 'SUBCALLIBER', 'HIGH_EXPLOSIVE', 'CUMULATIVE');

CREATE TABLE ammunition (
  id SERIAL PRIMARY KEY ,
  ammunition_type VARCHAR(255) NOT NULL ,
  callibr INTEGER NOT NULL CHECK (
    callibr > 0
  ),
  breakage INTEGER
);


CREATE TABLE ammunition_weapon (
  id_ammunition INTEGER REFERENCES ammunition ON DELETE RESTRICT ,
  id_weapon INTEGER REFERENCES weapon ON DELETE RESTRICT,
  PRIMARY KEY (id_ammunition, id_weapon)
);

CREATE TYPE firm_specialization AS ENUM ('TANK', 'ENGINE', 'BOTH');

CREATE TABLE firm (
  id SERIAL PRIMARY KEY ,
  title VARCHAR(255) NOT NULL ,
  specialization firm_specialization NOT NULL
);

CREATE TABLE firm_engine (
  id_firm INTEGER REFERENCES firm ON DELETE SET NULL ,
  id_engine INTEGER REFERENCES engine ON DELETE RESTRICT ,
  serial_no VARCHAR(64) UNIQUE NOT NULL,
  PRIMARY KEY (serial_no)
);

CREATE TABLE firm_tower (
  id_firm INTEGER REFERENCES firm ON DELETE SET NULL ,
  id_tower INTEGER REFERENCES tower ON DELETE RESTRICT ,
  serial_no VARCHAR(64) UNIQUE NOT NULL,
  PRIMARY KEY (serial_no)
);

CREATE TABLE firm_weapon (
  id_firm INTEGER REFERENCES firm ON DELETE SET NULL ,
  id_weapon INTEGER REFERENCES weapon ON DELETE RESTRICT ,
  serial_no VARCHAR(64) UNIQUE NOT NULL,
  PRIMARY KEY (serial_no)
);

CREATE TABLE tower_weapon (
  id_tower INTEGER REFERENCES tower ON DELETE RESTRICT ,
  id_weapon INTEGER REFERENCES weapon ON DELETE RESTRICT,
  PRIMARY KEY (id_tower, id_weapon)
);

CREATE TABLE tank (
  id SERIAL PRIMARY KEY ,
  id_model INTEGER REFERENCES model ON DELETE RESTRICT ,
  id_chassis INTEGER REFERENCES chassis ON DELETE RESTRICT ,
  sn_engine VARCHAR(64) REFERENCES firm_engine(serial_no) ON DELETE RESTRICT,
  sn_tower VARCHAR(64) REFERENCES firm_tower(serial_no) ON DELETE RESTRICT,
  sn_weapon VARCHAR(64) REFERENCES firm_weapon(serial_no) ON DELETE RESTRICT,

  team_number INTEGER CHECK (
    team_number >= 0
  )
);

CREATE TABLE specialization (
  id SERIAL PRIMARY KEY ,
  title VARCHAR(255) NOT NULL
);

CREATE TABLE relation (
  id SERIAL PRIMARY KEY ,
  id_human INTEGER,
  id_parent INTEGER REFERENCES relation ON DELETE SET NULL,
  stage INTEGER
);

CREATE TABLE human (
  id SERIAL PRIMARY KEY ,
  id_tank INTEGER REFERENCES tank ON DELETE SET NULL ,
  name VARCHAR(255) NOT NULL ,
  vacation_start TIMESTAMP,
  vacation_end TIMESTAMP CHECK (
    vacation_end > vacation_start
  )
);

CREATE TABLE specialization_human (
  id SERIAL PRIMARY KEY,
  id_specialization INTEGER REFERENCES specialization ON DELETE SET NULL ,
  id_human INTEGER REFERENCES human ON DELETE RESTRICT ,
  type_tank type_model NOT NULL
);

CREATE TABLE tank_specialization (
  id_tank INTEGER REFERENCES tank,
  id_specialization INTEGER REFERENCES specialization ON DELETE SET NULL,
  PRIMARY KEY (id_tank, id_specialization)
);

CREATE OR REPLACE FUNCTION add_to_tank(id_model INTEGER, id_chassis INTEGER, sn_engine INTEGER,
  sn_tower INTEGER, sn_weapon INTEGER, team_number INTEGER)
RETURNS void AS $$
BEGIN
INSERT INTO tank(id_model, id_chassis, sn_engine, sn_tower, sn_weapon, team_number)
VALUES(id_model, id_chassis, sn_engine, sn_tower, sn_weapon, team_number);
END
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION check_unique_ch() RETURNS trigger AS $$
DECLARE
  rec INTEGER;
BEGIN
  rec := (SELECT chassis.id FROM chassis WHERE NEW.id= chassis.id);
  IF rec is not NULL THEN
      RAISE EXCEPTION 'number % was found', NEW.id;
  END IF;

--  rec = (SELECT firm_tower.serial_no FROM firm_tower WHERE NEW.id = firm_tower.serial_no);
--  IF rec is not NULL THEN
--    RAISE EXCEPTION 'number % was found', NEW.id;
--  END IF;
--
--  rec = (SELECT firm_weapon.serial_no FROM firm_weapon WHERE NEW.id = firm_weapon.serial_no);
--  IF rec is not NULL THEN
--    RAISE EXCEPTION 'number % was found', NEW.id;
--  END IF;
--
--  rec = (SELECT firm_engine.serial_no FROM firm_engine WHERE NEW.id = firm_engine.serial_no);
--  IF rec is not NULL THEN
--    RAISE EXCEPTION 'number % was found', NEW.id;
--  END IF;
--
  RETURN NEW;

END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER chassis_add BEFORE INSERT OR UPDATE ON chassis
    FOR EACH ROW EXECUTE PROCEDURE check_unique_ch();


CREATE OR REPLACE FUNCTION check_unique() RETURNS trigger AS $$
DECLARE
  rec VARCHAR(64);
BEGIN
  rec = (SELECT firm_tower.serial_no FROM firm_tower WHERE NEW.serial_no = firm_tower.serial_no);
  IF rec is not NULL THEN
    RAISE EXCEPTION 'number % was found', NEW.serial_no;
  END IF;

  rec = (SELECT firm_weapon.serial_no FROM firm_weapon WHERE NEW.serial_no = firm_weapon.serial_no);
  IF rec is not NULL THEN
    RAISE EXCEPTION 'number % was found', NEW.serial_no;
  END IF;

  rec = (SELECT firm_engine.serial_no FROM firm_engine WHERE NEW.serial_no = firm_engine.serial_no);
  IF rec is not NULL THEN
    RAISE EXCEPTION 'number % was found', NEW.serial_no;
  END IF;

  RETURN NEW;

END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER serail_add_f_t BEFORE INSERT OR UPDATE ON firm_tower
  FOR EACH ROW EXECUTE PROCEDURE check_unique();
CREATE TRIGGER serail_add_f_w BEFORE INSERT OR UPDATE ON firm_weapon
  FOR EACH ROW EXECUTE PROCEDURE check_unique();
CREATE TRIGGER serail_add_f_e BEFORE INSERT OR UPDATE ON firm_engine
  FOR EACH ROW EXECUTE PROCEDURE check_unique();

CREATE OR REPLACE FUNCTION tanks_count(OUT result integer) AS
$func$
BEGIN
  EXECUTE format('SELECT count(ID) FROM tank')
  INTO result;
END
$func$  LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION check_firm_engine() RETURNS trigger AS $$
DECLARE
  rec INTEGER;
BEGIN

  rec := (SELECT firm.id FROM firm WHERE NEW.id_firm = firm.id AND firm.specialization = 'TANK');
  IF rec is not NULL THEN
    RAISE EXCEPTION 'Firm % cannot create engines', NEW.id_firm;
  END IF;

  RETURN NEW;

END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER firm_check_weapons BEFORE INSERT OR UPDATE ON firm_engine
  FOR EACH ROW EXECUTE PROCEDURE check_firm_engine();

CREATE OR REPLACE FUNCTION check_firm_tank() RETURNS trigger AS $$
DECLARE
  rec INTEGER;
BEGIN

  rec := (SELECT firm.id FROM firm WHERE NEW.id_firm = firm.id AND firm.specialization = 'ENGINE');
  IF rec is not NULL THEN
    RAISE EXCEPTION 'Firm % cannot create weapons', NEW.id_firm;
  END IF;

  RETURN NEW;

END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER firm_check_weap BEFORE INSERT OR UPDATE ON firm_weapon
  FOR EACH ROW EXECUTE PROCEDURE check_firm_tank();
CREATE TRIGGER firm_check_tow BEFORE INSERT OR UPDATE ON firm_tower
  FOR EACH ROW EXECUTE PROCEDURE check_firm_tank();


CREATE OR REPLACE FUNCTION check_firm_weap_tow() RETURNS trigger AS $$
DECLARE
  rec INTEGER;
  rec2 INTEGER;
BEGIN

  rec := (SELECT firm_tower.id_firm FROM firm_tower, firm_weapon WHERE NEW.id_tower = firm_tower.id_tower AND
  NEW.id_weapon = firm_weapon.id_weapon AND firm_tower.id_firm = firm_weapon.id_firm);
  IF rec is NULL THEN
    RAISE EXCEPTION 'Different firms';
  END IF;

  RETURN NEW;

END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER firm_check_tow_weap BEFORE INSERT OR UPDATE ON tower_weapon
  FOR EACH ROW EXECUTE PROCEDURE check_firm_weap_tow();

 CREATE OR REPLACE FUNCTION tank_valid() RETURNS trigger AS $$
 DECLARE
   rec INTEGER;
 BEGIN

   rec := (SELECT chassis.id FROM chassis WHERE NEW.id_chassis = chassis.id AND NEW.id_model = chassis.id_model);
   IF rec is NULL THEN
     RAISE EXCEPTION 'Problem with chassis and model';
   END IF;

   rec := (SELECT tower_weapon.id_tower FROM tower_weapon, firm_weapon, firm_tower
             WHERE NEW.sn_tower = firm_tower.serial_no AND firm_tower.id_tower = tower_weapon.id_tower
               AND NEW.sn_weapon = firm_weapon.serial_no AND firm_weapon.id_weapon = tower_weapon.id_weapon);

   IF rec is NULL THEN
     RAISE EXCEPTION 'Problem tower and weapon';
   END IF;

   RETURN NEW;

 END;
 $$ LANGUAGE plpgsql;

 CREATE TRIGGER tank_validation BEFORE INSERT OR UPDATE ON tank
   FOR EACH ROW EXECUTE PROCEDURE tank_valid();

--CREATE INDEX firm_specializations 
--	ON firm USING Hash(specialization);
--CREATE INDEX ammunition_types
--	ON ammunition USING Hash("type");
--
--CREATE INDEX tower_serial
--	ON firm_tower USING Btree(serial_no);
--CREATE INDEX weapon_serial
--	ON firm_weapon USING Btree(serial_no);
--CREATE INDEX engine_serial
--	ON firm_engine USING Btree(serial_no);
--CREATE INDEX chassis_serial
--	ON chassis USING Btree(id);
--CREATE INDEX model_speed
--	ON model USING Btree(max_speed_forward);
