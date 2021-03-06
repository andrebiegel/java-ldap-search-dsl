package de.abiegel.ldap.api;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import de.abiegel.ldap.internal.Operation;
import de.abiegel.ldap.internal.Token;
import de.abiegel.ldap.internal.TypedInstance;

public interface Not extends Operation {
	public static final String NOT = "!";

	
	default TypedInstance<Operation> not(@SuppressWarnings("unchecked") TypedInstance<? extends Token> child) {
		return ops(new Not() {
			@Override
			public String asString() {
				return operation(NOT);
			}

			@Override
			public List<Token> children() {
				return Arrays.asList(child.instance());
			}
		});
	}

	default Not not(@SuppressWarnings("unchecked") Supplier<?extends Token> child) {
		return new Not() {
			@Override
			public String asString() {
				return operation(NOT);
			}

			@Override
			public List<Token> children() {
				return Arrays.asList(child.get());
			}
		};
	}

}
