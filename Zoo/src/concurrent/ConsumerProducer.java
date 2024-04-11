package concurrent;


import jdk.jfr.Percentage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//todo: pattern di programazzione multi-thread rappresentante tutti i casi in cui un thread produce delle cose e un altro
// thread le consuma.
public class ConsumerProducer {

    public static  List<Integer> buff= new ArrayList<>();

    public static class Producer extends Thread {
        @Override
        public void  run(){
            Random rnd = new Random();
            while(true){
                int n= rnd.nextInt();
                synchronized (buff) { //todo: con synchronized entro nel blocco synchronized e metto LOCK del mutex
                    buff.add(n);
                    buff.notify();
                } //todo quando esco da scope tramite synchronized mando UNLOCK
            }
        }
    }

    public static  class Consumer extends Thread {
        @Override
        public void run(){
            while(true){
                if(buff.isEmpty()) {

                    try {
                        buff.wait();    //todo: wait non è POST-BUFFERIZZATA -> se ho ricevuto notify prima di andare in wait
                                        // vengono "dimenticate" e non succede nulla, sennò non andrebbe mai in wait()
                    } catch (InterruptedException e) {
                        //todo: necessario usare try/catch perchè altrimenti la wait potrebbe mandare il thread in deadlock
                        throw new RuntimeException(e);
                    }


                }
                System.out.println(buff.remove(0));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Producer p = new Producer();
        Consumer c = new Consumer();
        p.start();
        c.start();

        //todo: while(true) è busy loop ovvero usa la CPU sono nop e jump quindi usa tutto il tempo di CPU per non fare
        // nulla. Per vedere invece il funzionamento di Prod-Cons posso utilizzare la join()


        //todo: join è bloccante-> se lancia eccezione ferma tutto.

        //todo: un problema della gestione solo tramite try catch è che se il Consumer lavora più velocemente o parte
        // prima del producer, vado in eccezione e quindi blocca l'esecuzione. Serve quindi un Monitor -> entità che può
        // essere condivisa da più thread. un thread può mettersi in attesa sul Monitor e poi il monitor può essere
        // svegliato da altri thread e a quel punto si sblocca anche il thread in attesa.


        try {
            p.join();
            c.join();
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        //todo: metodo NON statico, fosse stato statico dovrei avre scritto Producer.join()
        // chiamando p.join il main thread aspetta e p viene "atteso" ovvero il main thread aspetta che p finisca


    }

}
