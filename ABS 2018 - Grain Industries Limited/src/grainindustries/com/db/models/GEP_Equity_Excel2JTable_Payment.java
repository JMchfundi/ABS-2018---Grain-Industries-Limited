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

public class GEP_Equity_Excel2JTable_Payment {

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

		column_name.addElement("#");
		column_name.addElement("Refference");
		column_name.addElement("TransDate");
		column_name.addElement("Payment From");
		column_name.addElement("Till Number");
		column_name.addElement("Sales Location");
		column_name.addElement("Amount");

		defaultTableModelObj.setColumnIdentifiers(column_name);

		int i = 0;

		for (int row = 0; row < tableObj.getModel().getRowCount(); row++)
			if (tableObj.getModel().getValueAt(row, 2) != null)
				if (tableObj.getModel().getValueAt(row, 2).toString().startsWith("MPS 2547")) {
					data_rows = new Vector();

					data_rows.addElement(++i + ".");
					data_rows.addElement(new GEP_PhoneTillReff(tableObj.getModel().getValueAt(row, 2).toString())
							.getMpsRefference());
					data_rows.addElement(tableObj.getModel().getValueAt(row, 1).toString());
					data_rows.addElement(
							new GEP_PhoneTillReff(tableObj.getModel().getValueAt(row, 2).toString()).getPhoneNumber());
					data_rows.addElement(
							new GEP_PhoneTillReff(tableObj.getModel().getValueAt(row, 2).toString()).getTillNumber());

					List<GEP_Driver_Obj> driver_Objs;

					driver_Objs = GEP_Driver_Obj.GEP_User_DataList.createList(
							JdbcUniversalSelector.selectRecordFromTable(GEP_Connection.createConnection(infoTextField),
									GEP_Driver_Obj.TableName.TABLENAME, "till",
									GEP_Encoder.encodeData(
											new GEP_PhoneTillReff(tableObj.getModel().getValueAt(row, 2).toString())
													.getTillNumber())),
							infoTextField);

					if (!driver_Objs.isEmpty())
						for (final GEP_Driver_Obj gep_Driver_Obj : driver_Objs)
							data_rows.addElement(gep_Driver_Obj.getLocation());
					else
						data_rows.addElement("To Be Defined");

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
					+ new GEP_Equity_Excel2JTable_Payment().getClass().getSimpleName());
			infoTextField.setForeground(Color.RED);
		}
	}
}
