package com.rodrigojbarrera.restaurante;

public class Pedido {
    private int idPedido;
    private String mesa;
    private String mozo;
    private String descripcionPedido;
    private int estadoPedido;

    public Pedido(int idPedido, String mesa, String mozo, String descripcionPedido, int estadoPedido) {
        this.idPedido = idPedido;
        this.mesa = mesa;
        this.mozo = mozo;
        this.descripcionPedido = descripcionPedido;
        this.estadoPedido = estadoPedido;
    }

    public Pedido(String mesa, String mozo, String descripcionPedido, int estadoPedido) {
        this.mesa = mesa;
        this.mozo = mozo;
        this.descripcionPedido = descripcionPedido;
        this.estadoPedido = estadoPedido;
    }

    public Pedido(int checkestadoPedido) {
        this.estadoPedido = checkestadoPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getMozo() {
        return mozo;
    }

    public void setMozo(String mozo) {
        this.mozo = mozo;
    }

    public String getDescripcionPedido() {
        return descripcionPedido;
    }

    public void setDescripcionPedido(String descripcionPedido) {
        this.descripcionPedido = descripcionPedido;
    }

    public int getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(int estadoPedido) {
        this.estadoPedido = estadoPedido;
    }
}