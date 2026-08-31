public class LibraryIsbnValidator {

    public static String normalizeCode(String raw) {
        if (raw == null) {
            return "";
        }
        
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed;
        }

        // Uppercase only the first 3 characters and keep the rest untouched
        String pubCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);

        return pubCode + rest;
    }

    public static String validateAndFormat(String code) {
        if (code == null || code.length() != 13) {
            return "Invalid: code must be exactly 13 characters";
        }

        // Validate first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Validate remaining 10 characters are digits
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must contain digits only";
            }
        }

        // Build formatted string
        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] ")
          .append("YEAR: ").append(year).append(" | ")
          .append("CATALOG: ").append(catalog);

        return sb.toString();
    }

    public static void main(String[] args) {
        String code1 = normalizeCode(" pen2026004251 ");
        System.out.println(validateAndFormat(code1));

        String code2 = normalizeCode("12N2026004251");
        System.out.println(validateAndFormat(code2));
    }
}
