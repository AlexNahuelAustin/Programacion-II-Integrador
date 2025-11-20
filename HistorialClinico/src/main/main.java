package main;

import dao.HistorialDAO;
import dao.PacienteDAO;
import java.util.Scanner;
import service.HistorialServiceImpl;
import service.PacienteServiceImpl;

/**
 * @Grupo: 50
 * @Alumnos:
 * @Alex Nahuel Austin-Comision 17
 * @Cristian Gabriel Aguirre-Comision 6
 * @Cain Cabrera Bertilazzi-Comision 11
 * @Leonel Jesus Aballay-Comision 17
 */
public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PacienteServiceImpl pacienteService = new PacienteServiceImpl(new PacienteDAO());
        HistorialServiceImpl historialService = new HistorialServiceImpl(new HistorialDAO());

        AppMenu menu = new AppMenu(pacienteService, historialService, sc);
        menu.iniciar();
    }
}
