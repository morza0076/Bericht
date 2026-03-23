package at.brugger.berichte;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.splashscreen.SplashScreen;
import androidx.drawerlayout.widget.DrawerLayout;
import android.widget.ImageButton;
import android.widget.Button;
import android.view.Gravity;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int FILE_CHOOSER_REQUEST_CODE = 1001;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1002;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1003;
    private static final int CREATE_DOCUMENT_REQUEST_CODE = 1004;
    private static final int NATIVE_CAMERA_REQUEST_CODE = 1005;
    private static final int NATIVE_GALLERY_REQUEST_CODE = 1006;
    private static final String APP_URL = "file:///android_asset/www/index.html";
    private static final int PENDING_NATIVE_PHOTO_NONE = 0;
    private static final int PENDING_NATIVE_PHOTO_CAMERA = 1;

    private WebView webView;
    private ValueCallback<Uri[]> filePathCallback;
    private Uri cameraOutputUri;
    private GeolocationPermissions.Callback geolocationCallback;
    private String geolocationOrigin;
    private WebChromeClient.FileChooserParams pendingFileChooserParams;
    private byte[] pendingSaveBytes;
    private String pendingSaveMimeType;
    private String pendingSaveFileName;
    private int pendingNativePhotoAction = PENDING_NATIVE_PHOTO_NONE;

    // Drawer/Menu
    private DrawerLayout drawerLayout;
    private ImageButton menuButton;
    private Button btnFoto;
    private Button btnDrucken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Drawer/Menu initialisieren
        drawerLayout = findViewById(R.id.drawer_layout);
        menuButton = findViewById(R.id.menu_button);
        btnFoto = findViewById(R.id.btn_foto);
        btnDrucken = findViewById(R.id.btn_drucken);

        menuButton.setOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(Gravity.START)) {
                drawerLayout.closeDrawer(Gravity.START);
            } else {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        btnFoto.setOnClickListener(v -> {
            // Foto-Logik: Dialog zur Auswahl Kamera/Galerie anzeigen
            showPhotoSourceChooser();
            drawerLayout.closeDrawer(Gravity.START);
        });

        btnDrucken.setOnClickListener(v -> {
            // Druck-Logik: aktuelle Seite drucken
            printCurrentPage();
            drawerLayout.closeDrawer(Gravity.START);
        });

        webView = findViewById(R.id.webView);
        configureWebView();
        webView.loadUrl(APP_URL);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);
                } else if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
            }
        });
    }

    // ...EXISTIERENDER CODE AUS DEINER MAINACTIVITY (Methoden wie configureWebView, showPhotoSourceChooser, printCurrentPage, usw.)...
}
