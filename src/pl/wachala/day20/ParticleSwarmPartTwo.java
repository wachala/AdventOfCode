package pl.wachala.day20;

import java.util.*;

public class ParticleSwarmPartTwo extends ParticleSwarmPartOne {

    public int getNumberOfParticles(List<Particle> input) {
        for (int i = 0; i < 100; i++) {
            Map<Vector, Set<Integer>> positionsToParticles = new HashMap<>();

            input.forEach(particle -> {
                Vector velocity = particle.getV().increaseBy(particle.getA());
                Vector position = particle.getP().increaseBy(velocity);

                particle.setV(velocity);
                particle.setP(position);

                Set<Integer> previous = positionsToParticles.getOrDefault(position, new HashSet<>());
                previous.add(particle.getId());
                positionsToParticles.put(position, previous);
            });

            Collection<Set<Integer>> values = positionsToParticles.values();

            for (Set<Integer> value : values)
                if (value.size() > 1)
                    input.removeIf(p -> value.contains(p.getId()));
        }

        return input.size();
    }

}
