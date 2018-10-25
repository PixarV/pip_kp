package servlets;

import dao.*;
import entities.*;
import enums.FirmSpecializtion;
import enums.TypeFuel;
import enums.TypeModel;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import services.HWService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HWServlet extends HttpServlet {

    @Inject
    HWService helloService;

    @EJB
    EngineDao engineDao;

    @EJB
    ModelDao modelDao;
    @EJB
    ChassisDao chassisDao;

    @EJB
    AmmunitionDao ammunitionDao;
    //    @EJB
    @EJB
    FirmEngineDao firmEngineDao;

    @EJB
    FirmWeaponDao firmWeaponDao;

    @EJB
    FirmTowerDao firmTowerDao;

    @EJB
    TowerDao towerDao;

    @EJB
    WeaponDao weaponDao;
//    private AmmunitionDao ammunitionDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        System.out.println(helloService.createHelloMessage("ritt"));

        Model model = Model.builder().armor("kk")
                .type(TypeModel.M)
                .maxSpeedBackward(1)
                .maxSpeedForward(2)
                .title("lll")
                .build();

        Chassis chassis = Chassis.builder()
                .title("first")
                .carring(11.2)
                .weight(5)
                .model(model)
                .turnSpeed(33)
                .build();

        Tower tower = Tower.builder()
                .armor("fdgh")
                .title("tower")
                .turnSpeed(12)
                .viewRadius(13)
                .weight(14)
                .build();


        Ammunition ammunition = Ammunition.builder()
                .breakage(1)
                .callibr(2)
                .type("dsds")
                .build();

        Weapon weapon = Weapon.builder()
                .callibr(2)
                .title("weapon")
                .weight(22.5)
                .build();

        Firm firm = Firm.builder()
                .specialization(FirmSpecializtion.BOTH)
                .title("first")
                .build();

        Engine engine = Engine.builder()
                .fuel(TypeFuel.BENZIN)
                .power(1)
                .title("engine")
                .weight(1.55)
                .build();

        FirmEngine build = FirmEngine.builder()
                .engine(engine)
                .firm(firm)
                .build();


        FirmWeapon firmWeapon = FirmWeapon.builder()
                .firm(firm)
                .weapon(weapon)
                .build();

        FirmTower firmTower = FirmTower.builder()
                .firm(firm)
                .tower(tower)
                .build();

//        firmTowerDao.saveEntity(firmTower);

//        weaponDao.saveEntity(weapon);
//        firmWeaponDao.updateEntity(firmWeapon);
//        ammunition.getWeapons().add(weapon);
//        tower.getWeapons().add(weapon);
//        System.out.println(weapon);
//        towerDao.updateEntity(tower);
//        firmEngineDao.saveEntity(build);
//        ammunitionDao.saveEntity(ammunition);
//        chassis.getTowers().add(tower);

        modelDao.saveEntity(model);
//        modelDao.updateFromDB(model);
        System.out.println(model);
        chassisDao.saveEntity(chassis);
        System.out.println(chassis);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
