package tinyjdk;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class StructuralSortedSet<T extends Comparable<T>> extends StructuralSet<T> implements SortedSet<T> {

    @Override
    public void add(T x){
        super.add(x);
        sort();

    }

    private void sort(){
        T[] src = (T[]) a;
        Arrays.sort(src, 0, size(), new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });
        //todo: Arrays è utility class che sorta nativamente array C-like
    }

    @Override
    public T first() {
        if (isEmpty()) throw new NoSuchElementException();
        return (T) a[0];
    }

    @Override
    public T last() {
        if (isEmpty()) throw new NoSuchElementException();
        return (T) a[size() - 1];
    }

}

//todo: UTILITY CLASS-> fatta non per l'utilizzo ad oggetti ma solo per "contenere delle funzione utili".
// No campi No costruttore
