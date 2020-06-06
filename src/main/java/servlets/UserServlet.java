package servlets;

import database.DataBase;
import models.User;
import utils.JsonUtil;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class UserServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(UserServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId;
        try{
            userId = Integer.parseInt(req.getParameter("id"));
        } catch (Exception e){
            ServletUtils.safePrint("Invalid parameter apartment owner id", resp, logger);
            return;
        }
        User user = DataBase.getInstance().getUser(userId);
        if(user == null){
            ServletUtils.safePrint("couldn't find user with given ID", resp, logger);
            return;
        }
        ServletUtils.safePrint(JsonUtil.objToJson(user), resp, logger);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String cardInfo = req.getParameter("cardInfo");
        User newUser = new User(0, firstName, lastName, cardInfo);
        boolean successful = DataBase.getInstance().addUser(newUser);

        if(successful){
            ServletUtils.safePrint(JsonUtil.objToJson(newUser), resp, logger);
        } else{
            ServletUtils.safePrint("Couldn't add new user", resp, logger);
        }
    }
}
