/**
 * 
 */
package de.usu.abiegel.ldap.api;

import static org.junit.Assert.*;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat; // main one
import static org.assertj.core.api.Assertions.atIndex; // for List assertions
import static org.assertj.core.api.Assertions.entry; // for Map assertions
import static org.assertj.core.api.Assertions.tuple; // when extracting several properties at once
import static org.assertj.core.api.Assertions.fail; // use when writing exception tests
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown; // idem
import static org.assertj.core.api.Assertions.filter; // for Iterable/Array assertions
import static org.assertj.core.api.Assertions.offset; // for floating number assertions

import static org.assertj.core.api.Assertions.anyOf; // use with Condition
import static org.assertj.core.api.Assertions.contentOf; // use with File assertions

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.usu.abiegel.ldap.internal.Attribute;

/**
 * @author usiabiegel
 *
 */
public class SearchDslTestJunit4 implements SearchDsl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4037443573521241L;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link de.usu.abiegel.ldap.api.Query#query(de.usu.abiegel.ldap.internal.Token)}
	 * .
	 */
	@Test
	public void testQuery() {
		assertThat(query(test -> "value").asString()).isInstanceOf(String.class).isNotNull().isEqualTo("(test=value)");
	}

	/**
	 * Test method for
	 * {@link de.usu.abiegel.ldap.api.And#and(de.usu.abiegel.ldap.internal.Token[])}
	 * .
	 */
	@Test
	public void testAnd() {
		assertThat(query(and(attr(test -> "value"), attr(test2 -> "value2"))).asString()).isInstanceOf(String.class)
				.isNotNull().isEqualTo("(&(test=value)(test2=value2))");

		assertThat(query(and((Supplier<Attribute<String>>) () -> test -> "value",
				(Supplier<Attribute<String>>) () -> test2 -> "value2")).asString()).isInstanceOf(String.class)
						.isNotNull().isEqualTo("(&(test=value)(test2=value2))");

		assertThat(query(and((Attribute<String>) test -> "value", (Attribute<String>) test2 -> "value2")).asString())
				.isInstanceOf(String.class).isNotNull().isEqualTo("(&(test=value)(test2=value2))");

		assertThat(query(and(attr(test -> "value"), attr(test2 -> "value2"),
				and(attr(test -> "value"), attr(test2 -> "value2")))).asString()).isInstanceOf(String.class).isNotNull()
						.isEqualTo("(&(test=value)(&(test=value)(test2=value2))(test2=value2))");

	}

	/**
	 * Test method for
	 * {@link de.usu.abiegel.ldap.api.Or#or(de.usu.abiegel.ldap.internal.Token[])}
	 * .
	 */
	@Test
	public void testOr() {
		assertThat(query(or(attr(test -> "value"), attr(test2 -> "value2"))).asString()).isInstanceOf(String.class)
				.isNotNull().isEqualTo("(|(test=value)(test2=value2))");
	}

	/**
	 * Test method for
	 * {@link de.usu.abiegel.ldap.api.Not#not(de.usu.abiegel.ldap.internal.Token[])}
	 * .
	 */
	@Test
	public void testNot() {
		assertThat(query(not(attr(test -> "value"), attr(test2 -> "value2"))).asString()).isInstanceOf(String.class)
				.isNotNull().isEqualTo("(!(test=value)(test2=value2))");
	}

}
