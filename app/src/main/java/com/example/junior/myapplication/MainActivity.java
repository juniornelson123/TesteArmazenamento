package com.example.junior.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView foto;
    Button tirar;
    TextView uri;
    static final int REQUEST_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foto=(ImageView) findViewById(R.id.foto);
        tirar=(Button) findViewById(R.id.tirar);
        uri=(TextView) findViewById(R.id.uri);

        tirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE && resultCode == RESULT_OK){
            Uri fotoUri=data.getData();
            uri.setText(fotoUri.toString());
            try {
                Bitmap b= MediaStore.Images.Media.getBitmap(this.getContentResolver(),fotoUri);
                foto.setImageBitmap(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
