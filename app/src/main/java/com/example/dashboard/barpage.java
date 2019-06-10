package com.example.dashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
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

import android.os.StrictMode;
import android.util.Log;

public class barpage extends AppCompatActivity {

    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@info706.cwwvo42siq12.ap-southeast-2.rds.amazonaws.com:1521:ORCL";
    private static final String DEFAULT_USERNAME = "19451218";
    private static final String DEFAULT_PASSWORD = "eirin1998";

    private Connection connection;
    private static final String TAG = "MyDBTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barpage);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setUpBarChart();
    }

    //Connection
    public static Connection createConnection (String driver, String url, String
            username, String password) throws ClassNotFoundException, SQLException
    {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    public static Connection createConnection () throws ClassNotFoundException, SQLException {
        return createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    //Make Chart
    private void setUpBarChart() {

        // Declare array lists to hold graph data
        ArrayList<Float> sup_data = new ArrayList<>();
        ArrayList<String> sup_name = new ArrayList<>();
        BarChart barChart = (BarChart) findViewById(R.id.bar_chart);

        //oracle connection
        try {
            Log.i(TAG, "Starting App - Create Connection");
            this.connection = createConnection();
            Log.i(TAG, "Connected...");

            Statement stmnt = connection.createStatement();
            Log.i(TAG, "Running Query...");
            ResultSet rs = stmnt.executeQuery("SELECT * FROM country_view where rownum<=5");

            Log.i(TAG, "Query complete, printing results...");

            int i = 0; // row counter

            //Specify columns index from Database
            while (rs.next()) {
                sup_name.add(rs.getString(1));
                sup_data.add((float) rs.getDouble(2));
                Log.i(TAG, ">>>> " + rs.getString(1) + ", >>>> " + rs.getDouble(2));
                i++;

            }
            Log.i(TAG, "Complete...");
            Log.i(TAG, "Close Connection...");

            connection.close(); // always close connection

            barChart.setDrawBarShadow(false);
            barChart.setDrawValueAboveBar(true);
            barChart.setMaxVisibleValueCount(50);
            barChart.setPinchZoom(false);
            barChart.setDrawGridBackground(true);
            barChart.getDescription().setEnabled(true);
            Description d = new Description();
            d.setText("Top 5 Country Suppliers");
            d.setTextSize(15);
            barChart.setDescription(d);

            //Chart Data
            List<BarEntry> barEntries = new ArrayList<>();
            for (int j = 0; j < sup_data.size(); j++) {
                barEntries.add(new BarEntry(j, sup_data.get(j)));
            }

            // X Axis
            final ArrayList<String> xAxisLabels = new ArrayList<>();
            for (int j = 0; j < sup_name.size(); j++) {
                xAxisLabels.add(sup_name.get(j));
            }
            XAxis xAxis = barChart.getXAxis();
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return xAxisLabels.get((int) value);
                }
            });
            xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
            xAxis.setGranularity(1);

            //Data sets
            BarDataSet bds = new BarDataSet(barEntries, "Suppliers Country");
            bds.setColors(ColorTemplate.PASTEL_COLORS);
            BarData bData = new BarData(bds);
            bData.setBarWidth(0.7f);
            barChart.setData(bData);
            barChart.animateY(2000);
            barChart.invalidate();

            //Error Handler
        } catch (Exception e) {
            Log.i(TAG, "Error>>>" + e.getMessage());
            StringWriter sw = new StringWriter(); // Prints stack overflow message
            e.printStackTrace(new PrintWriter(sw));
            Log.i(TAG, sw.toString());
        }
    }

}