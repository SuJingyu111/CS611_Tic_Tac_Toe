/** Abstract class for piece used in board games */

public abstract class AbstractPiece {

    protected String name;

    protected AbstractPlayer owner;

    public AbstractPiece(String name, AbstractPlayer owner) {
        this.name = name;
        this.owner = owner;
    }

    /** Gets the name of piece */
    public String getName() {
        return name;
    }

    /** Gets the owner of piece */
    public AbstractPlayer getOwner() { return owner; }
}
