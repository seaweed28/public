package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServletInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	@RequestMapping(value = "/test00", method = RequestMethod.GET)
	public String getSomething() {
		return "something";
	}

	@RequestMapping(value = "/test01")
	public String dispTest01() {
		return "テスト01だよ";
	}

	@RequestMapping(value = "/DBconnect")
	public String dispTest02() {

		String result = "結果：初期値";
		try {
			System.out.println("データベースに接続します...");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "＠＠＠", "＠＠＠");
			// jdbc:oracle:thin:@（ホスト名）:（ポート番号）:（接続するデータベースのSID)
			/*
						Connection conn = DriverManager.getConnection(url, user, password);
						Connection conn = DriverManager.getConnection("jdbc:oracle:oci8:@oracle.techscore","scott","tiger");
			*/


			if (conn != null) {
				System.out.println("/test02 で　データベースと接続しました");

				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery("select * from ZE" );
				result = "DB　検索正常終了";

				while ( rset.next() ) {
					System.out.println( rset.getString(1) + " : " + rset.getString(2) );
				}
				System.out.println( "DBからの取得終わり" );

				conn.close();
				System.out.println("データベースを閉じました。");
				result = "正常終了：DB接続終了";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = "SQL例外";
		} catch (Exception e) {
			e.printStackTrace();
			result = "その他例外";
		}
		return "DB結果：" + result;
	}
}
