package Zoo;

import java.lang.invoke.TypeDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Zoo {
    public static class Animal{
        protected int weight;

        public Animal(int w){
            this.weight=w;
        }
        public void eat(Animal a){
            this.weight += a.weight;
        }
        public int getWeight(){
            return this.weight;
        }
    }
    public static class Cat extends Animal{

        public Cat(int w) {
            super(w);
        }

    }

    public static class Dog extends Animal{
        private boolean pedigree;

        public Dog(int w, boolean a){
            super(w);
            this.pedigree=a;
        }

        @Override
        public void eat(Animal a){
            this.weight+=a.weight*2;
        }
    }
    public static void main(String[] args) {

        Dog f = new Dog(30,false);
        Dog g = f; //stesso oggetto di f, due alias diversi
        Animal pluto = new Dog(40,true);
        pluto.eat(f);
        Class<?> d =g.getClass();
        try {
            Field x = d.getDeclaredField("pedigree");
            x.set(g,true);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        //todo: Dynamic Dispatching nonostante tipo Animal ho istanziato un oggetto di tipo Dog quindi accedo
        // al metodo tramite override

        //todo: Subsumption può avvenire al momento del binding, passagio di argomento o assegnamento

        Dog fido= new Dog(50,false);
        Animal gigi = new Dog(30, true);
        gigi.eat(fido); //chiama la eat di Dog per Dynamic Dispatching
    }
}

/*polimorfismo -> subsumption: un oggetto di un certo tipo può essere "spacciato" per un oggetto di un altro tipo

* Override: ridefinizione di un metodo già implementato

GENERIC: tipi parametrici su un altro tipo -> Iterable<T> crea un iterable di oggetti di tipo T

default -> possibilità di creare implementazioni di default dentro ad interfaccia. permette ad interfaccia di
comportarsi come classe estratta */