package logic;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.ibatis.sqlmap.client.SqlMapClient;

import ibatis.MyAppSqlConfig;
import ibatis.dto.Library;
import ibatis.dto.SearchLibrary;
import ibatis.dto.Stock;

public class DBOperationLogic {

	static SqlMapClient sqlMap = MyAppSqlConfig.getSqlMapInstance();

	/**
	 * 初期表示、検索時にDBより書籍リストを取得します
	 * @param mode 検索対象カラム(nullの場合はすべて取得)
	 * @param word 検索文字
	 * @return 書籍リスト
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static List<Library> serchLiblary(String mode, String word) throws SQLException{

		List<String> searchWord = null;

		if(word != null) {
			searchWord = Arrays.asList(word.split("[\\s]+"));

			if(word.equals("")) {
				searchWord = null;
			}
		}

		SearchLibrary search;

		if(mode != null) {
			switch (mode) {
			case "title":
				search = new SearchLibrary(searchWord, null, null);
				break;
			case "publisher":
				search = new SearchLibrary(null, searchWord, null);
				break;
			case "author":
				search = new SearchLibrary(null, null, searchWord);
				break;
			default:
				search = new SearchLibrary(null, null, null);
				break;
			}
		} else {
			search = new SearchLibrary(null, null, null);
		}

		List<Library> list = (List<Library>)sqlMap.queryForList("searchLibrary", search);

		return list;

	}


	/**
	 * 書籍情報、巻数を登録します
	 * @param addData 書籍情報
	 * @param volume 巻数 ([数字]-[数字]なら複数登録)
	 * @throws SQLException
	 */
	public static void addLibrary(Library addData ,String volume) throws SQLException {

		Library data = (Library)sqlMap.queryForObject("getLibrary", addData.getTitle());

		if(data.getTitle() == null) {
			sqlMap.insert("insertLibrary", addData);
		}

		addStock(addData.getTitle() ,volume);

	}

	/**
	 * 巻数を登録します
	 * @param title タイトル
	 * @param volume　巻数 ([数字]-[数字]なら複数登録)
	 * @throws SQLException
	 */
	private static void addStock(String title ,String volume) throws SQLException {

		if(Pattern.compile("\\-").matcher(volume).find()) {
			String[] volumes = volume.split("-");
			Integer startNo = Integer.parseInt(volumes[0]);
			Integer endNo = Integer.parseInt(volumes[1]);

			for(int i=startNo;i<endNo;i++) {
				Stock insertData = new Stock(title,String.valueOf(i));
				sqlMap.insert("insertStock", insertData);
			}

		} else {
			Stock insertData = new Stock(title,volume);
			sqlMap.insert("insertStock", insertData);

		}
	}

}
