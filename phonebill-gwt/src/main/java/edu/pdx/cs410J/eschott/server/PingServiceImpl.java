package edu.pdx.cs410J.eschott.server;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.eschott.client.PhoneBill;
import edu.pdx.cs410J.eschott.client.PhoneCall;
import edu.pdx.cs410J.eschott.client.PingService;

import java.util.HashMap;
import java.util.Map;

/**
 * The server-side implementation of the Phone Bill service
 */
public class PingServiceImpl extends RemoteServiceServlet implements PingService
{
  private final Map<String, PhoneBill> data = new HashMap<>();


  @Override
  public AbstractPhoneBill ping(String customerName, PhoneCall call) {
    //If phone bill exists, adds to it
    //otherwise creates new phone bill
    if (!this.data.containsKey(customerName)) {
      PhoneBill bill = new PhoneBill(customerName);
      bill.addPhoneCall(call);
      this.data.put(customerName, bill);
    } else {
      PhoneBill bill = this.data.get(customerName);
      bill.addPhoneCall(call);
      this.data.replace(customerName, bill);
    }
    return this.data.get(customerName);
  }

  /**
   * Returns the the Phone Bill for the given customer
   *
   * @param customerName
   */
  @Override
  public AbstractPhoneBill ping(String customerName) {
    PhoneBill bill = this.data.get(customerName);
    if (bill == null) {
      //todo: should be handled in client
      Window.alert("Bill does not exist for this customer");
      return null;
    }
    return bill;
  }

  /**
   * Log unhandled exceptions to standard error
   *
   * @param unhandled
   *        The exception that wasn't handled
   */
  @Override
  protected void doUnexpectedFailure(Throwable unhandled) {
    unhandled.printStackTrace(System.err);
    super.doUnexpectedFailure(unhandled);
  }
}