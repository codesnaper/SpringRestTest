package com.example.springTestSample.controller;

import com.example.springTestSample.model.Name;
import com.google.gson.Gson;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//In order for the unit test to run a batch job, the framework must load the job's ApplicationContext. Two annotations are used to trigger this:
//@RunWith(SpringJUnit4ClassRunner.class): Indicates that the class should use Spring's JUnit facilities.
//@ContextConfiguration(locations = {...}): Indicates which XML files contain the ApplicationContext.


//For building the request we use MockMvcRequestBuilders
//For assertion we use andExpect it accept parameter matcher
//For testing the result of url MockMvcResultMatchers
@RunWith(SpringJUnit4ClassRunner.class)
public class ResourceControllerTest {

    @InjectMocks
    ResourceController resourceController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();
    }

    @Test
    public void getHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello shubham"));
    }

    @Test
    public void getName() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/name").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Shubham")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("Khandelwal")));
    }

    @Test
    public void getNames() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/names").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.notNullValue()));
    }

    @Test
    public void addName() throws Exception{
        Name name = new Name("shubham","khandelwal");
        mockMvc.perform(MockMvcRequestBuilders.post("/newName").contentType(MediaType.APPLICATION_JSON_VALUE).content(new Gson().toJson(name)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}