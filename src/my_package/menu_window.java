package my_package;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.JPanel;

import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;

public class menu_window {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void afficher_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu_window window = new menu_window();
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
	public menu_window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBounds(new Rectangle(0, 0, 100, 100));
		frame.setBounds(100, 100, 533, 312);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent event) {
		        int retour = showConfirmDialog();
		        if(retour==0)
		         System.exit(0);
		       }
		    });
		
		JLabel lblNewLabel = new JLabel("  ");
		lblNewLabel.setIcon(new ImageIcon(menu_window.class.getResource("/my_package/login_image.png")));
		lblNewLabel.setBounds(22, 62, 216, 158);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Suivi des Sessions");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(175, 10, 180, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Ajout Session");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				saisir_etudiant.display();
				
			}
		});
		btnNewButton.setBounds(254, 58, 190, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Suppression session");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				delete_window.display();
			}
		});
		btnNewButton_1.setBounds(251, 106, 193, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Liste salari\u00E9s");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				//XTOM_liste_salarie myListe = new XTOM_liste_salarie();
				delete_filiere.display();
			}
		});
		btnNewButton_2.setBounds(254, 157, 190, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Quitter");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    if (showConfirmDialog() == JOptionPane.YES_OPTION) {
			        frame.dispose();
			      }
			}
		});
		btnNewButton_3.setBounds(254, 199, 190, 21);
		frame.getContentPane().add(btnNewButton_3);
	}
	
	static int showConfirmDialog(){
		  return JOptionPane.showConfirmDialog(
		       null,
		       "Voulez-vous vraiment quitter?",
	       "Quitter",
	       JOptionPane.YES_NO_OPTION);
		  
		
	 }
}
