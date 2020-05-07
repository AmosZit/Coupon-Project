package coupon.bean;

import coupon.enums.ClientType;

public class UserDataClient {


	private ClientType clientType;
	private int token;
	private Long id;
	private Long companyId;

	public UserDataClient() {
		super();
	}

	public UserDataClient(int token, ClientType clientType, Long id , Long companyId) {
		super();
		this.token = token;
		this.clientType = clientType;
		this.id = id;
		this.companyId =  companyId ; 
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserDataClient [clientType=" + clientType + ", token=" + token + ", id=" + id + ", companyId="
				+ companyId + "]";
	}

	
}


