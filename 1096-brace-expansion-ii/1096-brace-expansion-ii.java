import java.util.*;

class Solution {
    private int index = 0;
    private String s;

    public List<String> braceExpansionII(String expression) {
        this.s = expression;
        this.index = 0;

        Set<String> resultSet = parseExpr();

        List<String> sortedList = new ArrayList<>(resultSet);
        Collections.sort(sortedList);
        return sortedList;
    }

    // Parses a concatenation of factors until hitting ',' or '}' or EOF
    private Set<String> parseExpr() {
        Set<String> current = new HashSet<>();
        current.add("");

        while (index < s.length() && s.charAt(index) != '}' && s.charAt(index) != ',') {
            Set<String> nextFactor = parseFactor();
            current = cartesianProduct(current, nextFactor);
        }

        return current;
    }

    // Parses either a literal string or a brace group {...}
    private Set<String> parseFactor() {
        if (s.charAt(index) == '{') {
            index++; // consume '{'
            Set<String> unionSet = new HashSet<>();

            while (true) {
                unionSet.addAll(parseExpr());
                if (index < s.length() && s.charAt(index) == ',') {
                    index++; // consume ','
                } else {
                    break;
                }
            }

            index++; // consume '}'
            return unionSet;
        } else {
            // Read contiguous lowercase letters
            StringBuilder sb = new StringBuilder();
            while (index < s.length() && Character.isLowerCase(s.charAt(index))) {
                sb.append(s.charAt(index));
                index++;
            }
            Set<String> literalSet = new HashSet<>();
            literalSet.add(sb.toString());
            return literalSet;
        }
    }

    private Set<String> cartesianProduct(Set<String> setA, Set<String> setB) {
        Set<String> res = new HashSet<>();
        for (String a : setA) {
            for (String b : setB) {
                res.add(a + b);
            }
        }
        return res;
    }
}