package tinyjdk;

public class ArrayListIterator<T> implements Iterator<T> {  //todo: abbiamo preso static class MyIterator2 e
                                                            // fatta diventare una classe separata

    private int pos=0;
    private ArrayList<T> enclosing;


    public ArrayListIterator(ArrayList<T> a){
        this.enclosing=a;
    }

    @Override
    public boolean hasNext() {
        return pos < enclosing.size();  //todo: chiamata fully qualifying a size() dovrebbe richiamare
                                        // [enclosing].this
    }
    @Override
    public T next() {
        return enclosing.get(pos++);
    }
}
