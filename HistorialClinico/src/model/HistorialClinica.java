package model;

/**
 * -Grupo: 50 -Alumnos:
 *
 * @author Alex Nahuel Austin-Comision 17
 * @author Cristian Gabriel Aguirre-Comision 6
 * @author Cain Cabrera Bertilazzi-Comision 11
 * @author Leonel Jesus Aballay-Comision 17
 */
public class HistorialClinica extends Base {

    //Declaramos atributos de la clase.
    private String numeroHistoria;
    private GrupoSanguineo grupoSanguineo;
    private String antecedentes;
    private String medicacionActual;
    private String observaciones;

    //Declaramos Constructores, una para parametros y otro vacio.
    public HistorialClinica(String numeroHistoria, GrupoSanguineo grupoSanguineo, String antecedentes, String medicacionActual, String observaciones, int id) {
        super(false, id);
        this.numeroHistoria = numeroHistoria;
        this.grupoSanguineo = grupoSanguineo;
        this.antecedentes = antecedentes;
        this.medicacionActual = medicacionActual;
        this.observaciones = observaciones;
    }

    public HistorialClinica() {
        super();
    }

    //Getters y Setters
    public String getNumeroHistoria() {
        return numeroHistoria;
    }

    public void setNumeroHistoria(String numeroHistoria) {
        this.numeroHistoria = numeroHistoria;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getMedicacionActual() {
        return medicacionActual;
    }

    public void setMedicacionActual(String medicacionActual) {
        this.medicacionActual = medicacionActual;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public GrupoSanguineo getGrupoSanguineo() {
        return grupoSanguineo;
    }

    @Override
    public String toString() {
        return "HistorialClinica:\n"
                + "  id=" + getId() + "\n"
                + "  numeroHistoria=" + numeroHistoria + "\n"
                + "  grupoSanguineo=" + grupoSanguineo + "\n"
                + "  antecedentes=" + antecedentes + "\n"
                + "  medicacionActual=" + medicacionActual + "\n"
                + "  observaciones=" + observaciones;
    }

}
