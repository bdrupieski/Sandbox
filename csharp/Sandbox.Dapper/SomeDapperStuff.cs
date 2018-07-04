using System;
using ConsoleDump;
using Dapper;
using DapperExtensions.Sql;
using Sandbox.Dapper.DatabaseModels;

namespace Sandbox.Dapper
{
    public class SomeDapperStuff : SomePostgresStuff
    {
        /*

CREATE DATABASE sandbox;
CREATE SCHEMA dapper;

CREATE SEQUENCE dapper.person_id_seq
    AS INTEGER
    MAXVALUE 2147483647;

CREATE TABLE dapper.person
(
    id   SERIAL NOT NULL
        CONSTRAINT person_pkey
        PRIMARY KEY,
    name TEXT   NOT NULL,
    age  INTEGER DEFAULT 0
);

         */

        public SomeDapperStuff() : base("sandbox", "dapper")
        {
            DapperExtensions.DapperExtensions.SqlDialect = new PostgreSqlDialect();
        }

        public override void DoSomeStuff()
        {
            QueryAllPeople();
            MultipleObjectsPerRow();
            MultipleQueries();
        }

        protected void QueryAllPeople()
        {
            using (var conn = GetConnection())
            {
                conn.Query<Person>("select * from person order by id desc limit 10").Dump();
            }
        }

        protected void MultipleObjectsPerRow()
        {
            using (var conn = GetConnection())
            {
                conn.Query<Person, Person, Tuple<Person, Person>>("select person.*, person.* from person", (person, person1) => new Tuple<Person, Person>(person, person1)).Dump();
            }
        }

        protected void MultipleQueries()
        {
            using (var conn = GetConnection())
            {
                var gridReader = conn.QueryMultiple("select * from person; select * from person order by id desc");

                gridReader.Read<Person>().Dump();
                gridReader.Read<Person>().Dump();
            }
        }
    }
}