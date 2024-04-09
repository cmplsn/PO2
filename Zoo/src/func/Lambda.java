package func;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

//todo: Function: NON VOID -> NON VOID*/

//todo:Consumer: void function(parametri non void). consuma input e non ritorna niente

//todo:Supplier ->NON VOID function(void) non prende parametri e ritorna qualcosa

//todo: quarto tipo =  RUNNABBLE VOID->VOID usata per esempio per i thread


public class Lambda {
    interface MyFunction<T>{
        void apply(T x);
    }

    public static <T> void forEach(Collection<T> c, MyFunction<T> f){
        for(T x: c){
            f.apply(x);
        }
    }


    public static void main(String[] args) {
        List<Integer> l = List.of(1,2,3,4);


        forEach(l, x -> System.out.println(x));

        forEach(l, new MyFunction<Integer>() {
            @Override
            public void apply(Integer x) {
                System.out.println(x);
            }
        });


        forEach(l, new MyFunction<Integer>() {
            @Override
            public void apply(Integer x) {
                x++;
            }
        });

        forEach(l, x ->x++); //todo: se dovessi scrivere una lambda con un corpo formato da più di uno statement,
                             // x -> {metto il corpo tra grafe}






    }
    interface Function<A,B>{
        B apply(A x);   //todo: interfaccia che rappresenta tutte funzioni che hanno UN dominio e UN codominio
                        // (void non è considerato un tipo quindi ho la necessità di creare sia MyFunction che Function
                        // perchè con B apply (A x) non rappresentera le funzioni void.
    }

    public static <A,B> Collection <B> map(Iterable <A> c, Function<A,B> f){
        Collection<B> r = new ArrayList<>();
        for (A x: c){
            B b= f.apply(x);
            r.add(b);
        }
        return r;
    }

    public static void main2(String[] args) {
        List<Integer> l=new ArrayList<>();

        Collection<Integer> r = map(l, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x+1;
            }
        });

        Collection<Integer> r2= map(l, x->x+1);
        /*oppure*/
        Collection<Integer> r3=map(l,x -> {return x+1;});

        Collection<Boolean> r4 = map(l, x -> x>0);
    }
    /*public static <A,B> Collection <B> map(Collection<A> c,)
    public static <T> void forEach(Collection<T> c, Function<T,T> f){

    }*/


    public static <T> Collection<T> filter(Collection<T> c, Function<T, Boolean> f){
        Collection<T> r= new ArrayList<>();
        for (T x : c){
            if(f.apply(x)){
                r.add(x);
            }
        }
        return r;
    }

    public static <T> void filter_impure(List <T> c, Function<T, Boolean> f){
        for (int i = 0; i < c.size(); i++) {
            if(!f.apply(c.get(i))){
                c.remove(i);
            }
        }
    }

    public static <T> void filter_impure2(Iterable<T> c, Function<T,Boolean> f){
        Iterator<T> it = c.iterator();
        while(it.hasNext()){
            if(!f.apply(it.next())){
                it.remove();
            }
        }
    }

    public static void main3(String[] args) {
        List<Integer> l = List.of(1,2,3,4,5);
        Collection<Integer> c1 = filter(l,x->x>2);
    }
}


//todo: Fare pull da Github per Predicate 