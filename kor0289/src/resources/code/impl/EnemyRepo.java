public interface EnemyRepo extends JpaRepository<EnemyDTO, Integer> {

    @Query("select c " +
            "from EnemyDTO c " +
            "join HexEnemyDTO he on c.id = he.key.idEnemy " +
            "where he.key.idLocation = ?1")
    List<EnemyDTO> findAllByLocationId(int id);
}