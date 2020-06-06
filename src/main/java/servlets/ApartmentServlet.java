package servlets;

import database.DataBase;
import models.Apartment;
import models.ApartmentOwner;
import utils.JsonUtil;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class ApartmentServlet extends HttpServlet {
    private static final int INITIAL_RESERVED_DAYS = 0;
    private static final Logger logger = Logger.getLogger(ApartmentServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        int apartmentId;
        try{
            apartmentId = Integer.parseInt(req.getParameter("id"));
        } catch (Exception e){
            ServletUtils.safePrint("Invalid parameter apartment id", resp, logger);
            return;
        }
        Apartment apartment = DataBase.getInstance().getApartment(apartmentId);
        if(apartment == null){
            ServletUtils.safePrint("couldn't find apartment with given ID", resp, logger);
            return;
        }
        ServletUtils.safePrint(JsonUtil.objToJson(apartment), resp, logger);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String address;
        double price;
        double lat;
        double lng;
        int ownerId;
        try {
            address = req.getParameter("address");
            price = Double.parseDouble(req.getParameter("price"));
            lat = Double.parseDouble(req.getParameter("lat"));
            lng = Double.parseDouble(req.getParameter("lng"));
            ownerId = Integer.parseInt("ownerId");
        } catch (Exception e) {
            ServletUtils.safePrint("Invalid parameters", resp, logger);
            return;
        }
        ApartmentOwner apartmentOwner = DataBase.getInstance().getApartmentOwner(ownerId);
        if (apartmentOwner == null) {
            ServletUtils.safePrint("Invalid owner id", resp, logger);
            return;
        }
        Apartment apartment = new Apartment(address, lat, lng, INITIAL_RESERVED_DAYS, price, apartmentOwner);
        boolean success = DataBase.getInstance().addApartment(apartment);
        if (success) {
            ServletUtils.safePrint(JsonUtil.objToJson(apartment), resp, logger);
        } else {
            ServletUtils.safePrint("Couldn't add new apartment", resp, logger);
        }
    }
}
