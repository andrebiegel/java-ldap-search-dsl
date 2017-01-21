package de.usu.abiegel.ldap.internal;

public class TypedInstance<T extends Token >{

	private T instance;

	public static <Type extends Token> TypedInstance<Type> genericType(Type instance) {
		return new TypedInstance<Type>(instance);
	}
	
	private TypedInstance(T instance) {
		super();
		this.instance = instance;
	}

	public T instance() {
		return instance;
	}


}