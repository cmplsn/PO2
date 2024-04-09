package concurrent;


import jdk.jfr.Percentage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//todo: pattern di programazzione multi-thread rappresentante tutti i casi in cui un thread produce delle cose e un altro
// thread le consuma.
public class ConsumerProducer {

    public static List<Integer> buff= new ArrayList<>();

    public static class Producer extends Thread {
        @Override
        public void run(){
            Random rnd = new Random();
            while(true){
                int n= rnd.nextInt();
                buff.add(n);
            }
        }
    }

    public static class Consumer extends Thread{
        @Override
        public void run(){
            while(true){
                System.out.println(buff.remove(0));
            }
        }
    }

    public static void main(String[] args) {
        Producer p = new Producer();
        Consumer c = new Consumer();
        p.start();
        c.start();

        //todo: while(true) è busy loop ovvero usa la CPU sono nop e jump quindi usa tutto il tempo di CPU per non fare
        // nulla. Per vedere invece il funzionamento di Prod-Cons posso utilizzare la join()

        /*while(true){

        }*/
        try {
            p.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
