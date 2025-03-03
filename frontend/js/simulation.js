const canvas = document.getElementById('simulationCanvas');
const ctx = canvas.getContext('2d');

function dessinerParticles(particles) {
  ctx.fillStyle = 'rgba(0, 0, 0, 0.1)';
  ctx.fillRect(0, 0, canvas.width, canvas.height);

  particles.forEach((p, i) => {
    if (p.mass > 50000) {
      ctx.fillStyle = '#111';
      ctx.beginPath();
      ctx.arc(p.x, p.y, 8, 0, 2 * Math.PI);
      ctx.fill();
    } else {
      const hue = (i * 137) % 360;
      ctx.fillStyle = `hsl(${hue}, 100%, 70%)`;
      ctx.beginPath();
      ctx.arc(p.x, p.y, 2, 0, 2 * Math.PI);
      ctx.fill();
    }
  });
}

function fetchParticles() {
  fetch('http://localhost:8080/api/particles')
    .then(response => response.json())
    .then(data => {
      dessinerParticles(data);
    })
    .catch(error => console.error('Erreur:', error));
}

setInterval(fetchParticles, 50);

const addButton = document.getElementById('addParticlesBtn');
addButton.addEventListener('click', () => {
  fetch('http://localhost:8080/api/add-particles?count=50')
    .then(response => {
      if (!response.ok) {
        throw new Error('Erreur lors de l\'ajout des particules');
      }
      console.log('50 particules ajoutées');
    })
    .catch(error => console.error(error));
});

const resetButton = document.getElementById('resetSimulationBtn');
resetButton.addEventListener('click', () => {
  fetch('http://localhost:8080/api/reset-simulation')
    .then(response => {
      if (!response.ok) {
        throw new Error('Erreur lors du reset de la simulation');
      }
      console.log('Simulation réinitialisée');
    })
    .catch(error => console.error(error));
});
