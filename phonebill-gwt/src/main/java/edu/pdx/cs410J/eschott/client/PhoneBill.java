package edu.pdx.cs410J.eschott.client;

import com.google.gwt.user.client.Window;
import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PhoneBill extends AbstractPhoneBill
{
  private ArrayList<PhoneCall> calls = new ArrayList<PhoneCall>();
  private String customerName;

  /**
   * Method to create phone bill
   * @param customerName
   */
  public PhoneBill(String customerName) {
    this.customerName = customerName;
  }

  /**
   * Default constructor needed for extending AbstractPhoneBill
   */
  public PhoneBill() {
  }

  @Override
  public String getCustomer() {
    return customerName;
  }

  @Override
  public void addPhoneCall(AbstractPhoneCall call) {
    boolean found = false;
    for(PhoneCall o : calls) {
      if (call.equals(o)){
        Window.alert("Call already exists in phone bill");
        found = true;
      }
    }
    if (!found){
      this.calls.add((PhoneCall)call);
      Collections.sort(calls);
    }

  }

  @Override
  public Collection getPhoneCalls() {
    return this.calls;
  }


}

