package MegaMart;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class Billing extends JFrame {
	
	private JTextField txtiname;
	private JTextField txticode;
	private JTextField txtiquant;
	private JTextField txtst;
	private Connection con;
	private JTextField txttotalcost;
	private JTextField txtcash;
	private JTextField txtbalance;
	private JTextPane epbill;
	private static JMenuItem it_1;

	
	private Double totalAmt = 0.0;
	
	/**
	 * The ArrayLists store the input values 
	 * to create the final bill
	 * */
	ArrayList<String> Itemname = new ArrayList<String>();
	ArrayList<String> Quantity = new ArrayList<String>();
	ArrayList<String> Price = new ArrayList<String>();
	ArrayList<String> Subtotal = new ArrayList<String>();
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Billing frame = new Billing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * This function adds the user input to the bill and updates the database
	 * 
	 * */
	
	private void AddToBill()
	{
		
		con = Connector.DBcon();
		
		try 
		{
			int quantity=Integer.parseInt(txtiquant.getText());
			String query = "update Items set IT_quantity=IT_quantity-? where IT_ID=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, quantity+"");
			pst.setString(2, txticode.getText());
			
  //Updating database
			int result=pst.executeUpdate();
			if(result!=1)
				JOptionPane.showMessageDialog(null, "Datbase Update Failed", "Alert", JOptionPane.ERROR_MESSAGE);
			pst.close();
			
  //Setting sub total
			query = "select IT_price from Items where IT_ID=?";
			pst = con.prepareStatement(query);
			pst.setString(1, txticode.getText());
			ResultSet rs = pst.executeQuery();
			rs.next();
			Double price = rs.getDouble(1);
			Double st = price*quantity;
			txtst.setText(st+"");
			rs.close();
			pst.close();
			con.close();

	//Setting Total	
			totalAmt += st;
			txttotalcost.setText(totalAmt+"");
			
	//Updating Bill
			Itemname.add(txtiname.getText());
			Quantity.add(txtiquant.getText());
			Price.add(price+"");
			Subtotal.add(st+"");
			
			append("\n          "+txtiname.getText()+"\t              "+txtiquant.getText()+"\t \t"+ price + "\t"+st);
			
			txtiname.requestFocus();
			Clear();
		} 
		
		
		catch (SQLException e5)
		{
			if(e5.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (CHECK constraint failed: Items)"))
				JOptionPane.showMessageDialog(null, "Order Quantity Exceeds Available Stock", "Alert", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, e5);
		}
	}
	
	/**
	 * This Function clears all the textfields
	 * 
	 * */
	private void Clear()
	{
		txticode.setText("");
		txtiname.setText("");
		txtiquant.setText("");
		txtst.setText("");
	}
	
	/**
	 * This function sets the bill header
	 * 
	 * */
	private void billHeader()
	{
		DateTimeFormatter df= DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate d = LocalDate.now();
		DateTimeFormatter df1= DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime d1 = LocalDateTime.now();
		
		epbill.setText("\n"
				+"\t                           Mega Mart\n"
				+"\t  Loomba Nibbas, Summerhill, Shimla\n"
				+"\t                  Phone: 9418450347\n\n"
				+"\t           Date: "+df.format(d)+"     "+df1.format(d1)
				+"\n\n                  --------------------------------------------------------------------------\n\n\n"
				+"          NAME\t         QUANTITY\tPRICE\tSUBTOTAL\n\n");
	}
	
	/**
	 * This function adds the input String to the bill
	 * @param : The String to be added
	 * 
	 * */
	private void append(String s)
	{
		   try
		   {
		      Document doc = epbill.getDocument();
		      doc.insertString(doc.getLength(), s, null);
		   } 
		   catch(BadLocationException exc)
		   {
		      exc.printStackTrace();
		   }
	}
	
	
	/**
	 * This function totals and displays the final amount
	 * 
	 * */
	private void totaling()
	{
		if(Itemname.isEmpty())
			JOptionPane.showMessageDialog(null, "Enter The Bill Details First", "Alert", JOptionPane.ERROR_MESSAGE);
		
		else if(Double.parseDouble(txtcash.getText())<Double.parseDouble(txttotalcost.getText()))
			JOptionPane.showMessageDialog(null, "Cash cannot be less than total", "Alert", JOptionPane.ERROR_MESSAGE);
		
		else
		{
			Double bal = Double.parseDouble(txtcash.getText()) - Double.parseDouble(txttotalcost.getText());
			txtbalance.setText(bal+"");
		}
	}
	
	/**
	 * This function clears all the ArrayLits
	 * 
	 * */
	private void delete()
	{
		Itemname.clear();
		Price.clear();
		Quantity.clear();
		Subtotal.clear();
	}
	
	/**
	 * Create the frame.
	 */
	protected Billing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(170, 70, 1200, 700);
		setUndecorated(true);
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLUE);
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(0, 0, 1200, 700);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtiname = new JTextField();
		txtiname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					
					if(txtiname.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Enter Details", "Alert", JOptionPane.ERROR_MESSAGE);
					
					else
					{
						con = Connector.DBcon();
						
						try
						{
							String query = "select * from Items where IT_name=?";
							PreparedStatement pst = con.prepareStatement(query);
							pst.setString(1, txtiname.getText());
							ResultSet rs = pst.executeQuery();
							
							rs.next();
							txticode.setText(rs.getInt("IT_ID")+"");
							txtiquant.requestFocus();
							rs.close();
							pst.close();
							con.close();
							
							
						} 
						catch (Exception e1)
						{
							if(e1.getMessage()=="ResultSet closed")
								JOptionPane.showMessageDialog(null, "Wrong Item Name", "Alert", JOptionPane.ERROR_MESSAGE);
							else
								JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
				
				if(e.getKeyCode()==KeyEvent.VK_DOWN)
					txticode.requestFocus();
				
			}
		});
		txtiname.setHorizontalAlignment(SwingConstants.CENTER);
		txtiname.setFont(new Font("Montserrat", Font.PLAIN, 16));
		txtiname.setBounds(249, 97, 270, 32);
		panel.add(txtiname);
		txtiname.setColumns(10);
		
		txticode = new JTextField();
		txticode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					
					if(txticode.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Enter Details", "Alert", JOptionPane.ERROR_MESSAGE);
					
					else
					{
						con = Connector.DBcon();
						
						try
						{
							String query = "select * from Items where IT_ID=?";
							PreparedStatement pst = con.prepareStatement(query);
							pst.setString(1, txticode.getText());
							ResultSet rs = pst.executeQuery();
							
							rs.next();
							txtiname.setText(rs.getString("IT_name"));
							txtiquant.requestFocus();
							rs.close();
							pst.close();
							con.close();
							
							
						} 
						catch (Exception e1)
						{
							if(e1.getMessage()=="ResultSet closed")
								JOptionPane.showMessageDialog(null, "Wrong Item Code", "Alert", JOptionPane.ERROR_MESSAGE);
							else
								JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
				
				if(e.getKeyCode()==KeyEvent.VK_DOWN)
					txtiquant.requestFocus();
				
				if(e.getKeyCode()==KeyEvent.VK_UP)
					txtiname.requestFocus();
				
			}
		});
		txticode.setHorizontalAlignment(SwingConstants.CENTER);
		txticode.setFont(new Font("Montserrat", Font.PLAIN, 16));
		txticode.setColumns(10);
		txticode.setBounds(249, 177, 270, 32);
		panel.add(txticode);
		
		txtiquant = new JTextField();
		txtiquant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					
					if(txticode.getText().isEmpty()||txtiname.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Enter Details", "Alert", JOptionPane.ERROR_MESSAGE);
					else
						AddToBill();
					
				}
				
				if(e.getKeyCode()==KeyEvent.VK_UP)
					txticode.requestFocus();
			}
		});
		txtiquant.setHorizontalAlignment(SwingConstants.CENTER);
		txtiquant.setFont(new Font("Montserrat", Font.PLAIN, 16));
		txtiquant.setColumns(10);
		txtiquant.setBounds(249, 260, 270, 32);
		panel.add(txtiquant);
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setFont(new Font("Montserrat", Font.PLAIN, 14));
		lblItemName.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemName.setBounds(88, 98, 122, 32);
		panel.add(lblItemName);
		
		JLabel lblItemCode = new JLabel("Item Code");
		lblItemCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemCode.setFont(new Font("Montserrat", Font.PLAIN, 14));
		lblItemCode.setBounds(88, 177, 122, 32);
		panel.add(lblItemCode);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantity.setFont(new Font("Montserrat", Font.PLAIN, 14));
		lblQuantity.setBounds(88, 261, 122, 32);
		panel.add(lblQuantity);
		
		JLabel lblSubTotal = new JLabel("Sub Total");
		lblSubTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubTotal.setFont(new Font("Montserrat", Font.PLAIN, 14));
		lblSubTotal.setBounds(24, 313, 107, 32);
		panel.add(lblSubTotal);
		
		txtst = new JTextField();
		txtst.setDisabledTextColor(new Color(211, 211, 211));
		txtst.setEditable(false);
		txtst.setHorizontalAlignment(SwingConstants.CENTER);
		txtst.setFont(new Font("Montserrat", Font.PLAIN, 14));
		txtst.setColumns(10);
		txtst.setBounds(141, 313, 95, 32);
		panel.add(txtst);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtiname.getText().isEmpty()||txticode.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "No Details Entered", "Alert", JOptionPane.ERROR_MESSAGE);
				
				else
					AddToBill();
				
			}
		});
		btnAdd.setBackground(new Color(59, 89, 182));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Montserrat", Font.PLAIN, 13));
		btnAdd.setBounds(109, 377, 127, 38);
		panel.add(btnAdd);
		
		
		
		txttotalcost = new JTextField();
		txttotalcost.setEditable(false);
		txttotalcost.setHorizontalAlignment(SwingConstants.CENTER);
		txttotalcost.setFont(new Font("Montserrat", Font.PLAIN, 16));
		txttotalcost.setColumns(10);
		txttotalcost.setBounds(249, 448, 270, 32);
		panel.add(txttotalcost);
		
		txtcash = new JTextField();
		txtcash.setEditable(false);
		txtcash.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					totaling();
				}
			}
		});
		txtcash.setHorizontalAlignment(SwingConstants.CENTER);
		txtcash.setFont(new Font("Montserrat", Font.PLAIN, 16));
		txtcash.setColumns(10);
		txtcash.setBounds(249, 545, 270, 32);
		panel.add(txtcash);
		
		txtbalance = new JTextField();
		txtbalance.setEditable(false);
		txtbalance.setHorizontalAlignment(SwingConstants.CENTER);
		txtbalance.setFont(new Font("Montserrat", Font.PLAIN, 16));
		txtbalance.setColumns(10);
		txtbalance.setBounds(249, 623, 270, 32);
		panel.add(txtbalance);
		
		JLabel lblTotalCost = new JLabel("Total Cost");
		lblTotalCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalCost.setFont(new Font("Montserrat", Font.PLAIN, 14));
		lblTotalCost.setBounds(88, 449, 122, 32);
		panel.add(lblTotalCost);
		
		JLabel lblCash = new JLabel("Cash");
		lblCash.setHorizontalAlignment(SwingConstants.CENTER);
		lblCash.setFont(new Font("Montserrat", Font.PLAIN, 14));
		lblCash.setBounds(88, 546, 122, 32);
		panel.add(lblCash);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalance.setFont(new Font("Montserrat", Font.PLAIN, 14));
		lblBalance.setBounds(88, 624, 122, 32);
		panel.add(lblBalance);
		
		JRadioButton rdbtnCash = new JRadioButton("Cash");
		rdbtnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtcash.setEditable(true);
			}
		});
		rdbtnCash.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnCash.setFont(new Font("Montserrat", Font.PLAIN, 13));
		rdbtnCash.setHorizontalTextPosition(SwingConstants.RIGHT);
		rdbtnCash.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnCash.setBounds(249, 518, 105, 21);
		panel.add(rdbtnCash);
		
		JRadioButton rdbtnCard = new JRadioButton("Card");
		rdbtnCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtcash.setEditable(false);
			}
		});
		rdbtnCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnCard.setHorizontalTextPosition(SwingConstants.RIGHT);
		rdbtnCard.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnCard.setFont(new Font("Montserrat", Font.PLAIN, 13));
		rdbtnCard.setBounds(397, 518, 105, 21);
		panel.add(rdbtnCard);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnCard);
		bg.add(rdbtnCash);
		
		
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				if(rdbtnCash.isSelected())
				{
					totaling();
					append("\n\n\n\t                   TOTAL COST: "+txttotalcost.getText());
					append("\n\n\t         CASH: "+Double.parseDouble(txtcash.getText())
					+"         BALANCE: "+txtbalance.getText()
					+"\n\n\t            ***********************************************************"
					+"\n\n\t           THANKS FOR VISITING"
					+"\n\n\t            ***********************************************************");
				}
				
				else
				{
					append("\n\n\n\t                   TOTAL COST: "+txttotalcost.getText());
					append("\n\n\t            ***********************************************************"
					+"\n\n\t           THANKS FOR VISITING"
					+"\n\n\t            ***********************************************************");
				}
				
				try 
				{
					boolean done = epbill.print();
					if(done)
					{
						JOptionPane.showMessageDialog(null, "Printing Done", "Result", JOptionPane.INFORMATION_MESSAGE);
						billHeader();
						delete();
					}
						
					
				} 
				catch (Exception e2)
				{
					JOptionPane.showMessageDialog(null, "Printing Failed", "Result", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnPrint.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setFont(new Font("Montserrat", Font.PLAIN, 13));
		btnPrint.setBackground(new Color(59, 89, 182));
		btnPrint.setBounds(896, 621, 127, 38);
		panel.add(btnPrint);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Itemname.isEmpty())
					JOptionPane.showMessageDialog(null, "No previous Record exists", "Alert", JOptionPane.ERROR_MESSAGE);
				
				else
				{
					int last = Itemname.size();
					int quant = Integer.parseInt(Quantity.get(last-1));
					String lastname= Itemname.get(last-1);
					try
					{
						con = Connector.DBcon();
						String query = "update Items set IT_quantity=IT_quantity+? where IT_name=?";
						PreparedStatement pst = con.prepareStatement(query);
						pst.setString(1, quant+"");
						pst.setString(2, lastname);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Last Item Deleted", "Alert", JOptionPane.PLAIN_MESSAGE);
					} 
					catch (Exception e2)
					{
						JOptionPane.showMessageDialog(null, e2);
					}
					
					Double newcost = Double.parseDouble(txttotalcost.getText())-Double.parseDouble(Subtotal.get(last-1));
					txttotalcost.setText(newcost+"");
					Itemname.remove(last-1);
					Quantity.remove(last-1);
					Price.remove(last-1);
					Subtotal.remove(last-1);
					
					
					try 
					{
						Document doc = epbill.getDocument();
						String content = doc.getText(0, epbill.getDocument().getLength());
						int lastLineBreak = content.lastIndexOf('\n');
						doc.remove(lastLineBreak,doc.getLength()-lastLineBreak);
					} 
					catch (BadLocationException e1) 
					{
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Montserrat", Font.PLAIN, 13));
		btnDelete.setBackground(new Color(59, 89, 182));
		btnDelete.setBounds(310, 377, 127, 38);
		panel.add(btnDelete);
		
		epbill = new JTextPane();
		epbill.setFont(new Font("Open Sans", Font.PLAIN, 13));
		epbill.setBorder(new LineBorder(new Color(0, 0, 0)));
		epbill.setEditable(false);
		epbill.setBounds(751, 88, 382, 490);
		panel.add(epbill);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Montserrat", Font.PLAIN, 13));
		btnClear.setBackground(new Color(59, 89, 182));
		btnClear.setBounds(524, 377, 127, 38);
		panel.add(btnClear);
		
		JPanel panel_bar = new JPanel();
		panel_bar.setLayout(null);
		panel_bar.setBackground(new Color(59, 89, 182));
		panel_bar.setBounds(0, 0, 1200, 62);
		panel.add(panel_bar);
		
		JPanel panel_X = new JPanel();
		panel_X.setLayout(null);
		panel_X.setBorder(null);
		panel_X.setBackground(new Color(59, 89, 182));
		panel_X.setBounds(1161, 0, 39, 38);
		panel_bar.add(panel_X);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int c=JOptionPane.showConfirmDialog(null, "Are you sure you want to exit", "EXIT", JOptionPane.YES_NO_OPTION);
				if(c==JOptionPane.YES_OPTION)
					dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_X.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_X.setBackground(new Color(59, 89, 182));
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Montserrat", Font.BOLD, 16));
		lblX.setBorder(null);
		lblX.setBackground(new Color(255, 250, 250));
		lblX.setBounds(0, 0, 39, 38);
		panel_X.add(lblX);
		
		JLabel lblNewLabel = new JLabel("Billing   System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Open Sans", Font.BOLD, 35));
		lblNewLabel.setBounds(380, 0, 402, 59);
		panel_bar.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 50, 62);
		panel_bar.add(menuBar);
		
		JMenu menu = new JMenu();
		Image img = new ImageIcon(this.getClass().getResource("menu.png")).getImage();
		menu.setIcon(new ImageIcon(img));
		
		menuBar.add(menu);
		
		JMenuItem mntmLogOut = new JMenuItem("Log Out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int c=JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "LOG OUT", JOptionPane.YES_NO_OPTION);
				if(c==JOptionPane.YES_OPTION)
				{
					dispose();
					new Login().setVisible(true);
				}
			}
		});
		Image img1 = new ImageIcon(this.getClass().getResource("logout.png")).getImage();
		mntmLogOut.setIcon(new ImageIcon(img1));
		mntmLogOut.setFont(new Font("Open Sans", Font.PLAIN, 14));
		menu.add(mntmLogOut);
		
		
		
		billHeader();
	}
}
