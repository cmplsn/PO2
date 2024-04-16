## Programmazione Pseudo-Funzionale orientata agli oggetti
Java è linguaggio **imperativo** con stile Orientato agli Oggeti, ma comprende dei particolari di tipo funzionale, 
ovvero le _**lambda** expressions_ 

- Linguaggio **IMPERATIVO** -> ha assegnamento ovvero modifica della variabile/oggetto.
Assegnameto != Binding. Binding = creo e do valore. Assegnamento modifico e basta.
- Linguaggio **FUNZIONALE** -> Non ho assegnamento

C++  
non parlo di OO quando uso classi e creo costruttori, parlo di OO quando ho Polimorfismo (Subtyping), con Virtual 
Table e Dynamic Dispatching. 

## Lambda Expressions
si "trasforma" in anonymous class a run-time  
Una Lambda è una **funzione ANONIMA**, originate per necessità di funzionamento nei linguaggi Funzionali.

Si opta per utilizzare paradigma funzionale anche in Java che è OO perchè paradigma funzionale è codice più robusto meno
error-prone.

**Funzione di ordine superiore** : funzione che prende come parametro un'altra funzione  
_Function_ in jdk Java è un tipo di dato che ha due type parameter: uno è l'input e uno è l'output.  
_Entità di primo ordine_ : entità che posso manipolare come un espressione, avente tipo e valore.

In C per sfruttare la programmazione di tipo funzionale e quindi creare funzioni di ordine superiore con altre funzioni 
come parametri, sono costretto comunque a creare delle funzioni globali e poi passarle alla funzione.  

Con le **LAMBDA** invece in Java è possibile creare delle funzioni anonimo a questo scopo!   
da New fino a parentesi grafa chiusa è solo secondo parametro della funzione _forEach_ che abbiamo definito.  
tutta la definizione di new Myfunction può essere sostituita da una lambda MOLTO più corta.  

**funzione PURA** : non faccio mai side-effect

**funzione IMPURA** : faccio side-effecting

### Come fa il Compilatore a sapere in che anonymous class trasformare la Lambda?
Viene dedotto quando necessario in base alla necessità del contesto di passaggio purchè quell'anonymous class purchè 
l'anonymous abbia un metodo solo.

## Wild Cards 
.

## Thread
processi lightweight. entità schedulate dallo scheduler che non hanno call-segment e data-segment personali come 
i processi. Hanno solo Stato e Stack e condividono memoria con processo padre. Il thread non ha il proprio "codice", 
esegue il codice del processo padre.

Thread condividono Data Segment e Code Segment con il thread genitore ed invece hanno un proprio Program Counter e 
Stack Pointer

Quando si parla di Threads in Java, la JVM non crea dei veri e propri thread ma è in virtuale nella VM. Quando spawniamo
un thread da Java stiamo in realtà facendo una fork della jvm perchè non uso direttamente codice assembly ma uso java 
bytecode quindi crasherebbe.

## Consumer-Producer and Monitor
In Java tutti i Reference-Type sono anche Monitor tramite i metodi wait() e notify() ( notifyAll() ) ereditate da Object.  
anche mettendo wait() e notify() su buff non riesco a far funzionare Consumer Producer perchè ho bisogno del costrutto 
**synchronized**  
Quando ci sono thread che devono cooperare su una medesima struttura dati come regola generale si potrebbe decidere di 
utilizzare la struttura dati stessa come mutex quindi porre la sezione critica dentro ad uno scope 
**synchronized(strutt.dati){---}**. La wait deve essere sempre dentro alla sezione critica quindi dentro allo scope 
synchronized altrimenti otteniamo l'errore di *Illegal Monitor object is not owner*