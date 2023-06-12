package be.ehb.jfproject.dao;

        import be.ehb.jfproject.entities.Verkoper;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.query.Param;

public interface VerkoperDAO extends CrudRepository<Verkoper, Integer> {
        @Query("SELECT v FROM Verkoper v WHERE v.email = :email")
        Verkoper findByEmail(@Param("email") String email);

        @Query("SELECT v FROM Verkoper v WHERE v.username = :username")
        Verkoper findByUsername(@Param("username") String username);

}
