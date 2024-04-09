package tinyjdk;

public class PairMap<K,V> implements Map<K,V> { //KV su PairMap sono type-parameter su Map invece sono type-argument
    private final List<Pair<K,V>> l = new ArrayList<>(); //todo: dicendo private FINAL non dico che non posso più accedere
                                                         // ed aggiungere elementi ma che non posso riassegnare l'oggetto
                                                         // l con altre cose

    @Override
    public void put(K k, V v) {
        assert (k != null);
        l.add(new Pair<>(k,v));
    }

    @Override
    public V get(K k) throws KeyNotFoundException { //todo se exception è CHECKED -> aggiungere in firma del metodo

        /* for (Pair<K,V> p:l){

        }*/

        //todo: non posso usare for each per scorrere. facendo così il for each vuole Iterable della std library mentre
        // in questo caso sta andando a prendere quello che abbiamo implementato noi.

        /* Iterator<Pair<K,V>> it= l.iterator();
        while(it.hasNext()){
            Pair<K,V> p =it.next();
            if(p.first.equals(k)){
                return p.second;
            }
        }
        return null;*/

        for (int i=l.size()-1; i>=0; --i){
            final Pair<K,V> p =l.get(i);
            if(p.first.equals(k)){
                return p.second;
            }
        }
        throw new KeyNotFoundException(k);

        //todo: se metto eccezione Unchecked e non trovo quello che cercavo -> termina il programma

        //todo: se invece metto Exception CHECKED -> sono obbligato ad utilizzare try-catch ???
        //return null;
        //todo: "sbagliato" fare return null perchè se io avessi volutamente delle chiavi salvate a null non distinguerei
        // il caso in cui non trovo la chiave o stavo cercando la chiave == null
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        //todo: invece di crear un iteratore che deve tenere traccia di duplicati/saltarli ecc ecc. prima di creare
        // l'iteratore, creo algoritmo che preprocessa la PairMap e con un while creo ArrayList senza duplicati + che
        // conservi solo gli ultimi valori per ogni chiave POI resituisco l'iteratore della nuova PairMap.
        Set<K> history =new HashSet<>();
        List<Pair<K,V>> r =new ArrayList<>();
        for (int i = l.size()-1; i >= 0 ; i--) {
            Pair<K,V> p=l.get(i);
            if(!history.contains(p.first)){
                history.add(p.first);
                r.add(p);
            }
        }
        return r.iterator();


       /* return new Iterator<>() {

            private int pos = l.size()-1;
            private Set<K> history =new HashSet<>();

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Pair<K, V> next() {
                Pair<K,V> r = l.get(pos--);
                K k=r.first;

                if(history.contains(k)){
                    if(hasNext()) return next();
                }else{
                   history.add(k);
                   return r;
                }
            }
        };*/
    }

    public static void main(String[] args) {
        Map<Integer,String> m = new PairMap<>();
        m.put(5,"ciao");
        m.put(678,"ciccio");
        m.put(24,"x");
        m.put(5,"x1");
        m.put(5,"cio2");
    }
}

//todo: se volessi PairMap implements Iterable dovrei solo fare l'override di iterator

//todo: se invece metto PairMap implements Collection: put diventa alias di add, però devo comunque mantenere la get.
// inoltre alla contains doverei passare come generic Pair<K,V> che non ha senso.

//todo: NON CHECKED EXCEPTION di solito rappresentano eventi RARI ed UNUSUAL
