package com.matheuscordeiro.bffageofempiresapi.service;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.matheuscordeiro.bffageofempiresapi.BffAgeOfEmpiresApiApplication;
import com.matheuscordeiro.bffageofempiresapi.clients.utils.ResourceUtils;
import com.matheuscordeiro.bffageofempiresapi.services.interfaces.CivilizationService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

@WireMockTest(httpPort = 8083)
@SpringBootTest(classes = BffAgeOfEmpiresApiApplication.class)
@TestPropertySource(locations = "classpath:application.yml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CivilizationServiceTest {
    @Autowired
    private CivilizationService service;

    @Value("classpath:json/listCivilizations_OK.json")
    private Resource listCivilizationsOK;

    @Value("classpath:json/listCivilizations_NOT_FOUND.json")
    private Resource listCivilizationsNotFound;

    @Value("${client.url}")
    private String baseUrlClient;

    @Value("${app.url}")
    private String baseUrl;


    @Test
    @Order(1)
    @DisplayName("1 - Executando o Service com a orquestração das chamadas de API Age of empires")
    public void testFindCivilization() {
        WireMock.stubFor(WireMock
                .get(baseUrlClient + "/civilizations")
                .willReturn(WireMock.aResponse().withStatus(200).withHeader("Content-Type", "application/json")
                        .withBody(ResourceUtils.getContentFile(listCivilizationsOK))));

        final var result = service.getCivilizations();

        assertFalse(result.isEmpty());
        assertThat(result.size(), equalTo(32));
        assertThat(result.get(0).getId(), equalTo(1L));
        assertThat(result.get(0).getName(), equalTo("Aztecs"));
        assertThat(result.get(0).getArmyType(), equalTo("Infantry and Monk"));
        assertThat(result.get(0).getTeamBonus(), equalTo("Relics generate +33% gold"));
    }

}
