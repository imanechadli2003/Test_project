package com.example.nbodysimulation.service;

import com.example.nbodysimulation.model.Particle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimulationEngineTest {

    private SimulationEngine simulationEngine;

    @BeforeEach
    public void setUp() {
        simulationEngine = new SimulationEngine();
    }

    @Test
    public void testInitialParticleCount() {
        List<Particle> particles = simulationEngine.getParticles();
        // On s'attend à avoir 1001 particules (1 trou noir + 1000 étoiles)
        assertThat(particles.size()).isEqualTo(1001);
    }

    @Test
    public void testUpdateChangesPositions() {
        List<Particle> particlesBefore = simulationEngine.getParticles();
        // Sauvegarde des positions initiales
        double[] initialPositions = new double[particlesBefore.size() * 2];
        for (int i = 0; i < particlesBefore.size(); i++) {
            Particle p = particlesBefore.get(i);
            initialPositions[i * 2] = p.getX();
            initialPositions[i * 2 + 1] = p.getY();
        }

        // Effectue une mise à jour
        simulationEngine.update();

        // Vérifie qu'au moins une particule a changé de position
        List<Particle> particlesAfter = simulationEngine.getParticles();
        boolean changed = false;
        for (int i = 0; i < particlesAfter.size(); i++) {
            Particle p = particlesAfter.get(i);
            if (p.getX() != initialPositions[i * 2] || p.getY() != initialPositions[i * 2 + 1]) {
                changed = true;
                break;
            }
        }
        assertThat(changed).isTrue();
    }

    @Test
    public void testAddRandomParticles() {
        int initialCount = simulationEngine.getParticles().size();
        simulationEngine.addRandomParticles(20);
        int newCount = simulationEngine.getParticles().size();
        assertThat(newCount).isEqualTo(initialCount + 20);
    }
}
