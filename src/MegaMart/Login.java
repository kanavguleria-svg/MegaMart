package MegaMart;

import java.awt.BorderLayout;
import java.awt.Image;
import java.sql.*;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.LineBorder;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField eid;
	private JPasswordField password;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	
	private void loginevent()
	{
		Connection con = Connector.DBcon();
		try 
		{
		String query = "select * from Employee where E_ID=? and password=?";
					
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, eid.getText());
			pst.setString(2, new String(password.getPassword()));
			ResultSet rs = pst.executeQuery();
			
			int count = 0;
			while(rs.next())
			{
				count++;
			}
			
			if(count == 1)
			{
				JOptionPane.showMessageDialog(null, "Login Successful");
				rs.close();
				pst.close();
				con.close();
				//EID=Integer.parseInt(eid.getText());
				dispose();
				java.awt.EventQueue.invokeLater(new Runnable() {
			          public void run() {
			        	  new Billing().setVisible(true); 
			          }
			       });

			}
			else
				JOptionPane.showMessageDialog(null, "Username or Password are Incorrect");
			rs.close();
			pst.close();
			con.close();

		} 
		catch (Exception e2) 
		{
			JOptionPane.showMessageDialog(null, e2);
		}
	}
	

	protected Login() {
		
		setBounds(435, 200, 658, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 250, 250));
		panel.setBounds(0, 0, 658, 400);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 250, 250));
		panel_1.setBorder(new LineBorder(new Color(220, 220, 220)));
		panel_1.setBounds(328, 62, 322, 315);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		eid = new JTextField();
		eid.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(password.getText().isEmpty())
						password.requestFocus();
					
					else
						loginevent();
						
				}
						
			}
		});
		eid.setToolTipText("Employee ID");
		eid.setHorizontalAlignment(SwingConstants.CENTER);
		eid.setFont(new Font("Montserrat", Font.PLAIN, 13));
		eid.setBorder(new LineBorder(new Color(211, 211, 211)));
		eid.setBounds(115, 79, 156, 28);
		panel_1.add(eid);
		eid.setColumns(10);
		
		password = new JPasswordField();
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					loginevent();
			}
		});
		password.setToolTipText("Password");
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBorder(new LineBorder(new Color(211, 211, 211)));
		password.setBounds(115, 138, 156, 28);
		panel_1.add(password);
		
		JLabel lbleid = new JLabel("");
		lbleid.setFont(new Font("Montserrat", Font.PLAIN, 14));
		lbleid.setHorizontalAlignment(SwingConstants.CENTER);
		lbleid.setBounds(54, 79, 51, 32);
		Image img1 = new ImageIcon(this.getClass().getResource("profile.png")).getImage();
		lbleid.setIcon(new ImageIcon(img1));
		panel_1.add(lbleid);
		
		JLabel lblpass = new JLabel("");
		lblpass.setHorizontalAlignment(SwingConstants.CENTER);
		lblpass.setFont(new Font("Montserrat", Font.PLAIN, 14));
		lblpass.setBounds(51, 138, 51, 39);
		Image img2 = new ImageIcon(this.getClass().getResource("key.png")).getImage();
		lblpass.setIcon(new ImageIcon(img2));
		panel_1.add(lblpass);
		
		JLabel lblSignIn = new JLabel("LOGIN");
		lblSignIn.setForeground(new Color(0, 0, 0));
		lblSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignIn.setFont(new Font("Montserrat Medium", Font.PLAIN, 16));
		lblSignIn.setBounds(115, 29, 148, 28);
		panel_1.add(lblSignIn);
		
		JLabel lblCancel = new JLabel("Cancel");
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCancel.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancel.setForeground(Color.BLACK);
		lblCancel.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblCancel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCancel.setBounds(199, 221, 87, 28);
		panel_1.add(lblCancel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(62, 221, 87, 28);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				loginevent();
			}
		});
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 250, 250));
		lblNewLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setBackground(new Color(135, 206, 235));
		lblNewLabel.setBounds(0, 0, 87, 28);
		panel_2.add(lblNewLabel);
		
		
		
		JLabel lblMgmt = new JLabel("Management");
		lblMgmt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMgmt.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblMgmt.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				MgmtLogin a = new MgmtLogin();
				a.setVisible(true);
				dispose();
			}
		});
		lblMgmt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMgmt.setFont(new Font("Montserrat", Font.PLAIN, 10));
		lblMgmt.setHorizontalAlignment(SwingConstants.CENTER);
		lblMgmt.setBounds(131, 277, 87, 13);
		panel_1.add(lblMgmt);
		
		JLabel lbllogin = new JLabel("");
		lbllogin.setBounds(6, 62, 313, 315);
		panel.add(lbllogin);
		Image img3 = new ImageIcon(this.getClass().getResource("jk.jpg")).getImage();
		lbllogin.setIcon(new ImageIcon(img3));
		
		JLabel lblNewLabel_1 = new JLabel("xxx");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Open Sans", Font.BOLD, 18));
		lblNewLabel_1.setBounds(99, 10, 414, 28);
		panel.add(lblNewLabel_1);
		
		setUndecorated(true);
	}
}

