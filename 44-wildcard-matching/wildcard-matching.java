class Solution {
    private Boolean[][] dp;
    private char[] sChars, pChars;
    private int sLength, pLength;

    public boolean isMatch(String s, String p) {
        sChars = s.toCharArray();
        pChars = p.toCharArray();
        sLength = s.length();
        pLength = p.length();
        dp = new Boolean[sLength][pLength];
        return dfs(0, 0);
    }

    private boolean dfs(int sIndex, int pIndex) {
        if (sIndex == sLength) {
            // If string is exhausted, pattern must be empty or only '*' left
            for (int i = pIndex; i < pLength; i++) {
                if (pChars[i] != '*') return false;
            }
            return true;
        }

        if (pIndex == pLength) {
            // Pattern exhausted but string remains, no match
            return false;
        }

        if (dp[sIndex][pIndex] != null) {
            return dp[sIndex][pIndex];
        }

        boolean match;
        if (pChars[pIndex] == '*') {
            // '*' matches zero or more chars
            match = dfs(sIndex, pIndex + 1) || dfs(sIndex + 1, pIndex);
        } else {
            // Match single char: '?' or exact char
            match = (pChars[pIndex] == '?' || sChars[sIndex] == pChars[pIndex]) && dfs(sIndex + 1, pIndex + 1);
        }

        dp[sIndex][pIndex] = match;
        return match;
    }
}
