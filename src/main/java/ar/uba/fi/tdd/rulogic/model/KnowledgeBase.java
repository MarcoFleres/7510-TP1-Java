package ar.uba.fi.tdd.rulogic.model;

import java.util.HashMap;
import java.util.LinkedList;

public class KnowledgeBase {

	private HashMap<String, LinkedList<Evaluable>> evaluables = new HashMap<>();

	public KnowledgeBase(String database) {

		for (String line : database.split("\n")) {

			if(Fact.isFact(line)) {
				addEvaluable(new Fact(line));
			} else if(Rule.isRule(line)) {
				addEvaluable(new Rule(line));
			} else {
				throw new IllegalArgumentException("Illegal Line: " + line);
			}

		}

	}

	private void addEvaluable(Evaluable evaluable) {

		String verb = evaluable.getVerb();

		if(!evaluables.containsKey(verb))
			evaluables.put(verb, new LinkedList<>());

		evaluables.get(verb).add(evaluable);

	}

	public boolean answer(String query) {
		return answer(new Query(query));
	}

	public boolean answer(Query query) {

		LinkedList<Evaluable> evaluables = this.evaluables.get(query.getVerb());

		if(evaluables == null) {
			return false;
		}

		return evaluables.stream().anyMatch(e -> e.evaluate(this, query));

	}

	@Override
	@CoverageIgnore
	public String toString() {

		StringBuilder str = new StringBuilder();

		for (String verb : evaluables.keySet()) {

			str.append(verb).append('\n');

			for (Evaluable evaluable : evaluables.get(verb)) {
				str.append('\t').append(evaluable).append('\n');
			}

		}

		return str.toString();
	}

}
