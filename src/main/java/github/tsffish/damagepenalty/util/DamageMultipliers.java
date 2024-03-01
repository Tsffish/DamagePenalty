package github.tsffish.damagepenalty.util;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DamageMultipliers {
    private ConcurrentMap<UUID, Double> damageMultipliers = new ConcurrentHashMap<>();
    public ConcurrentMap<UUID, Double> getDamageMultiplierMap() {
        return new ConcurrentHashMap<>(damageMultipliers);
    }
}
