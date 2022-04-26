package com.matheuscordeiro.bffageofempiresapi.client;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.matheuscordeiro.bffageofempiresapi.BffAgeOfEmpiresApiApplication;
import com.matheuscordeiro.bffageofempiresapi.clients.interfaces.AgeOfEmpiresClient;
import com.matheuscordeiro.bffageofempiresapi.clients.utils.ResourceUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

@WireMockTest(httpPort = 8081)
@SpringBootTest(classes = BffAgeOfEmpiresApiApplication.class)
@TestPropertySource(locations = "classpath:application.yml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AgeOfEmpiresClientTest {
    @Autowired
    private AgeOfEmpiresClient ageOfEmpiresClient;

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
    @DisplayName("1 - Obtendo um personagem através do nome")
    public void testGetCharacter_200() {
        WireMock.stubFor(WireMock
                .get(baseUrlClient + "/civilizations")
                .willReturn(WireMock.aResponse().withStatus(200).withHeader("Content-Type", "application/json")
                        .withBody(ResourceUtils.getContentFile(listCivilizationsOK))));

        final var civilizations = ageOfEmpiresClient.findCivilizations();

        assertFalse(civilizations.getCivilizations().isEmpty());
        assertThat(civilizations.getCivilizations().size(), equalTo(32));
        assertThat(civilizations.getCivilizations().get(0).getId(), equalTo(1L));
        assertThat(civilizations.getCivilizations().get(0).getName(), equalTo("Aztecs"));
        assertThat(civilizations.getCivilizations().get(0).getArmyType(), equalTo("Infantry and Monk"));
        assertThat(civilizations.getCivilizations().get(0).getTeamBonus(), equalTo("Relics generate +33% gold"));
        assertThat(civilizations.getCivilizations().get(0).getUniqueUnit().get(0), equalTo("https://age-of-empires-2-api.herokuapp.com/api/v1/unit/jaguar_warrior"));
        assertThat(civilizations.getCivilizations().get(0).getUniqueTech().get(0), equalTo("https://age-of-empires-2-api.herokuapp.com/api/v1/technology/garland_wars"));
        assertThat(civilizations.getCivilizations().get(0).getCivilizationBonus().get(0), equalTo("Villagers carry +5"));
    }
}
