package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Person getAllPersons(int id, Person person){
        person.setId(id);
        personRepository.save(person);
        return person;
    }




    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Данный метод позволяет вернуть всех пользователей
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    // Данный метод позволяет вернуть пользователей по id
    public Person getPersonId(int id){
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.orElse(null);
    }


    public Person getPersonFindByLogin(Person person){
        Optional<Person> person_new_pass= personRepository.findByLogin(person.getLogin());
        return person_new_pass.orElse(null);
    }

    @Transactional
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        personRepository.save(person);
    }



//    public void changePassword(int id, String password) {
//        personRepository.updatePersonById(id,passwordEncoder.encode(password));
//    }

//    public void changePasswordUser(int id, String password) {
//        personRepository.updatePersonById(id,passwordEncoder.encode(password));
//    }

    @Transactional
    public void changePassword(int id, String password){
        personRepository.updatePersonById(id, passwordEncoder.encode(password));
    }
//@Transactional
//    public Person getAllPersons(){int id, Person person){
//    person.setId(id);
//        orderRepository.save(order);
//    }

//    public void changePassword(int id, String password) {
//        personRepository.updatePersonById(id,passwordEncoder.encode(password));
//    }
// Данный метод позволяет получить всех пользователей
public List<Person> getAllPerson(){
    return personRepository.findAll();
}

    // Данный метод позволяет получить пользователя по id
    public Person getPersonById(int id){
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.orElse(null);
    }

    // Данный метод позволяет обновить данные пользователя
    @Transactional
    public void updatePerson(int id, Person person){
        person.setId(id);
        personRepository.save(person);
    }

    // Данный метод позволяет удалить пользовател по id
    @Transactional
    public void deletePerson(int id){
        personRepository.deleteById(id);
    }



}
