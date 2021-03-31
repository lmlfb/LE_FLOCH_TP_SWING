package my_package;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jtable_eleves extends JFrame{
    public Jtable_eleves(){
        super();
        try {

            int nombre_colonne = 7;
            Connection conn = Model_class.getConnection();
            Statement stmNbFil = (Statement) conn.createStatement();
            ResultSet resNbFil = stmNbFil.executeQuery("SELECT count(*) FROM salarie");
            int nbSalarie=0;
            while (resNbFil.next()){
                nbSalarie = resNbFil.getInt("count(*)");
            }
            
            // 	 idSal	nom	prenoms	age	fonction	service	grade	

            Statement stmInfo = (Statement) conn.createStatement();
            String reqInfo = "SELECT * FROM etudiant";
            ResultSet resInfo = stmInfo.executeQuery(reqInfo);

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
 
                if(j==10){
                	col.setPreferredWidth(500);
                }
                if(j==11){
                	col.setPreferredWidth(300);
                }
                else {
                	col.setPreferredWidth(200);
                }
                
            }
            int hauteur_pixel = 30;
            int cache_pixel = 60;
            for (int j=0; j<nbSalarie;j++){
                table.setRowHeight(j,30);
                cache_pixel+=hauteur_pixel;
            }
            table.setFont(new Font("Serif", Font.PLAIN, 15));
            JScrollPane pane = new JScrollPane(table);
            pane.setBounds(0, 25, 1540, 113);
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
            btnNewButton.setBounds(1422, 148, 85, 21);
            panel.add(btnNewButton);
            f.setSize(1554, 219);
            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
            f.setLocationRelativeTo(null);
        }
        catch (SQLException e) {
            System.out.println("Erreur lors du chargement "+e.getMessage()) ;
        }
    }
}
