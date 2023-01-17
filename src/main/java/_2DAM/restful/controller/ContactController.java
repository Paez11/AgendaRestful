package _2DAM.restful.controller;

import _2DAM.restful.exceptions.RecordNotFoundException;
import _2DAM.restful.model.Contact;
import _2DAM.restful.model.Person;
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

    @Autowired
    ContactService contactService;

    @Autowired
    PersonService personService;

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getAllContactsById(){
        List<Contact> result = contactService.getAllContacts();
        return new ResponseEntity<>(result,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable long id){
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact,new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
        Contact created = contactService.createOrUpdateContact(contact);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.CREATED);
    }
    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> UpdateContact(@RequestBody Long id) throws RecordNotFoundException {
       Contact update = getContactById(id).getBody();
       return new ResponseEntity<>(update,new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/contacts/{id}")
    public HttpStatus deleteContactById(@PathVariable("id") Long id) throws RecordNotFoundException {
        contactService.deleteContactById(id);
        return HttpStatus.ACCEPTED;
    }
}

