package de.abiegel.ldap.api;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import de.abiegel.ldap.internal.Operation;
import de.abiegel.ldap.internal.Token;
import de.abiegel.ldap.internal.TypedInstance;

public interface And extends Operation {
	public static final String AND = "&";

	default TypedInstance<Operation> and(TypedInstance<? extends Token>... children) {
		return ops(new And() {
			@Override
			public String asString() {
				return operation(AND);
			}

			@Override
			public List<Token> children() {
				return Arrays.asList(children).stream().map(TypedInstance::instance).collect(Collectors.toList());
			}
		});
	}

	@SuppressWarnings("unchecked")
	default And and(Supplier<? extends Token>... children) {
		return new And() {
			@Override
			public String asString() {
				return operation(AND);
			}

			@Override
			public List<Token> children() {
				return Arrays.asList(children).stream().map(Supplier::get).collect(Collectors.toList());
			}
		};
	}

	default And and(Token... children) {
		return new And() {
			@Override
			public String asString() {
				return operation(AND);
			}

			@Override
			public List<Token> children() {
				return Arrays.asList(children).stream().collect(Collectors.toList());
			}
		};
	}

}
