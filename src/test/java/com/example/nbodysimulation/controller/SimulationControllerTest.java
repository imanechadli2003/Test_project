package com.example.nbodysimulation.controller;

import com.example.nbodysimulation.model.Particle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimulationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetParticles() {
        ResponseEntity<Particle[]> response = restTemplate.getForEntity("/api/particles", Particle[].class);
        Particle[] particles = response.getBody();
        assertThat(particles).isNotEmpty();
    }

    @Test
    public void testAddParticles() {
        ResponseEntity<Particle[]> beforeResponse = restTemplate.getForEntity("/api/particles", Particle[].class);
        int initialCount = beforeResponse.getBody().length;

        restTemplate.getForEntity("/api/add-particles?count=10", Void.class);

        ResponseEntity<Particle[]> afterResponse = restTemplate.getForEntity("/api/particles", Particle[].class);
        int newCount = afterResponse.getBody().length;
        assertThat(newCount).isEqualTo(initialCount + 10);
    }

    @Test
    public void testResetSimulation() {
        restTemplate.getForEntity("/api/add-particles?count=20", Void.class);
        ResponseEntity<Particle[]> beforeReset = restTemplate.getForEntity("/api/particles", Particle[].class);
        int countBefore = beforeReset.getBody().length;

        restTemplate.getForEntity("/api/reset-simulation", Void.class);
        ResponseEntity<Particle[]> afterReset = restTemplate.getForEntity("/api/particles", Particle[].class);
        int countAfter = afterReset.getBody().length;
        assertThat(countAfter).isEqualTo(1001);
    }
}
