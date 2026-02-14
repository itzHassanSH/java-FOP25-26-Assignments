package h12;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents the watch history of a user on a video streaming service.
 *
 * @param videos the videos in the watch history with the time they were watched
 * @author Nhan Huynh
 */
@DoNotTouch
public record UserHistory(List<Map.Entry<Video, LocalDateTime>> videos) {

    /**
     * The number of days after which videos are removed from the history.
     */
    @DoNotTouch
    private static final int CLEANUP_DAYS = 30;

    /**
     * Creates a new empty watch history.
     */
    @DoNotTouch
    public UserHistory() {
        this(new ArrayList<>());
    }

    /**
     * Adds a video to the watch history with the current time.
     *
     * @param video the video to add
     */
    @DoNotTouch
    public void add(Video video) {
        videos.add(Map.entry(video, LocalDateTime.now()));
    }

    /**
     * Returns the number of videos in the watch history.
     *
     * @return the number of videos in the watch history
     */
    @DoNotTouch
    public int size() {
        return videos.size();
    }

    /**
     * Removes videos from the watch history that were watched more than {@value CLEANUP_DAYS} ago.
     */
    @DoNotTouch
    public void cleanup() {
        videos.removeIf(entry -> entry.getValue().isBefore(LocalDateTime.now().minusDays(CLEANUP_DAYS)));
    }

    /**
     * Clears the watch history.
     */
    @DoNotTouch
    public void clear() {
        videos.clear();
    }

    /**
     * Returns all unique tags of the videos in the watch history.
     *
     * @return all unique tags of the videos in the watch history
     */
    @StudentImplementationRequired("H12.6.1")
    public Set<String> getTags() {
        // TODO: H12.6.1
        return videos.stream().flatMap(v -> v.getKey().metaData().stream().flatMap(m -> m.tags().stream())).collect(Collectors.toSet());
        // Initially did: (seemed wrong because isPresent() was never checked)
        // return videos.stream().flatMap(v -> v.getKey().metaData().get().tags().stream()).collect(Collectors.toSet());
    }

    /**
     * Returns all unique categories of the videos in the watch history.
     *
     * @return all unique categories of the videos in the watch history
     */
    @StudentImplementationRequired("H12.6.1")
    public Set<Category> getCategories() {
        // TODO: H12.6.1
        return videos.stream().flatMap(v -> v.getKey().metaData().stream().map(VideoMetaData::category)).collect(Collectors.toSet());
    }

    /**
     * Returns all unique channels of the videos in the watch history, sorted by the time they were watched (oldest first).
     *
     * @return all unique channels of the videos in the watch history, sorted by the time they were watched (oldest first)
     */
    @StudentImplementationRequired("H12.6.1")
    public List<Channel> getChannels() {
        // TODO: H12.6.1
        // LocalDateTime already implements Comparable<T>, therefore comparingByValue() should work to order channels chronologically
        return videos.stream().sorted(Map.Entry.comparingByValue()).map(v -> v.getKey().channel()).distinct().toList();
    }

    /**
     * Returns all unique videos uploaded by the given user in the watch history.
     *
     * @param user the user to get the videos of
     * @return all unique videos from the given user in the watch history
     */
    @StudentImplementationRequired("H12.6.2")
    public Set<Video> getVideosFromUploader(User user) {
        // TODO: H12.6.2
        return videos.stream().map(Map.Entry::getKey).filter(x -> x.uploader() == user).collect(Collectors.toSet());
    }

    /**
     * Returns all unique videos from the given channel in the watch history.
     *
     * @param channel the channel to get the videos from
     * @return all unique videos from the given channel in the watch history
     */
    @StudentImplementationRequired("H12.6.2")
    public Set<Video> getVideosFromChannel(Channel channel) {
        // TODO: H12.6.2
        return videos.stream().map(Map.Entry::getKey).filter(x -> x.channel() == channel).collect(Collectors.toSet());
    }

    /**
     * Returns all unique videos with the given tag in the watch history.
     *
     * @param tag the tag to get the videos from
     * @return all unique videos with the given tag in the watch history
     */
    @StudentImplementationRequired("H12.6.2")
    public Set<Video> getVideosFromTag(String tag) {
        // TODO: H12.6.2
        // First filter out all the non-value Optionals and only then in the next filter use .get() to check for tag
        // Other way of dissolving the Optional to stream doesn't work here as streams shouldn't exist in .filter, unless a valid operator can
        // dissolve the stream back to required values
        return videos.stream().filter(e -> e.getKey().metaData().isPresent()).filter(e -> e.getKey().metaData().get().tags().contains(tag)).map(Map.Entry::getKey).collect(Collectors.toSet());
    }
}
