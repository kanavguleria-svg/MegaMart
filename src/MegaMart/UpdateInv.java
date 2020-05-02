package MegaMart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdateInv extends JDialog {
	private JTable table;
	private JTextField txtiname;
	private JTextField txticode;
	private JTextField txtstock;
	private JTextField txtprice;
	private JPanel contentPanel;
	private final DefaultTableModel tableModel = new DefaultTableModel();
	
	
	
	/**
	 * Create the dialog.
	 */
	protected UpdateInv(Dashboard a, String t, boolean m) {
		super(a, t, m);
		setBounds(230, 20, 1050, 750);
		getContentPane().setLayout(null);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(245, 245, 245));
		contentPanel.setBounds(0, 0, 1036, 703);
		getContentPane().add(contentPanel);
		
		String[] list = {"Kitchen Essentials", "Fashion Store", "Kids Section", "Electronics", "Hardware"};
		JComboBox combob = new JComboBox(list);
		combob.setFont(new Font("Open Sans", Font.PLAIN, 14));
		combob.setBounds(79, 474, 236, 39);
		contentPanel.add(combob);
		
		table = new JTable(tableModel);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					selectLine(combob);
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectLine(combob);
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
		
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setHorizontalTextPosition(SwingConstants.LEFT);
		lblItemName.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemName.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblItemName.setBounds(79, 75, 105, 27);
		contentPanel.add(lblItemName);
		
		txtiname = new JTextField();
		txtiname.setHorizontalAlignment(SwingConstants.CENTER);
		txtiname.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtiname.setColumns(10);
		txtiname.setBounds(79, 102, 236, 39);
		contentPanel.add(txtiname);
		
		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setHorizontalTextPosition(SwingConstants.LEFT);
		lblItemId.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemId.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblItemId.setBounds(79, 171, 105, 27);
		contentPanel.add(lblItemId);
		
		txticode = new JTextField();
		txticode.setHorizontalAlignment(SwingConstants.CENTER);
		txticode.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txticode.setColumns(10);
		txticode.setBounds(79, 199, 236, 39);
		contentPanel.add(txticode);
		
		JLabel lblCurrentStock = new JLabel("Current Stock");
		lblCurrentStock.setHorizontalTextPosition(SwingConstants.LEFT);
		lblCurrentStock.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurrentStock.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblCurrentStock.setBounds(79, 273, 105, 27);
		contentPanel.add(lblCurrentStock);
		
		txtstock = new JTextField();
		txtstock.setHorizontalAlignment(SwingConstants.CENTER);
		txtstock.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtstock.setColumns(10);
		txtstock.setBounds(79, 300, 236, 39);
		contentPanel.add(txtstock);
		
		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setHorizontalTextPosition(SwingConstants.LEFT);
		lblUnitPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnitPrice.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblUnitPrice.setBounds(79, 362, 105, 27);
		contentPanel.add(lblUnitPrice);
		
		txtprice = new JTextField();
		txtprice.setHorizontalAlignment(SwingConstants.CENTER);
		txtprice.setFont(new Font("Open Sans", Font.PLAIN, 14));
		txtprice.setColumns(10);
		txtprice.setBounds(79, 389, 236, 39);
		contentPanel.add(txtprice);
		
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
		
		JLabel lblItemName_1 = new JLabel("Item Name");
		lblItemName_1.setHorizontalTextPosition(SwingConstants.LEFT);
		lblItemName_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemName_1.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblItemName_1.setBounds(479, 10, 89, 27);
		contentPanel.add(lblItemName_1);
		
		JLabel lblCurrentStock_1 = new JLabel("Stock");
		lblCurrentStock_1.setHorizontalTextPosition(SwingConstants.LEFT);
		lblCurrentStock_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurrentStock_1.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblCurrentStock_1.setBounds(722, 10, 50, 27);
		contentPanel.add(lblCurrentStock_1);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setHorizontalTextPosition(SwingConstants.LEFT);
		lblPassword_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword_1.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblPassword_1.setBounds(798, 10, 74, 27);
		contentPanel.add(lblPassword_1);
		
		JLabel lblUnitPrice_1 = new JLabel("Unit Price");
		lblUnitPrice_1.setHorizontalTextPosition(SwingConstants.LEFT);
		lblUnitPrice_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnitPrice_1.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblUnitPrice_1.setBounds(905, 10, 89, 27);
		contentPanel.add(lblUnitPrice_1);
		
		JLabel lblLastName_1 = new JLabel("Item ID");
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
		columnNames.add("Item Name");
		columnNames.add("Item ID");
		columnNames.add("Current Stock");
		columnNames.add("Unit Price");
		columnNames.add("Section");
		
		Vector<Vector<Object>> data= new Vector<Vector<Object>>();
		
		ResultSet rs = null;
		try 
		{
			Connection con = Connector.DBcon();
			String query="select * from items natural join sections order by sections.s_name";
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
				String query="update items set it_name=?, it_id=?, it_quantity=?, it_price=? where it_ID=?";
				PreparedStatement pst = con.prepareStatement(query);
				
				pst.setString(1, txtiname.getText().trim());
				pst.setString(2, txticode.getText().trim());
				pst.setString(3, txtstock.getText().trim());
				pst.setString(4, txtprice.getText().trim());
				
				String sec = combo.getSelectedItem().toString();
				int sec_num = sec.equals("Kitchen Essentials")? 347: sec.equals("Fashion Store")? 357: sec.equals("Kids Section")? 367: sec.equals("Electronics")? 377: 387;
				pst.setString(5, sec_num+"");
				
				pst.setString(6, txticode.getText());
				
				pst.execute();
				
				JOptionPane.showMessageDialog(null, "Updated Successfully", "RESULT", JOptionPane.INFORMATION_MESSAGE);
				
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
			String query = "select * from items where IT_ID=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, txticode.getText());
			ResultSet rs = pst.executeQuery();
			
			int count = 0;
			while(rs.next())
			{
				count++;
			}
			
			if(count == 1)
			{
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete selected item data?", "ALERT", JOptionPane.YES_NO_OPTION);
				if(choice==JOptionPane.YES_OPTION)
				{
					query="delete from items where IT_ID=?";
					pst = con.prepareStatement(query);
					
					pst.setString(1, txticode.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Deleted Successfully", "RESULT", JOptionPane.INFORMATION_MESSAGE);
				}
				
				loadData();
				
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
	
	/**
	 * This Function selects the selected record from the table
	 * 
	 * */
	private void selectLine(JComboBox combob)
	{
		int i = table.getSelectedRow();
		txtiname.setText(table.getValueAt(i, 0).toString());
		txticode.setText(table.getValueAt(i, 1).toString());
		txtstock.setText(table.getValueAt(i, 2).toString());
		txtprice.setText(table.getValueAt(i, 3).toString());
		
		String sec=table.getValueAt(i, 4).toString();
		
		combob.setSelectedItem(sec.equals("Kitchen Essentials")? sec: sec.equals("Fashion Store")? sec: sec.equals("Kids Section")? sec: sec.equals("Electronics")? sec: "Hardware");
	}
}
