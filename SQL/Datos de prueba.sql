-- Trabajo integrador de programacion 2.
-- Tema elegido: Historias clinicas.
-- Grupo: 50
-- Alumnos: Alex Nahuel Austin, Cain Bertolazzi, Leonel Jesus Aballay, Cristrian Gabriel Aguirre. 
-- ==============================================================================================

 -- Usamos USE para dejar para poder cargar los datos de pruebas en ambas tablas.
 USE historial_clinico;

-- El id es auto incremental por lo que no se aclara en la insercion de datos.
-- 1. Creamos los historiales clinicos.
INSERT INTO historia_clinica (nro_historia, grupo_sanguineo, antecedentes, medicacion_actual, observaciones) VALUES
('HC-0001', 'O+', 'Alergia al látex.', 'Ninguna.', 'Control de rutina.'),
('HC-0002', 'A-', 'Diabetes Tipo 1.', 'Insulina.', 'Requiere monitoreo constante.'),
('HC-0003', 'B+', 'Asma crónica.', 'Salbutamol.', 'Seguimiento por neumonología.'),
('HC-0004', 'AB-', 'Hipertensión arterial (HTA).', 'Losartán.', 'Dieta baja en sodio.'),
('HC-0005', 'O-', 'Antecedentes de fractura de clavícula.', 'Paracetamol.', 'No deportes de contacto.'),
('HC-0006', 'A+', 'Migrañas.', 'Sumatriptán.', 'Evitar luces fuertes.'),
('HC-0007', 'B-', 'Hipotiroidismo.', 'Levotiroxina.', 'Control hormonal anual.'),
('HC-0008', 'AB+', 'Alergia al marisco.', 'Ninguna.', 'Advertir en comidas.'),
('HC-0009', 'O+', 'Cirugía de apéndice a los 12 años.', 'Ninguna.', 'Buen estado general.'),
('HC-0010', 'A-', 'Fiebre reumática en la infancia.', 'Profilaxis antibiótica.', 'Control cardiológico.');

-- 2. Creamos el paciente con asociado al id del historial_Clinica, paciente-->historial_clinico 1:1.
INSERT INTO paciente (nombre, apellido, dni, fecha_nacimiento, historia_clinica_id) VALUES
('Alex', 'Austin', '40123456', '1997-05-15', 1),
('Cain', 'Bertolazzi', '35678901', '1989-11-23', 2),
('Leonel', 'Aballay', '42345678', '2000-01-01', 3),
('Cristian', 'Aguirre', '38765432', '1992-08-04', 4),
('Maria', 'Gimenez', '25098765', '1975-03-10', 5),
('Juan', 'Perez', '18654321', '1960-07-20', 6),
('Sofia', 'Rodriguez', '45876543', '2005-02-28', 7),
('Pedro', 'Gomez', '29123987', '1981-12-05', 8),
('Laura', 'Diaz', '33456789', '1986-06-19', 9),
('Carlos', 'Nuñez', '20112233', '1970-04-25', 10);


-- Verificacion de la asociación de relación 1:1 entre Paciente e Historia Clínica.
SELECT *
FROM paciente P
JOIN historia_clinica H ON P.historia_clinica_id = H.id;