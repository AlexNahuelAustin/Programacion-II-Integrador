# Programacion-II-Integrador
Sistema de Gestión de Pacientes e Historias Clínicas
Trabajo Práctico Integrador – Programación II
Descripción del Proyecto

========================================================================================
### Índice
1. Descripción del Proyecto
2. Integrantes del Equipo
3. Tecnologías Utilizadas
4. Objetivos Académicos
5. Estructura del Proyecto
6. Script SQL
7. Funcionalidades del Sistema
8. Instalación y Puesta en Marcha
9. Uso del Sistema
10. Arquitectura del Sistema
11. Solución de Problemas Frecuentes
12. Contexto Académico

========================================================================================
Este Trabajo Práctico Integrador tiene como objetivo aplicar de forma práctica los conceptos de Programación Orientada a Objetos, arquitectura en capas y persistencia de datos con JDBC vistos en la materia Programación II.

El proyecto consiste en desarrollar un sistema de gestión de pacientes e historias clínicas que permita realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre estas entidades, utilizando una arquitectura robusta, organizada y cercana a un entorno profesional.

El sistema trabaja con dos entidades principales:

Paciente: datos personales y de contacto del paciente.

Historia Clínica: información médica asociada a un paciente (1:1), como antecedentes, alergias, observaciones, etc.
========================================================================================
Integrantes del Equipo

Leonel Jesús Aballay – Comisión 17

Caín Cabrera Bertolazzi – Comisión 11

Alex Nahuel Austin – Comisión 17

Cristian Gabriel Aguirre – Comisión 6
========================================================================================
Tecnologías Utilizadas
Componente	Versión / Descripción
Lenguaje	Java SE 24
JDK	JDK 24
IDE	Apache NetBeans (entorno de desarrollo)
Base de Datos	MySQL 8.x (motor InnoDB)
Cliente SQL 1	DBeaver 25.2.4
Cliente SQL 2	MySQL Workbench (para consultas y diseño)
Conector JDBC	mysql-connector-j-[versión].jar
========================================================================================
El archivo mysql-connector-j-[versión].jar debe estar agregado a las Libraries del proyecto en NetBeans para que Java pueda conectarse correctamente a MySQL.

Objetivos Académicos

El desarrollo de este sistema permite consolidar los siguientes conceptos clave de la materia:

1. Arquitectura en Capas (Layered Architecture)

Separación de responsabilidades en 4 capas principales:

Capa de Presentación / UI: menú de consola para interactuar con el usuario.

Capa de Servicio (Service): contiene la lógica de negocio, validaciones y orquestación de transacciones.

Capa de Acceso a Datos (DAO): se encarga de las operaciones de persistencia mediante JDBC.

Capa de Modelo (Entities/Models): representa las entidades del dominio (Paciente, HistoriaClinica, enum GrupoSanguineo).



2. Programación Orientada a Objetos

Definición de clases de dominio con atributos privados y métodos de acceso (encapsulamiento).

Uso de constructores sobrecargados, métodos toString() para depuración y visualización.

Implementación de enumeraciones (enum) para valores acotados (por ejemplo, grupo sanguíneo).

Separación de responsabilidades y diseño orientado a objetos en DAO y Services.



3. Persistencia de Datos con JDBC

Conexión a MySQL utilizando el driver MySQL Connector/J.

Uso del patrón DAO (Data Access Object) para encapsular el acceso a la base de datos.

Sentencias SQL parametrizadas mediante PreparedStatement para prevenir SQL Injection.

Manejo de transacciones en la capa de servicio, con control de commit y rollback para operaciones que afectan a múltiples tablas (por ejemplo, creación de Paciente + Historia Clínica).



4. Manejo de Recursos y Excepciones

Uso de try-with-resources para cierre automático de Connection, PreparedStatement y ResultSet.

Manejo de excepciones SQLException y propagación controlada hacia la capa de servicio.

Mensajes claros hacia el usuario cuando ocurre un error de conexión o de validación.



5. Patrones de Diseño

Factory Pattern: clase DatabaseConnection como fábrica de conexiones JDBC.

DAO Pattern: clases PacienteDaoImpl, HistoriaClinicaDaoImpl para el acceso a datos.

Service Layer Pattern: clases PacienteServiceImpl, HistorialServiceImpl que encapsulan la lógica de negocio.

Posible uso de Soft Delete en algunas tablas mediante un campo eliminado (según diseño del grupo).



6. Validación de Integridad de Datos

Validación de campos obligatorios (por ejemplo, nombre, apellido, DNI).

Validación de unicidad de DNI desde la aplicación y/o la base de datos.

Validación de la relación 1:1 entre Paciente e Historia Clínica (cada historia clínica debe corresponder a un paciente existente).

Manejo de claves foráneas para mantener la integridad referencial.

Estructura del Proyecto

Proyecto NetBeans: TPI_HistoriaClinica

Paquetes Principales

config

DatabaseConnection.java

Clase responsable de inicializar el driver JDBC (com.mysql.cj.jdbc.Driver) y obtener conexiones hacia la base de datos MySQL.

Implementa un método estático getConnection() que centraliza la configuración de URL, usuario y contraseña.

entities / model

Paciente.java

Representa al paciente con atributos como: id, nombre, apellido, dni, fechaNacimiento, telefono, email, direccion, grupoSanguineo, etc.

HistoriaClinica.java

Representa la historia clínica asociada a un paciente, con campos como: id, idPaciente, fechaAlta, observaciones, enfermedadesPrevias, alergias, medicamentosHabituales, etc.

GrupoSanguineo.java (enum)

Enumeración para grupos sanguíneos válidos: O+, O-, A+, A-, B+, B-, AB+, AB-.

Dao

Interfaces y clases concretas de acceso a datos utilizando JDBC.

Ejemplos:

PacienteDao, PacienteDaoImpl

HistoriaClinicaDao, HistoriaClinicaDaoImpl

Implementan operaciones CRUD: crear, buscarPorId, listarTodos, actualizar, eliminar.

service

PacienteService, PacienteServiceImpl

HistorialService, HistorialServiceImpl

Orquestan llamadas a los DAO, aplican validaciones y manejan transacciones cuando se realizan operaciones combinadas (por ejemplo, alta de paciente + alta de historia clínica).

app / menu / main

AppMenu.java o MenuPrincipal.java

Implementa el menú de consola para interactuar con el usuario.

Llama a los métodos de la capa de servicio.

Main.java

Punto de entrada del sistema (public static void main(String[] args)).

========================================================================================

Ejemplo de script SQL base

CREATE DATABASE IF NOT EXISTS historia_clinica;
USE historia_clinica;

CREATE TABLE paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    dni VARCHAR(20) NOT NULL UNIQUE,
    fecha_nacimiento DATE,
    telefono VARCHAR(30),
    email VARCHAR(80),
    direccion VARCHAR(100),
    grupo_sanguineo VARCHAR(5), -- Ej: 'O+', 'A-', etc.
    eliminado BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE historia_clinica (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    fecha_alta DATE NOT NULL,
    observaciones TEXT,
    enfermedades_previas TEXT,
    alergias TEXT,
    medicamentos_habituales TEXT,
    eliminado BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_historia_paciente
        FOREIGN KEY (id_paciente) REFERENCES paciente(id)
);



La relación entre paciente e historia_clinica es de 1 a 1, por lo que el diseño y las validaciones del sistema apuntan a mantener esa correspondencia.


La clase DatabaseConnection centraliza la configuración:
private static final String URL = "jdbc:mysql://localhost:3306/historia_clinica;
private static final String USER = "root";              // Usuario de MySQL
private static final String PASSWORD = "";              // Contraseña (si existe)


Requisitos para la conexión

1) El servidor MySQL debe estar iniciado (por ejemplo, desde XAMPP, WAMP o el servicio de MySQL).

2) La base de datos historia_clinica debe existir y tener las tablas creadas.

3) El archivo mysql-connector-j-[versión].jar debe estar agregado a las Libraries del proyecto NetBeans.




========================================================================================


Funcionalidades Implementadas

El sistema permite gestionar las entidades de la siguiente forma:

Gestión de Pacientes

Crear un nuevo paciente (alta).

Listar todos los pacientes activos.

Buscar paciente por ID o por DNI.

Actualizar datos de un paciente existente (nombre, apellido, contacto, etc.).

Eliminar paciente (borrado lógico mediante campo eliminado o eliminación física según diseño del grupo).

Gestión de Historias Clínicas

Crear historia clínica asociada a un paciente existente.

Listar todas las historias clínicas registradas.

Consultar historia clínica de un paciente.

Actualizar observaciones, antecedentes, alergias y medicamentos.

Eliminar historia clínica (lógica o física).

Validaciones y Reglas de Negocio

No permitir crear un paciente sin datos obligatorios (nombre, apellido, DNI).

No permitir duplicar el DNI de un paciente.

No permitir crear una historia clínica para un paciente inexistente.

Opcional: impedir múltiples historias clínicas para el mismo paciente (relación 1:1).
========================================================================================
Requisitos del Sistema
Componente	Versión recomendada
Java JDK	24 (utilizado en el proyecto)
MySQL	8.0 o superior
IDE	Apache NetBeans
Cliente SQL	DBeaver 25.2.4 / MySQL Workbench
Sistema Operativo	Windows / Linux / macOS
Instalación y Puesta en Marcha
========================================================================================
1. Crear la Base de Datos

Ejecutar el script SQL (o el script completo del proyecto) en MySQL utilizando:

DBeaver, o

MySQL Workbench, o

phpMyAdmin, según preferencia.

Asegurarse de:

Crear la base de datos historia_clinica.

Crear las tablas paciente y historia_clinica (y otras auxiliares si el grupo las definió: tablas de catálogo, etc.).

2. Configurar la Conexión en Java

En la clase DatabaseConnection.java:

Verificar que la URL, usuario y password coincidan con la configuración local de MySQL.

Ejemplo:

private static final String URL = "jdbc:mysql://localhost:3306/historia_clinica";
private static final String USER = "root";
private static final String PASSWORD = "";

3. Agregar el Driver JDBC en NetBeans

Descargar MySQL Connector/J (archivo mysql-connector-j-[versión].jar).

En NetBeans:

Click derecho en el proyecto → Properties.

Ir a Libraries → Add JAR/Folder.

Seleccionar el .jar del conector.

Aceptar.

4. Ejecutar el Proyecto
Opción 1: Desde NetBeans

Abrir el proyecto TPI_HistoriaClinica en NetBeans.

Asegurarse de que la clase con public static void main(String[] args) (por ejemplo, app.Main) esté definida como Main Class:

Click derecho en el proyecto → Properties → Run → Main Class.

Presionar Run Project (F6).

Opción 2: Desde línea de comandos (opcional)

Compilar y ejecutar el proyecto indicando el classpath con el conector de MySQL. Esto depende de la estructura de salida que genera NetBeans, pero conceptualmente sería:

javac -cp ".;ruta\mysql-connector-j-[versión].jar" app\Main.java
java  -cp ".;ruta\mysql-connector-j-[versión].jar" app.Main
========================================================================================
Uso del Sistema (Menú de Consola)

========= MENU =========
1. Crear paciente con Historial
2. Listar pacientes
3. Listar paciente por id
4. Actualizar Paciente

5. Eliminar Paciente por ID
6. crear Historial
7. Listar Historial
8. Leer Historial por ID
9. Actualizar Historial
10. Eliminar Historial
0. Salir
========================================================================================
Opciones habituales
Gestión de Pacientes

1. Crear paciente con Historial

Solicita los datos básicos del paciente: nombre, apellido, DNI, fecha de nacimiento, teléfono, email, dirección, grupo sanguíneo, etc.

Valida que el DNI no esté duplicado.

En la misma operación, solicita los datos de la historia clínica inicial (fecha de alta, observaciones, antecedentes, alergias, medicamentos habituales, etc.).

Crea el paciente y su historia clínica asociada dentro de una misma transacción (si algo falla, se realiza rollback).

2. Listar pacientes

Muestra todos los pacientes activos registrados en la base de datos.

Para cada paciente se visualizan los datos principales: ID, nombre, apellido, DNI y, opcionalmente, otros campos de contacto.

3. Listar paciente por ID

Solicita el ID del paciente.

Muestra los datos completos del paciente correspondiente a ese ID.

Si el ID no existe, informa al usuario que no se encontró el registro.

4. Actualizar Paciente

Solicita el ID del paciente a actualizar.

Muestra los valores actuales y permite modificar campos específicos (por ejemplo: teléfono, email, dirección, etc.).

Puede implementarse la lógica de “Enter para mantener el valor actual” según el diseño.

Aplica validaciones sobre los campos obligatorios y formato de datos.

5. Eliminar Paciente por ID

Solicita el ID del paciente a eliminar.

Según el diseño del trabajo, puede:

Realizar un borrado lógico (marcando un campo eliminado = TRUE), o

Realizar un borrado físico del registro.

En caso de tener una historia clínica asociada, se respetan las reglas de integridad referencial definidas en la base de datos y/o en la capa de servicio.

Gestión de Historias Clínicas

6. Crear Historial

Permite crear una historia clínica para un paciente ya existente.

Solicita el ID del paciente y verifica que exista.

Pide datos como fecha de alta, observaciones, antecedentes médicos, alergias, medicamentos habituales, etc.

Registra la historia clínica asociada a ese paciente.

7. Listar Historial

Muestra todas las historias clínicas registradas en la base de datos.

Incluye información clave como: ID de la historia clínica, ID del paciente, fecha de alta y un resumen de observaciones.

8. Leer Historial por ID

Solicita el ID de la historia clínica.

Muestra todos los detalles de esa historia clínica (fecha de alta, observaciones, antecedentes, alergias, medicamentos, etc.).

Si el ID no existe, informa al usuario que no se encontró el historial solicitado.

9. Actualizar Historial

Solicita el ID de la historia clínica a modificar.

Permite actualizar campos como observaciones, antecedentes, alergias o medicamentos habituales.

Mantiene la relación con el paciente y respeta las reglas de integridad.

10. Eliminar Historial

Solicita el ID de la historia clínica a eliminar.

Según el diseño, puede:

Marcar el registro como eliminado (borrado lógico), o

Eliminarlo físicamente de la base.

Se asegura de no dejar la base de datos en un estado inconsistente respecto al paciente asociado.

0. Salir

========================================================================================

Finaliza la ejecución del sistema y cierra el programa de consola.
Arquitectura del Sistema
Diagrama en Capas (Vista Lógica)
┌───────────────────────────────────────────┐
│           Capa de Presentación           │
│         (Main / Menú de Consola)         │
│   Main, AppMenu, opciones de interacción │
└───────────────────▲───────────────────────┘
                    │
┌───────────────────┴───────────────────────┐
│           Capa de Servicio                │
│    PacienteServiceImpl, HistorialService  │
│  Validaciones, reglas de negocio,        │
│  manejo de transacciones                 │
└───────────────────▲───────────────────────┘
                    │
┌───────────────────┴───────────────────────┐
│           Capa de DAO (Persistencia)      │
│  PacienteDaoImpl, HistoriaClinicaDaoImpl  │
│  JDBC, PreparedStatement, ResultSet       │
└───────────────────▲───────────────────────┘
                    │
┌───────────────────┴───────────────────────┐
│           Capa de Modelo (Entidades)      │
│  Paciente, HistoriaClinica, GrupoSanguineo│
└───────────────────────────────────────────┘
========================================================================================
Componentes Clave

config.DatabaseConnection:

Implementa el patrón Factory para crear conexiones JDBC.

DAO (PacienteDaoImpl, HistoriaClinicaDaoImpl)

Encapsulan el SQL y el manejo de ResultSet.

Services (PacienteServiceImpl, HistorialServiceImpl)

Aplican reglas como:

No duplicar DNI.

Verificar existencia de paciente antes de crear historia clínica.

Manejar commit/rollback cuando una operación implica múltiples pasos.

Solución de Problemas Comunes
Error: ClassNotFoundException: com.mysql.cj.jdbc.Driver

Causa: el .jar del conector de MySQL no está agregado correctamente al proyecto.
Solución: agregar mysql-connector-j-[versión].jar a las Libraries del proyecto en NetBeans.

Error: Communications link failure

Causa: MySQL no está iniciado o la URL/puerto es incorrecto.
Solución:

Verificar que MySQL esté corriendo.

Verificar que la URL JDBC tenga el puerto correcto (ej. 3306).

Error: Access denied for user 'root'@'localhost'

Causa: usuario o contraseña incorrectos.
Solución:

Revisar USER y PASSWORD en DatabaseConnection.

Probar las mismas credenciales en DBeaver o MySQL Workbench.

Error: Unknown database 'historia_clinica' o tablas inexistentes

Causa: la base de datos o las tablas no están creadas.
Solución: ejecutar el script SQL de creación de BD y tablas antes de correr el proyecto.

Contexto Académico
========================================================================================
Carrera: Tecnicatura Universitaria en Programación

Materia: Programación II

Tipo de Evaluación: Trabajo Práctico Integrador (TPI)

Objetivo General:

Implementar un sistema completo con arquitectura en capas, persistencia en base de datos y aplicación de POO.

Simular un escenario real de gestión de información médica (pacientes e historias clínicas).
========================================================================================
Versión del Proyecto: 1.0
Lenguaje: Java 24
Base de Datos: MySQL 8.x
IDE: Apache NetBeans
Clientes SQL: DBeaver 25.2.4, MySQL Workbench


Proyecto educativo desarrollado como Trabajo Práctico Integrador de Programación II
