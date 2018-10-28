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
import java.time.LocalDate;

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

    @EJB
    FirmDao firmDao;

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

    @EJB
    HumanDao humanDao;

    @EJB
    RelationDao relationDao;
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
                .model(model)
                .weight(5)
                .turnSpeed(33)
                .build();

//        model.getChassis().add(chassis);

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

        FirmEngine firmEngine = FirmEngine.builder()
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

        Human human = Human.builder()
                .name("ritt")
                .vacationEnd(LocalDate.now())
                .vacationStart(LocalDate.now().minusDays(5))
                .build();

        Relation r1 = Relation.builder()
                .stage(1)
                .human(human)
                .build();

        Relation r2 = Relation.builder()
                .stage(2)
                .human(human)
                .parent(r1)
                .build();

        humanDao.saveEntity(human);
        relationDao.saveEntity(r1);
        relationDao.saveEntity(r2);

        System.out.println(r1);
        System.out.println(r2);



        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
