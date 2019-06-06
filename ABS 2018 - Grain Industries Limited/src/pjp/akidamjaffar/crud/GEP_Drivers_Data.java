package pjp.akidamjaffar.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import grainindustries.com.db.models.GEP_Driver_Obj;

public class GEP_Drivers_Data {
	public static ResultSet getRecords(Connection connectionObj, String keyValue) {
		final String selectTableDetails = "SELECT * FROM " + GEP_Driver_Obj.TableName.TABLENAME + " "
				+ "WHERE sCode = \'" + keyValue + "\'";
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