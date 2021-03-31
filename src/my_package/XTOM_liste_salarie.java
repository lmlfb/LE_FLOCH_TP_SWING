package my_package;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XTOM_liste_salarie extends JFrame{
	
	int idSession;
    public XTOM_liste_salarie(int idSession){
    	
    	 super();
    	this.idSession = idSession;
    	
    	System.out.println(this.idSession);
    	
       
        try {

            int nombre_colonne = 7;
            Connection conn = Model_class.getConnection();
            
	        String query = "SELECT COUNT(salarie.idSal) AS TOT FROM salarie, Session WHERE Session.idSess = ? AND session.idSal = salarie.idsal"; 
	        PreparedStatement myStmt  = conn.prepareStatement(query); 
	        myStmt.setInt(1, this.idSession); 
	        ResultSet myRs = myStmt.executeQuery(); 
            int nbSalarie=0;
            while (myRs.next()){
                nbSalarie = myRs.getInt("TOT");
            }
            
            // 	 idSal	nom	prenoms	age	fonction	service	grade
            
	        String reqInfo = "SELECT salarie.idSal, salarie.nom, salarie.prenoms, salarie.age, salarie.fonction, salarie.service, salarie.grade FROM salarie, Session WHERE Session.idSess = ? AND session.idSal = salarie.idsal"; 
	        PreparedStatement stmInfo  = conn.prepareStatement(reqInfo); 
	        stmInfo.setInt(1, this.idSession); 
	        ResultSet resInfo = stmInfo.executeQuery(); 


            String entetes[] = {"Id Salarié", "nom","prenom", "age", "fonction", "service", "grade"};
            String donnees[][] = new String[nbSalarie][nombre_colonne];

            int i=0;
            while (resInfo.next()) {
                donnees[i][0]= resInfo.getString("idSal");
                donnees[i][1]= resInfo.getString("nom");
                donnees[i][2]= resInfo.getString("prenoms");
                donnees[i][3]= resInfo.getString("age");
                donnees[i][4]= resInfo.getString("fonction");
                donnees[i][5]= resInfo.getString("service");
                donnees[i][6]= resInfo.getString("grade");
                i++;
            }


            DefaultTableModel model = new DefaultTableModel(donnees, entetes);
            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);
            TableColumn col = null;
            for (int j = 0; j < nombre_colonne; j++) {
                col = table.getColumnModel().getColumn(j);
                col.setWidth(100);
                
            }
            int hauteur_pixel = 30;
            int cache_pixel = 60;
            for (int j=0; j<nbSalarie;j++){
                table.setRowHeight(j,30);
                cache_pixel+=hauteur_pixel;
            }
            table.setFont(new Font("Serif", Font.PLAIN, 15));
            JScrollPane pane = new JScrollPane(table);
            pane.setBounds(0, 25, 703, 113);
            JFrame f = new JFrame("Liste des etudiants");
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.add(pane);
            f.getContentPane().add(panel);
            
            JButton btnNewButton = new JButton("Retour");
            btnNewButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		f.dispose();
            		menu_window.afficher_menu();
            	}
            });
            btnNewButton.setBounds(101, 151, 85, 21);
            panel.add(btnNewButton);
            f.setSize(725, 219);
            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
            f.setLocationRelativeTo(null);
        }
        catch (SQLException e) {
            System.out.println("Erreur lors du chargement "+e.getMessage()) ;
        }
    }
}
