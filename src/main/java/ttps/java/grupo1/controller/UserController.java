package ttps.java.grupo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.service.UserService;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String get() {
        return this.userService.findByEmail();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("Creando el usuario: " + user.getName());
        userService.saveUser(user);
        System.out.println("Se creo el usuario :)");
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }


}
