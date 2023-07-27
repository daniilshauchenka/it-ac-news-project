package by.tr.conspect.bean;

import java.util.List;

/**
 * @author k0z9vk1n
 *
 */
public class User {
	private int id;
	private String login;
	private String password;
	private String email;	
	private String name;
	private String surname;
	private Role role;
	private List<News> createdNewsList;
	private List<News> favoriteNewsList;
	private List<News> viewedNewsList;
	
	
//	private UserInfo userInfo = null;

	
	public User(){}
	
	public User(int id, String login, String password){
		this.login = login;
		this.password = password;
		this.id = id;
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
	public User(String login, String password, String email, String name, String surname){
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

	public void setRole(Role role) {
		this.role = role;
	}

	public List<News> getCreatedNewsList() {
		return createdNewsList;
	}

	public void setCreatedNewsList(List<News> createdNewsList) {
		this.createdNewsList = createdNewsList;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", email=" + email + ", name=" + name
				+ ", surname=" + surname + ", role=" + role + ", createdNewsList=" + createdNewsList
				+ ", favoriteNewsList=" + favoriteNewsList + ", viewedNewsList=" + viewedNewsList + "]";
	}
	
	
}
