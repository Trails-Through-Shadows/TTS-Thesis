public class Entity<T> {

    private Integer id;
    private Integer idGroup;

    private int health;
    private int defence;
    private int initiative;

    private EntityType type;
    private T entity;

    private final List<Effect> effects = new ArrayList<>();

    public void addEffect(Effect effect) { ... }
    public void applyEffect(Effect effect) { ... }
    public void decreaseEffectDuration(Effect effect) { ... }

    public void damage(int damage, DamageSource source) { ... }
    public void heal(int heal) { ... }

    public void startTurn() { ... }
    public void endTurn() { ... }

    public enum EntityType {
        CHARACTER,
        ENEMY,
        SUMMON,
        OBSTACLE,
    }

    public enum DamageSource {
        ATTACK,
        EFFECT,
    }
}

