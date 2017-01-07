package de.usu.abiegel.ldap;

public class TypedInstance<T extends Token > implements Token{
	Class<T> type;
	T instance;

	private static <Type extends Token> TypedInstance<Type> genericType(Type instance) {
		return new TypedInstance<Type>(instance);
	}

	public static TypedInstance<Attribute<String>> typed(Attribute<String> instance) {
		return genericType(instance);
	}
	
	public static TypedInstance<Operation> typed(Operation instance) {
		return genericType(instance);
	}
	
	private TypedInstance(T instance) {
		super();
		this.instance = instance;
	}

	@Override
	public String asString() {
		return instance.asString();
	}
	
	public T instance() {
		return instance;
	}
}