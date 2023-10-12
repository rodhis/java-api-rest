package rodhis.java.api.controller;

import rodhis.java.api.model.User;
import rodhis.java.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") //toda requisição vai começar com a rota users
public class UserController {

    @Autowired
    private UserService service;
    @PostMapping
    public void postUser(@RequestBody User user) {
        service.createUser(user);
    }

//    código anterior, com uso do CRUD via annotations
//    @Autowired
//    private UserRepository repository;
//    @GetMapping() //este método será utilizado exclusivamente nessa rota users, evitando conflitos
//    public List<User> getUsers() {
//        return repository.listAll();
//    }
//
//    @GetMapping("/{username}")
//    public User getByUsername(@PathVariable("username") String username) {
//        return repository.findByUsername(username);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser (@PathVariable("id") Integer id) {
//        repository.remove(id);
//    }
//
//    @PostMapping()
//    public void postUser(@RequestBody User user) { //transforma o json recebido num formato para deleção
//        repository.save(user);
//    }
//
//    @PutMapping
//    public void putUser(@RequestBody User user) {
//        repository.save(user);
//    }

}
