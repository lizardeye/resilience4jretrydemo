package sample.service;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RetryService {

  @Retry(name = "SERVICE")
  public Mono<String> monoFailure() {
    return Mono.error(new RuntimeException("FATALITY"));
  }

  @Retry(name = "SERVICE")
  public String failure() {
    throw new RuntimeException("FATALITY");
  }
}
