public abstract class AbstractPiece {

    protected String name;

    protected AbstractPlayer owner;

    public AbstractPiece(String name, AbstractPlayer owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public AbstractPlayer getOwner() { return owner; }
}
