package foro.hub.api.domain.Respuesta;

import java.time.LocalDateTime;

public record DatosRespuesta(String mensaje, LocalDateTime fecha, String nombreUsuario) {
    public DatosRespuesta(Respuesta respuesta) {
        this(
                respuesta.getMensaje(),
                respuesta.getFecha_De_Creacion(),
                respuesta.getUsuario().getNombre_Usuario()
        );
    }
}
