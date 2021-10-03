package com.example.pemjsondescargardehttp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button boton;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = findViewById(R.id.button);
        imagen = findViewById(R.id.imageView);



        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask().execute("https://mcdonalds.es/api/cms/images/mcdonalds-es/38f31b25-62e7-4b59-9f58-87f805f616e5_4+-+HM+SOLA.png?auto=compress,format");
            }

        });




    }
    public class DownloadTask extends AsyncTask<String,Void,Bitmap>{





        public Bitmap getBitmap(String url) {
            try {
                InputStream is = (InputStream) new URL(url).getContent();
                Bitmap d = BitmapFactory.decodeStream(is);
                is.close();
                return d;
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            return getBitmap(urls[0]);

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imagen.setImageBitmap(bitmap);

        }
    }
}

