using Npgsql;

namespace Sandbox.Dapper
{
    public abstract class SomePostgresStuff
    {
        private readonly string _databaseName;
        private readonly string _schemaName;

        protected SomePostgresStuff(string databaseName, string schemaName)
        {
            _databaseName = databaseName;
            _schemaName = schemaName;
        }

        protected NpgsqlConnection GetConnection()
        {
            string connectionString = "User ID=postgres;Password=postgres;Host=localhost;Port=5432;" +
                                      $"Database={_databaseName};Search Path={_schemaName};Pooling=true;";

            return new NpgsqlConnection(connectionString);
        }

        public abstract void DoSomeStuff();
    }
}