package com.example.a67371.tabtest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.UserData;
import com.example.a67371.tabtest.ui.adapter.DietAdapter;
import com.example.a67371.tabtest.ui.bean.BackBean;
import com.example.a67371.tabtest.ui.bean.Diet;
import com.example.a67371.tabtest.ui.bean.UserDataBean;
import com.example.a67371.tabtest.ui.calendar.CalendarDay;
import com.example.a67371.tabtest.ui.calendar.MaterialCalendarView;
import com.example.a67371.tabtest.ui.calendar.OnDateSelectedListener;
import com.example.a67371.tabtest.ui.dialog.AddFoodDialog;
import com.example.a67371.tabtest.ui.dialog.DateDialog;
import com.example.a67371.tabtest.ui.draw.DietView;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DietActivity extends AppCompatActivity {

    private String userName = UserData.getName();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
    Date day = new Date();
    private String selectDate;
    private int karori = 0;
    private List<Diet> dietList = new ArrayList<Diet>();

    @BindView(R.id.diet_image)
    ImageView dietImage;

    @BindView(R.id.diet_view)
    DietView dietView;

    @BindView(R.id.diet_date)
    TextView dietDate;

    @BindView(R.id.diet_food)
    ListView listView;

    DateDialog dateDialog;

    OnDateSelectedListener listener = new OnDateSelectedListener() {
        @Override
        public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
            day = date.getDate();
            selectDate=simpleDateFormat.format(date.getDate());
            dietDate.setText(df.format(date.getDate()));
            dateDialog.cancel();
        }
    };

    AddFoodDialog dialog;

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_food_number_button:
                    if(dialog.getFoodNumber().getText().toString().isEmpty()) {
                        Toast.makeText(DietActivity.this,"请输入数字", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int number = Integer.valueOf(dialog.getFoodNumber().getText().toString().trim());
                    if(number!=0) {
                        Diet d = dietList.get(dialog.getI());
                        int karori = d.getKarori() / d.getNumber();
                        d.setNumber(number);
                        d.setKarori(number * karori);
                        dietList.set(dialog.getI(), d);
                        DietAdapter adapter = new DietAdapter(DietActivity.this, R.layout.diet_food, dietList);
                        listView.setAdapter(adapter);
                    } else {
                        dietList.remove(dialog.getI());
                        ViewGroup.LayoutParams params = listView.getLayoutParams();
                        params.height = 240 * dietList.size();
                        listView.setLayoutParams(params);
                        if(dietList.isEmpty()) {
                            ImageView noDiet = (ImageView) findViewById(R.id.no_diet_image);
                            noDiet.setVisibility(View.VISIBLE);
                        } else {
                            DietAdapter adapter = new DietAdapter(DietActivity.this, R.layout.diet_food, dietList);
                            listView.setAdapter(adapter);
                        }
                    }

                    dialog.cancel();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectDate = simpleDateFormat.format(day);
        setContentView(R.layout.activity_diet);
        ButterKnife.bind(this);
        Http.getUserData(userName, selectDate, new Http.OnGetUserData() {
            @Override
            public void OnGetUserData(String s) {
                Gson gson = new Gson();
                UserDataBean userDataBean = gson.fromJson(s, UserDataBean.class);
                if(userDataBean.isSuccess()) karori = (int)userDataBean.getData().getCalorie();
                else Toast.makeText(DietActivity.this,"获取数据失败！", Toast.LENGTH_SHORT).show();
                dietView.setRestKarori(karori);
                new Thread(dietView).start();
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.diet_bar);
        TextView label = (TextView) findViewById(R.id.diet_label);
        label.setText("饮食");
        dietDate.setText(df.format(day));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dietImage.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog = new DateDialog(DietActivity.this, R.layout.date_dialog, listener);
                dateDialog.show();
            }
        });
        initAdapter();
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = 240 * dietList.size();
        listView.setLayoutParams(params);
        if(!dietList.isEmpty()) {
            DietAdapter adapter = new DietAdapter(DietActivity.this, R.layout.diet_food, dietList);
            listView.setAdapter(adapter);
        } else {
//            ImageView noDiet = (ImageView) findViewById(R.id.no_diet_image);
//            noDiet.setVisibility(View.VISIBLE);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog = new AddFoodDialog(DietActivity.this, R.layout.add_food_dialog, onClickListener, i);
                dialog.show();
            }
        });
        Button button1 = (Button)findViewById(R.id.diet_button1);
        Button button2 = (Button)findViewById(R.id.diet_button2);
        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietActivity.this, AddFoodActivity.class);
                startActivityForResult(intent,1);
            }
        });
        ScrollView scrollView = (ScrollView) findViewById(R.id.diet_scroll);
        scrollView.smoothScrollTo(0, 20);
    }

    private void initAdapter() {
        Diet d1 = new Diet(R.drawable.foodd,"包子",2,454);
//        dietList.add(d1);
//        dietList.add(d1);
//        dietList.add(d1);
//        dietList.add(d1);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
//                    String[] foodName = data.getStringArrayExtra("food_name");
//                    int[] foodNumber = data.getIntArrayExtra("food_number");
//                    int[] imageId = data.getIntArrayExtra("food_image");
//                    int[] foodKarori = data.getIntArrayExtra("food_karori");
//                    int index = data.getIntExtra("index", 0);
//                    for(int i = 0;i<index;i++) {
//                        boolean b = false;
//                        for(int j = 0;j<dietList.size();j++) {
//                            if (foodName[i].equals(dietList.get(j).getFoodName())) {
//                                Diet d = dietList.get(j);
//                                d.setNumber(d.getNumber() + foodNumber[i]);
//                                d.setKarori(d.getKarori() + foodKarori[i]);
//                                dietList.set(j, d);
//                                b = true;
//                                break;
//                            }
//                        }
//                        if(!b)dietList.add(new Diet(imageId[i], foodName[i], foodNumber[i], foodKarori[i]));
//                        b = false;
//                    }
//                    if(!dietList.isEmpty()) {
//                        ViewGroup.LayoutParams params = listView.getLayoutParams();
//                        params.height = 240 * dietList.size();
//                        listView.setLayoutParams(params);
//                        DietAdapter adapter = new DietAdapter(DietActivity.this, R.layout.diet_food, dietList);
//                        listView.setAdapter(adapter);
//                        ImageView noDiet = (ImageView) findViewById(R.id.no_diet_image);
//                        noDiet.setVisibility(View.GONE);
//                    }
                    int karori = data.getIntExtra("karori", 0);
                    this.karori+=karori;
                    Date date = new Date();
                    if(day.getTime()>date.getTime()) {
                        Toast.makeText(DietActivity.this,"选择日期大于当前日期", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Http.uploadUserData(userName, selectDate, 0, 0, 0, 0, this.karori, "", "", new Http.OnUploadUserData() {
                        @Override
                        public void OnUploadUserData(String s) {
                            Gson gson = new Gson();
                            BackBean back = gson.fromJson(s, BackBean.class);
                            if(back.isSuccess()) Toast.makeText(DietActivity.this,"上传成功！", Toast.LENGTH_SHORT).show();
                            else Toast.makeText(DietActivity.this,"上传失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dietView.setRestKarori(this.karori);
                    new Thread(dietView).start();
                }
                break;
            default:
        }
        new Thread(dietView).start();
    }
}
