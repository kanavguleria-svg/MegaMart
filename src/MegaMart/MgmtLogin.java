package MegaMart;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class MgmtLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txteid;
	private JPasswordField password;
	
	
	/**
	 * Create the frame.
	 */
	
	
	protected void loginevent()
	{
		Connection con = Connector.DBcon();
		try
		{
			String query = "select * from Employee,Department where Dmgr_ID=E_ID and E_ID=? and password=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, txteid.getText());
			pst.setString(2, new String(password.getPassword()));
			ResultSet rs = pst.executeQuery();
			
			int count = 0;
			while(rs.next())
			{
				count++;
			}
			
			if(count==1)
			{
				JOptionPane.showMessageDialog(null, "Login Successfull");
				dispose();
				Dashboard a = new Dashboard();
				a.setVisible(true);
				rs.close();
				pst.close();
				con.close();
			}
			
			else
				JOptionPane.showMessageDialog(null, "Invalid Credentials");
			
			rs.close();
			pst.close();
			con.close();
		} 
		catch (Exception e2) 
		{
			JOptionPane.showMessageDialog(null, e2);
		}
	}
	
	
	
	public MgmtLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(425, 180, 700, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 700, 460);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(30, 144, 255));
		panel_1.setBounds(10, 53, 309, 380);
		panel.add(panel_1);
		
		txteid = new JTextField();
		txteid.setToolTipText("Manager ID");
		txteid.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(new String(password.getPassword()).isEmpty())
						password.requestFocus();
					
					else
						loginevent();
						
				}
			}
		});
		txteid.setHorizontalAlignment(SwingConstants.CENTER);
		txteid.setFont(new Font("Montserrat", Font.PLAIN, 13));
		txteid.setColumns(10);
		txteid.setBounds(72, 132, 169, 27);
		panel_1.add(txteid);
		
		password = new JPasswordField();
		password.setToolTipText("Password");
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					loginevent();
			}
		});
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setFont(new Font("Montserrat", Font.PLAIN, 12));
		password.setBounds(72, 202, 169, 27);
		panel_1.add(password);
		
		JLabel lblemp = new JLabel("Not Manegement?");
		lblemp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblemp.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblemp.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Login a = new Login();
				a.setVisible(true);
			}
		});
		lblemp.setForeground(new Color(0, 0, 0));
		lblemp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblemp.setHorizontalAlignment(SwingConstants.CENTER);
		lblemp.setFont(new Font("Montserrat", Font.PLAIN, 10));
		lblemp.setBounds(102, 349, 119, 21);
		panel_1.add(lblemp);
		
		JLabel lblCancel = new JLabel("Cancel");
		lblCancel.setForeground(new Color(255, 255, 255));
		lblCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblCancel.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancel.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblCancel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCancel.setBounds(172, 285, 96, 27);
		panel_1.add(lblCancel);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setBackground(new Color(255, 99, 71));
		panel_1_1.setBounds(51, 282, 96, 30);
		panel_1.add(panel_1_1);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				loginevent();
			}
		});
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setBounds(0, 0, 96, 30);
		panel_1_1.add(lblNewLabel);
		
		JLabel lbleid = new JLabel("");
		lbleid.setHorizontalAlignment(SwingConstants.CENTER);
		lbleid.setBounds(22, 132, 46, 36);
		Image img1 = new ImageIcon(this.getClass().getResource("profile.png")).getImage();
		lbleid.setIcon(new ImageIcon(img1));
		panel_1.add(lbleid);
		
		JLabel lblpass = new JLabel("");
		lblpass.setHorizontalAlignment(SwingConstants.CENTER);
		lblpass.setBounds(22, 193, 46, 36);
		Image img2 = new ImageIcon(this.getClass().getResource("key.png")).getImage();
		lblpass.setIcon(new ImageIcon(img2));
		panel_1.add(lblpass);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Montserrat Medium", Font.PLAIN, 16));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(72, 48, 157, 36);
		panel_1.add(lblLogin);
		
		JLabel label = new JLabel("");
		label.setBounds(342, 53, 378, 380);
		Image img = new ImageIcon(this.getClass().getResource("mgmt1.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		panel.add(label);
		
		JLabel lblXxx = new JLabel("xxx");
		lblXxx.setFont(new Font("Montserrat", Font.BOLD, 16));
		lblXxx.setHorizontalAlignment(SwingConstants.CENTER);
		lblXxx.setBounds(104, 10, 453, 46);
		panel.add(lblXxx);
		setUndecorated(true);
	}
}

