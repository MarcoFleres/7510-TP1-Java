package ar.uba.fi.tdd.rulogic.model;

interface Evaluable {

    boolean evaluate(KnowledgeBase db, Query query);

    String getVerb();

}
