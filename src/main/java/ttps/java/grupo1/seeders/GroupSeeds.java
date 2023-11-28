package ttps.java.grupo1.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttps.java.grupo1.controller.GroupCategoryController;
import ttps.java.grupo1.controller.GroupController;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.model.GroupCategory;
import ttps.java.grupo1.repository.GroupCategoryRepository;

@Component
@RestController
@RequestMapping("/init-db")
public class GroupSeeds {

    @Autowired
    private GroupController groupController;

    @Autowired
    private GroupCategoryRepository groupCategoryRepository;

    @PostMapping("/initialize")
    public void seed() {
        System.out.println("Initializing seed groups...");
        try {
            GroupCategory groupCategory = groupCategoryRepository.findFirstByOrderById();
            Group group1 = new Group("Viaje a brasil", false, null, groupCategory);
            Group group2 = new Group("Viaje a china", false, null, groupCategory);
            Group group3 = new Group("Viaje a japon", false, null, groupCategory);
            ResponseEntity<Group> response1 = groupController.createGroup(group1);
            ResponseEntity<Group> response2 = groupController.createGroup(group2);
            ResponseEntity<Group> response3 = groupController.createGroup(group3);
        } catch (Exception e) {
            System.out.println("Error initializing seed groups: " + e.getMessage());
        }
    }
}
