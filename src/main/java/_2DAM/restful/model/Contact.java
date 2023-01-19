package _2DAM.restful.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    private Long id;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPerson", referencedColumnName = "id", nullable = false, unique = true)
    private Person person;
    @Column(name = "phone_number")
    private int phoneNumber;

    public Contact() {
    }
    public Contact(Person person, int phoneNumber) {
        this.person = person;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
        if (person.getId()!=null){
            return "Contact{" +
                    "contact="+ id +
                    ", person=" + person.getId() +
                    ", phoneNumber=" + phoneNumber +
                    '}';
        }else {
            return "Contact{" +
                    "contact="+ id +
                    ", phoneNumber=" + phoneNumber +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return phoneNumber == contact.phoneNumber && Objects.equals(id, contact.id) && Objects.equals(person, contact.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, person, phoneNumber);
    }
}
