package concurrent;
import org.w3c.dom.ls.LSOutput;

import java.lang.Thread;


public class Threads {

    public static String suffix="baudo";
    public static void loop(String msg){
        while(true){
            System.out.println(msg + ' ' + suffix);
        }
    }
    //todo: se non definisco nessun costruttore il compilare ne genera uno automaticamente che
    // chiama super() senza argomenti

   public static class MyThread extends Thread{
       @Override
       public void run(){
           loop("ciao");
       }
   }

    public static void main(String[] args) {
        Thread t=new MyThread();
        t.start();  //todo: crea vero e proprio thread e inserisce thread in coda di esecuzione del Kernel per esecuzione
                    // del metodo run. Start è chiamata non bloccante. chiede lo scheduling del thread e poi ritorna subito.


        //t.run(); //todo: sono io chiamante che eseguo il codice in run.

       //loop("main");

        //todo: medoto alternativo di costruzione thread con costruttore che prende Runnable task come parametro

        //todo: con anonymous class, con questo metodo di costruzione Thread evito ereditarietà, non serve che creo una
        // classe "MyThread" che fa override di run() ecc.
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                loop("SECONDO");
            }
        });
        t2.start();

        //todo: metodo 2: LAMBDA.
        Thread t3 =  new Thread(()->loop("TERZO"));
        t3.start();

        //todo: metodo 3 dove NON FACCIO BINDING. anche se passa garbage collector ho chiamato start quindi il kernel ha
        // schedulato il thread che prosegue nella sua exe. Così però non posso più utilizzarlo a mio piacimento,
        // per esempio per fare la join.
        new Thread(()->loop("QUARTO")).start();


    }
}
