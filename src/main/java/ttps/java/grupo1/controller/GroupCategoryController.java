package ttps.java.grupo1.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttps.java.grupo1.apidoc.GroupCategoryApi;
import ttps.java.grupo1.model.GroupCategory;
import ttps.java.grupo1.service.GroupCategoryService;

@RestController
@Validated
@RequestMapping(value = "/groupCategorys", produces = MediaType.APPLICATION_JSON_VALUE)

public class GroupCategoryController implements GroupCategoryApi {

    @Autowired
    private GroupCategoryService groupCategoryService;

    @PostMapping
    public ResponseEntity<GroupCategory> create(@Valid @RequestBody GroupCategory groupCategory) {
        System.out.println("creando categoria de grupo: " + groupCategory.getName());
        GroupCategory newGroupCategory = groupCategoryService.save(groupCategory);
        return new ResponseEntity<>(newGroupCategory, HttpStatus.CREATED);
    }


}
