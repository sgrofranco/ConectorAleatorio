package conectorAleatorio;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class conectorGitHubTest {
    @Test
    public void testRun() {
        // Crear un FakeHTTPClient
        HTTPClient fakeClient = mock(HTTPClient.class);
        
        Randomizer random = new RandomNumberGenerator();

        // Crear una instancia de GitHubCIConnector con el FakeHTTPClient
        conectorGitHub conector = new conectorGitHub(fakeClient, random);

        // Ejecutar el conector CI
        conector.run();

        // Verificar que el FakeHTTPClient fue llamado
        verify(fakeClient, atLeastOnce()).get("https://github.com");
    }
    
   
    @Test
    public void testRandomIntervals() {
        // Crear un FakeHTTPClient y un RandomNumberGenerator
        HTTPClient fakeClient = new FakeHTTPClient();
        Randomizer randomizer = mock(Randomizer.class);
        when(randomizer.getRandomNumber(anyInt(), anyInt())).thenReturn(3000); // Simulamos un intervalo de 3 segundos

        // Crear una instancia de GitHubCIConnector con el FakeHTTPClient y RandomNumberGenerator
        conectorGitHub connector = new conectorGitHub(fakeClient, randomizer);

        // Ejecutar el conector CI durante un tiempo para registrar los tiempos entre solicitudes
        long startTime = System.currentTimeMillis();
        long lastRequestTime = startTime;

        for (int i = 0; i < 5; i++) { // Realizar 10 solicitudes para el test
            connector.run(); // Llamamos directamente al método para evitar el sleep

            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastRequestTime;

            assertTrue(elapsedTime >= 1000 && elapsedTime <= 5000); // Verificar que el tiempo está en el rango

            lastRequestTime = currentTime;
        }
    }
}