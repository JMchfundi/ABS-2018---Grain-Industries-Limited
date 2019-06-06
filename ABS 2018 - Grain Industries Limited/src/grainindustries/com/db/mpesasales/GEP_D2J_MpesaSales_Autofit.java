package grainindustries.com.db.mpesasales;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class GEP_D2J_MpesaSales_Autofit {

	private static JTable tableObj;

	private static void tableObjAutofit() {
		// TODO Auto-generated method stub
		tableObj.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		for (int column = 0; column < tableObj.getColumnCount(); column++) {
			final TableColumn tableObjColumn = tableObj.getColumnModel().getColumn(column);

			int preferredWidth = tableObjColumn.getMinWidth();
			final int maxWidth = tableObjColumn.getMaxWidth();

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

			tableObjColumn.setPreferredWidth(preferredWidth);
		}
	}

	public GEP_D2J_MpesaSales_Autofit(JTable tableObj) {
		// TODO Auto-generated constructor stub
		GEP_D2J_MpesaSales_Autofit.tableObj = tableObj;
		GEP_D2J_MpesaSales_Autofit.tableObjAutofit();
	}
}
