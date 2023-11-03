package conectorAleatorio;

import java.util.Random;

public class conectorGitHub implements CIConnector {
	private static final String GITHUB_URL = "https://github.com";
	private static final int MIN_INTERVAL = 1000; // 1 segundo
	private static final int MAX_INTERVAL = 5000; // 5 segundos

	private final Randomizer randomizer;
	private final HTTPClient httpClient;

	public conectorGitHub(HTTPClient httpClient, Randomizer randomizer) {
		this.randomizer = randomizer;
		this.httpClient = httpClient;
	}

	public void run() {

		waitRandomInterval();
		makeRequestToGitHub();

	}

	public void makeRequestToGitHub() {
		httpClient.get(GITHUB_URL);
	}

	private void waitRandomInterval() {
		int randomInterval = randomizer.getRandomNumber(MIN_INTERVAL, MAX_INTERVAL);
		try {
			Thread.sleep(randomInterval);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
