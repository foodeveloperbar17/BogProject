package models;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "card_info")
    private String cardInfo;


    public User(int id, String firstName, String lastName, String cardInfo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardInfo = cardInfo;
    }

    public User(String firstName, String lastName, String cardInfo){
        this(0, firstName, lastName, cardInfo);
    }

    public User(){}

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
