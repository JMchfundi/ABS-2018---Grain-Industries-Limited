package grainindustries.com.db.models;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GEP_DB2JTable_Users {

	private static DefaultTableModel defaultTableModelObj;
	private static Vector column_name;
	private static Vector data_rows;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void readData(JTable tableObj, List<GEP_User_Obj> user_DataObj, JTextField infoTextField) {

		defaultTableModelObj = new DefaultTableModel() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 8:
					return Boolean.class;
				default:
					return Object.class;
				}
			}
		};

		column_name = new Vector();
		data_rows = new Vector();

		final JComboBox<String> comboBoxObj = new JComboBox<>();

		final Vector<String> vectorObj = GEP_Locations.readLocations();

		comboBoxObj.setModel(new DefaultComboBoxModel<>(vectorObj));

		column_name.addElement("#");
		column_name.addElement("User Id");
		column_name.addElement("Name");
		column_name.addElement("Location");
		column_name.addElement("Done");
		column_name.addElement("Active Times");
		column_name.addElement("Last Active");
		column_name.addElement("Authentication");
		column_name.addElement("Check To Update");

		defaultTableModelObj.setColumnIdentifiers(column_name);

		int i = 0;

		for (final GEP_User_Obj user_Data : user_DataObj) {

			data_rows = new Vector();

			data_rows.addElement(++i + ".");
			data_rows.addElement(user_Data.getId());
			data_rows.addElement(user_Data.getName());
			data_rows.addElement(user_Data.getLocation());
			data_rows.addElement(user_Data.getCreator());
			data_rows.addElement(user_Data.getActive_mult());
			data_rows.addElement(user_Data.getLast_log());
			data_rows.addElement(user_Data.getUser_authencity());
			data_rows.addElement(Boolean.FALSE);

			defaultTableModelObj.addRow(data_rows);
		}

		if (!data_rows.isEmpty()) {
			try {
				tableObj.setModel(defaultTableModelObj);
			} catch (final Exception e) {
				JOptionPane.showMessageDialog(null, e.getCause() + e.getLocalizedMessage() + e.getStackTrace());
			}
			tableObj.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBoxObj));
		}

		else {
			infoTextField.setText(
					"Cant Find Records For The Given Range " + new GEP_DB2JTable_Users().getClass().getSimpleName());
			infoTextField.setForeground(Color.RED);
		}
	}
}
