package de.usu.abiegel.ldap.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.usu.abiegel.ldap.internal.Attribute;
import de.usu.abiegel.ldap.internal.InheritenceBreaker;
import de.usu.abiegel.ldap.internal.Operation;
import de.usu.abiegel.ldap.internal.Token;

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
