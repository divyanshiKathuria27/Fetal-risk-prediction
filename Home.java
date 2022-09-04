

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;



import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.UIManager;
public class Home extends JFrame implements ActionListener{

	Container container=getContentPane(); 
    JCheckBox showPassword = new JCheckBox("Show Password");
    JButton btnEnterUncertainties;
    private JTextField textField;
    private JTextField textField_1;
    JButton btnSaveDecision;
    JButton btnViewAvailableObjectives;
    JButton btnViewAvailableChoices ;
    JButton btnEnterChoices;
    JButton btnViewUncertainties;
    JButton btnViewObjectives;
    JButton btnViewActions;
    JButton btnViewChoices;
	public static void main(String[] args) {
		Home frame=new Home();
		frame.setTitle("Home Page");
		frame.setVisible(true);
		frame.setBounds(200, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setResizable(false);
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		getContentPane().setBackground(new Color(95, 158, 160));
		container.setLayout(null);
		showPassword.setBounds(50, 270, 110, 23);
		
		JLabel lblDecisionToBe = new JLabel("Enter the Decision to be Taken : ");
		lblDecisionToBe.setForeground(new Color(255, 255, 0));
		lblDecisionToBe.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblDecisionToBe.setBounds(10, 63, 207, 30);
		getContentPane().add(lblDecisionToBe);
		
		btnEnterUncertainties = new JButton("Enter Uncertainties");
		btnEnterUncertainties.setBackground(new Color(255, 192, 203));
		btnEnterUncertainties.setForeground(UIManager.getColor("Tree.foreground"));
		btnEnterUncertainties.setBounds(29, 216, 153, 23);
		getContentPane().add(btnEnterUncertainties);
		
		btnViewAvailableObjectives = new JButton("Enter Objectives");
		btnViewAvailableObjectives.setBackground(new Color(255, 192, 203));
		btnViewAvailableObjectives.setForeground(UIManager.getColor("Tree.foreground"));
		btnViewAvailableObjectives.setBounds(29, 250, 153, 23);
		getContentPane().add(btnViewAvailableObjectives);
		
		btnViewAvailableChoices = new JButton("Enter Actions");
		btnViewAvailableChoices.setBackground(new Color(255, 192, 203));
		btnViewAvailableChoices.setForeground(UIManager.getColor("Tree.foreground"));
		btnViewAvailableChoices.setBounds(29, 284, 153, 23);
		getContentPane().add(btnViewAvailableChoices);
		
		textField = new JTextField();
		textField.setBounds(227, 70, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(326, 70, 86, 20);
		getContentPane().add(textField_1);
		
		JLabel lblImperative = new JLabel("Imperative");
		lblImperative.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImperative.setBounds(237, 45, 65, 14);
		getContentPane().add(lblImperative);
		
		JLabel lblNoun = new JLabel("Noun");
		lblNoun.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNoun.setBounds(346, 45, 46, 14);
		getContentPane().add(lblNoun);
		
		btnSaveDecision = new JButton("Save Decision");
		btnSaveDecision.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSaveDecision.setBackground(new Color(0, 255, 0));
		btnSaveDecision.setForeground(new Color(0, 0, 0));
		btnSaveDecision.setBounds(156, 98, 131, 23);
		getContentPane().add(btnSaveDecision);
		
		btnViewUncertainties = new JButton("View Uncertainties");
		btnViewUncertainties.setBackground(new Color(255, 140, 0));
		btnViewUncertainties.setForeground(Color.BLACK);
		btnViewUncertainties.setBounds(259, 216, 153, 23);
		getContentPane().add(btnViewUncertainties);
		
		btnViewObjectives = new JButton("View Objectives");
		btnViewObjectives.setBackground(new Color(255, 140, 0));
		btnViewObjectives.setForeground(Color.BLACK);
		btnViewObjectives.setBounds(259, 250, 153, 23);
		getContentPane().add(btnViewObjectives);
		
		btnViewActions = new JButton("View Actions");
		btnViewActions.setBackground(new Color(255, 140, 0));
		btnViewActions.setForeground(Color.BLACK);
		btnViewActions.setBounds(259, 284, 153, 23);
		getContentPane().add(btnViewActions);
		
		btnViewChoices = new JButton("View Choices");
		btnViewChoices.setBackground(new Color(255, 140, 0));
		btnViewChoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnViewChoices.setForeground(Color.BLACK);
		btnViewChoices.setBounds(259, 318, 153, 23);
		getContentPane().add(btnViewChoices);
		
		btnEnterChoices = new JButton("Enter Choices");
		btnEnterChoices.setForeground(Color.BLACK);
		btnEnterChoices.setBackground(new Color(255, 192, 203));
		btnEnterChoices.setBounds(29, 318, 153, 23);
		getContentPane().add(btnEnterChoices);
		
		addActionEvent();
		
	}
	public void addActionEvent()
    {
		btnEnterUncertainties.addActionListener(this);	
		btnSaveDecision.addActionListener(this);
		btnViewAvailableObjectives.addActionListener(this);
		btnViewAvailableChoices.addActionListener(this);
		btnEnterChoices.addActionListener(this);
		btnViewUncertainties.addActionListener(this);
		btnViewObjectives.addActionListener(this);
		btnViewActions.addActionListener(this);
		btnViewChoices.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource()==btnViewChoices)
		{
			viewChoices viewchoices	= new viewChoices();
			viewchoices.setVisible(true);
		}
		if(arg0.getSource()==btnViewActions)
		{
			ViewActions viewactions= new ViewActions();
			viewactions.setVisible(true);
		}
		if(arg0.getSource()==btnViewObjectives)
		{
			ViewObjectives viewobjectives=new ViewObjectives();
			viewobjectives.setVisible(true);
		}
		if(arg0.getSource()==btnViewUncertainties)
		{
			ViewUncertainty viewuncertainty=new ViewUncertainty();
			viewuncertainty.setVisible(true);
		}
		if(arg0.getSource()==btnEnterChoices)
		{
			Choices choice= new Choices();
	        choice.setVisible(true);	
		}
		if(arg0.getSource()==btnViewAvailableChoices)
		{
			Actions action= new Actions ();
	        action.setVisible(true);	
		}
		if(arg0.getSource()==btnViewAvailableObjectives)
		{
			Objective objective= new Objective ();
			objective.setVisible(true);
			
		}
		if(arg0.getSource()==btnEnterUncertainties)
		  {
			Uncertainities uncertainities= new Uncertainities();
			uncertainities.setVisible(true);
		  }
		if(arg0.getSource()== btnSaveDecision)
		{
			String imperative,noun;
			imperative=textField.getText();
			noun=textField_1.getText();
			System.out.println(imperative+" "+noun);
			if(imperative.length()==0 || noun.length() ==0)
			{
				JFrame f=new JFrame();  
				JOptionPane.showMessageDialog(f,"Hello, Decision empty");
			    
			}
			else{
			String query = "INSERT INTO decision(decision_imperative,decision_noun) "+ "VALUES(?,?)";
			try
			{
				System.out.println(imperative);
				System.out.println(noun);
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		  		Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=abcd123;database=Sample");
		  		if(conn!=null)
		  			System.out.println("Connection Successful");
		  		PreparedStatement ps = conn.prepareStatement(query);
	  			ps.setString(1, imperative);
	  			ps.setString(2, noun);
				int r= ps.executeUpdate();
				if(r>0)
				{
					System.out.println("HII");
					 JFrame f=new JFrame();  
					 JOptionPane.showMessageDialog(f,"Hello, Decision inserted");  
				}
			}
			catch(Exception e)
			{
				
			}
			}
		}
	}
}
