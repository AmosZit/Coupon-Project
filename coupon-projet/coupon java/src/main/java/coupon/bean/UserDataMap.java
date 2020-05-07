package coupon.bean;

import coupon.enums.ClientType;

public class UserDataMap {
	
	private long id;
    private Long companyId;
    private ClientType type;

    // constructor

    public UserDataMap(long id, Long companyId) {
        this();
        this.id = id;
        this.companyId = companyId;
    }

    public UserDataMap() {
        super();
    }

    public UserDataMap(long id, Long companyId, ClientType type) {
        this.id = id;
        this.companyId = companyId;
        this.type = type;
    }

    // getter & setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }
}
