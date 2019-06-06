package grainindustries.com.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import grainindustries.com.db.models.GEP_User_Obj;
import grainindustries.com.db.userdata.JdbcSelectLoginAccount;
import grainindustries.com.db.userdata.OnSystemUser;
import pjp.akidamjaffar.config.GEP_Connection;
import pjp.akidamjaffar.config.GEP_Unique_Box;
import pjp.akidamjaffar.config.GEP_Util;
import pjp.akidamjaffar.crud.GEP_CRUD;
import pjp.akidamjaffar.crypto.GEP_Decoder;
import pjp.akidamjaffar.crypto.GEP_Encoder;
import pjp.akidamjaffar.dateconversion.GEP_UtilDateToSqlDate;

@SuppressWarnings("serial")
public class GEP_UserPanelUI extends JDialog {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField unamecreatejTextField;
	private JPasswordField passwordcreatejPasswordField;
	private JTextField confirmpasswordcreatejTextField;
	private JTextField systemusnameupdate;
	private JPasswordField systempassupdate;
	private JTextField infoTextField;

	/**
	 * Create the dialog.
	 */
	public GEP_UserPanelUI() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 650, 600);
		setLocationRelativeTo(null);

		final JLabel lblAdminPanel = new JLabel("Admin Panel");
		lblAdminPanel.setForeground(new Color(255, 51, 0));
		lblAdminPanel.setBackground(Color.WHITE);

		final JLabel lblCreateNewSystem = new JLabel("Create New System User");
		lblCreateNewSystem.setForeground(new Color(255, 102, 0));

		final JLabel lblUserName = new JLabel("User name");

		unamecreatejTextField = new JTextField();
		unamecreatejTextField.setColumns(10);

		final JLabel lblPasssword = new JLabel("Passsword");

		passwordcreatejPasswordField = new JPasswordField();

		final JLabel lblConfirmPswd = new JLabel("Confirm pswd");

		confirmpasswordcreatejTextField = new JTextField();
		confirmpasswordcreatejTextField.setColumns(10);

		final JLabel lblViewSystemUsers = new JLabel("View System Users");

		final JButton btnView = new JButton("View");
		btnView.addActionListener(arg0 -> {
			final GEP_User frame = new GEP_User();
			frame.setSize(1100, 600);
			frame.setLocationRelativeTo(null);
			frame.setModal(true);
			frame.setVisible(true);
		});

		final JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			private GEP_Util<GEP_User_Obj> utilObj;
			private GEP_User_Obj user_DataObj;

			@Override
			public void actionPerformed(ActionEvent arg0) {

				utilObj = new GEP_Util<>(GEP_User_Obj.class);

				if (!(unamecreatejTextField.getText().length() >= 1)) {
					infoTextField.setText(
							"ECOD: User Name Can Never Be Left Undefined " + new GEP_M().getClass().getSimpleName());
					infoTextField.setForeground(Color.RED);
				} else if (!(passwordcreatejPasswordField.getText().toString().length() >= 1)) {
					infoTextField.setText(
							"ECOD: Sorry We Dont Accept Blank Password " + new GEP_M().getClass().getSimpleName());
					infoTextField.setForeground(Color.RED);
				} else if (!passwordcreatejPasswordField.getText().toString()
						.equals(confirmpasswordcreatejTextField.getText().toString())) {
					infoTextField.setText("ECOD: Password Mismatch" + new GEP_M().getClass().getSimpleName());
					infoTextField.setForeground(Color.RED);
				} else {
					saveUserObj(getGEP_User_Data());
					infoTextField.setText("User@ " + GEP_Decoder.decodeData(user_DataObj.getId().toString())
							+ " generated successful " + new GEP_M().getClass().getSimpleName());
					infoTextField.setForeground(Color.RED);

				}
			}

			private GEP_User_Obj getGEP_User_Data() {
				// TODO Auto-generated method stub
				user_DataObj = new GEP_User_Obj();

				user_DataObj.setId(GEP_Encoder.encodeData(GEP_Unique_Box.getUniqueIndex(infoTextField,
						GEP_User_Obj.Unique_Box.UNIQUE_BOX, GEP_User_Obj.TableName.TABLENAME)));
				user_DataObj.setName(GEP_Encoder.encodeData(unamecreatejTextField.getText().toString()));
				user_DataObj.setWord(GEP_Encoder.encodeData(passwordcreatejPasswordField.getText().toString()));
				user_DataObj.setLocation(GEP_Encoder.encodeData("defualt"));
				user_DataObj.setCreator(GEP_Encoder.encodeData(OnSystemUser.getUserName().toString()));
				user_DataObj.setActive_mult(GEP_Encoder.encodeData("0"));
				user_DataObj.setLast_log(GEP_UtilDateToSqlDate.convertUtilToSqlDate(new Date().toString()));
				user_DataObj.setUser_authencity(GEP_Encoder.encodeData(OnSystemUser.GetUserAutho.NORMAL));

				return user_DataObj;
			}

			private void saveUserObj(GEP_User_Obj user_DataObj) {
				try {
					new GEP_CRUD(user_DataObj, utilObj.getSessionFactory()).saveObject();

					JOptionPane.showMessageDialog(null,
							"ABS001: Kindly Note Down User Id "
									+ GEP_Decoder.decodeData(user_DataObj.getId().toString()),
							new GEP_UserPanelUI().getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);

				} catch (final Exception e) {
					infoTextField.setText(e.getMessage() + " " + new GEP_M().getClass().getSimpleName());
					infoTextField.setForeground(Color.RED);
				}

			}

		});

		final JLabel lblSystemUsersPanel = new JLabel("System User's Panel");
		lblSystemUsersPanel.setForeground(Color.RED);

		final JLabel lblUpdateUsersInfo = new JLabel("Update User's Info");
		lblUpdateUsersInfo.setForeground(new Color(255, 102, 0));

		final JLabel label = new JLabel("User name");

		systemusnameupdate = new JTextField();
		systemusnameupdate.setColumns(10);

		systempassupdate = new JPasswordField();

		final JLabel label_1 = new JLabel("Passsword");

		final JButton button = new JButton("Update");
		button.addActionListener(arg0 -> {

			if (systemusnameupdate.getText().length() >= 1) {
				if (OnSystemUser.getUserName().equals(systemusnameupdate.getText())) {

					final List<GEP_User_Obj> userDataObj = GEP_User_Obj.GEP_User_DataListForUpdate.createList(
							JdbcSelectLoginAccount.selectRecordFromTable(GEP_Connection.createConnection(infoTextField),
									GEP_Encoder.encodeData(systemusnameupdate.getText().toString())),
							infoTextField);

					if (!userDataObj.isEmpty()) {
						if (systempassupdate.getText().length() >= 1) {
							userDataObj.get(0).setWord(GEP_Encoder.encodeData(systempassupdate.getText().toString()));

							new GEP_CRUD(userDataObj.get(0), new GEP_Util<>(GEP_User_Obj.class).getSessionFactory())
									.saveorupdateObject();
							infoTextField.setText("User@ " + userDataObj.get(0).getId() + "s' password changed to "
									+ userDataObj.get(0).getWord().toString() + new GEP_M().getClass().getSimpleName());
							infoTextField.setForeground(Color.RED);
						} else
							JOptionPane.showMessageDialog(null,
									"ECODE: Sorry But You Are Not Allowed To Nullify Your Account",
									new GEP_UserPanelUI().getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null,
								"ECODE: User@" + systemusnameupdate.getText() + " Updation Failed, Try Again",
								new GEP_UserPanelUI().getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null,
							"ECODE: Sorry But Your Are Not Allowed To Interfere With Any Ones Account At This Panel",
							new GEP_UserPanelUI().getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "ECODE: Sorry But We Cant Operate On Null User",
						new GEP_UserPanelUI().getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
		});

		final JLabel lblExitSystemPanel = new JLabel("Exit System Panel");
		lblExitSystemPanel.setForeground(new Color(255, 102, 0));

		final JButton btnLogOut = new JButton("exit");
		btnLogOut.addActionListener(e -> dispose());
		final SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.EAST, btnLogOut, 0, SpringLayout.EAST, systempassupdate);
		springLayout.putConstraint(SpringLayout.NORTH, btnCreate, 31, SpringLayout.SOUTH,
				confirmpasswordcreatejTextField);
		springLayout.putConstraint(SpringLayout.NORTH, confirmpasswordcreatejTextField, 0, SpringLayout.NORTH,
				lblConfirmPswd);
		springLayout.putConstraint(SpringLayout.SOUTH, confirmpasswordcreatejTextField, 19, SpringLayout.NORTH,
				lblConfirmPswd);
		springLayout.putConstraint(SpringLayout.EAST, confirmpasswordcreatejTextField, 0, SpringLayout.EAST, btnView);
		springLayout.putConstraint(SpringLayout.WEST, btnCreate, 176, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, button, 129, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, button, 515, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, button, 613, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, systemusnameupdate, 54, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, systemusnameupdate, 480, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, systemusnameupdate, 613, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, systempassupdate, 79, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, systempassupdate, 480, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, systempassupdate, 613, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, label_1, 81, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_1, 389, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, label_1, 468, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, label, 56, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label, 389, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, label, 468, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblSystemUsersPanel, 12, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblSystemUsersPanel, 389, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblUpdateUsersInfo, 33, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblUpdateUsersInfo, 389, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, unamecreatejTextField, 54, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, unamecreatejTextField, 119, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, unamecreatejTextField, 252, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, passwordcreatejPasswordField, 79, SpringLayout.NORTH,
				getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, passwordcreatejPasswordField, 119, SpringLayout.WEST,
				getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, passwordcreatejPasswordField, 252, SpringLayout.WEST,
				getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, confirmpasswordcreatejTextField, 119, SpringLayout.WEST,
				getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblConfirmPswd, 106, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblConfirmPswd, 12, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblPasssword, 81, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblPasssword, 12, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblPasssword, 107, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblUserName, 56, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblUserName, 12, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblUserName, 107, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblCreateNewSystem, 33, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCreateNewSystem, 12, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblAdminPanel, 12, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAdminPanel, 12, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnView, 227, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnView, 188, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblViewSystemUsers, 237, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblViewSystemUsers, 12, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblExitSystemPanel, 475, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblExitSystemPanel, 12, SpringLayout.WEST, getContentPane());
		getContentPane().setLayout(springLayout);
		getContentPane().add(lblExitSystemPanel);
		getContentPane().add(btnLogOut);
		getContentPane().add(lblViewSystemUsers);
		getContentPane().add(btnView);
		getContentPane().add(lblAdminPanel);
		getContentPane().add(lblCreateNewSystem);
		getContentPane().add(lblUserName);
		getContentPane().add(lblPasssword);
		getContentPane().add(lblConfirmPswd);
		getContentPane().add(confirmpasswordcreatejTextField);
		getContentPane().add(passwordcreatejPasswordField);
		getContentPane().add(btnCreate);
		getContentPane().add(unamecreatejTextField);
		getContentPane().add(lblUpdateUsersInfo);
		getContentPane().add(lblSystemUsersPanel);
		getContentPane().add(label);
		getContentPane().add(label_1);
		getContentPane().add(systempassupdate);
		getContentPane().add(systemusnameupdate);
		getContentPane().add(button);

		infoTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, btnLogOut, -3, SpringLayout.NORTH, infoTextField);
		infoTextField.setBackground(Color.GRAY);
		springLayout.putConstraint(SpringLayout.NORTH, infoTextField, 12, SpringLayout.SOUTH, lblExitSystemPanel);
		springLayout.putConstraint(SpringLayout.WEST, infoTextField, 12, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, infoTextField, -10, SpringLayout.EAST, lblUpdateUsersInfo);
		getContentPane().add(infoTextField);
		infoTextField.setEditable(false);
		infoTextField.setColumns(10);
	}
}
