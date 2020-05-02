package MegaMart;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private JTextField txtfname;
	private JTextField txtlname;
	private JTextField txteid;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private Connection con;
	private JTextField txtiname;
	private JTextField txticode;
	private JTextField txtup;
	private JTextField txtis;
	private JPanel panel_addEmp;
	private JPanel panel_addInv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	protected Dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(170, 70, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JPanel parent_panel = new JPanel();
		parent_panel.setBounds(278, 88, 922, 612);
		contentPane.add(parent_panel);
		parent_panel.setLayout(new CardLayout(0, 0));
		
		JPanel panel_dash = new JPanel();
		panel_dash.setBackground(new Color(245, 245, 245));
		parent_panel.add(panel_dash, "name_55316585355200");
		panel_dash.setLayout(null);
		
		JPanel panel_update = new JPanel();
		panel_update.setBackground(new Color(245, 245, 245));
		parent_panel.add(panel_update, "name_8013952725800");
		panel_update.setLayout(null);

		panel_addInv = new JPanel();
		panel_addInv.setBackground(new Color(245, 245, 245));
		parent_panel.add(panel_addInv, "name_110633019899800");
		panel_addInv.setLayout(null);
		
		JLabel lblADD = new JLabel("");
		lblADD.setHorizontalAlignment(SwingConstants.CENTER);
		lblADD.setForeground(Color.WHITE);
		lblADD.setFont(new Font("Open Sans", Font.PLAIN, 33));
		lblADD.setBounds(36, 226, 291, 101);
		
		panel_addEmp = new JPanel();
		panel_addEmp.setBackground(new Color(245, 245, 245));
		parent_panel.add(panel_addEmp, "name_13629128585900");
		panel_addEmp.setLayout(null);
		JPanel panel_22 = new JPanel();
		panel_22.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(lblADD.getText().equals("Employee"))
					new UpdateEmp(Dashboard.this, "UPDATE FORM", true).setVisible(true);
				
				else
					new UpdateInv(Dashboard.this, "UPDATE FORM", true).setVisible(true);
			}
		});

		
		JPanel panel_21 = new JPanel();
		panel_21.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(lblADD.getText().equals("Employee"))
				{
					parent_panel.removeAll();
					parent_panel.add(panel_addEmp);
					parent_panel.repaint();
					parent_panel.revalidate();
				}
				else
				{
					parent_panel.removeAll();
					parent_panel.add(panel_addInv);
					parent_panel.repaint();
					parent_panel.revalidate();
				}
					
			}
		});
		panel_21.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_21.setBackground(new Color(147, 112, 219));
		panel_21.setBounds(73, 47, 355, 517);
		panel_update.add(panel_21);
		panel_21.setLayout(null);
		
		panel_21.add(lblADD);
		
		JLabel lblAddNewEmployee = new JLabel("Add   New");
		lblAddNewEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewEmployee.setFont(new Font("Open Sans", Font.PLAIN, 33));
		lblAddNewEmployee.setForeground(new Color(255, 255, 255));
		lblAddNewEmployee.setBounds(36, 178, 291, 101);
		panel_21.add(lblAddNewEmployee);
		
		panel_22.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_22.setBackground(new Color(0, 206, 209));
		panel_22.setBounds(495, 47, 355, 517);
		panel_update.add(panel_22);
		panel_22.setLayout(null);
		
		JLabel lblUpdateExisting = new JLabel("Update   Existing ");
		lblUpdateExisting.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateExisting.setForeground(Color.WHITE);
		lblUpdateExisting.setFont(new Font("Open Sans", Font.PLAIN, 33));
		lblUpdateExisting.setBounds(40, 173, 291, 102);
		panel_22.add(lblUpdateExisting);
		
		JLabel lblRCD = new JLabel("Records");
		lblRCD.setHorizontalAlignment(SwingConstants.CENTER);
		lblRCD.setForeground(Color.WHITE);
		lblRCD.setFont(new Font("Open Sans", Font.PLAIN, 33));
		lblRCD.setBounds(30, 210, 291, 113);
		panel_22.add(lblRCD);
		
		JPanel panel_emp = new JPanel();
		panel_emp.setBackground(new Color(245, 245, 245));
		parent_panel.add(panel_emp, "name_55612422690100");
		panel_emp.setLayout(null);
		
		JPanel panel_11 = new JPanel();
		panel_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String loc = "D:\\Java\\MegaMart\\Reports\\empInfo.jrxml";
				new ViewAll(loc).setVisible(true);
				
			}
		});
		panel_11.setBackground(new Color(233, 150, 122));
		panel_11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_11.setBounds(134, 226, 284, 86);
		panel_emp.add(panel_11);
		panel_11.setLayout(null);
		
		JLabel lblviewEmp = new JLabel("View All");
		
		lblviewEmp.setBounds(63, 10, 155, 66);
		panel_11.add(lblviewEmp);
		lblviewEmp.setHorizontalAlignment(SwingConstants.CENTER);
		lblviewEmp.setForeground(Color.WHITE);
		lblviewEmp.setFont(new Font("Open Sans", Font.PLAIN, 22));
		
		
		JPanel panel_12 = new JPanel();
		panel_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parent_panel.removeAll();
				parent_panel.add(panel_update);
				parent_panel.repaint();
				parent_panel.revalidate();
				lblADD.setText("Employee");
			}
		});
		panel_12.setBackground(new Color(238, 130, 238));
		panel_12.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_12.setBounds(543, 226, 284, 86);
		panel_emp.add(panel_12);
		panel_12.setLayout(null);
		
		JLabel lblUpdate = new JLabel("Update");
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setForeground(Color.WHITE);
		lblUpdate.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblUpdate.setBounds(64, 10, 155, 66);
		panel_12.add(lblUpdate);
		
		JPanel panel_inv = new JPanel();
		parent_panel.add(panel_inv, "name_58515503586100");
		panel_inv.setLayout(null);
		panel_inv.setBackground(new Color(245, 245, 245));
		
		JPanel panel_31 = new JPanel();
		panel_31.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String loc = "D:\\Java\\MegaMart\\Reports\\viewInve.jrxml";
    			new ViewAll(loc).setVisible(true);
			}
		});
		panel_31.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_31.setBackground(new Color(0, 206, 209));
		panel_31.setBounds(96, 191, 306, 91);
		panel_inv.add(panel_31);
		panel_31.setLayout(null);
		
		JLabel lblviewInv = new JLabel("View Inventory");
		lblviewInv.setForeground(new Color(255, 255, 255));
		lblviewInv.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblviewInv.setHorizontalAlignment(SwingConstants.CENTER);
		lblviewInv.setBounds(48, 22, 206, 59);
		panel_31.add(lblviewInv);
		
		JPanel panel_32 = new JPanel();
		panel_32.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parent_panel.removeAll();
				parent_panel.add(panel_update);
				parent_panel.repaint();
				parent_panel.revalidate();
				lblADD.setText("Inventory");
			}
		});
		panel_32.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_32.setLayout(null);
		panel_32.setBackground(new Color(0, 191, 255));
		panel_32.setBounds(516, 191, 306, 91);
		panel_inv.add(panel_32);
		
		JLabel lblupInv = new JLabel("Update Inventory");
		lblupInv.setHorizontalAlignment(SwingConstants.CENTER);
		lblupInv.setForeground(Color.WHITE);
		lblupInv.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblupInv.setBounds(48, 22, 206, 59);
		panel_32.add(lblupInv);
		
		JPanel panel_01 = new JPanel();
		panel_01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parent_panel.removeAll();
				parent_panel.add(panel_emp);
				parent_panel.repaint();
				parent_panel.revalidate();
			}
		});
		panel_01.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_01.setLayout(null);
		panel_01.setBackground(new Color(100, 149, 237));
		panel_01.setBounds(122, 155, 242, 79);
		panel_dash.add(panel_01);
		
		JLabel lblEmployee_1 = new JLabel("Employees");
		
		lblEmployee_1.setBounds(0, 0, 242, 77);
		panel_01.add(lblEmployee_1);
		lblEmployee_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployee_1.setForeground(Color.WHITE);
		lblEmployee_1.setFont(new Font("Open Sans", Font.PLAIN, 22));
		
		
		JPanel panel_02 = new JPanel();
		panel_02.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parent_panel.removeAll();
				parent_panel.add(panel_inv);
				parent_panel.repaint();
				parent_panel.revalidate();
			}
		});
		panel_02.setLayout(null);
		panel_02.setBackground(new Color(50, 205, 50));
		panel_02.setBounds(439, 155, 242, 79);
		panel_dash.add(panel_02);
		
		JLabel lblInventory_1 = new JLabel("Inventory");
		lblInventory_1.setBounds(28, 0, 189, 77);
		panel_02.add(lblInventory_1);
		lblInventory_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventory_1.setForeground(Color.WHITE);
		lblInventory_1.setFont(new Font("Open Sans", Font.PLAIN, 22));
		
		txtfname = new JTextField();
		txtfname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					txtlname.requestFocus();
				if(e.getKeyCode()==KeyEvent.VK_DOWN)
					txtlname.requestFocus();
			}
		});
		txtfname.setHorizontalAlignment(SwingConstants.CENTER);
		txtfname.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtfname.setBounds(142, 103, 236, 39);
		panel_addEmp.add(txtfname);
		txtfname.setColumns(10);
		
		txtlname = new JTextField();
		txtlname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					txteid.requestFocus();
				if(e.getKeyCode()==KeyEvent.VK_DOWN)
					txteid.requestFocus();
				if(e.getKeyCode()==KeyEvent.VK_UP)
					txtfname.requestFocus();
			}
		});
		txtlname.setHorizontalAlignment(SwingConstants.CENTER);
		txtlname.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtlname.setColumns(10);
		txtlname.setBounds(142, 200, 236, 39);
		panel_addEmp.add(txtlname);
		
		txteid = new JTextField();
		txteid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					passwordField1.requestFocus();
				if(e.getKeyCode()==KeyEvent.VK_DOWN)
					passwordField1.requestFocus();
				if(e.getKeyCode()==KeyEvent.VK_UP)
					txtlname.requestFocus();
			}
		});
		txteid.setHorizontalAlignment(SwingConstants.CENTER);
		txteid.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txteid.setColumns(10);
		txteid.setBounds(142, 301, 236, 39);
		panel_addEmp.add(txteid);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstName.setHorizontalTextPosition(SwingConstants.LEFT);
		lblFirstName.setBounds(142, 76, 105, 27);
		panel_addEmp.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalTextPosition(SwingConstants.LEFT);
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblLastName.setBounds(142, 172, 105, 27);
		panel_addEmp.add(lblLastName);
		
		JLabel lblEmployeeId = new JLabel("Employee Id");
		lblEmployeeId.setHorizontalTextPosition(SwingConstants.LEFT);
		lblEmployeeId.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmployeeId.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblEmployeeId.setBounds(142, 274, 105, 27);
		panel_addEmp.add(lblEmployeeId);
		
		passwordField1 = new JPasswordField();
		passwordField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					passwordField2.requestFocus();
				if(e.getKeyCode()==KeyEvent.VK_DOWN)
					passwordField2.requestFocus();
				if(e.getKeyCode()==KeyEvent.VK_UP)
					txteid.requestFocus();
			}
		});
		passwordField1.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField1.setFont(new Font("Open Sans", Font.PLAIN, 14));
		passwordField1.setBounds(606, 148, 236, 39);
		panel_addEmp.add(passwordField1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalTextPosition(SwingConstants.LEFT);
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblPassword.setBounds(606, 121, 142, 27);
		panel_addEmp.add(lblPassword);
		
		String[] list = {"Marketing", "Product Management", "Maintenance"};
		@SuppressWarnings("rawtypes")
		JComboBox combob= new JComboBox(list);
		combob.setFont(new Font("Open Sans", Font.PLAIN, 14));
		combob.setBounds(142, 390, 236, 39);
		panel_addEmp.add(combob);
		
		passwordField2 = new JPasswordField();
		passwordField2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					addEmployee(combob,panel_addEmp);
				if(e.getKeyCode()==KeyEvent.VK_UP)
					passwordField1.requestFocus();
			}

		});
		
		passwordField2.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField2.setFont(new Font("Open Sans", Font.PLAIN, 14));
		passwordField2.setBounds(606, 251, 236, 39);
		panel_addEmp.add(passwordField2);
		
		
		
		JLabel lblFirstName_1_1 = new JLabel("Re-Enter Password");
		lblFirstName_1_1.setHorizontalTextPosition(SwingConstants.LEFT);
		lblFirstName_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstName_1_1.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblFirstName_1_1.setBounds(606, 224, 142, 27);
		panel_addEmp.add(lblFirstName_1_1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEmployee(combob,panel_addEmp);
				}
		});
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setBackground(new Color(59, 89, 182));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Open Sans", Font.PLAIN, 24));
		btnAdd.setBounds(85, 493, 236, 62);
		panel_addEmp.add(btnAdd);
		
		JButton btnViewAll = new JButton("View All");
		btnViewAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loc = "D:\\Java\\Mega Mart\\Reports\\empInf.jrxml";
				new ViewAll(loc).setVisible(true);
			}
		});
		btnViewAll.setForeground(Color.WHITE);
		btnViewAll.setFont(new Font("Open Sans", Font.PLAIN, 24));
		btnViewAll.setBackground(new Color(59, 89, 182));
		btnViewAll.setBounds(659, 493, 236, 62);
		panel_addEmp.add(btnViewAll);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartment.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblDepartment.setBounds(142, 363, 105, 27);
		panel_addEmp.add(lblDepartment);
		
		JButton btnClear_1 = new JButton("Clear");
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAE(panel_addEmp);
			}
		});
		btnClear_1.setForeground(Color.WHITE);
		btnClear_1.setFont(new Font("Open Sans", Font.PLAIN, 24));
		btnClear_1.setBackground(new Color(59, 89, 182));
		btnClear_1.setBounds(376, 493, 236, 62);
		panel_addEmp.add(btnClear_1);
		
		txtiname = new JTextField();
		txtiname.setHorizontalAlignment(SwingConstants.CENTER);
		txtiname.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtiname.setColumns(10);
		txtiname.setBounds(134, 99, 236, 39);
		panel_addInv.add(txtiname);
		
		txticode = new JTextField();
		txticode.setHorizontalAlignment(SwingConstants.CENTER);
		txticode.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txticode.setColumns(10);
		txticode.setBounds(134, 196, 236, 39);
		panel_addInv.add(txticode);
		
		txtup = new JTextField();
		txtup.setHorizontalAlignment(SwingConstants.CENTER);
		txtup.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtup.setColumns(10);
		txtup.setBounds(530, 99, 236, 39);
		panel_addInv.add(txtup);
		
		String[] list1= {"Kitchen Essentials", "Fashion Store", "Kids Section", "Electronics", "Hardware"};
		JComboBox combob_1 = new JComboBox(list1);
		combob_1.setFont(new Font("Open Sans", Font.PLAIN, 14));
		combob_1.setBounds(325, 294, 236, 39);
		panel_addInv.add(combob_1);
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setHorizontalTextPosition(SwingConstants.LEFT);
		lblItemName.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemName.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblItemName.setBounds(134, 73, 105, 27);
		panel_addInv.add(lblItemName);
		
		JLabel lblItemCode = new JLabel("Item Code");
		lblItemCode.setHorizontalTextPosition(SwingConstants.LEFT);
		lblItemCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemCode.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblItemCode.setBounds(134, 169, 105, 27);
		panel_addInv.add(lblItemCode);
		
		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setHorizontalTextPosition(SwingConstants.LEFT);
		lblUnitPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnitPrice.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblUnitPrice.setBounds(530, 73, 105, 27);
		panel_addInv.add(lblUnitPrice);
		
		JLabel lblInitialStock = new JLabel("Initial Stock");
		lblInitialStock.setHorizontalTextPosition(SwingConstants.LEFT);
		lblInitialStock.setHorizontalAlignment(SwingConstants.LEFT);
		lblInitialStock.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblInitialStock.setBounds(530, 169, 105, 27);
		panel_addInv.add(lblInitialStock);
		
		txtis = new JTextField();
		txtis.setHorizontalAlignment(SwingConstants.CENTER);
		txtis.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtis.setColumns(10);
		txtis.setBounds(530, 196, 236, 39);
		panel_addInv.add(txtis);
		
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInventory(combob_1,panel_addInv);
			}
		});
		btnAdd_1.setForeground(Color.WHITE);
		btnAdd_1.setFont(new Font("Open Sans", Font.PLAIN, 24));
		btnAdd_1.setBackground(new Color(59, 89, 182));
		btnAdd_1.setBounds(51, 420, 236, 62);
		panel_addInv.add(btnAdd_1);
		
		JButton btnViewAll_1 = new JButton("View All");
		btnViewAll_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loc = "D:\\Java\\Mega Mart\\Reports\\ViewInv.jrxml";
					new ViewAll(loc).setVisible(true);
			}
		});
		btnViewAll_1.setForeground(Color.WHITE);
		btnViewAll_1.setFont(new Font("Open Sans", Font.PLAIN, 24));
		btnViewAll_1.setBackground(new Color(59, 89, 182));
		btnViewAll_1.setBounds(618, 420, 236, 62);
		panel_addInv.add(btnViewAll_1);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAE(panel_addInv);
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Open Sans", Font.PLAIN, 24));
		btnClear.setBackground(new Color(59, 89, 182));
		btnClear.setBounds(337, 420, 236, 62);
		panel_addInv.add(btnClear);
		
		JPanel panel_qacc = new JPanel();
		panel_qacc.setBackground(new Color(112, 128, 144));
		panel_qacc.setBounds(0, 88, 278, 612);
		contentPane.add(panel_qacc);
		panel_qacc.setLayout(null);
		
		JLabel lblEmployee = new JLabel("Employees");
		lblEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parent_panel.removeAll();
				parent_panel.add(panel_emp);
				parent_panel.repaint();
				parent_panel.revalidate();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEmployee.setForeground(new Color(135,206,235));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEmployee.setForeground(new Color(255, 255, 255));
			}
		});
		lblEmployee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEmployee.setForeground(new Color(255, 255, 255));
		lblEmployee.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmployee.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblEmployee.setBounds(109, 175, 131, 38);
		panel_qacc.add(lblEmployee);
		
		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parent_panel.removeAll();
				parent_panel.add(panel_inv);
				parent_panel.repaint();
				parent_panel.revalidate();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblInventory.setForeground(new Color(135,206,235));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblInventory.setForeground(new Color(255, 255, 255));
			}
		});
		lblInventory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblInventory.setHorizontalAlignment(SwingConstants.LEFT);
		lblInventory.setForeground(new Color(255, 255, 255));
		lblInventory.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblInventory.setBounds(109, 235, 131, 38);
		panel_qacc.add(lblInventory);
		
		JLabel lblinvico = new JLabel("");
		lblinvico.setHorizontalAlignment(SwingConstants.CENTER);
		lblinvico.setBounds(53, 235, 46, 38);
		Image img1 = new ImageIcon(this.getClass().getResource("cart.png")).getImage();
		lblinvico.setIcon(new ImageIcon(img1));
		panel_qacc.add(lblinvico);
		
		JLabel lblempico = new JLabel("");
		lblempico.setHorizontalAlignment(SwingConstants.CENTER);
		lblempico.setBounds(53, 175, 46, 38);
		Image img2 = new ImageIcon(this.getClass().getResource("news.png")).getImage();
		lblempico.setIcon(new ImageIcon(img2));
		panel_qacc.add(lblempico);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Open Sans", Font.BOLD, 28));
		lblWelcome.setBounds(42, 31, 198, 48);
		panel_qacc.add(lblWelcome);
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parent_panel.removeAll();
				parent_panel.add(panel_dash);
				parent_panel.repaint();
				parent_panel.revalidate();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblDashboard.setForeground(new Color(135,206,235));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblDashboard.setForeground(new Color(255, 255, 255));
			}
		});
		lblDashboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDashboard.setHorizontalAlignment(SwingConstants.LEFT);
		lblDashboard.setForeground(Color.WHITE);
		lblDashboard.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblDashboard.setBounds(109, 113, 131, 38);
		panel_qacc.add(lblDashboard);
		
		JLabel lbldashico = new JLabel("");
		lbldashico.setHorizontalAlignment(SwingConstants.CENTER);
		lbldashico.setBounds(53, 113, 46, 38);
		Image img3 = new ImageIcon(this.getClass().getResource("dashboard.png")).getImage();
		lbldashico.setIcon(new ImageIcon(img3));
		panel_qacc.add(lbldashico);
		
		JPanel panel_bar = new JPanel();
		panel_bar.setBackground(new Color(245, 245, 245));
		panel_bar.setBounds(0, 0, 1200, 88);
		contentPane.add(panel_bar);
		panel_bar.setLayout(null);
		
		JPanel panel_X = new JPanel();
		panel_X.setBounds(1168, 0, 32, 34);
		panel_bar.add(panel_X);
		panel_X.setBackground(new Color(245, 245, 245));
		panel_X.setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(0, 0, 32, 34);
		panel_X.add(lblX);
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
				lblX.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_X.setBackground(new Color(245, 245, 245));
				lblX.setForeground(Color.BLACK);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Montserrat", Font.BOLD, 17));
		
		JMenuBar menuBar = new JMenuBar();
		
		menuBar.setBackground(new Color(245, 245, 245));
		menuBar.setBounds(0, 0, 63, 88);
		panel_bar.add(menuBar);
		
		JMenu menu = new JMenu();
		menu.setFont(new Font("Open Sans", Font.PLAIN, 16));
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
		Image img4 = new ImageIcon(this.getClass().getResource("logout.png")).getImage();
		mntmLogOut.setIcon(new ImageIcon(img4));
		mntmLogOut.setFont(new Font("Open Sans", Font.PLAIN, 14));
		menu.add(mntmLogOut);
		
		setUndecorated(true);
	}
	
	
	/**
	 * This function adds the user Input values to the Employee table SQLite database
	 * @param : JComboBox which gives the Department Name
	 * @param : JPanel which contains the components
	 * 
	 * */
	private void addEmployee(JComboBox comboBox,JPanel p)
	{

		
		if(check(p))
		{
			if(new String(passwordField1.getPassword()).equals(new String(passwordField2.getPassword())))
			{
				
				if(new String(passwordField1.getPassword()).length()<4)
					JOptionPane.showMessageDialog(null, "Password must be atleast 4 characters long", "Alert", JOptionPane.ERROR_MESSAGE);
				
				else
				{
					con = Connector.DBcon();
					String query = "insert into Employee(fname,lname,E_ID,password,D_num) values(?,?,?,?,?)";
						try
						{
							PreparedStatement pst=con.prepareStatement(query);
							pst.setString(1, txtfname.getText());
							pst.setString(2, txtlname.getText());
							pst.setString(3, txteid.getText());
							
							pst.setString(4, new String(passwordField1.getPassword()));
							if((String)comboBox.getSelectedItem()=="Marketing")
								pst.setString(5, "1065");
							else if((String)comboBox.getSelectedItem()=="Product Management")
								pst.setString(5, "1066");
							else
								pst.setString(5, "1067");
							
							pst.execute();
							
							JOptionPane.showMessageDialog(null, "Added Successfully", "Alert", JOptionPane.INFORMATION_MESSAGE);
							clearAE(panel_addEmp);
							
							pst.close();
							con.close();
						} 
					
						catch (Exception e1)
						{
							if(e1.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (UNIQUE constraint failed: Employee.E_ID)"))
							{
								JOptionPane.showMessageDialog(null, "Employee ID already exists, enter new ID", "Alert", JOptionPane.ERROR_MESSAGE);
								txteid.setText("");
								txteid.requestFocus();
								
							}
								
							else
								JOptionPane.showMessageDialog(null, e1.getMessage(), "Alert", JOptionPane.INFORMATION_MESSAGE);
						}
						
				}
			}
			
			
			else
			{
				JOptionPane.showMessageDialog(null, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
				passwordField1.setText("");
				passwordField2.setText("");
				passwordField1.requestFocus();
			}
		}
			
		
		else
		{
			JOptionPane.showMessageDialog(null, "All fields Are mandatory to be filled", "Alert", JOptionPane.ERROR_MESSAGE);
		}
		
			
	
	}
	
	
	/**
	 * This function checks if all the JTextFields are filled
	 * @param : The JPanel which contains the JTextFields
	 * 
	 * */
	protected boolean check(JPanel p)
	{
		Component[] comp = p.getComponents();
		int check=0;
		for(Component c:comp)
		{
			if(c instanceof JTextField)
			{
				if(((JTextField)c).getText().trim().equals(""))
					check++;
			}
				
		}
		
		return (check==0)?true:false;
	}

	
	/**
	 * This function adds the user Input values to the Items table in the SQLite database
	 * @param : JComboBox which gives the Section Name
	 * @param : JPanel which contains the components
	 * 
	 * */
	private void addInventory(JComboBox comboBox, JPanel p)
	{
		if(check(p))
		{
			con = Connector.DBcon();
			String query = "insert into Items(IT_name,IT_ID,IT_quantity,IT_price,S_ID) values(?,?,?,?,?)";
			try 
			{
				PreparedStatement pst=con.prepareStatement(query);
				pst.setString(1, txtiname.getText());
				pst.setString(2, txticode.getText());
				pst.setString(3, txtis.getText());
				pst.setString(4, txtup.getText());
				
				String sname = comboBox.getSelectedItem().toString();
				int snum = sname.equals("Kitchen Essentials")? 347: sname.equals("Fashion Store")? 357: sname.equals("Kids Section")? 367: sname.equals("Electronics")? 377 : 387;
				pst.setString(5, snum+"");
				
				pst.execute();

				JOptionPane.showMessageDialog(null, "Added Successfully", "Alert", JOptionPane.INFORMATION_MESSAGE);
				clearAE(panel_addInv);
				
				pst.close();
				con.close();
			}

			catch (Exception e) 
			{
				if(e.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (UNIQUE constraint failed: Items.IT_ID)"))
				{
					JOptionPane.showMessageDialog(null, "Item ID already exists, enter new ID", "Alert", JOptionPane.ERROR_MESSAGE);
					txteid.setText("");
					txteid.requestFocus();
					
				}
					
				else
					JOptionPane.showMessageDialog(null, e.getMessage(), "Alert", JOptionPane.INFORMATION_MESSAGE);
			}
				
		}
			
		else
			JOptionPane.showMessageDialog(null, "All Fields Are Mandatory to be filled", "Alert", JOptionPane.ERROR_MESSAGE);
		
		}
	
	/**
	 * This function clears all JTextFields from a panel
	 * @param : panel to be cleared
	 * 
	 * */
	private void clearAE(JPanel p)
	{
		Component[] comp = p.getComponents();
		for(Component c:comp)
		{
			if(c instanceof JTextField)
			{
				((JTextField) c).setText("");
			}
				
		}
		
	}
}
