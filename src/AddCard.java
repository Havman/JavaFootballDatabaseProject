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

public class AddCard extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	private JComboBox comboBox_1;

	
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
	
	public void fillComboBox_1() {
		try {
			String query = "SELECT klub_id, nazwa FROM klub ORDER BY klub_id;"; 
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			String str = "";
			while( rs.next() ) {
				str += rs.getString("nazwa");
				comboBox_1.addItem(str);
				str = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public AddCard() {
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
		
		JMenuItem mntmListaPikarzy = new JMenuItem("Lista Meczy");
		mntmListaPikarzy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				Games games = new Games();
				games.setVisible(true);
			}
		});
		mnMenu.add(mntmListaPikarzy);
		mnMenu.add(mntmStronaGwna);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNazwisko = new JLabel("Gość:");
		lblNazwisko.setHorizontalAlignment(SwingConstants.CENTER);
		lblNazwisko.setBounds(154, 132, 124, 22);
		contentPane.add(lblNazwisko);
		
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
		
		JButton btnDodajPikarza = new JButton("Dodaj Mecz");
		btnDodajPikarza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "INSERT INTO mecz ( gospodarze_id, goście_id, gospodarze_gol, goście_gol, czerwone_kartki, żółte_kartki ) VALUES (?, ?, 0, 0, 0, 0);";
					PreparedStatement ps = conn.prepareStatement(query);
					int home = Integer.parseInt(textField.getText());
					int away = Integer.parseInt(textField_1.getText());
					if ( home != away ) {
						ps.setInt(1, home);
						ps.setInt(2, away);
						ps.execute();
						
						JOptionPane.showMessageDialog(null, "Dodano Mecz!");
						ps.close();
						
						contentPane.setVisible(false);
						dispose();
						Games games = new Games();
						games.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Wybierz 2 różne drużyny!");
					}

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDodajPikarza.setBounds(235, 295, 131, 25);
		contentPane.add(btnDodajPikarza);
		
		JLabel lblImi = new JLabel("Gospodarz: ");
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
						textField.setText(rs.getString("klub_id"));
					}
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		comboBox.setBounds(333, 96, 190, 24);
		contentPane.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT klub_id FROM klub WHERE nazwa = ?;";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, (String)(comboBox_1.getSelectedItem()));
					ResultSet rs = ps.executeQuery();
					while( rs.next() ) {
						textField_1.setText(rs.getString("klub_id"));
					}
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		comboBox_1.setBounds(333, 161, 190, 24);
		contentPane.add(comboBox_1);
		fillComboBox();
		fillComboBox_1();
	}
}
