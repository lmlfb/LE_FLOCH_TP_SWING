package my_package;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class saisir_etudiant {

	JFrame frame;
	private JTextField module;
	private JTextField nbre_jour;
	
	ArrayList<Integer> filiere_index = new ArrayList<Integer>();
	ArrayList<Integer> libelle_index = new ArrayList<Integer>();

	private JTextField lieuForm;
	private JFormattedTextField date_deb;

	
	private String sexe_str = "Homme";
	
	char[] LoisirArray = {'_', '_', '_', '_'};
	
	JComboBox salarie;

	/**
	 * Launch the application.
	 */
	public static void display() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					saisir_etudiant window = new saisir_etudiant();

					
					
					
					
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
	public saisir_etudiant() {
		initialize();
		
		ResultSet res1 = Model_class.getSalarieField(Model_class.getConnection());
		try {
			while (res1.next()) {
				 salarie.addItem(res1.getString("nom"));
				 filiere_index.add(Integer.valueOf(res1.getString("idSal")));
				 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		frame.setBounds(100, 100, 499, 347);
		
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent event) {
		        exit();
		    }
		});
		
		
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(date_deb.getText().compareTo("") != 0 && nbre_jour.getText().compareTo("") != 0  && module.getText().compareTo("") != 0   && lieuForm.getText().compareTo("") != 0) {
					
				//Connection connection, String date, String nbjour, String module, String idSal, String lieuForm
				
					int indexTableau = filiere_index.get(salarie.getSelectedIndex());
					
							
				Model_class.InsertSession(Model_class.getConnection(), date_deb.getText(), nbre_jour.getText(), module.getText(),  indexTableau, lieuForm.getText()); 
				
				frame.dispose();
				saisir_etudiant.display();
				
				}
				else {
					alert("Un des champs du formulaire n'est pas rempli, veuillez le compl�ter");

				}
			}
		});
		ok.setBounds(249, 248, 85, 21);
		frame.getContentPane().add(ok);
		
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				menu_window.afficher_menu();
			}
		});
		annuler.setBounds(124, 248, 85, 21);
		frame.getContentPane().add(annuler);
		
		
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("+## # ## ## ## ##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		mask.setValidCharacters("0123456789"); 
		
		MaskFormatter mask1 = null;
		try {
			mask1 = new MaskFormatter("####-##-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		mask1.setValidCharacters("0123456789"); 
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		panel.setBounds(0, 40, 333, 198);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		module = new JTextField();
		module.setBounds(116, 10, 96, 19);
		panel.add(module);
		module.setColumns(10);
		
		nbre_jour = new JTextField();
		nbre_jour.setBounds(116, 39, 96, 19);
		panel.add(nbre_jour);
		nbre_jour.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("module");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 13, 71, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("nombre jour");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 42, 96, 13);
		panel.add(lblNewLabel_1);
		date_deb = new JFormattedTextField(mask1);
		date_deb.setBounds(116, 65, 96, 19);
		panel.add(date_deb);
		
		JLabel lblNewLabel_2 = new JLabel("Date de d\u00E9but");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 68, 133, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Lieu de formation");
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 91, 133, 13);
		panel.add(lblNewLabel_3);
		

		lieuForm = new JTextField();
		lieuForm.setBounds(116, 88, 96, 19);
		panel.add(lieuForm);
		lieuForm.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Salarie");
		lblNewLabel_10.setBounds(10, 161, 45, 13);
		panel.add(lblNewLabel_10);
		lblNewLabel_10.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		
		salarie = new JComboBox();
		salarie.setBounds(116, 158, 133, 21);
		panel.add(salarie);
		
		JLabel lblContactEtudiant = new JLabel("Saisie Session");
		lblContactEtudiant.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContactEtudiant.setBounds(187, 10, 171, 20);
		frame.getContentPane().add(lblContactEtudiant);
		
	}
	
	public void alert(String str) {
		JFrame f=new JFrame();  
		JOptionPane.showMessageDialog(f,str);  
	}
	
	public String LoisirToStr(char[] LoisirArray) {
		
		String res = "";
		if(LoisirArray[0] == 'O'){
			res+="Sport,";
		}
		if(LoisirArray[1] == 'O'){
			res+="Musique,";
		}
		if(LoisirArray[2] == 'O'){
			res+="Voyage,";
		}
		if(LoisirArray[3] == 'O'){
			res+="Lecture.";
		}

		return res;
	}
	

	
	public void exit() {
		
		frame.dispose();
		menu_window.afficher_menu();
		
	}
}
