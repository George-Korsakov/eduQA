package ru.stqa.pft.soap;

import com.acme.mypackage.BLZService;
import com.acme.mypackage.DetailsType;
import com.acme.mypackage.GetBankType;
import org.junit.Assert;
import org.junit.Test;
//import org.testng.annotations.Test;

public class GetBankTypeTests {

  @Test
  public void getBankTypeTest(){
    String t = String.valueOf(60060000);
    DetailsType bank = new BLZService().getBLZServiceSOAP12PortHttp().getBank(t);
    System.out.println(bank);
    Assert.assertEquals(bank.getBezeichnung(), "DZ BANK");
  }

}
