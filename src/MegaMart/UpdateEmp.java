package MegaMart;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateEmp extends JDialog {
	private JTable table;
	private JTextField txtfname;
	private JTextField txtlname;
	private JTextField txteid;
	private JTextField txtpass;
	private JPanel contentPanel;
	private final DefaultTableModel tableModel = new DefaultTableModel();
	
	
	/**
	 * Create the dialog.
	 */
	protected UpdateEmp(Dashboard a, String t, boolean m) {
		super(a, t, m);
		setBounds(230, 20, 1050, 750);
		getContentPane().setLayout(null);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(245, 245, 245));
		contentPanel.setBounds(0, 0, 1036, 703);
		getContentPane().add(contentPanel);

		String[] list = {"Marketing", "Product Management", "Maintenance"};
		JComboBox combob = new JComboBox(list);
		combob.setFont(new Font("Open Sans", Font.PLAIN, 14));
		combob.setBounds(79, 474, 236, 39);
		contentPanel.add(combob);
		
		
		
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				txtfname.setText(table.getValueAt(i, 0).toString());
				txtlname.setText(table.getValueAt(i, 1).toString());
				txteid.setText(table.getValueAt(i, 2).toString());
				txtpass.setText(table.getValueAt(i, 3).toString());
				
				String dep=table.getValueAt(i, 4).toString();
				
				combob.setSelectedItem(dep.equals("Marketing")? dep: dep.equals("Product Management")? dep: "Maintenance");
				
			}
		});
		table.setFont(new Font("Open Sans", Font.PLAIN, 13));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(479, 34, 531, 570);
		
		contentPanel.add(table);
		
		
		new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                loadData();
                return null;
            }
        }.execute();
		
         
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setHorizontalTextPosition(SwingConstants.LEFT);
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstName.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblFirstName.setBounds(79, 75, 105, 27);
		contentPanel.add(lblFirstName);
		
		txtfname = new JTextField();
		txtfname.setHorizontalAlignment(SwingConstants.CENTER);
		txtfname.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtfname.setColumns(10);
		txtfname.setBounds(79, 102, 236, 39);
		contentPanel.add(txtfname);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalTextPosition(SwingConstants.LEFT);
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblLastName.setBounds(79, 171, 105, 27);
		contentPanel.add(lblLastName);
		
		txtlname = new JTextField();
		txtlname.setHorizontalAlignment(SwingConstants.CENTER);
		txtlname.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtlname.setColumns(10);
		txtlname.setBounds(79, 199, 236, 39);
		contentPanel.add(txtlname);
		
		JLabel lblEmployeeId = new JLabel("Employee Id");
		lblEmployeeId.setHorizontalTextPosition(SwingConstants.LEFT);
		lblEmployeeId.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmployeeId.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblEmployeeId.setBounds(79, 273, 105, 27);
		contentPanel.add(lblEmployeeId);
		
		txteid = new JTextField();
		txteid.setHorizontalAlignment(SwingConstants.CENTER);
		txteid.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txteid.setColumns(10);
		txteid.setBounds(79, 300, 236, 39);
		contentPanel.add(txteid);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalTextPosition(SwingConstants.LEFT);
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblPassword.setBounds(79, 362, 105, 27);
		contentPanel.add(lblPassword);
		
		txtpass = new JTextField();
		txtpass.setHorizontalAlignment(SwingConstants.CENTER);
		txtpass.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtpass.setColumns(10);
		txtpass.setBounds(79, 389, 236, 39);
		contentPanel.add(txtpass);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(combob);
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Open Sans", Font.PLAIN, 24));
		btnUpdate.setBackground(new Color(59, 89, 182));
		btnUpdate.setBounds(64, 617, 158, 62);
		contentPanel.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Open Sans", Font.PLAIN, 24));
		btnClear.setBackground(new Color(59, 89, 182));
		btnClear.setBounds(292, 617, 158, 62);
		contentPanel.add(btnClear);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadData();
			}
		});
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Open Sans", Font.PLAIN, 24));
		btnRefresh.setBackground(new Color(59, 89, 182));
		btnRefresh.setBounds(514, 617, 158, 62);
		contentPanel.add(btnRefresh);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Open Sans", Font.PLAIN, 24));
		btnDelete.setBackground(new Color(59, 89, 182));
		btnDelete.setBounds(736, 617, 158, 62);
		contentPanel.add(btnDelete);
		
		JLabel lblName = new JLabel("First Name");
		lblName.setHorizontalTextPosition(SwingConstants.LEFT);
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblName.setBounds(479, 10, 81, 27);
		contentPanel.add(lblName);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalTextPosition(SwingConstants.LEFT);
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblId.setBounds(736, 10, 36, 27);
		contentPanel.add(lblId);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setHorizontalTextPosition(SwingConstants.LEFT);
		lblPassword_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword_1.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblPassword_1.setBounds(798, 10, 74, 27);
		contentPanel.add(lblPassword_1);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartment.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblDepartment.setBounds(905, 10, 89, 27);
		contentPanel.add(lblDepartment);
		
		JLabel lblLastName_1 = new JLabel("Last Name");
		lblLastName_1.setHorizontalTextPosition(SwingConstants.LEFT);
		lblLastName_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName_1.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblLastName_1.setBounds(591, 10, 81, 27);
		contentPanel.add(lblLastName_1);
	}

	/**
	 * This function loads data from database and displays it in the JTable
	 * 
	 * */
	private void loadData()
	{
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("First Name");
		columnNames.add("Last Name");
		columnNames.add("ID");
		columnNames.add("Password");
		columnNames.add("Department");
		
		Vector<Vector<Object>> data= new Vector<Vector<Object>>();
		
		ResultSet rs = null;
		try 
		{
			Connection con = Connector.DBcon();
			String query="select * from employee natural join department order by department.d_name";
			PreparedStatement pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= 9; columnIndex++)
		        {
		        	
		        	vector.add(rs.getObject(1));
		        	vector.add(rs.getObject(2));
		            vector.add(rs.getObject(3));
		            vector.add(rs.getObject(4));
		            vector.add(rs.getObject(6));
		            
		        }
		        data.add(vector);
			}
			
			 tableModel.setDataVector(data, columnNames);
			 
			 rs.close();
			 pst.close();
			 con.close();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
		
	}
	
	/**
	 * This Function clears the JTextFields
	 * 
	 * */
	private void clear()
	{
		Component[] comp = contentPanel.getComponents();
		for(Component c:comp)
		{
			if(c instanceof JTextField)
				((JTextField)c).setText("");
		}
		
	}
	
	
	/**
	 * This function checks if all the JTextFields are filled
	 * 
	 * */
	private boolean check()
	{
		Component[] comp = contentPanel.getComponents();
		int check=0;
		for(Component c:comp)
		{
			if(c instanceof JTextField)
			{
				if(((JTextField)c).getText().trim().equals(""))
					check++;
			}
				
		}
		
		return check==0 ? true : false;
	}
	
	
	/**
	 * This Function updates the database with user Input values
	 * 
	 * */
	private void update(JComboBox combo)
	{
		if(check() && table.getSelectedRow() != -1)
		{
			try
			{
				Connection con = Connector.DBcon();
				
				
					String query="update employee set fname=?, lname=?, E_ID=?, Password=?, D_num=? where E_ID=?";
					PreparedStatement pst = con.prepareStatement(query);
					
					pst.setString(1, txtfname.getText().trim());
					pst.setString(2, txtlname.getText().trim());
					pst.setString(3, txteid.getText().trim());
					pst.setString(4, txtpass.getText().trim());
					
					String dep_name = combo.getSelectedItem().toString();
					int dep_num = dep_name.equals("Marketing") ? 1065 : dep_name.equals("Product Management") ? 1066 : 1067;
					pst.setString(5, dep_num+"");
					
					pst.setString(6, txteid.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Updated Successfully", "RESULT", JOptionPane.INFORMATION_MESSAGE);
					loadData();
					
					pst.close();
					con.close();
				
				
			} 
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, e);
			}
		}
		
		else if(check() && table.getSelectedRow() == -1)
			JOptionPane.showMessageDialog(null, "Select the field to be updated!", "Alert", JOptionPane.ERROR_MESSAGE);
		
		else
			JOptionPane.showMessageDialog(null, "Fill all fields!", "Alert", JOptionPane.ERROR_MESSAGE);
	}
	
	
	/**
	 * This Function deletes the selected record from the database
	 * 
	 * */
	
	private void delete()
	{
		try
		{
			Connection con = Connector.DBcon();
			String query = "select * from Employee where E_ID=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, txteid.getText());
			ResultSet rs = pst.executeQuery();
			
			int count = 0;
			while(rs.next())
			{
				count++;
			}
			
			if(count == 1)
			{
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete selected employee data?", "ALERT", JOptionPane.YES_NO_OPTION);
				if(choice==JOptionPane.YES_OPTION)
				{
					query="delete from employee where E_ID=?";
					pst = con.prepareStatement(query);
					
					pst.setString(1, txteid.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Deleted Successfully", "RESULT", JOptionPane.INFORMATION_MESSAGE);
				}
				rs.close();
				pst.close();
				con.close();
				
			}
			
			else
				JOptionPane.showMessageDialog(null, "Record Not Found!", "Alert", JOptionPane.ERROR_MESSAGE);
			
			rs.close();
			pst.close();
			con.close();
			
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
}
