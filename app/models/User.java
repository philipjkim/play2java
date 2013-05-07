package models;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;

import play.data.format.Formats.NonEmpty;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class User extends Model {

	private static final long serialVersionUID = 5854422586239724109L;

	private static final int PASSWORD_MIN_LENGTH = 6;
	private static final int SALT_LENGTH = 6;
	private static final int ACCESS_TOKEN_LENGTH = 32;

	@Id
	@Required
	@NonEmpty
	public String email;

	public String nickname;

	@Required
	public String password;

	public String salt;

	public String accessToken;

	public static Finder<String, User> find = new Finder<String, User>(
			String.class, User.class);

	public static List<User> findAll() {
		return find.all();
	}

	public static User findByEmail(String email) {
		return find.where().eq("email", email).findUnique();
	}

	public static void create(User user) {
		user.updatePassword(user.password);
		if (isNullOrEmpty(user.nickname))
			user.nickname = StringUtils.substringBefore(user.email, "@");
		user.save();
	}

	public static void remove(String email) {
		find.ref(email).delete();
	}

	public static boolean isAuthValid(String email, String password) {
		User user = findByEmail(email);
		if (user == null)
			return false;

		return user.isValidPassword(password);
	}

	public boolean isValidPassword(String password) {
		return this.password.equals(DigestUtils.md5Hex(password + this.salt));
	}

	public void updatePassword(String plainPassword) {
		checkNotNull(plainPassword, "password should not be null.");
		checkArgument(plainPassword.length() >= PASSWORD_MIN_LENGTH,
				"password length should be greater than 5.");

		salt = RandomStringUtils.randomAlphanumeric(SALT_LENGTH);
		password = DigestUtils.md5Hex(plainPassword + salt);
		updateAccessToken();
	}

	public void updateAccessToken() {
		accessToken = RandomStringUtils.randomAlphanumeric(ACCESS_TOKEN_LENGTH);
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", nickname=" + nickname
				+ ", password=" + password + ", salt=" + salt
				+ ", accessToken=" + accessToken + "]";
	}

}
