package _2DAM.restful.services;

import _2DAM.restful.exceptions.RecordNotFoundException;
import _2DAM.restful.model.Contact;
import _2DAM.restful.model.Person;
import _2DAM.restful.repository.ContactRepository;
import _2DAM.restful.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    PersonService personService;

    /**
     * Get all contacts
     * @return List of contacts
     */
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * Get contact by id if exists or throw RecordNotFoundException
     * @param id Contact id
     * @return Contact
     */
    public Contact getContactById(Long id){
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()){
            return contact.get();
        }else{
            throw  new RecordNotFoundException("No contact record exist for given id", id);
        }
    }

    /**
     * Create a new contact or update if exists
     * @param contact Contact
     * @return Contact
     */
    public Contact createOrUpdateContact(Contact contact,long id){
        if (contact.getId()!=null){
            Optional<Contact> c = contactRepository.findById(contact.getId());
            if (c.isPresent()){
                contact = contactRepository.save(contact);
            }else{
                throw new RecordNotFoundException("No contact record exist for given id", contact);
            }
        }else{
            Person person = personService.getPersonById(id);
            contact.setPerson(person);
            person.getContacts().add(contact);
            contact = contactRepository.save(contact);
        }
        return contact;
    }

    /**
     * Delete contact by id if exists or throw RecordNotFoundException
     * @param id Contact id
     */
    public void deleteContactById(Long id){
        Optional<Contact> c = contactRepository.findById(id);
        if (c.isPresent()){
            contactRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No contact record exist for given id", id);
        }
    }

}
