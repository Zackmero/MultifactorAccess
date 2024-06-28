/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Warnings;

/**
 *
 * @author Zacarias_Mercado
 */
public enum Alert {

    OK(0),
    ID_DUPLICADO(10),
    ID_VACIO(20),
    CAMPOS_VACIOS(30),
    RELACION_INCORRECTA(40),
    ENTIDAD_VACIA(50),
    FORMATO_INCORRECTO(60);

    private final int valor;

    private Alert(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
