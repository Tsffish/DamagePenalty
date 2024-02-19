package github.tsffish.damagepenalty.util;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DamageMultipliers {
    public static ConcurrentMap<UUID, Double> damageMultipliers = new ConcurrentHashMap<>();
    public static final double defaultMultipliers = 1;
}
