- Programacion II Trabajo Integrador
- Sistema de Gestión de Historias Clínicas
- Descripcion del Proyecto: Sistema desarrollado para la materia Programación II que permite gestionar pacientes y sus historias clínicas mediante una aplicación de consola en Java.
------

Grupo: 50
Integrantes del Equipo
* Leonel Jesús Aballay – Comisión 17
* Caín Cabrera Bertolazzi – Comisión 11
* Alex Nahuel Austin – Comisión 17
* Cristian Gabriel Aguirre – Comisión 6
* Link del video:-------
-----

ObJetivos
1. Organizar el codigo por capas.
   * Capa de Presentación: gestión de menús y entrada de datos por consola.
   * Una contiene las reglas de negocio.
   * Una parte se encarga de mostrar menús y recibir datos a travez de la consola
   * Y la última son las clases que representan pacientes y historiales.

2. Programación Orientada a Objetos en la práctica.
   * Manejar los atributos privados y metodos publicos.
   * Usamos herencia para que varias clases compartan código común.
   * Definir los contratos que deben cumplirse.
   * Que cada clase tenga una sola responsabilidad.

3. Trabajo conjunto con Bases de Datos.
   * Conectar a MYSQL usando JDBC.
   * Hacer consultas con PreparedStatement.
   * Uso de patron DAO ara separar la lógica de base de datos.
     
4. Manejo correcto de recursos y errores.
   * Uso de try-with-resources para que Java cierre automáticamente las conexiones.
   * Capturar y manejar excepciones de forma controlada.

5.Validación de datos
  * Validación de datos.
  * Verificamos que no haya DNI duplicados.
  * Validamos que los campos obligatorios no estén vacíos.
  * Respetamos las relaciones entre tablas con claves foráneas.
  * Implementamos eliminaciones seguras para no dejar datos huérfanos.
------

Tecnologia Utilizada:
  - Java - versión 24
  - MySQL - versión 8.x
  - Apache NetBeans - versión 25
  - Driver MySQL Connector - versión 8.4.0
  - HikariCP-6.2.1.jar
  - slf4-api-1.7.36.jar
  - slf4-simple-1.7.36.jar
-------
Configurar Base de Datos
SCRIP

create database historial_clinico;
* Tabla principal:

    CREATE TABLE historia_clinica (

      id BIGINT PRIMARY KEY AUTO_INCREMENT, 
 
      eliminado BOOLEAN NOT NULL DEFAULT FALSE, 
      
      nro_historia VARCHAR(20) UNIQUE, 
      
      grupo_sanguineo ENUM('A+','A-','B+','B-','AB+','AB-','O+','O-'), 
      
      antecedentes TEXT, 
      
      medicacion_actual TEXT, 

      observaciones TEXT, 

      CONSTRAINT chk_nro_historia_len CHECK (CHAR_LENGTH(nro_historia) <= 20)
  
      ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

* Tabla PACIENTE
  
    CREATE TABLE paciente (
  
      id BIGINT PRIMARY KEY AUTO_INCREMENT,
  
      eliminado BOOLEAN NOT NULL DEFAULT FALSE,
  
      nombre VARCHAR(80) NOT NULL,
  
      apellido VARCHAR(80) NOT NULL,
  
      dni VARCHAR(15) NOT NULL UNIQUE,
  
      fecha_nacimiento DATE,
  
      historia_clinica_id BIGINT UNIQUE,
  
      CONSTRAINT chk_nombre_len CHECK (CHAR_LENGTH(nombre) <= 80),
  
      CONSTRAINT chk_apellido_len CHECK (CHAR_LENGTH(apellido) <= 80),
  
      CONSTRAINT chk_dni_len CHECK (CHAR_LENGTH(dni) <= 15),
  
      CONSTRAINT fk_paciente_historia FOREIGN KEY (historia_clinica_id)
  
      REFERENCES historia_clinica(id)
  
      ON DELETE RESTRICT
  
      ON UPDATE CASCADE) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-----

Uso del Sistema
1. Crear paciente con Historial.
2. Listar pacientes.
3. Listar paciente por Id.
4. Actualizar Paciente.
5. Eliminar Paciente por ID.
6. Crear Historial.
7. Listar Historial.
8. Leer Historial por ID.
9. Actualizar Historial.
10. Eliminar Historial.
0. Salir.
   
Ejemplo de uso mas comunes:
  1. Crear paciente con Historial.
     * Te pide los datos del paciente.
     * Verifica que no exista otro paciente con el mismo DNI.
     * Si algo sale mal no se guardan los cambios.
       
  2. Listar pacientes.
     * Muestra todos los pacientes que hay en el sistema.
     * Muestra los datos del paciente.
       
  4. Actualizar Paciente
     * Modificás los datos de un paciente que ya existe.
       
  5. Crear Historial
     * Creás una historia clínica para un paciente que ya existe.
     * Tenés que poner el ID del paciente
     * Cargás observaciones, antecedentes, medicamentos, etc.

  7. Listar Historial
     * Mostramos todos los historiales médicos que hay en la base de datos.
     
  9. Leer Historial por ID
      * Buscás un historial específico por su número de ID.
      * Te muestra todos los detalles de ese historial médico  
-------



   
