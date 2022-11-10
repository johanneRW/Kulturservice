package com.kulturservice;

import com.kulturservice.Repository.UserRepository;
import com.kulturservice.controller.UserController;
import com.kulturservice.model.User;
import com.kulturservice.security.JwtUserDetailsService;
import com.kulturservice.security.TokenManager;
import com.kulturservice.service.RoleService;
import com.kulturservice.service.UserService;
import com.kulturservice.service.VenueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest() //
class KulturserviceApplicationTests {
    private MockMvc mockMvc;
    private UserService userService;
    private JwtUserDetailsService jwtUserDetailsService;
    private JwtUserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;
    private VenueService venueService;
    private RoleService roleService;

    @Autowired
    UserRepository userRepository;


    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userDetailsService, authenticationManager, tokenManager, userService, venueService, roleService, jwtUserDetailsService)).build();
    }

//    @Test
//    public void index() throws Exception {
//        mockMvc.perform(get("/"))
//                .andExpect(status().isOk())  // http 200 kode for "ok"
//                .andExpect(view().name("index"));
//    }

    @Test
    public void signup() throws Exception {
        mockMvc.perform(post("/signup")
                        .param("username", "anna")
                        .param("password", "444"))
                .andExpect(status().isOk())
                .andDo(print());

//        mockMvc.perform(post("/addUser")
//                        .param("id", "123")
//                        .param("username", "anna")
//                        .param("password", "444"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("index"))
//                .andExpect(model().attribute("name","anna"))
//                .andDo(print());
    }

    ;


    @AutoConfigureMockMvc(printOnlyOnFailure = false)

    @Test
    public void shouldSignup() throws Exception {
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                      .content("{\"username\":\"anna\",\"password\":\"444\"}")
                        .content(("""
                                {"username":"anna",
                                {"password":"444"}
                                """))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

   /* @Test
    public void shouldCreatePerson() throws Exception {
        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"age\":30}")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }*/


}
