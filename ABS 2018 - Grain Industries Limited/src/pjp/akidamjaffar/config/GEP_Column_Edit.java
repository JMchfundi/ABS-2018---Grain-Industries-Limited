package pjp.akidamjaffar.config;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class GEP_Column_Edit {

	private static TableColumn tableColumnValue;

	private static void columnEditability() {
		tableColumnValue.setCellEditor(new TableCellEditor() {

			@Override
			public void addCellEditorListener(CellEditorListener arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void cancelCellEditing() {
				// TODO Auto-generated method stub

			}

			@Override
			public Object getCellEditorValue() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isCellEditable(EventObject arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void removeCellEditorListener(CellEditorListener arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean shouldSelectCell(EventObject arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean stopCellEditing() {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	public GEP_Column_Edit(TableColumn tableColumnValue) {
		// TODO Auto-generated constructor stub
		GEP_Column_Edit.tableColumnValue = tableColumnValue;
		GEP_Column_Edit.columnEditability();
	}
}
