package pl.wachala.day20;

public class Particle {
    private int id;
    private Vector p;
    private Vector v;
    private Vector a;

    public Particle(int id, Vector p, Vector v, Vector a) {
        this.id = id;
        this.p = p;
        this.v = v;
        this.a = a;
    }

    public Vector getP() {
        return p;
    }

    public Vector getV() {
        return v;
    }

    public Vector getA() {
        return a;
    }

    public int getId() {
        return id;
    }

    public void setP(Vector p) {
        this.p = p;
    }

    public void setV(Vector v) {
        this.v = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Particle particle = (Particle) o;

        if (p != null ? !p.equals(particle.p) : particle.p != null) return false;
        if (v != null ? !v.equals(particle.v) : particle.v != null) return false;
        return a != null ? a.equals(particle.a) : particle.a == null;
    }

    @Override
    public int hashCode() {
        int result = p != null ? p.hashCode() : 0;
        result = 31 * result + (v != null ? v.hashCode() : 0);
        result = 31 * result + (a != null ? a.hashCode() : 0);
        return result;
    }
}
