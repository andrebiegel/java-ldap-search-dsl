package de.abiegel.ldap.api;

import de.abiegel.ldap.internal.Attribute;

public interface SearchDsl extends Query, And, Or, Not,Attribute<String>,Exists {


	@Override
	default String asString() {
		return "";
	}

	
	@Override
	default String apply(String t) {
		return t;
	}

	
}
