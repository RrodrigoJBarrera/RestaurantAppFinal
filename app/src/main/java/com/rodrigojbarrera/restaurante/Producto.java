package com.rodrigojbarrera.restaurante;

import java.util.ArrayList;

public class Producto {
    private int id;
    private String descripcion;
    private int precio;
    private int cantidad;
    private int total;

    public Producto(int id, String descripcion, int precio, int cantidad, int total) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Producto(String descripcion, int precio, int cantidad, int total) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Producto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}
