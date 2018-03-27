package com.example.a67371.tabtest.ui;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.UserData;
import com.example.a67371.tabtest.ui.Weather.TodayWeather;
import com.example.a67371.tabtest.location.LocationHelper;
import com.example.a67371.tabtest.ui.bean.BackBean;
import com.example.a67371.tabtest.ui.bean.UserDataBean;
import com.example.a67371.tabtest.ui.refresh.MainRefreshableView;
import com.google.gson.Gson;
import com.leng.jbq.ISportStepInterface;
import com.leng.jbq.TodayStepManager;
import com.leng.jbq.TodayStepService;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by 67371 on 2018/3/7.
 */

public class FirstFragment extends android.support.v4.app.Fragment {
    private String context = "多读书多看报少吃零食多睡觉  ";
    private TextView mTextView;

    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    UserDataBean[] userDataBean = new UserDataBean[7];
    int index = 0;

    public FirstFragment() {
    }

    public FirstFragment(String context) {
        this.context = context;
    }

    public View view;
    private static final int REFRESH_STEP_WHAT = 0;

    //循环取当前时刻的步数中间的间隔时间
    private long TIME_INTERVAL_REFRESH = 500;

    private Handler mDelayHandler = new Handler(new TodayStepCounterCall());
    private int mStepSum;

    private ISportStepInterface iSportStepInterface;


    private LineChartView lineChart;
    String[] weeks = new String[7];//X轴的标注
    //图表的数据
    int[] bushuarray=new int[7];

    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisValues = new ArrayList<AxisValue>();

    private ColumnChartView chart;
    private ColumnChartData data;
    public final static String[] week = new String[7];
    private List<Integer> list = new ArrayList<>();
    /*for(int i=0;i<7;i++){
    * list.add();}*/

    private TextView textView1,textView2,textView3;

    MainRefreshableView refreshableView;
    private Handler mHandler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what)
            {
                case 1:
                    updateTodayWeather((TodayWeather)message.obj);
                    break;
                default:
                    break;
            }
        }
    };
    void updateTodayWeather(TodayWeather todayWeather)
    {
        //TextView textView1=(TextView)view.findViewById(R.id.);
        TextView textView2=(TextView)view.findViewById(R.id.dgree);
        TextView textView3=(TextView)view.findViewById(R.id.weatherbox);
        //textView1.setText(todayWeather.getCity());
        textView2.setText(todayWeather.getWendu()+"℃");
        textView3.setText(todayWeather.getType());

        //Toast.makeText(MainActivity.this,"更新成功",Toast.LENGTH_SHORT).show();
    }
    TodayWeather todayWeather=null;

    public static String weizhi;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.first_fragment, container, false);
        Date datee = new Date();
        datee = new Date(datee.getTime() - 86400000L * 6);
        SimpleDateFormat df = new SimpleDateFormat("MM-dd");
        for(int i=0;i<7;i++) {
            week[i] = df.format(datee);
            weeks[i] = df.format(datee);
            datee = new Date(datee.getTime() + 86400000L);
        }
        refreshableView = (MainRefreshableView) view.findViewById(R.id.first_refreshable_view);
        refreshableView.setOnRefreshListener(new MainRefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                index = 0;
                date = new Date();
                initData(1);
            }
        }, 1);
        initData(0);
        final TextView citybox = (TextView) view.findViewById(R.id.citybox);


        //初始化计步模块
        TodayStepManager.init(getActivity().getApplication());

//        mStepArrayTextView = (TextView)findViewById(R.id.stepArrayTextView);

        //开启计步Service，同时绑定Activity进行aidl通信
        Intent intent = new Intent(getActivity(), TodayStepService.class);
        getActivity().startService(intent);
        getActivity().bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //Activity和Service通过aidl进行通信
                iSportStepInterface = ISportStepInterface.Stub.asInterface(service);
                try {
                    mStepSum = iSportStepInterface.getCurrentTimeSportStep();
                    updateStepCount();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                mDelayHandler.sendEmptyMessageDelayed(REFRESH_STEP_WHAT, TIME_INTERVAL_REFRESH);

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);


//获取日期
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("EE");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String riqi = formatter1.format(curDate);
        String xingqi = formatter2.format(curDate);
        TextView riqibox = (TextView) view.findViewById(R.id.riqi);
        TextView xingqibox = (TextView) view.findViewById(R.id.xingqi);
        xingqibox.setText(xingqi);
        riqibox.setText(riqi);


//获取地理位置

        LocationHelper helper = new LocationHelper(getActivity(), new LocationHelper.CallBack() {
            @Override
            public void callback(boolean isSuccess, String message, String city, String town) {
                if (isSuccess) {
                    citybox.setText(city + " " + town + ":");
                    getWeatherDatafromNet(city.substring(0,city.length()-1));
                } else {
                    citybox.setText(message);
                }
            }
        });
        helper.getLocation();//获取位置




        //ImageView imageView1 = (ImageView) view.findViewById(R.id.bushutu);
        //ImageView imageView2 = (ImageView) view.findViewById(R.id.kalulitu);
        //imageView1.setImageResource(R.drawable.leilei);
        //imageView2.setImageResource(R.drawable.yuyu);
        TextView textView = (TextView) view.findViewById(R.id.jiankangjianyi);

        return view;
    }

    class TodayStepCounterCall implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_STEP_WHAT: {
                    //每隔500毫秒获取一次计步数据刷新UI
                    if (null != iSportStepInterface) {
                        int step = 0;
                        try {
                            step = iSportStepInterface.getCurrentTimeSportStep();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        if (mStepSum != step) {
                            mStepSum = step;
                            updateStepCount();
                        }
                    }
                    mDelayHandler.sendEmptyMessageDelayed(REFRESH_STEP_WHAT, TIME_INTERVAL_REFRESH);

                    break;
                }
            }
            return false;
        }
    }

    private void updateStepCount() {
        // Log.e(TAG,"updateStepCount : " + mStepSum);
        TextView stepTextView = (TextView) view.findViewById(R.id.bushu);
        stepTextView.setText(mStepSum + "步");

    }

    private void bushutongjichushi() {

        lineChart = (LineChartView) view.findViewById(R.id.line_chart1);
        lineChart.setZoomEnabled(false);
        getAxisLables();//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();//初始化
    }



    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(Color.BLACK).setCubic(false);  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        line.setCubic(true);//曲线是否平滑
        line.setFilled(true);//是否填充曲线的面积
        //line.setHasLabels(true);//曲线的数据坐标是否加上备注
        line.setColor(ChartUtils.pickColor());

        line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示
        lines.add(line);
        line.setStrokeWidth(2);
        line.setPointRadius(3);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);//X轴上字体是否倾斜
        axisX.setTextColor(0xFF888888);  //设置字体颜色
        //axisX.setName("最近一周步数统计");  //表格名称
        axisX.setTextSize(7);//设置字体大小
        axisX.setMaxLabelChars(7);  //最多几个X轴坐标

        axisX.setValues(mAxisValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
//      data.setAxisXTop(axisX);  //x 轴在顶部

        Axis axisY = new Axis();  //Y轴
        axisY.setMaxLabelChars(7); //默认是3，只能看最后三个数字
        axisY.setTextSize(7);//设置字体大小
        axisY.setTextColor(0xFF888888);
        data.setAxisYLeft(axisY);  //Y轴设置在左边
//      data.setAxisYRight(axisY);  //y轴设置在右边
        axisX.setHasLines(true);// 是否显示X轴网格线
        axisY.setHasLines(true);// 是否显示Y轴网格线
        //设置行为属性，支持缩放、滑动以及平移
        //lineChart.setInteractive(true);
       // lineChart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        //lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
    }


    /**
     * X 轴的显示
     */
    private void getAxisLables() {
        mAxisValues = new ArrayList<AxisValue>();
        for (int i = 0; i < weeks.length; i++) {
            mAxisValues.add(new AxisValue(i).setLabel(weeks[i]));
        }
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints() {
        mPointValues = new ArrayList<PointValue>();
        for (int i = 0; i < bushuarray.length; i++) {
            mPointValues.add(new PointValue(i, bushuarray[i]));
        }
    }
//卡路里统计功能实现
    private void kalulitongji(){
        chart= (ColumnChartView) view.findViewById(R.id.chart);


        chart.setZoomEnabled(false);//禁止手势缩放
        setFirstChart();
    }
    //卡路里数据填充
    private void initData(final int n) {
        if(index==7)  {
            index++;
            for(int i=0;i<7;i++){
                bushuarray[i]=userDataBean[6-i].getData().getWalk();
                list.add((int)userDataBean[6-i].getData().getCalorie());
            }
            bushutongjichushi();//步数统计折线图
            kalulitongji();
            if(n==1)refreshableView.finishRefreshing();
            return;
        }
        if(index>7) {
            index++;
            return;
        }
        Http.getUserData(UserData.getName(), format.format(date), new Http.OnGetUserData() {
            @Override
            public void OnGetUserData(String s) {
                Gson gson = new Gson();
                userDataBean[index] = gson.fromJson(s, UserDataBean.class);
                if(!userDataBean[index].isSuccess()) {
                    //Toast.makeText(getContext(),"获取用户信息失败！code: "+index, Toast.LENGTH_SHORT).show();
                    Http.uploadUserData(UserData.getName(), format.format(date), 0, 0f, 0f, 0f, 0f, "", "", new Http.OnUploadUserData() {
                        @Override
                        public void OnUploadUserData(String s) {
                            Gson gson = new Gson();
                            BackBean back = gson.fromJson(s, BackBean.class);
                            if(back.isSuccess()) Toast.makeText(getContext(),"上传失败！", Toast.LENGTH_SHORT).show();

                        }
                    });
                    userDataBean[index] = new UserDataBean();
                    userDataBean[index].setData(new UserDataBean.DataBean());
                    userDataBean[index].getData().setWalk(0);
                    userDataBean[index].getData().setCalorie(0f);
                } else userDataBean[index] = gson.fromJson(s, UserDataBean.class);
                index++;
                date = new Date(date.getTime() - 86400000L);
                initData(n);
            }
        });

    }




    private void setFirstChart() {
        // 使用的 7列，每列1个subcolumn。
        int numSubcolumns = 1;
        int numColumns = 7;
        //定义一个圆柱对象集合
        List<Column> columns = new ArrayList<Column>();
        //子列数据集合
        List<SubcolumnValue> values;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        //遍历列数numColumns
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            //遍历每一列的每一个子列
            for (int j = 0; j < numSubcolumns; ++j) {
                //为每一柱图添加颜色和数值

                values.add(new SubcolumnValue(list.get(i), ChartUtils.pickColor()));
            }
            //创建Column对象
            Column column = new Column(values);
            //这一步是能让圆柱标注数据显示带小数的重要一步 让我找了好久问题
            //作者回答https://github.com/lecho/hellocharts-android/issues/185
            //ColumnChartValueFormatter chartValueFormatter = new SimpleColumnChartValueFormatter(2);
            //column.setFormatter(chartValueFormatter);
            //是否有数据标注
            column.setHasLabels(true);
            //是否是点击圆柱才显示数据标注
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
            //给x轴坐标设置描述
            axisValues.add(new AxisValue(i).setLabel(week[i]));
        }
        //创建一个带有之前圆柱对象column集合的ColumnChartData
        data= new ColumnChartData(columns);

        //定义x轴y轴相应参数
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        //axisY.setName("出场率(%)");//轴名称
        axisY.hasLines();//是否显示网格线
        axisY.setTextColor(0xFF888888);//颜色

        axisX.hasLines();
        axisX.setTextColor(0xFF888888);
        axisX.setValues(axisValues);
        //把X轴Y轴数据设置到ColumnChartData 对象中
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        //给表填充数据，显示出来
        chart.setColumnChartData(data);
    }

    //获取天气
    private void getWeatherDatafromNet(String cityCode)
    {
        final String address = "https://www.sojson.com/open/api/weather/xml.shtml?city="+cityCode;
        Log.d("Address:",address);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL(address);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(8000);
                    urlConnection.setReadTimeout(8000);
                    InputStream in = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer sb = new StringBuffer();
                    String str;
                    while((str=reader.readLine())!=null)
                    {
                        sb.append(str);
                        Log.d("date from url",str);
                    }
                    String response = sb.toString();
                    //Log.d("response",response);
                    todayWeather=parseXML(response);
                    if (todayWeather!=null){
                        Message message=new Message();
                        message.what=1;
                        message.obj=todayWeather;
                        mHandler.sendMessage(message);
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private TodayWeather parseXML(String xmlData)
    {
        TodayWeather todayWeather = null;
        int typeCount = 0;
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));

            int eventType = xmlPullParser.getEventType();
            Log.d("MWeater","start parse xml");

            while(eventType!=xmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    //文档开始位置
                    case XmlPullParser.START_DOCUMENT:
                        Log.d("parse","start doc");
                        break;
                    //标签元素开始位置
                    case XmlPullParser.START_TAG:
                        if (xmlPullParser.getName().equals("resp")){
                            todayWeather=new TodayWeather();
                        }
                        else if(xmlPullParser.getName().equals("wendu"))
                        {
                            eventType=xmlPullParser.next();
                            Log.d("wendu",xmlPullParser.getText()+"℃");
                            todayWeather.setWendu(xmlPullParser.getText());
                        }else if(xmlPullParser.getName().equals("type") && typeCount==0 )
                        {
                            eventType=xmlPullParser.next();
                            Log.d("type",xmlPullParser.getText());
                            todayWeather.setType(xmlPullParser.getText());
                            typeCount++;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType=xmlPullParser.next();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return todayWeather;
    }

}