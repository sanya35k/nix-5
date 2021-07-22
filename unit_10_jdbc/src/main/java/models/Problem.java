package models;

import java.util.Objects;

public class Problem {
    private int id;
    private int fromId;
    private int toId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }
    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }
    public void setToId(int toId) {
        this.toId = toId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return id == problem.id &&
                fromId == problem.fromId &&
                toId == problem.toId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromId, toId);
    }
}