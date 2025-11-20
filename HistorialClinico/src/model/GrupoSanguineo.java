package model;

/**
 * -Grupo: 50 -Alumnos:
 *
 * @author Alex Nahuel Austin-Comision 17
 * @author Cristian Gabriel Aguirre-Comision 6
 * @author Cain Cabrera Bertilazzi-Comision 11
 * @author Leonel Jesus Aballay-Comision 17
 */
public enum GrupoSanguineo {

    // Definición de las constantes del Enum.
    A_POSITIVO("A", "+"),
    A_NEGATIVO("A", "-"),
    B_POSITIVO("B", "+"),
    B_NEGATIVO("B", "-"),
    AB_POSITIVO("AB", "+"),
    AB_NEGATIVO("AB", "-"),
    O_POSITIVO("O", "+"),
    O_NEGATIVO("O", "-");

    //Declaramos los atributos y son final y private para asegurar la inmutabilidad de la información.
    private final String tipo;
    private final String factor;

    private GrupoSanguineo(String tipo, String factor) {
        this.tipo = tipo;
        this.factor = factor;
    }

    // Metetodos Getter
    public String getTipo() {
        return tipo;
    }

    public String getFactor() {
        return factor;
    }

    // Método Estático para conversión y permite obtener una constante Enum a partir de su representación en String (ej. "A+")
    public static GrupoSanguineo fromString(String valor) {
        for (GrupoSanguineo gs : GrupoSanguineo.values()) {
            if (gs.toString().equals(valor)) {
                return gs;
            }
        }
        throw new IllegalArgumentException("Grupo sanguíneo inválido: " + valor);
    }

    // Sobrescritura del método toString()
    @Override
    public String toString() {
        return tipo + factor;
    }

}
