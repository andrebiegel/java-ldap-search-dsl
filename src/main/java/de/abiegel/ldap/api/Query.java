package de.abiegel.ldap.api;

import de.abiegel.ldap.internal.Attribute;
import de.abiegel.ldap.internal.InheritenceBreaker;
import de.abiegel.ldap.internal.Operation;
import de.abiegel.ldap.internal.Token;
import de.abiegel.ldap.internal.TypedInstance;

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
