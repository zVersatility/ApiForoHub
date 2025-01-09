package foro.hub.api.domain.Respuesta;

import java.time.LocalDateTime;

public record DatosListadoRespuesta(

        String tituloTopico,
        String nombreUsuario,
        String mensaje,
        LocalDateTime fecha
) {

    public DatosListadoRespuesta(Respuesta respuesta){
        this(
                respuesta.getTopico().getTitulo(),
                respuesta.getUsuario().getNombre_Usuario(),
                respuesta.getMensaje(),
                respuesta.getFecha_De_Creacion()

        );
    }
}
