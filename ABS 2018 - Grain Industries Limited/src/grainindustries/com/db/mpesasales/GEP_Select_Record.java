package grainindustries.com.db.mpesasales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class GEP_Select_Record {
	public static ResultSet getRecords(Connection connectionObj, String tableName, String keyValue) {
		final String selectTableDetails = "SELECT * FROM " + tableName + " " + "WHERE salesRef = \'" + keyValue + "\'";
		ResultSet resultSetObj = null;
		try {
			final PreparedStatement ps = connectionObj.prepareStatement(selectTableDetails);

			resultSetObj = ps.executeQuery();
		} catch (final SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Interna Error", JOptionPane.ERROR_MESSAGE);
		}

		return resultSetObj;
	}
}