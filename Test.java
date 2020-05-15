import java.lang.reflect.*;

public class Test {
    public static void main(String... args) {
        AutocompleteSystem x = new AutocompleteSystem(
                new String[] { "i love you", "island", "ironman", "i love leetcode" }, new int[] { 5, 3, 2, 2 });
        System.out.println(x.input('i').toString());
        System.out.println(x.input(' ').toString());
        System.out.println(x.input('a').toString());
        System.out.println(x.input('#').toString());

        // try {
        // Class<?> c = Class.forName(args[0]);
        // LeetcodeSolution solution = (LeetcodeSolution)
        // c.getDeclaredConstructor().newInstance();

        // Method testFunction = c.getDeclaredMethod("test");
        // System.out.format("invoking %s.test()%n", c.getName());
        // testFunction.invoke(solution);

        // // production code should handle these exceptions more gracefully
        // } catch (InstantiationException x) {
        // x.printStackTrace();
        // } catch (ClassNotFoundException x) {
        // x.printStackTrace();
        // } catch (NoSuchMethodException x) {
        // x.printStackTrace();
        // } catch (IllegalAccessException x) {
        // x.printStackTrace();
        // } catch (InvocationTargetException x) {
        // x.printStackTrace();
        // }
    }
}