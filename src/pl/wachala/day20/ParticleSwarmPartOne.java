package pl.wachala.day20;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Long.parseLong;
import static java.lang.Math.abs;

public class ParticleSwarmPartOne {

    public long getClosestParticle(List<Particle> particles) {
        particles.sort((p1, p2) -> {
            int compare = Long.compare(getAbsoluteAcceleration(p1), getAbsoluteAcceleration(p2));
            if (compare != 0) return compare;
            compare = Long.compare(getAbsoluteInitialSpeed(p1), getAbsoluteInitialSpeed(p2));
            if (compare != 0) return compare;
            compare = Long.compare(getAbsoluteInitialPosition(p1), getAbsoluteInitialPosition(p2));
            if (compare != 0) return compare;

            return 0;
        });

        return particles.get(0).getId();
    }

    private Long getAbsoluteAcceleration(Particle particle) {
        Vector position = particle.getP();
        Vector acceleration = particle.getA();

        return Math.abs(position.getX() * acceleration.getX())
                + Math.abs(position.getY() * acceleration.getY())
                + Math.abs(position.getZ() * acceleration.getZ());
    }

    private Long getAbsoluteInitialSpeed(Particle particle) {
        return getDistance(particle.getV());
    }

    private Long getAbsoluteInitialPosition(Particle particle) {
        return getDistance(particle.getP());
    }

    private Long getDistance(Vector position) {
        return abs(position.getX()) + abs(position.getY()) + abs(position.getZ());
    }

    public List<Particle> parserInput(String input[]) {
        List<Particle> particles = new LinkedList<>();

        for (int i = 0; i < input.length; i++) {
            String splitted[] = input[i].split(", ");

            Vector p = toVector(splitted[0]);
            Vector v = toVector(splitted[1]);
            Vector a = toVector(splitted[2]);

            particles.add(new Particle(i, p, v, a));
        }

        return particles;
    }

    private Vector toVector(String line) {
        int startIndex = line.indexOf("<");
        int lastIndex = line.indexOf(">");

        String[] split = line.substring(startIndex + 1, lastIndex).split(",");

        return new Vector(parseLong(split[0]), parseLong(split[1]), parseLong(split[2]));
    }

}
