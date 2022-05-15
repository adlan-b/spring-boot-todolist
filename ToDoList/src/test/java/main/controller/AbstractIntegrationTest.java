package main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.model.ToDo;
import main.repository.ToDoRepository;
import main.service.ToDoService;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
@WebAppConfiguration
public abstract class AbstractIntegrationTest {
    protected String url;

    @Autowired
    protected WebApplicationContext ctx;

    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper mapper;

    @MockBean
    protected ToDoService toDoService;




    @Before
    public void setUp() throws Exception {
        url = "http://localhost:8080/todos";
        mvc = MockMvcBuilders.webAppContextSetup(ctx).build();

    }


}
