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

public class Games extends JFrame {

	private JPanel contentPane;
	private JTable table;

	Connection conn = Connect.getConnect();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Games frame = new Games();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void printTable( ) {
		try {
			String query = "SELECT (SELECT k.nazwa AS gospodarz FROM klub k INNER JOIN mecz me ON k.klub_id = me.gospodarze_id AND mecz.mecz_id = me.mecz_id), (SELECT k.nazwa AS gość FROM klub k INNER JOIN mecz me ON k.klub_id = me.goście_id AND mecz.mecz_id = me.mecz_id), gospodarze_gol, goście_gol, czerwone_kartki, żółte_kartki FROM mecz;";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Games() {
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
		
		JMenuItem mntmDodajMecz = new JMenuItem("Dodaj Mecz");
		mntmDodajMecz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				AddGame addGame = new AddGame();
				addGame.setVisible(true);
			}
		});
		mnMenu.add(mntmDodajMecz);
		mnMenu.add(mntmStronaGwna);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 606, 405);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		printTable();
	}

}
