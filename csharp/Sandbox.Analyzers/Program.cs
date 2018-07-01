using System;
using SwifterSharp;

namespace Sandbox.Analyzers
{
    class Program
    {
        static void Main(string[] args)
        {
            SomeMethod(a: 1, b: 2, c: 3);
            
            // Will not compile.
            //SomeMethod(1, b: 2, c: 3);
        }

        [ForceNamedArguments]
        public static void SomeMethod(int a, int b, int c)
        {
            Console.WriteLine("do some stuff");
        }
    }
}
