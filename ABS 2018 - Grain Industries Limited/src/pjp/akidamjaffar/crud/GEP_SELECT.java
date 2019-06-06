package pjp.akidamjaffar.crud;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

public class GEP_SELECT {
	public static ResultSet getRecords(Connection connectionObj, String tableName, JTextField infoTextField) {
		final String selectTableDetails = "SELECT * FROM " + tableName;
		ResultSet resultSetObj = null;
		try {
			final PreparedStatement ps = connectionObj.prepareStatement(selectTableDetails);

			resultSetObj = ps.executeQuery();
		} catch (final SQLException e) {
			infoTextField.setText(e.getMessage() + " " + new GEP_SELECT().getClass().getSimpleName());
			infoTextField.setForeground(Color.RED);
		}

		return resultSetObj;
	}
}