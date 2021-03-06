package com.matheuscordeiro.bffageofempiresapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.matheuscordeiro.bffageofempiresapi.BffAgeOfEmpiresApiApplication;
import com.matheuscordeiro.bffageofempiresapi.clients.utils.ResourceUtils;
import com.matheuscordeiro.bffageofempiresapi.dtos.response.CivilizationResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

@WireMockTest(httpPort = 8085)
@SpringBootTest(classes = BffAgeOfEmpiresApiApplication.class)
@TestPropertySource(locations = "classpath:application.yml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CivilizationsControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Value("classpath:json/listCivilizations_OK.json")
    private Resource listCivilizationsOK;

    @Value("classpath:json/listCivilizations_NOT_FOUND.json")
    private Resource listCivilizationsNotFound;

    @Value("classpath:json/civilization_OK.json")
    private Resource civilizationOK;

    @Value("${client.url}")
    private String baseUrlClient;

    @Value("${app.url}")
    private String baseUrl;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @Order(1)
    @DisplayName("Efetuando a busca de uma civiliza????o de acordo com o crit??rio de pesquisa")
    public void testFindCivilizations() throws Exception {
        WireMock.stubFor(WireMock.get(baseUrlClient + "/civilizations")
                .willReturn(WireMock.aResponse().withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ResourceUtils.getContentFile(listCivilizationsOK))));

        final var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/civilizations"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        final var result = mapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<CivilizationResponse>>() {});

        assertFalse(result.isEmpty());
        assertThat(result.size(), equalTo(32));
        assertThat(result.get(0).getId(), equalTo(1L));
        assertThat(result.get(0).getName(), equalTo("Aztecs"));
        assertThat(result.get(0).getArmyType(), equalTo("Infantry and Monk"));
        assertThat(result.get(0).getTeamBonus(), equalTo("Relics generate +33% gold"));
    }

    @Test
    @Order(2)
    @DisplayName("Efetuando a busca de uma civiliza????o por id")
    public void testFindCivilizationById() throws Exception {
        WireMock.stubFor(WireMock.get(baseUrlClient + "/civilization/1")
                .willReturn(WireMock.aResponse().withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ResourceUtils.getContentFile(civilizationOK))));

        final var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        final var result = mapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<CivilizationResponse>() {});

        assertThat(result.getId(), equalTo(1L));
        assertThat(result.getName(), equalTo("Aztecs"));
        assertThat(result.getArmyType(), equalTo("Infantry and Monk"));
        assertThat(result.getTeamBonus(), equalTo("Relics generate +33% gold"));
    }

    @Test
    @Order(3)
    @DisplayName("Civiliza????o n??o encontrado")
    public void testNotFoundFindCivilization() throws Exception {
        WireMock.stubFor(WireMock.get(baseUrlClient + "/civilization/1").willReturn(WireMock.aResponse().withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ResourceUtils.getContentFile(listCivilizationsNotFound))));

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/33"))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();
    }
}
