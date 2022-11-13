package com.ba.payloads;

public class JwtAuthResponse {
	
	private String token;

	private UserDto udto;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDto getUdto() {
		return udto;
	}

	public void setUdto(UserDto udto) {
		this.udto = udto;
	}

	public JwtAuthResponse() {
		super();
	}

	public JwtAuthResponse(String token, UserDto udto) {
		super();
		this.token = token;
		this.udto = udto;
	}

	@Override
	public String toString() {
		return "JwtAuthResponse [token=" + token + ", udto=" + udto + "]";
	}
	
	

}
