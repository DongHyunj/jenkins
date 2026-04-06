package org.example.jenkins;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cicd")
public class CicdController {

    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.ok("성공");
    }
}
