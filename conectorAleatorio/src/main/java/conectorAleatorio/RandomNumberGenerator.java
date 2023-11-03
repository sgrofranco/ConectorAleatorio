package conectorAleatorio;

import java.util.Random;

public class RandomNumberGenerator implements Randomizer {
    private final Random random;

    public RandomNumberGenerator() {
        this.random = new Random();
    }

    public int getRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
