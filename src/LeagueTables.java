import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class LeagueTables extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeagueTables frame = new LeagueTables();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public LeagueTables() {
		
		frame = new JFrame();
		frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.getContentPane().setLayout(null);
		Connection conn = Connect.getConnect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 57, 616, 360);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 640, 21);
		contentPane.add(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JMenu mnWybierzLig = new JMenu("Wybierz Ligę");
		menu.add(mnWybierzLig);
		
		JMenuItem menuItem = new JMenuItem("Premier League");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT nazwa, menadżer, pkt, ilość_meczy, bramki_strzelone, bramki_stracone FROM klub WHERE liga_id = 1 ORDER BY pkt DESC;";
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnWybierzLig.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Ligue 1");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT nazwa, menadżer, pkt, ilość_meczy, bramki_strzelone, bramki_stracone FROM klub WHERE liga_id = 3 ORDER BY pkt DESC;";
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnWybierzLig.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Serie A");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT nazwa, menadżer, pkt, ilość_meczy, bramki_strzelone, bramki_stracone FROM klub WHERE liga_id = 4 ORDER BY pkt DESC;";
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnWybierzLig.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("Bundesliga");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT nazwa, menadżer, pkt, ilość_meczy, bramki_strzelone, bramki_stracone FROM klub WHERE liga_id = 5 ORDER BY pkt DESC;";
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnWybierzLig.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("La Liga");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT nazwa, menadżer, pkt, ilość_meczy, bramki_strzelone, bramki_stracone FROM klub WHERE liga_id = 2 ORDER BY pkt DESC;";
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnWybierzLig.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("Ekstraklasa");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT nazwa, menadżer, pkt, ilość_meczy, bramki_strzelone, bramki_stracone FROM klub WHERE liga_id = 6 ORDER BY pkt DESC;";
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnWybierzLig.add(menuItem_5);
		
		/* *******************Powrót do Strony Głównej ************************ */
		
		JMenuItem mntmPowrt = new JMenuItem("Strona Główna");
		mntmPowrt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				MainWindow.main(null);
			}
		});
		menu.add(mntmPowrt);
	}
}
