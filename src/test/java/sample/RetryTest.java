package sample;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import sample.service.RetryService;

@SpringBootTest(classes = Application.class)
public class RetryTest {

  @SpyBean
  private RetryService retryService;

  @Test
  public void callMonoRetry() {
    assertThrows(RuntimeException.class, ()-> retryService.monoFailure().block());
    verify(retryService, times(3)).monoFailure();
  }

  @Test
  public void callRetry() {
    assertThrows(RuntimeException.class, ()->retryService.failure());
    verify(retryService, times(3)).failure();
  }
}
