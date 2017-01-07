package de.usu.abiegel.ldap.api;

import de.usu.abiegel.ldap.internal.Attribute;
import de.usu.abiegel.ldap.internal.InheritenceBreaker;
import de.usu.abiegel.ldap.internal.Operation;

public interface Query extends InheritenceBreaker{

	default Attribute<String> query(Attribute<String> child) {
		return child;
	}
	default Operation query(Operation child) {
		return child;
	}

}
