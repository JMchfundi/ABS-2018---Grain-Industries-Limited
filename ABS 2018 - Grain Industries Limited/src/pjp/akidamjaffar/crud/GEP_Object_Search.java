package pjp.akidamjaffar.crud;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

public class GEP_Object_Search {
	private static ResultSet resultSetObj;
	private static DatabaseMetaData metaDataObj;

	public static ResultSet searchTable(Connection conObj, String tableName, JTextField infoTextField) {
		try {
			metaDataObj = conObj.getMetaData();
			resultSetObj = metaDataObj.getTables(null, null, tableName, null);
		} catch (final SQLException e) {
			infoTextField.setText(e.getMessage() + " " + new GEP_Object_Search().getClass().getSimpleName());
			infoTextField.setForeground(Color.RED);
		}

		return resultSetObj;
	}
}
