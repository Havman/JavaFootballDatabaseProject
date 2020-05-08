import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddPlayer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox comboBox;

	
	Connection conn = Connect.getConnect();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPlayer frame = new AddPlayer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void fillComboBox() {
		try {
			String query = "SELECT klub_id, nazwa FROM klub ORDER BY klub_id;"; 
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			String str = "";
			while( rs.next() ) {
				str += rs.getString("nazwa");
				comboBox.addItem(str);
				str = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public AddPlayer() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmStronaGwna = new JMenuItem("Strona Główna");
		mntmStronaGwna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				MainWindow.main(null);
			}
		});
		
		JMenuItem mntmListaPikarzy = new JMenuItem("Lista Piłkarzy");
		mntmListaPikarzy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				Players players = new Players();
				players.setVisible(true);
			}
		});
		mnMenu.add(mntmListaPikarzy);
		mnMenu.add(mntmStronaGwna);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNazwisko = new JLabel("Nazwisko:");
		lblNazwisko.setHorizontalAlignment(SwingConstants.CENTER);
		lblNazwisko.setBounds(154, 132, 124, 22);
		contentPane.add(lblNazwisko);
		
		JLabel lblKlubzListy = new JLabel("Klub_id: (z listy)");
		lblKlubzListy.setHorizontalAlignment(SwingConstants.CENTER);
		lblKlubzListy.setBounds(154, 197, 124, 22);
		contentPane.add(lblKlubzListy);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		textField.setColumns(10);
		textField.setBounds(154, 98, 124, 22);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(154, 163, 124, 22);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(154, 224, 124, 22);
		contentPane.add(textField_2);
		
		JButton btnDodajPikarza = new JButton("Dodaj Piłkarza");
		btnDodajPikarza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query_1 = "SELECT imię, nazwisko FROM piłkarz;";
					String name = textField.getText();
					String surname = textField_1.getText();
					PreparedStatement ps_1 = conn.prepareStatement(query_1);
					ResultSet rs_1 = ps_1.executeQuery();
					boolean flag = true;
					while( rs_1.next() ) {
						if ( name.equals(rs_1.getString("imię")) || surname.equals(rs_1.getString("nazwisko")) || name.length() < 3 || surname.length() < 3 )
							flag = false;
					}
					if ( flag ) {
						String query = "INSERT INTO piłkarz (imię, nazwisko, czerwone_kartki, żółte_kartki, klub_id, gole) VALUES (?, ?, 0, 0, ?, 0);";
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setString(1, name);
						ps.setString(2, surname);
						ps.setInt(3, Integer.parseInt(textField_2.getText()));
						ps.execute();
						JOptionPane.showMessageDialog(null, "Dodano Piłkarza!");
						
						ps.close();
						
						contentPane.setVisible(false);
						dispose();
						Players players = new Players();
						players.setVisible(true);						
					}
					else {
						JOptionPane.showMessageDialog(null, "Jest już taki piłkarz lub podano zbyt krótkie dane!");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDodajPikarza.setBounds(235, 295, 131, 25);
		contentPane.add(btnDodajPikarza);
		
		JLabel lblImi = new JLabel("Imię: ");
		lblImi.setHorizontalAlignment(SwingConstants.CENTER);
		lblImi.setBounds(154, 65, 124, 22);
		contentPane.add(lblImi);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT klub_id FROM klub WHERE nazwa = ?;";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, (String)(comboBox.getSelectedItem()));
					ResultSet rs = ps.executeQuery();
					while( rs.next() ) {
						textField_2.setText(rs.getString("klub_id"));
					}
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		comboBox.setBounds(321, 222, 190, 24);
		contentPane.add(comboBox);
		fillComboBox();
	}
}
