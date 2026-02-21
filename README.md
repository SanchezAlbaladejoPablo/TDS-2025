# Gestor de Gastos Personales

## Participantes
Este proyecto ha sido realizado por: 
- Javier Munuera Guirao
- Pablo Sánchez Albaladejo
- Juan Miguel Conesa Cerón

## Descripción del Proyecto

Este proyecto consiste en el desarrollo de una aplicación de escritorio para la gestión y el control de gastos personales. La aplicación permitirá a los usuarios registrar, editar y borrar gastos, organizarlos por categorías, y visualizar la información de diversas maneras (tablas, gráficos). Además, incluirá un sistema de alertas configurable, gestión de cuentas de gasto compartidas y la importación de datos desde fuentes externas. La interfaz de usuario se implementará con JavaFX, y la persistencia de datos se realizará en formato JSON utilizando la librería Jackson, siguiendo el patrón Repositorio. El proyecto será gestionado con Maven y Git.

## Cómo Ejecutar el Proyecto

1.  **Clonar el repositorio:**
    ```bash
    git clone [URL_DEL_REPOSITORIO]
    cd GestionGastos
    ```
2.  **Compilar y ejecutar con Maven:**
    Asegúrese de tener Maven y un JDK 21 instalado.
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



## Estructura del Proyecto

```
├── pom.xml
├── README.md
├── lib/
│   └── .gitkeep
├── docs/
│   └── arquitectura_diseno.md
│   └── diagrama_clases_dominio.md
│   └── diagrama_interaccion.md
│   └── historias_usuario.md
│   └── manual_usuario.md
│   └── patrones_diseno.md
└── src/
    ├── com/
    │   └── tds/
    │       └── gestiongastos/
    │           ├── app/
    │               └──Main.java
    │           ├── controlador/
    │               └── controlador.java
    │           ├── modelo/
    │               └── Alerta.java
    │               └── AlertaMensual.java
    │               └── AlertaSemanal.java
    │               └── Categoria.java
    │               └── CatPredef.java
    │               └── Cuenta.java
    │               └── CuentaCompartida.java
    │               └── EstrategiaAlerta.java
    │               └── FactoriaImporador.java
    │               └── Gasto.java
    │               └── GestorCategoria.java
    │               └── GestorGastos.java
    │               └── GestorUsuarios.java
    │               └── ImportadorBancario.java
    │               └── ImportadorGastos.java
    │               └── Notificacion.java
    │               └── Persona.java
    │               └── TipoCate.java
    │           ├── persistencia/
    │               └── DatosApp.java
    │               └── GestorPersistencia.java
    │               └── Repositorio.java
    │               └── RepositorioJSON.java
    │           ├── vista/
    │               └── AlertasViewController.java
    │               └── CrearCuentaViewController.java
    │               └── ImportarViewController.java
    │               └── LoginViewController.java
    │               └── MenuViewController.java
    │               └── RegistrarGastoViewController.java
    │               └── RegistroViewController.java
    │               └── SceneManager.java
    │               └── VerGastosViewController.java
    ├── resources/
    │   └── .gitkeep
    │   └── estilos/
    │       └── estilos.css
    │   └── vista/
    │       └── panel_alertas.fxml
    │       └── panel_crear_cuenta.fxml
    │       └── panel_importar.fxml
    │       └── panel_registrar_gasto.fxml
    │       └── panel_ver_gasto.fxml
    │       └── ventana_login.fxml
    │       └── ventana_menu.fxml
    │       └── ventana_registro.fxml
    └── test/
        └── java/
            └── .gitkeep
```
