package servlets;

import database.DataBase;
import models.ApartmentOwner;
import utils.JsonUtil;
import utils.ServletUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class ApartmentOwnersServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(ApartmentOwnersServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        int apartmentOwnerId;
        try{
            apartmentOwnerId = Integer.parseInt(req.getParameter("id"));
        } catch (Exception e){
            ServletUtils.safePrint("Invalid parameter apartment owner id", resp, logger);
            return;
        }
        ApartmentOwner apartmentOwner = DataBase.getInstance().getApartmentOwner(apartmentOwnerId);
        if(apartmentOwner == null){
            ServletUtils.safePrint("couldn't find apartment owner with given ID", resp, logger);
            return;
        }
        ServletUtils.safePrint(JsonUtil.objToJson(apartmentOwner), resp, logger);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String cardInfo = req.getParameter("cardInfo");
        ApartmentOwner apartmentOwner = new ApartmentOwner(firstName, lastName, cardInfo, null);

        boolean successful = DataBase.getInstance().addApartmentOwner(apartmentOwner);
        if (successful) {
            ServletUtils.safePrint(JsonUtil.objToJson(apartmentOwner), resp, logger);
        } else {
            ServletUtils.safePrint("Couldn't add apartment owner", resp, logger);
        }
    }
}
