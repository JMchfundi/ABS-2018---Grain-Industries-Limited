package grainindustries.com.main;

import java.io.IOException;

import javax.swing.JOptionPane;

public class DBConnection {

	public static void dbConnect() {
		// TODO Auto-generated method stub
		try {
			if (System.getProperty("os.name").equalsIgnoreCase("Linux"))
				Runtime.getRuntime().exec("./startdb");
			else
				Runtime.getRuntime().exec("cmd /c startdb.bat");
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), new DBConnection().getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
