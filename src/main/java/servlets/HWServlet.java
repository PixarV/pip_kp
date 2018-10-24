package servlets;

import dao.AmmunitionDao;
import dao.ChassisDao;
import dao.EngineDao;
import dao.ModelDao;
import entities.*;
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
//                .model(model)
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

        ammunition.getWeapons().add(weapon);

        ammunitionDao.saveEntity(ammunition);
//        chassis.getTowers().add(tower);

//        modelDao.saveEntity(model);
//        chassisDao.saveEntity(chassis);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
