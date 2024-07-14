package com.example.examinspection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    String url;
    String data;
    String localURL = "";
    String onlineURL = "";
    String cam1Url = "";
    String cam2Url = "";

    TextToSpeech T;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.refreshLayout);



        final ImageButton up = findViewById(R.id.imageButton6);
        final ImageButton down = findViewById(R.id.imageButton5);
        final ImageButton right = findViewById(R.id.imageButton3);
        final ImageButton left = findViewById(R.id.imageButton4);
        final ImageButton camRight = findViewById(R.id.imageButton);
        final ImageButton camLeft = findViewById((R.id.imageButton2));

        final ImageView settings = findViewById(R.id.imageView6);
        final WebView cameraFeed = findViewById(R.id.webView);
        final Button cam1 = findViewById(R.id.button2);
        final Button cam2 = findViewById(R.id.button3);

        final Button go = findViewById(R.id.button);
//        final WebView webView = findViewById(R.id.webView);
//        final TextView t = findViewById(R.id.textView);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("ip", Context.MODE_PRIVATE);
        cam1Url = sp.getString("ip1", "");
        cam2Url = sp.getString("ip2", "");
        url = sp.getString("RD", "");

        WebSettings webSettings = cameraFeed.getSettings();
        webSettings.setJavaScriptEnabled(true);

        T = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    T.setLanguage(Locale.ENGLISH);
                }
            }
        });

        url = getIntent().getStringExtra("url");
        // Load an initial URL
//        webView.loadUrl("http://" + url);

        // Set up WebViewClient to open links within the WebView
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });



        // Set up SwipeRefreshLayout to refresh the WebView
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cameraFeed.reload();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    postDataUsingVolley("A");

                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    postDataUsingVolley("B");
                }
                return false;
            }
        });

        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    postDataUsingVolley("C");
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    postDataUsingVolley("D");
                }
                return false;
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    postDataUsingVolley("E");
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    postDataUsingVolley("F");
                }
                return false;
            }
        });

        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    postDataUsingVolley("G");
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    postDataUsingVolley("H");
                }
                return false;
            }
        });


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cmdText = null;
                String btnState = go.getText().toString().toLowerCase();
                switch (btnState) {
                    case "go line off":
                        go.setText("Go line On");
//                        t.setText(url);
                        cmdText = "I";
                        break;
                    case "go line on":
                        go.setText("Go line Off");
                        cmdText = "J";
                        break;
                }
                postDataUsingVolley(cmdText);
            }

        });

        camRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    postDataUsingVolley("K");
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    postDataUsingVolley("L");
                }

                return false;
            }
        });

        camLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    postDataUsingVolley("M");
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    postDataUsingVolley("L");
                }
                return false;
            }
        });

        cam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cam1Name = cam1.getText().toString().toLowerCase();
                if (cam1Name.equals("cam1 off")) {
                    cam1.setText("cam1 on");
                    cam2.setText("cam2 off");

                }
                cameraFeed.loadUrl(sp.getString("ip1", ""));
                cameraFeed.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
            }
        });


        cam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cam2Name = cam2.getText().toString().toLowerCase();
                if (cam2Name.equals("cam2 off")) {
                    cam1.setText("cam1 off");
                    cam2.setText("cam2 on");

                }
                cameraFeed.loadUrl(sp.getString("ip2", ""));
                cameraFeed.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
            }
        });

    }


    private void postDataUsingVolley(String name) {
        // url to post our data
        String url = "http://192.168.4.1/post";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                data = response;
                Toast.makeText(MainActivity2.this, data, Toast.LENGTH_SHORT).show();
                int dis = Integer.parseInt(data);
                if (dis < 50) {
//                   Toast.makeText(MainActivity2.this, "Obstacle observed at " + dis + " centimeter", Toast.LENGTH_SHORT).show();
                    T.speak("Obstacle observed at " + dis + " centimeter", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(MainActivity2.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key
                // and value pair to our parameters.
                params.put("data", name);

                // at last we are
                // returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }
}