@Data
@Entity
@NoArgsConstructor
@Table(name = "Enemy")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EnemyDTO extends Validable implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 128)
    private String title;

    //other fields

    @OneToMany(mappedBy = "key.idEnemy", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonSerialize(using = LazyFieldsSerializer.class)
    private List<EnemyEffectDTO> effects = new ArrayList<>();

    @OneToMany(mappedBy = "key.idEnemy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonSerialize(using = LazyFieldsSerializer.class)
    private List<EnemyActionDTO> actions = new ArrayList<>();


    @JsonIgnore
    public List<EffectDTO> getMappedEffects() { ... }

   
    // region Validation
    @Override
    protected void validateInner(@Nullable ValidationConfig validationConfig) { ... }

    @Override
    public String getValidableValue() { ... }
    // endregion
}