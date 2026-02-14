package h12;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import javax.smartcardio.CardTerminal;
import java.sql.Time;
import java.time.Duration;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents a video streaming service.
 *
 * @param accounts the user accounts on the service
 * @author Nhan Huynh
 */
@DoNotTouch
public record VideoStreamingService(
    Set<UserAccount> accounts
) {

    /**
     * Creates an empty video streaming service.
     */
    @DoNotTouch
    public VideoStreamingService() {
        this(new HashSet<>());
    }

    /**
     * Adds a user account to the service. If an account with the same user already exists or the streaming service of
     * the user does not match this streaming service, the account is not added.
     *
     * @param account the account to add
     * @return {@code true} if the account was added, {@code false} if an account with the same user already exists or
     * the streaming service of the user does not match this streaming service
     */
    @StudentImplementationRequired("H12.7.1")
    public boolean addUserAccount(UserAccount account) {
        // TODO: H12.7.1
        return (accounts.stream().anyMatch(a -> a == account) || accounts.stream().anyMatch(a -> a.user().streamingService() == account.user().streamingService()))? false: accounts.add(account);

    }

    /**
     * Removes a user account from the service, including all references to the user account.
     * If the account does not exist, nothing happens.
     *
     * @param account the account to remove
     * @return {@code true} if the account was removed, {@code false} if the account did not exist
     */
    @DoNotTouch
    public boolean removeUserAccount(UserAccount account) {
        if (accounts.remove(account)) {
            account.history().clear();
            account.ownedChannels().forEach(channel -> channel.subscribers().remove(account.user()));
            account.subscribedChannels().forEach(channel -> channel.subscribers().remove(account.user()));
            return true;
        }
        return false;
    }

    /**
     * Returns the user account with the given display name.
     *
     * @param displayName the display name of the user
     * @return the user account with the given display name
     * @throws IllegalArgumentException if no user with the given display name exists
     */
    @StudentImplementationRequired("H12.7.2")
    public UserAccount getUserAccountByName(String displayName) {
        // TODO: H12.7.2
        return accounts.stream().filter(a -> a.user().displayName().equals(displayName)).findAny().orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Returns the most active user, i.e., the user with the most videos in their watch history.
     *
     * @return the most active user, or an empty Optional if there are no users
     */
    @StudentImplementationRequired("H12.7.3")
    public Optional<User> getMostActiveUser() {
        // TODO: H12.7.3
        return accounts.stream().map(UserAccount::user).max(Comparator.comparing(User::getTotalWatchTime));
    }

    /**
     * Returns the top watched videos across all user accounts, sorted by the number of times they were watched (most
     * watched first).
     *
     * @param count the maximum number of videos to return
     * @return the top watched videos
     */
    @StudentImplementationRequired("H12.7.4")
    public List<Video> getTopWatchedVideos(int count) {
        // TODO: H12.7.4
        return accounts.stream().flatMap(a -> a.history().videos().stream().map(Map.Entry::getKey)).distinct().sorted((v1, v2) -> Integer.compare(v2.metaData().map(x -> x.viewers().size()).orElse(0), v1.metaData().map(x -> x.viewers().size()).orElse(0))).limit(count).toList();
//        return accounts.stream().flatMap(a -> a.videos().stream()).distinct().sorted((v1,v2) -> Integer.compare(v2.metaData().map(x -> x.viewers().size()).orElse(0),v1.metaData().map(x -> x.viewers().size()).orElse(0))).limit(count).toList();
    }

    /**
     * Returns all users who have watched at least one video in the given category.
     *
     * @param categoryName the name of the category
     * @return all users who have watched at least one video in the given category
     */
    @StudentImplementationRequired("H12.7.5")
    public Set<User> getUserByCategory(String categoryName) {
        // TODO: H12.7.5
        return accounts.stream().filter(a -> a.history().videos().stream().anyMatch(v -> v.getKey().metaData().map(x -> x.category().name().equals(categoryName)).orElse(false))).map(UserAccount::user).collect(Collectors.toSet());
        // Alternatively (slightly longer):
        // return accounts.stream().filter(a -> a.history().videos().stream().flatMap(v -> v.getKey().metaData().stream().map(VideoMetaData::category)).anyMatch(category -> category.name().equals(categoryName))).map(UserAccount::user).collect(Collectors.toSet());
    }

    /**
     * Returns the top categories from all playlists across all user accounts, sorted by the number of videos in each
     * category (most videos first).
     *
     * @param count the maximum number of categories to return
     * @return the top categories from all playlists
     */
    @StudentImplementationRequired("H12.7.6")
    public List<Category> getTopCategoriesFromAllPlaylists(int count) {
        // TODO: H12.7.6
        return accounts.stream().
            flatMap(a -> a.user().playlists().stream()
                .flatMap(p -> p.videos().stream()).flatMap(v -> v.metaData().stream()).map(VideoMetaData::category))
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
            .entrySet().stream().sorted(Map.Entry.<Category, Long>comparingByValue().reversed()).map(Map.Entry::getKey).limit(count).toList();

    }
}
