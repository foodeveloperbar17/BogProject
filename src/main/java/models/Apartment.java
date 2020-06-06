package models;

import javax.persistence.*;

@Entity
@Table(name = "Apartments")
public class Apartment {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "lat")
    private double lat;

    @Column(name = "price")
    private double price;

    @Column(name = "reserved_days")
    private int unavailableDays;

    @Column(name = "lng")
    private double lng;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private ApartmentOwner apartmentOwner;

    public Apartment(String address, double lat, double lng, int unavailableDays, double price, ApartmentOwner apartmentOwner) {
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.unavailableDays = unavailableDays;
        this.price = price;
        this.apartmentOwner = apartmentOwner;
    }

    public Apartment(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getUnavailableDays() {
        return unavailableDays;
    }

    public void setUnavailableDays(int unavailableDays) {
        this.unavailableDays = unavailableDays;
    }

    public ApartmentOwner getApartmentOwner() {
        return apartmentOwner;
    }

    public void setApartmentOwner(ApartmentOwner apartmentOwner) {
        this.apartmentOwner = apartmentOwner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
