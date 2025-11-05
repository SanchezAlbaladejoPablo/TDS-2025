## 1. Registro y gestión básica de gastos

**HU-1 — Registrar gasto**  
Como **usuario**,  
quiero **registrar un gasto indicando la cantidad, fecha, categoría y descripción**,  
para **llevar un control detallado de mis gastos personales**.

Dado **que estoy en la pantalla/CLI de alta**, cuando **introduzco cantidad > 0, fecha válida, categoría existente y descripción opcional**, entonces **el gasto se guarda y aparece en el listado**.  
Dado **que dejo un campo obligatorio vacío o con formato inválido**, cuando **intento guardar**, entonces **se muestra un error y no se persiste el gasto**.  
Dado **que uso la CLI**, cuando **ejecuto el comando de alta con parámetros correctos**, entonces **el resultado es equivalente a la GUI**.

**HU-2 — Editar o eliminar gasto**  
Como **usuario**,  
quiero **editar o eliminar un gasto registrado**,  
para **corregir errores o mantener actualizada la información**.

Dado **un gasto existente**, cuando **modifico sus datos y confirmo**, entonces **el cambio se guarda y se refleja en listados y gráficos**.  
Dado **un gasto existente**, cuando **confirmo su eliminación**, entonces **desaparece de listados, gráficos y cálculos**.  
Dado **que uso la CLI**, cuando **ejecuto los comandos de edición/eliminación válidos**, entonces **el resultado es equivalente a la GUI**.

**HU-3 — Crear nuevas categorías**  
Como **usuario**,  
quiero **crear nuevas categorías personalizadas**,  
para **organizar mis gastos de acuerdo con mis necesidades particulares**.

Dado **un nombre de categoría no vacío y no duplicado**, cuando **confirmo la creación**, entonces **la categoría queda disponible para nuevos gastos**.  
Dado **un nombre duplicado o inválido**, cuando **intento crear la categoría**, entonces **se muestra error y no se crea**.  
Dado **que los datos se persisten**, cuando **reinicio la aplicación**, entonces **la nueva categoría sigue disponible**.

---

## 2. Visualización y análisis de gastos

**HU-4 — Ver gastos en formato tabla o lista**  
Como **usuario**,  
quiero **ver mis gastos en una tabla o lista**,  
para **consultar fácilmente las cantidades y categorías asociadas**.

Dado **que existen gastos**, cuando **abro la vista de lista/tabla**, entonces **veo fecha, categoría, descripción y cantidad por fila**.  
Dado **que no existen gastos**, cuando **abro la vista**, entonces **veo un estado vacío informativo**.

**HU-5 — Visualizar gastos en gráficos**  
Como **usuario**,  
quiero **visualizar mis gastos mediante gráficos de barras o circulares**,  
para **comprender mejor la distribución de mis gastos por categoría**.

Dado **un conjunto de gastos**, cuando **abro la vista de gráficos**, entonces **se muestran agregaciones por categoría en barras o circular**.  
Dado **que aplico filtros**, cuando **actualizo la vista**, entonces **los gráficos reflejan únicamente los datos filtrados**.

**HU-6 — Visualizar gastos en calendario**  
Como **usuario**,  
quiero **ver mis gastos en formato de calendario**,  
para **identificar los días o semanas con más gasto**.

Dado **gastos con fecha**, cuando **abro el calendario**, entonces **cada gasto aparece en su día**.  
Dado **que navego entre periodos**, cuando **cambio de mes o semana**, entonces **se actualizan los eventos mostrados**.

**HU-7 — Filtrar gastos**  
Como **usuario**,  
quiero **filtrar mis gastos por meses, intervalos de fechas o categorías**,  
para **analizar periodos o tipos de gasto específicos**.

Dado **una selección de meses**, cuando **aplico el filtro**, entonces **la vista muestra solo esos meses**.  
Dado **un intervalo de fechas**, cuando **aplico el filtro**, entonces **se muestran solo gastos en ese rango**.  
Dado **una o varias categorías**, cuando **aplico el filtro**, entonces **se muestran solo esas categorías**.  
Dado **varios filtros combinados**, cuando **los aplico**, entonces **se intersecan correctamente**.

---

## 3. Alertas y notificaciones

**HU-8 — Configurar alertas de gasto**  
Como **usuario**,  
quiero **configurar alertas de gasto semanales o mensuales**,  
para **recibir avisos cuando se superen los límites establecidos**.

Dado **un periodo (semanal/mensual) y un límite > 0**, cuando **guardo la alerta**, entonces **queda activa para ese periodo**.  
Dado **gastos que superan el límite del periodo**, cuando **se realiza el cálculo**, entonces **se genera una notificación**.

**HU-9 — Alertas por categoría**  
Como **usuario**,  
quiero **vincular una alerta a una categoría concreta**,  
para **controlar de forma más específica mis gastos en esa categoría**.

Dado **una categoría y un límite**, cuando **creo la alerta**, entonces **el umbral se evalúa solo con gastos de esa categoría**.

**HU-10 — Notificación de alertas**  
Como **usuario**,  
quiero **recibir notificaciones cuando se active una alerta**,  
para **tomar medidas y reducir mis gastos**.

Dado **una alerta activa**, cuando **se supera su límite**, entonces **recibo una notificación visible y registrada**.

**HU-11 — Historial de notificaciones**  
Como **usuario**,  
quiero **consultar un historial de notificaciones anteriores**,  
para **revisar cuándo y por qué se activaron las alertas pasadas**.

Dado **notificaciones generadas**, cuando **abro el historial**, entonces **veo fecha/hora, alerta asociada y motivo (importe vs. límite)**.  
Dado **persistencia habilitada**, cuando **reinicio la aplicación**, entonces **el historial sigue disponible**.

---

## 4. Cuentas compartidas

**HU-12 — Crear cuenta compartida**  
Como **usuario**,  
quiero **crear cuentas de gasto compartidas con otras personas**,  
para **gestionar gastos comunes de forma equitativa**.  
*Restricción*: **Una vez creada la cuenta, la lista de personas no se puede modificar.**

Dado **un nombre y una lista de ≥ 2 participantes**, cuando **creo la cuenta**, entonces **se inicializan saldos a 0 y reparto equitativo por defecto**.  
Dado **una cuenta ya creada**, cuando **intento añadir o quitar personas**, entonces **el sistema lo impide e informa**.

**HU-13 — Registrar gasto en cuenta compartida**  
Como **usuario**,  
quiero **registrar quién ha pagado cada gasto en una cuenta compartida**,  
para **mantener actualizado el saldo de cada persona, recalculándolo automáticamente según la participación**.

Dado **una cuenta y un pagador válido**, cuando **registro un gasto**, entonces **se actualizan los saldos: suma al pagador y distribuye deuda al resto**.  
Dado **porcentajes personalizados**, cuando **registro un gasto**, entonces **el reparto usa dichos porcentajes**.

**HU-14 — Consultar saldos del grupo**  
Como **usuario**,  
quiero **consultar el saldo de cada participante en la cuenta compartida**,  
para **saber quién debe dinero y cuánto**.

Dado **una cuenta con movimientos**, cuando **abro la vista de saldos**, entonces **veo por persona saldo positivo (le deben) o negativo (debe), con total del grupo = 0**.

**HU-15 — Definir porcentaje de participación**  
Como **usuario**,  
quiero **definir el porcentaje de participación de cada miembro en una cuenta compartida**,  
para **ajustar la distribución del gasto según los acuerdos del grupo (la suma debe ser 100%)**.

Dado **n participantes**, cuando **introduzco porcentajes**, entonces **el sistema valida que todos son ≥ 0 y suman 100%**.  
Dado **porcentajes válidos**, cuando **guardo**, entonces **los nuevos repartos aplican a gastos futuros sin reescribir históricos**.

---

## 5. Importación de datos externos

**HU-16 — Importar fichero de gastos bancarios**  
Como **usuario**,  
quiero **importar un fichero con mis gastos desde una plataforma bancaria**,  
para **añadir automáticamente mis movimientos sin introducirlos manualmente**.  
*Restricción*: **El sistema selecciona el importador mediante una Fábrica y Adaptadores por banco.**

Dado **un fichero válido del banco X**, cuando **lo selecciono**, entonces **se detecta el formato, se usa el importador correcto y se crean gastos con fecha/categoría/cantidad/descripción**.  
Dado **un fichero con errores de formato**, cuando **intento importarlo**, entonces **se informa del error y no se crean gastos parciales**.  
Dado **una importación exitosa**, cuando **finaliza**, entonces **los gastos importados aparecen en listas, gráficos y calendario**.

**HU-17 — Importar distintos formatos**  
Como **usuario**,  
quiero **importar gastos en distintos formatos de fichero (por ejemplo, CSV o TXT)**,  
para **poder usar datos de distintas fuentes sin problemas de compatibilidad**.

Dado **un formato soportado (p. ej., CSV, TXT)**, cuando **lo importo**, entonces **los movimientos se procesan sin pasos manuales adicionales**.  
Dado **un formato no soportado**, cuando **lo intento importar**, entonces **se informa y se sugiere el formato correcto o crear un adaptador**.

---

## 6. Interacción y uso

**HU-18 — Uso desde interfaz gráfica o CLI**  
Como **usuario**,  
quiero **gestionar mis gastos tanto desde una interfaz gráfica como desde la línea de comandos**,  
para **poder usarlos en diferentes entornos según mis preferencias**.

Dado **las funciones de registrar/editar/eliminar**, cuando **las ejecuto en CLI**, entonces **producen el mismo resultado y persistencia que en GUI**.  
Dado **un comando mal formado**, cuando **lo ejecuto**, entonces **recibo ayuda/uso correcto sin efectos colaterales**.

**HU-19 — Guardado automático en JSON**  
Como **usuario**,  
quiero **que los cambios se guarden automáticamente en un fichero JSON**,  
para **no perder información entre sesiones**.

Dado **una acción de alta/edición/borrado exitosa**, cuando **finaliza**, entonces **el estado queda persistido en JSON sin pasos adicionales**.  
Dado **un fallo de escritura**, cuando **ocurre**, entonces **se muestra un mensaje claro y no se pierde el estado ya persistido**.

**HU-20 — Cargar datos al iniciar**  
Como **usuario**,  
quiero **que la aplicación cargue mis categorías, gastos, cuentas y notificaciones al iniciar**,  
para **continuar donde lo dejé sin configuraciones adicionales**.

Dado **un fichero JSON existente**, cuando **inicio la aplicación**, entonces **se cargan categorías, gastos, cuentas (incl. porcentajes) y notificaciones previas**.  
Dado **un fichero inexistente o corrupto**, cuando **inicio**, entonces **se inicializa un estado vacío y se informa del problema sin bloquear el uso**.
