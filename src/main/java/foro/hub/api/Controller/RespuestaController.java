package foro.hub.api.Controller;


import foro.hub.api.domain.Respuesta.DatosListadoRespuesta;
import foro.hub.api.domain.Respuesta.DatosRegistroRespuesta;
import foro.hub.api.domain.Respuesta.Respuesta;
import foro.hub.api.domain.Respuesta.RespuestaRepository;
import foro.hub.api.domain.Topico.Topico;
import foro.hub.api.domain.Topico.TopicoRepository;
import foro.hub.api.domain.Usuario.Usuario;
import foro.hub.api.domain.Usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping
    public ResponseEntity<Respuesta>registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta){

        // Validar si el tópico y el usuario existen
        Topico topico = topicoRepository.findById(datosRegistroRespuesta.idTopico())
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        Usuario usuario = usuarioRepository.findById(datosRegistroRespuesta.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Crear la entidad Respuesta a partir del record DatosRegistroRespuesta
        Respuesta respuesta = new Respuesta(
                datosRegistroRespuesta.mensaje(),
                datosRegistroRespuesta.fecha(),
                topico,
                usuario
        );


        // Guardar la respuesta en la base de datos
        respuestaRepository.save(respuesta);

        // Retornar una respuesta de éxito
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);

    }

    @GetMapping
    public Page<DatosListadoRespuesta> listadoRespuestas(@PageableDefault(size = 5)Pageable paginacion){
        return respuestaRepository.findAll(paginacion).map(DatosListadoRespuesta::new);
    }
}
