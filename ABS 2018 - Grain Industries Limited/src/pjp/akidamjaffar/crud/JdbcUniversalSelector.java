package pjp.akidamjaffar.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class JdbcUniversalSelector {

	public static ResultSet selectRecordFromTable(Connection conObj, String dbTableName, String dbTableID,
			String idValue) {

		dbTableID += "=?";
		final String selectTableDetails = "SELECT * FROM " + dbTableName + " WHERE " + dbTableID;

		ResultSet rs = null;
		try {
			final PreparedStatement ps = conObj.prepareStatement(selectTableDetails);

			ps.setString(1, idValue);

			rs = ps.executeQuery();
		} catch (final SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), new JdbcUniversalSelector().getClass().getName(),
					JOptionPane.ERROR_MESSAGE);
		}

		return rs;
	}
}