/**
 * 
 */
package de.usu.abiegel.ldap.api;

import static org.junit.Assert.*;
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
import static de.usu.abiegel.ldap.TypedInstance.typed;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author usiabiegel
 *
 */
public class SearchDslTestJunit4 implements SearchDsl {

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
	 * {@link de.usu.abiegel.ldap.api.Query#query(de.usu.abiegel.ldap.Token)}.
	 */
	@Test
	public void testQuery() {
		assertThat(query(test -> "value").asString()).isInstanceOf(String.class).isNotNull().isEqualTo("(test=value)");
	}

	/**
	 * Test method for
	 * {@link de.usu.abiegel.ldap.api.And#and(de.usu.abiegel.ldap.Token[])}.
	 */
	@Test
	public void testAnd() {
		assertThat(query(and(typed(test -> "value"), typed(test2 -> "value2"))).asString()).isInstanceOf(String.class)
				.isNotNull().isEqualTo("(&(test=value)(test2=value2))");
	}

	/**
	 * Test method for
	 * {@link de.usu.abiegel.ldap.api.Or#or(de.usu.abiegel.ldap.Token[])}.
	 */
	@Test
	public void testOr() {
		assertThat(query(or(typed(test -> "value"), typed(test2 -> "value2"))).asString()).isInstanceOf(String.class)
				.isNotNull().isEqualTo("(|(test=value)(test2=value2))");
	}

	/**
	 * Test method for
	 * {@link de.usu.abiegel.ldap.api.Not#not(de.usu.abiegel.ldap.Token[])}.
	 */
	@Test
	public void testNot() {
		assertThat(query(not(typed(test -> "value"), typed(test2 -> "value2"))).asString()).isInstanceOf(String.class)
				.isNotNull().isEqualTo("(!(test=value)(test2=value2))");
	}

}
