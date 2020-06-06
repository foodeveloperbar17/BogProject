package servlets;

import database.DataBase;
import models.Rent;
import utils.JsonUtil;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class RentApartmentServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(RentApartmentServlet.class.getName());

    private static final int ILLEGAL_APARTMENT_ID = -1;
    private static final int ALREADY_RENTED_APARTMENT = -2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        int rentId;
        try{
            rentId = Integer.parseInt(req.getParameter("id"));
        } catch (Exception e){
            ServletUtils.safePrint("Invalid parameter apartment id", resp, logger);
            return;
        }
        Rent rent = DataBase.getInstance().getRent(rentId);
        if(rent == null){
            ServletUtils.safePrint("couldn't find rent with given ID", resp, logger);
        } else {
            ServletUtils.safePrint(JsonUtil.objToJson(rent), resp, logger);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        int apartmentId;
        int userId;
        int days;
        try {
            apartmentId = Integer.parseInt(req.getParameter("apartmentId"));
            userId = Integer.parseInt(req.getParameter("userId"));
            days = Integer.parseInt(req.getParameter("days"));
        } catch (NumberFormatException e) {
            ServletUtils.safePrint("Invalid parameters", resp, logger);
            return;
        }

        int result = DataBase.getInstance().rentApartment(userId, apartmentId, days);
        if (result == ILLEGAL_APARTMENT_ID) {
            ServletUtils.safePrint("invalid apartment id", resp, logger);
        } else if (result == ALREADY_RENTED_APARTMENT) {
            ServletUtils.safePrint("You can't rent already rented apartment", resp, logger);
        } else if(result != 1){
            throw new RuntimeException("Internal Error, two apartments shouldn't be updated");
        } else{
            ServletUtils.safePrint("apartment successfully rented", resp, logger);
        }
    }
}
