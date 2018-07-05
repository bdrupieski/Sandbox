using System;
using System.Data;
using ConsoleDump;
using Dapper;
using Npgsql;
using Npgsql.NameTranslation;

namespace Sandbox.Dapper
{
    public class SomePostgresCustomTypeStuff : SomePostgresStuff
    {
        /*

CREATE DATABASE sandbox;
CREATE SCHEMA dappercustomtypestuff;

CREATE SEQUENCE dappercustomtypestuff.location_id_seq
    AS INTEGER
    MAXVALUE 2147483647;

CREATE SEQUENCE dappercustomtypestuff.location_active_id_seq
    AS INTEGER
    MAXVALUE 2147483647;

CREATE TYPE dappercustomtypestuff.ID_DATE AS
(
    id   INTEGER,
    date TIMESTAMP
);

CREATE TABLE dappercustomtypestuff.location
(
    id   SERIAL NOT NULL
        CONSTRAINT location_pkey
        PRIMARY KEY,
    name TEXT   NOT NULL
);

CREATE TABLE dappercustomtypestuff.location_active
(
    id         SERIAL NOT NULL
        CONSTRAINT location_active_pkey
        PRIMARY KEY,
    locationid INTEGER
        CONSTRAINT fk_location_active_location_id
        REFERENCES location,
    start      TIMESTAMP,
    "end"      TIMESTAMP
);

insert into location (name) values ('location 1');
insert into location (name) values ('location 2');
insert into location (name) values ('location 3');

insert into location_active (locationid, start, "end") values (1, '2018-01-01', '2018-01-15');
insert into location_active (locationid, start, "end") values (1, '2018-01-20', '2018-01-25');
insert into location_active (locationid, start, "end") values (2, '2018-01-01', '2018-01-05');
insert into location_active (locationid, start, "end") values (3, '2018-02-01', '2018-02-10');

        */

        public SomePostgresCustomTypeStuff() : base("sandbox", "dappercustomtypestuff")
        {
            var npgsqlSnakeCaseNameTranslator = new NpgsqlSnakeCaseNameTranslator();
            SqlMapper.AddTypeHandler(new IdDateTypeHandler());
            NpgsqlConnection.GlobalTypeMapper.MapComposite<IdDate>("dappercustomtypestuff.id_date", npgsqlSnakeCaseNameTranslator);
        }

        public override void DoSomeStuff()
        {
            // TODO: upgrade to npgsql 4.0.2 when it's released to fix this https://github.com/npgsql/npgsql/issues/1983
            //ShowLocations();
            TryQueryingCustomType();
            //JoinOnUnnestedCollectionOfCustomTypes();
        }

        private void ShowLocations()
        {
            using (var conn = GetConnection())
            {
                var locations = conn.Query<Location>("select * from location");
                var locationActives = conn.Query<LocationActive>("select * from location_active");

                locations.Dump();
                locationActives.Dump();
            }
        }

        // kind of like passing a TVP in SQL Server
        private void JoinOnUnnestedCollectionOfCustomTypes()
        {
            using (var conn = GetConnection())
            {
                var idDates = new[]
                {
                    new IdDate
                    {
                        Id = 1,
                        Date = new DateTime(2018, 1, 2)
                    },
                    new IdDate
                    {
                        Id = 2,
                        Date = new DateTime(2018, 12, 25)
                    },
                };

                string sql = @"
                    select LA.* from location L
                    inner join location_active LA on LA.locationid = L.id
                    inner join unnest(@idDates) idDates on idDates.id = LA.locationid
                    where idDates.date < LA.end
                    ;";

                var result = conn.Query<LocationActive>(sql, new { idDates = idDates });
                result.Dump();
            }
        }

        private void TryQueryingCustomType()
        {
            using (var conn = GetConnection())
            {
                var idDates = new[]
                {
                    new IdDate
                    {
                        Id = 42,
                        Date = new DateTime(2018, 1, 2)
                    },
                    new IdDate
                    {
                        Id = 57,
                        Date = DateTime.Now
                    },
                };

                // If you try to query into a type that has a SqlMapper.TypeHandler<T>,
                // then Dapper will call that TypeHandler's Parse method but
                // only pass the first column in the result rows. 
                // This means for the above query it'll pass an int 
                // to SqlMapper.TypeHandler<IdDate>.Parse since
                // that's the first column in this result.
                try
                {
                    var result = conn.Query<IdDate>("select s.* from unnest(@idDates) s;", new { idDates });
                    result.Dump();
                }
                catch (Exception e)
                {
                    Console.WriteLine($"Yeah, it failed. This happened: {e.Message}");
                }

                // This _will_ work.
                var result2 = conn.Query<IdDateOutput>("select s.id, s.date from unnest(@idDates) s;", new { idDates });
                result2.Dump();
            }
        }
    }

    public class IdDateTypeHandler : SqlMapper.TypeHandler<IdDate>
    {
        public override void SetValue(IDbDataParameter parameter, IdDate value)
        {
            if (parameter is NpgsqlParameter param)
            {
                param.Value = value;
            }
        }

        public override IdDate Parse(object value)
        {
            return (IdDate)value;
        }
    }

    public class IdDateOutput
    {
        public int Id { get; set; }
        public DateTime Date { get; set; }
    }

    // mapped to postgres type id_date
    public class IdDate
    {
        public int Id { get; set; }
        public DateTime Date { get; set; }
    }

    public class Location
    {
        public int Id { get; set; }
        public string Name { get; set; }
    }

    public class LocationActive
    {
        public int Id { get; set; }
        public int LocationId { get; set; }
        public DateTime Start { get; set; }
        public DateTime End { get; set; }
    }
}