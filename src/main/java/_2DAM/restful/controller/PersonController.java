package _2DAM.restful.controller;

import _2DAM.restful.exceptions.RecordNotFoundException;
import _2DAM.restful.model.Person;
import _2DAM.restful.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * Get all persons
     * @return List of persons
     */
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> result = personService.getAllPersons();
        return new ResponseEntity<List<Person>>(result,new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Get person by id if exists or throw RecordNotFoundException
     * @param id Person id
     * @return Person
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<Person>> getPersonById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Person entity = personService.getPersonById(id);
        return new ResponseEntity<List<Person>>((List<Person>) entity, new HttpHeaders(), HttpStatus.OK);
    }
}
