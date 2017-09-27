package vb.dto;

/**
 *
 * @author virtual
 */
public class PerfilDto {

    private String ID_perfil;
    private String Perfil;

    public PerfilDto(String ID_perfil, String Perfil) {
        this.ID_perfil = ID_perfil;
        this.Perfil = Perfil;
    }

    public PerfilDto() {
    }

    public String getID_perfil() {
        return ID_perfil;
    }

    public void setID_perfil(String ID_perfil) {
        this.ID_perfil = ID_perfil;
    }

    public String getPerfil() {
        return Perfil;
    }

    public void setPerfil(String Perfil) {
        this.Perfil = Perfil;
    }

}
