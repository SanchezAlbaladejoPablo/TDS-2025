## 1. Registro y gestión básica de gastos

**HU-1 — Registrar gasto**   
Como **usuario**,  
quiero **registrar un gasto indicando la cantidad, fecha, categoría y descripción**,  
para **llevar un control detallado de mis gastos personales**.

**HU-2 — Editar o eliminar gasto**  
Como **usuario**,  
quiero **editar o eliminar un gasto registrado**,  
para **corregir errores o mantener actualizada la información**.

**HU-3 — Crear nuevas categorías**  
Como **usuario**,  
quiero **crear nuevas categorías personalizadas**,  
para **organizar mis gastos de acuerdo con mis necesidades particulares**.

---

## 2. Visualización y análisis de gastos

**HU-4 — Ver gastos en formato tabla o lista**  
Como **usuario**,  
quiero **ver mis gastos en una tabla o lista**,  
para **consultar fácilmente las cantidades y categorías asociadas**.

**HU-5 — Visualizar gastos en gráficos**  
Como **usuario**,  
quiero **visualizar mis gastos mediante gráficos de barras o circulares**,  
para **comprender mejor la distribución de mis gastos por categoría**.

**HU-6 — Visualizar gastos en calendario**  
Como **usuario**,  
quiero **ver mis gastos en formato de calendario**,  
para **identificar los días o semanas con más gasto**.

**HU-7 — Filtrar gastos**  
Como **usuario**,  
quiero **filtrar mis gastos por meses, intervalos de fechas o categorías**,  
para **analizar periodos o tipos de gasto específicos**.

---

## 3. Alertas y notificaciones

**HU-8 — Configurar alertas de gasto**  
Como **usuario**,  
quiero **configurar alertas de gasto semanales o mensuales**,  
para **recibir avisos cuando se superen los límites establecidos**.

**HU-9 — Alertas por categoría**  
Como **usuario**,  
quiero **vincular una alerta a una categoría concreta**,  
para **controlar de forma más específica mis gastos en esa categoría**.

**HU-10 — Notificación de alertas**  
Como **usuario**,  
quiero **recibir notificaciones cuando se active una alerta**,  
para **tomar medidas y reducir mis gastos**.

**HU-11 — Historial de notificaciones**  
Como **usuario**,  
quiero **consultar un historial de notificaciones anteriores**,  
para **revisar cuándo y por qué se activaron las alertas pasadas**.

---

## 4. Cuentas compartidas

**HU-12 — Crear cuenta compartida**  
Como **usuario**,  
quiero **crear cuentas de gasto compartidas con otras personas**,  
para **gestionar gastos comunes de forma equitativa.**.  
*Nota*: **Una vez creada la cuenta, la lista de personas no se puede modificar.**

**HU-13 — Registrar gasto en cuenta compartida**
Como **usuario**,  
quiero **registrar quién ha pagado cada gasto en una cuenta compartida**,  
para **mantener actualizado el saldo de cada persona, recalculándolo automáticamente según la participación**.

**HU-14 — Consultar saldos del grupo**  
Como **usuario**,  
quiero **consultar el saldo de cada participante en la cuenta compartida**,  
para **saber quién debe dinero y cuánto**.

**HU-15 — Definir porcentaje de participación** 
Como **usuario**,  
quiero **definir el porcentaje de participación de cada miembro en una cuenta compartida**,  
para **ajustar la distribución del gasto según los acuerdos del grupo (la suma debe ser 100%)**.

---

## 5. Importación de datos externos

**HU-16 — Importar fichero de gastos bancarios** 
Como **usuario**,  
quiero **importar un fichero con mis gastos desde una plataforma bancaria**,  
para **añadir automáticamente mis movimientos sin introducirlos manualmente**.  
*Nota*: El sistema selecciona el importador adecuado mediante una **Fábrica** y **Adaptadores** por banco.

**HU-17 — Importar distintos formatos**  
Como **usuario**,  
quiero **importar gastos en distintos formatos de fichero (por ejemplo, CSV o TXT)**,  
para **poder usar datos de distintas fuentes sin problemas de compatibilidad**.

---

## 6. Interacción y uso

**HU-18 — Uso desde interfaz gráfica o CLI**  
Como **usuario**,  
quiero **gestionar mis gastos tanto desde una interfaz gráfica como desde la línea de comandos**,  
para **poder usarlos en diferentes entornos según mis preferencias**.  

**HU-19 — Guardado automático en JSON**  
Como **usuario**,  
quiero **que los cambios se guarden automáticamente en un fichero JSON**,  
para **no perder información entre sesiones**.

**HU-20 — Cargar datos al iniciar**  
Como **usuario**,  
quiero **que la aplicación cargue mis categorías, gastos, cuentas y notificaciones al iniciar**,  
para **continuar donde lo dejé sin configuraciones adicionales**.

---
