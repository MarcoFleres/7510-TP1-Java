package ar.uba.fi.tdd.rulogic.model;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Rule implements Evaluable {

    private static Pattern rulePattern = Pattern.compile("\\s*([a-zA-Z]+)\\(([A-Z\\s,]*?)\\)\\s*:-\\s*((?:\\s*(?:[a-zA-Z]+)\\((?:[A-Z\\s,]*?)\\)\\s*,?\\s*)+)\\.");
    private static Pattern targetPattern = Pattern.compile("([a-zA-Z]+)\\(([A-Z\\s,]*?)\\)");

    private String verb;
    private LinkedList<String> parameters = new LinkedList<>();
    private LinkedList<Target> targets = new LinkedList<>();

    public Rule(String line) {

        Matcher matcher = rulePattern.matcher(line);

        if(! matcher.find()) throw new IllegalArgumentException(line + " is not a Rule");

        verb = matcher.group(1);
        parameters.addAll(Arrays.asList(matcher.group(2).split(" *, *")));

        Matcher factsMatcher = targetPattern.matcher(matcher.group(3));

        while(factsMatcher.find()) {

            targets.add(new Target(factsMatcher.group()));

        }

    }

    @Override
    public boolean evaluate(KnowledgeBase db, Query query) {

        List<String> arguments = query.getArguments();

        if(arguments.size() != parameters.size()) return false;

        HashMap<String, String> map = new HashMap<>();

        return targets.stream().allMatch(t -> t.evaluate(db, arguments));

    }

    public static boolean isRule(String line) {
        return rulePattern.matcher(line).matches();
    }

    @Override
    public String getVerb() {
        return verb;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "verb='" + verb + '\'' +
                ", parameters=" + parameters +
                ", targets=" + targets +
                '}';
    }

    class Target {

        private String verb;
        /** Mapea los parametros por posicion entre la regla y el objetivo */
        private SortedMap<Integer, Integer> parameterMapping = new TreeMap<>();

        public Target(String line) {

            Matcher matcher = targetPattern.matcher(line);

            if(!matcher.find()) throw new InvalidParameterException(line + " is not a valid Target");

            verb = matcher.group(1);
            String[] parameters = matcher.group(2).split(" *, *");

            // Creamos un mapeo entre los parametros del objetivo y la regla

            for (int i = 0; i < parameters.length; i++) {
                String parameter = parameters[i];

                int ruleParameterIndex = Rule.this.parameters.indexOf(parameter);
                parameterMapping.put(i, ruleParameterIndex);

            }

        }

        /**@param parameters parametros con que la regla es evaluada
         * */
        public boolean evaluate(KnowledgeBase db, List<String> parameters) {

            List<String> appliedParameters = new LinkedList<>();

            for(int i = 0 ; i < parameterMapping.size() ; i++) {
                Integer ruleParameterIndex = parameterMapping.get(i);

                appliedParameters.add(parameters.get(ruleParameterIndex));

            }

            return db.answer(new Query(verb, appliedParameters));

        }

        @Override
        public String toString() {
            return "Target{" +
                    "verb='" + verb + '\'' +
                    ", parameterMapping=" + parameterMapping +
                    '}';
        }
    }

}
