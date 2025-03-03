# Projet Test
Below is an example of a GitHub README file for your n-body galaxy simulation project. You can adapt it to your needs:

---

# Galaxy Simulation (n-body)

This project is an n-body simulation that models a galaxy-like system with a supermassive black hole at its center. The backend is developed using Java and Spring Boot, while the frontend is built with HTML, CSS, and JavaScript.

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)
- [CI/CD and Quality](#cicd-and-quality)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

## Overview

This project simulates a galaxy by computing gravitational forces among many particles. A supermassive black hole is placed at the center to serve as the main gravitational attractor, around which thousands of stars orbit. The simulation's logic is fully handled on the backend, while the frontend retrieves the simulation state via a REST API and renders the results in real time on an HTML `<canvas>`.

## Architecture

The project follows a clean, layered architecture:

- **Model Layer**:  
  Contains the `Particle` class, which defines the properties (position, velocity, mass) of each particle.

- **Service Layer**:  
  The `SimulationEngine` class is responsible for the n-body simulation logic. It calculates gravitational forces, updates velocities, and positions, and runs continuously in a separate thread. It also provides methods to add new particles dynamically.

- **Controller Layer**:  
  The `SimulationController` exposes REST endpoints for:
  - Retrieving the current simulation state (`/api/particles`).
  - Adding particles (`/api/add-particles`).
  - Resetting the simulation (`/api/reset-simulation`).

- **Frontend**:  
  The frontend is built with HTML, CSS, and JavaScript. It fetches simulation data from the backend and renders it on a `<canvas>`, creating a dynamic visualization of a galaxy.

## Features

- **Galaxy Simulation**:  
  Models a galaxy with a central supermassive black hole and thousands of orbiting stars.

- **Real-Time Updates**:  
  The backend updates the simulation continuously, while the frontend polls for the latest data and renders it.

- **Interactive Controls**:  
  Buttons allow the user to add more particles or reset the simulation.

- **Automated Testing**:  
  The project includes unit tests (using JUnit 5 and AssertJ) and integration tests (using TestRestTemplate) to ensure code quality.

## Technologies

- **Backend**:
  - Java
  - Spring Boot
  - Maven

- **Frontend**:
  - HTML, CSS, JavaScript
  - `<canvas>` for rendering

- **Testing**:
  - JUnit 5, AssertJ
  - TestRestTemplate for integration tests
  - (Optional) JaCoCo for code coverage

- **Version Control**:
  - Git (with best practices for commits and branch management)

## Installation

### Prerequisites

- JDK 17 or later
- Maven
- (Optional) Python (for serving the frontend locally)

### Steps

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/galaxy-simulation.git
   cd galaxy-simulation
   ```

2. **Backend Setup**:
   - Navigate to the project root where the `pom.xml` is located.
   - Install dependencies and build the project:
     ```bash
     mvn clean install
     ```

3. **Frontend Setup**:
   - Navigate to the `frontend` folder.
   - (Optional) Serve the frontend using Python:
     ```bash
     python -m http.server 8000
     ```
   - Alternatively, you can open the `index.html` file directly, but using a server avoids potential CORS issues.

## Usage

### Running the Backend

1. Start the Spring Boot application by running the main class:
   - In IntelliJ, right-click `NbodySimulationApplication.java` and select **Run**.
   - Alternatively, run:
     ```bash
     mvn spring-boot:run
     ```

2. The backend will start on `http://localhost:8080` and expose the following endpoints:
   - **`GET /api/particles`**: Returns the current simulation state as JSON.
   - **`GET /api/add-particles?count=50`**: Adds 50 particles to the simulation.
   - **`GET /api/reset-simulation`**: Resets the simulation to its initial state.

### Running the Frontend

1. Open your browser and navigate to `http://localhost:8000` (if using Python server) or open `frontend/index.html`.
2. The simulation should display on the `<canvas>`.
3. Use the provided buttons to add particles or reset the simulation.

## Testing

### Running Tests Locally

- **Using Maven**:
  ```bash
  mvn test
  ```
- **Within IntelliJ**:
  - Right-click the `src/test` directory or individual test files and select **Run Tests**.

### Code Coverage

- You can add the JaCoCo Maven plugin to generate a test coverage report. After running:
  ```bash
  mvn clean test
  ```
  the report will be available at `target/site/jacoco/index.html`.

## CI/CD and Quality

- **Integration Continuous (CI)**:
  - We plan to integrate our tests into a CI pipeline (using Jenkins, GitHub Actions, or GitLab CI) to automatically run tests on each commit.
- **Code Quality**:
  - Our code follows Clean Architecture and SOLID principles.
  - The project includes unit tests and integration tests to ensure robust functionality and prevent regressions.
- **Version Control**:
  - We use Git with frequent, atomic commits and clear commit messages, ensuring proper traceability and collaboration.

## Future Enhancements

- **Algorithm Optimization**:  
  Implement the Barnes-Hut algorithm to optimize force calculations for large numbers of particles.
- **Advanced Graphics**:  
  Explore using WebGL (e.g., Three.js) for a more immersive 3D visualization.
- **Real-Time Metrics**:  
  Add live statistics (total energy, average velocity, particle count, etc.) to the interface.
- **Improved CI/CD**:  
  Fully automate builds, tests, and deployments using a CI/CD pipeline.



