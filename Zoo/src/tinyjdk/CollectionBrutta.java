package tinyjdk;

public interface CollectionBrutta {
    void add(Object o);

    //todo:potrei farlo perchè Object sussume al massimo però non funziona al momento della get perchè devo per forza
    // fare get di tipo Object e non me ne faccio un cazzo. Potrei downcastare ma non so a cosa downcastare e
    // diventerebbe responsabilità del programmatore -> alto rischio di sbagliare tipo in downcast = bug ecc.
}
