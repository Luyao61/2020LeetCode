import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class FindRejectedCards implements LeetcodeSolution {
    // A group of friends went to a small restaurant for dinner. They split the bill
    // with a bunch of credit cards after the dinner. Unfortunately some of these
    // cards were rejected. Even more unfortunately the small restaurant uses a very
    // poor point of sale, outputting very limited information. It only reports the
    // number of rejected tenders and tthe total rejected amount. The cashier has
    // already known how much should be charged on each card. Could you help the
    // cashier figure out which cards are rejected?
    // Example:
    // Tenders: {1, $18}, {2, $14}, {3, $21}, {4, $10}, {5, $32}
    // Num of rejected cards : 2
    // Rejected amount : $50
    // Expected Output: <1, 5>
    /**
     * 
     * @param payments
     *                     array of payments
     * @param amount
     *                     rejected amount
     * @param num
     *                     number of rejected transcation
     * @return Lists of rejected transcation indices.
     */
    public List<List<Transcation>> findRejectedCards(Transcation[] payments, int amount, int num) {
        List<List<List<Transcation>>> dp = new ArrayList<>(amount + 1);
        Arrays.sort(payments);
        // initialize dp[0];
        List<List<Transcation>> base = new ArrayList<>();
        base.add(new ArrayList<Transcation>());
        dp.add(base);
        // iterate from t = 1:amount
        for (int t = 1; t <= amount; t++) {
            dp.add(new ArrayList<>());
            for (int i = 0; i < payments.length; i++) {
                Transcation currentTrans = payments[i];
                int currentAmount = currentTrans.amount;
                if (currentAmount <= t) {
                    for (List<Transcation> combination : dp.get(t - currentAmount)) {
                        // isEmpty: base case, dp[0] -> Empty Array, currentAmount == t;
                        // combination.size() < num: short circuit, because we have a upper limit num;
                        // a.compareTo(b) < 0: 1. avoid same transcation appear more than 1 time,
                        // 2. avoid duplicate answer, like [<1, 2>, <2, 3>] and [<2, 3>, <1, 2>]
                        if (combination.isEmpty() || combination.size() < num
                                && combination.get(combination.size() - 1).compareTo(currentTrans) < 0) {
                            List<Transcation> temp = new ArrayList<Transcation>(combination);
                            temp.add(currentTrans);
                            dp.get(t).add(temp);
                        }
                    }
                }
            }
        }
        return dp.get(amount).stream().filter(treeSet -> treeSet.size() == num).collect(Collectors.toList());
    }

    public void test() {
        Transcation[] trans = new Transcation[] { new Transcation(1, 18), new Transcation(2, 14),
                new Transcation(3, 21), new Transcation(4, 10), new Transcation(5, 32) };
        List<List<Transcation>> ans = findRejectedCards(trans, 50, 2);
        System.out.println(ans.toString());

        Transcation[] trans2 = new Transcation[] { new Transcation(1, 2), new Transcation(2, 3) };
        List<List<Transcation>> ans2 = findRejectedCards(trans2, 5, 2);
        System.out.println(ans2.toString());

        Transcation[] trans3 = new Transcation[] { new Transcation(1, 2), new Transcation(2, 3),
                new Transcation(3, 2) };
        List<List<Transcation>> ans3 = findRejectedCards(trans3, 5, 2);
        System.out.println(ans3.toString());

        Transcation[] trans4 = new Transcation[] { new Transcation(1, 2), new Transcation(2, 2) };
        List<List<Transcation>> ans4 = findRejectedCards(trans4, 4, 2);
        System.out.println(ans4.toString());

        Transcation[] trans5 = new Transcation[] { new Transcation(1, 2), new Transcation(2, 3),
                new Transcation(3, 2) };
        List<List<Transcation>> ans5 = findRejectedCards(trans5, 4, 2);
        System.out.println(ans5.toString());
    }
}

class Transcation implements Comparable<Transcation> {
    int id;
    int amount;

    public Transcation(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public int compareTo(Transcation o) {
        if (this.amount == o.amount) {
            return this.id - o.id;
        }
        return this.amount - o.amount;
    }

    @Override
    public String toString() {
        return String.format("<%d, %d>", id, amount);
    }
}
