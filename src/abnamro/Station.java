package abnamro;

/**
 * @tapan .
 */
public enum Station {

    A('A'), B('B'), C('C'), D('D'), E('E');

    private char name;

    Station(char name) {
        this.name = name;
    }

    public int getVertex() {
        return ordinal();
    }
}
