package grainindustries.com.db.driversdata;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GEP_D2J_SalesDrivers {

	private static DefaultTableModel defaultTableModelObj;
	private static Vector column_name;
	private static Vector data_rows;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void readData(JTable tableObj, List<GEP_SalesDrivers> receive_Sales, JTextField infoTextField) {

		defaultTableModelObj = new DefaultTableModel() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 1:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};

		column_name = new Vector();
		data_rows = new Vector();

		final JComboBox<String> comboBoxObj = new JComboBox<>();
		final Vector<String> vectorObj = new Vector<>();

		vectorObj.add("Mombasa");
		vectorObj.add("Nairobi");
		vectorObj.add("Mtwapa");
		vectorObj.add("Ukunda");

		comboBoxObj.setModel(new DefaultComboBoxModel<>(vectorObj));

		// pass Default Table Object into myTable
		column_name.addElement("#");
		column_name.addElement("Check To Update");
		column_name.addElement("Till NO");
		column_name.addElement("Name");
		column_name.addElement("Location");

		defaultTableModelObj.setColumnIdentifiers(column_name);

		int i = 0;
		for (final GEP_SalesDrivers receive_SalesObj : receive_Sales) {

			data_rows = new Vector();
			data_rows.addElement(++i + ".");

			data_rows.addElement(Boolean.FALSE);
			data_rows.addElement(receive_SalesObj.getDriversId());
			data_rows.addElement(receive_SalesObj.getDriversName());
			data_rows.addElement(receive_SalesObj.getDriversLocation());

			defaultTableModelObj.addRow(data_rows);
		}

		if (!data_rows.isEmpty()) {
			tableObj.setModel(defaultTableModelObj);
			tableObj.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(comboBoxObj));

			// tableObjAutofit(tableObj);
		}

		else {
			infoTextField.setText(
					"Cant Find Records For The Given Range " + new GEP_D2J_SalesDrivers().getClass().getSimpleName());
			infoTextField.setForeground(Color.RED);
		}
	}
}
