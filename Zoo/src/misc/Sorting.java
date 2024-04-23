package misc;

import Zoo.Zoo.*;
import tinyjdk.ArrayList;
import tinyjdk.List;

import java.util.Comparator;

//todo: wildcard si usano solo come tipe parameter -> mentre sul polimorfismo di metodo definisco upper bound <T>

public class Sorting {

    //todo: subsumendo type argument permetto perdita di informazione che poi viola il tipo
    public static <T> void sort(List<T> list, Comparator<? super T> c){

    }

    public static class Dog extends Animal{
        public boolean pedigree;

        public Dog(int w,boolean p) { super(w); this.pedigree=p;}
    }

    //todo: jdk è generale a livello che se ho un comparator per Dog e gli do una lista di Animal, supporta il
    // comparator anche di Animal
    public static void main(String[] args) {
        List<Animal> l= new ArrayList<>();
        l.add(new Animal(50));
        l.add(new Animal(30));
        l.add(new Animal(70));
        sort(l, new Comparator<Animal>(){

            @Override
            public int compare(Animal o1, Animal o2) {
                return o1.getWeight()-o2.getWeight();
            }
        });
    }
}

