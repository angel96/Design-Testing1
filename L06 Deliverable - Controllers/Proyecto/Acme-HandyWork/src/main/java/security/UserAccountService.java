
package security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAccountService {

	@Autowired
	private UserAccountRepository	repository;


	public UserAccountService() {
		super();
	}

	public UserAccount create(final String auth) {
		UserAccount user;
		Authority authority;

		Collection<Authority> authorities;
		authorities = new ArrayList<Authority>();
		authority = new Authority();
		authority.setAuthority(auth);
		authorities.add(authority);

		user = new UserAccount();
		user.setAuthorities(authorities);
		return user;
	}

	public UserAccount submit(final String username, final String password, final String authority) {
		UserAccount result, commit;
		result = this.create(authority);
		result.setUsername(username);
		result.setPassword(password);
		commit = this.repository.save(result);
		return commit;
	}

	public UserAccount hashPassword(final UserAccount account) {
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();
		String passEncoded;

		passEncoded = encoder.encodePassword(account.getPassword(), null);

		account.setPassword(passEncoded);

		return account;
	}

}
