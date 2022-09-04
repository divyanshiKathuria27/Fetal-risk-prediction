import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;

public class Choices extends JFrame implements ActionListener{
	
    String attribute_list;
	private JPanel contentPane;
	private JTextField textField;
	JButton btnSave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
	}

	/**
	 * Create the frame.
	 */
	public Choices() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(95, 158, 160));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		JButton button = new JButton("Home");
		button.setBounds(10, 11, 69, 23);
		button.setBackground(new Color(255, 222, 173));
		panel.add(button);
	
		
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	  		Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=abcd123;database=Sample");
	  		  if(conn!=null)
	  		  {
	  			System.out.println("Connection done");
	  		  }
	  		  else
	  			  System.out.println("Connection failed");
	  		String q = "Select attributes from Objectives where decision_id = (Select max(decision_id) from decision)";
	  		PreparedStatement ps2 = conn.prepareStatement(q);
	  		ResultSet rs=ps2.executeQuery();
	  		attribute_list="";
	  		while(rs.next())
			{
	  			attribute_list=attribute_list.concat(rs.getString(1));
	  			attribute_list=attribute_list.concat(",");
	  			
			}
		}
		catch(Exception e)
		{
			
		}
		
		JLabel l = new JLabel("Enter the values for each of the attributes : "+attribute_list);
		l.setBounds(24, 100, 399, 43);
		panel.add(l);
		
		JTextField tf=new JTextField();
		panel.add(tf);
		
		textField = new JTextField();
		textField.setBounds(41, 188, 277, 43);
		panel.add(textField);
		textField.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(149, 292, 89, 23);
		panel.add(btnSave);
		
		
		addActionEvent();
	}
	
	public void addActionEvent()
    {
		btnSave.addActionListener(this);
    }
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource()==btnSave)
		{
			String Value;
			Value=textField.getText();
			try
			{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		  		Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=abcd123;database=Sample");
		  		String q= "Select max(decision_id) from decision";
		  		PreparedStatement ps = conn.prepareStatement(q);
				ResultSet rs=ps.executeQuery();
				int decision_id=0;
				while(rs.next())
				{
					decision_id=rs.getInt(1);
				}
				String p="insert into choices(decision_id,attributes_names,attributes_values) values(?,?,?)";
				PreparedStatement ps1 = conn.prepareStatement(p);
				ps1.setInt(1, decision_id);
	  			ps1.setString(2,attribute_list);
	  			ps1.setString(3,Value);
	  			int r= ps1.executeUpdate();
	  			
	  			if(r>0)
				{
					
					 JFrame f=new JFrame();  
					 JOptionPane.showMessageDialog(f,"Hello, Choices inserted");  
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}
  }
	

