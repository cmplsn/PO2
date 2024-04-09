package tinyjdk;
//todo: generic possono essere solo reference-type

public class ArrayList<T> implements List<T>{
    private Object[] a; //todo: tutti i reference type dichiarati senza "inizializzazione" sono inizializzati a NULL
                        // pointer ad Object -> pointer occupa sempre  8 byte in CPU a 64 bit. puntatore occupa
                        // un indirizzo di memoria
    private int sz;

    public ArrayList(){ //todo: quando viene chiamato il costruttore, le variabili sono già inizializzate
                        // e vengono solo modificate
        this.a = new Object[10]; //todo: non posso fare array di generics quindi sono costretto ad
                                 // utilizzare Object
        this.sz=0;
    }

    @Override
    public void add(T e) {
        if(sz>=a.length){
            Object[] old = a;
            a=new Object[2* a.length];
            for (int i = 0; i < old.length; i++) {
                a[i]=old[i];
            }

        } //todo: si chiude l'if e garbage collector elimina old

        a[sz++]=e; //todo: subsumption da T a Object sempre valida
    }

    @Override
    public void clear() {
        //a = null todo: non va bene vado in puntatore nullo
        sz=0;

    }

    //todo: == fa confronto by reference se i due argomenti sono REFERENCE-TYPE. == è polimorfo omogeneo,
    // sia a dx che a sx devo avere stesso tipo.

    //todo: equals -> metodo di Object.

    @Override
    public boolean isEmpty() {
        return sz==0;
    }

    @Override
    public void remove(T x) {
        for (int i = 0; i <size(); i++) {
            T o= get(i);
            if(x.equals(o)){
                for (int j = i; j <size()-1; j++) {
                    set(j,get(j+1));
                }
                --sz;
            }
        }
    }

    @Override
    public int size() {
        return sz;
    }
    //todo: membro static = campi, metodi, classi nested
    // metodo static -> non ha accesso a this. non posso "modificare stato" di ciò in cui sono dichiarato ?!?

    //todo: membro static non ha accesso a this quindi NON viene salvato in VIRTUAL TABLE

    //NESTED NON STATICA PER ITERATORE
    private class MyIterator implements Iterator<T> {
        //todo: tre maniere per implementare: classe globale, nested statica o non statica o  anonymous ???

        //todo: vantaggio di fare iteratore tramite nested class è perchè ha accesso allo
        // stato della enclosing. posso accedere ad elemento di MyIterator +  tutti elementi di ArrayList
        private int pos=0;
        private ArrayList<T> enclosing;

        @Override
        public boolean hasNext() {
            return pos < ArrayList.this.size(); //todo: chiamata fully qualifying a size() dovrebbe richiamare
                                                // [enclosing].this
        }
        @Override
        public T next() {
            return get(pos++);
        }
    }

    //NESTED STATIC PER ITERATORE
    private static class MyIterator2<T> implements Iterator<T>{
        private int pos=0;
        private ArrayList<T> enclosing;

        public MyIterator2(ArrayList<T> a){
            this.enclosing=a;
        }

        @Override
        public boolean hasNext() {
            return pos < enclosing.size(); //todo: chiamata fully qualifying a size() dovrebbe richiamare
            // [enclosing].this
        }
        @Override
        public T next() {
            return enclosing.get(pos++);
        }
    }


    //ITERATORE ANONYMOUS CLASS
    @Override
    public Iterator<T> iterator() {
        //todo: anche il contenuto di questo return è un'espressione sola
        return new Iterator<T>() {
            private int pos=0;
            @Override
            public boolean hasNext() {
                return pos<size();
            }

            @Override
            public T next() {
                return get(pos++);
            }
        }; //questo return è statemente che inizia a riga 107 e finisce a 117
    }
    //todo: tramite anonymous class (in realtà non Class ma Object possiamo creare implementazione di
    // un'interfaccia "al volo". Sembra invocazione di costruttore ma invece sto chiamando new su interfaccia quindi
    // sto creando un oggetto con tipo Interfaccia<T>.

    //todo: dentro ad anonymous class potrei eventualmente implementare metodi anche non facenti parte dell'interfaccia
    // originaria ma, al momento di creazione dell'oggetto viene in questo caso creato un Iterator<T> ed il chiamante sa
    // che Iterator prevede da interfaccia solo i due metodi di cui dobbiamo fare overriding dall'interfaccia stessa,
    // il metodo quindi potrebbe esistere ma non essere richiamato.

    //todo: CLOSURE -> anonymous class porta con se scope in cui è stata definita. quindi la variabile pos utilizzata in
    // in questa anonymous class di Iterator<T> può essere dichiarata prima del return in cui creiamo l'anonymous class,
    // e vi si può accedere anche dall'interno dell'anonymous class.

    @Override
    public T get(int i) {
        if (i<sz)
            return (T) a[i]; //todo: costretto a fare casting a (T) ma è brutto tollerabile perchè ho già protetto
                         // il generic con le add e gli altri metodi assicurandomi che tutti gli elementi saranno T
        throw new RuntimeException(String.format("ArrayList.get(): index %d out of bounds %d", i, sz));
    }

    @Override
    public T set(int i, T x) {
        if (i < sz) {
            T old = get(i);
            a[i] = x;
            return old;
        }
        throw new RuntimeException(String.format("ArrayList.set(): index %d out of bounds %d", i, sz));
    }

    @Override
    public void add(int i, T x) {
        if(i>size()){

        }else{

        }
    }

    @Override
    public T remove(int i) {
        return null;
    }
}

//todo: Virtual Table = tabella dei puntatori a metodi generata per OGNI oggetto che viene inizializzato
// nel caso di ArrayList che ha 11 metodi sarà allocato 8byte (puntatore a Object[]) 4 byte (int) e alloco spazio
// Virtual Table per 11 pointer (un pointer per ogni metodo), i costruttori non essendo soggetti a polimorfismo
// non vengono salvati in Virtual Table

//todo: MEMBRO STATIC NON É IN VIRTUAL TABLE. Metodo static per esempio è un metodo costruito perchè
// non dipenda dal resto dei membri dell'enclosing

//todo: blocco codice è fatto da statement. ciò che è Statement occupa una riga intera a se stante chiusa da ;
// un blocco tra {} contiene uno o più statement. Uno statement può essere composto da espressioni.
// Le espressioni *valutano qualcosa* e sono innestabili, collegabili una all'altra.

//todo:     VARIABILE -> non definisco attributi di visibilità (int a=7)
//          CAMPO -> hanno attributi di visibilita private, public ecc.
