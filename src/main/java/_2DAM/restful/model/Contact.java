package _2DAM.restful.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    private static final long serialVersion = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPerson")
    private Person person;
    @Column(name = "phoneNumber")
    private int phoneNumber;

    public Contact() {
    }
    public Contact(Person person, int phoneNumber) {
        this.person = person;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "person=" + person +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return getId() == contact.getId();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
