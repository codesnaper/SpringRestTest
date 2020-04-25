package com.example.springTestSample.controller;

import com.example.springTestSample.model.Name;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResourceController {

    @GetMapping("/hello")
    public String getHello(){
        return "Hello shubham";
    }

    @GetMapping(value = "/name",produces = MediaType.APPLICATION_JSON_VALUE)
    public Name getName(){
        return new Name("Shubham","Khandelwal");
    }

    @GetMapping(value = "/names",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Name> getNames(){
        List<Name> names = new ArrayList<>();
        names.add(new Name("shubham","khandelwal"));
        names.add(new Name("sina","ach"));
        return  names;
    }

    @PostMapping(value = "/newName",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addName(Name name){
        System.out.println(name);
    }
}
