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

CREATE TRIGGER chassis_add BEFORE INSERT ON chassis
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
--CREATE INDEX model
