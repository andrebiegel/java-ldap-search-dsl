package de.usu.abiegel.ldap;

import java.util.Collections;
import java.util.List;


public interface InheritenceBreaker extends Operation{
	   default List<? extends Token> children() {
	        return Collections.emptyList();
	    }
	   default String asString() {
	        return "";
	    }
}
