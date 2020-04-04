import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {

        Map<String, Integer> rank = new HashMap<>();
        Map<String, PriorityQueue<Song>> map = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            rank.put(genres[i], rank.getOrDefault(genres[i], 0) + plays[i]);

            if (!map.containsKey(genres[i])) {
                PriorityQueue<Song> pq = new PriorityQueue<>();
                map.put(genres[i], pq);
            }

            map.get(genres[i]).add(new Song(i, plays[i]));
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(rank.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        List<Integer> answer = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : list) {
            String key = entry.getKey();
            PriorityQueue<Song> pq = map.get(key);

            int count = 2;
            while(!pq.isEmpty() && count > 0) {
                answer.add(pq.poll().idx);
                count--;
            }
        }

        int[] ans = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            ans[i] = answer.get(i);
        }
        
        return ans;
    }

    class Song implements Comparable<Song> {
        int idx;
        int play;

        Song(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }

        @Override
        public int compareTo(Song o) {
            if (o.play != this.play) {
                return o.play - this.play;
            }
            return this.idx - o.idx;
        }
    }
}