using System;

namespace Sandbox.VisualizeCircuitBreaker.Simulation
{
    public class HealthCount : IEquatable<HealthCount>
    {
        public int Successes { get; }
        public int Failures { get; }
        public int Total => Successes + Failures;
        public DateTime StartedAt { get; }
        public TimeSpan? DifferenceFromNextHealthCount { get;}

        public HealthCount(int successes, int failures, DateTime startedAt, TimeSpan? difference = null)
        {
            Successes = successes;
            Failures = failures;
            StartedAt = startedAt;
            DifferenceFromNextHealthCount = difference;
        }

        public bool Equals(HealthCount other)
        {
            if (ReferenceEquals(null, other)) return false;
            if (ReferenceEquals(this, other)) return true;
            return Successes == other.Successes && Failures == other.Failures && StartedAt.Equals(other.StartedAt);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((HealthCount)obj);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                var hashCode = Successes;
                hashCode = (hashCode * 397) ^ Failures;
                hashCode = (hashCode * 397) ^ StartedAt.GetHashCode();
                return hashCode;
            }
        }

        public static bool operator ==(HealthCount left, HealthCount right)
        {
            return Equals(left, right);
        }

        public static bool operator !=(HealthCount left, HealthCount right)
        {
            return !Equals(left, right);
        }
    }
}