package co.movistar.descubre.utils;

public class Login {

    String url;
    String cedula;
    String celular;
    String correo;
    String otp;
    String cuota;
    String equipo;

    public Login() {
    }

    public Login(String url, String cedula, String celular, String correo, String otp, String cuota, String equipo) {
        this.url = url;
        this.cedula = cedula;
        this.celular = celular;
        this.correo = correo;
        this.otp = otp;
        this.cuota = cuota;
        this.equipo = equipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    @Override
    public String toString() {
        return "Login{" +
                "url='" + url + '\'' +
                ", cedula='" + cedula + '\'' +
                ", celular='" + celular + '\'' +
                ", correo='" + correo + '\'' +
                ", otp='" + otp + '\'' +
                ", cuota='" + cuota + '\'' +
                ", equipo='" + equipo + '\'' +
                '}';
    }
}
