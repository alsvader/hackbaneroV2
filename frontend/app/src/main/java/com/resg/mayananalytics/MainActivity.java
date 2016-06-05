package com.resg.mayananalytics;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.loadUrl("file:///android_asset/index.html");
        mWebView.setWebViewClient(new myViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        getSupportActionBar().hide();
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed()
    {
        if(mWebView.canGoBack()){
            mWebView.goBack();
        }else{
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Salir!")
                    .setMessage("¿Realmente está seguro de salir?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clearWebView();
                            mWebView.loadUrl("file:///android_asset/index.html");
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void clearWebView() {
        mWebView.loadData("", "text/html", "utf-8");
        //networkWebView.clearView();
        mWebView.clearCache(false);
        mWebView.clearCache(true);

        mWebView.clearFormData();
        mWebView.clearHistory();
        mWebView.clearCache(true);
        mWebView.clearMatches();
    }
    public class myViewClient extends WebViewClient {
        ProgressDialog pd = null;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true; // as mentioned in below notes, for your case., you do 'return false'
        }

        @Override
        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
            //Context context = getApplicationContext();
            //CharSequence text = description;
            //int duration = Toast.LENGTH_SHORT;

            //Toast toast = Toast.makeText(context, text, duration);
            //toast.show();
            pd.dismiss();
            mWebView.loadUrl("file:///android_asset/error.html");
        }

        @Override
        public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            pd = new ProgressDialog(MainActivity.this);
            pd.setTitle("Espere un momento");
            pd.setMessage("Mayan Analytics está cargando...");
            pd.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pd.dismiss();
        }

    }
}
