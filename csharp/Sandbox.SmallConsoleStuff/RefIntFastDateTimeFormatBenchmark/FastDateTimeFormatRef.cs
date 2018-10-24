using System;

namespace Sandbox.SmallConsoleStuff.RefIntFastDateTimeFormatBenchmark
{
    public class FastDateTimeFormatRef
    {
        private static readonly char[] _buffer = new char[22];

        public static string FastFormat(DateTime dt)
        {
            int offset = 0;
            char[] chars = _buffer;

            int month = dt.Month;
            if (month < 10)
                Write1Char(chars, ref offset, month);
            else
                Write2Chars(chars, ref offset, month);

            chars[offset++] = '/';
            Write2Chars(chars, ref offset, dt.Day);
            chars[offset++] = '/';
            Write4Chars(chars, ref offset, dt.Year);
            chars[offset++] = ' ';

            int hour = dt.Hour % 12;
            if (hour < 10)
                Write1Char(chars, ref offset, hour);
            else
                Write2Chars(chars, ref offset, hour);

            chars[offset++] = ':';
            Write2Chars(chars, ref offset, dt.Minute);
            chars[offset++] = ':';
            Write2Chars(chars, ref offset, dt.Second);
            chars[offset++] = ' ';
            chars[offset++] = dt.Hour > 12 ? 'P' : 'A';
            chars[offset++] = 'M';

            return new string(chars, 0, offset);
        }

        private static void Write1Char(char[] chars, ref int offset, int value)
        {
            chars[offset++] = Digit(value);
        }

        private static void Write2Chars(char[] chars, ref int offset, int value)
        {
            chars[offset++] = Digit(value / 10);
            chars[offset++] = Digit(value % 10);
        }

        private static void Write4Chars(char[] chars, ref int offset, int value)
        {
            chars[offset++] = Digit(value / 1000);
            chars[offset++] = Digit(value / 100 % 10);
            chars[offset++] = Digit(value / 10 % 100);
            chars[offset++] = Digit(value % 10);
        }

        private static char Digit(int value)
        {
            return (char)(value + '0');
        }
    }
}