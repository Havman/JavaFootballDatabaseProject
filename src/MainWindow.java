import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JScrollBar;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class MainWindow {

	public JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.getContentPane().setLayout(null);
		Connection conn = Connect.getConnect();
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/img.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(34, 0, 560, 255);
		frame.getContentPane().add(label);
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		
		/* *******************Przejście do Tabel Ligowych ************************ */
		
		JMenuItem mntmTabeleLigowe = new JMenuItem("Tabele Ligowe");
		mntmTabeleLigowe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				LeagueTables leagueT = new LeagueTables();
				leagueT.setVisible(true);
			}
		});
		mnMenu.add(mntmTabeleLigowe);
		
		JMenuItem mntmPikarze = new JMenuItem("Piłkarze");
		mntmPikarze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Players players = new Players();
				players.setVisible(true);
			}
		});
		mnMenu.add(mntmPikarze);
		
		JMenuItem mntmMecze = new JMenuItem("Mecze");
		mntmMecze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Games games = new Games();
				games.setVisible(true);
			}
		});
		mnMenu.add(mntmMecze);
		
		JMenuItem mntmBramkiIKartki = new JMenuItem("Bramki i Kartki");
		mntmBramkiIKartki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Events events = new Events();
				events.setVisible(true);
			}
		});
		mnMenu.add(mntmBramkiIKartki);
	}
}
