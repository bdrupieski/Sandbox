package JdbiTest;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.text.MessageFormat;
import java.util.List;

public class JdbiTestStuff {
    public static void main(String[] args) {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/library");
        config.setUsername("postgres");
        config.setPassword("postgres");
        HikariDataSource hikariDataSource = new HikariDataSource(config);

        DBI dbi = new DBI(hikariDataSource);
        Handle h = dbi.open();

        h.execute("insert into author (first_name, last_name) values (?, ?)", "John", "Doe");

        List<Author> authors = h.createQuery("select * from author where id = :id")
                .bind("id", 1)
                .map(new AuthorMapper())
                .list();

        h.close();

        for (Author a: authors) {
            System.out.println(MessageFormat.format("{0} {1} ({2})", a.getFirstName(), a.getLastName(), a.getId()));
        }

        useDaoALittleBit(dbi);
        useDaoALittleBitMore(dbi);
    }

    private static void useDaoALittleBit(DBI dbi) {
        AuthorDao dao = dbi.open(AuthorDao.class);

        Author authorById = dao.findAuthorById(3);
        System.out.println(MessageFormat.format("ID: {0} name: {1} {2}",
                authorById.getId(), authorById.getFirstName(), authorById.getLastName()));

        Author authorByIdNull = dao.findAuthorById(10000000);
        System.out.println(authorByIdNull);

        dao.insert("Jane", "Doe");
        dao.insert("Billy", "Field");

        dao.close();
    }

    private static void useDaoALittleBitMore(DBI dbi) {
        AuthorDao dao = dbi.onDemand(AuthorDao.class);

        List<Author> janes = dao.findAuthorByFirstName("Jane");
        System.out.println(janes);
    }
}
