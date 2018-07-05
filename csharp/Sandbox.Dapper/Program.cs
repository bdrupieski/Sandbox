using DapperExtensions.Sql;

namespace Sandbox.Dapper
{
    class Program
    {
        static void Main(string[] args)
        {
            DapperExtensions.DapperExtensions.SqlDialect = new PostgreSqlDialect();

            new SomePostgresCustomTypeStuff().DoSomeStuff();
        }
    }
}
