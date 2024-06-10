package representation;

// Interface pour les événements dans les nœuds
public interface Event {
    void display();

    Node chooseNext();

}
