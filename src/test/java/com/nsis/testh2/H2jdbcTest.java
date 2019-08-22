package com.nsis.testh2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.h2.tools.DeleteDbFiles;
import org.junit.Test;

public class H2jdbcTest {
	@Test
	public void insertIntoBaseH2() {
		try {
		// On efface la base de données
		DeleteDbFiles.execute("~", "test", true);
		// Connexion
		Class.forName("org.h2.Driver");
		Connection connection = DriverManager.getConnection("jdbc:h2:~/test");
		Statement statement = connection.createStatement();
		// Création de la table
		statement.execute("create table test(id int primary key, name varchar(255))");
		// Insertion d'un ligne
		statement.execute("insert into test values(1, 'Jean-Luc')");
		// Lecture de la ligne
		ResultSet rs;
		rs = statement.executeQuery("select * from test");
		while (rs.next()) {
			// Affichage console
			//System.out.println(rs.getString("name"));
			assertEquals("Jean-Luc", rs.getString("name")); 
		}
		// Fermeture statement
		statement.close();
		// Fermeture base de données
		connection.close(); 
		} catch (Exception e) {
			fail(e.getMessage()); 
		}
	}
}
