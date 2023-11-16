package ttps.java.grupo1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.service.UserService;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> get() {
        List<User> userList = this.userService.findAll();

        return userList.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable("id") Long id) {
        Optional<User> user = this.userService.findById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        return new ResponseEntity<>(this.userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> update(@Valid @RequestBody User user) {
        return new ResponseEntity<>(this.userService.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        Optional<User> user = this.userService.findById(id);
        if(user.isPresent()) {
            this.userService.deleteById(id);
        }
        return user.isPresent()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
