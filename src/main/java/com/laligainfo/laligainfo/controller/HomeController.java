
package com.laligainfo.laligainfo.controller;

import com.laligainfo.laligainfo.repository.*;
import com.laligainfo.laligainfo.servicios.*;
import com.laligainfo.laligainfo.vo.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.laligainfo.laligainfo.servicios.EquipoService;
//import com.laligainfo.laligainfo.servicios.VotacionesService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class HomeController {
    private final EquipoService equipoService;
    private final JugadorDAO jugadorDAO;
    private final PartidoDAO partidoDAO;
    private final EquipoDAO equipoDAO;
    private final JornadaDAO jornadaDAO;
    private final ActualidadService actualidadService;
    private final ActualidadDAO actualidadDAO;
    private final VotacionesDAO votacionesDAO;
    private final UsuarioDAO usuarioDAO;
    private final EquipoVotadoDAO equipoVotadoDAO;
    private final JugadorVotadoDAO jugadorVotadoDAO;
    private final ComentariosDAO comentariosDAO;
    // private final VotacionesService votacionesService;
    @Autowired
    public HomeController(EquipoService equipoService, JugadorDAO jugadorDAO, PartidoDAO partidoDAO, JornadaDAO jornadaDAO, EquipoDAO equipoDAO, ActualidadService actualidadService, ActualidadDAO actualidadDAO/*, VotacionesService votacionesService*/, VotacionesDAO votacionesDAO, UsuarioDAO usuarioDAO, EquipoVotadoDAO equipoVotadoDAO, JugadorVotadoDAO jugadorVotadoDAO, ComentariosDAO comentariosDAO) {
        this.equipoService = equipoService;
        this.jugadorDAO = jugadorDAO;
        this.partidoDAO = partidoDAO;
        this.jornadaDAO = jornadaDAO;
        this.equipoDAO = equipoDAO;
        this.actualidadService = actualidadService;
        //this.votacionesService = votacionesService;
        this.actualidadDAO = actualidadDAO;
        this.votacionesDAO = votacionesDAO;
        this.usuarioDAO = usuarioDAO;
        this.equipoVotadoDAO = equipoVotadoDAO;
        this.jugadorVotadoDAO = jugadorVotadoDAO;
        this.comentariosDAO = comentariosDAO;
    }
    @ModelAttribute("equipoEscudo")
    public List<Equipo> obtenerEquiposOrdenadosParaTodasLasPaginas() {
        // Obtén la lista completa de equipos desde el servicio
        List<Equipo> todosLosEquipos = equipoService.obtenerEquiposOrdenados();

        // Define el orden específico que deseas
        List<String> ordenEspecifico = Arrays.asList(
                "Deportivo Alavés", "Athletic Club", "Atlético de Madrid",
                "FC Barcelona", "Celta de Vigo", "RCD Espanyol", "Girona FC",
                "Getafe CF", "UD Las Palmas", "Leganés", "RCD Mallorca",
                "CA Osasuna", "Rayo Vallecano", "Real Sociedad", "Real Madrid",
                "Real Betis", "Sevilla FC", "Valencia CF", "Real Valladolid",
                "Villarreal CF"
        );

        // Crear una lista para los equipos en el orden deseado
        List<Equipo> equiposOrdenados = new ArrayList<>();

        // Recorre la lista de orden específico y agrega los equipos a la nueva lista en el orden que quieres
        for (String nombreEquipo : ordenEspecifico) {
            for (Equipo equipo : todosLosEquipos) {
                if (equipo.getNombre().equals(nombreEquipo)) {
                    equiposOrdenados.add(equipo);
                    break; // Si encuentras el equipo, no necesitas seguir buscando
                }
            }
        }

        return equiposOrdenados; // Esta lista estará disponible en todas las páginas como "equipoEscudo"
    }

    @GetMapping({"/", "/index"})
    public String index(@RequestParam(value = "jornada", required = false) Integer jornada, Model model) {
        // Si no se selecciona ninguna jornada, se muestra por defecto la jornada 1
        int numeroJornada = (jornada != null) ? jornada : 1;

        model.addAttribute("equipos", equipoService.obtenerEquiposOrdenados());
        model.addAttribute("equipoEscudo", obtenerEquiposOrdenadosParaTodasLasPaginas());

        // Obtener los partidos de la jornada seleccionada
        List<Partido> partidosJornada = partidoDAO.findByJornadaNumero(numeroJornada);
        model.addAttribute("partidosJornada", partidosJornada);

        // Agregar la lista de todas las jornadas al modelo para el select
        List<Jornada> jornadas = jornadaDAO.findAll();
        model.addAttribute("jornadas", jornadas);

        // Agregar el número de la jornada seleccionada al modelo para que sea visible en el select
        model.addAttribute("jornadaSeleccionada", numeroJornada);

        actualidadService.actualizarActualidad();
        List<Actualidad> actualidadGen = actualidadDAO.findByIdJornadaNumero(numeroJornada);
        actualidadGen.sort(Comparator.comparing(Actualidad::getPuntos).reversed());

        model.addAttribute("actualidadGen", actualidadGen);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Usuario autenticado: " + authentication.getName());
        System.out.println("Autoridades: " + authentication.getAuthorities());
        return "index";
    }

    @GetMapping("/clasificacion")
    public String clasificacion(Model model) {
        // Agrega la lista ordenada al modelo
        model.addAttribute("equipos", equipoService.obtenerEquiposOrdenados());
        model.addAttribute("equipoEscudo", obtenerEquiposOrdenadosParaTodasLasPaginas());
        actualidadService.actualizarActualidad();
        List<Actualidad> actualidadGen = actualidadDAO.findByIdJornadaNumero(3);
        actualidadGen.sort(Comparator.comparing(Actualidad::getPuntos).reversed());


        model.addAttribute("actualidadGen", actualidadGen);
        return "clasificacion"; // Busca el archivo index.html en la carpeta templates
    }



    @GetMapping("/equipo/{nombre}")
    public String detalleEquipo(@PathVariable("nombre") String nombreEquipo, Model model) {
        // Busca el equipo en la base de datos usando el nombre
        Equipo equipo = equipoService.obtenerEquipoPorNombre(nombreEquipo);
        model.addAttribute("equipo", equipo);
        List<Partido> partidosCasa = partidoDAO.findByJornadaNumeroAndEquipoCasa(3, equipo);
        List<Partido> partidosFuera = partidoDAO.findByJornadaNumeroAndEquipoFuera(3, equipo);
        List<Jugadores> titulares = jugadorDAO.findByEquipoAndTitularTrue(equipo);
        List<Jugadores> portero = titulares.stream().filter(j -> "Portero".equals(j.getPosicion())).toList();
        List<Jugadores> defensas = titulares.stream().filter(j -> "Defensa".equals(j.getPosicion())).toList();
        List<Jugadores> mediocentros = titulares.stream().filter(j -> "Mediocentro".equals(j.getPosicion())).toList();
        List<Jugadores> delanteros = titulares.stream().filter(j -> "Delantero".equals(j.getPosicion())).toList();
        model.addAttribute("portero", portero);
        model.addAttribute("defensas", defensas);
        model.addAttribute("mediocentros", mediocentros);
        model.addAttribute("delanteros", delanteros);
        // Combina ambas listas
        List<Partido> partidos = new ArrayList<>();
        partidos.addAll(partidosCasa);
        partidos.addAll(partidosFuera);
        Partido primerPartido = partidos.get(0);
        List<Actualidad> puntosCasa = actualidadDAO.findByIdEquipoAndIdJornadaNumero(primerPartido.getEquipoCasa(), 3);
        List<Actualidad> puntosFuera = actualidadDAO.findByIdEquipoAndIdJornadaNumero(primerPartido.getEquipoFuera(), 3);
        model.addAttribute("puntosCasa", puntosCasa);
        model.addAttribute("puntosFuera", puntosFuera);
        model.addAttribute("partidos", partidos);
        return "equipo"; // Nombre del archivo HTML en la carpeta templates
    }

    @GetMapping("/buscar")
    public String buscarJugadores(@RequestParam("query") String query, Model model) {
        List<Jugadores> resultados = jugadorDAO.findByNombreContainingIgnoreCase(query);

        if (resultados.isEmpty()) {
            return "noresultados"; // Redirigir a una página de "No resultados"
        }
        else {
            model.addAttribute("resultados", resultados);
            Jugadores primerJugador = resultados.get(0);
            model.addAttribute("primerJugador", primerJugador);
            return "jugadores"; // Vista donde se mostrarán los resultados
        }
    }

    @GetMapping("/buscarEquipo")
    @ResponseBody // Añade esta anotación para indicar que los datos deben devolverse como JSON
    public List<Equipo> buscarEquipoPorNombre(@RequestParam("nombre") String nombre, Model model) {
        //List<Equipo> resultados = equipoDAO.findByNombreContainingIgnoreCase(query);
        return equipoDAO.findByNombreContainingIgnoreCase(nombre);
    }


    @GetMapping("/buscarJugadores")
    @ResponseBody
    public List<Jugadores> buscarJugadoresPorNombre(@RequestParam("nombre") String nombre) {
        return jugadorDAO.findByNombreContainingIgnoreCase(nombre);
    }

    @GetMapping("/noresultados")
    public String noresultados(Model model) {
        model.addAttribute("equipos", equipoService.obtenerEquiposOrdenados());
        model.addAttribute("equipoEscudo", obtenerEquiposOrdenadosParaTodasLasPaginas());

        return "noresultados"; // Busca noresultados.html en la carpeta templates
    }

    @GetMapping("/comentarios")
    public String verForo(Model model) {
        // Obtener todos los comentarios
        List<Comentario> comentarioschat = comentariosDAO.findAll();
        model.addAttribute("comentarioschat", comentarioschat); // Pasa los comentarios a la vista
        return "comentarios"; // Devuelve la misma vista
    }

    @PostMapping("/comentarios")
    public String agregarComentario( @RequestParam("mensaje") String mensaje, Model model) {
        // obtiene el usuario actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoUsuarioActual = authentication.getName();
        Usuario usuarioActual = usuarioDAO.findById(correoUsuarioActual)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Optional<Usuario> usuarioOpt = usuarioDAO.findByCorreo(usuarioActual.getCorreo());

        // Comprobar si el usuario existe
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // Crear el comentario
            AuxComentario auxComentario = new AuxComentario(usuario, mensaje);
            Comentario comentario = new Comentario(auxComentario);

            // Guardar el comentario en la base de datos
            comentariosDAO.save(comentario);

            // Agregar un mensaje de éxito
            model.addAttribute("mensaje", "Comentario agregado exitosamente.");
        } else {
            // Si el usuario no existe, agregar un mensaje de error
            model.addAttribute("mensaje", "Usuario no encontrado.");
        }

        // Después de agregar el comentario, recargar la lista de comentarios
        List<Comentario> comentarioschat = comentariosDAO.findAll();
        model.addAttribute("comentarioschat", comentarioschat); // Actualiza los comentarios
        return "comentarios"; // Vuelve a la misma vista con los comentarios actualizados
    }


    @GetMapping("/calendario")
    public String calendario(@RequestParam(value = "equipo", required = false) String equipo, Model model) {
        // Agregar la lista de equipos al modelo
        model.addAttribute("equipos", equipoService.obtenerEquiposOrdenados());
        model.addAttribute("equipoEscudo", obtenerEquiposOrdenadosParaTodasLasPaginas());
        List<Partido> partidos;
        if (equipo != null && !equipo.isEmpty()) {
            // Obtener el equipo usando el nombre del equipo seleccionado
            Equipo equipoSel = equipoDAO.findByNombre(equipo);

            // Nos aseguramos de que equipoSel no sea null antes de buscar partidos
            if (equipoSel != null) {
                // Obtener los partidos en los que el equipo es local o visitante
                partidos = partidoDAO.findByEquipoCasaOrEquipoFuera(equipoSel, equipoSel);
                model.addAttribute("equipoSeleccionado", equipoSel.getNombre());
            } else {
                partidos = new ArrayList<>(); // Si no se encuentra el equipo, asignar una lista vacía
            }
        } else {
            partidos = partidoDAO.findByEquipoCasaNombreOrEquipoFueraNombre("Deportivo Alavés", "Deportivo Alavés"); // Por defecto apareceran los partidos del Alaves
        }

        model.addAttribute("partidos", partidos); // Agregar la lista de partidos al modelo
        return "calendario"; // Busca calendario.html en la carpeta templates
    }

    @Autowired
    private AdminService adminService;

    @PostMapping("/iniAdmin")
    public String loginAdmin(@RequestParam("clave") String clave, RedirectAttributes redirectAttributes) {
        if (adminService.esClaveAdministrador(clave)) {
            return "redirect:/inicioSesion"; // Redirige al panel de administrador
        } else {
            redirectAttributes.addFlashAttribute("error", "Clave incorrecta.");
            return "redirect:/iniAdmin"; // Redirige de nuevo a la página de inicio de sesión con un mensaje de error
        }
    }

    @Autowired
    private JugadorService jugadorService;
    @PostMapping("/admin/actualizarEstado")
    @PreAuthorize("hasAuthority('ADMIN')")  // Solo permite el acceso a administradores
    public ResponseEntity<?> actualizarEstado(@RequestBody Jugadores  request) {
        try {
            // Llama al servicio para actualizar el estado
            jugadorService.actualizarEstado(request.getId_jugador(), request.getEstado());

            return ResponseEntity.ok("Estado actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar el estado");
        }

    }
    // Clase auxiliar para recibir la solicitud de actualización
    public static class JugadorEstadoRequest {
        private Integer id;
        private String estado;

        // Getters y Setters
        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }
    }
    @GetMapping("/inicioSesion")
    public String inicioSesion(Model model) {
        model.addAttribute("equipos", equipoService.obtenerEquiposOrdenados());
        model.addAttribute("equipoEscudo", obtenerEquiposOrdenadosParaTodasLasPaginas());

        return "inicioSesion"; // Busca inicioSesion.html en la carpeta templates
    }

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping({"/inicioSesion",""})
    public String procesarInicioSesion(
            @RequestParam("correo") String nombre,
            @RequestParam("contrasena") String contrasena,
            Model model,
            HttpSession session) {

        // Busca el usuario por correo
        Usuario usuario = usuarioService.obtenerUsuarioPorNombreyContrasena(nombre, contrasena);

        // Verifica si el usuario existe y si la contraseña es correcta
        if (usuario != null && usuarioService.validarContrasena(contrasena, usuario.getContrasena())) {
            // Guarda el usuario en la sesión
            session.setAttribute("usuario", usuario);
            return "redirect:/index";  // Redirige a la página principal o dashboard
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "inicioSesion";  // Regresa a la página de inicio de sesión con un mensaje de error
        }
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();  // Invalida la sesión
        return "redirect:/inicioSesion?logout";  // Redirige al formulario de inicio de sesión
    }

    @PostMapping({"/registrarse",""})
    public String registrarUsuario(Usuario usuario, Model model) {
        try {
            usuarioService.registrarUsuario(usuario);
            model.addAttribute("mensaje", "Usuario registrado exitosamente");
            return "exito";  // Redirige a una página de éxito
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "registrarse";  // Retorna a la página de registro en caso de error
        }
    }
    @GetMapping({"/registrarse", ""})
    public String registrarse(Model model) {
        model.addAttribute("equipos", equipoService.obtenerEquiposOrdenados());
        model.addAttribute("equipoEscudo", obtenerEquiposOrdenadosParaTodasLasPaginas());
        return "registrarse";
    }


    @GetMapping("/jugadores")
    public String jugadores(Model model) {
        model.addAttribute("equipos", equipoService.obtenerEquiposOrdenados());
        model.addAttribute("equipoEscudo", obtenerEquiposOrdenadosParaTodasLasPaginas());

        return "jugadoresEstatica"; // Busca calendario.html en la carpeta templates
    }

    @GetMapping("/iniAdmin")
    public String iniAdmin(Model model) {
        model.addAttribute("equipos", equipoService.obtenerEquiposOrdenados());
        model.addAttribute("equipoEscudo", obtenerEquiposOrdenadosParaTodasLasPaginas());

        return "iniAdmin"; // Busca iniAdmin.html en la carpeta templates
    }

    @GetMapping("/equipo")
    public String equipo(Model model) {
        model.addAttribute("equipos", equipoService.obtenerEquiposOrdenados());
        model.addAttribute("equipoEscudo", obtenerEquiposOrdenadosParaTodasLasPaginas());

        return "equipoEstatica"; // Busca equipo.html en la carpeta templates
    }

    @GetMapping("/onceideal")
    public String onceideal(Model model) {
        model.addAttribute("equipos", equipoService.obtenerEquiposOrdenados());
        model.addAttribute("equipoEscudo", obtenerEquiposOrdenadosParaTodasLasPaginas());

        Optional<EquipoVotado> equipoMasVotado = equipoVotadoDAO.findTopByOrderByVotosDesc();
        equipoMasVotado.ifPresent(equipo -> model.addAttribute("equipoMasVotado", equipo));

        // Obtener el jugador con más votos
        Optional<JugadorVotado> jugadorMasVotado = jugadorVotadoDAO.findTopByOrderByVotosDesc();
        jugadorMasVotado.ifPresent(jugador -> model.addAttribute("jugadorMasVotado", jugador));
        return "onceideal"; // Busca onceideal.html en la carpeta templates
    }

    @GetMapping("/votaciones")
    public String votaciones(Model model) {
        model.addAttribute("equipos", equipoService.obtenerEquiposOrdenados());
        model.addAttribute("equipoEscudo", obtenerEquiposOrdenadosParaTodasLasPaginas());

        return "votaciones"; // Busca votaciones.html en la carpeta templates
    }

    @GetMapping("/buscar2**")
    public String votarJugador(@RequestParam("query") String jugadorId, Model model) {
        List<Jugadores> resultados = jugadorDAO.findByNombreContainingIgnoreCase(jugadorId);

        if (resultados.isEmpty()) {
            return "noresultados"; // Redirigir a una página de "No resultados"
        }
        else {
            model.addAttribute("resultados", resultados);
            Jugadores primerJugador = resultados.get(0);
            model.addAttribute("primerJugador", primerJugador);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String correoUsuarioActual = authentication.getName();
            Usuario usuarioActual = usuarioDAO.findById(correoUsuarioActual)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            Jornada jornadaActual = jornadaDAO.findByNumero(3);
            try {
                // Crear clave compuesta y verificar si ya existe un voto en la jornada
                AuxVotaciones auxVotaciones = new AuxVotaciones(usuarioActual, jornadaActual);
                Optional<Votaciones> votoExistente = votacionesDAO.findById(auxVotaciones);

                if (votoExistente.isPresent()) {
                    // Si ya votó en esta jornada, verificar si ya votó por un jugador
                    Votaciones voto = votoExistente.get();
                    if (voto.getJugador() == null) {
                        // Añadir el jugador al voto existente
                        voto.setJugador(primerJugador);
                        votacionesDAO.save(voto);
                        model.addAttribute("mensajeJugador", "Voto registrado correctamente para el jugador.");
                    } else {
                        model.addAttribute("mensajeJugador", "Ya has votado por un jugador en esta jornada.");
                        return "votaciones";
                    }
                } else {
                    // Crear nuevo voto con el jugador
                    Votaciones nuevoVoto = new Votaciones(auxVotaciones, correoUsuarioActual, null, primerJugador);
                    votacionesDAO.save(nuevoVoto);
                    model.addAttribute("mensajeJugador", "Voto registrado correctamente para el jugador.");
                }

            } catch (Exception e) {
                model.addAttribute("error", "Error al registrar el voto del jugador: " + e.getMessage());
            }
            AuxJugador auxJugador = new AuxJugador(jornadaActual, primerJugador);
            Optional<JugadorVotado> jugadorVotadoOptional = jugadorVotadoDAO.findById(auxJugador);
            if (jugadorVotadoOptional.isPresent()) {
                // Si el equipo ya existe, incrementar el contador de votos
                JugadorVotado jugadorVotado = jugadorVotadoOptional.get();
                jugadorVotado.incrementarVotos();
                jugadorVotadoDAO.save(jugadorVotado); // Guardar el cambio en la base de datos
            } else {
                // Si el equipo no existe, crear una nueva entrada con votos iniciales en 1
                JugadorVotado nuevoJugadorVotado = new JugadorVotado(auxJugador, 1);
                System.out.println("Jugador votado");
                jugadorVotadoDAO.save(nuevoJugadorVotado); // Guardar el nuevo equipo en la base de datos
            }
            return "votaciones";
        }
    }
    @GetMapping("/buscar3**")
    public String votarEquipo(@RequestParam("query") String equipoId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoUsuarioActual = authentication.getName();
        Usuario usuarioActual = usuarioDAO.findById(correoUsuarioActual)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Jornada jornadaActual = jornadaDAO.findByNumero(3); // Ajusta según la lógica de la jornada actual
        Equipo equipo = equipoDAO.findById(equipoId)
                .orElseThrow(() -> new IllegalArgumentException("Equipo no encontrado"));
        try {
            // Crear clave compuesta y verificar si ya existe un voto en la jornada
            AuxVotaciones auxVotaciones = new AuxVotaciones(usuarioActual, jornadaActual);
            Optional<Votaciones> votoExistente = votacionesDAO.findById(auxVotaciones);

            if (votoExistente.isPresent()) {
                // Si ya votó en esta jornada, verificar si ya votó por un equipo
                Votaciones voto = votoExistente.get();
                if (voto.getEquipo() == null) {
                    // Añadir el equipo al voto existente
                    voto.setEquipo(equipo);
                    votacionesDAO.save(voto);
                    model.addAttribute("mensajeEquipo", "Voto registrado correctamente para el equipo.");
                } else {
                    model.addAttribute("mensajeEquipo", "Ya has votado por un equipo en esta jornada.");
                    return "votaciones";
                }
            } else {
                // Crear nuevo voto con el equipo
                Votaciones nuevoVoto = new Votaciones(auxVotaciones, correoUsuarioActual, equipo, null);
                //Sumar voto al Equipo

                votacionesDAO.save(nuevoVoto);
                model.addAttribute("mensajeEquipo", "Voto registrado correctamente para el equipo.");
            }

        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar el voto del equipo: " + e.getMessage());
        }
        AuxEquipoVotado auxEquipoVotado = new AuxEquipoVotado(jornadaActual, equipo);
        Optional<EquipoVotado> equipoVotadoOptional = equipoVotadoDAO.findById(auxEquipoVotado);
        if (equipoVotadoOptional.isPresent()) {
            // Si el equipo ya existe, incrementar el contador de votos
            EquipoVotado equipoVotado = equipoVotadoOptional.get();
            equipoVotado.incrementarVotos();
            equipoVotadoDAO.save(equipoVotado); // Guardar el cambio en la base de datos
        } else {
            // Si el equipo no existe, crear una nueva entrada con votos iniciales en 1
            EquipoVotado nuevoEquipoVotado = new EquipoVotado(auxEquipoVotado, 1);
            System.out.println("Equipo votado");
            equipoVotadoDAO.save(nuevoEquipoVotado); // Guardar el nuevo equipo en la base de datos
        }
        return "votaciones";
    }
}