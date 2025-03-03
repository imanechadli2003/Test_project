package com.example.nbodysimulation.service;

import com.example.nbodysimulation.model.Particle;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements Runnable {
    private final List<Particle> particles = new ArrayList<>();
    private boolean running = true;

    private final double dt = 0.1;

    public SimulationEngine() {
        // Centre du canvas (800x600)
        double centerX = 400;
        double centerY = 300;

        double blackHoleMass = 100000;
        Particle blackHole = new Particle(centerX, centerY, 0, 0, blackHoleMass);
        particles.add(blackHole);

        int numberOfStars = 1000; // Vous pouvez augmenter/diminuer
        for (int i = 0; i < numberOfStars; i++) {
            double radius = 50 + Math.random() * 250;
            double angle = Math.random() * 2 * Math.PI;

            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);

            double mass = 1 + Math.random() * 2;

            double v = Math.sqrt((1 * blackHoleMass) / radius);

            double vx = -v * Math.sin(angle);
            double vy = v * Math.cos(angle);

            Particle star = new Particle(x, y, vx, vy, mass);
            particles.add(star);
        }
    }

    private double[] computeForce(Particle p1, Particle p2) {
        double G = 1;
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        double distanceSquared = dx * dx + dy * dy;
        double distance = Math.sqrt(distanceSquared);
        if (distance < 1e-5) {
            distance = 1e-5; // éviter division par zéro
        }
        double force = G * p1.getMass() * p2.getMass() / distanceSquared;
        return new double[]{ force * dx / distance, force * dy / distance };
    }

    public void update() {
        for (int i = 0; i < particles.size(); i++) {
            Particle p1 = particles.get(i);
            double totalFx = 0;
            double totalFy = 0;

            for (int j = 0; j < particles.size(); j++) {
                if (i == j) continue;
                Particle p2 = particles.get(j);
                double[] force = computeForce(p1, p2);
                totalFx += force[0];
                totalFy += force[1];
            }

            p1.applyForce(totalFx, totalFy, dt);
        }

        for (Particle p : particles) {
            p.updatePosition(dt);
        }
    }

    public List<Particle> getParticles() {
        return particles;
    }
    public synchronized void addRandomParticles(int count) {
        double centerX = 400;
        double centerY = 300;
        double blackHoleMass = 100000;
        for (int i = 0; i < count; i++) {
            double radius = 50 + Math.random() * 250;
            double angle = Math.random() * 2 * Math.PI;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            double mass = 1 + Math.random() * 2;
            double v = Math.sqrt((1 * blackHoleMass) / radius);
            double vx = -v * Math.sin(angle);
            double vy = v * Math.cos(angle);
            Particle star = new Particle(x, y, vx, vy, mass);
            particles.add(star);
        }
    }



    @Override
    public void run() {
        while (running) {
            update();
            try {
                Thread.sleep((long) (dt * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }
}


