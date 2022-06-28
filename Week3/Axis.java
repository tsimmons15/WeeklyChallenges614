public class Axis {
    /**
     * Given N+1 lights, walking to each light in the pattern "always walk to the rightmost
     *  light not turned on, turn on, walk to leftmost light not turned on, turn on repeat until
     *  no lights remain", returns the integer distance travelled.
     * @param N The number of lights (minus the origin light)
     * @return The distance travelled visiting each of the lights.
     */
    public static int walkLength(int N) {
        // Takes advantage of the pattern of visiting the lights.
        // The first trip will always be N (from 0 to N).
        // The second trip will also always be N (from N to 0).
        // The third trip will be N - 1 (0 to N - 1)
        // The fourth will be N - 2 (N - 1 to 1)
        // ...
        // The second to last trip will be 1
        // So the pattern is N + sum_{i = 0}^{N} i = N + (N^2 + N)/2.
        return N + (int)(Math.pow(N, 2) + N)/2;
    }
}
