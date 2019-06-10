package com.example.dashboard;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class horizontalpage extends Activity {

    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@info706.cwwvo42siq12.ap-southeast-2.rds.amazonaws.com:1521:ORCL";
    private static final String DEFAULT_USERNAME = "19451218";
    private static final String DEFAULT_PASSWORD = "eirin1998";

    ArrayList<Float> supplierData = new ArrayList<>();
    ArrayList<String> supplierName = new ArrayList<>();

    private Connection connection;
    private static final String TAG="MyDBTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontalpage);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // Connection to Oracle
        try{
            Log.i(TAG,"Starting app -create Connection");
            this.connection = createConnection();
            Log.i(TAG,"Connected");
            Statement stmt=connection.createStatement();
            Log.i(TAG,"Running query");
            ResultSet rs=stmt.executeQuery("select * from products where rownum<=5");
            Log.i(TAG,"query complete");

            int i = 0;

            //Specify columns index from Database
            while(rs.next())
            {
                supplierName.add(rs.getString(2));
                supplierData.add((float)rs.getDouble(4));
                //rs.getArray(6);
                Log.i(TAG, ">>>> " + rs.getString(2) + ", >>>> " + rs.getDouble(4));
                i++;
            }
            Log.i(TAG, ">>>> Done");
            connection.close();
        }
        catch(Exception e){

            Log.i(TAG,"ERROR>>>"+e.getMessage());
            StringWriter sw=new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Log.i(TAG,sw.toString());
        }
        setUpHBarChart();
    }

    //Connection
    public static Connection createConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {

        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        return createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    // Graph creation
    public void setUpHBarChart(){

        HorizontalBarChart hChart = (HorizontalBarChart) findViewById(R.id.chart);

        hChart.setDrawBarShadow(false);
        hChart.setDrawValueAboveBar(true);
        hChart.setMaxVisibleValueCount(50);
        hChart.setPinchZoom(false);
        hChart.setDrawGridBackground(true);
        hChart.getDescription().setEnabled(true);
        Description d = new Description();
        d.setText("GRAPH OF SALES PER MONTH");
        d.setTextSize(15);
        hChart.setDescription(d);

        // Horizontal chart
        List<BarEntry> bEntries = new ArrayList<>();
        for (int j=0; j< supplierData.size(); j++){
            bEntries.add(new BarEntry(j, supplierData.get(j)));
        }
        // X axis
        final ArrayList<String> xAxisLabels = new ArrayList<>();
        for (int j=0; j<supplierName.size(); j++){
            xAxisLabels.add(supplierName.get(j));
        }
        XAxis xAxis = hChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xAxisLabels.get((int) value);
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);

        // Dataset
        BarDataSet bds = new BarDataSet(bEntries,"Product Prices");
        bds.setColors(ColorTemplate.VORDIPLOM_COLORS);
        BarData data = new BarData(bds);
        BarData bData = new BarData(bds);
        bData.setBarWidth(0.7f);
        hChart.setData(bData);
        hChart.animateY(2000);
        hChart.invalidate();

    }
}