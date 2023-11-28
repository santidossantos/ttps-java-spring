package ttps.java.grupo1.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttps.java.grupo1.controller.GroupCategoryController;
import ttps.java.grupo1.controller.GroupController;
import ttps.java.grupo1.model.GroupCategory;

@Component
@RestController
@RequestMapping("/init-db")
public class GroupCategorySeeds {

    @Autowired
    private GroupCategoryController groupCategoryController;

    @RequestMapping("/initialize")
    public void seed() {
        System.out.println("Initializing seed group categories...");
        try {
            GroupCategory groupCategory1 = new GroupCategory("Viajes","avioncito.png");
            GroupCategory groupCategory2 = new GroupCategory("Trabajo", "maletin.png");
            GroupCategory groupCategory3 = new GroupCategory("Familia", "familia.png");
            groupCategoryController.createGroupCategory(groupCategory1);
            groupCategoryController.createGroupCategory(groupCategory2);
            groupCategoryController.createGroupCategory(groupCategory3);
        } catch (Exception e) {
            System.out.println("Error initializing seed group categories: " + e.getMessage());
        }
    }
}
