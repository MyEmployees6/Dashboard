package com.example.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class linepage extends AppCompatActivity {

    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@info706.cwwvo42siq12.ap-southeast-2.rds.amazonaws.com:1521:ORCL";
    private static final String DEFAULT_USERNAME = "19451218";
    private static final String DEFAULT_PASSWORD = "eirin1998";

    ArrayList<Float> supplierdata = new ArrayList<>();
    ArrayList<String> suppliername = new ArrayList<>();

    private Connection connection;
    private static final String TAG="MyDBTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linepage);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try{
            Log.i(TAG,"Starting app -create Connection");
            this.connection = createConnection();
            Log.i(TAG,"Connected");
            Statement stmt=connection.createStatement();
            Log.i(TAG,"Running query");
            ResultSet rs=stmt.executeQuery("select * from products where rownum<=5");
            Log.i(TAG,"query complete");

            //Specify columns index from Database
            while(rs.next())
            {
                suppliername.add(rs.getString(2));
                supplierdata.add(rs.getFloat(6));

                Log.d(TAG, ">>>"+rs.getInt(1)+ ", >>> :"+rs.getString(2));
            }

            connection.close();
        }
        catch(Exception e){

            Log.i(TAG,"ERROR>>>"+e.getMessage());
            StringWriter sw=new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Log.i(TAG,sw.toString());
        }
        setUpLineChart();
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
    private void setUpLineChart() {
        LineChart lChart = (LineChart)findViewById(R.id.lineChart);

        lChart.getDescription().setEnabled(true);
        Description d = new Description();
        d.setText("Products");
        d.setTextSize(15);
        lChart.setDescription(d);

        // Data to list
        List<Entry> entries = new ArrayList<>();
        for(int i = 0; i<supplierdata.size(); i++){
            entries.add(new Entry(i, supplierdata.get(i)));
        }

        // X axis
        final ArrayList<String> xAxisLabels = new ArrayList<>();
        for (int j=0; j <suppliername.size(); j++){
            xAxisLabels.add(suppliername.get(j));
        }


        XAxis xAxis = lChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xAxisLabels.get((int) value);
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setGranularity(1);

        // Data Set
        LineDataSet lds = new LineDataSet(entries, "Products per Month");
        lds.setColors(ColorTemplate.JOYFUL_COLORS);
        lds.setLineWidth(5);
        lds.setCircleColor(Color.RED);
        lds.setCircleRadius(5);
        lds.setHighLightColor(Color.BLUE);
        lds.setValueTextSize(14);
        lds.setColors(Color.YELLOW);
        lds.setDrawFilled(true);
        lds.setFillColor(Color.GREEN);

        LineData data = new LineData(lds);

        lChart.setData(data);
        lChart.animateY(3000);
        lChart.invalidate();

    }
}
