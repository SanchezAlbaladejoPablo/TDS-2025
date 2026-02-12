package com.tds.gestiongastos.modelo;

import java.util.List;


/* Se crea como Interfaz porque así define la estrategia para calcular el gasto en un periodo de tiempo.
 Se utiliza el patrón Estrategia para permitir diferentes formas de calcular
 el periodo (semanal, mensual, etc.) sin modificar la clase Alerta.
 Para añadir un nuevo tipo de periodo, basta con crear una nueva implementación
 de esta interfaz.
*/
public interface EstrategiaAlerta {
	
	
	double calcularGastoEnPeriodo(List<Gasto> gastos);

	String getDescripcion();
}
