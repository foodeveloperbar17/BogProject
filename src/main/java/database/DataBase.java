package database;

import models.Apartment;
import models.ApartmentOwner;
import models.Rent;
import models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

public class DataBase {
    private static DataBase dataBase;

    private static final Logger logger = Logger.getLogger(DataBase.class.getName());
    private static final int INVALID_PARAMETER = -1;
    private static final int INVALID_APARTMENT = -2;

    private DataBase() {
    }

    public synchronized static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }


    public int rentApartment(int userId, int apartmentId, int days) {
        try (Session session = getSession()) {
            Apartment apartment = getApartment(apartmentId);
            if(apartment == null){
                return INVALID_PARAMETER;
            } else if(apartment.getUnavailableDays() > 0){
                return INVALID_APARTMENT;
            }
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("update Apartment set unavailableDays = :days where id = :apartment_id");
            query.setParameter("days", days);
            query.setParameter("apartment_id", apartmentId);
            int rowsAffected = query.executeUpdate();
            logger.info("method: rentApartment, rows affected: " + rowsAffected);

            Rent rent = new Rent(apartmentId, userId, days, apartment.getPrice() * days);
            session.save(rent);

            transaction.commit();
            return rowsAffected;
        }
    }

    public boolean addUser(User user) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean addApartmentOwner(ApartmentOwner apartmentOwner) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(apartmentOwner);
            transaction.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean addApartment(Apartment apartment) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(apartment);
            transaction.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public ApartmentOwner getApartmentOwner(int ownerId) {
        List<ApartmentOwner> result = getListFromTable(ownerId, "models.ApartmentOwner");
        if (result == null || result.size() == 0) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public User getUser(int userId) {
        List<User> result = getListFromTable(userId, "models.User");
        if (result == null || result.size() == 0) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public Apartment getApartment(int apartmentId) {
        List<Apartment> result = getListFromTable(apartmentId, "models.Apartment");
        if (result == null || result.size() == 0) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public Rent getRent(int rentId){
        List<Rent> result = getListFromTable(rentId, "models.Rent");
        if (result == null || result.size() == 0) {
            return null;
        } else {
            return result.get(0);
        }
    }

    private List getListFromTable(int id, String tableName) {
        try (Session session = getSession()) {
            Query query = session.createQuery("from " + tableName + " where id = :id");
            query.setParameter("id", id);
            List result = query.getResultList();
            return result;
        }
    }

    public List getAll(String tableName) {
        Session session = getSession();
        Query query = session.createQuery("from " + tableName);
        return query.getResultList();
    }
}
