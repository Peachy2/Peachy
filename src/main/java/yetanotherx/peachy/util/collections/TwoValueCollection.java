package yetanotherx.peachy.util.collections;

public class TwoValueCollection<X, Y> {

    private X first;
    private Y second;

    public TwoValueCollection(X first, Y second) {
        this.first = first;
        this.second = second;
    }

    public TwoValueCollection() {
    }

    public X getFirst() {
        return first;
    }

    public void setFirst(X first) {
        this.first = first;
    }

    public Y getSecond() {
        return second;
    }

    public void setSecond(Y second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("[").append(first).append("|").append(second).append("]").toString();
    }

}
