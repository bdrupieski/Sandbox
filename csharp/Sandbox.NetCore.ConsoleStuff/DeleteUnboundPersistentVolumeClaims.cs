using System;
using System.Linq;
using System.Threading.Tasks;
using k8s;

namespace Sandbox.NetCore.ConsoleStuff
{
    public static class DeleteUnboundPersistentVolumeClaims
    {
        /// <summary>
        /// Run kubectl proxy first.
        /// </summary>
        public static async Task DoIt()
        {
            var config = new KubernetesClientConfiguration { Host = "http://127.0.0.1:8001" };
            var client = new Kubernetes(config);

            await DeleteUnboundPersistentVolumeClaimsForNamespace(client);
        }

        private static async Task DeleteUnboundPersistentVolumeClaimsForNamespace(Kubernetes client)
        {
            var namespaces = await client.ListNamespaceAsync();

            foreach (var ns in namespaces.Items)
            {
                await DeleteUnboundPersistentVolumeClaimsForNamespace(client, ns.Metadata.Name);
            }
        }

        private static async Task DeleteUnboundPersistentVolumeClaimsForNamespace(Kubernetes client, string targetNamespace)
        {
            var pods = await client.ListNamespacedPodAsync(targetNamespace);
            var pvcs = await client.ListNamespacedPersistentVolumeClaimAsync(targetNamespace);

            var allPvcs = pvcs.Items
                .Select(x => x.Metadata.Name)
                .ToHashSet();

            var boundPvcs = pods.Items
                .Where(x => x.Spec?.Volumes != null)
                .SelectMany(x => x.Spec.Volumes)
                .Select(x => x.PersistentVolumeClaim?.ClaimName)
                .Where(x => !String.IsNullOrWhiteSpace(x))
                .ToHashSet();

            var unboundPvcs = allPvcs.Except(boundPvcs).ToList();

            foreach (var unboundPvc in unboundPvcs)
            {
                await client.DeleteNamespacedPersistentVolumeClaimAsync(unboundPvc, targetNamespace);
                Console.WriteLine(unboundPvc);
            }
        }
    }
}