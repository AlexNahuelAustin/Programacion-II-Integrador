package main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.GrupoSanguineo;
import model.HistorialClinica;
import model.Paciente;
import service.HistorialServiceImpl;
import service.PacienteServiceImpl;
/**
 * @Grupo: 50 
 * @Alumnos: 
 * @Alex Nahuel Austin-Comision 17
 * @Cristian Gabriel Aguirre-Comision 6
 * @Cain Cabrera Bertolazzi-Comision 11
 * @Leonel Jesus Aballay-Comision 17
 */
public class AppMenu {
    
    private final Scanner sc;
    private final PacienteServiceImpl pacienteService;
    private final HistorialServiceImpl historialService;

    public AppMenu(PacienteServiceImpl pacienteService, HistorialServiceImpl historialService, Scanner sc) {
        this.pacienteService = pacienteService;
        this.historialService = historialService;
        this.sc = sc;
    }
    // Método principal que inicia el menú y maneja el flujo del programa y muestra el menú continuamente hasta que el usuario elige salir
    public void iniciar() {
    int opcion = -1;
    do {
        DesplegarMenu();
        try {
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1 -> crearPacienteConHistorial();
                case 2 -> leerPacientes();
                case 3 -> leerPacientePorId();
                case 4 -> actualizarPaciente();
                case 5 -> eliminarPacientePorId();
                case 6 -> listarHistorial();
                case 7 -> leerHistorialPorId();
                case 8 -> actualizarHistorial();
                case 9 -> borrarHistorial();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Ingrese un número.");
        }
    } while (opcion != 0);
}
    
    // Muestra el menú principal con todas las opciones disponible y esta organizado en secciones para que sea mas legible
    public static void DesplegarMenu() {

        System.out.println("\n===============================");
        System.out.println("       SISTEMA CLINICO         ");
        System.out.println("===============================");
        System.out.println("[1] Crear paciente con historial");
        System.out.println("[2] Listar pacientes");
        System.out.println("[3] Buscar paciente por ID");
        System.out.println("[4] Actualizar paciente");
        System.out.println("[5] Eliminar paciente por ID");
        System.out.println("-------------------------------");
        System.out.println("[6] Listar historiales clinicos");
        System.out.println("[7] Buscar historial por ID");
        System.out.println("[8] Actualizar historial");
        System.out.println("[9] Eliminar historial");
        System.out.println("-------------------------------");
        System.out.println("[0] Salir");
        System.out.println("===============================");
        System.out.print("Ingrese una opcion: ");
    }

    /*
    Crea un nuevo paciente junto con su historial clínico.
    Pide todos los datos personales del paciente y luego crea el historial médico.
    En caso de poner un dato erroneo sale error.
     */
    public void crearPacienteConHistorial() {
        try {
            System.out.print("Ingrese el nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese el apellido: ");
            String apellido = sc.nextLine();
            System.out.print("Ingrese el DNI: ");
            String dni = sc.nextLine();
            System.out.print("Ingrese su fecha en formato yyyy-mm-dd: ");
            LocalDate fecha = LocalDate.parse(sc.nextLine());
            System.out.println("");
            System.out.println("=== CREANDO HISTORIAL ===");
            HistorialClinica historial = crearHistorial();
            Paciente paciente = new Paciente(nombre, apellido, dni, fecha, historial.getId());
            paciente.setHistorial(historial);
            pacienteService.insertar(paciente);

        } catch (Exception e) {
            System.out.println("Error al crear Paciente: " + e.getMessage());
        }

    }

    /*
    Crea un nuevo historial clínico pidiendo todos los datos médicos necesarios.
    Muestra los grupos sanguíneos disponibles para que el usuario elija.
    Valida que las entradas numéricas sean correctas.
     */
    public HistorialClinica crearHistorial() {
        try {
            System.out.print("Ingrese el numero Historial: ");
            String nroHistorial = sc.nextLine();
            int cont = 1;
            for (GrupoSanguineo g : GrupoSanguineo.values()) {
                System.out.println(cont + ")" + g.toString());
                cont++;
            }
            System.out.print("Ingrese el Grupo Sanguineo: ");
            int opcion = Integer.parseInt(sc.nextLine());
            GrupoSanguineo grupo = GrupoSanguineo.values()[opcion - 1];
            System.out.print("Ingrese los antecedentes: ");
            String antecedentes = sc.nextLine();
            System.out.print("Ingrese su medicacion: ");
            String medicacion = sc.nextLine();
            System.out.print("Observaciones: ");
            String observaciones = sc.nextLine();
            HistorialClinica historial = new HistorialClinica(nroHistorial, grupo, antecedentes, medicacion, observaciones, 0);
            historialService.insertar(historial);

            return historial;

        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numérico para el Grupo Sanguíneo.");
            throw new RuntimeException("Error de entrada de datos: " + e.getMessage(), e);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Opción de Grupo Sanguíneo fuera de rango.");
            throw new RuntimeException("Error de entrada de datos (rango): " + e.getMessage(), e);

        } catch (Exception ex) {
            System.out.println("Error al crear el Historial Clínico: " + ex.getMessage());
            throw new RuntimeException("Error de sistema al crear el historial: " + ex.getMessage(), ex);
        }
    }

    /*
     Actualiza los datos de un paciente existente
     Pide el ID del paciente y luego solicita los nuevos datos
     Valida que el ID sea válido antes de proceder
     */
    public Paciente actualizarPaciente() {
        try {
            System.out.print("Ingrese el id Del paciente a actualizar: ");
            int idPaciente = Integer.parseInt(sc.nextLine());
            if (idPaciente > 0) {
                pacienteService.getById(idPaciente);
            }

            System.out.print("Ingrese el nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese el apellido: ");
            String apellido = sc.nextLine();
            System.out.print("Ingrese el DNI: ");
            String dni = sc.nextLine();
            System.out.print("Ingrese su fecha en formato yyyy-mm-dd: ");
            LocalDate fecha = LocalDate.parse(sc.nextLine());
            Paciente paciente = new Paciente(nombre, apellido, dni, fecha, idPaciente);
            pacienteService.actualizar(paciente);

        } catch (Exception e) {
            System.out.println("Error al actualizar Paciente..." + e.getMessage());
        }
        return null;
    }

    /*
    Muestra todos los pacientes registrados en el sistema
    Lista cada paciente con sus datos principales
    Informa si no hay pacientes registrados
     */
    public void leerPacientes() {
        try {
            List<Paciente> pacientes = pacienteService.getAll();
            if (!pacientes.isEmpty()) {
                for (Paciente paciente : pacientes) {
                    System.out.println(paciente);
                    System.out.println("-----------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al Listar Pacientes " + e.getMessage());
        }
    }

    /* 
    Elimina un paciente por su ID (borrado lógico)
    Verifica que el paciente exista y no esté ya eliminado
    Valida que el ID sea un número positivo
     */
    public void eliminarPacientePorId() {
        try {
            System.out.print("Ingrese id de Paciente: ");
            int id = Integer.parseInt(sc.nextLine());
            if (id <= 0) {
                System.out.println("El id debe ser mayor a 0.");
                return;

            }
            Paciente paciente = pacienteService.getById(id);
            if (paciente != null && !paciente.isEliminado()) {
                pacienteService.eliminar(id);
                System.out.println("Paciente eliminado con exito: ");
            }

        } catch (NumberFormatException nfe) {
            System.out.println("El id debe ser un Numero: " + nfe.getMessage());

        } catch (Exception e) {
            System.out.println("Error al eliminar paciente: " + e.getMessage());
        }
    }

    /*
    Busca y muestra un paciente específico por su ID
    Verifica que el paciente exista y no esté eliminado
    Maneja errores de formato de ID y pacientes no encontrados
     */
    public void leerPacientePorId() {
        try {
            System.out.print("Ingrese el ID para buscar: ");
            int id = Integer.parseInt(sc.nextLine());
            Paciente paciente = pacienteService.getById(id);

            if (id > 0 && !paciente.isEliminado()) {
                System.out.println("Paciente: " + paciente.toString());
            }

        } catch (NumberFormatException nfe) {
            System.out.println("Numero de ID incorrecto... " + nfe.getMessage());

        } catch (Exception e) {
            System.out.println("EL ID no fue encontrado o el paciente fue eliminado... ");
        }
    }

    /*
    Lista todos los historiales clínicos registrados en el sistema
    Muestra cada historial con sus datos principales
     */
    public void listarHistorial() {
        try {
            List<HistorialClinica> historial = historialService.getAll();
            if (historial != null) {
                for (HistorialClinica historialClinica : historial) {
                    System.out.println(historialClinica);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer Historial" + e.getMessage());
        }
    }

    /*
    Elimina un historial clínico por su ID
    También elimina el paciente asociado para mantener la consistencia
    Valida que el ID sea un número positivo
     */
    public void borrarHistorial() {
        try {
            System.out.print("Igrese ID para borrar Historial: ");
            int id = Integer.parseInt(sc.nextLine());
            if (id > 0) {
                System.out.println("Borrando Historial..");
                historialService.eliminar(id);
                pacienteService.eliminar(id);
            }

        } catch (NumberFormatException nfe) {
            System.out.println("Error al ingresar ID... " + nfe.getMessage());

        } catch (Exception e) {
            System.out.println("Error al borrar Historial..." + e.getMessage());
        }
    }

    /*
    Busca y muestra un historial clínico específico por su ID
    Verifica que el historial exista y no esté eliminado
     */
    public void leerHistorialPorId() {
        try {
            System.out.print("Ingrese el ID para buscar: ");
            int id = Integer.parseInt(sc.nextLine());
            HistorialClinica historial = historialService.getById(id);
            if (id > 0 && !historial.isEliminado()) {
                System.out.println("Historial: " + historial.toString());
            }

        } catch (NumberFormatException nfe) {
            System.out.println("ID incorrecto...");
        } catch (Exception e) {
            System.out.println("Error al Leer Historial...");
        }

    }

    /*
    Actualiza los datos de un historial clínico existente
    Pide el ID del historial y luego solicita los nuevos datos médicos
    Muestra las opciones de grupo sanguíneo para selecciona
     */
    public void actualizarHistorial() {
        try {
            System.out.print("Ingrese el ID del Historial: ");
            int idHistorial = Integer.parseInt(sc.nextLine());
            if (idHistorial > 0) {
                historialService.getById(idHistorial);
            }
            System.out.print("Ingrese el numero Historial: ");
            String nroHistorial = sc.nextLine();
            int cont = 1;
            int opcion = -1;
            for (GrupoSanguineo g : GrupoSanguineo.values()) {
                System.out.println(cont + ")" + g.toString());
                cont++;
            }
            System.out.print("Ingrese el Grupo Sanguineo: ");
            opcion = Integer.parseInt(sc.nextLine());
            GrupoSanguineo grupo = GrupoSanguineo.values()[opcion - 1];
            System.out.print("Ingrese los antecedentes: ");
            String antecedentes = sc.nextLine();
            System.out.print("Ingrese su medicacion: ");
            String medicacion = sc.nextLine();
            System.out.print("Observaciones: ");
            String observaciones = sc.nextLine();
            HistorialClinica historial = new HistorialClinica(nroHistorial, grupo, antecedentes, medicacion, observaciones, idHistorial);
            historialService.actualizar(historial);
            System.out.println("Historial Actualizado");

        } catch (Exception e) {
            System.out.println("Error al actualizar Historial... " + e.getMessage());
        }
    }

}





