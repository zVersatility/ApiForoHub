package foro.hub.api.Controller;


import foro.hub.api.domain.Curso.Curso;
import foro.hub.api.domain.Curso.CursoRepository;
import foro.hub.api.domain.Curso.DatosListadoCurso;
import foro.hub.api.domain.Curso.DatosRegistroCurso;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public void registrarCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso){
        cursoRepository.save(new Curso(datosRegistroCurso));

    }

    @GetMapping
    public Page<DatosListadoCurso> listadoCursos(@PageableDefault(size = 5)Pageable paginacion){
        return cursoRepository.findAll(paginacion).map(DatosListadoCurso::new);
    }
}
