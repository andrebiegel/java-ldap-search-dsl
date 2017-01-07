package de.usu.abiegel.ldap;

public interface Attribute<T> extends NamedValue<T>,Token {
    default String asString() {
        return "(" +name() + "=" + value() + ")";
    }
}