package org.pberruceo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "empleados")
public class Empleados {

    private List<Empleado> empleado;

    public List<Empleado> getEmpleado() {
        return empleado;
    }
    @XmlElement(name = "empleado")
    public void setEmpleado(List<Empleado> empleado) {
        this.empleado = empleado;
    }
}
