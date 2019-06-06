package grainindustries.com.db.mpesasales;

import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GEP_D2J_MpesaSales {

	private static DefaultTableModel defaultTableModelObj;
	private static Vector column_name;
	private static Vector data_rows;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void readData(JTable tableObj, List<GEP_MpesaSales> mpesaSalesObj) {
		// This object will pass data into Table
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

		// pass Default Table Object into myTable
		column_name.addElement("#");
		column_name.addElement("Check Cleared");
		column_name.addElement("Trans. Date");
		column_name.addElement("Cust. Phone NO.");
		column_name.addElement("Sale's Till NO.");
		column_name.addElement("Sale's Man Name");
		column_name.addElement("Mpesa Reff");
		column_name.addElement("Amt Paid");

		defaultTableModelObj.setColumnIdentifiers(column_name);

		int i = 0;
		for (final GEP_MpesaSales mpesaSalesData : mpesaSalesObj) {

			data_rows = new Vector();
			data_rows.addElement(++i + ".");
			if (mpesaSalesData.getSalesStatus().equals("On Hold"))
				data_rows.addElement(Boolean.FALSE);
			else
				data_rows.addElement(Boolean.TRUE);

			data_rows.addElement(mpesaSalesData.getSalesDate());
			data_rows.addElement(mpesaSalesData.getCustPhone());
			data_rows.addElement(mpesaSalesData.getSalesTill());
			data_rows.addElement(mpesaSalesData.getSalesName());
			data_rows.addElement(mpesaSalesData.getSalesRef());
			data_rows.addElement(mpesaSalesData.getSalesPayement());

			defaultTableModelObj.addRow(data_rows);
		}

		if (!data_rows.isEmpty())
			tableObj.setModel(defaultTableModelObj);
		else
			JOptionPane.showMessageDialog(null, "Cant Find Records For The Given Range",
					new GEP_D2J_MpesaSales().getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
	}
}
