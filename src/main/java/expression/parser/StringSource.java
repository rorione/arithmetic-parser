package expression.parser;

public class StringSource implements CharSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public void goBack(final int amount) {
        pos = Math.max(0, pos - amount);
    }

    @Override
    public int getOffset() {
        return pos;
    }
}
