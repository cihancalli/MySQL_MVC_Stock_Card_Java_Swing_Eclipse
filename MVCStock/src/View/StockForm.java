package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Service;
import Controller.ServiceJdbc;
import Model.Stock;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StockForm {

	private JFrame frame;
	private JTextField txt_Stock_Code;
	private JTextField txt_Stock_Name;
	private JTextField txt_Barcode;
	private JTextField txt_Explanation;
	private JTextField txt_Search_Stock_Code;
	
	private JFormattedTextField FtxtF_Create_Date;
	
	private JPanel pnl_save;
	private JPanel pnl_search;
	private JPanel pnl_control;
	private JScrollPane scrollPane;
	private JTable table;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
	private ServiceJdbc service= new ServiceJdbc();
	private DefaultTableModel model;
	
	private JLabel lbl_Title;
	private JLabel lbl_Stock_Code;
	private JLabel lbl_Stock_Name;
	private JLabel lbl_Stock_Type;
	private JLabel lbl_Unit;
	private JLabel lbl_Barcode;
	private JLabel lbl_KDV;
	private JLabel lbl_Explanation;
	private JLabel lbl_Create_Date;
	private JLabel lbl_Search_Stock_Code;
	
	private JButton btn_Save;
	private JButton btn_Update;
	private JButton btn_Clear;
	private JButton btn_Delete;
	private JButton btn_Exit;
	
	private JComboBox cbox_StockType;
	private JComboBox cbox_Unit;
	private JComboBox cbox_KDV;

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockForm window = new StockForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StockForm() {
		initialize();
		showData(null);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		// J PANEL
		
		pnl_save = new JPanel();
		pnl_save.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "STOCK REGISTRATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnl_save.setBounds(20, 100, 310, 320);
		frame.getContentPane().add(pnl_save);
		pnl_save.setLayout(null);
		
		pnl_search = new JPanel();
		pnl_search.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "STOCK SEARCH", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnl_search.setBounds(20, 430, 310, 130);
		frame.getContentPane().add(pnl_search);
		pnl_search.setLayout(null);
		
		pnl_control = new JPanel();
		pnl_control.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "CONTROL", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnl_control.setBounds(350, 430, 530, 130);
		frame.getContentPane().add(pnl_control);
		pnl_control.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(350, 107, 526, 310);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int iData = table.getSelectedRow();
				SetData(table.getValueAt(iData, 0),
						table.getValueAt(iData, 0),
						table.getValueAt(iData, 1),
						table.getValueAt(iData, 2),
						table.getValueAt(iData, 3),
						table.getValueAt(iData, 4),
						table.getValueAt(iData, 5),
						table.getValueAt(iData, 6),
						table.getValueAt(iData, 7));
			}
		});
		scrollPane.setViewportView(table);
		
		// J LABEL
		
		lbl_Title = new JLabel("MVC STOCK CARD");
		lbl_Title.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		lbl_Title.setBounds(315, 35, 290, 30);
		frame.getContentPane().add(lbl_Title);
		
		lbl_Stock_Code = new JLabel("Stock Code:");
		lbl_Stock_Code.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lbl_Stock_Code.setBounds(20, 30, 100, 25);
		pnl_save.add(lbl_Stock_Code);
		
		lbl_Stock_Name = new JLabel("Stock Name:");
		lbl_Stock_Name.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lbl_Stock_Name.setBounds(20, 65, 100, 25);
		pnl_save.add(lbl_Stock_Name);
		
		lbl_Stock_Type = new JLabel("Stock Type:");
		lbl_Stock_Type.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lbl_Stock_Type.setBounds(20, 100, 100, 25);
		pnl_save.add(lbl_Stock_Type);
		
		lbl_Unit = new JLabel("Unit of:");
		lbl_Unit.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lbl_Unit.setBounds(20, 135, 100, 25);
		pnl_save.add(lbl_Unit);
		
		lbl_Barcode = new JLabel("Barcode:");
		lbl_Barcode.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lbl_Barcode.setBounds(20, 170, 100, 25);
		pnl_save.add(lbl_Barcode);
		
		lbl_KDV = new JLabel("TAX Type:");
		lbl_KDV.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lbl_KDV.setBounds(20, 205, 100, 25);
		pnl_save.add(lbl_KDV);
		
		lbl_Explanation = new JLabel("Description:");
		lbl_Explanation.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lbl_Explanation.setBounds(20, 240, 100, 25);
		pnl_save.add(lbl_Explanation);
		
		lbl_Create_Date = new JLabel("Creat Date:");
		lbl_Create_Date.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lbl_Create_Date.setBounds(20, 275, 100, 25);
		pnl_save.add(lbl_Create_Date);
		
		lbl_Search_Stock_Code = new JLabel("Stock Code:");
		lbl_Search_Stock_Code.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lbl_Search_Stock_Code.setBounds(20, 53, 100, 25);
		pnl_search.add(lbl_Search_Stock_Code);
		
		//J TEXT FIELD
		
		txt_Stock_Code = new JTextField();
		txt_Stock_Code.setBounds(130, 30, 160, 25);
		pnl_save.add(txt_Stock_Code);
		txt_Stock_Code.setColumns(10);
		
		txt_Stock_Name = new JTextField();
		txt_Stock_Name.setColumns(10);
		txt_Stock_Name.setBounds(130, 65, 160, 25);
		pnl_save.add(txt_Stock_Name);
		
		txt_Barcode = new JTextField();
		txt_Barcode.setColumns(10);
		txt_Barcode.setBounds(130, 170, 160, 25);
		pnl_save.add(txt_Barcode);
		
		txt_Explanation = new JTextField();
		txt_Explanation.setColumns(10);
		txt_Explanation.setBounds(130, 240, 160, 25);
		pnl_save.add(txt_Explanation);
		
		txt_Search_Stock_Code = new JTextField();
		txt_Search_Stock_Code.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			showData(txt_Search_Stock_Code.getText());
	
				
			}
		});
		txt_Search_Stock_Code.setColumns(10);
		txt_Search_Stock_Code.setBounds(130, 53, 160, 25);
		pnl_search.add(txt_Search_Stock_Code);
		
		// J FORMATTED TEXT FIELD
		
		FtxtF_Create_Date = new JFormattedTextField();
		FtxtF_Create_Date.setBounds(130, 275, 160, 25);
		pnl_save.add(FtxtF_Create_Date);
		
		// J COMBO BOX
		
		cbox_StockType = new JComboBox();
		cbox_StockType.setModel(new DefaultComboBoxModel(new String[] {"Physically", "Service", "Basis", "Source"}));
		cbox_StockType.setBounds(130, 100, 160, 25);
		pnl_save.add(cbox_StockType);
		
	    cbox_Unit = new JComboBox();
		cbox_Unit.setModel(new DefaultComboBoxModel(new String[] {"Warehouse", "Production", "Office"}));
		cbox_Unit.setBounds(130, 135, 160, 25);
		pnl_save.add(cbox_Unit);
		
		cbox_KDV = new JComboBox();
		cbox_KDV.setModel(new DefaultComboBoxModel(new String[] {"1.0", "8.0", "18.0"}));
		cbox_KDV.setBounds(130, 205, 160, 25);
		pnl_save.add(cbox_KDV);
		
		// J BUTTON 
		
		btn_Save = new JButton("CREATE");
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.save(getData());
				SetData("","","",0,"","","","","");
				showData(null);
			}
		});
		btn_Save.setBounds(20, 20, 160, 40);
		pnl_control.add(btn_Save);
		
		btn_Update = new JButton("UPDATE");
		btn_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.update(getData());
				SetData("","","",0,"","","","","");
				showData(null);
			}
		});
		btn_Update.setBounds(20, 70, 160, 40);
		pnl_control.add(btn_Update);
		
		btn_Clear = new JButton("CLEAR");
		btn_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetData("","","",0,"","","","","");
				showData(null);
			}
		});
		btn_Clear.setBounds(190, 20, 160, 40);
		pnl_control.add(btn_Clear);
		
		btn_Delete = new JButton("DELETE");
		btn_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.delete(getData());
				SetData("","","",0,"","","","","");
				showData(null);
			}
		});
		btn_Delete.setBounds(190, 70, 160, 40);
		pnl_control.add(btn_Delete);
		
		btn_Exit = new JButton("EXIT");
		btn_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn_Exit.setBounds(360, 20, 150, 90);
		pnl_control.add(btn_Exit);
		
		
	}


	protected Stock getData() {
		Stock  stock = new Stock();
		stock.setStockCode(txt_Stock_Code.getText());
		stock.setStockName(txt_Stock_Name.getText());
		stock.setStockType(cbox_StockType.getSelectedIndex());
		stock.setUnit(cbox_Unit.getSelectedItem().toString());
		stock.setBarcode(txt_Barcode.getText());
		stock.setKDV(Double.parseDouble(cbox_KDV.getSelectedItem().toString()));
		stock.setExplanation(txt_Explanation.getText());
		stock.setCreateDate(FtxtF_Create_Date.getText());
		
		return stock;
	}

	private void SetData(	Object _searchstockcode, 
							Object _stockcode, 
							Object _stockname, 
							Object _stocktype, 
							Object _unit, 
							Object _barcode, 
							Object _kdv, 
							Object _explanation, 
							Object _createdate) 
	{
		txt_Search_Stock_Code.setText(_searchstockcode.toString());
		txt_Stock_Code.setText(_stockcode.toString());
		txt_Stock_Name.setText(_stockname.toString());
		cbox_StockType.setSelectedIndex(Integer.parseInt(_stocktype.toString()));
		cbox_Unit.setSelectedItem(_unit.toString());
		txt_Barcode.setText(_barcode.toString());
		cbox_KDV.setSelectedItem(_kdv.toString());
		txt_Explanation.setText(_explanation.toString());
		FtxtF_Create_Date.setText(_createdate.toString());
	}

	private void showData(String Stock_Code) {
		Object[] column = {"","","","","","","",""};
		Object[][] data = new Object[service.getStocks(Stock_Code).size()][8];
		for (int i=0; i<service.getStocks(Stock_Code).size();i++) {
			Stock stock = service.getStocks(Stock_Code).get(i);
			Object[] arr_data = {stock.getStockCode(),stock.getStockName(),stock.getStockType(),stock.getUnit(),stock.getBarcode(),stock.getKDV(),stock.getExplanation(),stock.getCreateDate()};
			data[i] = arr_data;
		}
		model = new DefaultTableModel(data,column);
		table.setModel(model);
		
	}
}
