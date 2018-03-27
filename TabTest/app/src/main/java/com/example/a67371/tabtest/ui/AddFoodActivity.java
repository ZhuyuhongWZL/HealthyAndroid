package com.example.a67371.tabtest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.UserData;
import com.example.a67371.tabtest.ui.adapter.AddFoodAdapter;
import com.example.a67371.tabtest.ui.bean.AddFood;
import com.example.a67371.tabtest.ui.bean.BackBean;
import com.example.a67371.tabtest.ui.bean.Food;
import com.example.a67371.tabtest.ui.dialog.AddFoodDialog;
import com.example.a67371.tabtest.ui.dialog.AddNewFoodDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddFoodActivity extends AppCompatActivity {

    private int lastIndex;
    private int totalIndex;
    private boolean isLoading = false;
    private String userName = UserData.getName();

    AddFoodAdapter adapter;

    private List<AddFood> addFoodList = new ArrayList<AddFood>();
    private List<Food.FoodBean> foodBeans = new ArrayList<Food.FoodBean>();

    private Intent intent;

    private AddFoodDialog dialog;

    private AddNewFoodDialog newFoodDialog;

    private View loadingView;

    @BindView(R.id.add_food_search_text)
    TextView search;

    @BindView(R.id.add_food_search_button)
    Button button;

    @BindView(R.id.add_food_list)
    ListView listView;

    @BindView(R.id.add_food_linear)
    LinearLayout addFoodLinear;

    @BindView(R.id.add_food_button)
    Button addFoodButton;

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            dialog = new AddFoodDialog(AddFoodActivity.this, R.layout.add_food_dialog,onClickListener, i);
            dialog.show();
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.add_food_number_button:
                    if(dialog.getFoodNumber().getText().toString().isEmpty()) {
                        Toast.makeText(AddFoodActivity.this,"请输入数字", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int number = Integer.valueOf(dialog.getFoodNumber().getText().toString().trim());
                    AddFood f = addFoodList.get(dialog.getI());
                    f.setFoodNumber(number);
                    addFoodList.set(dialog.getI(), f);
                    adapter.updateView(addFoodList);
                    dialog.cancel();
                    break;
            }
        }
    };

    View.OnClickListener newFoodOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String newFoodName = newFoodDialog.getFoodName().getText().toString().trim();
            String newFoodAlias = newFoodDialog.getFoodAlias().getText().toString().trim();
            if(newFoodDialog.getFoodKarori().getText().toString().trim().isEmpty()) {
                Toast.makeText(AddFoodActivity.this,"请输入每份的卡路里量", Toast.LENGTH_SHORT).show();
                return;
            }
            int newFoodKarori = Integer.valueOf(newFoodDialog.getFoodKarori().getText().toString().trim());
            if(newFoodName.equals("")) {
                Toast.makeText(AddFoodActivity.this,"请输入食物名称", Toast.LENGTH_SHORT).show();
                return;
            }
            Http.uploadFood(newFoodName, newFoodAlias, newFoodKarori, userName, new Http.OnUploadFood() {
                @Override
                public void OnUploadFood(String s) {
                    Gson gson = new Gson();
                    BackBean back = gson.fromJson(s, BackBean.class);
                    if(back.isSuccess()) {
                        Toast.makeText(AddFoodActivity.this,"上传成功！", Toast.LENGTH_SHORT).show();
                        newFoodDialog.cancel();
                        return;
                    } else {
                        Toast.makeText(AddFoodActivity.this,"上传失败！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });
        }
    };

    Handler handler=new Handler();

    Runnable searchFood = new Runnable() {
        @Override
        public void run() {
            AddFood addFood;
            addFoodList = new ArrayList<AddFood>();
            for(int i = 0;i<foodBeans.size();i++) {
                addFood = new AddFood(foodBeans.get(i).getName(), R.drawable.foodd, foodBeans.get(i).getCalorie());
                addFoodList.add(addFood);
            }
            adapter = new AddFoodAdapter(AddFoodActivity.this, addFoodList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(onItemClickListener);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar)findViewById(R.id.add_food_bar);
        toolbar.setTitle("添加饮食");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initAdapter();
        if(!addFoodList.isEmpty()) {
            adapter = new AddFoodAdapter(AddFoodActivity.this, addFoodList);
            listView.setAdapter(adapter);
            loadingView = LayoutInflater.from(AddFoodActivity.this).inflate(R.layout.load, null);
            loadingView.setVisibility(View.VISIBLE);
//            listView.addFooterView(loadingView,null,false);
            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    if(lastIndex == totalIndex && (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE))
                    {
                        //表示此时需要显示刷新视图界面进行新数据的加载(要等滑动停止)
                        if(!isLoading)
                        {
                            //不处于加载状态的话对其进行加载
                            isLoading = true;
                            onLoad();
                        }
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    lastIndex = firstVisibleItem + visibleItemCount;
                    totalIndex = totalItemCount;
                }
            });
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(search.getText().toString())) {
                    Toast.makeText(AddFoodActivity.this,"搜索不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                search.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                Http.food(search.getText().toString().trim(), new Http.OnFood() {
                    @Override
                    public void OnFood(String s) {
                        Gson gson = new Gson();
                        Food food = gson.fromJson(s, Food.class);
                        System.out.println(s);
                        if(food.isSuccess()) {
                            if(listView.getVisibility()==View.GONE)listView.setVisibility(View.VISIBLE);
                            if(addFoodLinear.getVisibility()==View.VISIBLE)addFoodLinear.setVisibility(View.GONE);
                            foodBeans = food.getFood();
                            handler.post(searchFood);
                        } else {
                            listView.setVisibility(View.GONE);
                            addFoodLinear.setVisibility(View.VISIBLE);
                            addFoodButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    newFoodDialog = new AddNewFoodDialog(AddFoodActivity.this, R.layout.add_new_food_dialog, newFoodOnClickListener);
                                    newFoodDialog.show();
                                }
                            });
                        }
                    }

                });
            }
        });
    }

    /**
     * 刷新加载
     */
    public void onLoad()
    {
        if(adapter == null)
        {
            adapter = new AddFoodAdapter(AddFoodActivity.this, addFoodList);
            listView.setAdapter(adapter);
        }else
        {
            loadList();
            adapter.updateView(addFoodList);
        }
        loadComplete();//刷新结束
    }

    public void loadList() {

    }

    /**
     * 加载完成
     */
    public void loadComplete()
    {
        isLoading = false;//设置正在刷新标志位false
        AddFoodActivity.this.invalidateOptionsMenu();
        if(false) listView.removeFooterView(loadingView);//如果是最后一页的话，则将其从ListView中移出
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.yes:
//                String[]foodName = new String[100];
//                int[]foodNumber = new int[100];
//                int[]imageId = new int[100];
//                int[]foodKarori = new int[100];
//                int index = 0;
//                for(int i = 0;i<addFoodList.size();i++)
//                    if(addFoodList.get(i).getFoodNumber()!=0) {
//                        foodName[index] = addFoodList.get(i).getFoodName();
//                        foodNumber[index] = addFoodList.get(i).getFoodNumber();
//                        imageId[index] = addFoodList.get(i).getImageId();
//                        foodKarori[index] = addFoodList.get(i).getEachKarori()*foodNumber[index];
//                        index++;
//                    }
//                intent = new Intent();
//                intent.putExtra("food_name", foodName);
//                intent.putExtra("food_number", foodNumber);
//                intent.putExtra("food_image", imageId);
//                intent.putExtra("food_karori", foodKarori);
//                intent.putExtra("index", index);
//                setResult(RESULT_OK, intent);
                int karori = 0;
                for(int i = 0;i<addFoodList.size();i++)
                    if(addFoodList.get(i).getFoodNumber()!=0)
                        karori+=addFoodList.get(i).getEachKarori()*addFoodList.get(i).getFoodNumber();
                intent = new Intent();
                intent.putExtra("karori", karori);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_food_menu, menu);//加载menu布局
        return true;
    }

    public void initAdapter() {
//        Http.food("", new Http.OnFood() {
//            @Override
//            public void OnFood(String s) {
//                Gson gson = new Gson();
//                Food food = gson.fromJson(s, Food.class);
//                System.out.println(s);
//                if(food.isSuccess()) {
//                    foodBeans = food.getFood();
//                    handler.post(searchFood);
//                } else {
//                    Toast.makeText(AddFoodActivity.this,"搜索失败！", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        });

    }

}
