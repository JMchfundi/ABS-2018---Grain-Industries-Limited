package grainindustries.com.mui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import grainindustries.com.db.models.GEP_Excel2JTable_Payment;

public class GEP_E2JM_xls {
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation", "static-access" })
	public static TableModel showExcel2Jtable(String excelFilePath, JTable excelDataTable, JTextField infoTextField,
			ResultSet setObj) {
		FileInputStream excelFileToRead = null;
		try {
			excelFileToRead = new FileInputStream(excelFilePath);
		} catch (final FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_E2JM_xls().getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
		}

		HSSFWorkbook workbookExcelFile = null;

		try {
			workbookExcelFile = new HSSFWorkbook(excelFileToRead);
		} catch (final IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_E2JM_xls().getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
		}

		HSSFSheet excelSheet = workbookExcelFile.getSheetAt(0);

		// THE MODEL OF OUR TABLE
		DefaultTableModel excelData = new DefaultTableModel() {
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
					return Object.class;
				}
			}
		};

		Vector column_name = new Vector();
		Vector data_rows = new Vector();

		column_name.addElement("#");
		column_name.addElement("Confirmed");
		column_name.addElement("Ref Index");
		column_name.addElement("Value Date");
		column_name.addElement("Details");
		column_name.addElement("Cash In");

		excelData.setColumnIdentifiers(column_name);

		int i = 0;
		for (int j = 7; j <= excelSheet.getLastRowNum(); j++) {
			data_rows = new Vector();
			data_rows.addElement(++i + ".");

			data_rows.addElement(Boolean.FALSE);
			final HSSFRow rowIndex = excelSheet.getRow(j);

			if (rowIndex != null)
				for (int k = 0; k <= rowIndex.getLastCellNum(); k++)
					if (k == 2 || k == 4)
						continue;
					else if (k > 5)
						break;
					else {

						final HSSFCell excelCell = rowIndex.getCell(k);

						if (excelCell != null)
							data_rows.addElement(excelCell);
					}
			excelData.addRow(data_rows);
		}

		if (!data_rows.isEmpty()) {
			excelDataTable.setModel(excelData);
			GEP_Excel2JTable_Payment.readData(excelDataTable, infoTextField, setObj);
		} else
			JOptionPane.showMessageDialog(null, "An Error Occured Will Reading File Data", "File Data Error",
					JOptionPane.ERROR_MESSAGE);

		return excelDataTable.getModel();
	}
}
