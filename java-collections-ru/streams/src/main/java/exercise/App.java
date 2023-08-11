package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
public class App {

    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
            .filter(App::hasFreeDomain)
            .count();
    }

    private static boolean hasFreeDomain(String email) {
        var domain = getDomain(email);
        var freeDomains = List.of("gmail.com", "yandex.ru", "hotmail.com");
        return freeDomains.contains(domain);
    }

    private static String getDomain(String email) {
        var emailParts = email.split("@");
        var emailPartNumber = emailParts.length - 1;
        return emailPartNumber > 0 ? emailParts[emailPartNumber] : "";
    }
}
// END
