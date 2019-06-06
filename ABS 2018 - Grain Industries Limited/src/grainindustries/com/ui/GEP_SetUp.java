package grainindustries.com.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import pjp.akidamjaffar.config.ReadImage;

public class GEP_SetUp extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 4506121008230234111L;
	private static JTextField infoTextField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				final GEP_SetUp frame = new GEP_SetUp();
				frame.setSize(770, 400);
				frame.getContentPane()
						.add(ReadImage.readBackground_Image(new GEP_SetUp().getClass().getSimpleName(), infoTextField));
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		});
	}

	private final JPanel contentPane;

	private final JButton estConnnection;

	/**
	 * Create the frame.
	 */
	public GEP_SetUp() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				new Runnable() {
					@Override
					public void run() {
						estConnnection.setVisible(true);
						infoTextField.setText(null);
						infoTextField.setBackground(new Color(244, 164, 96));
					}
				};
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				estConnnection.setVisible(true);
				infoTextField.setText(null);
				infoTextField.setBackground(new Color(244, 164, 96));
			}
		});

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 770, 400);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		final SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		infoTextField = new JTextField();
		infoTextField.setBackground(new Color(161, 137, 97));
		infoTextField.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, infoTextField, -20, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, infoTextField, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, infoTextField, -10, SpringLayout.EAST, contentPane);
		contentPane.add(infoTextField);
		infoTextField.setVisible(false);
		infoTextField.setColumns(10);

		estConnnection = new JButton("Establish Connection");
		estConnnection.addActionListener(arg0 -> {
			dispose();
			final GEP_M frame = new GEP_M();
			frame.setSize(1250, 750);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, estConnnection, -70, SpringLayout.NORTH, infoTextField);
		sl_contentPane.putConstraint(SpringLayout.WEST, estConnnection, 22, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, estConnnection, -15, SpringLayout.NORTH, infoTextField);
		sl_contentPane.putConstraint(SpringLayout.EAST, estConnnection, 319, SpringLayout.WEST, contentPane);
		estConnnection.setVisible(false);
		contentPane.add(estConnnection);

	}
}
