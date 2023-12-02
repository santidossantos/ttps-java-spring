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
import ttps.java.grupo1.apidoc.GroupApi;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.model.GroupCategory;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.service.GroupCategoryService;
import ttps.java.grupo1.service.GroupService;
import ttps.java.grupo1.service.UserService;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Group> get(@Valid @PathVariable("id") Long id) {
        Optional<Group> optionalGroup = groupService.findById(id);
        return optionalGroup.map(group -> new ResponseEntity<>(group, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Group> create(@Valid @RequestBody Group group) {
        List<User> users = group.getUsers();
        if (users == null || users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Long userId = users.get(0).getId();
        Long categoryId = group.getCategory().getId();
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<GroupCategory> categoryOptional = groupCategoryService.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Group newGroup = groupService.save(group);
        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Group> update(@Valid @PathVariable Long id, @RequestBody Group updatedGroup) {
        Optional<Group> existingGroupOptional = groupService.findById(id);
        if (existingGroupOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Group existingGroup = existingGroupOptional.get();
        Long categoryId = updatedGroup.getCategory().getId();
        Optional<GroupCategory> categoryOptional = groupCategoryService.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        existingGroup.setName(updatedGroup.getName());
        existingGroup.setHidden(updatedGroup.isHidden());
        existingGroup.setCategory(updatedGroup.getCategory());
        Group updatedGroupResult = groupService.save(existingGroup);
        return new ResponseEntity<>(updatedGroupResult, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Group> delete(@Valid @PathVariable("id") Long id) {
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

