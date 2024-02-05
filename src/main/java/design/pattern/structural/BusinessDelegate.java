package design.pattern.structural;


import common.helper.streaming.NetflixVideoStreamingService;
import common.helper.streaming.PrimeVideoStreamingService;
import common.helper.streaming.VideoStreamingService;

/**
 * {@link BusinessDelegate} is used to add an abstraction layer between presentation tier and business tier
 * */
public interface BusinessDelegate {
    void playMovie(String movieName);
}

class VideoStreamingLookup {
    public VideoStreamingService getVideoStreamingService(String movieName) {
        if (NetflixVideoStreamingService.hasMovie(movieName)) {
            return new NetflixVideoStreamingService();
        } else if (PrimeVideoStreamingService.hasMovie(movieName)) {
            return new PrimeVideoStreamingService();
        }

        throw new UnsupportedOperationException("Movie "+ movieName +" is not available to watch");
    }
}

class VideoStreamingDelegate implements BusinessDelegate {
    private final VideoStreamingLookup videoStreamingLookup = new VideoStreamingLookup();

    @Override
    public void playMovie(String movieName) {
        VideoStreamingService videoStreamingService = videoStreamingLookup.getVideoStreamingService(movieName);
        videoStreamingService.streamVideo(movieName);
    }

    public static void main(String[] args) {
        BusinessDelegate businessDelegate = new VideoStreamingDelegate();
        businessDelegate.playMovie("Superman");

        businessDelegate.playMovie("Mission Impossible");

        businessDelegate.playMovie("Jujutsu Kaisen");
    }
}
