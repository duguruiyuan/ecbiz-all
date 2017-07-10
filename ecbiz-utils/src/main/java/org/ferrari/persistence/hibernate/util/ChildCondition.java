package org.ferrari.persistence.hibernate.util;

public class ChildCondition extends Condition{

	public ChildCondition(String enityPropertyName, RelationOperation relation,
			LogicOperation logic, Boolean logicNot, Object queryValue,
			Object[] queryValues) {
		super(enityPropertyName, relation, logic, queryValue);

	}

}
