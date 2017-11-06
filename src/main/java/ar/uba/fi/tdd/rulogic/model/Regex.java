package ar.uba.fi.tdd.rulogic.model;

abstract class Regex {
    static final String espacio = "\\s*";
    static final String nombreFuncion = "[a-zA-Z]+";
    static final String parametro = "[a-zA-Z]+";
    /** Al menos un parametro, seguido de "," + otro parametro cualquier cantidad de veces */
    static final String parametros = "(?:" + espacio + parametro + espacio + ")(?:,?" + espacio + parametro + espacio + ")*";
}
