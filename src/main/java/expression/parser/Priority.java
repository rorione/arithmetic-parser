package expression.parser;

import java.util.Objects;

public class Priority implements Comparable<Priority> {
    private final int value;

    public Priority(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Priority value must be positive");
        }
        this.value = value;
    }

    @Override
    public int compareTo(final Priority p) {
        return Integer.compare(this.value, p.getValue());
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Priority priority = (Priority) o;
        return value == priority.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
