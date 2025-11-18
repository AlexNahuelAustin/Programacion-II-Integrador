package model;

/**
 -Grupo: 50
 -Alumnos: 
 * Alex Nahuel Austin-Comision 17
 * Cristian Gabriel Aguirre-Comision 6
 * Cain Cabrera Bertilazzi-Comision 11
 * Leonel Jesus Aballay-Comision 17
 */
public class HistorialClinica extends Base {
    private String numeroHistoria;
    private GrupoSanguineo grupoSanguineo;
    private String antecedentes;
    private String medicacionActual;
    private String observaciones;

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
        return "HistorialClinica:\n" +
           "  id=" + id + "\n" +
           "  numeroHistoria=" + numeroHistoria + "\n" +
           "  grupoSanguineo=" + grupoSanguineo + "\n" +
           "  antecedentes=" + antecedentes + "\n" +
           "  medicacionActual=" + medicacionActual + "\n" +
           "  observaciones=" + observaciones;
}
    
    
}
