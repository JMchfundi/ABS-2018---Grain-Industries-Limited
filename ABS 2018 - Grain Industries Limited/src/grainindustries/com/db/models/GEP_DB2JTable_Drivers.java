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

public class GEP_DB2JTable_Drivers {

	private static DefaultTableModel defaultTableModelObj;
	private static Vector column_name;
	private static Vector data_rows;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void readData(JTable tableObj, List<GEP_Driver_Obj> driver_DataObj, JTextField infoTextField) {

		defaultTableModelObj = new DefaultTableModel() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 7:
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
		column_name.addElement("Id");
		column_name.addElement("Name");
		column_name.addElement("Location");
		column_name.addElement("Creator");
		column_name.addElement("Till");
		column_name.addElement("Short Code");
		column_name.addElement("Check To Update");

		defaultTableModelObj.setColumnIdentifiers(column_name);

		int i = 0;

		for (final GEP_Driver_Obj driver_Data : driver_DataObj) {

			data_rows = new Vector();

			data_rows.addElement(++i + ".");
			data_rows.addElement(driver_Data.getId());
			data_rows.addElement(driver_Data.getName());
			data_rows.addElement(driver_Data.getLocation());
			data_rows.addElement(driver_Data.getCreator());
			data_rows.addElement(driver_Data.getTill());
			data_rows.addElement(driver_Data.getsCode());
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
					"Cant Find Records For The Given Range " + new GEP_DB2JTable_Drivers().getClass().getSimpleName());
			infoTextField.setForeground(Color.RED);
		}
	}
}
