package com.tds.gestiongastos.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImportadorBancario implements ImportadorGastos {

    private static final DateTimeFormatter FORMATO_FECHA = 
        DateTimeFormatter.ofPattern("M/d/yyyy H:mm");

    @Override
    public List<Gasto> importar(String ruta) {
        List<Gasto> gastos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            // Saltar la cabecera
            br.readLine();

            String linea;
            while ((linea = br.readLine()) != null) {
                Gasto gasto = parsearLinea(linea);
                if (gasto != null) {
                    gastos.add(gasto);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el fichero: " + ruta, e);
        }

        return gastos;
    }

    private Gasto parsearLinea(String linea) {
        try {
            String[] campos = linea.split(",");

            String fechaStr = campos[0].trim();
            LocalDateTime fecha = LocalDateTime.parse(fechaStr, FORMATO_FECHA);
            String categoriaNombre = campos[3].trim();
            String concepto = campos[4].trim();
            String nombrePersona = campos[5].trim();
            double valor = Double.parseDouble(campos[6].trim());

            GestorUsuarios gestorU = GestorUsuarios.getInstance();
            GestorCategoria gestorC = GestorCategoria.getInstance();
      
            Persona persona = gestorU.obtenerOCrearPersona(nombrePersona);
            Categoria categoria = gestorC.obtenerOCrearCategoria(categoriaNombre);

            return new Gasto(valor, concepto, persona, categoria, fecha);

        } catch (Exception e) {
            System.err.println("Error parseando línea: " + linea);
            return null;
        }
    }
}