import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Events extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection conn = Connect.getConnect();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Events frame = new Events();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void printTableGoal( ) {
		try {
			String query = "SELECT (SELECT p.imię FROM piłkarz p INNER JOIN gol g ON g.piłkarz_id = p.piłkarz_id AND g.gol_id = gol.gol_id), (SELECT p.nazwisko FROM piłkarz p INNER JOIN gol g ON g.piłkarz_id = p.piłkarz_id AND g.gol_id = gol.gol_id), drużyna AS kto, minuta, mecz_id FROM gol;";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printTableCard( ) {
		try {
			String query = "SELECT (SELECT p.imię FROM piłkarz p INNER JOIN kartka k ON k.piłkarz_id = p.piłkarz_id AND k.kartka_id = kartka.kartka_id), (SELECT p.nazwisko FROM piłkarz p INNER JOIN kartka k ON k.piłkarz_id = p.piłkarz_id AND k.kartka_id = kartka.kartka_id), kolor, minuta, mecz_id FROM kartka;";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Events() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 640, 21);
		contentPane.add(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JMenuItem mntmDodajGola = new JMenuItem("Dodaj Gola");
		mntmDodajGola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				AddGoal addGoal = new AddGoal();
				addGoal.setVisible(true);
			}
		});
		
		JMenuItem mntmWywietlBramki = new JMenuItem("Wyświetl Bramki");
		mntmWywietlBramki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				printTableGoal();
			}
		});
		menu.add(mntmWywietlBramki);
		
		JMenuItem mntmWywietlKartki = new JMenuItem("Wyświetl Kartki");
		mntmWywietlKartki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				printTableCard();
			}
		});
		menu.add(mntmWywietlKartki);
		menu.add(mntmDodajGola);
		
		JMenuItem mntmDodajKartk = new JMenuItem("Dodaj Kartkę");
		mntmDodajKartk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				AddCard addCard = new AddCard();
				addCard.setVisible(true);
			}
		});
		menu.add(mntmDodajKartk);
		
		JMenuItem menuItem_1 = new JMenuItem("Strona Główna");
		menu.add(menuItem_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 618, 415);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
