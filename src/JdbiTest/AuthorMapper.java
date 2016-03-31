package JdbiTest;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements ResultSetMapper<Author>
{
    public Author map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        return new Author(r.getInt("id"), r.getString("first_name"), r.getString("last_name"));
    }
}