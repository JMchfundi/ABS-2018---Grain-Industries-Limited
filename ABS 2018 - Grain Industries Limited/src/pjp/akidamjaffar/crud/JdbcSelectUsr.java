package pjp.akidamjaffar.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import grainindustries.com.db.models.GEP_User_Obj;

public class JdbcSelectUsr {

	public static ResultSet selectRecordFromTable(Connection conObj, String usrName) {

		final String selectTableDetails = "SELECT * FROM " + GEP_User_Obj.TableName.TABLENAME + " " + "WHERE ID=?";
		ResultSet rs = null;
		try {
			final PreparedStatement ps = conObj.prepareStatement(selectTableDetails);

			ps.setString(1, usrName);

			rs = ps.executeQuery();
		} catch (final SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), new JdbcSelectUsr().getClass().getName(),
					JOptionPane.ERROR_MESSAGE);
		}

		return rs;
	}
}