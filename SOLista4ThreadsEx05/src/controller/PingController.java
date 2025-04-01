package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class PingController extends Thread {

	String server;

	public PingController(String server) {

		this.server = server;

	}

	@Override
	public void run() {

		try {

			// Verificando se o sistema é linux ou não

			Process process = null;
			String osName = System.getProperty("os.name");

			if (!osName.toLowerCase().contains("ux") || !osName.toLowerCase().contains("ix")
					|| !osName.toLowerCase().contains("mac")) {

				System.out.println("Software disponível apenas para sistemas linux");

			}

			process = Runtime.getRuntime().exec("ping -4 -c 10 " + server);

			String iteracao;

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String linha;

			// Se a linha conter informações de tempo médio ou tempo da iteração, será
			// impresso

			while ((linha = reader.readLine()) != null) {

				linha = linha.trim();

				System.out.println(server + " - ");

				if (linha.contains("ms")) {

					iteracao = linha.split("seq=")[1];
					iteracao = linha.split(" ")[0];

					System.out.println("Tempo da iteração" + iteracao + " : " + linha.split("time=")[1]);

				} else if (linha.contains("avg")) {

					System.out.println("Tempo médio: " + linha.split("/")[4]);

				}

			}

			process.waitFor();
			reader.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
