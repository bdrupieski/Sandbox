using System;
using System.Collections.Generic;
using System.Net;
using RedLockNet;
using RedLockNet.SERedis;
using RedLockNet.SERedis.Configuration;

namespace Sandbox.Redis
{
    /// <summary>
    /// Trying out https://github.com/samcook/RedLock.net
    /// docker run -p 6379:6379 -d redis
    /// </summary>
    public static class RedlockStuff
    {
        public static void DoIt()
        {
            var redlockFactory = RedLockFactory.Create(new List<RedLockEndPoint>
            {
                new DnsEndPoint("localhost", 6379)
            });

            string resource = "some-resource";

            using (var firstLock = redlockFactory.CreateLock(resource, TimeSpan.FromSeconds(0.1)))
            {
                PrintLock(firstLock);

                using (var secondLock = redlockFactory.CreateLock(resource, TimeSpan.FromSeconds(0.1), TimeSpan.FromSeconds(3), TimeSpan.FromSeconds(0.25)))
                {
                    PrintLock(secondLock);
                }
            }
        }
        
        private static void PrintLock(IRedLock redLock)
        {
            Console.WriteLine(redLock.IsAcquired);
            Console.WriteLine(redLock.ExtendCount);
            Console.WriteLine(redLock.LockId);
            Console.WriteLine(redLock.Resource);
        }
    }
}