package ttps.java.grupo1.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttps.java.grupo1.controller.GroupController;
import ttps.java.grupo1.model.Group;

@Component
@RestController
@RequestMapping("/init-db")
public class GroupSeeds {

    @Autowired
    private GroupController groupController;

    @PostMapping("/initialize")
    public void seed() {
        System.out.println("Initializing seed groups...");
        try {
            Group group1 = new Group("Viaje a brasil", false, null);
            ResponseEntity<Group> response1 = groupController.createGroup(group1);
        } catch (Exception e) {
            System.out.println("Error initializing seed groups: " + e.getMessage());
        }
    }
}
