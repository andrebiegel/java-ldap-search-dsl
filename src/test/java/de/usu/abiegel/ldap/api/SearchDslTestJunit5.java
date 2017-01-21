package de.usu.abiegel.ldap.api;

import java.util.function.Supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import de.usu.abiegel.ldap.internal.Attribute;

public class SearchDslTestJunit5 implements SearchDsl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2045308473464630473L;

	/**
	 * Test method for
	 * {@link de.usu.abiegel.ldap.api.Query#query(de.usu.abiegel.ldap.internal.Token)}
	 * .
	 */
	@Test
	public void testQuery() {
		Assertions.assertAll("simple query",
				() -> Assertions.assertEquals("(test=value)", query(test -> "value").asString()));

	}

	/**
	 * Test method for
	 * {@link de.usu.abiegel.ldap.api.And#and(de.usu.abiegel.ldap.internal.Token[])}
	 * .
	 */
	@Test
	public void testAnd() {
		Assertions.assertAll("And", () -> {
			Assertions.assertEquals("(&(test=value)(test2=value2))",
					query(and(attr(test -> "value"), attr(test2 -> "value2"))).asString());
			Assertions.assertEquals("(&(test=value)(test2=value2))",
					query(and((Supplier<Attribute<String>>) () -> test -> "value",
							(Supplier<Attribute<String>>) () -> test2 -> "value2")).asString());
			Assertions.assertEquals("(&(test=value)(test2=value2))",
					query(and((Attribute<String>) test -> "value", (Attribute<String>) test2 -> "value2")).asString());
			Assertions.assertEquals("(&(test=value)(&(test=value)(test2=value2))(test2=value2))",
					query(and(attr(test -> "value"), attr(test2 -> "value2"),
							and(attr(test -> "value"), attr(test2 -> "value2")))).asString());
		});

	}

	/**
	 * Test method for
	 * {@link de.usu.abiegel.ldap.api.Or#or(de.usu.abiegel.ldap.internal.Token[])}
	 * .
	 */
	@Test
	public void testOr() {
		Assertions.assertEquals("(|(test=value)(test2=value2))",query(or(attr(test -> "value"), attr(test2 -> "value2"))).asString());
	}

	/**
	 * Test method for
	 * {@link de.usu.abiegel.ldap.api.Not#not(de.usu.abiegel.ldap.internal.Token[])}
	 * .
	 */
	@Test
	public void testNot() {
		Assertions.assertEquals("(!(test=value)(test2=value2))",query(not(attr(test -> "value"), attr(test2 -> "value2"))).asString())
				;
	}

	@Test
	// implicitly uses TestInfoParameterResolver to provide testInfo
	void testWithBuiltIntParameterResolver(TestInfo testInfo) {
		// ...
	}


}
