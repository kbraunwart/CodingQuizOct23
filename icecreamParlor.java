import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'icecreamParlor' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER m
     *  2. INTEGER_ARRAY arr
     */

    public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
        // Write your code here
        ArrayList<Integer> maxCandidates = new ArrayList<Integer>();
        return helper(m, arr, 0, 1, 2, maxCandidates, arr.size());

    }

    public static List<Integer> helper(int m, List<Integer> arr, int index1, int index2, int index3, List<Integer> maxCandidates, int min) {
        if (index1 >= (arr.size() - 3)) {
            return maxCandidates;
        } else if (index2 >= (arr.size() - 2)) {
            return helper(m, arr, index1 + 1, index1 + 2, index1 + 3, maxCandidates, min);
        } else if (index3 >= arr.size() - 1) {
            return helper(m, arr, index1, index2 + 1, index2 + 2, maxCandidates, min);
        } else {
            int sum = arr.get(index1) + arr.get(index2) + arr.get(index3);
            if ((m - sum) < min) {
                int newMin = m - sum;
                ArrayList<Integer> candidates = new ArrayList<Integer>();
                candidates.add(index1 + 1);
                candidates.add(index2 + 1);
                candidates.add(index3 + 1);
                return helper(m, arr, index1, index2, index3 + 1, candidates, newMin);
            }
            return helper(m, arr, index1, index2, index3 + 1, maxCandidates, min);
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int m = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                List<Integer> result = Result.icecreamParlor(m, arr);

                bufferedWriter.write(
                        result.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                                + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
