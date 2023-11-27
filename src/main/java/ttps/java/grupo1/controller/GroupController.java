package ttps.java.grupo1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.service.GroupService;

@RestController
@Validated
@RequestMapping(value = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroup(@PathVariable("id") Long id) {
        System.out.println("obteniendo el grupo con id: " + id);
        Group group = groupService.findById(id);
        if(group == null) {
            System.out.println("Grupo con id: " + id + " no encontrado");
            return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@Valid @RequestBody Group group) {
        System.out.println("creando grupo: " + group.getName());
        Group newGroup = groupService.save(group);
        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
    }




}

