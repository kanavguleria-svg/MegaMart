package MegaMart;

import java.awt.BorderLayout;
import java.sql.Connection;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import java.awt.Color;

public class ViewAll extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	protected Connection con;
	
	/**
	 * Create the dialog.
	 */
	
	public ViewAll(String Loc) {
		setBounds(230, 20, 1050, 750);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 250, 240));
		panel.setBounds(0, 0, 1036, 713);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		/*
		 * The Code Below Loads and Displays the Jasper Report
		 */
		
		panel.removeAll();
		panel.repaint();
		panel.revalidate();
		
		try 
		{
			con = Connector.DBcon();
			JasperDesign design = JRXmlLoader.load(Loc);
			JasperReport report = JasperCompileManager.compileReport(design);
			JasperPrint print = JasperFillManager.fillReport(report, null, con);
			
			JRViewer view = new JRViewer(print);
			panel.add(view);
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
}

