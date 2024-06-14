package univers.base;

// Interface pour gérer les interactions communes
public interface Interact {
    void speak();

    void interact();

    void fight(int intakeDamage);

    void trade(Item item);
}
