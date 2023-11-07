package ttps.java.grupo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    servicio usuarioService;

    @GetMapping("/")
    public String helloWorld() {
        return this.usuarioService.resolver()
    }

}
