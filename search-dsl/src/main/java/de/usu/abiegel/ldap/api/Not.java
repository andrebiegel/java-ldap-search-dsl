package de.usu.abiegel.ldap.api;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import de.usu.abiegel.ldap.internal.InheritenceBreaker;
import de.usu.abiegel.ldap.internal.Token;
import de.usu.abiegel.ldap.internal.TypedInstance;

public interface Not extends InheritenceBreaker {
	public static final String NOT = "!";

	
	default TypedInstance<Not> not(@SuppressWarnings("unchecked") TypedInstance<? extends Token>... children) {
		return ops(new Not() {
			@Override
			public String asString() {
				return operation(NOT);
			}

			@Override
			public List<Token> children() {
				return Arrays.asList(children).stream().map(TypedInstance::instance).collect(Collectors.toList());
			}
		});
	}
//	default Not not(TypedInstance<?extends Token>... children) {
//		return new Not() {
//			@Override
//			public String asString() {
//				return operation(NOT);
//			}
//
//			@Override
//			public List<? extends Token> children() {
//				return Arrays.asList(children).stream().map(TypedInstance::instance).collect(Collectors.toList());
//			}
//		};
//	}

	default Not not(@SuppressWarnings("unchecked") Supplier<?extends Token>... children) {
		return new Not() {
			@Override
			public String asString() {
				return operation(NOT);
			}

			@Override
			public List<Token> children() {
				return Arrays.asList(children).stream().map(Supplier::get).collect(Collectors.toList());
			}
		};
	}

}
