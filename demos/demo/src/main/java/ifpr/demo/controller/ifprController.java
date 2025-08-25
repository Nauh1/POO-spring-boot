package ifpr.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Alo")

public class ifprController {
    @GetMapping
    public String a(){
        return "Boa";
    }
}
