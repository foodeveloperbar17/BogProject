package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "rents")
public class Rent {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "apartment_id")
    private int apartmentId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "days")
    private int days;

    @Column(name = "active_status")
    private boolean activeStatus;

    @Column(name = "money_payed")
    private double moneyPayed;

    public Rent(int apartmentId, int userId, int days, double moneyPayed) {
        startDate = new Date();
        this.apartmentId = apartmentId;
        this.userId = userId;
        this.days = days;
        this.activeStatus = true;
        this.moneyPayed = moneyPayed;
    }

    public Rent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public double getMoneyPayed() {
        return moneyPayed;
    }

    public void setMoneyPayed(double moneyPayed) {
        this.moneyPayed = moneyPayed;
    }
}
