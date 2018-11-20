package to.garazuj.message.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	private UserDetails userDetails;
	
	public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
		this.token = accessToken;
		this.username = username;
		this.authorities = authorities;
	}

	public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities,UserDetails userDetails) {
		this.token = accessToken;
		this.username = username;
		this.authorities = authorities;
		this.userDetails = userDetails;
	}

		
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}