package com.example.myapplication;

public class Comida {

    private String comida, descripcion;
    private int imgComida;

    public Comida(String comida, String restaurante, int imgComida) {
        this.comida = comida;
        this.descripcion = restaurante;
        this.imgComida = imgComida;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImgComida() {
        return imgComida;
    }

    public void setImgComida(int imgComida) {
        this.imgComida = imgComida;
    }
}
