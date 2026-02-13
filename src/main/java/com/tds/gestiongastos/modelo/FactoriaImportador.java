package com.tds.gestiongastos.modelo;

/*
 Método Factoría que crea el importador adecuado según el formato.
 Si en el futuro se añaden nuevos formatos, solo hay que añadir
 un nuevo case aquí y crear la clase importadora correspondiente.
 */
public class FactoriaImportador {

    public static ImportadorGastos crearImportador(String formato) {
        switch (formato.toLowerCase()) {
            case "bancario":
                return new ImportadorBancario();
            default:
                throw new IllegalArgumentException(
                    "Formato de importación no soportado: " + formato);
        }
    }
}