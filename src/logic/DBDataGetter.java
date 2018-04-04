package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import data.UserData;

public class DBDataGetter {

	private static Connection connection;

	public DBDataGetter() throws NamingException, SQLException {

		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/myapp");

		connection = ds.getConnection();

	}

	/**
	 * すべてのユーザー情報を取得します
	 * @return ユーザーデータ
	 * @throws SQLException
	 */
	public List<UserData> getALLUserData() throws SQLException {

		String sql = "SELECT * FROM pelibrary.userdata;";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet queryResult = statement.executeQuery();

		List<UserData> userDataList = new ArrayList<UserData>();

		while(queryResult.next()) {
			userDataList.add(new UserData(queryResult.getString("id"), queryResult.getString("password")));

		}

		return userDataList;

	}

	/**
	 * IDよりユーザー情報を取得します
	 * @return ユーザーデータ
	 * @throws SQLException
	 */
	public UserData getUserData(String id) throws SQLException {

		String sql = "SELECT * FROM pelibrary.userdata WHERE id = '" + id + "';";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet queryResult = statement.executeQuery();

		if(queryResult.next()) {
			return new UserData(queryResult.getString("id"), queryResult.getString("password"));
		}

		return new UserData(null, null);

	}

}
