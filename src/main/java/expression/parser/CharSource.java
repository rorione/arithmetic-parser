package expression.parser;

public interface CharSource {
    boolean hasNext();
    char next();
    void goBack(int amount);
    int getOffset();
}
