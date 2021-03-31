package my_package;
import java.awt.GridLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jtable_bac extends JFrame{
    public Jtable_bac(){
        super();
        try {
            Connection conn = Model_class.getConnection();
            Statement stmNbFil = (Statement) conn.createStatement();
            ResultSet resNbFil = stmNbFil.executeQuery("SELECT count(idBac) FROM bac");
            int nbBac=0;
            while (resNbFil.next()){
                nbBac = resNbFil.getInt("count(idBac)");
            }

            Statement stmInfo = (Statement) conn.createStatement();
            String reqInfo = "SELECT * FROM bac";
            ResultSet resInfo = stmInfo.executeQuery(reqInfo);

            String entetes[] = {"idBac", "libelle"};
            String donnees[][] = new String[nbBac][2];

            int i=0;
            while (resInfo.next()) {
                donnees[i][0]= resInfo.getString("idBac");
                donnees[i][1]= resInfo.getString("libelle");
                i++;
            }


            DefaultTableModel model = new DefaultTableModel(donnees, entetes);
            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);
            TableColumn col = null;
            for (int j = 0; j < 2; j++) {
                col = table.getColumnModel().getColumn(j);
                col.setPreferredWidth(150);
            }
            for (int j=0; j<nbBac;j++){
                table.setRowHeight(j,30);
            }
            table.setFont(new Font("Serif", Font.PLAIN, 20));
            JScrollPane pane = new JScrollPane(table);
            pane.setBounds(39, 49, 305, 260);
            JFrame f = new JFrame("Information des �tudiants");
            f.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.add(pane);
            f.getContentPane().add(panel);
            
            JButton btnNewButton = new JButton("retour");
            btnNewButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		f.dispose();
            		menu_window.afficher_menu();
            	}
            });
            btnNewButton.setBounds(253, 319, 93, 21);
            panel.add(btnNewButton);
            
            JLabel lblNewLabel = new JLabel("Liste des fili\u00E8res");
            lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            lblNewLabel.setBounds(122, 10, 162, 29);
            panel.add(lblNewLabel);

            f.setSize(400, 408);
            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
            f.setLocationRelativeTo(null);
        }
        catch (SQLException e) {
            System.out.println("Erreur lors du chargement "+e.getMessage()) ;
        }
    }
}
