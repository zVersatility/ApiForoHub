package foro.hub.api.domain.Usuario;



public record DatosListadoUsuario(Long id, String nombreUsuario, String CorreoUsuario) {

    public DatosListadoUsuario(Usuario usuario){
        this(usuario.getId_Usuario(), usuario.getNombre_Usuario(),usuario.getCorreo());
    }


}
