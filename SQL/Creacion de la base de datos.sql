-- Trabajo integrador de programacion 2.
-- Tema elegido: Historias clinicas.
-- Grupo: 50
-- Alumnos: Alex Nahuel Austin, Cain Bertolazzi, Leonel Jesus Aballay, Cristrian Gabriel Aguirre.
-- ==============================================================================================

 -- Creamos la base de datos con create
 CREATE DATABASE historial_clinico;
 
 -- Usamos USE para dejar predefinida historial_clinico y poder crear las tablas
 USE historial_clinico;

-- Creamos la tabla de historia clinica
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

-- Creamos la tabla paciente co el id de Historia clinica.
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

-- usamos el select para que se crearon las tablas.
SELECT * FROM historia_clinica;
SELECT * FROM paciente;
