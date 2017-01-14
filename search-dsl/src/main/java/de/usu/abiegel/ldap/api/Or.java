package de.usu.abiegel.ldap.api;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import de.usu.abiegel.ldap.internal.InheritenceBreaker;
import de.usu.abiegel.ldap.internal.Operation;
import de.usu.abiegel.ldap.internal.Token;
import de.usu.abiegel.ldap.internal.TypedInstance;

public interface Or extends InheritenceBreaker {
	public static final String OR = "|";

	default TypedInstance<Operation> or(TypedInstance<? extends Token>... children) {
		return ops(new Or() {
			@Override
			public String asString() {
				return operation(OR);
			}

			@Override
			public List<Token> children() {
				return Arrays.asList(children).stream().map(TypedInstance::instance).collect(Collectors.toList());
			}
		});
	}

	
//	default Or or(TypedInstance<? extends Token>... children) {
//		return new Or() {
//
//			@Override
//			public String asString() {
//				return operation(OR);
//			}
//
//			@Override
//			public List<? extends Token> children() {
//				return Arrays.asList(children).stream().map(TypedInstance::instance).collect(Collectors.toList());
//			}
//		};
//	}
	default Or or(Supplier<? extends Token>... children) {
		return new Or() {

			@Override
			public String asString() {
				return operation(OR);
			}

			@Override
			public List<Token> children() {
				
				return Arrays.asList(children).stream().map(Supplier::get).collect(Collectors.toList());
			}
		};
	}

}
