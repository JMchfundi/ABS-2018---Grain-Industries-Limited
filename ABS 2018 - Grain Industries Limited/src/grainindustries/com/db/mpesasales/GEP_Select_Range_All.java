package grainindustries.com.db.mpesasales;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class GEP_Select_Range_All {
	public static ResultSet getRecords(Connection connectionObj, String tableName, Date dateFrom, Date dateTo) {
		final String selectTableDetails = "SELECT * FROM " + tableName + " " + "WHERE salesDate BETWEEN \'" + dateFrom
				+ "\' AND \'" + dateTo + "\'";
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