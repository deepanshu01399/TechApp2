package com.deepanshu.android.techapp2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.deepanshu.android.techapp2.ui.gallery.GalleryFragment;
import com.deepanshu.android.techapp2.ui.share.ShareFragment;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;
    WebView webView;
    DrawerLayout drawer;
    private Boolean exit = false;
    TextView networkTxtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        webView = findViewById(R.id.webView);
        networkTxtStatus = findViewById(R.id.networkStatus);
        checkConnection();
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if(id==R.id.nav_Facebook){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/deepanshu.deepanshu.7545/")));
                }else if(id==R.id.nav_twitter){
                    webView.loadUrl("https://twitter.com/Deepans75015916/");
                   // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Deepans75015916/")));
                }else if(id==R.id.nav_linkedIn){
                    webView.loadUrl("https://www.linkedin.com/login");//with in app web page is open
                }else if(id==R.id.nav_google){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")));

                }else if(id==R.id.nav_flipkart){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.flipkart.com/")));

                }else if(id==R.id.nav_amazon){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/")));
                }else if(id==R.id.nav_send){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.whatsapp.com/")));

                }else if(id==R.id.nav_share){
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareMessage= "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                }
                else if(id==R.id.aboutUs){
                startActivity(new Intent(MainActivity.this,AboutUs.class));
                }
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_share);
        }

    }


    protected boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else
            return false;
    }
    private void checkConnection() {
        if (isOnline()) {
            Toast.makeText(MainActivity.this, "you are connected to internet", Toast.LENGTH_LONG).show();
            webView.loadUrl("https://www.youtube.com/feed/trending");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
        } else {
            networkTxtStatus.setText("Something went wrong!\n Not connected to server\n plz check your Internet");
            //WebSettings webSettings = webView.getSettings();
            //webSettings.setJavaScriptEnabled(true);
            //webView.loadUrl("");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Email) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"deepanshu01399@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(Intent.EXTRA_TEXT, "mail body");
            startActivity(Intent.createChooser(intent, ""));
            //webView.loadUrl("https://www.amazon.in");
            //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/")));
            return true;
        }
        if (id == R.id.ContectUs) {
            String phone = "+917452825490";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel" , phone,null));
            startActivity(intent);
            return super.onOptionsItemSelected(item);
        }return false;
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            if (webView.canGoBack()) {
                webView.goBack();//vedio ke chalen ke bad  back dabanenge to exit double tap me hogi
            } else {
                if (exit) {
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Press again to exit", Toast.LENGTH_LONG).show();
                }
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 2000);//2 sec ke ander agar tap kar diye to ext hoga vrna nahi
                exit = true;
            }
        }
    }
}
