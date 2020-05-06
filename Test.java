import java.lang.reflect.*;

public class Test {
    public static void main(String... args) {
        try {
            Class<?> c = Class.forName(args[0]);
            LeetcodeSolution solution = (LeetcodeSolution) c.getDeclaredConstructor().newInstance();

            Method testFunction = c.getDeclaredMethod("test");
            System.out.format("invoking %s.test()%n", c.getName());
            testFunction.invoke(solution);

            // production code should handle these exceptions more gracefully
        } catch (InstantiationException x) {
            x.printStackTrace();
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x) {
            x.printStackTrace();
        }
    }
}