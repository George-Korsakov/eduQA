package beginer.simple;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTest {
// тест на функции проверки на простое число
  @Test
  public void testPrime(){
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test(enabled = false)
  public void testNonPrime(){
    Assert.assertTrue(Primes.isPrimeWhile(Integer.MAX_VALUE-2));
 }

  @Test(enabled = false)
  public void testPrimeLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }

  @Test
  public void testPrimeFast(){
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }


}
