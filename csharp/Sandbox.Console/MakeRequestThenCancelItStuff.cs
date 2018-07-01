using System;
using System.Net.Http;
using System.Threading;
using Nito.AsyncEx.Synchronous;

namespace Sandbox.Console
{
    /// <summary>
    /// Trying out making an HTTP request using <see cref="HttpClient"/> and then canceling
    /// the request before it's completed, and seeing how my own API reacts to those canceled
    /// requests. WebAPI throws OperationCanceledException or TaskCanceledException when this
    /// happens.
    /// </summary>
    public class MakeRequestThenCancelItStuff
    {
        public static void DoIt()
        {
            var httpClient = new HttpClient();

            var cancellationTokenSource = new CancellationTokenSource();
            var token = cancellationTokenSource.Token;

            var delay = "http://localhost:21230/api/v2/tests/delayasync";
            var task = httpClient.GetAsync(delay, token);

            cancellationTokenSource.CancelAfter(600);

            try
            {
                task.WaitAndUnwrapException();
            }
            catch (Exception e)
            {
                System.Console.WriteLine(e);
            }

            System.Console.WriteLine(task.IsCanceled);
        }
    }
}