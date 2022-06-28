public class Name {
    /**
     * Determines if two names are a match using the following criteria:
     * Name A matches Name B if Name A is a superset of Name B where you can remove
     *  letters from Name A and make Name B. Only operation allowed is deleting characters.
     * @param shortest The shorter of the names
     * @param longest The longer of the names
     * @return true if shortest is a subset of longest, false otherwise. Will return true
     *  if either is null, since you can just remove all the other name's letters.
     */
    public static boolean isMatch(String shortest, String longest) {
        if (shortest == null || longest == null) return true;
        
        // If they are equal, a set is a superset of itself.
        if (shortest.equals(longest)) return true;
        
        // A quality of life check. Since we're looking for one of the strings inside the
        //  other, it makes sense to enforce we know which one is longest/shortest so we
        //  can find the shortest inside the longest. It is impossible to do the reverse
        if (shortest.length() > longest.length()) return isMatch(longest, shortest);

        int shortIndex = 0, longIndex = 0;
    
        // While there is a character left to find in the short string
        while (shortIndex < shortest.length()) {
            // and we have not run out of characters in the larger string
            if (longIndex >= longest.length()) break;

            // Check if there's a match
            if (shortest.charAt(shortIndex) == longest.charAt(longIndex)) {
                // If a match is found, move both indices to the next spot.
                shortIndex++;
                longIndex++;
                continue;
            }

            // Otherwise, 'delete' the char in the longer string.
            longIndex++;
        }

        // We'll know whether we found a match based on the index positions.
        // If shortIndex is < shortest.length() it means we're missing characters from
        //  shortest. If there are missing characters from shortest, we'd need to 
        //  at best remove from both strings which is not allowed.
        return shortIndex == shortest.length();
        // Note to add: I was surprised that it is only dependent on the shortIndex
        //  but it does seem to be the case. There are two relevant cases for the longIndex:
        //  1) longIndex < longest.length()
        //     The while condition guarantees shortIndex cannot be greater than shortest.length()
        //     Likewise, the while condition guarantees at this point shortIndex == shortest.length()
        //     If shortIndex == shortest.length() then we just have extra characters. We know
        //      that the last characters match, otherwise we would have already broke out of the
        //      while because !(shortIndex < shortest.length()) makes the loop condition false so
        //      the increments in the if statement must have happened.
        //  2) longIndex == longest.length()
        //     Likewise for the first case, if shortIndex == shortest.length() then it must mean the last two
        //      characters match because the while condition would have broken before.
        //     Otherwise, there are missing letters from shortest
        //  Both cases simplify to the condition shortIndex == shortest.length().
    }
}
