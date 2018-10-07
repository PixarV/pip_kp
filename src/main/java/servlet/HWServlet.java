package servlet;

import dao.UserDao;
import entity.UserEntity;
import service.HWService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class HWServlet extends HttpServlet {

    @Inject
    private HWService helloService;

    @EJB
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        System.out.println(helloService.createHelloMessage("ritt"));

        System.out.println(userDao.getUser(1));
        UserEntity user = new UserEntity();
        user.setName("ritt");
        user.setSurname("fedotova");
//        UserEntity user = UserEntity.builder()
//                .name("ritt")
//                .surname("fedotova")
//                .build();
//
        userDao.saveUser(user);
        System.out.println(user);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
