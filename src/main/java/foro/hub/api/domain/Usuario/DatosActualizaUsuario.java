package foro.hub.api.domain.Usuario;

import jakarta.validation.constraints.NotNull;

public record DatosActualizaUsuario(@NotNull Long idUsuario,String nombreUsuario) {
}
