package ttps.java.grupo1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ttps.java.grupo1.apidoc.GroupCategoryApi;
import ttps.java.grupo1.model.GroupCategory;
import ttps.java.grupo1.service.GroupCategoryService;

import java.util.List;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(value = "/group-categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupCategoryController implements GroupCategoryApi {

    @Autowired
    private GroupCategoryService groupCategoryService;

    @PostMapping
    public ResponseEntity<GroupCategory> create(@Valid @RequestBody GroupCategory groupCategory) {
        System.out.println("creando categoria de grupo: " + groupCategory.getName());
        GroupCategory newGroupCategory = groupCategoryService.save(groupCategory);
        return new ResponseEntity<>(newGroupCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity <List<GroupCategory>> getAll() {
        List<GroupCategory> groupCategoryList = groupCategoryService.findAll();
        return groupCategoryList.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(groupCategoryList, HttpStatus.OK);
    }

}
