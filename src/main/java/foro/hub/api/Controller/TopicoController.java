package foro.hub.api.Controller;



import foro.hub.api.domain.Curso.Curso;
import foro.hub.api.domain.Curso.CursoRepository;
import foro.hub.api.domain.Respuesta.DatosRespuesta;
import foro.hub.api.domain.Topico.*;
import foro.hub.api.domain.Usuario.Usuario;
import foro.hub.api.domain.Usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;


    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder) {
        // Validar y buscar usuario por ID
        Usuario usuario = usuarioRepository.findById(datosRegistroTopico.idUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario con " +
                        "ID " + datosRegistroTopico.idUsuario() + " no encontrado"));

        // Validar y buscar curso por ID
        Curso curso = cursoRepository.findById(datosRegistroTopico.idCurso())
                .orElseThrow(() -> new IllegalArgumentException("Curso con " +
                        "ID " + datosRegistroTopico.idCurso() + " no encontrado"));

        // Crear una nueva instancia de Topico usando el constructor definido
        Topico topico = new Topico(
                datosRegistroTopico.titulo(),
                datosRegistroTopico.mensaje(),
                datosRegistroTopico.fecha(),
                datosRegistroTopico.status(),
                usuario,
                curso,
                datosRegistroTopico.respuestas()
        );

        // Guardar el tópico en la base de datos
        Topico topicoGuardado = topicoRepository.save(topico);

        // Construir los datos de respuesta
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topicoGuardado.getId_Topico(),
                topicoGuardado.getTitulo(),
                topicoGuardado.getMensaje(),
                topicoGuardado.getFecha_De_Creacion(),
                topicoGuardado.getStatus(),
                usuario.getNombre_Usuario(),
                curso.getNombre_Curso(),
                topicoGuardado.isActivo(),
                topicoGuardado.getRespuestas().stream()
                        .map(DatosRespuesta::new)
                        .collect(Collectors.toList())
        );

        // Construir la URI del recurso creado para la respuesta
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoGuardado.getId_Topico()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaTopicoCreado(@PathVariable Long id){
         Topico topico = topicoRepository.getReferenceById(id);

        // Validar que el tópico esté activo
        if (!topico.isActivo()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        var datosTopico = new DatosRespuestaTopico(topico.getId_Topico(),topico.getTitulo(),topico.getMensaje(),
                topico.getFecha_De_Creacion(),topico.getStatus(),topico.getUsuario().getNombre_Usuario(),
                topico.getCurso().getNombre_Curso(),topico.isActivo(),topico.getRespuestas()
                .stream()
                .map(DatosRespuesta::new)
                .collect(Collectors.toList()));
        return ResponseEntity.ok(datosTopico);
    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>>  listadoTopicos(@PageableDefault(size = 10,
            sort = "fechaDeCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion)
                .map(DatosListadoTopico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizaTopico datosActualizaTopico){

        Topico topico = topicoRepository.findById(datosActualizaTopico.idTopico())
                .orElseThrow(() -> new IllegalArgumentException("El tópico con ID " + datosActualizaTopico.idTopico() + " no existe."));
        topico.actualizarDatos(datosActualizaTopico,usuarioRepository,cursoRepository);

        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getId_Topico(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha_De_Creacion(),
                topico.getStatus(),
                topico.getUsuario().getNombre_Usuario(),
                topico.getCurso().getNombre_Curso(),
                topico.isActivo(),
                topico.getRespuestas().stream()
                        .map(DatosRespuesta::new)
                        .collect(Collectors.toList())
        ));



    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Topico> eleminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El tópico con ID " + id + " no existe."));
            topico.deshabilitarTopico();
            return ResponseEntity.noContent().build();

    }

    //Eliminacion fisica
    @DeleteMapping("/fisica/{id}")
    @Transactional
    public ResponseEntity<Topico> eleminarTopicoFisico(@PathVariable Long id){
        topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El tópico con ID " + id + " no existe."));
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }


}