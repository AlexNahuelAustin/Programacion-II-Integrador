package model;

/**
 -Grupo: 50
 -Alumnos: 
 * Alex Nahuel Austin-Comision 17
 * Cristian Gabriel Aguirre-Comision 6
 * Cain Cabrera Bertilazzi-Comision 11
 * Leonel Jesus Aballay-Comision 17
 */


public enum GrupoSanguineo {
    A_POSITIVO("A", "+"),
    A_NEGATIVO("A", "-"),
    B_POSITIVO("B", "+"),
    B_NEGATIVO("B", "-"),
    AB_POSITIVO("AB", "+"),
    AB_NEGATIVO("AB", "-"),
    O_POSITIVO("O", "+"),
    O_NEGATIVO("O", "-");
    
    private final String tipo;
    private final String factor;

    private GrupoSanguineo(String tipo, String factor) {
        this.tipo = tipo;
        this.factor = factor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFactor() {
        return factor;
    }
    
    public static GrupoSanguineo fromString(String valor) {
        for (GrupoSanguineo gs : GrupoSanguineo.values()) {
            if (gs.toString().equals(valor)) {
                return gs;
            }
        }
        throw new IllegalArgumentException("Grupo sanguíneo inválido: " + valor);
    }

    @Override
    public String toString() {
        return tipo + factor;
    }
    
}
