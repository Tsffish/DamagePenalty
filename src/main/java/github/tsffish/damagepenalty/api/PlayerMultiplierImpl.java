package github.tsffish.damagepenalty.api;

import github.tsffish.damagepenalty.util.DamageMultipliers;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PlayerMultiplierImpl implements PlayerMultipliers {
    private final ConcurrentMap<UUID, Double> damageMultipliers;
    private final DamageMultipliers damage = new DamageMultipliers();
    public PlayerMultiplierImpl() {
        this.damageMultipliers = damage.getDamageMultiplierMap();
    }
    @Override
    public void setDamageMultiplier(UUID playerId, double multiplier) {
        damageMultipliers.put(playerId, multiplier);
    }

    @Override
    public void removeDamageMultiplier(UUID playerId) {
        damageMultipliers.remove(playerId);
    }

    @Override
    public double getDamageMultiplier(UUID playerId) {
        return damageMultipliers.getOrDefault(playerId, 1.0);
    }

    @Override
    public ConcurrentMap<UUID, Double> getDamageMultiplierMap() {
        return new ConcurrentHashMap<>(damageMultipliers);
    }
}

