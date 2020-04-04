class Solution {
    public boolean solution(String[] phone_book) {

        Trie root = new Trie();
        root.fin = false;

        for (String phone : phone_book) {

            Trie current = root;

            for (int i = 0; i < phone.length(); i++) {
                int num = phone.charAt(i) - '0';

                if (current.child[num] == null) {
                    current.child[num] = new Trie();
                }

                current = current.child[num];

                if (current.fin) {
                    return false;
                }
            }

            current.fin = true;

            for (int i = 0; i < 10; i++) {
                if (current.child[i] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    static class Trie {
        Trie[] child = new Trie[10];
        boolean fin = false;
    }
}
