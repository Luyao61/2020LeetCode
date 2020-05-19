import java.util.ArrayDeque;
import java.util.Deque;

class StockSpanner {
    Deque<PriceAndSpan> history;

    public StockSpanner() {
        history = new ArrayDeque<>();
    }

    public int next(int price) {
        int span = 1;
        while (!history.isEmpty() && history.peekLast().price <= price) {
            PriceAndSpan last = history.pollLast();
            span += last.value;
        }
        history.add(new PriceAndSpan(price, span));
        return span;
    }
}

class PriceAndSpan {
    int price;
    int value;

    PriceAndSpan(int price, int value) {
        this.price = price;
        this.value = value;
    }
}
