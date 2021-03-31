package my_package;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;




public class Model_class {
	
	
	public static Boolean Login(Connection connection, String login, String password) {
		 	int i = 0;
		    try   {	
		        String query = "SELECT * FROM User WHERE login = ? AND password = ?"; 
		        PreparedStatement myStmt  = connection.prepareStatement(query); 
		        myStmt.setString(1, login); 
		        myStmt.setString(2, password); 
		        ResultSet myRs = myStmt.executeQuery(); 
		        while (myRs.next()) { 
		            String Name = myRs.getString("login"); 
		            i++;
		        }  
		    }
		    catch ( SQLException e )
		    {
		      e.printStackTrace();
		    }   
			if(i==0) {
				return false;
				//return true;
			}
			else {
				return true;
			}
	}
	
	
	public static Connection getConnection()
	{
	  Connection connection = null;
	  boolean ok = false;
	  String URL_BDD = "jdbc:mysql://localhost:3306/xtom_bdd?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
	
	  try {
	    Class.forName("com.mysql.cj.jdbc.Driver") ;
	    ok = true;
	 }
	 catch ( ClassNotFoundException e ) {
	   System.out.println( "ERREUR chargement du pilote: pilote non trouv�" );
	   e.printStackTrace();
	 }
	
	 if (ok) {
	   try {
	     connection = DriverManager.getConnection(URL_BDD,"root","");
	   }
	   catch ( SQLException e ) {
	     System.out.println( "ERREUR de connexion � la base de donn�es: " + URL_BDD );
	     e.printStackTrace();
	   }
	 }  
	 
	  return connection;
	}
	
	public static void alert(String str) {
		JFrame f=new JFrame();  
		JOptionPane.showMessageDialog(f,str);  
	}

	
	public static void InsertSession(Connection connection, String date, String nbjour, String module, int idSal, String lieuForm) {
		
	 	int i = 0;
	 
	    try   {
	    	
	        String query = "INSERT INTO `session` VALUES (NULL, ?, ?, ?, ?, ?);"; 
	        PreparedStatement myStmt  = connection.prepareStatement(query); 
	        
	        myStmt.setString(1, date); 
	        myStmt.setString(2, nbjour);
	        myStmt.setString(3, module);
	        myStmt.setInt(4, idSal);
	        myStmt.setString(5, lieuForm);
	        myStmt.executeUpdate();
	        
	        alert("La session a bien �t� ins�r�e");
	        
	    }
	    catch ( SQLException e )
	    {
	      // TODO Auto-generated catch block
	    	alert("Erreur durant l'insertion, veuillez v�rifier votre saisi");
	      e.printStackTrace();
	    }
	}
	
	public static ResultSet getSessionField(Connection connection) {
		
		ResultSet rs = null;
		
	    try   {
	        Statement st = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
	        rs = st.executeQuery( "SELECT * FROM session" );
	        
	        
	    }
	    catch ( SQLException e )
	    {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    
	    return rs;

	}
	

	
	
	public static ResultSet getSalarieField(Connection connection) {
		
		ResultSet rs = null;
		
	    try   {
	        Statement st = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
	        rs = st.executeQuery( "SELECT * FROM salarie" );
	        
	        
	    }
	    catch ( SQLException e )
	    {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    
	    return rs;

	}
	
	
	
	
	
	//////////////////////////////////
	
	public static void InsertFiliere(Connection connection, String filiereAInserer) {
		
	 	int i = 0;
	 
	    try   {
	    	
	        String query = "INSERT INTO `filiere` (`idFil`, `nom`) VALUES (NULL, ?);"; 
	        PreparedStatement myStmt  = connection.prepareStatement(query); 
	        
	        myStmt.setString(1, filiereAInserer); 

	    
	        myStmt.executeUpdate();
	        
	        alert("La fili�re a bien �t� ins�r�e");
	        
	    }
	    catch ( SQLException e )
	    {
	      // TODO Auto-generated catch block
	    	alert("Erreur durant l'insertion, veuillez v�rifier votre saisi");
	      e.printStackTrace();
	    }
	}
	
	public static void InsertSpe(Connection connection, String SPeAInserer) {
		
	 	int i = 0;
	 
	    try   {
	    	
	        String query = "INSERT INTO `bac` (`idBac`, `libelle`) VALUES (NULL, ?);"; 
	        PreparedStatement myStmt  = connection.prepareStatement(query); 
	        
	        myStmt.setString(1, SPeAInserer); 

	    
	        myStmt.executeUpdate();
	        
	        alert("La fili�re a bien �t� ins�r�e");
	        
	    }
	    catch ( SQLException e )
	    {
	      // TODO Auto-generated catch block
	    	alert("Erreur durant l'insertion, veuillez v�rifier votre saisi");
	      e.printStackTrace();
	    }
	}
	
	public static void InsertStudent(Connection connection, String Nom, String Prenom, String Date_naiss, String Lieu_naiss, String sexe, String nation, String Rue, String Cp, String ville, String telephone, String mail, String niveau, Integer filiere, String loisir, Integer Bac) {
		
	 	int i = 0;
	 
	    try   {
	    	
	        String query = "INSERT INTO `etudiant` "
	        		+ "(`nom`, `prenom`, `dateNaiss`, `lieuNaiss`, `sexe`, `nationalite`, `rue`, `cp`, `ville`, `telephone`, `mail`, `niveau`, `loisir`, `idFil`, `idBac`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
	        PreparedStatement myStmt  = connection.prepareStatement(query); 
	        
	        myStmt.setString(1, Nom); 
	        myStmt.setString(2, Prenom); 
	        //myStmt.setString(3, "2021-12-31"); 
	        myStmt.setString(3, Date_naiss); 
	        myStmt.setString(4, Lieu_naiss); 
	        myStmt.setString(5, sexe); 
	        myStmt.setString(6, nation); 
	        myStmt.setString(7, Rue); 
	        myStmt.setString(8, Cp); 
	        myStmt.setString(9, ville); 
	        myStmt.setString(10, telephone); 
	        myStmt.setString(11, mail); 	
	        myStmt.setString(12, niveau); 
	        myStmt.setString(13, loisir); 
	        myStmt.setInt(14, filiere); 
	        myStmt.setInt(15, Bac); 
	        
	        myStmt.executeUpdate();
	        
	        alert("L'�tudiant a bien �t� ins�r�");
	        
	    }
	    catch ( SQLException e )
	    {
	      // TODO Auto-generated catch block
	    	alert("Erreur durant l'insertion, veuillez v�rifier votre saisi");
	      e.printStackTrace();
	    }
	}
	
	public static void supprimerEtudiant(Connection connection, String nomEtu) {
		
	 	int i = 0;
	 
	    try   {
	    	
	    	String query = "DELETE FROM `session` WHERE idSess = ?";       
	        PreparedStatement myStmt  = connection.prepareStatement(query); 
	        
	        myStmt.setString(1, nomEtu); 
	    
	        myStmt.executeUpdate();
	        
	        alert("La suppression a bien �t� effectu�e");
	        
	    }
	    catch ( SQLException e )
	    {
	      // TODO Auto-generated catch block
	    	alert("Erreur durant l'insertion, veuillez v�rifier votre saisi");
	      e.printStackTrace();
	    }
	}
	
	public static ResultSet ListerEtudiant(Connection connection) {
		
		   
		ResultSet rs = null;
		
	    try   {
	        Statement st = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
	        rs = st.executeQuery( "SELECT `nom`, `prenom`, `dateNaiss`, `lieuNaiss`, `sexe`, `nationalite`, `rue`, `cp`, `ville`, `telephone`, `mail`, `niveau` FROM `etudiant`" );
	        Vue_class vue_class = new Vue_class(rs);
	    }
	    catch ( SQLException e )
	    {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    
    return rs;

}
	
	public static ResultSet geFiliereField(Connection connection) {
		
		ResultSet rs = null;
		
	    try   {
	        Statement st = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
	        rs = st.executeQuery( "SELECT idFil, nom FROM filiere" );
	        
	        
	    }
	    catch ( SQLException e )
	    {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    
	    return rs;

}
	
	public static ResultSet getEtudiantField(Connection connection) {
		
		ResultSet rs = null;
		
	    try   {
	        Statement st = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
	        rs = st.executeQuery( "SELECT * FROM `Session`" );
	        
	        
	    }
	    catch ( SQLException e )
	    {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    
	    return rs;

}
	
	public static ResultSet geBacField(Connection connection) {
		
		ResultSet rs = null;
		
	    try   {
	        Statement st = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
	        rs = st.executeQuery( "SELECT idBac, libelle FROM bac" );
	        
	        
	    }
	    catch ( SQLException e )
	    {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    
	    return rs;

}
		

}


