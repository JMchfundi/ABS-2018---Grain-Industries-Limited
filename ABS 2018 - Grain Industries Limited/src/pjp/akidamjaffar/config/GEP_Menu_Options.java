
package pjp.akidamjaffar.config;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public final class GEP_Menu_Options extends JPopupMenu {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	JMenuItem settingsMenuItem;

	public GEP_Menu_Options() {
		settingsMenuItem = new JMenuItem("Settings");

		add(settingsMenuItem);
	}
}
