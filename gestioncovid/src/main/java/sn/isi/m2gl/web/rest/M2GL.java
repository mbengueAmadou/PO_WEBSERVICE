package sn.isi.m2gl.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class M2GL {

    @GetMapping("/classe")
    public ResponseEntity<String> getNomDeLaClasse(){

        return new ResponseEntity<>("M2GL", HttpStatus.OK);
    }
}
