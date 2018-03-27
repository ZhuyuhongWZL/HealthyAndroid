package com.mrjk.persoanl.mrjk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class weight_record extends AppCompatActivity {
    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_record);
        cardView = (CardView)findViewById(R.id.weight_card);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(weight_record.this,Weight_input_Activity.class);
                startActivity(intent);
            }
        });
    }
}
