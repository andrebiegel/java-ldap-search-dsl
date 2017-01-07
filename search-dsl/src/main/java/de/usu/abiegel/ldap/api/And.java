package de.usu.abiegel.ldap.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.usu.abiegel.ldap.Attribute;
import de.usu.abiegel.ldap.InheritenceBreaker;
import de.usu.abiegel.ldap.Operation;
import de.usu.abiegel.ldap.Token;
import de.usu.abiegel.ldap.TypedInstance;

public interface And extends InheritenceBreaker {
	public static final String AND = "&";

	default And and(TypedInstance<? extends Token>... children) {
		return new And() {
			@Override
			public String asString() {
				return operation(AND);
			}

			@Override
			public List<? extends Token> children() {
				return Arrays.asList(children).stream().map(TypedInstance::instance).collect(Collectors.toList());
			}
		};
	}

}
