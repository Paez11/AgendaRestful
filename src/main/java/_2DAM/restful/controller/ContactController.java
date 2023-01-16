package _2DAM.restful.controller;

import _2DAM.restful.model.Contact;
import _2DAM.restful.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts(){
        List<Contact> result = contactService.getAllContacts();
        return new ResponseEntity<List<Contact>>(result,new HttpHeaders(), HttpStatus.OK);
    }
}

