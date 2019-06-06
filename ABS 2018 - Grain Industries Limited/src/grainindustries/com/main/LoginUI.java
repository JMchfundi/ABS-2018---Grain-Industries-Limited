package grainindustries.com.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import grainindustries.com.db.models.GEP_User_Obj;
import grainindustries.com.db.userdata.GEP_SystemUserDetails;
import grainindustries.com.db.userdata.OnSystemUser;
import grainindustries.com.ui.GEP_M;
import pjp.akidamjaffar.config.GEP_Connection;
import pjp.akidamjaffar.config.GEP_Unique_Box;
import pjp.akidamjaffar.config.GEP_Util;
import pjp.akidamjaffar.config.ReadImage;
import pjp.akidamjaffar.crud.GEP_CRUD;
import pjp.akidamjaffar.crud.GEP_Object_Search;
import pjp.akidamjaffar.crud.JdbcUniversalSelector;
import pjp.akidamjaffar.crypto.GEP_Encoder;

@SuppressWarnings("serial")
public class LoginUI extends JDialog {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField infoTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			final GEP_M frame = new GEP_M();
			frame.setSize(1250, 750);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

			/*			final JLabel backgroungImage_Label = ReadImage
					.readBackground_Image(new LoginUI().getClass().getSimpleName(), infoTextField);
			final LoginUI dialog = new LoginUI();
			dialog.setLocationRelativeTo(null);
			dialog.getContentPane().add(backgroungImage_Label);
			dialog.setVisible(true);
			 */		

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private final JTextField usernametextField;

	private final JTextField passwordField;

	/**
	 * Create the dialog.
	 */
	public LoginUI() {

		DBConnection.dbConnect();

		setTitle("In Java, We Value Privacy!!!");
		getContentPane().setBackground(Color.MAGENTA);
		setBounds(100, 100, 1350, 700);
		final SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		final JLabel lblUserName = new JLabel("user name");
		lblUserName.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, lblUserName, 13, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblUserName, -178, SpringLayout.EAST, getContentPane());
		getContentPane().add(lblUserName);

		usernametextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, usernametextField, -3, SpringLayout.NORTH, lblUserName);
		springLayout.putConstraint(SpringLayout.WEST, usernametextField, 6, SpringLayout.EAST, lblUserName);
		springLayout.putConstraint(SpringLayout.EAST, usernametextField, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(usernametextField);
		usernametextField.setColumns(10);

		final JLabel lblPassword = new JLabel("password");
		lblPassword.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 12, SpringLayout.SOUTH, lblUserName);
		springLayout.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblUserName);
		getContentPane().add(lblPassword);

		final JButton btnLogIn = new JButton("log in");
		springLayout.putConstraint(SpringLayout.EAST, btnLogIn, 0, SpringLayout.EAST, usernametextField);
		btnLogIn.addActionListener(new ActionListener() {
			private GEP_Util<GEP_User_Obj> utilObj;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					utilObj = new GEP_Util<>(GEP_User_Obj.class);

					utilObj.getSessionFactory();

					if (GEP_Object_Search.searchTable(GEP_Connection.createConnection(infoTextField),
							GEP_User_Obj.TableName.TABLENAME, infoTextField).next())
						checkingAuthentication(GEP_Connection.createConnection(infoTextField));
					else {
						saveSalesObj();
						checkingAuthentication(GEP_Connection.createConnection(infoTextField));
					}
				} catch (final SQLException e) {
					infoTextField.setText(e.getMessage() + " " + new GEP_Unique_Box().getClass().getSimpleName());
					infoTextField.setForeground(Color.RED);
				}
			}

			private void checkingAuthentication(Connection conObj) {
				final String usrName = usernametextField.getText();
				final String pswd = passwordField.getText();

				final List<GEP_User_Obj> userDataObj = GEP_User_Obj.GEP_User_DataList.createList(
						JdbcUniversalSelector.selectRecordFromTable(GEP_Connection.createConnection(infoTextField),
								GEP_User_Obj.TableName.TABLENAME, "id", GEP_Encoder.encodeData(usrName)),
						infoTextField);

				if (!userDataObj.isEmpty()) {
					if (pswd.equals(userDataObj.get(0).getWord())) {
						OnSystemUser.setSystemUser(userDataObj.get(0).getId(), userDataObj.get(0).getUser_authencity(),
								userDataObj.get(0).getLocation());
						openMainPanelUI();
					} else
						JOptionPane.showMessageDialog(null, "Incorrect password",
								new LoginUI().getClass().getSimpleName(), javax.swing.JOptionPane.ERROR_MESSAGE);
				} else if (!(usernametextField.getText().length() >= 1))
					JOptionPane.showMessageDialog(null, "ECOD: Your Operation Cant Be Recognised With Null User",
							new LoginUI().getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
				else if (OnSystemUser.GetUserAutho.USERNAME.equals(usernametextField.getText())) {
					JOptionPane.showMessageDialog(null, "Establishing initial connection... Please Try Again...",
							new LoginUI().getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
					saveSalesObj();
				} else
					JOptionPane.showMessageDialog(null, "User @" + usernametextField.getText() + " not in the system",
							new LoginUI().getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
			}

			private void openMainPanelUI() {
				/*
				 * JLabel backgroungImage_Label = ReadImage.readBackground_Image(new
				 * MainPanelUI().getClass().getSimpleName());
				 *
				 * MainPanelUI dialog = new MainPanelUI(); dialog.setLocationRelativeTo(null);
				 * dialog.getContentPane().add(backgroungImage_Label); dialog.setVisible(true);
				 */

				dispose();
				final GEP_M frame = new GEP_M();
				frame.setSize(1250, 750);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}

			private void saveSalesObj() {
				try {
					new GEP_CRUD(GEP_SystemUserDetails.getUserDataEntries(), utilObj.getSessionFactory()).saveObject();
				} catch (final Exception e) {
					infoTextField.setText(e.getMessage() + " " + new GEP_M().getClass().getSimpleName());
					infoTextField.setForeground(Color.RED);
				}

			}
		});
		getContentPane().add(btnLogIn);

		final JButton btnGuest = new JButton("guest");
		springLayout.putConstraint(SpringLayout.NORTH, btnGuest, 6, SpringLayout.SOUTH, lblPassword);
		springLayout.putConstraint(SpringLayout.WEST, btnGuest, 0, SpringLayout.WEST, lblUserName);
		getContentPane().add(btnGuest);

		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, btnLogIn, 8, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 6, SpringLayout.SOUTH, usernametextField);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 14, SpringLayout.EAST, lblPassword);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, usernametextField);
		getContentPane().add(passwordField);
		passwordField.setColumns(10);

		final JButton btnTerminate = new JButton("terminate");
		btnTerminate.addActionListener(e -> dispose());
		springLayout.putConstraint(SpringLayout.SOUTH, btnTerminate, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnTerminate, 0, SpringLayout.EAST, usernametextField);
		getContentPane().add(btnTerminate);

		infoTextField = new JTextField();
		infoTextField.setBackground(Color.LIGHT_GRAY);
		springLayout.putConstraint(SpringLayout.WEST, infoTextField, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, infoTextField, -16, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, infoTextField, 0, SpringLayout.EAST, lblPassword);
		getContentPane().add(infoTextField);
		infoTextField.setEditable(false);
		infoTextField.setColumns(10);
	}
}
