import java.util.LinkedList;
import java.util.List;

// leetcode 271
// https://leetcode.com/problems/encode-and-decode-strings/
public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(intToString(s));
            sb.append(s);
        }
        return sb.toString();
    }

    private String intToString(String s) {
        int l = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            char c = (char) (l >> (i * 8) & 255);
            sb.append(c);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> list = new LinkedList<>();
        int i = 0;
        while (i < s.length()) {
            int length = stringToInt(s.substring(i, i + 4));
            i = i + 4;
            list.add(s.substring(i, i + length));
            i = i + length;
        }
        return list;
    }

    private int stringToInt(String s) {
        int number = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            number += ((int) c) << ((3 - i) * 8);
        }
        return number;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));