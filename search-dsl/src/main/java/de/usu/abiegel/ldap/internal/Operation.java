package de.usu.abiegel.ldap.internal;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  &(() ()())
 * @author usiabiegel
 *
 */
public interface Operation extends Token{
	List<Token> children();
    default String childrenAsString() {
		return children().stream().map(Token::asString).collect(Collectors.joining());
    }

    default String operation(String op) {
    	return "(" + op + childrenAsString() + ")";
    }
    
	default<Type extends Operation> TypedInstance<Operation> ops(Type or) {
		return TypedInstance.genericType(or);
	}

}
