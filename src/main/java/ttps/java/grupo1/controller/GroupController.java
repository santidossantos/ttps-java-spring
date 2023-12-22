package ttps.java.grupo1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ttps.java.grupo1.DTO.AddMemberDTO;
import ttps.java.grupo1.DTO.GroupDTO;
import ttps.java.grupo1.apidoc.GroupApi;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.model.GroupCategory;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.service.GroupCategoryService;
import ttps.java.grupo1.service.GroupService;
import ttps.java.grupo1.service.UserService;

import java.util.*;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(value = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController implements GroupApi {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupCategoryService groupCategoryService;

    @GetMapping()
    public ResponseEntity<List<Group>> get() {
        List<Group> groupList = this.groupService.findAll();
        return groupList.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(groupList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Group> get(@Valid @PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        Optional<Group> optionalGroup = groupService.findById(id);
        if (optionalGroup.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Group> myGroups = groupService.findMyGroups(token);
        if (!myGroups.contains(optionalGroup.get())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(optionalGroup.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody GroupDTO groupDTO) {
        Map<String, String> errorResponse = new HashMap<>();
        if (groupDTO.getCreator() == null) {
            errorResponse.put("message", "The creator of the group cant be null");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = userService.findById(groupDTO.getCreator().getId());
        if (user.isEmpty()) {
            errorResponse.put("message", "The user creator with id provided doesnt exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Optional<GroupCategory> category = groupCategoryService.findById(groupDTO.getCategory().getId());
        if (category.isEmpty()) {
            errorResponse.put("message", "The category with id provided doesnt exist");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<User> userList = Arrays.asList(groupDTO.getCreator());
        Group group = new Group(groupDTO.getName(), groupDTO.getHidden(), userList, groupDTO.getCategory(), groupDTO.getImg());
        Group newGroup = groupService.save(group);
        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @PathVariable Long id,@Valid @RequestBody GroupDTO groupDTO) {
        Map<String, String> errorResponse = new HashMap<>();
        Optional<Group> optionalGroup = groupService.findById(id);
        if (optionalGroup.isEmpty()) {
            errorResponse.put("message", "The group with id provided doesnt exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if (groupDTO.getCreator() != null) {
            errorResponse.put("message", "The creator of the group cant be changed");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Optional<GroupCategory> category = groupCategoryService.findById(groupDTO.getCategory().getId());
        if (category.isEmpty()) {
            errorResponse.put("message", "The category with id provided doesnt exist");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Group group = new Group(groupDTO.getName(), groupDTO.getHidden(), null, groupDTO.getCategory(), groupDTO.getImg());
        group.setUsers(optionalGroup.get().getUsers());
        group.setId(id);
        Group newGroup = groupService.update(group);
        return new ResponseEntity<>(newGroup, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Group> delete(@Valid @PathVariable("id") Long id) {
        return this.groupService.deleteById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/members")
    public ResponseEntity<Object> addMember(@Valid @RequestBody AddMemberDTO addMemberDTO) {
        Map<String, String> errorResponse = new HashMap<>();
        Long userId = addMemberDTO.getUserId();
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty()) {
            errorResponse.put("message", "The user with id provided doesnt exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Long groupId = addMemberDTO.getGroupId();
        Optional<Group> optionalGroup = groupService.findById(groupId);
        if (optionalGroup.isEmpty()) {
            errorResponse.put("message", "The group with id provided doesnt exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Group group = optionalGroup.get();
        List<User> users = group.getUsers();
        if (users.contains(optionalUser.get())) {
            errorResponse.put("message", "The user is already a member of the group");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        users.add(optionalUser.get());
        group.setUsers(users);
        Group newGroup = groupService.update(group);
        return new ResponseEntity<>(newGroup, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<List<Group>> findMyGroups(@RequestHeader("Authorization") String token) {
        List<Group> myGroups = groupService.findMyGroups(token);
        return myGroups.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(myGroups, HttpStatus.OK);
    }

}

