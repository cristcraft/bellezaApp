package com.example.bellezaapp.modelo;

public class Producto {
    private String idProducto;
    private String nameProducto;
    private  String descripcionProducto;
    private byte[] imageProducto;


    public Producto(String idProducto, String nameProducto, String descripcionProducto, byte[] imageProducto) {
        this.idProducto = idProducto;
        this.nameProducto = nameProducto;
        this.descripcionProducto = descripcionProducto;
        this.imageProducto = imageProducto;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNameProducto() {
        return nameProducto;
    }

    public void setNameProducto(String nameProducto) {
        this.nameProducto = nameProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public byte[] getImageProducto() {
        return imageProducto;
    }

    public void setImageProducto(byte[] imageProducto) {
        this.imageProducto = imageProducto;
    }
}
