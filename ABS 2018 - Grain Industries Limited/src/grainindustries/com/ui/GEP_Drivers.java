package grainindustries.com.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import grainindustries.com.db.models.GEP_DB2JTable_Drivers;
import grainindustries.com.db.models.GEP_Driver_Obj;
import grainindustries.com.db.userdata.OnSystemUser;
import pjp.akidamjaffar.config.GEP_Connection;
import pjp.akidamjaffar.config.GEP_Unique_Box;
import pjp.akidamjaffar.config.GEP_Util;
import pjp.akidamjaffar.crud.GEP_CRUD;
import pjp.akidamjaffar.crud.GEP_SELECT;
import pjp.akidamjaffar.crud.JdbcSelectDriver;
import pjp.akidamjaffar.crypto.GEP_Decoder;
import pjp.akidamjaffar.crypto.GEP_Encoder;

public class GEP_Drivers extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 4506121008230234111L;
	private JPanel contentPane;
	private JTable table;
	private JLabel Name;
	private JTextField drv_Name;
	private JTextField infoTextField;
	private JTextField drv_Till;
	private JTextField drv_sCode;

	/**
	 * Create the frame.
	 */
	public GEP_Drivers() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				infoTextField.setText(null);
				infoTextField.setBackground(new Color(244, 164, 96));
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				infoTextField.setText(null);
				infoTextField.setBackground(new Color(244, 164, 96));
			}
		});
		setBounds(100, 100, 1100, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(244, 164, 96));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		final SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		final JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 100, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, 400, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {

			private GEP_Util<GEP_Driver_Obj> utilObj;

			@Override
			public void mouseClicked(MouseEvent e) {
				utilObj = new GEP_Util<>(GEP_Driver_Obj.class);

				if (table.getModel()
						.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), table.getSelectedColumn())
						.toString().equalsIgnoreCase("true")) {

					List<GEP_Driver_Obj> driver_Objs = null;

					driver_Objs = GEP_Driver_Obj.GEP_User_DataListForUpdate.createList(
							JdbcSelectDriver.selectRecordFromTable(GEP_Connection.createConnection(infoTextField),
									GEP_Encoder.encodeData(table.getModel()
											.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 1)
											.toString())),
							infoTextField);

					for (final GEP_Driver_Obj gep_Driver_Obj : driver_Objs) {

						/*
						 * Refer to GEP_DB2JTable_Drivers
						 *
						 * column_name.addElement("#"); column_name.addElement("Id");
						 * column_name.addElement("Name"); column_name.addElement("Location");
						 * column_name.addElement("Creator"); column_name.addElement("Till");
						 * column_name.addElement("Short Code");
						 * column_name.addElement("Check To Update");
						 */

						gep_Driver_Obj.setName(GEP_Encoder.encodeData(table.getModel()
								.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 2).toString()));
						gep_Driver_Obj.setLocation(GEP_Encoder.encodeData(table.getModel()
								.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 3).toString()));
						gep_Driver_Obj.setCreator(GEP_Encoder.encodeData(OnSystemUser.getUserName()));
						gep_Driver_Obj.setTill(GEP_Encoder.encodeData(table.getModel()
								.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 5).toString()));
						gep_Driver_Obj.setsCode(GEP_Encoder.encodeData(table.getModel()
								.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 6).toString()));

						updateGEP_Driver_Obj(gep_Driver_Obj);
					}

				}

			}

			private void updateGEP_Driver_Obj(GEP_Driver_Obj driver_Obj) {
				try {

					new GEP_CRUD(driver_Obj, utilObj.getSessionFactory()).saveorupdateObject();

				} catch (final Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_M().getClass().getSimpleName(),
							JOptionPane.ERROR_MESSAGE);
				}
			}// TODO Auto-generated method stub
		});
		table.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "#", "Till NO", "Name", "Location" }));
		scrollPane.setViewportView(table);

		Name = new JLabel("Name");
		sl_contentPane.putConstraint(SpringLayout.NORTH, Name, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, Name, 10, SpringLayout.WEST, contentPane);
		contentPane.add(Name);

		drv_Name = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, drv_Name, -2, SpringLayout.NORTH, Name);
		sl_contentPane.putConstraint(SpringLayout.WEST, drv_Name, 6, SpringLayout.EAST, Name);
		sl_contentPane.putConstraint(SpringLayout.EAST, drv_Name, 166, SpringLayout.EAST, Name);
		contentPane.add(drv_Name);
		drv_Name.setColumns(10);

		final JButton btnView = new JButton("View / Refresh");
		btnView.addActionListener(arg0 -> GEP_DB2JTable_Drivers.readData(table,
				GEP_Driver_Obj.GEP_User_DataList
						.createList(GEP_SELECT.getRecords(GEP_Connection.createConnection(infoTextField),
								GEP_Driver_Obj.TableName.TABLENAME, infoTextField), infoTextField),
				infoTextField));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnView, -6, SpringLayout.NORTH, scrollPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnView, -10, SpringLayout.EAST, contentPane);
		contentPane.add(btnView);

		final JLabel lblUserAutho = new JLabel("Till");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblUserAutho, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblUserAutho, -90, SpringLayout.SOUTH, contentPane);
		contentPane.add(lblUserAutho);

		drv_Till = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, drv_Till, 10, SpringLayout.EAST, lblUserAutho);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, drv_Till, -87, SpringLayout.SOUTH, contentPane);
		contentPane.add(drv_Till);
		drv_Till.setColumns(15);

		drv_sCode = new JTextField();
		// comboBoxLocations.setModel(new DefaultComboBoxModel(new String[] {"Mombasa",
		// "Nairobi", "Makindu", "Ukunda", "Mtwapa", "Voi", "Mariakani"}));
		sl_contentPane.putConstraint(SpringLayout.EAST, drv_sCode, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, drv_sCode, -86, SpringLayout.SOUTH, contentPane);
		contentPane.add(drv_sCode);
		drv_sCode.setColumns(10);

		final JButton btnGenerateUser = new JButton("Add Van Sale");
		sl_contentPane.putConstraint(SpringLayout.EAST, btnGenerateUser, 0, SpringLayout.EAST, scrollPane);
		btnGenerateUser.addActionListener(new ActionListener() {

			private GEP_Util<GEP_Driver_Obj> utilObj;
			private GEP_Driver_Obj driver_Obj;

			@Override
			public void actionPerformed(ActionEvent arg0) {

				utilObj = new GEP_Util<>(GEP_Driver_Obj.class);

				save_GEP_SalesDrivers(sgGEP_SalesDrivers());

				/*
				 * infoTextField.setText("Driver@ "+GEP_Decoder.decodeData(
				 * driver_Obj.getId().toString())+ " generated successful "+ new
				 * GEP_M().getClass().getSimpleName()); infoTextField.setForeground(Color.RED);
				 */ }

			private void save_GEP_SalesDrivers(GEP_Driver_Obj driver_Obj) {
				try {
					new GEP_CRUD(driver_Obj, utilObj.getSessionFactory()).saveObject();

					JOptionPane.showMessageDialog(null,
							"ABS001: Kindly Note Down Driver\'s Id "
									+ GEP_Decoder.decodeData(driver_Obj.getId().toString()),
							new GEP_UserPanelUI().getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);

				} catch (final Exception e) {
					infoTextField.setText(e.getMessage() + " " + new GEP_Drivers().getClass().getSimpleName());
					infoTextField.setForeground(Color.RED);

				}
			}

			private GEP_Driver_Obj sgGEP_SalesDrivers() {

				driver_Obj = new GEP_Driver_Obj();

				driver_Obj.setId(GEP_Encoder.encodeData(GEP_Unique_Box.getUniqueIndex(infoTextField,
						GEP_Driver_Obj.Unique_Box.UNIQUE_BOX, GEP_Driver_Obj.TableName.TABLENAME)));
				driver_Obj.setName(GEP_Encoder.encodeData(drv_Name.getText().toString()));
				driver_Obj.setsCode(GEP_Encoder.encodeData(drv_sCode.getText().toString()));
				driver_Obj.setTill(GEP_Encoder.encodeData(drv_Till.getText().toString()));
				driver_Obj.setCreator(GEP_Encoder.encodeData(OnSystemUser.getUserName()));
				driver_Obj.setLocation(GEP_Encoder.encodeData("default"));

				return driver_Obj;
			}
		});
		contentPane.add(btnGenerateUser);

		final JLabel lblUserLocation = new JLabel("Short Code");
		sl_contentPane.putConstraint(SpringLayout.EAST, lblUserLocation, -4, SpringLayout.WEST, drv_sCode);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblUserLocation, -90, SpringLayout.SOUTH, contentPane);
		contentPane.add(lblUserLocation);

		infoTextField = new JTextField();
		infoTextField.setBackground(new Color(161, 137, 97));
		infoTextField.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, infoTextField, -20, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, infoTextField, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, infoTextField, -10, SpringLayout.EAST, contentPane);
		contentPane.add(infoTextField);
		infoTextField.setColumns(10);

		final JButton btnExit = new JButton("Exit");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnExit, -30, SpringLayout.NORTH, infoTextField);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnGenerateUser, 0, SpringLayout.NORTH, btnExit);
		btnExit.addActionListener(arg0 -> dispose());
		sl_contentPane.putConstraint(SpringLayout.WEST, btnExit, 10, SpringLayout.WEST, contentPane);
		contentPane.add(btnExit);

	}
}
