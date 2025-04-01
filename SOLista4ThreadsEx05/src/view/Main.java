package view;

import controller.*;

public class Main {

	public static void main(String[] args) {

		String[] server = { "www.uol.com.br", "www.terra.com.br", "www.google.com.br" };

		for (int i = 0; i <= 2; i++) {

			PingController ping = new PingController(server[i]);
			ping.start();

		}

	}

}
