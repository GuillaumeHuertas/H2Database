package com.nsis.testh2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.h2.tools.DeleteDbFiles;

public class ConnectH2 {
	public static void main(String[] args) throws Exception {
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
			System.out.println(rs.getString("name"));
		}
		// Fermeture statement 
		statement.close();
		// Fermeture base de données
		connection.close();
	}
}
