using System;
using PhoneNumbers;

namespace Sandbox.Console
{
    /// <summary>
    /// Trying out the PhoneNumberUtil package.
    /// </summary>
    public static class PhoneNumberUtilStuff
    {
        public static void DoIt()
        {
            var num = PhoneNumberUtil.GetInstance().ParseAndKeepRawInput(Int64.MaxValue.ToString(), RegionCode.US);
            System.Console.WriteLine(num);
        }

        public static bool IsValidPhoneNumber(string phone)
        {
            PhoneNumber phoneNumber;
            try
            {
                phoneNumber = PhoneNumberUtil.GetInstance().ParseAndKeepRawInput(phone, RegionCode.US);
            }
            catch (NumberParseException)
            {
                return false;
            }

            return PhoneNumberUtil.GetInstance().IsValidNumber(phoneNumber);
        }
    }
}