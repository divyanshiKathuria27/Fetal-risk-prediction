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
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Objective extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	JButton btnSave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Objective frame = new Objective();
					frame.setVisible(true);
					frame.setTitle("Objectives Page");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Objective() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 444);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Home");
		button.setBackground(new Color(255, 222, 173));
		button.setBounds(10, 11, 69, 23);
		contentPane.add(button);
		
		JLabel lblEnterObjective = new JLabel("Enter Objective : ");
		lblEnterObjective.setForeground(Color.YELLOW);
		lblEnterObjective.setFont(new Font("Microsoft New Tai Lue", Font.ITALIC, 12));
		lblEnterObjective.setBounds(10, 77, 105, 26);
		contentPane.add(lblEnterObjective);
		
		JLabel lblEnterIndicatorUnit = new JLabel("Enter Indicator Unit : ");
		lblEnterIndicatorUnit.setForeground(Color.YELLOW);
		lblEnterIndicatorUnit.setFont(new Font("Microsoft New Tai Lue", Font.ITALIC, 12));
		lblEnterIndicatorUnit.setBounds(10, 129, 131, 26);
		contentPane.add(lblEnterIndicatorUnit);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(185, 131, 213, 23);
		contentPane.add(textField_1);
		
		JLabel lblEnterDirectionl = new JLabel("Enter Direction\r\n(L for Low to High and H for High to low) : ");
		lblEnterDirectionl.setForeground(Color.YELLOW);
		lblEnterDirectionl.setFont(new Font("Microsoft New Tai Lue", Font.ITALIC, 12));
		lblEnterDirectionl.setBounds(10, 171, 312, 26);
		contentPane.add(lblEnterDirectionl);
		
		textField = new JTextField();
		textField.setBounds(170, 80, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(312, 80, 86, 20);
		contentPane.add(textField_2);
		
		JLabel lblImperative = new JLabel("Imperative");
		lblImperative.setBounds(180, 55, 76, 14);
		contentPane.add(lblImperative);
		
		JLabel lblNoun = new JLabel("Noun");
		lblNoun.setBounds(316, 55, 76, 14);
		contentPane.add(lblNoun);
		
		textField_3 = new JTextField();
		textField_3.setBounds(345, 174, 53, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblEnterAttributeName = new JLabel("Enter Attribute Name :");
		lblEnterAttributeName.setForeground(Color.YELLOW);
		lblEnterAttributeName.setFont(new Font("Microsoft New Tai Lue", Font.ITALIC, 12));
		lblEnterAttributeName.setBounds(10, 208, 131, 26);
		contentPane.add(lblEnterAttributeName);
		
		textField_4 = new JTextField();
		textField_4.setBounds(293, 214, 105, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSave.setBackground(new Color(255, 222, 173));
		btnSave.setBounds(167, 322, 69, 23);
		contentPane.add(btnSave);
		
		addActionEvent();
	}
	public void addActionEvent()
    {
		btnSave.addActionListener(this);	
	
    }

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource()==btnSave)
		{
			String Imperative, Noun;
			Imperative = textField.getText();
			Noun= textField_2.getText();
			if(Imperative.length()==0 || Noun.length() ==0)
			{
				JFrame f=new JFrame();  
				JOptionPane.showMessageDialog(f,"Hello, Objective empty");
			    
			}
			else
			{
				String indicator, direction, attributes;
				indicator=textField_1.getText();
				direction=textField_3.getText();
				attributes=textField_4.getText();
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
			  		String p="insert into Objectives(decision_id,imperative,noun,indication_unit,direction,attributes) values(?,?,?,?,?,?)";
					PreparedStatement ps1 = conn.prepareStatement(p);
					ps1.setInt(1, decision_id);
		  			ps1.setString(2,Imperative);
		  			ps1.setString(3,Noun);
		  			ps1.setString(4,indicator);
		  			ps1.setString(5,direction);
		  			ps1.setString(6,attributes);
		  			int r= ps1.executeUpdate();
		  			if(r>0)
					{
						
						 JFrame f=new JFrame();  
						 JOptionPane.showMessageDialog(f,"Hello, Objective inserted");  
					}
		  			textField.setText("");
		  			textField_1.setText("");
		  			textField_2.setText("");
		  			textField_3.setText("");
		  			textField_4.setText("");
		  	
				}
				catch(Exception e)
				{
					
				}
			}
		}
		
	}

}
