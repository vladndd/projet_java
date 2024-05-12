package representation;

public abstract class Node {
    protected int id;
    protected String description;

    public Node(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public void display() {
        System.out.println(description);
    }

    public abstract Node chooseNext();
}
