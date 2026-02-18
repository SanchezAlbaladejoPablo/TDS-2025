package com.tds.gestiongastos.persistencia;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class RepositorioJSON implements Repositorio {

	private final String rutaFichero;
	private final ObjectMapper mapper;

	public RepositorioJSON(String rutaFichero) {
		this.rutaFichero = rutaFichero;
		this.mapper = new ObjectMapper();

		// Módulo para LocalDateTime
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		// No fallar si hay propiedades desconocidas en el JSON
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public void guardar(DatosApp datos) {
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(rutaFichero), datos);
		} catch (IOException e) {
			throw new RuntimeException("Error al guardar datos en: " + rutaFichero, e);
		}
	}

	@Override
	public DatosApp cargar() {
		File fichero = new File(rutaFichero);

		// Si no existe el fichero, devolver datos vacíos
		if (!fichero.exists()) {
			return new DatosApp();
		}

		try {
			return mapper.readValue(fichero, DatosApp.class);
		} catch (IOException e) {
			throw new RuntimeException("Error al cargar datos de: " + rutaFichero, e);
		}
	}
}