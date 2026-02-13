package com.tds.gestiongastos.modelo;

import java.util.List;

/*
 Interfaz que define el contrato para importar gastos desde fuentes externas.
 Se utiliza el patrón Adaptador: cada implementación adapta un formato
 externo diferente al formato que entiende nuestro sistema (List<Gasto>).
 */
public interface ImportadorGastos {
    List<Gasto> importar(String ruta);
}