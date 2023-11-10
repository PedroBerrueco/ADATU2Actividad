package org.pberruceo;

import javax.xml.bind.annotation.XmlElement;

public class Empleado {

    private String nombre;
    private String apellido;
    private String dni;
    private String departamento;

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getDepartamento() {
        return departamento;
    }
    @XmlElement(name = "nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @XmlElement(name = "apellido")
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    @XmlElement(name = "dni")
    public void setDni(String dni) {
        this.dni = dni;
    }
    @XmlElement(name = "departamento")
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
