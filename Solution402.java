import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/remove-k-digits
class Solution402 implements LeetcodeSolution {
    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<Character>();

        int index = 0;
        while (k != 0 && (index < num.length() || stack.size() > 0)) {
            if (stack.size() == 0) {
                stack.offerLast(num.charAt(index));
                index++;
            } else {
                if (index < num.length()) {
                    if (stack.peekLast() > num.charAt(index)) {
                        stack.pollLast();
                        k--;
                    } else {
                        stack.offerLast(num.charAt(index));
                        index++;
                    }
                } else {
                    stack.pollLast();
                    k--;
                }
            }
        }
        while (index < num.length()) {
            stack.offerLast(num.charAt(index));
            index++;
        }
        StringBuilder sb = new StringBuilder();
        while (stack.size() != 0) {
            Character c = stack.pollFirst();
            if (sb.length() == 0 && c == '0') {
                continue;
            }
            sb.append(c);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public void test() {
        String x = this.removeKdigits("1234567890", 9);
        System.out.println(x);
    }
}