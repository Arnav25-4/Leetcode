import java.util.*;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];

        for (char task : tasks) {
            freq[task - 'A']++;
        }

        PriorityQueue<Integer> pq =
            new PriorityQueue<>(Collections.reverseOrder());

        for (int f : freq) {
            if (f > 0) {
                pq.offer(f);
            }
        }

        int time = 0;

        while (!pq.isEmpty()) {
            List<Integer> remaining = new ArrayList<>();
            int cycle = n + 1;

            while (cycle > 0 && !pq.isEmpty()) {
                int count = pq.poll();

                if (count > 1) {
                    remaining.add(count - 1);
                }

                time++;
                cycle--;
            }

            for (int count : remaining) {
                pq.offer(count);
            }

            if (pq.isEmpty()) {
                break;
            }

            time += cycle;
        }

        return time;
    }
}