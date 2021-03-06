package com.example.demo.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectOracle01 {

	@RequestMapping(value = "/test_connect01")
	public String dispTest02() {
		try {
			System.out.println("データベースに接続します...");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "test", "seiten10");

			if (conn != null) {
				System.out.println("/test_connect01 で　データベースと接続しました");

				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery("select * from ZE" );

				while ( rset.next() ) {
					System.out.println( rset.getString(1) + "\t" + rset.getString(2) + "\t" + rset.getString(3) );
				}
				System.out.println( "DBからの取得終わり" );

				conn.close();
				System.out.println("データベースを閉じました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/test_connect01 DB接続中　だよ";
	}
}
