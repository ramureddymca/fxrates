package com.viseo.fx.controller;

import com.viseo.fx.config.WebConfig;
import com.viseo.fx.model.User;
import com.viseo.fx.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User("101", "A", "abc@gmail.com");
        when(userService.getUserById("101")).thenReturn(user);
        mockMvc.perform(get("/user/profile/101"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.userId", is("101")))
                .andExpect(jsonPath("$.pricingTier", is("A")))
                .andExpect(jsonPath("$.email", is("abc@gmail.com")));
        verify(userService, times(1)).getUserById("101");
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testGetUserByIdFailNotFound() throws Exception {
        when(userService.getUserById("104")).thenReturn(null);
        mockMvc.perform(get("/user/profile/104"))
                .andExpect(status().isNotFound());

    }
}
