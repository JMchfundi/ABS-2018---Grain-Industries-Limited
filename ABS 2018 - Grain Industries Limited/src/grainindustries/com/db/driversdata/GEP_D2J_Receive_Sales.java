package grainindustries.com.db.driversdata;

import java.awt.Component;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class GEP_D2J_Receive_Sales {

	private static DefaultTableModel defaultTableModelObj;
	private static Vector column_name;
	private static Vector data_rows;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void readData(JTable tableObj, List<GEP_Receive_Sales> receive_Sales) {
		defaultTableModelObj = new DefaultTableModel();
		column_name = new Vector();
		data_rows = new Vector();

		// pass Default Table Object into myTable
		column_name.addElement("#");
		column_name.addElement("Trans. Date");
		column_name.addElement("Transaction Details");
		column_name.addElement("Amt Paid");

		defaultTableModelObj.setColumnIdentifiers(column_name);

		int i = 0;
		for (final GEP_Receive_Sales receive_SalesObj : receive_Sales) {

			data_rows = new Vector();
			data_rows.addElement(++i + ".");

			data_rows.addElement(receive_SalesObj.getAcknowledgementDate());
			data_rows.addElement(receive_SalesObj.getTransactionDetails());
			data_rows.addElement(receive_SalesObj.getDuePayment());

			defaultTableModelObj.addRow(data_rows);
		}

		if (!data_rows.isEmpty()) {
			tableObj.setModel(defaultTableModelObj);

			tableObjAutofit(tableObj);
		} else
			JOptionPane.showMessageDialog(null, "Cant Find Records For The Given Range",
					new GEP_D2J_Receive_Sales().getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
	}

	private static void tableObjAutofit(JTable tableObj) {
		tableObj.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int column = 0; column < tableObj.getColumnCount(); column++) {
			final TableColumn tableColumn = tableObj.getColumnModel().getColumn(column);

			if (column == 1)
				tableColumn.setPreferredWidth(150);
			else if (column == 2)
				tableColumn.setPreferredWidth(840);
			else if (column == 3)
				tableColumn.setPreferredWidth(150);
			else {
				int preferredWidth = tableColumn.getMinWidth();
				final int maxWidth = tableColumn.getMaxWidth();

				for (int row = 0; row < tableObj.getRowCount(); row++) {
					final TableCellRenderer cellRenderer = tableObj.getCellRenderer(row, column);
					final Component c = tableObj.prepareRenderer(cellRenderer, row, column);
					final int width = c.getPreferredSize().width + tableObj.getIntercellSpacing().width;
					preferredWidth = Math.max(preferredWidth, width);

					// We've exceeded the maximum width, no need to check other rows
					if (preferredWidth >= maxWidth) {
						preferredWidth = maxWidth;
						break;
					}
				}

				tableColumn.setPreferredWidth(preferredWidth);
			}
		}

	}
}
