package ar.uba.fi.tdd.rulogic.model;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class KnowledgeBase {

	private HashMap<String, LinkedList<Evaluable>> evaluables = new HashMap<>();


	public KnowledgeBase() throws IOException {

		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(KnowledgeBase.class.getClassLoader().getResourceAsStream("./rules.db")));

		String line;

		while((line = reader.readLine()) != null) {

			if(Fact.isFact(line)) {
				addEvaluable(new Fact(line));
			} else if(Rule.isRule(line)) {
				addEvaluable(new Rule(line));
			}

		}

		System.out.println(evaluables);

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

		if(evaluables == null || evaluables.size() == 0) {
			return false;
		}

		return evaluables.stream().anyMatch(e -> e.evaluate(this, query));

	}


}
