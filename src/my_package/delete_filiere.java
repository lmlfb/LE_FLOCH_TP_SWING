package my_package;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class delete_filiere {

	private JFrame frame;

	JComboBox liste_all;
	private JLabel lblNewLabel;

	
	public static void display() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					delete_filiere window = new delete_filiere();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public delete_filiere() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 260, 182);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		
		liste_all = new JComboBox();
		liste_all.setBounds(25, 56, 211, 21);
		frame.getContentPane().add(liste_all);
		
		update(liste_all);
		
		JButton btnNewButton = new JButton("Suite");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					
										
					XTOM_liste_salarie myListe = new XTOM_liste_salarie(Integer.valueOf(liste_all.getSelectedItem().toString()));
					frame.dispose();
					//delete_filiere.display();
					
				

			 
			}
		});
		btnNewButton.setBounds(138, 101, 98, 21);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel("Selectionner session");
		lblNewLabel.setBounds(33, 21, 176, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        		menu_window.afficher_menu();
			}
		});
		btnNewButton_1.setBounds(29, 101, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
	}
	
	public static void update(JComboBox jComboBox) {
		ResultSet res1 = Model_class.getSessionField(Model_class.getConnection());
		try {
			while (res1.next()) {
				jComboBox.addItem(res1.getString("idSess"));
				 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static int showConfirmDialog(){
		  return JOptionPane.showConfirmDialog(
		       null,
		       "Voulez-vous vraiment supprimer ?",
	       "Annuler",
	       JOptionPane.YES_NO_OPTION);
	 }
}
