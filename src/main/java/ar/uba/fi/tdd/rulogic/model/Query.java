package ar.uba.fi.tdd.rulogic.model;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Query {

    private String verb;
    private List<String> parameters;

    private static Pattern assertionPattern = Pattern.compile("^ *([a-zA-Z]+) *\\(([a-z ,]*?)\\)\\.? *$");


    public Query(String line) {
        Matcher matcher = assertionPattern.matcher(line);

        if(!matcher.find()) throw new InvalidParameterException("Invalid Query: " + line);

        verb = matcher.group(1);
        parameters = Arrays.asList(matcher.group(2).split(" *, *"));

    }

    public Query(String verb, List<String> parameters) {
        this.verb = verb;
        this.parameters = parameters;
    }

    public String getVerb() {
        return verb;
    }

    public List<String> getArguments() {
        return parameters;
    }

}
