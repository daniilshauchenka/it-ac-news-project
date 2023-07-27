package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;

public class NewsDAO implements INewsDAO {
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private final static String SQL_GETTING_LASTEST_NEWS = "SELECT * FROM news ORDER BY date_created DESC LIMIT ?";

	@Override
	public List<News> getLatestsList(int count) throws DaoException {
		List<News> result = new ArrayList<News>();

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_GETTING_LASTEST_NEWS)){
			preparedStatement.setInt(1, count);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				result.add(getFullNewsFromResultSet(resultSet));
			}
		
		}catch (SQLException | ConnectionPoolException e){
			throw new DaoException(e);
		}
		
		
//		result.add(new News(1, "Объявлена Всеобщая Кальянизация! Все представители мужского пола должны незамедлительно явиться в районный лаунж и как следует накумариться.", 
//				"Город, 31 мая 2023 г. - В смелом решении, которое точно вызовет недоумение у многих, городские власти объявили о запуске проекта \"Всеобщая кальянизация\". Согласно этому объявлению, каждый представитель мужского пола должен незамедлительно отправиться в свой районный лаунж, чтобы полностью погрузиться в мир ароматного дыма и беззаботного кумара.", 
//				"	qw123",
//				"05/31/23", 
//				"src/main/webapp/images/kalik.jpg"));
//		
//		result.add(new News(2, "Ученые из Саратовской области открыли банку пива", 
//				"Саратовская область, 31 мая 2023 г. - Группа исследователей из Саратовской области сделала революционное открытие, раскрыв тайну, хранящуюся внутри банки пива. После многолетних исследований и научных экспериментов ученые наконец-то разгадали загадку, что находится внутри этого популярного напитка.", 
//				"Команда, состоящая из биохимиков, пищевых технологов и специалистов по анализу, долгие годы изучала состав и структуру пивной банки. Их целью было выяснить, какие компоненты и процессы делают это пенящееся напиток таким популярным среди миллионов людей.\r\n"
//						+ "Исследователи использовали самые современные технологии и аналитические методы, чтобы проникнуть в секреты банки пива. Они проводили молекулярные исследования, анализируя состав пива на уровне атомов и молекул. Также были проведены специальные эксперименты, чтобы выяснить, какие процессы происходят внутри банки при длительном хранении и транспортировке.\r\n"
//						+ "К основным результатам исследования относится выявление более 200 различных химических соединений, которые составляют пиво. Оказалось, что их сочетание и взаимодействие создают уникальный вкус и аромат напитка. Ученые также выяснили, что пиво содержит важные питательные вещества, такие как витамины, минералы и антиоксиданты, которые могут оказывать положительное влияние на здоровье человека.\r\n"
//						+ "Следующим шагом для исследователей будет дальнейшее изучение процесса производства пива и его влияние на окружающую среду. Они надеются использовать свои результаты, чтобы разработать более эффективные и экологически чистые методы производства этого популярного напитка.\r\n"
//						+ "Открытие этих саратовских ученых является значимым прорывом в понимании пивной промышленности и может привести к новым технологическим разработкам в этой области. Это открывает двери для улучшения качества пива и создания новых вкусовых комбинаций, радуя любителей этого напитка по всему миру.\r\n"
//						+ "Саратовская область продолжает удивлять нас своими научными достижениями, и эта последняя находка подтверждает ее репутацию как центра инноваций и научных открытий. Пиво всегда было важной частью культуры и наследия региона, и теперь благодаря работе ученых мы можем лучше понять его секреты и насладиться этим популярным напитком с еще большим удовольствием.", 
//						"05/31/23", 
//				"https://cs10.pikabu.ru/post_img/big/2019/12/11/9/1576078123142810661.jpg"));
		return result;
	}
	

	@Override
	public List<News> getList() throws DaoException {
		List<News> result = new ArrayList<News>();
		result.add(new News(1, "Объявлена Всеобщая Кальянизация! Все представители мужского пола должны незамедлительно явиться в районный лаунж и как следует накумариться.", 
				"Город, 31 мая 2023 г. - В смелом решении, которое точно вызовет недоумение у многих, городские власти объявили о запуске проекта \"Всеобщая кальянизация\". Согласно этому объявлению, каждый представитель мужского пола должен незамедлительно отправиться в свой районный лаунж, чтобы полностью погрузиться в мир ароматного дыма и беззаботного кумара.", 
				"	qw123",
				"05/31/23", 
				"src/main/webapp/images/kalik.jpg"));
		
		result.add(new News(2, "Ученые из Саратовской области открыли банку пива", 
				"Саратовская область, 31 мая 2023 г. - Группа исследователей из Саратовской области сделала революционное открытие, раскрыв тайну, хранящуюся внутри банки пива. После многолетних исследований и научных экспериментов ученые наконец-то разгадали загадку, что находится внутри этого популярного напитка.", 
				"Команда, состоящая из биохимиков, пищевых технологов и специалистов по анализу, долгие годы изучала состав и структуру пивной банки. Их целью было выяснить, какие компоненты и процессы делают это пенящееся напиток таким популярным среди миллионов людей.\r\n"
						+ "Исследователи использовали самые современные технологии и аналитические методы, чтобы проникнуть в секреты банки пива. Они проводили молекулярные исследования, анализируя состав пива на уровне атомов и молекул. Также были проведены специальные эксперименты, чтобы выяснить, какие процессы происходят внутри банки при длительном хранении и транспортировке.\r\n"
						+ "К основным результатам исследования относится выявление более 200 различных химических соединений, которые составляют пиво. Оказалось, что их сочетание и взаимодействие создают уникальный вкус и аромат напитка. Ученые также выяснили, что пиво содержит важные питательные вещества, такие как витамины, минералы и антиоксиданты, которые могут оказывать положительное влияние на здоровье человека.\r\n"
						+ "Следующим шагом для исследователей будет дальнейшее изучение процесса производства пива и его влияние на окружающую среду. Они надеются использовать свои результаты, чтобы разработать более эффективные и экологически чистые методы производства этого популярного напитка.\r\n"
						+ "Открытие этих саратовских ученых является значимым прорывом в понимании пивной промышленности и может привести к новым технологическим разработкам в этой области. Это открывает двери для улучшения качества пива и создания новых вкусовых комбинаций, радуя любителей этого напитка по всему миру.\r\n"
						+ "Саратовская область продолжает удивлять нас своими научными достижениями, и эта последняя находка подтверждает ее репутацию как центра инноваций и научных открытий. Пиво всегда было важной частью культуры и наследия региона, и теперь благодаря работе ученых мы можем лучше понять его секреты и насладиться этим популярным напитком с еще большим удовольствием.", 
						"05/31/23", 
				"https://cs10.pikabu.ru/post_img/big/2019/12/11/9/1576078123142810661.jpg"));

		return result;
		
		

		
		
	}
	
	
	private News getFullNewsFromResultSet(ResultSet resultSet) throws SQLException {
		News news = new News();
		news.setIdNews(resultSet.getInt("id"));
		news.setBriefNews(resultSet.getString("brief"));
		news.setContent(resultSet.getString("content"));
		news.setNewsDate(resultSet.getDate("date_created").toString());
		news.setTitle(resultSet.getString("title"));
		news.setImagePath(resultSet.getString("image_path"));
	
		return news;
	}
	
	private News getBriefNewsFromResultSet(ResultSet resultSet) throws SQLException {
		News news = new News();
		news.setIdNews(resultSet.getInt("id"));
		news.setBriefNews(resultSet.getString("brief"));
		news.setNewsDate(resultSet.getDate("date_created").toString());
		news.setTitle(resultSet.getString("title"));
			System.out.println(news);
		return news;
	}
	
	
	private final static String SQL_GETTING_NEWS = "SELECT * FROM news WHERE id = ?";

	@Override
	public News fetchById(int id) throws DaoException {
		News news = null;
		try(Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_GETTING_NEWS)){
				statement.setInt(1, id);
				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					news = getFullNewsFromResultSet(resultSet);
				}
			}
			catch (SQLException |ConnectionPoolException e){
				throw new DaoException(e);
			}
	
			return news;
	}


	@Override
	public int addNews(News news) throws DaoException {
		
		return 0;
	}

	@Override
	public void updateNews(News news) throws DaoException {
		// TODO Auto-generated method stub
	}
	


	@Override
	public void deleteNews(String[] idNews) throws DaoException {
		// TODO Auto-generated method stub

	}

}
