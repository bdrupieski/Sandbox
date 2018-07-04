using System;
using System.Collections.Generic;
using ConsoleDump;
using Dapper;
using DapperExtensions;
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
            GetListPredicate();
            MultipleObjectsPerRow();
            MultipleQueries();

            InsertPerson();
            UpdatePerson();
            QueryAllPeople();
            DeletePerson();
            QueryAllPeople();
        }

        #region Plain Dapper

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

        #endregion

        #region DapperExtensions

        protected void InsertPerson()
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

        protected void UpdatePerson()
        {
            using (var conn = GetConnection())
            {
                var person = conn.QuerySingle<Person>("select * from person limit 1");

                person.Age++;

                conn.Update(person).Dump();
            }
        }

        protected void DeletePerson()
        {
            using (var conn = GetConnection())
            {
                var person = conn.QuerySingle<Person>("select * from person id limit 1");

                conn.Delete(person).Dump();
            }
        }

        // The predicate stuff in DapperExtensions is junk. Don't use it.

        protected void GetListPredicate()
        {
            using (var conn = GetConnection())
            {
                conn.GetList<Person>(Predicates.Field<Person>(f => f.Age, Operator.Eq, 44)).Dump();
            }
        }

        protected void CountPredicateProperty()
        {
            using (var conn = GetConnection())
            {
                // NREs if you don't call it just right.
                conn.Count<Person>(Predicates.Property<Person, Person>(x => x.Name, Operator.Eq, x => x.Name)).Dump();
            }
        }

        protected void CountPredicateField()
        {
            using (var conn = GetConnection())
            {
                conn.Count<Person>(Predicates.Field<Person>(x => x.Name, Operator.Eq, "barclay")).Dump();
            }
        }

        protected void GetPagePredicateField()
        {
            using (var conn = GetConnection())
            {
                var predicate = Predicates.Field<Person>(x => x.Name, Operator.Eq, "barclay");
                var sorts = new List<ISort>
                {
                    new Sort
                    {
                        PropertyName = "Name",
                        Ascending = false
                    }
                };

                // This doesn't seem to work?
                conn.GetPage<Person>(predicate, sorts, page: 0, resultsPerPage: 3).Dump();
                conn.GetPage<Person>(predicate, sorts, page: 1, resultsPerPage: 3).Dump();
                conn.GetPage<Person>(predicate, sorts, page: 2, resultsPerPage: 3).Dump();
                conn.GetPage<Person>(predicate, sorts, page: 3, resultsPerPage: 3).Dump();
                conn.GetPage<Person>(predicate, sorts, page: 4, resultsPerPage: 3).Dump();
            }
        }

        #endregion
    }
}