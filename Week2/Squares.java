public class Squares {
    public static void main(String[] args) {
        testSquares();
    }

    /***
     * Takes advantage of the idea that since b > a
     * then sqrt(b) > sqrt(a) since sqrt() is a continuous
     * function while a, b > 0. Equally, some c s.t. b > c > a
     * will also have the property that
     *  sqrt(b) > sqrt(c) > sqrt(a).
     * So, you only need to check how many integers exist in
     * the range [sqrt(a), sqrt(b)] (where the result of sqrt() is
     *  rounded down) to determine how many squares exist in the
     * range [a, b].
     * @param a The lower bound of our range, 1 <= a <= b
     * @param b The upper bound of our range, b <= Integer.MAX_VALUE
     * @return The count of square values in the range [a, b]
     */
    public static int squares(int a, int b) {
        int upper = (int)Math.sqrt(b);
        int lower = -1;

        // Since 1 is a reflection point, if the lower bound is 1
        //  it will be one off from the correct count. To account
        //  for this, I could set lower to 0. However, none of the
        //  rest of the code can apply if I set lower to 0 so just return
        //  upper.
        if (a <= 1) {
            return upper;
        } else {
            // Otherwise, calculate that lower bound.
            lower = (int)Math.sqrt(a);
        }

        // Possibly due to the rounding happening to account for
        //  the bounds not being perfect squares, if both bounds
        //  are perfect squares then we get a result that's low
        //  by 1. To account for that, just add 1 to upper.
        if (Math.pow(upper, 2) == b && Math.pow(lower, 2) == a) {
            upper++;
            if (a == 1) {
                System.out.println("Testing?");
            }
        }

        // The result is then upper - lower.
        return upper - lower;
    }

    public static void testSquares() {
        int[][] tests = new int[][] {
                {24, 49},        // 3
                {3, 9},          // 2
                {4, 9},
                {17,24},         // 0
                {1, 1000000000}, // 31622
                {1, (int)Math.sqrt(Integer.MAX_VALUE-1)},
                {1, Integer.MAX_VALUE - 1},
                {106937, 1000012337},
                {1, 106937},
                {1, 1000012337},
                {5413, (int)Math.pow(5413,2)},
                {1, (int)Math.pow(5413, 2)},
                {(int)Math.pow(11, 2), (int)Math.pow(5413, 2)},
                {(int)Math.pow(121, 2), (int)Math.pow(4445, 2)},
                {121, 121},
                {120, 120},
                {1, 1}
        };

        for (int i = 0; i < tests.length; i++) {
            System.out.println("TEST #" + (i+1));
            System.out.println(tests[i][0] + " & " + tests[i][1]);

            int result = squares(tests[i][0], tests[i][1]);
            int expected = manualCheck(tests[i][0], tests[i][1]);
            System.out.println("Returned: " + result);
            System.out.println("Expected: " + expected);
            System.out.println((result == expected) ? "PASSED" : "FAILED");
        }

    }

    public static int manualCheck(int a, int b) {
        int count = 0;

        int upper = (int)Math.sqrt(b);
        while (Math.pow(upper, 2) >= a) {
            count++;
            upper--;
        }

        return count;
    }
}
