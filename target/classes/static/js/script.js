const numEquipos = 20;
const tableBody = document.getElementById('table-body');
const contenedorPartidos = document.getElementById('contenedorPartidos');

// Generar filas para cada equipo, solo si tableBody existe
if (tableBody) {
    for (let i = 1; i <= numEquipos; i++) {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td><strong>Equipo ${i}</strong></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        `;
        tableBody.appendChild(row);
    }
}

// Generar los 10 partidos, siempre que contenedorPartidos exista
if (contenedorPartidos) {
    for (let i = 1; i <= 10; i++) {
        const rectangulo = document.createElement('div');
        rectangulo.classList.add('rectangulo'); // Añadir la clase al div

        rectangulo.innerHTML = `
            <div class="partido">
                <div class="partido-contenido">
                    <span class="equipo-izquierda"><strong>Equipo 1</strong></span>
                    <span class="vs"><strong>VS</strong></span>
                    <span class="equipo-derecha"><strong>Equipo 2</strong></span>
                </div>
            </div>
        `;

        contenedorPartidos.appendChild(rectangulo); // Añadir el rectángulo al contenedor
    }
}

function searchPlayers() {
    const query = document.getElementById("searchInput").value;
    const suggestionsBox = document.getElementById("suggestions");

    if (query.length === 0) {
        suggestionsBox.style.display = "none"; // Oculta el contenedor si el input está vacío
        return;
    }

    fetch(`/buscarJugadores?nombre=${query}`)
        .then(response => response.json())
        .then(data => {
            suggestionsBox.innerHTML = ""; // Limpia las sugerencias anteriores
            if (data.length > 0) {
                data.forEach(player => {
                    const suggestionItem = document.createElement("div");
                    suggestionItem.classList.add("suggestion-item");
                    suggestionItem.innerText = player.nombre;
                    // Redirige a la página jugadores.html con el nombre del jugador al hacer clic
                    suggestionItem.onclick = function () {
                        window.location.href = `/buscar?query=${player.nombre}`; // Cambia a la URL correspondiente
                    };
                    suggestionsBox.appendChild(suggestionItem);
                });
                suggestionsBox.style.display = "block"; // Muestra las sugerencias
            } else {
                suggestionsBox.style.display = "none"; // Oculta si no hay resultados
            }
        })
        .catch(error => {
            console.error('Error fetching suggestions:', error);
            suggestionsBox.style.display = "none"; // Oculta las sugerencias si hay un error
        });
}

function searchPlayersVotos() {
    const query = document.getElementById("searchInputVotos").value;
    const suggestionsBox = document.getElementById("suggestionsVotos");

    if (query.length === 0) {
        suggestionsBox.style.display = "none"; // Oculta el contenedor si el input está vacío
        return;
    }

    fetch(`/buscarJugadores?nombre=${query}`)
        .then(response => response.json())
        .then(data => {
            suggestionsBox.innerHTML = ""; // Limpia las sugerencias anteriores
            if (data.length > 0) {
                data.forEach(player => {
                    const suggestionItem = document.createElement("div");
                    suggestionItem.classList.add("suggestion-itemVotos");
                    suggestionItem.innerText = player.nombre;

                    // Cuando se hace clic, establece el valor del input sin redirigir
                    suggestionItem.onclick = function () {
                        document.getElementById("searchInputVotos").value = player.nombre; // Establece el valor del input con el nombre del jugador
                        suggestionsBox.style.display = "none"; // Oculta las sugerencias después de seleccionar
                    };

                    suggestionsBox.appendChild(suggestionItem);
                });
                suggestionsBox.style.display = "block"; // Muestra las sugerencias
            } else {
                suggestionsBox.style.display = "none"; // Oculta si no hay resultados
            }
        })
        .catch(error => {
            console.error('Error fetching suggestions:', error);
            suggestionsBox.style.display = "none"; // Oculta las sugerencias si hay un error
        });
}

function searchPlayersVotosEquipo() {
    const query = document.getElementById("searchInputVotosEquipo").value;
    const suggestionsBox = document.getElementById("suggestionsVotosEquipo");

    if (query.length === 0) {
        suggestionsBox.style.display = "none"; // Oculta el contenedor si el input está vacío
        return;
    }

    fetch(`/buscarEquipo?nombre=${query}`)
        .then(response => response.json())
        .then(data => {
            suggestionsBox.innerHTML = ""; // Limpia las sugerencias anteriores
            if (data.length > 0) {
                data.forEach(team => {
                    const suggestionItem = document.createElement("div");
                    suggestionItem.classList.add("suggestion-itemVotosEquipo");
                    suggestionItem.innerText = team.nombre;

                    // Cuando se hace clic, establece el valor del input sin redirigir
                    suggestionItem.onclick = function () {
                        document.getElementById("searchInputVotosEquipo").value = team.nombre; // Establece el valor del input con el nombre del equipo
                        suggestionsBox.style.display = "none"; // Oculta las sugerencias después de seleccionar
                    };

                    suggestionsBox.appendChild(suggestionItem);
                });
                suggestionsBox.style.display = "block"; // Muestra las sugerencias
            } else {
                suggestionsBox.style.display = "none"; // Oculta si no hay resultados
            }
        })
        .catch(error => {
            console.error('Error fetching suggestions:', error);
            suggestionsBox.style.display = "none"; // Oculta las sugerencias si hay un error
        });
}


function actualizarEstado(jugadorId, nuevoEstado) {
    fetch(`/admin/actualizarEstado`, {
        //console:log("ID del Jugador:", jugadorId, "Nuevo Estado:", nuevoEstado),
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id_jugador: jugadorId,
            estado: nuevoEstado
        })
    })
        .then(response => {
            if (response.ok) {
                alert("Estado actualizado correctamente");
            } else {
                alert("Hubo un problema al actualizar el estado");
            }
        })
        .catch(error => console.error('Error:', error));
}


function toggleNav() {
    const sidenav = document.getElementById("mySidenav");
    const overlay = document.getElementById("overlay");

    if (sidenav.style.width === "500px") {
        sidenav.style.width = "0";
        overlay.style.display = "none";
        document.body.style.overflow = "auto"; // Habilitar desplazamiento de la página
    } else {
        sidenav.style.width = "500px"; // Cambia este valor a la anchura deseada
        overlay.style.display = "block";
        document.body.style.overflow = "hidden"; // Deshabilitar desplazamiento de la página
    }
}