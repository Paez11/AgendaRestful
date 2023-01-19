package _2DAM.restful.controller;

import _2DAM.restful.exceptions.RecordNotFoundException;
import _2DAM.restful.model.Contact;
import _2DAM.restful.model.Person;
import _2DAM.restful.repository.ContactRepository;
import _2DAM.restful.services.ContactService;
import _2DAM.restful.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ContactController {
    ContactService contactService;
    PersonService personService;
    private final ContactRepository contactRepository;

    @Autowired
    public ContactController(ContactService contactService, PersonService personService,
                             ContactRepository contactRepository) {
        this.contactService = contactService;
        this.personService = personService;
        this.contactRepository = contactRepository;
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getAllContactsById(){
        List<Contact> result = contactService.getAllContacts();
        return new ResponseEntity<>(result,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id){
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact,new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/person/{id}/contacts")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact,@PathVariable(value = "id") Long personId){
        Contact created = contactService.createOrUpdateContact(contact,personId);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.CREATED);
    }
    @PutMapping("person/{pid}/contacts/{id}")
    public ResponseEntity<Contact> UpdateContact(@RequestBody Contact contact,@PathVariable(value = "id") Long id,@PathVariable(value = "pid") Long personId){
        contact.setId(id);
        Person person = personService.getPersonById(personId);
        contact.setPerson(person);
        Contact update = contactService.createOrUpdateContact(contact,personId);
        return new ResponseEntity<>(update, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/contacts/{id}")
    public HttpStatus deleteContactById(@PathVariable("id") Long id) throws RecordNotFoundException {
        contactService.deleteContactById(id);
        return HttpStatus.ACCEPTED;
    }
}

