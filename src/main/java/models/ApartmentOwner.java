package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "apartment_owner")
public class ApartmentOwner{

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "card_info")
    private String cardInfo;

    @OneToMany(mappedBy = "apartmentOwner")
    private List<Apartment> apartments;

    public ApartmentOwner(int id, String firstName, String lastName, String cardInfo, List<Apartment> apartments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardInfo = cardInfo;
        this.apartments = apartments;
    }

    public ApartmentOwner(String firstName, String lastName, String cardInfo, List<Apartment> apartments) {
        this(0, firstName, lastName, cardInfo, apartments);
    }

    public ApartmentOwner(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }
}
