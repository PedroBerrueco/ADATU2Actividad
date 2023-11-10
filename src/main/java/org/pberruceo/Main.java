package org.pberruceo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {
    private static String PATH_FILE = "./src/main/java/org/pberruceo/empleados.xml";

    public static void main(String[] args) {

        readXml();

    }

    private static void readXml() {

        System.out.println("Entramos al metodo read xml");

        JAXBContext jctx = null;
        try {
            jctx = JAXBContext.newInstance(Empleados.class);
            Unmarshaller um = jctx.createUnmarshaller();

            File employeeFile = new File(PATH_FILE);
            Empleados empleados = (Empleados) um.unmarshal(employeeFile);

            DBConection.Conectar();
            DBConection.Crear();

            for(Empleado empleado  : empleados.getEmpleado()){
                String nombre = empleado.getNombre();
                String apellido = empleado.getApellido();
                String dni = empleado.getDni();
                String departamento = empleado.getDepartamento();

                DBConection.Insertar(nombre, apellido, dni, departamento);
            }

            DBConection.Desonectar();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}