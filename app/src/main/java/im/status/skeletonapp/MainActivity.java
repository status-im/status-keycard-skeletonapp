package im.status.skeletonapp;

import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import im.status.keycard.android.NFCCardManager;

public class MainActivity extends AppCompatActivity {
  private NfcAdapter nfcAdapter;
  private NFCCardManager cardManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    nfcAdapter = NfcAdapter.getDefaultAdapter(this);

    this.cardManager = new NFCCardManager();
    this.cardManager.setCardListener(new AppCardListener());
    cardManager.start();
  }

  @Override
  public void onResume() {
    super.onResume();
    if (nfcAdapter != null) {
      nfcAdapter.enableReaderMode(this, this.cardManager,NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,null);
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    if (nfcAdapter != null) {
      nfcAdapter.disableReaderMode(this);
    }
  }
}
