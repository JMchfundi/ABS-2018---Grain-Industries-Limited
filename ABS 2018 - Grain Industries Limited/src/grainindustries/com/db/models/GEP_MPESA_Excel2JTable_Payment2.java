package grainindustries.com.db.models;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pjp.akidamjaffar.config.GEP_Connection;
import pjp.akidamjaffar.crud.JdbcUniversalSelector;
import pjp.akidamjaffar.crypto.GEP_Encoder;

public class GEP_MPESA_Excel2JTable_Payment2 {

	private static DefaultTableModel defaultTableModelObj;
	private static Vector column_name;
	private static Vector data_rows;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void readData(JTable tableObj, JTextField infoTextField) {

		defaultTableModelObj = new DefaultTableModel() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
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


		for (int i = 0; i < 5; i++) 
			column_name.addElement(i);

		defaultTableModelObj.setColumnIdentifiers(column_name);

		int i = 0;

		for (int row = 0; row < tableObj.getModel().getRowCount(); row++)
			if (tableObj.getModel().getValueAt(row, 2) != null)
				if (tableObj.getModel().getValueAt(row, 2).toString().startsWith("Merchant Payment")) {
					data_rows = new Vector();

					data_rows.addElement(++i + ".");
					data_rows.addElement(tableObj.getModel().getValueAt(row, 0).toString());
					data_rows.addElement(tableObj.getModel().getValueAt(row, 1).toString());
					data_rows.addElement(tableObj.getModel().getValueAt(row, 2).toString());
					data_rows.addElement(tableObj.getModel().getValueAt(row, 3).toString());

					defaultTableModelObj.addRow(data_rows);
				}

		if (!data_rows.isEmpty())
			try {
				tableObj.setModel(defaultTableModelObj);
			} catch (final Exception e) {
				JOptionPane.showMessageDialog(null, e.getCause() + e.getLocalizedMessage() + e.getStackTrace());
			}
		// tableObj.getColumnModel().getColumn(3).setCellEditor(new
		// DefaultCellEditor(comboBoxObj));
		else {
			infoTextField.setText("Cant Find Records For The Given Range "
					+ new GEP_MPESA_Excel2JTable_Payment2().getClass().getSimpleName());
			infoTextField.setForeground(Color.RED);
		}
	}
}
