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
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import net.proteanit.sql.DbUtils;
public class ViewObjectives extends JFrame {
	private JPanel contentPane;
	Container viewObjectives=getContentPane(); 
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewObjectives frame = new ViewObjectives();
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
	public ViewObjectives() {
		viewObjectives.setBackground(new Color(255, 127, 80));
		viewObjectives.setLayout(null);
		
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
	  			PreparedStatement statement = conn.prepareStatement("select * from objectives");    
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
