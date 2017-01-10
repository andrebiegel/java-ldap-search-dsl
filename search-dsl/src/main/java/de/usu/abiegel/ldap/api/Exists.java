package de.usu.abiegel.ldap.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.usu.abiegel.ldap.internal.Attribute;
import de.usu.abiegel.ldap.internal.Token;
import de.usu.abiegel.ldap.internal.TypedInstance;

public interface Exists extends Token {
 
	default TypedInstance<Exists> exists(String child) {
		return attr(new Exists() {
			@Override
			public String asString() {
				return "("+child+"=*)";
			}
		});
	}
	default TypedInstance<Exists> attr(Exists instance) {
		return TypedInstance.genericType(instance);
	}

}
