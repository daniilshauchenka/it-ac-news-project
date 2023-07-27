package by.tr.conspect.bean;

public enum Role {
	ADMIN, USER, GUEST;
	public String getRole() {
		return name().toUpperCase();
	}
}
