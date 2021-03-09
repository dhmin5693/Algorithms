class Solution {
    public boolean isPalindrome(int x) {
        
        var origin = String.valueOf(x);
        var sb = new StringBuilder(origin);
        
        return origin.equals(sb.reverse().toString());
    }
}