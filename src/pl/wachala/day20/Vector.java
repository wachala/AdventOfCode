package pl.wachala.day20;

public class Vector {
    private long x;
    private long y;
    private long z;

    public Vector(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector increaseBy(Vector n) {
        return new Vector(x + n.x, y + n.y, z + n.z);
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector vector = (Vector) o;

        if (x != vector.x) return false;
        if (y != vector.y) return false;
        return z == vector.z;
    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        result = 31 * result + (int) (z ^ (z >>> 32));
        return result;
    }
}
