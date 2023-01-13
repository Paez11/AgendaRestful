package _2DAM.restful.services;

import _2DAM.restful.exceptions.RecordNotFoundException;
import _2DAM.restful.model.Person;
import _2DAM.restful.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    /**
     * Get all persons
     * @return List of persons
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Get person by id if exists or throw RecordNotFoundException
     * @param id Person id
     * @return Person
     */
    public Person getPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if(person.isPresent()){
            return person.get();
        } else {
            throw new RecordNotFoundException("Person not found.",id);
        }
    }

    /**
     * Create a new person or update if exists
     * @param person Person
     * @return Person
     */
    public Person createOrUpdatePerson(Person person) {
        if(person.getId()!=null) {
            //update
            Optional<Person> p = personRepository.findById(person.getId());
            if(p.isPresent()){
                //existe, actualizo
                person = personRepository.save(person);
            } else {
                throw new RecordNotFoundException("Person not found", person);
            }
        } else{
            //insert
            person = personRepository.save(person);
        }
        return person;
    }

    /**
     * Delete person by id if exists or throw RecordNotFoundException
     * @param id Person id
     */
    public void deletePersonById(Long id) {
        Optional<Person> p = personRepository.findById(id);
        if(p.isPresent()) {
            personRepository.deleteById(id);
        } else  {
            throw new RecordNotFoundException("Person not found",id);
        }
    }
}
