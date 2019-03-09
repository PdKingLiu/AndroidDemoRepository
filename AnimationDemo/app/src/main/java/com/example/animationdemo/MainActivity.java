package com.example.animationdemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChart = findViewById(R.id.pie_chart);
        initChart();

    }

    private void initChart() {
        List<PieEntry> pieCharts = new ArrayList<>();
        pieCharts.add(new PieEntry(10, "缺勤"));
        pieCharts.add(new PieEntry(20, "请假"));
        pieCharts.add(new PieEntry(30, "迟到"));
        pieCharts.add(new PieEntry(40, "正常"));
        PieDataSet pieDataSet = new PieDataSet(pieCharts, "");
        PieData data = new PieData(pieDataSet);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(16f);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter());
        List<Integer> integerList = new ArrayList<>();
        integerList.add(getResources().getColor(R.color.pie_blue));
        integerList.add(getResources().getColor(R.color.pie_green));
        integerList.add(getResources().getColor(R.color.pie_orange));
        integerList.add(getResources().getColor(R.color.pie_yellow));
        pieDataSet.setHighlightEnabled(true);
        pieDataSet.setColors(integerList);
        pieDataSet.setSliceSpace(5);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh
        pieChart.getLegend();
        pieChart.setRotationEnabled(false);
        pieChart.setHighlightPerTapEnabled(true);
        pieChart.animateX(3000, Easing.EaseOutBounce);
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(20f);
        l.setYOffset(20);
        // 输入标签样式
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(12f);
    }

    public void click(View view) {
        initChart();
       /* new IntentIntegrator(this)
                // 自定义Activity，重点是这行----------------------------
                .setCaptureActivity(ScanQRActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)// 扫码的类型,
                可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan();// 初始化扫码*/
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
