package common.helper.streaming;

import java.util.Arrays;
import java.util.HashSet;

public class PrimeVideoStreamingService implements VideoStreamingService {
    private static final HashSet<String> availableMovies = new HashSet<>(Arrays.asList("Kung fu panda", "Superman"));
    @Override
    public void streamVideo(String movieName) {
        System.out.println("Streaming " + movieName + " on netflix");
    }

    public static boolean hasMovie(String movieName) {
        return availableMovies.contains(movieName);
    }
}
