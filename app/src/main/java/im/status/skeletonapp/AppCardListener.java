package im.status.skeletonapp;

import im.status.keycard.applet.ApplicationInfo;
import im.status.keycard.applet.KeycardCommandSet;
import im.status.keycard.io.APDUException;
import im.status.keycard.io.CardChannel;
import im.status.keycard.io.CardListener;

import java.io.IOException;

public class AppCardListener implements CardListener {
  KeycardCommandSet cmdSet;

  @Override
  public void onConnected(CardChannel channel) {
    cmdSet = new KeycardCommandSet(channel);

    try {
      //TODO: here goes the logic
      cmdSet.select().checkOK();
      ApplicationInfo info = cmdSet.getApplicationInfo();
      System.out.println("Applet version : " + info.getAppVersionString());

    } catch(APDUException e) {
      //TODO: change this with your favorite error handling/logging strategy
      System.out.println("Unexpected SW");
      e.printStackTrace();
    } catch(IOException e) {
      //TODO: change this with your favorite error handling/logging strategy
      System.out.println("Communication error");
      e.printStackTrace();
    }

  }

  @Override
  public void onDisconnected() {

  }
}
