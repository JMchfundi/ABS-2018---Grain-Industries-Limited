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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import grainindustries.com.db.models.GEP_Equity_Excel2JTable_Payment;
import grainindustries.com.db.models.GEP_MPESA_Excel2JTable_Payment2;

public class GEP_E2JM_MPESA {
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public static TableModel showExcel2Jtable(String excelFilePath, JTable excelDataTable, JTextField infoTextField) {
		FileInputStream excelFileToRead = null;
		try {
			excelFileToRead = new FileInputStream(excelFilePath);
		} catch (final FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_E2JM_MPESA().getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
		}

		HSSFWorkbook workbookExcelFile = null;

		try {
			workbookExcelFile = new HSSFWorkbook(excelFileToRead);
		} catch (final IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_E2JM_MPESA().getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
		}

		HSSFSheet excelSheet = workbookExcelFile.getSheetAt(0);

		// THE MODEL OF OUR TABLE
		DefaultTableModel excelData = new DefaultTableModel();

		Vector column_name = new Vector();
		Vector data_rows = new Vector();

		for (int i = 0; i < 4; i++) 
			column_name.addElement(i);

		excelData.setColumnIdentifiers(column_name);

		for (int j = 5; j <= excelSheet.getLastRowNum(); j++) {
			data_rows = new Vector();

			final HSSFRow rowIndex = excelSheet.getRow(j);

			if (rowIndex != null)
				for (int k = 0; k <= rowIndex.getLastCellNum(); k++)
					if (k == 2 || k == 4 || k == 6 || k == 7 || k == 8 
					|| k == 9 || k == 10 || k == 11 || k == 12)
						continue;
					else {

						final HSSFCell excelCell = rowIndex.getCell(k);

						if (excelCell != null)
							data_rows.addElement(excelCell);
					}
			excelData.addRow(data_rows);
		}

		if (!data_rows.isEmpty()) {
			excelDataTable.setModel(excelData);
			GEP_MPESA_Excel2JTable_Payment2.readData(excelDataTable, infoTextField);
		} else
			JOptionPane.showMessageDialog(null, "An Error Occured Will Reading File Data", "File Data Error",
					JOptionPane.ERROR_MESSAGE);

		return excelDataTable.getModel();
	}
}
