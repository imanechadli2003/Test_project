package com.example.nbodysimulation.model;

public class Particle {
    private double x, y;   // Position
    private double vx, vy; // Vitesse
    private double mass;   // Masse

    public Particle(double x, double y, double vx, double vy, double mass) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.mass = mass;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getVx() { return vx; }
    public double getVy() { return vy; }
    public double getMass() { return mass; }

    public void updatePosition(double dt) {
        this.x += vx * dt;
        this.y += vy * dt;
    }

    public void applyForce(double fx, double fy, double dt) {
        double ax = fx / mass;
        double ay = fy / mass;
        this.vx += ax * dt;
        this.vy += ay * dt;
    }
}

