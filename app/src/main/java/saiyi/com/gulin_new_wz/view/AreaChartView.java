/**
 * Copyright 2014  XCL-Charts
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @Project XCL-Charts
 * @Description Android图表基类库
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @Copyright Copyright (c) 2014 XCL-Charts (www.xclcharts.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 2.4
 */
package saiyi.com.gulin_new_wz.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import org.xclcharts.chart.AreaChart;
import org.xclcharts.chart.AreaData;
import org.xclcharts.chart.CustomLineData;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XEnum;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;


/**
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @ClassName AreaChart03View
 * @Description 面积图例子
 * 深睡浅水图表
 */

public class AreaChartView extends BaseCharView {

    private String TAG = "AreaChart03View";

    //前面显示的
    private AreaChart chart = new AreaChart();
    //后面显示的，如xy线等
    private AreaChart chartBg = new AreaChart();


    //X轴数据
    private LinkedList<String> xLabels = new LinkedList<String>();

    //线的集合
    private LinkedList<AreaData> mDataset = new LinkedList<AreaData>();
    //额外的线
    private List<CustomLineData> mCustomLineDataset = new LinkedList<CustomLineData>();

    //格式
    DecimalFormat df = new DecimalFormat("#0");
    private int mAreaStart;
    private int mAreaEnd;
    private int mLineColor;
    private int mMaxValue;
    private int mLeastValue;
    private String mUnit;
    private String mMiddleStr;

    public AreaChartView(Context context) {
        super(context);
    }

    public AreaChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AreaChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setInitialization(int areaStart,int lineColor,int maxValue,int leastValue,String unit,String middleStr){
        mAreaStart = areaStart;
        mAreaEnd = areaStart;
        mLineColor = lineColor;
        mDataset.clear();
        mMaxValue = maxValue;
        mLeastValue = leastValue;
        mUnit = unit;
        mMiddleStr = middleStr;
        initView();
    }


    private void initView() {
        //
        chartDataSet();

        //数据，背景，x轴，Y轴网格等
        chartBgRender();

        //
        chartRender();

        //綁定手势滑动事件
        //this.bindTouch(this,chart);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //图所占范围大小
        chartBg.setChartRange(w, h);
        chart.setChartRange(w, h);

    }

    private void chartRender() {
        try {
            //设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....
            int[] ltrb = getBarLnDefaultSpadding();
            chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);

            //标题，子标题
            String title = "本日用水统计";
            String sonTitle = "";
//            chart.setTitle(title);
//            chart.addSubtitle("(XCL-Charts Demo)");
//            缩减精度
            chart.disableHighPrecision();
//            禁用手势滑动
            chart.disablePanMode();
            //圆滑，XEnum.CrurveLineStyle.BEZIERCURVE，BEELINE线性
            chart.setCrurveLineStyle(XEnum.CrurveLineStyle.BEZIERCURVE);
            //透明度0-255
            chart.getAreaFillPaint().setAlpha(200);
            chart.setAreaAlpha(200);

            //网格隐藏
            chart.getPlotGrid().hideHorizontalLines();
            chart.getPlotGrid().hideVerticalLines();

            //横向显示柱形,如想看横向显示效果，可打开这两行的注释即可

            //X轴数据
            chart.setCategories(xLabels);
            //隐藏X轴的东西
            chart.getCategoryAxis().hide();
            chart.getCategoryAxis().hideAxisLine();
            chart.getCategoryAxis().hideTickMarks();

            //设置Y轴数据轴
            chart.setDataSource(mDataset);
            //数据轴最大值
            chart.getDataAxis().setAxisMax(100);
            //数据轴刻度间隔
            chart.getDataAxis().setAxisSteps(10);
            //Y轴double数据隐藏
            chart.getDataAxis().hide();
            //把轴线和刻度线给隐藏起来
            chart.getDataAxis().hideAxisLine();
            chart.getDataAxis().hideTickMarks();


            //设定交叉点标签显示格式
            chart.setItemLabelFormatter(new IFormatterDoubleCallBack() {
                @Override
                public String doubleFormatter(Double value) {
                    DecimalFormat df = new DecimalFormat("#0");
                    String label = df.format(value).toString();
                    return label;
                }
            });


            //背景渐变，不是填充渐变
            //chart.getBackgroundPaint().setAlpha(254);
//            chart.getPlotArea().setBackgroundColor(true, Color.rgb(163, 69, 213));
//            chart.getPlotArea().setApplayGradient(true);
//            chart.getPlotArea().setEndColor(Color.WHITE);


            //额外的线
//            CustomLineData line1 = new CustomLineData("30", mStdValue, Color.RED, 7);
//            line1.getLineLabelPaint().setColor(Color.RED);
//            line1.setLabelHorizontalPostion(Align.LEFT);
//            line1.hideLine();
//            line1.setLineStyle(XEnum.LineStyle.DASH);
//            line1.setLabelOffset(chart.getDataAxis().getTickLabelMargin());
//            mCustomLineDataset.add(line1);
//
//            CustomLineData line2 = new CustomLineData("20", 20d, Color.RED, 7);
//            line2.setLabelHorizontalPostion(Align.LEFT);
//            line2.hideLine();
//            line2.setLabelOffset(chart.getDataAxis().getTickLabelMargin());
//            line2.getLineLabelPaint().setColor(Color.RED);
//            mCustomLineDataset.add(line2);
//
//            CustomLineData line3 = new CustomLineData("10", 10d, Color.RED, 7);
//            line3.setLabelHorizontalPostion(Align.LEFT);
//            line3.hideLine();
//            line3.getLineLabelPaint().setColor(Color.RED);
//            line3.setLabelOffset(chart.getDataAxis().getTickLabelMargin());
//            mCustomLineDataset.add(line3);
//
//            chart.setCustomLines(mCustomLineDataset);
            //显示线的key名字
            chart.getPlotLegend().hide();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        }
    }

    //将标签与对应的数据集分别绑定
    //标签对应的数据集
    private void chartDataSet() {
        List<Double> dataSeries1 = new LinkedList<Double>();

        //设置每条线各自的显示属性
        //key,数据集,线颜色,区域颜色
//        int linecolor = 0xffffffff;
//        int linecolor = Color.parseColor(mLineColor);       //圆弧线的颜色
        AreaData line1 = new AreaData("", dataSeries1, mLineColor, Color.YELLOW);
        //不显示点
        line1.setDotStyle(XEnum.DotStyle.HIDE);
        //填充数据渐变颜色
        line1.setApplayGradient(true);
        line1.setAreaBeginColor(mAreaStart);
        line1.setAreaEndColor(mAreaEnd);
        line1.setGradientDirection(XEnum.Direction.VERTICAL);
        //设置线的大小
        line1.getLinePaint().setStrokeWidth(3);
        mDataset.add(line1);
    }


    /**
     * X，Y轴和他们的字体，后面的横竖线
     */
    private void chartBgRender() {
        try {
            //设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....
            int[] ltrb = getBarLnDefaultSpadding();
            chartBg.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);
            //忽略Java的float计算误差，能提高性能
            chartBg.disableHighPrecision();
            //禁掉平移，这样线上的标注框在最左和最右时才能显示全
            chartBg.disablePanMode();
            //
            chartBg.setCrurveLineStyle(XEnum.CrurveLineStyle.BEZIERCURVE);
            //显示横向线
            chartBg.getPlotGrid().showHorizontalLines();
            //显示竖向线
            chartBg.getPlotGrid().showVerticalLines();
            //连续线
            chartBg.getPlotGrid().setHorizontalLineStyle(XEnum.LineStyle.SOLID);  // 可以设置线的风格
            chartBg.getPlotGrid().setVerticalLineStyle(XEnum.LineStyle.SOLID);
            chartBg.getPlotGrid().getVerticalLinePaint().setColor(Color.GRAY);
            chartBg.getPlotGrid().getHorizontalLinePaint().setColor(Color.GRAY);

            float vStrokeWidth = 0.2f;
            float hStrokeWidth = 0.2f;
            chartBg.getPlotGrid().getVerticalLinePaint().setStrokeWidth(vStrokeWidth);
            chartBg.getPlotGrid().getHorizontalLinePaint().setStrokeWidth(hStrokeWidth);

            //设置X数据
            chartBg.setCategories(xLabels);
            chartBg.getCategoryAxis().getTickLabelPaint().setColor(Color.GRAY);
            //设置X轴浅白色
            int xlinecolor = 0x55fefefe;
            chartBg.getCategoryAxis().getAxisPaint().setColor(xlinecolor);
            chartBg.getCategoryAxis().getAxisPaint().setStrokeWidth(3);
            chartBg.getCategoryAxis().hideAxisLine();
            chartBg.getCategoryAxis().hideTickMarks();
            //设置Y数据
            //Y轴字体颜色
            chartBg.setDataSource(mDataset);
//            chartBg.getDataAxis().getTickLabelPaint().setColor(Color.BLACK);
            //Y轴，透明
//            chartBg.getDataAxis().getAxisPaint().setColor(Color.BLACK);
//            chartBg.getDataAxis().getAxisPaint().setStrokeWidth(3);
            chartBg.getDataAxis().hideAxisLine();//隐藏线
            chartBg.getDataAxis().hideTickMarks();

            //定义数据Y轴标签显示格式,
            chartBg.getDataAxis().setLabelFormatter(new IFormatterTextCallBack() {
                @Override
                public String textFormatter(String value) {
                    String label = "";
                    Double tmp = Double.parseDouble(value);
                    if (tmp == 100) {
                        label = ""+mMaxValue+mUnit;
                    } else if (tmp == 20) {
                        label = ""+mLeastValue+mUnit;
                    }else if (tmp == 60){
                        label = mMiddleStr;
                    }
                    return label;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        }
    }


    /**
     * 刷新表格数据
     *
     * @param X    X轴数据
     * @param data Y轴数据
     * @param max
     * @param min
     * @param step 步长
     */
    public void refreshChart(List<String> X, List<Double> data, int max, int min, int step) {
        //X轴,每一个传进去的X数据对应一个数据格式
        xLabels.clear();
        xLabels.addAll(X);
        setXLabelFormatter();

        mDataset.get(0).getLinePoint().clear();
        mDataset.get(0).getLinePoint().addAll(data);
        //数据更新
        chart.getDataAxis().setAxisMax(max);
        chart.getDataAxis().setAxisMin(min);
        chart.setDataSource(mDataset);

        //
        chartBg.getDataAxis().setAxisMax(max);
        chartBg.getDataAxis().setAxisMin(min);
        chartBg.getDataAxis().setAxisSteps(step);
        chartBg.setDataSource(mDataset);

        this.invalidate();
    }

    @Override
    public void render(Canvas canvas) {
        try {
            chartBg.render(canvas);
            chart.render(canvas);

            //画顶上的标题
//            Paint paint = new Paint();
//            paint.setAntiAlias(true);
//            paint.setColor(Color.rgb(11, 35, 122));
//            paint.setTextSize(22);
//            canvas.drawText("2015/10/26 晚上12点  周日  价位:xxxx",
//                    chart.getPlotArea().getLeft(), chart.getPlotArea().getTop() - 10.f, paint);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


    //画X轴标题
    void drawXtitle(Canvas canvas, String txt) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        paint.setTextSize(22);
        float txtleght = paint.measureText(txt);
        canvas.drawText(txt, chart.getRight() / 2 - txtleght / 2, chart.getBottom() - 5, paint);


    }

    //画Y轴标题
    void drawYtitle(Canvas canvas, String txt) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        paint.setTextSize(22);
        canvas.drawText(txt, chart.getPlotArea().getLeft(), chart.getPlotArea().getTop() - 10, paint);
    }


    void setXLabelFormatter() {
        //X轴数据格式
        chartBg.getCategoryAxis().setLabelFormatter(new IFormatterTextCallBack() {
            @Override
            public String textFormatter(String value) {
                int i = Integer.parseInt(value);

                String label = df.format(i).toString();
                return label;
            }
        });
        //X轴数据格式
        chart.getCategoryAxis().setLabelFormatter(new IFormatterTextCallBack() {
            @Override
            public String textFormatter(String value) {
                int i = Integer.parseInt(value);

                String label = df.format(i).toString();
                return label;
            }
        });

    }

}

