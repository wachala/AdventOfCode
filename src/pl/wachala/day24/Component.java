package pl.wachala.day24;

public class Component {
    private int firstPort;
    private int secondPort;

    public Component(int firstPort, int secondPort) {
        this.firstPort = firstPort;
        this.secondPort = secondPort;
    }

    public int getFirstPort() {
        return firstPort;
    }

    public int getSecondPort() {
        return secondPort;
    }

    public boolean couldBeUsed(int portRequired) {
        return firstPort == portRequired || secondPort == portRequired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component component = (Component) o;

        if (firstPort != component.firstPort) return false;
        return secondPort == component.secondPort;
    }

    @Override
    public int hashCode() {
        int result = firstPort;
        result = 31 * result + secondPort;
        return result;
    }
}
