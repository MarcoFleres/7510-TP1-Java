package ar.uba.fi.tdd.rulogic.model;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Fact implements Evaluable {

    private static Pattern factPattern = Pattern.compile("^ *([a-zA-Z]+)\\(([a-z ,]*?)\\) *\\. *$");

    private final String verb;
    private final LinkedList<String> parameters = new LinkedList<>();

    public Fact(String line) {

        Matcher matcher = factPattern.matcher(line);

        if(!matcher.find()) throw new InvalidParameterException("Line " + line + " is not a Fact");

        verb = matcher.group(1);

        for(int i = 2 ; i <= matcher.groupCount() ; i++) {
            parameters.add(matcher.group(i));
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
    public String toString() {
        return "Fact{" +
                "verb='" + verb + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
