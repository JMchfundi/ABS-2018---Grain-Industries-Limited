package pjp.akidamjaffar.crypto;

import java.io.IOException;

import javax.swing.JOptionPane;

import sun.misc.BASE64Decoder;

public class GEP_Decoder {
	private static String stringObj;

	public static String decodeData(String stringObj) {
		final BASE64Decoder decoderObj = new BASE64Decoder();

		try {
			GEP_Decoder.stringObj = new String(decoderObj.decodeBuffer(stringObj));
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_Decoder().getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
		}

		return GEP_Decoder.stringObj;
	}

	private GEP_Decoder() {
		// TODO Auto-generated constructor stub
	}
}
