package grainindustries.com.db.models;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pjp.akidamjaffar.crypto.GEP_Decoder;

public class GEP_Excel2JTable_Payment {

	private static DefaultTableModel defaultTableModelObj;
	private static Vector column_name;
	private static Vector data_rows;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void readData(JTable tableObj, JTextField infoTextField, ResultSet setObj) {

		defaultTableModelObj = new DefaultTableModel() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 6:
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
		column_name.addElement("Refference");
		column_name.addElement("Pay To");
		column_name.addElement("Merchant");
		column_name.addElement("Details");
		column_name.addElement("Amount");
		column_name.addElement("Cash In");

		defaultTableModelObj.setColumnIdentifiers(column_name);

		int i = 0;

		try {
			for (int row = 0; row < tableObj.getModel().getRowCount(); row++)
				if (tableObj.getModel().getValueAt(row, 4) != null)
					if (tableObj.getModel().getValueAt(row, 4).toString().startsWith("Merchant Payment from")) {
						data_rows = new Vector();

						data_rows.addElement(++i + ".");
						data_rows.addElement(tableObj.getModel().getValueAt(row, 2).toString());
						data_rows.addElement("Equity Bank KES");
						data_rows.addElement(GEP_Decoder.decodeData(setObj.getObject("till").toString()));
						data_rows.addElement("Paid By "
								+ tableObj.getModel().getValueAt(row, 4).toString().replace("Merchant Payment from", "")
								+ " To Till " + GEP_Decoder.decodeData(setObj.getObject("till").toString()) + " For "
								+ GEP_Decoder.decodeData(setObj.getObject("name").toString()));
						data_rows.addElement(tableObj.getModel().getValueAt(row, 5).toString());
						data_rows.addElement(Boolean.FALSE);

						defaultTableModelObj.addRow(data_rows);

					}
		} catch (final SQLException e) {
			infoTextField.setText(e.getMessage() + " " + new GEP_Excel2JTable_Payment().getClass().getSimpleName());
			infoTextField.setForeground(Color.RED);
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
					+ new GEP_Excel2JTable_Payment().getClass().getSimpleName());
			infoTextField.setForeground(Color.RED);
		}
	}
}
