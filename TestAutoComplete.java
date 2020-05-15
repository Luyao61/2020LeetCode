class TestAutoComplete {
    public static void main(String args[]) {
        AutocompleteSystem x = new AutocompleteSystem(
                new String[] { "i love you", "island", "ironman", "i love leetcode" }, new int[] { 5, 3, 2, 2 });
        System.out.println(x.input('i').toString());
        System.out.println(x.input(' ').toString());
        System.out.println(x.input('a').toString());
        System.out.println(x.input('#').toString());
    }
}