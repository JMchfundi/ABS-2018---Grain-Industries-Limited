package pjp.akidamjaffar.crud;

import java.io.Serializable;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GEP_CRUD {

	private Object objectData;
	private Session sessionObj;
	private Transaction transactionObj;
	private Serializable serializableObj;

	public GEP_CRUD(Object objectData, SessionFactory sessionFactoryObj) {
		// Initializing ObjectData & sessionObj
		this.objectData = objectData;

		sessionObj = sessionFactoryObj.openSession();
	}

	private GEP_CRUD() {
		// TODO Auto-generated constructor stub
	}

	public Serializable saveObject() {

		transactionObj = sessionObj.beginTransaction();
		serializableObj = (Serializable) sessionObj.save(objectData);

		try {
			transactionObj.commit();
		} catch (final Exception e) {
			transactionObj.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_CRUD().getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
		}

		sessionObj.close();

		return serializableObj;
	}

	public void saveorupdateObject() {

		transactionObj = sessionObj.beginTransaction();
		sessionObj.saveOrUpdate(objectData);
		try {
			transactionObj.commit();

			JOptionPane.showMessageDialog(null, "Updation Succeded Successfully",
					new GEP_CRUD().getClass().getSimpleName(), JOptionPane.INFORMATION_MESSAGE);
		} catch (final Exception e) {
			transactionObj.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_CRUD().getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
		}

		sessionObj.close();
	}
}