package de.usu.abiegel.ldap.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.usu.abiegel.ldap.internal.InheritenceBreaker;
import de.usu.abiegel.ldap.internal.Token;

public interface Not extends InheritenceBreaker {
	public static final String NOT = "!";

	default Not not(TypedInstance<?extends Token>... children) {
		return new Not() {
			@Override
			public String asString() {
				return operation(NOT);
			}

			@Override
			public List<? extends Token> children() {
				return Arrays.asList(children).stream().map(TypedInstance::instance).collect(Collectors.toList());
			}
		};
	}

}
