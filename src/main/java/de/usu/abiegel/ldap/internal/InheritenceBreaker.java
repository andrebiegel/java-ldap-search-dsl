package de.usu.abiegel.ldap.internal;

import java.util.Collections;
import java.util.List;


public interface InheritenceBreaker extends Operation{
	   default List<Token> children() {
	        return Collections.emptyList();
	    }
	   default String asString() {
	        return "";
	    }

	   
}
