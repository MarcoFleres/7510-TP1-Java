package ar.uba.fi.tdd.rulogic.model;

public abstract class Regex {
    public static final String espacio = "\\s*";
    public static final String nombreFuncion = "[a-zA-Z]+";
    public static final String parametro = "[a-zA-Z]+";
    public static final String parametros = "(?:" + espacio + parametro + espacio + ",?)*";
}
