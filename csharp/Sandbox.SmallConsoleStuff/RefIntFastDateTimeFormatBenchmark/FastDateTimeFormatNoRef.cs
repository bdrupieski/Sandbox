using System;

namespace Sandbox.SmallConsoleStuff.RefIntFastDateTimeFormatBenchmark
{
    public static class FastDateTimeFormatNoRef
    {
        private static readonly char[] _buffer = new char[22];

        public static string FastFormat(DateTime dt)
        {
            int offset = 0;
            char[] chars = _buffer;

            int month = dt.Month;
            if (month < 10)
                offset = Write1Char(chars, offset, month);
            else
                offset = Write2Chars(chars, offset, month);

            chars[offset++] = '/';
            offset = Write2Chars(chars, offset, dt.Day);
            chars[offset++] = '/';
            offset = Write4Chars(chars, offset, dt.Year);
            chars[offset++] = ' ';

            int hour = dt.Hour % 12;
            if (hour < 10)
                offset = Write1Char(chars, offset, hour);
            else
                offset = Write2Chars(chars, offset, hour);

            chars[offset++] = ':';
            offset = Write2Chars(chars, offset, dt.Minute);
            chars[offset++] = ':';
            offset = Write2Chars(chars, offset, dt.Second);
            chars[offset++] = ' ';
            chars[offset++] = dt.Hour > 12 ? 'P' : 'A';
            chars[offset++] = 'M';

            return new string(chars, 0, offset);
        }

        private static int Write1Char(char[] chars, int offset, int value)
        {
            chars[offset++] = Digit(value);

            return offset;
        }

        private static int Write2Chars(char[] chars, int offset, int value)
        {
            chars[offset++] = Digit(value / 10);
            chars[offset++] = Digit(value % 10);

            return offset;
        }

        private static int Write4Chars(char[] chars, int offset, int value)
        {
            chars[offset++] = Digit(value / 1000);
            chars[offset++] = Digit(value / 100 % 10);
            chars[offset++] = Digit(value / 10 % 100);
            chars[offset++] = Digit(value % 10);

            return offset;
        }

        private static char Digit(int value)
        {
            return (char)(value + '0');
        }
    }
}