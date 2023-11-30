package ttps.java.grupo1.controller;

import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ttps.java.grupo1.DTO.AddMemberDTO;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.service.GroupService;

import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping()
    public ResponseEntity<Iterable<Group>> getGroups() {
        Iterable<Group> groups = groupService.findAll();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroup(@PathVariable("id") Long id) {
        System.out.println("obteniendo el grupo con id: " + id);
        Group group = groupService.findById(id);
        if (group == null) {
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

    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable("id") Long id, @Valid @RequestBody Group group) {
        System.out.println("actualizando grupo con id: " + id);
        Group currentGroup = groupService.findById(id);
        if (currentGroup == null) {
            System.out.println("Grupo con id: " + id + " no encontrado");
            return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
        }
        currentGroup.setName(group.getName());
        currentGroup.setUsers(group.getUsers());
        groupService.update(currentGroup);
        return new ResponseEntity<>(currentGroup, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable("id") Long id) {
        return this.groupService.deleteById(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/members")
    public ResponseEntity<Group> addMember(@Valid @RequestBody AddMemberDTO addMemberDTO) {
        Long groupId = addMemberDTO.getGroupId();
        Long userId = addMemberDTO.getUserId();

        HttpStatus status = this.groupService.addMember(groupId, userId)
                .map(group -> HttpStatus.OK)
                .orElse(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(status);
    }


}

