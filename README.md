# Gestión de Gastos Personales

## Descripción del Proyecto

Este proyecto consiste en el desarrollo de una aplicación de escritorio para la gestión y el control de gastos personales. La aplicación permitirá a los usuarios registrar, editar y borrar gastos, organizarlos por categorías, y visualizar la información de diversas maneras (tablas, gráficos). Además, incluirá un sistema de alertas configurable, gestión de cuentas de gasto compartidas y la importación de datos desde fuentes externas. La interfaz de usuario se implementará con JavaFX, y la persistencia de datos se realizará en formato JSON utilizando la librería Jackson, siguiendo el patrón Repositorio. El proyecto será gestionado con Maven y Git.

## Cómo Ejecutar el Proyecto

1.  **Clonar el repositorio:**
    ```bash
    git clone [URL_DEL_REPOSITORIO]
    cd GestionGastos
    ```
2.  **Compilar y ejecutar con Maven:**
    Asegúrate de tener Maven y un JDK 11 o superior instalados.
    ```bash
    mvn clean javafx:run
    ```

## Documentación Relevante

La documentación detallada del proyecto se encuentra en la carpeta `docs/`.

*   [Diagrama de Clases del Dominio](docs/diagrama_clases_dominio.md)
*   [Especificación de Historias de Usuario](docs/historias_usuario.md)
*   [Diagrama de Interacción](docs/diagrama_interaccion.md)
*   [Arquitectura y Decisiones de Diseño](docs/arquitectura_diseno.md)
*   [Patrones de Diseño Usados](docs/patrones_diseno.md)
*   [Manual de Usuario](docs/manual_usuario.md)


*   [Contexto del Proyecto](docs/CONTEXT.md)


## Estructura del Proyecto

```
├── pom.xml
├── README.md
├── lib/
│   └── .gitkeep
├── test-h2/
│   └── .gitkeep
├── docs/
│   ├── CONTEXT.md
│   ├── arquitectura_diseno.md
│   ├── diagrama_clases_dominio.md
│   ├── diagrama_interaccion.md
│   ├── historias_usuario.md
│   ├── manual_usuario.md
│   └── patrones_diseno.md
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── tds/
    │   │           └── gestiongastos/
    │   │               ├── App.java
    │   │               ├── controlador/
    │   │               │   └── package-info.java
    │   │               ├── modelo/
    │   │               │   └── package-info.java
    │   │               ├── persistencia/
    │   │               │   └── package-info.java
    │   │               ├── tds/
    │   │               │   └── package-info.java
    │   │               └── vista/
    │   │                   └── package-info.java
    │   └── resources/
    │       └── .gitkeep
    └── test/
        └── java/
            └── .gitkeep
```
