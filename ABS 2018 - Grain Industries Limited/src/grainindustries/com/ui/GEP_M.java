package grainindustries.com.ui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import grainindustries.com.db.models.GEP_DB2JTable_Payment;
import grainindustries.com.db.models.GEP_Driver_Obj;
import grainindustries.com.db.models.GEP_Transction_Obj;
import grainindustries.com.main.LoginUI;
import grainindustries.com.mui.GEP_E2JM_MPESA;
import grainindustries.com.mui.GEP_E2JM_xls;
import grainindustries.com.mui.GEP_E2JM_xlsx;
import grainindustries.com.mui.GEP_E2J_Bankingsxlsx;
import grainindustries.com.mui.GEP_E2J_SAPxlsx;
import grainindustries.com.mui.GEP_E2J_VIVOxlsx;
import grainindustries.com.mui.GEP_Recon;
import pjp.akidamjaffar.config.GEP_Connection;
import pjp.akidamjaffar.config.GEP_Util;
import pjp.akidamjaffar.config.ReadImage;
import pjp.akidamjaffar.crud.GEP_CRUD;
import pjp.akidamjaffar.crud.GEP_SELECT;
import pjp.akidamjaffar.crypto.GEP_Decoder;

public class GEP_M extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static JTextField infoTextField;

	private JPanel contentPane;
	private JTable tableBanking;
	private JTextField fieldFilePath;
	private JTextField textField;
	private TableRowSorter<TableModel> rowSorter;
	protected String dataSelection;
	private JTable tableAcknow;
	private TableRowSorter<TableModel> rowSorterTableAcknow;
	private JScrollPane scrollPane_1;
	private JButton btnExtractData;

	private JButton buttonTableB;

	private JButton btnReconcile;

	private JDateChooser acknowledgementDate;

	/**
	 * Create the frame.
	 */
	public GEP_M() {
		/*
		 * addMouseMotionListener(new MouseMotionAdapter() {
		 *
		 * @Override public void mouseMoved(MouseEvent arg0) {
		 * infoTextField.setText(null); infoTextField.setBackground(new Color(244, 164,
		 * 96)); driversId.setModel(new DefaultComboBoxModel<>(
		 * GEP_SalesDrivers.setGetSalesDriversTillVector(
		 * GEP_SELECT.getRecords(GEP_Connection.createConnection(infoTextField),
		 * GEP_SalesDrivers.TableName.TABLENAME, infoTextField)))); }
		 *
		 * @Override public void mouseDragged(MouseEvent arg0) {
		 * infoTextField.setText(null); infoTextField.setBackground(new Color(244, 164,
		 * 96)); driversId.setModel(new DefaultComboBoxModel<>(
		 * GEP_SalesDrivers.setGetSalesDriversTillVector(
		 * GEP_SELECT.getRecords(GEP_Connection.createConnection(infoTextField),
		 * GEP_SalesDrivers.TableName.TABLENAME, infoTextField)))); } });
		 */
		setResizable(false);
		setTitle("ABS - Automated Banking System - ALPHA V1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 750);

		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		final JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		final JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);

		final JMenuItem mntmExtract = new JMenuItem("Extract");
		mnFile.add(mntmExtract);

		final JMenu mnNewMenu = new JMenu("Modules");
		menuBar.add(mnNewMenu);

		final JMenuItem mntmUser = new JMenuItem("User");
		mntmUser.addActionListener(arg0 -> {
			final GEP_UserPanelUI dialog = new GEP_UserPanelUI();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setModal(true);
			dialog.setVisible(true);
		});
		mnNewMenu.add(mntmUser);

		final JMenuItem mntmDriver = new JMenuItem("Driver");
		mntmDriver.addActionListener(arg0 -> {
			final GEP_Drivers frame = new GEP_Drivers();
			frame.setSize(1100, 600);
			frame.setLocationRelativeTo(null);
			frame.setModal(true);
			frame.setVisible(true);
		});
		mnNewMenu.add(mntmDriver);

		final JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(arg0 -> {
			dispose();
			final JLabel backgroungImage_Label = ReadImage
					.readBackground_Image(new LoginUI().getClass().getSimpleName(), infoTextField);
			final LoginUI dialog = new LoginUI();
			dialog.setLocationRelativeTo(null);
			dialog.getContentPane().add(backgroungImage_Label);
			dialog.setVisible(true);
		});

		final JMenu reconMenu = new JMenu("Van Recon");
		mnNewMenu.add(reconMenu);

		final JMenuItem mntmVivoRecon = new JMenuItem("Vivo Recon");
		mntmVivoRecon.addActionListener(arg0 -> {
			setTitle("Vivo Inter Recon Module - ALPHA V1.0");
			scrollPane_1.setVisible(true);
			btnExtractData.setVisible(true);
			buttonTableB.setVisible(true);
			btnReconcile.setVisible(true);
		});
		reconMenu.add(mntmVivoRecon);

		final JMenuItem mntmBankRecon = new JMenuItem("Bank Recon");
		mntmBankRecon.addActionListener(arg0 -> {
			setTitle("Banking Reconciliation Module - ALPHA V1.0");
			scrollPane_1.setVisible(true);
			btnExtractData.setVisible(true);
			buttonTableB.setVisible(true);
			btnReconcile.setVisible(true);
		});
		reconMenu.add(mntmBankRecon);
		mnNewMenu.add(mntmLogOut);

		final JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(e -> dispose());
		mnNewMenu.add(mntmExit);

		final JMenu mnHelp = new JMenu("Help?");
		menuBar.add(mnHelp);

		final JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(244, 164, 96));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		final SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		infoTextField = new JTextField();
		infoTextField.setBackground(new Color(161, 137, 97));
		infoTextField.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, infoTextField, -20, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, infoTextField, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, infoTextField, -10, SpringLayout.EAST, contentPane);
		contentPane.add(infoTextField);

		final JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -416, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, infoTextField);
		contentPane.add(scrollPane);

		tableBanking = new JTable();
		tableBanking.addMouseListener(new MouseAdapter() {

			private GEP_Util<GEP_Transction_Obj> utilObj;

			@Override
			public void mouseClicked(MouseEvent arg0) {

				utilObj = new GEP_Util<>(GEP_Transction_Obj.class);

				if (tableBanking.getModel()
						.getValueAt(tableBanking.convertRowIndexToModel(tableBanking.getSelectedRow()),
								tableBanking.getSelectedColumn())
						.toString().equalsIgnoreCase("true"))
					saveGEP_MpesaSalesDataObj(
							GEP_Transction_Obj.GEP_Transction_ObjJtable.sgGEP_Transction_ObjFJ(tableBanking,
									infoTextField, tableBanking.convertRowIndexToModel(tableBanking.getSelectedRow())));
			}

			private void saveGEP_MpesaSalesDataObj(GEP_Transction_Obj transction_Obj) {
				try {
					new GEP_CRUD(transction_Obj, utilObj.getSessionFactory()).saveObject();

					JOptionPane.showMessageDialog(null,
							"Operation " + GEP_Decoder.decodeData(transction_Obj.getId()) + "@"
									+ GEP_Decoder.decodeData(transction_Obj.getRef()) + " Succeded Succesful",
									new GEP_M().getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
				} catch (final Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_M().getClass().getSimpleName(),
							JOptionPane.ERROR_MESSAGE);
				}
			}// TODO Auto-generated method stub
		});
		tableBanking.setBackground(Color.LIGHT_GRAY);
		tableBanking.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "#", "Date", "Details", "Cash In" }));
		scrollPane.setViewportView(tableBanking);

		fieldFilePath = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, fieldFilePath, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, fieldFilePath, 0, SpringLayout.EAST, infoTextField);
		fieldFilePath.setColumns(20);
		fieldFilePath.setEditable(false);
		contentPane.add(fieldFilePath);

		final JButton btnFindFile = new JButton("Browse File");
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 7, SpringLayout.SOUTH, btnFindFile);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnFindFile, 0, SpringLayout.EAST, infoTextField);
		btnFindFile.addActionListener(arg0 -> {
			final JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			final int result = chooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File select = chooser.getSelectedFile();
				fieldFilePath.setText(select.getAbsolutePath());

				if (select.getName().toLowerCase().endsWith(".xlsx")) {

					if (getTitle().equals("Banking Reconciliation Module - ALPHA V1.0")) {
						GEP_E2JM_xlsx.showExcel2Jtable(fieldFilePath.getText(), tableBanking, infoTextField);
						rowSoter();
					} else if (getTitle().equals("Vivo Inter Recon Module - ALPHA V1.0"))
						GEP_E2J_VIVOxlsx.showExcel2Jtable(fieldFilePath.getText(), tableBanking, infoTextField);
				}

				else if (select.getName().toLowerCase().endsWith(".xls")) {

					if (getTitle().equals("Vivo Inter Recon Module - ALPHA V1.0")) {

						final ResultSet setObj = GEP_SELECT.getRecords(GEP_Connection.createConnection(infoTextField),
								GEP_Driver_Obj.TableName.TABLENAME, infoTextField);

						try {
							while (setObj.next())
								if (GEP_Decoder.decodeData(setObj.getObject("sCode").toString())
										.equalsIgnoreCase(select.getName().split("_")[1]))
									GEP_E2JM_xls.showExcel2Jtable(fieldFilePath.getText(), tableBanking, infoTextField,
											setObj);
								else {
									infoTextField.setText("ECODE: Van Sale for " + select.getName().split("_")[1]
											+ " Not Recognised");
									infoTextField.setForeground(Color.RED);
								}
						} catch (final SQLException e) {
							JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_M().getClass().getSimpleName(),
									JOptionPane.ERROR_MESSAGE);
						}

					} 

					else if (getTitle().equals("Banking Reconciliation Module - ALPHA V1.0")) {
						GEP_E2JM_MPESA.showExcel2Jtable(fieldFilePath.getText(), tableBanking, infoTextField);
					}

				} else
					JOptionPane.showMessageDialog(null, "Please Select A Valid File Of Type \"*.xls\" or  \"*.xlsx\"",
							"File Extensivity Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		contentPane.add(btnFindFile);

		final JLabel lblSortByTill = new JLabel("Sort");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSortByTill, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblSortByTill, -28, SpringLayout.SOUTH, contentPane);
		contentPane.add(lblSortByTill);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					final String sortBy = textField.getText();

					if (sortBy.trim().length() == 0) {
						rowSorter.setRowFilter(null);
						rowSorterTableAcknow.setRowFilter(null);
					} else
						try {
							rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + sortBy));
							rowSorterTableAcknow.setRowFilter(RowFilter.regexFilter("(?i)" + sortBy));
						} catch (final Exception exception) {
							infoTextField
							.setText("We Cant Sort From Empty Table, Please Review Your Data Before Sorting");
							infoTextField.setForeground(Color.RED);
						}
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, -27, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 10, SpringLayout.EAST, lblSortByTill);
		textField.setColumns(10);
		contentPane.add(textField);

		btnExtractData = new JButton("Extract Data");
		sl_contentPane.putConstraint(SpringLayout.EAST, btnExtractData, 0, SpringLayout.EAST, infoTextField);
		btnExtractData.addActionListener(arg0 -> {
			if (getTitle().equals("Banking Reconciliation Module - ALPHA V1.0"))
				GEP_DB2JTable_Payment
				.readData(tableAcknow,
						GEP_Transction_Obj.GEP_Transction_ObjList
						.createList(
								GEP_SELECT.getRecords(GEP_Connection.createConnection(infoTextField),
										GEP_Transction_Obj.TableName.TABLENAME, infoTextField),
								infoTextField),
						infoTextField);
			else
				JOptionPane.showMessageDialog(null,
						"Vivo Recon Module Is Yet To Be Implemented For Fetching Data From DB, Kindly Use Browse Button Above To Upload Your Data",
						"Data Source Error", JOptionPane.ERROR_MESSAGE);
		});
		btnExtractData.setVisible(false);
		contentPane.add(btnExtractData);

		dataSelection = "All";

		final JSeparator separator = new JSeparator();
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnFindFile, 6, SpringLayout.SOUTH, separator);
		sl_contentPane.putConstraint(SpringLayout.NORTH, separator, 75, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, separator, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, separator, -10, SpringLayout.EAST, contentPane);
		contentPane.add(separator);

		scrollPane_1 = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnExtractData, 6, SpringLayout.SOUTH, scrollPane_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane_1, 312, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane_1, -104, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane_1, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane_1, -10, SpringLayout.EAST, contentPane);
		scrollPane_1.setVisible(false);
		contentPane.add(scrollPane_1);

		tableAcknow = new JTable();
		tableAcknow.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "#", "Date", "Sales Man Name", "Transaction Details", "Due Payment" }));
		tableAcknow.setBackground(new Color(240, 230, 140));
		scrollPane_1.setViewportView(tableAcknow);

		final JDayChooser dayChooser = new JDayChooser();
		scrollPane_1.setColumnHeaderView(dayChooser);

		buttonTableB = new JButton("Browse File");
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonTableB, 6, SpringLayout.SOUTH, scrollPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonTableB, 0, SpringLayout.EAST, infoTextField);
		buttonTableB.addActionListener(arg0 -> {
			final JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			final int result = chooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				final File select = chooser.getSelectedFile();

				if (select.getName().toLowerCase().endsWith(".xlsx")) {
					final String path = select.getAbsolutePath();
					fieldFilePath.setText(path);

					if (getTitle().equals("Banking Reconciliation Module - ALPHA V1.0")) {
						GEP_E2J_Bankingsxlsx.showExcel2Jtable(fieldFilePath.getText(), tableAcknow, infoTextField);
						rowSoter();
					} else if (getTitle().equals("Vivo Inter Recon Module - ALPHA V1.0"))
						GEP_E2J_SAPxlsx.showExcel2Jtable(fieldFilePath.getText(), tableAcknow, infoTextField);
				} else
					JOptionPane.showMessageDialog(null, "Please Select A Valid File Of Type \"*.xls\" or  \"*.xlsx\"",
							"File Extensivity Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		contentPane.add(buttonTableB);
		buttonTableB.setVisible(false);

		btnReconcile = new JButton("Reconcile");
		btnReconcile.addActionListener(arg0 -> GEP_Recon.reconcileData(tableAcknow, tableBanking, infoTextField));
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnReconcile, 0, SpringLayout.NORTH, btnExtractData);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnReconcile, 0, SpringLayout.WEST, infoTextField);
		contentPane.add(btnReconcile);

		acknowledgementDate = new JDateChooser();
		sl_contentPane.putConstraint(SpringLayout.NORTH, acknowledgementDate, 15, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, acknowledgementDate, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, acknowledgementDate, 139, SpringLayout.WEST, contentPane);
		contentPane.add(acknowledgementDate);
		acknowledgementDate.setDate(new Date());
	}

	protected void rowSoter() {
		// TODO Auto-generated method stub
		rowSorter = new TableRowSorter<>(tableBanking.getModel());
		tableBanking.setRowSorter(rowSorter);

		rowSorterTableAcknow = new TableRowSorter<>(tableAcknow.getModel());
		tableAcknow.setRowSorter(rowSorterTableAcknow);
	}
}