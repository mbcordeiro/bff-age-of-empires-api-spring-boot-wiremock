package com.matheuscordeiro.bffageofempiresapi.controller;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.matheuscordeiro.bffageofempiresapi.BffAgeOfEmpiresApiApplication;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WireMockTest(httpPort = 8085)
@SpringBootTest(classes = BffAgeOfEmpiresApiApplication.class)
@TestPropertySource(locations = "classpath:application-bffControllerTest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CivilizationsControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @Order(1)
    @DisplayName("1 - Efetuando a busca de uma civilização de acordo com o critério de pesquisa")
    public void testFindCharacters() throws Exception {

    }


    @Test
    @Order(2)
    @DisplayName("2 - Civilização não encontrado")
    public void testNotFoundCharacter() throws Exception {

    }
}
