package utils;

import org.apache.commons.text.RandomStringGenerator;

public class RandStings {
    private static RandomStringGenerator generator = new RandomStringGenerator.Builder()
            .withinRange('a', 'z')
            .build();

    public static String appendRandom(String base, int maxLength) {
        int charactersAppend = maxLength - base.length() - 1;
        String randomLetters = generator.generate(charactersAppend);
        return base+"_"+randomLetters;
    }
}
