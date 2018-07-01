using System;
using ConsoleDump;
using Dapper;
using DapperExtensions;
using DapperExtensions.Sql;
using Npgsql;

namespace Sandbox.Dapper
{
    public static class BasicPostgresStuff
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

        private const string ConnectionString = "User ID=postgres;Password=postgres;Host=localhost;Port=5432;Database=sandbox;Search Path=dapper;Pooling=true;";

        public static void DoIt()
        {
            DapperExtensions.DapperExtensions.SqlDialect = new PostgreSqlDialect();

            SimplePredicate();
            MultipleObjectsPerRow();
            MultipleQueries();

            InsertPerson();
            UpdatePerson();
            QueryAllPeople();
            DeletePerson();
            QueryAllPeople();
        }

        private static NpgsqlConnection GetConnection()
        {
            return new NpgsqlConnection(ConnectionString);
        }

        public static void QueryAllPeople()
        {
            using (var conn = GetConnection())
            {
                var people = conn.Query<Person>("select * from person order by id desc limit 10");
                Console.WriteLine(people.Dump());
            }
        }

        public static void InsertPerson()
        {
            using (var conn = GetConnection())
            {
                var person = new Person
                {
                    Name = "barclay",
                    Age = 44,
                };
                
                var key = conn.Insert(person);
                Console.WriteLine(key);
            }
        }

        public static void UpdatePerson()
        {
            using (var conn = GetConnection())
            {
                var person = conn.QuerySingle<Person>("select * from person limit 1");

                person.Age++;

                conn.Update(person);
            }
        }

        public static void DeletePerson()
        {
            using (var conn = GetConnection())
            {
                var person = conn.QuerySingle<Person>("select * from person id limit 1");

                conn.Delete(person);
            }
        }

        public static void SimplePredicate()
        {
            using (var conn = GetConnection())
            {
                var people = conn.GetList<Person>(Predicates.Field<Person>(f => f.Age, Operator.Eq, 44));

                people.Dump();
            }
        }

        public static void MultipleObjectsPerRow()
        {
            using (var conn = GetConnection())
            {
                var people = conn.Query<Person, Person, Tuple<Person, Person>>("select person.*, person.* from person", (person, person1) => new Tuple<Person, Person>(person, person1));

                people.Dump();
            }
        }

        public static void MultipleQueries()
        {
            using (var conn = GetConnection())
            {
                var gridReader = conn.QueryMultiple("select * from person; select * from person order by id desc");

                var people1 = gridReader.Read<Person>();
                var people2 = gridReader.Read<Person>();

                people1.Dump();
                people2.Dump();
            }
        }
    }

    public class Person
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public int Age { get; set; }
    }
}