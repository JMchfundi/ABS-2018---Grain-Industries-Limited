package grainindustries.com.mui;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import grainindustries.com.db.models.GEP_VanSN_Loc;

public class GEP_Excel2JTable_SAP {

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

		column_name.addElement("#");
		column_name.addElement("Invoice Ref ID");
		column_name.addElement("TransDate");
		column_name.addElement("Name");
		column_name.addElement("Sales Route");
		column_name.addElement("Amount");

		defaultTableModelObj.setColumnIdentifiers(column_name);

		int i = 0;

		for (int row = 0; row < tableObj.getModel().getRowCount(); row++)
			if (tableObj.getModel().getValueAt(row, 4) != null)
				if (tableObj.getModel().getValueAt(row, 4).toString().startsWith("CSH")) {
					data_rows = new Vector();

					data_rows.addElement(++i + ".");
					data_rows.addElement(tableObj.getModel().getValueAt(row, 4).toString());
					data_rows.addElement(tableObj.getModel().getValueAt(row, 1).toString());
					data_rows.addElement(
							new GEP_VanSN_Loc(tableObj.getModel().getValueAt(row, 2).toString()).getVanSN());
					data_rows.addElement(
							new GEP_VanSN_Loc(tableObj.getModel().getValueAt(row, 2).toString()).getVanLoc());
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
			infoTextField.setText(
					"Cant Find Records For The Given Range " + new GEP_Excel2JTable_SAP().getClass().getSimpleName());
			infoTextField.setForeground(Color.RED);
		}
	}
}
