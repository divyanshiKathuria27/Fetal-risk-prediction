import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class ViewActions extends JFrame {

	private JPanel contentPane;
	Container viewActions=getContentPane(); 
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewActions frame = new ViewActions();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewActions() {
		viewActions.setBackground(new Color(255, 127, 80));
		viewActions.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 496, 356);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try
	  	  {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	  		Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=abcd123;database=Sample");
	  		  if(conn!=null)
	  		  { 
	  			PreparedStatement statement = conn.prepareStatement("select * from actions");    
	  			ResultSet resultSet = statement.executeQuery();
	  			table.setModel(DbUtils.resultSetToTableModel(resultSet));
	  		  }
	  	  }
	  		  
	  	  catch(Exception e)
	  	  {
	  		  System.out.println(e);
	  	  }
	}

}
