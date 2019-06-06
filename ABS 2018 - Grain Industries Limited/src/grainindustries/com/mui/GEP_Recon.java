package grainindustries.com.mui;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GEP_Recon {

	private static Vector<String> recorded_data;

	public static void reconcileData(JTable existing_data_jtable, JTable equity_data_jtable, JTextField infoTextField) {

		recorded_data = new Vector<>();

		for (int row = 0; row < existing_data_jtable.getModel().getRowCount(); row++)
			recorded_data.addElement(existing_data_jtable.getModel().getValueAt(row, 1).toString());

		for (int row = 0; row < equity_data_jtable.getModel().getRowCount(); row++)
			for (final String keyValue : recorded_data)
				if (keyValue.equals(equity_data_jtable.getModel().getValueAt(row, 1).toString())) {

					((DefaultTableModel) equity_data_jtable.getModel()).removeRow(row);
					for (int rowIndex = 0; rowIndex < existing_data_jtable.getModel().getRowCount(); rowIndex++)
						if (keyValue.equals(existing_data_jtable.getModel().getValueAt(rowIndex, 1).toString()))
							((DefaultTableModel) existing_data_jtable.getModel()).removeRow(rowIndex);
				}
	}
}
