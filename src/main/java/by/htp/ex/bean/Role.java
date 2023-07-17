package by.htp.ex.bean;

public enum Role {

	ADMIN, USER, GUEST;

	public String getRole() {
		return name().toUpperCase();
	}
	public String getRoleByName(String name) {
		String role;
		try {
			role = Role.valueOf(name).getRole();
		}catch (Exception ex) {
			role = "guest";
		}

		return role.toUpperCase();
	}
}
