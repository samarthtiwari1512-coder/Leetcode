import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Build dictionary from knowledge
        Map<String, String> dict = new HashMap<>();
        for (List<String> entry : knowledge) {
            dict.put(entry.get(0), entry.get(1));
        }

        StringBuilder res = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean insideBracket = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                insideBracket = true;
                key.setLength(0); // Reset key buffer
            } else if (c == ')') {
                insideBracket = false;
                res.append(dict.getOrDefault(key.toString(), "?"));
            } else {
                if (insideBracket) {
                    key.append(c);
                } else {
                    res.append(c);
                }
            }
        }

        return res.toString();
    }
}