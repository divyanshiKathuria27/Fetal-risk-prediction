import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
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
import javax.swing.JButton;

public class Actions extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	JButton btnSaveAction;
    static int id=1;
    private JTextField textField_2;
    private JLabel lblValue;
    JButton btnAddMore;
    String Title, Attributes="",Value="",a,v;
	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Actions frame = new Actions();
					frame.setVisible(true);
					frame.setTitle("Actions Page");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Actions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterActionTitle = new JLabel("Enter Action Title : ");
		lblEnterActionTitle.setForeground(Color.YELLOW);
		lblEnterActionTitle.setFont(new Font("Microsoft New Tai Lue", Font.BOLD | Font.ITALIC, 13));
		lblEnterActionTitle.setBounds(20, 46, 164, 26);
		contentPane.add(lblEnterActionTitle);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(211, 40, 213, 38);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(236, 129, 89, 38);
		contentPane.add(textField_1);
		
		JButton button = new JButton("Home");
		button.setBackground(new Color(255, 222, 173));
		button.setBounds(10, 11, 69, 23);
		contentPane.add(button);
		
		btnSaveAction = new JButton("Save Action");
		btnSaveAction.setBackground(new Color(240, 240, 240));
		btnSaveAction.setForeground(Color.BLACK);
		btnSaveAction.setBounds(146, 227, 128, 23);
		contentPane.add(btnSaveAction);
		
		JLabel lblEnterActionAttribute = new JLabel("Enter Action Attribute and value : ");
		lblEnterActionAttribute.setForeground(Color.YELLOW);
		lblEnterActionAttribute.setFont(new Font("Microsoft New Tai Lue", Font.BOLD | Font.ITALIC, 13));
		lblEnterActionAttribute.setBounds(10, 135, 216, 26);
		contentPane.add(lblEnterActionAttribute);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(335, 129, 89, 38);
		contentPane.add(textField_2);
		
		JLabel lblAttribute = new JLabel("Attribute");
		lblAttribute.setBounds(236, 104, 89, 14);
		contentPane.add(lblAttribute);
		
		lblValue = new JLabel("Value");
		lblValue.setBounds(335, 104, 89, 14);
		contentPane.add(lblValue);
		
		btnAddMore = new JButton("Add  More");
		btnAddMore.setBounds(146, 178, 128, 23);
		contentPane.add(btnAddMore);
		
		addActionEvent();
	}
	public void addActionEvent()
    {
		btnSaveAction.addActionListener(this);	
		btnAddMore.addActionListener(this);
	
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnAddMore)
		{
			a=textField_1.getText();
			v=textField_2.getText();
			Attributes=Attributes.concat(a+",");
			Value=Value.concat(v+",");
			textField_1.setText("");
			textField_2.setText("");
		}
		if(arg0.getSource()==btnSaveAction)
		{
			Title=textField.getText();
			a=textField_1.getText();
			v=textField_2.getText();
			Attributes=Attributes.concat(a+",");
			Value=Value.concat(v+",");
			if(Title.length()==0 || Attributes.length() ==0 || Value.length()==0)
			{
				JFrame f=new JFrame();  
				JOptionPane.showMessageDialog(f,"Hello, Actions empty");
			    
			}
			else
			{
				System.out.println(Title);
				System.out.println(Attributes);
				System.out.println(Value);
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
					
					String p="insert into actions(decision_id,action_title,action_attributes_list,action_attributes_values) values(?,?,?,?)";
					PreparedStatement ps1 = conn.prepareStatement(p);
					ps1.setInt(1, decision_id);
		  			ps1.setString(2,Title);
		  			ps1.setString(3,Attributes);
		  			ps1.setString(4,Value);
		  			int r= ps1.executeUpdate();
		  			if(r>0)
					{
						
						 JFrame f=new JFrame();  
						 JOptionPane.showMessageDialog(f,"Hello, Actions inserted");  
						 id++;
					}
		  			
				}
				catch(Exception e)
				{
					
				}
			}
			
		}
		
	}
}
