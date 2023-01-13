package _2DAM.restful.controller;

import _2DAM.restful.exceptions.RecordNotFoundException;
import _2DAM.restful.model.Person;
import _2DAM.restful.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Person entity = personService.getPersonById(id);
        return new ResponseEntity<Person>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Create person if not exists or update if exits
     * @param person Person to create or update
     * @return Person
     */
    @PostMapping
    @PutMapping
    public ResponseEntity<Person> createOrUpdate(@RequestBody Person person) throws RecordNotFoundException {
        Person created = personService.createOrUpdatePerson(person);
        return new ResponseEntity<Person>(created, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete person if exists
     * @param id Person id
     * @return Status
     */
    @DeleteMapping("/{id}")
    public HttpStatus deletePersonById(@PathVariable("id") Long id) throws RecordNotFoundException {
        personService.deletePersonById(id);
        return HttpStatus.ACCEPTED;
    }

}
