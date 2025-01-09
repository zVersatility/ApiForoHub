package foro.hub.api.domain.Usuario;

public record DatosRespuestaUsuario(
        Long id,
        String nombreUsuario,
        String correo,
        Boolean status
) {
}
