using System;

namespace Sandbox.SmallConsoleStuff.RefIntFastDateTimeFormatBenchmark
{
    public static class FastDateTimeFormatHardcodedIndexes
    {
        private static readonly char[] _buffer = new char[22];

        public static string FastFormat(DateTime dt)
        {
            int offset = 0;
            char[] chars = _buffer;

            Write2Chars(chars, 0, dt.Month);
            chars[2] = '/';
            Write2Chars(chars, 3, dt.Day);
            chars[5] = '/';
            Write4Chars(chars, 6, dt.Year);
            chars[10] = ' ';
            Write2Chars(chars, 11, dt.Hour);
            chars[13] = ':';
            Write2Chars(chars, 14, dt.Minute);
            chars[16] = ':';
            Write2Chars(chars, 17, dt.Second);
            chars[19] = ' ';
            chars[20] = dt.Hour > 12 ? 'P' : 'A';
            chars[21] = 'M';

            return new string(chars, 0, offset);
        }

        private static void Write1Char(char[] chars, int offset, int value)
        {
            chars[offset] = Digit(value);
        }

        private static void Write2Chars(char[] chars, int offset, int value)
        {
            chars[offset] = Digit(value / 10);
            chars[offset + 1] = Digit(value % 10);
        }

        private static void Write4Chars(char[] chars, int offset, int value)
        {
            chars[offset] = Digit(value / 1000);
            chars[offset + 1] = Digit(value / 100 % 10);
            chars[offset + 2] = Digit(value / 10 % 100);
            chars[offset + 3] = Digit(value % 10);
        }

        private static char Digit(int value)
        {
            return (char)(value + '0');
        }
    }
}