package com.softbecx.softbecx.dto;

public class RecuperarContrasenaRequest {

    private String correo;

    public RecuperarContrasenaRequest() {
    }

    public RecuperarContrasenaRequest(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}