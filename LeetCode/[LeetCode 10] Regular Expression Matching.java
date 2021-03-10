class Solution {
    public boolean isMatch(String s, String p) {

        if (s.isEmpty()) {
            return p.isEmpty();
        }

        if (p.contains("*")) {
            
            // do something...
            
            return false;
        }

        if (s.length() != p.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != p.charAt(i) && p.charAt(i) != '.') {
                return false;
            }
        }

        return true;
    }
}