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

public class AddGoal extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;

	
	Connection conn = Connect.getConnect();
	private JTextField textField_2;
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
			String query = "SELECT piłkarz_id, imię, nazwisko FROM piłkarz ORDER BY piłkarz_id;"; 
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			String str = "";
			while( rs.next() ) {
				str += rs.getString("imię");
				str += "   ";
				str += rs.getString("nazwisko");
				comboBox.addItem(str);
				str = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fillComboBox_1() {
		try {
			String query = "SELECT mecz_id, (SELECT k.nazwa AS gospodarz FROM klub k INNER JOIN mecz me ON k.klub_id = me.gospodarze_id AND mecz.mecz_id = me.mecz_id), (SELECT k.nazwa AS gość FROM klub k INNER JOIN mecz me ON k.klub_id = me.goście_id AND mecz.mecz_id = me.mecz_id) FROM mecz ORDER BY mecz_id;"; 
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			String str = "";
			while( rs.next() ) {
				str += rs.getString("gospodarz");
				str += "   vs  ";
				str += rs.getString("gość");
				comboBox_1.addItem(str);
				str = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public AddGoal() {
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
		
		JLabel lblNazwisko = new JLabel("Mecz:");
		lblNazwisko.setHorizontalAlignment(SwingConstants.CENTER);
		lblNazwisko.setBounds(228, 116, 124, 36);
		contentPane.add(lblNazwisko);
		
		JButton btnDodajPikarza = new JButton("Dodaj Gola");
		btnDodajPikarza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					System.out.print(Integer.parseInt(textField_2.getText()));
					if ( Integer.parseInt(textField_2.getText()) > 90 || Integer.parseInt(textField_2.getText()) <= 0) {
						
						JOptionPane.showMessageDialog(null, "Dodano Gola!");
					
						
						contentPane.setVisible(false);
						dispose();
						Events events = new Events();
						events.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Podaj liczbę od 1 do 90!");
					}

					
				}catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Nie podano liczby!");
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDodajPikarza.setBounds(231, 340, 131, 25);
		contentPane.add(btnDodajPikarza);
		
		JLabel lblImi = new JLabel("Strzelec: ");
		lblImi.setHorizontalAlignment(SwingConstants.CENTER);
		lblImi.setBounds(228, 64, 124, 22);
		contentPane.add(lblImi);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				try {
//					String query = "SELECT mecz_id FROM mecz WHERE;";
//					PreparedStatement ps = conn.prepareStatement(query);
//					ps.setString(1, (String)(comboBox.getSelectedItem()));
//					ResultSet rs = ps.executeQuery();
//					while( rs.next() ) {
//						textField.setText(rs.getString("klub_id"));
//					}
//					ps.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
		});
		comboBox.setBounds(203, 94, 190, 24);
		contentPane.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				try {
//					String query = "SELECT klub_id FROM klub WHERE nazwa = ?;";
//					PreparedStatement ps = conn.prepareStatement(query);
//					ps.setString(1, (String)(comboBox_1.getSelectedItem()));
//					ResultSet rs = ps.executeQuery();
//					while( rs.next() ) {
//						textField_1.setText(rs.getString("klub_id"));
//					}
//					ps.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
		});
		comboBox_1.setBounds(203, 146, 190, 24);
		contentPane.add(comboBox_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(228, 253, 124, 22);
		contentPane.add(textField_2);
		
		JLabel lblMinuta = new JLabel("Minuta:");
		lblMinuta.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinuta.setBounds(228, 234, 124, 22);
		contentPane.add(lblMinuta);
		
		JLabel lblDruyna = new JLabel("Drużyna:");
		lblDruyna.setHorizontalAlignment(SwingConstants.CENTER);
		lblDruyna.setBounds(228, 164, 124, 40);
		contentPane.add(lblDruyna);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(203, 198, 190, 24);
		comboBox_2.addItem(new String("Gospodarz"));
		comboBox_2.addItem(new String("Gość"));
		contentPane.add(comboBox_2);
		
		fillComboBox();
		fillComboBox_1();
	}
}
