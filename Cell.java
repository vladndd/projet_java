class Cell {
    String data;
    Cell next;

    Cell(String data) {
        this.data = data;
        this.next = null;
    }
}

class MyList {
    private Cell head;

    public MyList() {
        this.head = null;
    }

    public void add(String data) {
        Cell newCell = new Cell(data);
        newCell.next = head;
        head = newCell;
    }

    public int size() {
        int count = 0;
        Cell current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public String toString() {
        StringBuilder elements = new StringBuilder();
        Cell current = head;
        while (current != null) {
            elements.append(current.data);
            if (current.next != null) {
                elements.append(" -> ");
            }
            current = current.next;
        }
        return elements.toString();
    }

    public void addLast(String data) {
        Cell newCell = new Cell(data);
        if (head == null) {
            head = newCell;
        } else {
            Cell current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newCell;
        }
    }

    public void add(String data, int index) {
        if (index == 0) {
            add(data);
            return;
        }
        Cell newCell = new Cell(data);
        Cell current = head;
        for (int i = 0; i < index - 1; i++) {
            if (current == null) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            current = current.next;
        }
        newCell.next = current.next;
        current.next = newCell;
    }

    public String get(int index) {
        Cell current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            current = current.next;
        }
        if (current == null) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return current.data;
    }

    public int sumLetters() {
        int totalLetters = 0;
        Cell current = head;
        while (current != null) {
            totalLetters += current.data.length();
            current = current.next;
        }
        return totalLetters;
    }

    public static void main(String[] args) {
        MyList ml = new MyList();
        ml.addLast("tatu");
        ml.addLast("toto");
        ml.add("titi");
        ml.addLast("tutu");

        System.out.println("List size: " + ml.size());
        System.out.println("List elements: " + ml.toString());
        System.out.println("Element at index 2: " + ml.get(2));
        System.out.println("Sum of letters: " + ml.sumLetters());
    }
}