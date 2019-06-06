package grainindustries.com.mui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import grainindustries.com.db.models.GEP_Equity_Excel2JTable_Payment;

public class GEP_E2JM_xlsx {
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public static TableModel showExcel2Jtable(String excelFilePath, JTable excelDataTable, JTextField infoTextField) {
		FileInputStream excelFileToRead = null;
		try {
			excelFileToRead = new FileInputStream(excelFilePath);
		} catch (final FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_E2JM_xlsx().getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
		}

		XSSFWorkbook workbookExcelFile = null;

		try {
			workbookExcelFile = new XSSFWorkbook(excelFileToRead);
		} catch (final IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_E2JM_xlsx().getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
		}

		XSSFSheet excelSheet = workbookExcelFile.getSheetAt(0);

		// THE MODEL OF OUR TABLE
		DefaultTableModel excelData = new DefaultTableModel();

		Vector column_name = new Vector();
		Vector data_rows = new Vector();

		column_name.addElement("#");
		column_name.addElement("Value Date");
		column_name.addElement("Details");
		column_name.addElement("Cash In");

		excelData.setColumnIdentifiers(column_name);

		int i = 0;
		for (int j = 10; j <= excelSheet.getLastRowNum(); j++) {
			data_rows = new Vector();
			data_rows.addElement(++i + ".");

			final XSSFRow rowIndex = excelSheet.getRow(j);

			if (rowIndex != null)
				for (int k = 3; k <= rowIndex.getLastCellNum(); k++)
					if (k == 5 || k == 6 || k == 8)
						continue;
					else {

						final XSSFCell excelCell = rowIndex.getCell(k);

						if (excelCell != null)
							data_rows.addElement(excelCell);
					}
			excelData.addRow(data_rows);
		}

		if (!data_rows.isEmpty()) {
			excelDataTable.setModel(excelData);
			GEP_Equity_Excel2JTable_Payment.readData(excelDataTable, infoTextField);
		} else
			JOptionPane.showMessageDialog(null, "An Error Occured Will Reading File Data", "File Data Error",
					JOptionPane.ERROR_MESSAGE);

		return excelDataTable.getModel();
	}
}
