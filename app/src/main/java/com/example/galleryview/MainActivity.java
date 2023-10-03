package com.example.galleryview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Integer> imgIds = new ArrayList<>(Arrays.asList(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7
    ));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.girdView);


        gridView.setAdapter(new ImageAdapter(imgIds,MainActivity.this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int itemPosition = imgIds.get(i);
               showDialogueBox(itemPosition);
            }
        });
    }

    public void showDialogueBox(int itemPosition){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialouge_layout);

        // getting custom view
        ImageView dialogImg;
        Button fullViewBtn, closeBtn;



        dialogImg = dialog.findViewById(R.id.dialogImage);
        closeBtn = dialog.findViewById(R.id.closeBtn);
        fullViewBtn = dialog.findViewById(R.id.fullViewBtn);

        dialogImg.setImageResource(itemPosition);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        fullViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FullImageView.class);
                intent.putExtra("imgId", itemPosition);
                startActivity(intent);
            }
        });

        dialog.show();
    }
}