package de.abiegel.ldap.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.abiegel.ldap.internal.Attribute;
import de.abiegel.ldap.internal.Token;
import de.abiegel.ldap.internal.TypedInstance;

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
