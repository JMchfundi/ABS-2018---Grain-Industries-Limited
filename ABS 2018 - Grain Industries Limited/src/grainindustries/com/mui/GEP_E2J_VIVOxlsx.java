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

public class GEP_E2J_VIVOxlsx {
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation", "static-access" })
	public static TableModel showExcel2Jtable(String excelFilePath, JTable excelDataTable, JTextField infoTextField) {
		FileInputStream excelFileToRead = null;
		try {
			excelFileToRead = new FileInputStream(excelFilePath);
		} catch (final FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_E2J_VIVOxlsx().getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
		}

		XSSFWorkbook workbookExcelFile = null;

		try {
			workbookExcelFile = new XSSFWorkbook(excelFileToRead);
		} catch (final IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_E2J_VIVOxlsx().getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
		}

		final XSSFSheet excelSheet = workbookExcelFile.getSheetAt(0);

		// THE MODEL OF OUR TABLE
		final DefaultTableModel excelData = new DefaultTableModel();

		final Vector column_name = new Vector();
		Vector data_rows = new Vector();

		for (int i = 0; i < 6; i++)
			column_name.addElement(i);

		excelData.setColumnIdentifiers(column_name);

		int i = 0;

		for (int j = 0; j <= excelSheet.getLastRowNum(); j++) {
			data_rows = new Vector();
			data_rows.addElement(++i + ".");

			final XSSFRow rowIndex = excelSheet.getRow(j);

			if (rowIndex != null)
				for (int k = 4; k <= rowIndex.getLastCellNum(); k++)
					if (k == 7 || k == 8 || k == 11 || k == 12)
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
			GEP_Excel2JTable_VIVO.readData(excelDataTable, infoTextField);
		} else
			JOptionPane.showMessageDialog(null, "An Error Occured Will Reading File Data", "File Data Error",
					JOptionPane.ERROR_MESSAGE);

		return excelDataTable.getModel();
	}
}
