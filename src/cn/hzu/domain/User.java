package cn.hzu.domain;

public class User {
	
	private String stuid;
	private String password;
	private String fid;
	private String did;
	private double dmoney;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public double getDmoney() {
		return dmoney;
	}

	public void setDmoney(double dmoney) {
		this.dmoney = dmoney;
	}

	public User() {
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
