package ibatis;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MyAppSqlConfig {
    private static SqlMapClient sqlMap;

    static {
        try {
            String resource = "SqlMapConfig.xml";
            //シングルトンとして利用
            if (sqlMap == null) {
                Reader reader = Resources.getResourceAsReader(resource);
                sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlMapClient getSqlMapInstance() {
        return sqlMap;
    }
}