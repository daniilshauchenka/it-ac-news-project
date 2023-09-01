package by.htp.ex.dao.pool;

public final class DBParameters {
	
	private DBParameters() {
	}
	
	public static final String DB_URL = "jdbc:mysql://127.0.0.1/training_database_news?useSSL=false&";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "root";
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String COMMAND_PARAMETER_NAME = "command";
	public static final String LOCALE_PARAMETER_NAME = "locale";
	public static final int DB_POOL_SIZE = 3;
}
