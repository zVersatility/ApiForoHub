package foro.hub.api.domain.Curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCurso(

        @NotBlank
        String nombreCurso,
        @NotNull
        Categoria categoria
) {
}
