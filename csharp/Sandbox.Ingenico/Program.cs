using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using Newtonsoft.Json;

namespace Sandbox.Ingenico
{
    class Program
    {
        /// <summary>
        /// Automatically generate parts of class/enum declarations from Ingenico WSAPI documentation.
        /// </summary>
        static void Main(string[] args)
        {
            GenerateEnumsFromCsvs();
        }

        private static void GenerateEnumsFromCsvs()
        {
            var files = Directory.EnumerateFiles("C:\\Users\\bdrupieski\\Desktop\\csvs");

            var outputLines = new List<string>();

            foreach (var file in files.Where(x => x.EndsWith(".csv")))
            {
                var lines = File.ReadAllLines(file);

                var numberOfHeaders = lines[0].Split(",").Length;
                var dataLines = lines.Skip(1).ToArray();
                
                string enumName;
                string comment;
                string number;
                
                outputLines.Add($"public enum {Path.GetFileNameWithoutExtension(file)}");
                outputLines.Add("{");

                foreach (var dataLine in dataLines)
                {
                    var d = dataLine.Split(",", 3);

                    if (numberOfHeaders == 2)
                    {
                        enumName = d[0];
                        number = "";
                        comment = d[1];
                    }
                    else
                    {
                        enumName = d[0];
                        number = $" = {d[1]}";
                        comment = d[2];
                    }

                    var declaration = $@"
    /// <summary>
    /// {comment}
    /// </summary>
    [EnumMember(Value = ""{enumName}"")]
    {enumName.ToPascalCase()}{number},";

                    outputLines.Add(declaration);
                }

                outputLines.Add("}");
                outputLines.Add("");
                outputLines.Add("");
            }

            File.WriteAllLines("C:\\Users\\bdrupieski\\Desktop\\out.txt", outputLines);
        }

        private static void GenerateJson()
        {
            var lines = File.ReadAllLines("C:\\Users\\bdrupieski\\Desktop\\ingenico_error_codes.txt");

            var output = new List<IngenicoErrorCode>();

            for (int i = 0; i < lines.Length; i += 2)
            {
                var typeAndName = lines[i].Split(" ", 2, StringSplitOptions.RemoveEmptyEntries);
                var numberAndDescription = lines[i + 1].Split(" ", 2, StringSplitOptions.RemoveEmptyEntries);

                string type = typeAndName[0];
                string name = typeAndName[1];

                string number = numberAndDescription[0];
                string description = numberAndDescription[1];


                output.Add(new IngenicoErrorCode
                {
                    Type = type,
                    Name = name,
                    Value = int.Parse(number),
                    Description = description,
                });
            }

            var json = JsonConvert.SerializeObject(output, Formatting.Indented);

            File.WriteAllText("C:\\Users\\bdrupieski\\Desktop\\ingenico_error_codes.json", json);
        }

        class IngenicoErrorCode
        {
            public string Type { get; set; }
            public string Name { get; set; }
            public int Value { get; set; }
            public string Description { get; set; }
        }

        private static void GenerateCsv()
        {
            var lines = File.ReadAllLines("C:\\Users\\bdrupieski\\Desktop\\ingenico_error_codes.txt");

            var output = new List<string>();
            output.Add("Type,Name,Value,Description");

            for (int i = 0; i < lines.Length; i += 2)
            {
                var typeAndName = lines[i].Split(" ", 2, StringSplitOptions.RemoveEmptyEntries);
                var numberAndDescription = lines[i + 1].Split(" ", 2, StringSplitOptions.RemoveEmptyEntries);

                string type = typeAndName[0];
                string name = typeAndName[1];

                string number = numberAndDescription[0];
                string description = numberAndDescription[1];

                
                output.Add($"{type},{name},{number},\"{description}\"");
            }

            File.WriteAllLines("C:\\Users\\bdrupieski\\Desktop\\some_csv.csv", output);
        }

        private static void GenerateClassStuff()
        {
            var lines = File.ReadAllLines("C:\\Users\\bdrupieski\\Desktop\\ingenico_error_codes.txt");

            var output = new List<string>();

            for (int i = 0; i < lines.Length; i += 2)
            {
                var typeAndName = lines[i].Split(" ", 2, StringSplitOptions.RemoveEmptyEntries);
                var numberAndDescription = lines[i + 1].Split(" ", 2, StringSplitOptions.RemoveEmptyEntries);

                string type = typeAndName[0];
                string name = typeAndName[1];

                string number = numberAndDescription[0];
                string description = numberAndDescription[1];

                string property = $@"
/// <summary>
/// {type} - {description}
/// </summary>
public const int {name} = {number};";

                output.Add(property);
            }

            File.WriteAllLines("C:\\Users\\bdrupieski\\Desktop\\error_code_enum_stuff.txt", output);
        }
    }
}
