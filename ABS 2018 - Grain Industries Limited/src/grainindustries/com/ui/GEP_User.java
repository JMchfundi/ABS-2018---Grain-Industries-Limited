package grainindustries.com.ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import grainindustries.com.db.models.GEP_DB2JTable_Users;
import grainindustries.com.db.models.GEP_User_Obj;
import grainindustries.com.db.userdata.JdbcSelectLoginAccount;
import grainindustries.com.db.userdata.OnSystemUser;
import pjp.akidamjaffar.config.GEP_Connection;
import pjp.akidamjaffar.config.GEP_Util;
import pjp.akidamjaffar.crud.GEP_CRUD;
import pjp.akidamjaffar.crud.GEP_SELECT;
import pjp.akidamjaffar.crypto.GEP_Encoder;

public class GEP_User extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 4506121008230234111L;
	private final JPanel contentPane;
	private final JTable table;
	private JTextField infoTextField;

	/**
	 * Create the frame.
	 */
	public GEP_User() {
		setResizable(false);
		setBounds(100, 100, 550, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(244, 164, 96));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		final SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		final JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 100, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, 400, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPane);

		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			private GEP_Util<GEP_User_Obj> utilObj;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				utilObj = new GEP_Util<>(GEP_User_Obj.class);

				if (table.getModel()
						.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), table.getSelectedColumn())
						.toString().equalsIgnoreCase("true")) {

					List<GEP_User_Obj> user_Objs = null;

					user_Objs = GEP_User_Obj.GEP_User_DataListForUpdate.createList(
							JdbcSelectLoginAccount.selectRecordFromTable(GEP_Connection.createConnection(infoTextField),
									GEP_Encoder.encodeData(table.getModel()
											.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 1)
											.toString())),
							infoTextField);

					for (final GEP_User_Obj gep_User_Obj : user_Objs) {

						gep_User_Obj.setName(GEP_Encoder.encodeData(table.getModel()
								.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 2).toString()));
						gep_User_Obj.setLocation(GEP_Encoder.encodeData(table.getModel()
								.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 3).toString()));
						gep_User_Obj.setCreator(GEP_Encoder.encodeData(OnSystemUser.getUserName()));
						gep_User_Obj.setUser_authencity(GEP_Encoder.encodeData(table.getModel()
								.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 7).toString()));

						if (OnSystemUser.GetUserAutho.ADMIN.equals(OnSystemUser.getUserAutho()))
							updateGEP_User_Obj(gep_User_Obj);
						else
							JOptionPane.showMessageDialog(null,
									"ECODE: User " + OnSystemUser.getUserName()
											+ " Not Permitted for Updating Other Users",
									new GEP_User().getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
					}

				}
			}

			private void updateGEP_User_Obj(GEP_User_Obj user_Obj) {
				try {
					new GEP_CRUD(user_Obj, utilObj.getSessionFactory()).saveorupdateObject();

					/*
					 * JOptionPane.showMessageDialog(null, "Updation for " +
					 * GEP_Decoder.decodeData(user_Obj.getName()) + " Succeded By " +
					 * OnSystemUser.getUserName(), new GEP_M().getClass().getSimpleName(),
					 * JOptionPane.ERROR_MESSAGE);
					 */
				} catch (final Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_User().getClass().getSimpleName(),
							JOptionPane.ERROR_MESSAGE);
				}
			}// TODO Auto-generated method stub

		});

		table.setModel(new DefaultTableModel(new Object[][] {},

				new String[] { "#", "User ID", "Name", "Location", "Active Counts", "Last Count" }));
		scrollPane.setViewportView(table);

		final JButton btnView = new JButton("Refresh");
		btnView.addActionListener(arg0 -> viewUsers());
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnView, -6, SpringLayout.NORTH, scrollPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnView, -10, SpringLayout.EAST, contentPane);
		contentPane.add(btnView);

		final JButton btnGenerateUser = new JButton("Terminate User");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnGenerateUser, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnGenerateUser, 0, SpringLayout.SOUTH, contentPane);
		contentPane.add(btnGenerateUser);

		final JButton btnUpdateUser = new JButton("Update User");
		sl_contentPane.putConstraint(SpringLayout.EAST, btnUpdateUser, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnUpdateUser, 0, SpringLayout.SOUTH, contentPane);
		contentPane.add(btnUpdateUser);

		final JButton btnExit = new JButton("Exit");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnExit, 0, SpringLayout.NORTH, contentPane);
		btnExit.addActionListener(arg0 -> dispose());
		sl_contentPane.putConstraint(SpringLayout.EAST, btnExit, -10, SpringLayout.EAST, contentPane);
		contentPane.add(btnExit);

		viewUsers();
	}

	private void viewUsers() {
		// TODO Auto-generated method stub
		GEP_DB2JTable_Users.readData(table,
				GEP_User_Obj.GEP_User_DataList
						.createList(GEP_SELECT.getRecords(GEP_Connection.createConnection(infoTextField),
								GEP_User_Obj.TableName.TABLENAME, infoTextField), infoTextField),
				infoTextField);
	}
}
