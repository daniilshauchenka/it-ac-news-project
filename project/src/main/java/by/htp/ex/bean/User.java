package by.htp.ex.bean;

import java.util.List;

public class User {
	
	private int id;
	private String login;
	private String password;
	private String email;	
	private String name;
	private String surname;
	private Role role;

	private List<News> favoriteNewsList;
	private List<News> viewedNewsList;
// добавить статус? как это лушче делать, по аналогу с ролями или просто String statusName

	private boolean isBanned;
	
	
	public User(){}
	
	public User(int id, String login, Role role){
		this.login = login;
		this.id = id;
		this.role = role;
	}
	
	public User(int id, String login, String email, String name, String surname, Role role){
		this.login = login;
		this.id = id;
		this.role = role;
		this.email = email;
		this.name = name;
		this.surname = surname;
	}
	
	public User(int id, String login, String password){
		this.login = login;
		this.password = password;
		this.id = id;
	}
	public User( String login, String email, String password){
		this.login = login;
		this.password = password;
		this.email = email;
		
	}
	
	
	public User(int id, String login, String password, String email, String name, String surname, Role role){
		this.id = id;
		this.login = login;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.role = role;
	}
	
	public User(String login, String password, String email, String name, String surname, Role role){
		this.login = login;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.role = role;
	}
	public User(String login,String email, String password,  String name, String surname){
		this.login = login;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
	
	}
	

	


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}
	public String getRoleName() {
		return role.getRole();
	}

	public void setRole(Role role) {
		this.role = role;
	}



	public List<News> getFavoriteNewsList() {
		return favoriteNewsList;
	}

	public void setFavoriteNewsList(List<News> favoriteNewsList) {
		this.favoriteNewsList = favoriteNewsList;
	}

	public List<News> getViewedNewsList() {
		return viewedNewsList;
	}

	public void setViewedNewsList(List<News> viewedNewsList) {
		this.viewedNewsList = viewedNewsList;
	}

	

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", email=" + email + ", name=" + name
				+ ", surname=" + surname + ", role=" + role + ", favoriteNewsList=" + favoriteNewsList + ", viewedNewsList=" + viewedNewsList + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		if (this==o) {
			return true;
		}
		if (o==null) {
			return false;
		}
		if(getClass() != o.getClass()) {
			return false;
		}
		User other = (User)o;
		boolean idEquals = this.id == other.id;
		boolean loginEquals = (this.login==null && other.login==null) 
				|| (this.login !=null && this.login.equals(other.login));
		boolean passwordEquals = (this.password==null && other.password==null) 
				|| (this.password !=null && this.password.equals(other.password));
		boolean emailEquals = (this.email==null && other.email==null) 
				|| (this.email !=null && this.email.equals(other.email));
		boolean nameEquals = (this.name==null && other.name==null) 
				|| (this.name !=null && this.name.equals(other.name));
		boolean surnameEquals = (this.surname==null && other.surname==null) 
				|| (this.surname !=null && this.surname.equals(other.surname));
		boolean roleEquals = (this.role==null && other.role==null) 
				|| (this.role !=null && this.role.equals(other.role));
		boolean favoriteNewsListEquals = (this.favoriteNewsList==null && other.favoriteNewsList==null) 
				|| (this.favoriteNewsList !=null && this.favoriteNewsList.equals(other.favoriteNewsList));
		boolean viewedNewsListEquals = (this.viewedNewsList==null && other.viewedNewsList==null) 
				|| (this.viewedNewsList !=null && this.viewedNewsList.equals(other.viewedNewsList));
		
		return idEquals && loginEquals && passwordEquals &&
				emailEquals && nameEquals && surnameEquals && roleEquals
				&& favoriteNewsListEquals && viewedNewsListEquals;
		
	}
	
	@Override
	public final int hashCode() {
	    int result = 17;
	    Integer id = this.id;
	    if (id != null) {
	        result = 31 * result + id.hashCode();
	    }
	    if (login != null) {
	        result = 31 * result + login.hashCode();
	    }
	    if (password !=null) {
	        result = 31 * result + password.hashCode();
	    }
	    if (email !=null) {
	        result = 31 * result + email.hashCode();
	    }
	    if (name !=null) {
	        result = 31 * result + name.hashCode();
	    }
	    if (surname !=null) {
	        result = 31 * result + surname.hashCode();
	    }
	    if (role !=null) {
	        result = 31 * result + role.hashCode();
	    }
	    if (favoriteNewsList !=null) {
	        result = 31 * result + favoriteNewsList.hashCode();
	    }
	    if (viewedNewsList !=null) {
	        result = 31 * result + viewedNewsList.hashCode();
	    }
	    return result;
	}
	
}

