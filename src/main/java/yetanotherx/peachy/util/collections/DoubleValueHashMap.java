package yetanotherx.peachy.util.collections;

import java.util.HashMap;
import java.util.Iterator;

public class DoubleValueHashMap<K, V, W> implements Iterable<ThreeValueCollection<K, V, W>> {

    private HashMap<K, TwoValueCollection<V, W>> hash;
    private int current;

    public DoubleValueHashMap() {
        this.hash = new HashMap<K, TwoValueCollection<V, W>>();
        this.current = 0;
    }

    public void set(K key, V val1, W val2) {
        this.hash.put(key, new TwoValueCollection<V, W>(val1, val2));
    }

    @SuppressWarnings("unchecked")
    public V getVal1(K key) {
        TwoValueCollection<V, W> map = this.hash.get(key);
        if (map != null) {
            return map.getFirst();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public W getVal2(K key) {
        TwoValueCollection<V, W> map = this.hash.get(key);
        if (map != null) {
            return map.getSecond();
        }
        return null;
    }

    public Iterator<ThreeValueCollection<K, V, W>> iterator() {
        return new DoubleValueHashMapIterator(hash);
    }
    
    private static class DoubleValueHashMapIterator<K, V, W> implements Iterator<ThreeValueCollection<K, V, W>> {

        private int current;
        private HashMap<K, TwoValueCollection<V, W>> hash;

        public DoubleValueHashMapIterator(HashMap<K, TwoValueCollection<V, W>> hash) {
            this.hash = hash;
            this.current = 0;
        }

        public boolean hasNext() {
            return current < hash.size();
        }

        public ThreeValueCollection<K, V, W> next() {
            boolean nextIsTheOne = false;

            int i = 0;
            for (K key : hash.keySet()) {
                if (i == current) {
                    nextIsTheOne = true;
                }

                if (nextIsTheOne) {
                    TwoValueCollection<V, W> out;
                    out = hash.get(key);
                    current++;
                    return new ThreeValueCollection<K, V, W>(key, out.getFirst(), out.getSecond());
                }

                i++;
            }

            return null;

        }

        public void remove() {
        }
    }
}
