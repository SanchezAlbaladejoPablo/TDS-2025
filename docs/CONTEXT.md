# Contexto del Proyecto: Gestión de Gastos Personales

Este documento proporciona una visión exhaustiva del proyecto "Gestión de Gastos Personales", detallando su definición, casos de uso, requisitos funcionales y no funcionales, tecnologías, patrones de diseño y una visión general de la implementación. Su objetivo es servir como una fuente de referencia centralizada para todas las partes interesadas y facilitar la continuidad del desarrollo.

## 1. Definición del Proyecto

El proyecto "Gestión de Gastos Personales" es una aplicación de escritorio diseñada para ayudar a los usuarios a registrar, controlar y analizar sus gastos personales de manera eficiente. Busca ofrecer una solución robusta y fácil de usar que permita a los individuos tener una visión clara de sus finanzas, identificar patrones de gasto y gestionar presupuestos de forma proactiva. La aplicación se centrará en la persistencia de datos, la flexibilidad en la categorización y la visualización intuitiva de la información.

## 2. Casos de Uso Principales

Los casos de uso clave identificados para la aplicación incluyen:

*   **Gestión de Gastos:**
    *   Registrar un nuevo gasto (cantidad, fecha, categoría).
    *   Editar un gasto existente.
    *   Eliminar un gasto.
    *   Asignar gastos a categorías predefinidas o personalizadas.
*   **Visualización y Análisis de Gastos:**
    *   Consultar gastos en formato de tabla/lista.
    *   Visualizar gastos mediante diagramas de barras y circulares.
    *   Ver gastos en un calendario (CalendarFX).
    *   Filtrar gastos por: lista de meses, intervalos de fechas, categorías, o combinaciones de estos.
*   **Alertas y Notificaciones:**
    *   Configurar límites de gasto (semanal, mensual, por categoría específica).
    *   Recibir notificaciones al superar un límite de gasto.
    *   Revisar un historial de notificaciones pasadas.
*   **Gestión de Cuentas Compartidas:**
    *   Crear una cuenta de gasto compartida con múltiples personas.
    *   Registrar gastos en una cuenta compartida, asignando el pagador.
    *   Calcular saldos pendientes entre los participantes de una cuenta compartida (distribución equitativa o por porcentajes).
    *   La lista de personas en una cuenta compartida no es modificable una vez creada.
*   **Importación de Datos:**
    *   Importar datos de gastos desde fuentes externas (ficheros de texto plano).
    *   Soporte para diferentes formatos de importación.

## 3. Requisitos

### 3.1. Requisitos Funcionales

*   **CRUD de Gastos:** La aplicación debe permitir Crear, Leer, Actualizar y Eliminar gastos.
*   **Categorización:** Los gastos deben poder ser categorizados, y el usuario debe poder gestionar sus categorías.
*   **Persistencia:** Todos los datos (gastos, categorías, alertas, cuentas compartidas) deben ser persistidos.
*   **Visualización:** Ofrecer múltiples vistas de los datos (tabla, gráficos, calendario).
*   **Filtrado:** Funcionalidad de filtrado avanzada para los gastos.
*   **Alertas:** Sistema de alertas configurable con historial.
*   **Cuentas Compartidas:** Gestión de gastos entre múltiples usuarios con cálculo de saldos.
*   **Importación:** Capacidad de importar datos de gastos desde archivos externos.
*   **Interfaz Dual:** Operación a través de GUI (JavaFX) y CLI.

### 3.2. Requisitos No Funcionales

*   **Usabilidad:** Interfaz intuitiva y fácil de usar.
*   **Rendimiento:** La aplicación debe ser responsiva, incluso con un volumen considerable de datos.
*   **Fiabilidad:** Los datos deben ser almacenados de forma segura y consistente.
*   **Mantenibilidad:** El código debe ser legible, modular y seguir buenas prácticas de programación orientada a objetos.
*   **Seguridad:** Consideraciones básicas de seguridad para la persistencia de datos locales.

## 4. Tecnologías

*   **Lenguaje de Programación:** Java (JDK 11 o superior).
*   **Framework GUI:** JavaFX.
*   **Serialización/Deserialización JSON:** Librería Jackson (`jackson-databind`).
*   **Gestión de Proyectos y Dependencias:** Apache Maven.
*   **Control de Versiones:** Git y GitHub.
*   **Visualización Avanzada (Opcional/Sugerido):** CalendarFX para la vista de calendario.

## 5. Patrones de Diseño y Principios

La implementación deberá adherirse a principios de diseño sólido y utilizar patrones de diseño específicos:

*   **Patrón Repositorio:** Para desacoplar la lógica de negocio de la capa de persistencia de datos.
*   **Patrón Estrategia:** Obligatorio para la implementación del sistema de alertas, permitiendo diferentes lógicas de notificación o evaluación de límites.
*   **Patrón Adaptador:** Obligatorio para la importación de datos, permitiendo adaptar diferentes formatos de entrada a la estructura interna de la aplicación.
*   **Patrón Método Factoría:** Obligatorio para la creación de importadores de datos, proporcionando una interfaz para crear familias de objetos relacionados o dependientes sin especificar sus clases concretas.
*   **Patrón Singleton:** Obligatorio para clases que requieran una única instancia global en el sistema.
*   **Principios GRASP:** Se valorará la aplicación correcta de los principios GRASP.
*   **Programación Orientada a Objetos (POO):** Uso de principios básicos de POO, legibilidad del código y buen uso de streams y expresiones lambda.
*   **Diseño por Contrato:** Aplicación de diseño por contrato donde sea apropiado.

## 6. Estructura del Proyecto y Archivos Clave

El proyecto se organiza en una estructura Maven estándar, con directorios para código fuente, recursos y pruebas. La documentación se gestiona en una carpeta `docs/` separada.

```
expense-management-app/
├── .gitignore
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/tds/expensemanagement/App.java (Punto de entrada de la aplicación JavaFX)
│   │   └── resources/ (Archivos FXML, CSS, etc.)
│   └── test/
│       └── java/ (Clases de prueba JUnit)
├── lib/ (Librerías externas no gestionadas por Maven)
├── test-h2/ (Directorio para pruebas con H2 u otros elementos de prueba específicos)
└── docs/
    ├── images/ (Imágenes para la documentación)
    ├── CONTEXT.md (Este archivo de contexto detallado)
    ├── diagrama_clases_dominio.md (Plantilla para el diagrama de clases)
    ├── historias_usuario.md (Plantilla para las historias de usuario)
    ├── diagrama_interaccion.md (Plantilla para el diagrama de interacción)
    ├── arquitectura_diseno.md (Plantilla para la arquitectura y decisiones de diseño)
    ├── patrones_diseno.md (Plantilla para los patrones de diseño usados)
    └── manual_usuario.md (Plantilla para el manual de usuario)
```

## 7. Visión General de la Implementación (Fases Iniciales)

1.  **Configuración del Entorno:** Asegurar un JDK 11+ y Maven instalados.
2.  **Estructura Base:** Creación de la estructura de directorios Maven y `pom.xml` con dependencias iniciales.
3.  **Modelo de Dominio:** Definición de las clases principales (Gasto, Categoría, Alerta, CuentaCompartida, Persona) y sus relaciones.
4.  **Capa de Persistencia:** Implementación del patrón Repositorio para guardar y cargar objetos de dominio en archivos JSON utilizando Jackson.
5.  **Interfaz de Usuario (Mínima):** Creación de una ventana principal básica con JavaFX para verificar la configuración.
6.  **Lógica de Negocio:** Implementación de la lógica para registrar, editar, eliminar y filtrar gastos.
7.  **Documentación:** Desarrollo de los contenidos de los archivos Markdown en `docs/`.

## 8. Consideraciones Adicionales

*   **Colaboración:** El proyecto se gestionará en GitHub, y se espera una colaboración activa entre los miembros del equipo.
*   **Evaluación:** Se valorará la funcionalidad completa, la calidad del código, la aplicación de patrones y la documentación.

Este documento será actualizado a medida que el proyecto avance y se tomen nuevas decisiones o se descubra información relevante.



## 9. Historial de Cambios y Decisiones

Este apartado documenta la evolución del proyecto, los cambios realizados, las decisiones tomadas y las justificaciones detrás de ellas, así como las modificaciones significativas en el código.

### 9.1. Inicio del Proyecto y Configuración Inicial (30 de septiembre de 2025)

*   **Cambio:** Creación de la estructura base del proyecto Maven.
    *   **Decisión:** Se optó por una estructura estándar de Maven (`src/main/java`, `src/main/resources`, `src/test/java`) para facilitar la gestión de dependencias y la construcción del proyecto, tal como se especifica en el documento PDF original.
    *   **Justificación:** Maven es la herramienta de gestión de proyectos y dependencias requerida por el enunciado, y su estructura estándar es ampliamente reconocida y soportada por IDEs.
*   **Cambio:** Creación del archivo `pom.xml`.
    *   **Decisión:** Se configuró el `pom.xml` con las dependencias esenciales para JavaFX (para la GUI) y Jackson (para la persistencia JSON), así como JUnit para pruebas.
    *   **Justificación:** Estas librerías son explícitamente mencionadas en el enunciado como tecnologías clave para el desarrollo del proyecto.
*   **Cambio:** Creación de la estructura de documentación en `docs/` y `README.md`.
    *   **Decisión:** Se estableció una carpeta `docs/` con archivos Markdown de plantilla para la documentación requerida (diagramas, historias de usuario, arquitectura, patrones, manual de usuario) y un `README.md` inicial con instrucciones básicas y enlaces a la documentación.
    *   **Justificación:** El enunciado exige una documentación detallada en la carpeta `docs/` y un `README.md` completo. El uso de Markdown facilita la escritura y el mantenimiento de la documentación.
*   **Cambio:** Creación de `App.java` como punto de entrada.
    *   **Decisión:** Se añadió una clase `App.java` básica en `src/main/java` para servir como punto de entrada inicial de la aplicación JavaFX, verificando que la configuración de Maven y JavaFX fuera correcta.
    *   **Justificación:** Proporciona un esqueleto funcional mínimo para validar el entorno de desarrollo.
*   **Cambio:** Creación del archivo `.gitignore`.
    *   **Decisión:** Se incluyó un archivo `.gitignore` para excluir directorios y archivos generados automáticamente (como `target/`, archivos de IDEs, etc.) del control de versiones.
    *   **Justificación:** Es una buena práctica en el desarrollo de software para mantener el repositorio limpio y evitar conflictos innecesarios.

### 9.2. Incorporación del Archivo de Contexto (30 de septiembre de 2025)

*   **Cambio:** Creación inicial del archivo `docs/CONTEXT.md`.
    *   **Decisión:** Se creó un archivo `CONTEXT.md` para resumir el estado del proyecto, sus objetivos y decisiones clave.
    *   **Justificación:** A petición del usuario, para mantener un registro centralizado y dinámico del progreso y las decisiones del proyecto, facilitando futuras interacciones y el traspaso de información.
*   **Cambio:** Actualización del `README.md`.
    *   **Decisión:** Se añadió una referencia al `docs/CONTEXT.md` en la sección de `Documentación Relevante` del `README.md`.
    *   **Justificación:** Para asegurar que el archivo de contexto sea fácilmente accesible desde la entrada principal del proyecto.
*   **Cambio:** Expansión del contenido de `docs/CONTEXT.md`.
    *   **Decisión:** Se amplió el `CONTEXT.md` para incluir una descripción exhaustiva del proyecto, casos de uso, requisitos funcionales y no funcionales, tecnologías, patrones de diseño y una visión general de la implementación.
    *   **Justificación:** A petición del usuario, para proporcionar un contexto más detallado y completo que sirva como una fuente de referencia robusta para el desarrollo continuo del proyecto.



### 9.3. Ajuste de la Estructura del Proyecto (30 de septiembre de 2025)

*   **Cambio:** Creación de las carpetas `lib/` y `test-h2/`.
    *   **Decisión:** Se añadieron estas carpetas para alinear la estructura del proyecto con la imagen de referencia proporcionada por el usuario, que representa un proyecto de la misma asignatura.
    *   **Justificación:** Aunque `lib/` no es estrictamente necesaria con Maven para dependencias estándar, puede ser útil para JARs externos. `test-h2/` se añadió para mantener la coherencia con el ejemplo, sugiriendo un espacio para pruebas específicas o configuraciones de base de datos H2.
*   **Cambio:** Actualización de la sección "Estructura del Proyecto y Archivos Clave" en `CONTEXT.md`.
    *   **Decisión:** Se modificó la representación de la estructura de directorios en el `CONTEXT.md` para incluir las nuevas carpetas `lib/` y `test-h2/`.
    *   **Justificación:** Para mantener el archivo de contexto preciso y reflejar el estado actual del proyecto, incluyendo todos los cambios de estructura realizados.

