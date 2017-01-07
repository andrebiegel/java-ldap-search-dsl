package de.usu.abiegel.ldap.api;

import de.usu.abiegel.ldap.internal.Attribute;
import de.usu.abiegel.ldap.internal.Operation;
import de.usu.abiegel.ldap.internal.Token;

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
//		 ParameterizedType superclass = (ParameterizedType) instance.getClass().getGenericSuperclass();
//		    typeToken = superclass.getActualTypeArguments()[0];
	}

	@Override
	public String asString() {
		return instance.asString();
	}
	
	public T instance() {
		return instance;
	}
}