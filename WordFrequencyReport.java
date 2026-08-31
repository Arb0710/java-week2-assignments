import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordFrequencyReport {

    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.isEmpty()) {
            return;
        }

        // Stop words list
        Set<String> stopWords = new HashSet<>(Arrays.asList(
            "the", "was", "and", "a", "is", "of", "in"
        ));

        // Normalize text: lowercase and strip periods/commas
        String cleaned = feedback.toLowerCase()
                                 .replace(".", "")
                                 .replace(",", "");

        // Split into words by whitespace
        String[] words = cleaned.split("\\s+");

        // Count frequency of non-stop words
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty() && !stopWords.contains(word)) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        // Convert Map entries to List for sorting by frequency descending
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());
        entryList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Print formatted results
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        String feedback = "The mentor was great, the session was great and clear.";
        printFilteredWordFrequency(feedback);
    }
}
