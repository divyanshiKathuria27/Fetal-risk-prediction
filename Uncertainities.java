import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Checkbox;
import javax.swing.JButton;
import java.awt.TextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Uncertainities extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnHome;
	Connection conn;
	private JLabel lblEnterUncertaintyTitle;
	private JLabel label;
	private JTextField textField_1;
	JButton btnSave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Uncertainities frame = new Uncertainities();
					frame.setVisible(true);
					frame.setTitle("Uncertainity Page");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Uncertainities() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(211, 126, 213, 38);
		contentPane.add(textField);
		
		btnHome = new JButton("Home");
		btnHome.setBackground(new Color(255, 222, 173));
		btnHome.setBounds(10, 11, 69, 23);
		contentPane.add(btnHome);
		
		lblEnterUncertaintyTitle = new JLabel("Enter Uncertainty Attributes : ");
		lblEnterUncertaintyTitle.setForeground(new Color(255, 255, 0));
		lblEnterUncertaintyTitle.setFont(new Font("Microsoft New Tai Lue", Font.BOLD | Font.ITALIC, 13));
		lblEnterUncertaintyTitle.setBounds(10, 132, 191, 26);
		contentPane.add(lblEnterUncertaintyTitle);
		
		label = new JLabel("Enter Uncertainty Title : ");
		label.setForeground(Color.YELLOW);
		label.setFont(new Font("Microsoft New Tai Lue", Font.BOLD | Font.ITALIC, 13));
		label.setBounds(10, 67, 164, 26);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(211, 61, 213, 38);
		contentPane.add(textField_1);
		
		btnSave = 
				new JButton("Save");
		btnSave.setBounds(148, 195, 89, 23);
		contentPane.add(btnSave);
		
		
		try
	  	  {
	  		  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	  		   conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=abcd123;database=Sample");
	  		  if(conn!=null)
	  		  {
	  			System.out.println("Connection done");
	  		  }
	  		  else
	  			  System.out.println("Connection failed");
	  		  
	  	  }
	  	  catch(Exception e)
	  	  {
	  		  System.out.println(e);
	  	  }
		
		
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
			String Title, Attributes;
			Title= textField_1.getText();
			Attributes=textField.getText();
			if(Title.length()==0 || Attributes.length() ==0)
			{
				JFrame f=new JFrame();  
				JOptionPane.showMessageDialog(f,"Hello, Uncertainty empty");
			    
			}
			else{
			System.out.println(Title+" "+Attributes);
			String q= "Select max(decision_id) from decision";
			try{	
			PreparedStatement ps = conn.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			int decision_id=0;
			while(rs.next())
			{
				decision_id=rs.getInt(1);
			}
			String p="insert into uncertainty(decision_id,uncertainty_title,uncertainty_attributes) values(?,?,?)";
			PreparedStatement ps1 = conn.prepareStatement(p);
			ps1.setInt(1, decision_id);
  			ps1.setString(2,Title);
  			ps1.setString(3,Attributes);
  			int r= ps1.executeUpdate();
  			
  			if(r>0)
			{
				
				 JFrame f=new JFrame();  
				 JOptionPane.showMessageDialog(f,"Hello, Uncertainty inserted");  
			}
			textField_1.setText("");
			textField.setText("");
			}
			catch(Exception e)
			{
				
			}
		}
		}
	}
}
