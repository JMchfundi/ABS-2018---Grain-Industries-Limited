package pjp.akidamjaffar.config;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ReadImage {
	private static String backgroungImage_Path;

	public static JLabel readBackground_Image(String panelName, JTextField infoTextField) {
		setBackgroungImage_Path(panelName);
		final File backgroundImage_File = new File(backgroungImage_Path);
		BufferedImage backgroungImage_Buffered = null;
		try {
			backgroungImage_Buffered = ImageIO.read(backgroundImage_File);
		} catch (final IOException e) {
			infoTextField.setText(e.getMessage() + " " + new ReadImage().getClass().getSimpleName());
			infoTextField.setForeground(Color.RED);
		}
		final JLabel backgroungImage_Label = new JLabel(new ImageIcon(backgroungImage_Buffered));

		return backgroungImage_Label;
	}

	private static void setBackgroungImage_Path(String panelName) {
		if (panelName != null)
			backgroungImage_Path = "images//" + panelName + ".png";
	}

	private ReadImage() {
	}
}
