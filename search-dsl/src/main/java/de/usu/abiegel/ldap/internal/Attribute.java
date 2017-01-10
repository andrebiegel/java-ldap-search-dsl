package de.usu.abiegel.ldap.internal;

public interface Attribute<T> extends NamedValue<T>,Token {
    default String asString() {
        return "(" +name() + "=" + value() + ")";
    }
    
	default TypedInstance<Attribute<T>> attr(Attribute<T> instance) {
		return TypedInstance.genericType(instance);
	}

}