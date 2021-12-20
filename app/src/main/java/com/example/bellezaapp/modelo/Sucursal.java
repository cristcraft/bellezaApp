package com.example.bellezaapp.modelo;

public class Sucursal {
    private String id;
    private String name;
    private  String lacalization;
    private byte[] image;


    public Sucursal(String id, String name, String lacalization, byte[] image) {
        this.id = id;
        this.name = name;
        this.lacalization = lacalization;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLacalization() {
        return lacalization;
    }

    public void setLacalization(String lacalization) {
        this.lacalization = lacalization;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
