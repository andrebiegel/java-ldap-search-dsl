package de.usu.abiegel.ldap.api;

import de.usu.abiegel.ldap.internal.Attribute;
import de.usu.abiegel.ldap.internal.InheritenceBreaker;
import de.usu.abiegel.ldap.internal.Operation;
import de.usu.abiegel.ldap.internal.Token;
import de.usu.abiegel.ldap.internal.TypedInstance;

public interface Query extends InheritenceBreaker{

	default Attribute query(Attribute child) {
		return child;
	}
	default Operation query(Operation child) {
		return child;
	}

	default Token query(TypedInstance<? extends Token> child) {
		return child.instance();
	}

}
