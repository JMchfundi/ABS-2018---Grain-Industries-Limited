package pjp.akidamjaffar.config;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import pjp.akidamjaffar.crud.GEP_Object_Search;
import pjp.akidamjaffar.crud.GEP_SELECT;

public class GEP_Unique_Box {

	private static String uniqueIndex;
	private static int rowIndex;

	public static String getUniqueIndex(JTextField infoTextField, String uniqueIndex, String tableName) {
		GEP_Unique_Box.uniqueIndex = uniqueIndex;

		rowIndex = 0;

		try {

			if (GEP_Object_Search.searchTable(GEP_Connection.createConnection(infoTextField), tableName, infoTextField)
					.next()) {

				final ResultSet resultSetObj = GEP_SELECT.getRecords(GEP_Connection.createConnection(infoTextField),
						tableName, infoTextField);

				while (resultSetObj.next())
					++rowIndex;
			}

		} catch (final SQLException e) {
			infoTextField.setText(e.getMessage() + " " + new GEP_Unique_Box().getClass().getSimpleName());
			infoTextField.setForeground(Color.RED);
		}

		return GEP_Unique_Box.uniqueIndex += rowIndex;
	}
}