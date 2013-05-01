package models;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	User user;
	
	@Before
	public void setUp() {
		user = new User();
	}
	
	@Test
	public void testUpdatePassword() {
		assertThat(user.password).isNull();
		assertThat(user.salt).isNull();
		assertThat(user.accessToken).isNull();
		user.updatePassword("abcdef");
		assertThat(user.password).isNotEmpty();
		assertThat(user.salt).hasSize(6);
		assertThat(user.accessToken).hasSize(32);
	}
	
	@Test
	public void testIsValidPassword() {
		user.updatePassword("abcdef");
		assertThat(user.isValidPassword("abcdef")).isTrue();
		assertThat(user.isValidPassword("abcdee")).isFalse();
	}
	
	@Test
	public void testUpdateAccessToekn() {
		assertThat(user.accessToken).isNull();
		user.updateAccessToken();
		assertThat(user.accessToken).hasSize(32);
	}
}
