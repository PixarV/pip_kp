package servlets;

import dao.EngineDao;
import dao.ModelDao;
import entities.Engine;
import entities.Model;
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
//    @EJB
//    private UserDao userDao;
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

        Engine lll = Engine.builder().
                weight(1).title("lll")
                .power(1).fuel(TypeFuel.BENZIN)
                .build();

        engineDao.saveEntity(lll);
        model.getEngines().add(lll);
        modelDao.saveEntity(model);
//        lll.getModels().add(model);


//        lll.setModels(Collections.singletonList(model));
//        engineDao.flush();


        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
