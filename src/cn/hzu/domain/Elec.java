package cn.hzu.domain;

public class Elec {

	private String fid;
	private String did;
	private String year;
	private String month;
	private String electric;
	private double fare; 
	
	public Elec() {
	}
	
	public Elec(String fid, String did, String year, String month, String electric, double fare) {
		super();
		this.fid = fid;
		this.did = did;
		this.year = year;
		this.month = month;
		this.electric = electric;
		this.fare = fare;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getElectric() {
		return electric;
	}

	public void setElectric(String electric) {
		this.electric = electric;
	}

}
