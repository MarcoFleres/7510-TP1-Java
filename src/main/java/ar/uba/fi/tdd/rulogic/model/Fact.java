package ar.uba.fi.tdd.rulogic.model;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ar.uba.fi.tdd.rulogic.model.Regex.espacio;
import static ar.uba.fi.tdd.rulogic.model.Regex.nombreFuncion;
import static ar.uba.fi.tdd.rulogic.model.Regex.parametros;

class Fact implements Evaluable {

    private static final Pattern factPattern = Pattern.compile(
            "^" + espacio + "(" + nombreFuncion + ")" + espacio + // Capturamos el nombre de la funcion (verbo)
            "\\((" + parametros + ")\\)" + espacio + "\\." + espacio + "$"// Capturamos la lista de parametros
        );

    private final String verb;
    private final LinkedList<String> parameters = new LinkedList<>();

    public Fact(String line) {

        Matcher matcher = factPattern.matcher(line);

        if(!matcher.find()) throw new InvalidParameterException("Line " + line + " is not a Fact");

        verb = matcher.group(1);

        for (String parameter : matcher.group(2).split(",")) {
            parameters.add(parameter.trim());
        }

    }

    @Override
    public boolean evaluate(KnowledgeBase db, Query query) {

        List<String> arguments = query.getArguments();

        return arguments.equals(this.parameters);

    }

    @Override
    public String getVerb() {
        return verb;
    }

    public static boolean isFact(String line) {

        return factPattern.matcher(line).matches();

    }

    @Override
    @CoverageIgnore
    public String toString() {
        return "Fact{" +
                "verb='" + verb + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
