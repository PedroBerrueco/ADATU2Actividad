package org.pberruceo;

import java.sql.*;
import java.util.ArrayList;

public class DBConection {

    private static final String url = "jdbc:postgresql://localhost:5432/actividad";
    private static final String user = "postgres";
    private static final String pass = "postgres";
    private static Connection conexion;

    /**
     * Metodo para conectar a la BD.
     */
    public static Connection Conectar (){
        try {
            conexion = DriverManager.getConnection(url,user,pass);
            System.out.println("Conectado a la BD.");
            return conexion;
        } catch (SQLException e){
            System.out.println("Error al conectar: ");
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Metodo para desconectar la BD.
     */
    public static void Desonectar() {
        try {
            conexion.close();
            System.out.println("Desconectado de la BD");
        } catch (SQLException e){
            System.out.println("Error al DESconectar: ");
            e.printStackTrace();
        }
    }

    /**
     * Método para crear tablas
     * @throws SQLException
     */
    public static void Crear()  {
        String sentenciaCreate = "CREATE TABLE IF NOT EXISTS empleados (nombre VARCHAR(20), apellido VARCHAR(255), dni VARCHAR(9), departamento VARCHAR(20), PRIMARY KEY (nombre, apellido, dni));";
        Statement stmn = null;
        try {
            stmn = conexion.createStatement();
            stmn.executeUpdate(sentenciaCreate);
            System.out.println("tabla creada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Aqui recibimos los campos de la tabla.
     * @param nombre
     * @param apellido
     * @param dni
     * @param departamento
     */
    public static void Insertar(String nombre, String apellido, String dni, String departamento) {
        if (seleccionar(nombre, apellido, dni)){
            System.out.println("Usuario " + nombre + " - " + apellido + " - " + dni + " ya existe en BD" );
        } else {
            String sentenciaInsert = "INSERT INTO empleados (nombre, apellido, dni, departamento) VALUES (?, ?, ?, ?);";

            try {
                PreparedStatement pstmn = conexion.prepareStatement(sentenciaInsert);
                pstmn.setString(1, nombre);
                pstmn.setString(2, apellido);
                pstmn.setString(3, dni);
                pstmn.setString(4, departamento);
                pstmn.executeUpdate();
                System.out.println("Usuario agregado");
            } catch (SQLException e) {
                System.out.println("No se ha podido realizar la insercción del usuario");
                e.printStackTrace();
            }
        }
    }

    private static boolean seleccionar(String nombre, String apellido, String dni) {
        try {
            String sentenciaSelect = "SELECT * FROM empleados WHERE nombre = ? AND apellido = ? AND dni = ?";
            PreparedStatement pstmn = conexion.prepareStatement(sentenciaSelect);
            pstmn.setString(1, nombre);
            pstmn.setString(2, apellido);
            pstmn.setString(3, dni);

            ResultSet resultSet = pstmn.executeQuery();

            // Verificar si hay resultados en el conjunto de resultados
            if (resultSet.next()) {
                // Si hay al menos un resultado, significa que el usuario existe
                return true;
            } else {

                // Si no hay resultados, el usuario no existe
                return false;
            }
        } catch (SQLException e) {
            System.out.println("No se ha podido acceder a la tabla");
            e.printStackTrace();
        }
        return false;
}
}