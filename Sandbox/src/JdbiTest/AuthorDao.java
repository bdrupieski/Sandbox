package JdbiTest;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(AuthorMapper.class)
public interface AuthorDao
{
    @SqlUpdate("insert into author (first_name, last_name) values (:first, :last)")
    void insert(@Bind("first") String firstName, @Bind("last") String lastLast);

    @SqlQuery("select * from author where id = :id")
    Author findAuthorById(@Bind("id") int id);

    @SqlQuery("select * from author where first_name = :first")
    List<Author> findAuthorByFirstName(@Bind("first") String firstName);

    void close();
}
