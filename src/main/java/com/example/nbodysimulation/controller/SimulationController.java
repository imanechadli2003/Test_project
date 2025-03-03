package com.example.nbodysimulation.controller;

import com.example.nbodysimulation.model.Particle;
import com.example.nbodysimulation.service.SimulationEngine;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class SimulationController {

    private SimulationEngine simulationEngine;

    public SimulationController() {
        simulationEngine = new SimulationEngine();
        Thread simulationThread = new Thread(simulationEngine);
        simulationThread.setDaemon(true);
        simulationThread.start();
    }

    @GetMapping("/api/particles")
    public List<Particle> getParticles() {
        return simulationEngine.getParticles();
    }

    @GetMapping("/api/add-particles")
    public void addParticles(@RequestParam(defaultValue = "50") int count) {
        simulationEngine.addRandomParticles(count);
    }

    @GetMapping("/api/reset-simulation")
    public void resetSimulation() {
        simulationEngine.stop();
        simulationEngine = new SimulationEngine();
        Thread simulationThread = new Thread(simulationEngine);
        simulationThread.setDaemon(true);
        simulationThread.start();
    }
}
