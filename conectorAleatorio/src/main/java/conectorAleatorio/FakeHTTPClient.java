package conectorAleatorio;

public class FakeHTTPClient implements HTTPClient {
    public void get(String url) {
        System.out.println("Simulated GET request to: " + url);
    }
}