package ar.uba.fi.tdd.rulogic.model;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ar.uba.fi.tdd.rulogic.model.Regex.espacio;
import static ar.uba.fi.tdd.rulogic.model.Regex.nombreFuncion;
import static ar.uba.fi.tdd.rulogic.model.Regex.parametros;

public class Query {

    private final String verb;
    private final List<String> parameters = new LinkedList<>();

    private static Pattern assertionPattern = Pattern.compile(
            "^" + espacio + "(" + nombreFuncion + ")" + espacio +
            "\\((" + parametros + ")\\)" + espacio + "\\.?" + espacio + "$"
        );


    public Query(String line) {
        Matcher matcher = assertionPattern.matcher(line);

        // El plugin "cobertura" no parece reconocer este branch, aunque haya test que lo prueban.
        if(!matcher.find()) throw new InvalidParameterException("Invalid Query: " + line);

        verb = matcher.group(1);

        for (String parameter : matcher.group(2).split(",")) {
            parameters.add(parameter.trim());
        }

    }

    public Query(String verb, List<String> parameters) {
        this.verb = verb;
        this.parameters.addAll(parameters);
    }

    public String getVerb() {
        return verb;
    }

    public List<String> getArguments() {
        return parameters;
    }

}
