using System;
using System.Threading.Tasks;

namespace Sandbox.NetCore.ConsoleStuff
{
    class Program
    {
        public static async Task Main(string[] args)
        {
            await DeleteUnboundPersistentVolumeClaims.DoIt();
        }
    }
}
