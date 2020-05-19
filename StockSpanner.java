import java.util.ArrayList;
import java.util.List;

class StockSpanner {
    List<PriceAndSpan> history;

    public StockSpanner() {
        history = new ArrayList<>();
    }

    public int next(int price) {
        int i = history.size() - 1;
        int span = 1;
        while (i >= 0 && history.get(i).price <= price) {
            span += history.get(i).value;
            i = i - history.get(i).value;
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
