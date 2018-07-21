package ResilienceStuff;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Random;

public class TryOutResilience4J {

    public static void main(String[] args) {
        var resilienceStuff = new ResilienceStuff();
        resilienceStuff.retryStuff();
        resilienceStuff.circuitBreakerStuff();
    }
}

class ResilienceStuff {

    private Random random;
    private Retry retry;
    private CircuitBreaker circuitBreaker;

    ResilienceStuff() {
        random = new Random();

        var retryConfig = RetryConfig
                .custom()
                .retryOnException(throwable -> throwable instanceof SomeCustomException)
                .maxAttempts(40)
                .waitDuration(Duration.ofMillis(10))
                .build();

        this.retry = Retry.of("default_retry", retryConfig);

        var circuitBreakerConfig = CircuitBreakerConfig
                .custom()
                .failureRateThreshold(0.95f)
                .ringBufferSizeInClosedState(10)
                .recordFailure(throwable -> throwable instanceof SomeCustomException)
                .waitDurationInOpenState(Duration.ofSeconds(10))
                .build();

        this.circuitBreaker = CircuitBreaker.of("default_circuit_breaker", circuitBreakerConfig);
    }

    void retryStuff() {
        try {
            var result = retry.executeSupplier(() -> workThatFailsSometimes(0.85f));
            System.out.println(MessageFormat.format("Finished successfully! The result was {0}", result));
        } catch (SomeCustomException e) {
            System.out.println("well you don't always win");
        }
    }

    void circuitBreakerStuff() {
        var supplier = CircuitBreaker.decorateSupplier(circuitBreaker, () -> workThatFailsSometimes(0.50f));
        supplier = Retry.decorateSupplier(retry, supplier);

        circuitBreaker.getEventPublisher().onSuccess(event -> {
            var msg = MessageFormat.format("success! took this long {0}", event.getElapsedDuration());
            System.out.println(msg);
        });

        circuitBreaker.getEventPublisher().onError(event -> {
            var msg = MessageFormat.format("error! took this long {0}", event.getElapsedDuration());
            System.out.println(msg);
        });

        for (int i = 0; i < 100; i++) {
            supplier.get();
        }
    }

    private String workThatFailsSometimes(float failureRate) throws SomeCustomException {
        if (random.nextFloat() <= failureRate) {
            System.out.println("work failed!");
            throw new SomeCustomException();
        }

        System.out.println("completed some work");

        return "job's done!";
    }
}

class SomeCustomException extends RuntimeException {
}