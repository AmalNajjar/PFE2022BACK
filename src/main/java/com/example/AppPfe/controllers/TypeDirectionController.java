package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.TypeDirection;
import com.example.AppPfe.repository.TypeDirectionrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api/test/")
public class TypeDirectionController {
    @Autowired
    TypeDirectionrepo typeDirectionrepo;
    @PostMapping("/type")
    TypeDirection Save(@RequestBody()TypeDirection typeDirection){
        return this.typeDirectionrepo.save(typeDirection);
    }
    @GetMapping("/type")
    List<TypeDirection> gettAll(){
        return typeDirectionrepo.findAll();
    }

    @GetMapping("type/{id}")
    Optional<TypeDirection> gettById(@PathVariable("id") Long id){
        System.out.println(id);
        return typeDirectionrepo.findById(id);
    }

}
