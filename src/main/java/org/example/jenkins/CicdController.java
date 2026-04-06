package org.example.jenkins;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cicd")
public class CicdController {

    @GetMapping("/test")
    public String test() {
        return "v10";
    }
}
