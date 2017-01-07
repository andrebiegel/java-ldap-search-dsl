package de.usu.abiegel.ldap.api;

import de.usu.abiegel.ldap.Attribute;
import de.usu.abiegel.ldap.InheritenceBreaker;
import de.usu.abiegel.ldap.Operation;

public interface Query extends InheritenceBreaker{

	default Attribute<String> query(Attribute<String> child) {
		return child;
	}
	default Operation query(Operation child) {
		return child;
	}

}
