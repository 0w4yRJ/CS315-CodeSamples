package basic;

// Utility class for string manipulation.
class StringUtils {
    /**
     * Normalizes text in a natural way by:
     * 1. Trimming leading and trailing whitespace.
     * 2. Converting multiple consecutive spaces into a single space.
     * 3. Converting the text to title case.
     */
    public static String normalizeText(String input) {
        if (input == null || input.isEmpty()) return input;
        // First, trim and reduce extra spaces.
        String trimmed = input.trim().replaceAll("\\s+", " ");
        // Then, return the text in title case.
        return toTitleCase(trimmed);
    }

    // Capitalizes the first letter of a word.
    public static String capitalize(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        char firstChar = word.charAt(0);
        return Character.toUpperCase(firstChar) + word.substring(1).toLowerCase();
    }

    // Converts a sentence to title case.
    public static String toTitleCase(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return sentence;
        }
        String[] words = sentence.split(" ");
        StringBuilder titleCase = new StringBuilder();
        for (String word : words) {
            titleCase.append(capitalize(word)).append(" ");
        }
        return titleCase.toString().trim();
    }

}