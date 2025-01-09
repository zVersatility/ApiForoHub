package foro.hub.api.Controller;


import foro.hub.api.domain.Usuario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarMedico(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                                                 UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(
                usuario.getId_Usuario(),usuario.getNombre_Usuario(),usuario.getCorreo(),usuario.getActivo());

        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId_Usuario()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> retornaUsuarioCreado(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var datosUsuario = new DatosRespuestaUsuario( usuario.getId_Usuario(),usuario.getNombre_Usuario(),usuario.getCorreo(),usuario.getActivo());
        return ResponseEntity.ok(datosUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>>  listadoUsuarios(@PageableDefault(size = 5) Pageable paginacion){

        return ResponseEntity.ok(usuarioRepository.findByActivoTrue(paginacion).map(DatosListadoUsuario::new));

    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(@RequestBody @Valid DatosActualizaUsuario datosActualizaUsuario){
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizaUsuario.idUsuario());
        usuario.actualizarDatos(datosActualizaUsuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario.getId_Usuario(),usuario.getNombre_Usuario(),usuario.getCorreo(),usuario.getActivo()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.deshabilitarUsuario();
        return ResponseEntity.noContent().build();
    }

//Eliminacion fisica
//    public void eliminarUsuario(@PathVariable Long id){
//        Usuario usuario = usuarioRepository.getReferenceById(id);
//        usuarioRepository.delete(usuario);
//    }
}
