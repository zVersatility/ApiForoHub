package foro.hub.api.domain.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Table(name = "usuarios")
@Entity(name = "Usuario")
@AllArgsConstructor
@EqualsAndHashCode(of = "id_Usuario")

public class Usuario implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Usuario;
    private String nombre_Usuario;
    private String correo;
    private String contrasena;
    private Boolean activo;


    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.activo=true;
        this.nombre_Usuario= datosRegistroUsuario.nombre();
        this.correo=datosRegistroUsuario.correo();
        this.contrasena=datosRegistroUsuario.contrasena();
    }

    public void actualizarDatos(DatosActualizaUsuario datosActualizaUsuario) {
        if (datosActualizaUsuario.nombreUsuario() != null){
            this.nombre_Usuario=datosActualizaUsuario.nombreUsuario();
        }

    }

    public void deshabilitarUsuario() {
            this.activo=false;
    }

    public Usuario() {
    }

    public long getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(long id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNombre_Usuario() {
        return nombre_Usuario;
    }

    public void setNombre_Usuario(String nombre_Usuario) {
        this.nombre_Usuario = nombre_Usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
