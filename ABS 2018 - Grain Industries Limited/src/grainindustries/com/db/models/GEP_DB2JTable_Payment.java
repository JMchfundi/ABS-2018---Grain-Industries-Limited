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

public class GEP_DB2JTable_Payment {

	private static DefaultTableModel defaultTableModelObj;
	private static Vector column_name;
	private static Vector data_rows;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void readData(JTable tableObj, List<GEP_Transction_Obj> transction_DataObj,
			JTextField infoTextField) {

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

		column_name.addElement("#");
		column_name.addElement("Refference");
		column_name.addElement("Pay To");
		column_name.addElement("Merchant");
		column_name.addElement("Details");
		column_name.addElement("Transact By");
		column_name.addElement("Amount");

		defaultTableModelObj.setColumnIdentifiers(column_name);

		int i = 0;

		for (final GEP_Transction_Obj transction_Data : transction_DataObj) {

			data_rows = new Vector();

			data_rows.addElement(++i + ".");
			data_rows.addElement(transction_Data.getRef());
			data_rows.addElement(transction_Data.getPayto());
			data_rows.addElement(transction_Data.getMerchant());
			data_rows.addElement(transction_Data.getDescription());
			data_rows.addElement(transction_Data.getTransby());
			data_rows.addElement(transction_Data.getAmount());

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
			infoTextField.setText(
					"Cant Find Records For The Given Range " + new GEP_DB2JTable_Payment().getClass().getSimpleName());
			infoTextField.setForeground(Color.RED);
		}
	}
}
