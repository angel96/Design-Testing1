
package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utiles {

	public static String generateTicker() {
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");

		Date d = new Date();

		String formated = formato.format(d);

		Character[] ch = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
		};

		String c = "";

		Random random = new Random();

		for (int i = 0; i < 6; i++)
			c += ch[random.nextInt(ch.length)];

		return formated + c;
	}

	public static void main(final String[] args) {
		System.out.println(Utiles.generateTicker());
	}
}
