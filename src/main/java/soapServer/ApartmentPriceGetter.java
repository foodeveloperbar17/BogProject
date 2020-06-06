package soapServer;

import database.DataBase;
import models.Apartment;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ApartmentPriceGetter {
    private static final int INVALID_ARGUMENT = -1;

    @WebMethod
    public double getApartmentPrice(int apartmentId){
        Apartment apartment = DataBase.getInstance().getApartment(apartmentId);
        if(apartment == null){
            return INVALID_ARGUMENT;
        }
        return apartment.getPrice();
    }
}
