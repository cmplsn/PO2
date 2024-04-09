import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ProveJDK1 {
    public static void main(String[] args) {
        List<Integer> l =new ArrayList<Integer>(); //todo: dichiarazione con inizializzazione = binding
        l.add(21);
        l.add(12);
        l.add(144);
        Integer x = l.set(2,666);
        Iterator<Integer> k = l.iterator();

        while(k.hasNext()){
            int n= k.next(); //todo: salva in n il "puntato" correntemente e poi va al prossimo
            System.out.println(n);
        }

        Collection<Integer> m = new ArrayList<Integer>();
        m.add(47);
        //todo:compila perch add "appartiene" a Collection quindi ArrayList l'ha semplicemente ereditato
        // da Collection


    }
}
