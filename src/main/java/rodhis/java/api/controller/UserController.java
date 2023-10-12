package rodhis.java.api.controller;

import rodhis.java.api.model.User;
import rodhis.java.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/users") toda requisição vai começar com a rota users
public class UserController {
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

    @GetMapping
    public String welcome(){
        return "Welcome to My Spring Boot Web API";
    }
    @GetMapping("/users")
    public String users() {
        return "Authorized user";
    }
    @GetMapping("/managers")
    public String managers() {
        return "Authorized manager";
    }
}
