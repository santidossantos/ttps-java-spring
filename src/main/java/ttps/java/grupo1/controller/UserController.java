package ttps.java.grupo1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.java.grupo1.DTO.FriendRequestDTO;
import ttps.java.grupo1.DTO.UserDTO;
import ttps.java.grupo1.apidoc.UserApi;
import ttps.java.grupo1.exception.UserNotFoundException;
import ttps.java.grupo1.model.Expense;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.service.UserService;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements UserApi {

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

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable("username") String username) {
        Optional<User> user = this.userService.findByUsername(username);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @Valid @RequestBody UserDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());

        try {
            userService.updateById(id, user);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        return this.userService.deleteById(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/friends")
    public ResponseEntity<User> addFriend(@Valid @RequestBody FriendRequestDTO requestDTO) {
        Long id = requestDTO.getId();
        Long friendId = requestDTO.getFriendId();

        HttpStatus status = this.userService.addFriend(id, friendId)
                ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    @GetMapping("/{id}/friends")
    public ResponseEntity<List<User>> getFriends(@PathVariable("id") Long id) {
        Optional<User> user = this.userService.findById(id);
        return user.map(value -> new ResponseEntity<>(value.getFriends(), HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/friends/{friendId}")
    public ResponseEntity<User> getFriend(@PathVariable("id") Long id, @PathVariable("friendId") Long friendId) {
        Optional<User> user = this.userService.findById(id);
        return user.map(value -> new ResponseEntity<>(value.getFriends().get(0), HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/groups")
    public ResponseEntity<List<Group>> getGroups(@PathVariable("id") Long id) {
        Optional<User> user = this.userService.findById(id);
        return user.map(value -> new ResponseEntity<>(value.getGroups(), HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/{username}/expense")
    public ResponseEntity<List<Expense>> getExpensesWithUsername(@PathVariable("username") String username) {
        List<Expense> expenses = this.userService.getExpensesOfUserWithUsername(username);
        return expenses.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(expenses, HttpStatus.OK);
    }
}
