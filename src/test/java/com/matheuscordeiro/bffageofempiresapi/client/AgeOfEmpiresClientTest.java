package com.matheuscordeiro.bffageofempiresapi.client;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.matheuscordeiro.bffageofempiresapi.BffAgeOfEmpiresApiApplication;
import com.matheuscordeiro.bffageofempiresapi.clients.interfaces.AgeOfEmpiresClient;
import com.matheuscordeiro.bffageofempiresapi.clients.utils.ResourceUtils;
import feign.FeignException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Value("classpath:json/civilization_OK.json")
    private Resource civilizationOK;

    @Value("${client.url}")
    private String baseUrlClient;

    @Value("${app.url}")
    private String baseUrl;

    @Test
    @Order(1)
    @DisplayName("Obtendo lista de civilization")
    public void testGetCivilizationsOk() {
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

    @Test
    @Order(2)
    @DisplayName("Obtendo civilization")
    public void testGetCivilizationOk() {
        WireMock.stubFor(WireMock
                .get(baseUrlClient + "/civilization/1")
                .willReturn(WireMock.aResponse().withStatus(200).withHeader("Content-Type", "application/json")
                        .withBody(ResourceUtils.getContentFile(civilizationOK))));

        final var civilizations = ageOfEmpiresClient.findCivilizationById(1L);

        assertThat(civilizations.getId(), equalTo(1L));
        assertThat(civilizations.getName(), equalTo("Aztecs"));
        assertThat(civilizations.getArmyType(), equalTo("Infantry and Monk"));
        assertThat(civilizations.getTeamBonus(), equalTo("Relics generate +33% gold"));
        assertThat(civilizations.getUniqueUnit().get(0), equalTo("https://age-of-empires-2-api.herokuapp.com/api/v1/unit/jaguar_warrior"));
        assertThat(civilizations.getUniqueTech().get(0), equalTo("https://age-of-empires-2-api.herokuapp.com/api/v1/technology/garland_wars"));
        assertThat(civilizations.getCivilizationBonus().get(0), equalTo("Villagers carry +5"));
    }

    @Test
    @Order(3)
    @DisplayName("Retornando um erro Not Found do Client da API")
    public void testGetCivilizationNotFound() {
        WireMock.stubFor(WireMock
                .get(baseUrlClient + "/civilization/1")
                .willReturn(WireMock.aResponse().withStatus(404).withHeader("Content-Type", "application/json")
                        .withBody(ResourceUtils.getContentFile(listCivilizationsNotFound))));

        assertThrows(FeignException.NotFound.class, () -> {
            ageOfEmpiresClient.findCivilizationById(33L);
        });
    }
}
