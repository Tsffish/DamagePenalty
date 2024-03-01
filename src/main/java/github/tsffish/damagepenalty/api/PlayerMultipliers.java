package github.tsffish.damagepenalty.api;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public interface PlayerMultipliers {
    void setDamageMultiplier(UUID playerId, double multiplier);
    void removeDamageMultiplier(UUID playerId);
    double getDamageMultiplier(UUID playerId);
    ConcurrentMap<UUID, Double> getDamageMultiplierMap();
}
