class Solution777 implements LeetcodeSolution {
    public boolean canTransform(String start, String end) {
        if (start == null || end == null || start.length() != end.length()) {
            return false;
        }
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != 'X')
                sb1.append(start.charAt(i));
            if (end.charAt(i) != 'X')
                sb2.append(end.charAt(i));
        }
        if (!sb1.toString().equals(sb2.toString())) {
            return false;
        }

        return isValidString(start, end, 'L') && isValidString(end, start, 'R');
    }

    /**
     * Given string a and b, this function checks that every n-th target letter in a
     * has equal or larger index that the n-th target letter in b.
     *
     * @param a      String a
     * @param b      String b
     * @param letter the target letter
     * @return return true if the condition is satisfied, otherwise false.
     */
    public boolean isValidString(String a, String b, char target) {
        int j = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == target) {
                while (j < b.length()) {
                    if (b.charAt(j) == target) {
                        if (j > i) {
                            return false;
                        }
                        j++;
                        break;
                    }
                    j++;
                }
            }
        }
        return true;
    }

    public void test() {
        System.out.println(canTransform("XXXXXLXXXLXXXX", "XXLXXXXXXXXLXX"));
    }
}