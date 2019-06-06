package pjp.akidamjaffar.crypto;

import sun.misc.BASE64Encoder;

public class GEP_Encoder {

	public static String encodeData(String stringObj) {
		final BASE64Encoder encoderObj = new BASE64Encoder();

		return new String(encoderObj.encodeBuffer(stringObj.getBytes()));
	}

	private GEP_Encoder() {
		// TODO Auto-generated constructor stub
	}
}
