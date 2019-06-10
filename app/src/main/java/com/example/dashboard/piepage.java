
package com.example.dashboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import android.os.StrictMode;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

public class piepage extends AppCompatActivity {
    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@info706.cwwvo42siq12.ap-southeast-2.rds.amazonaws.com:1521:ORCL";
    private static final String DEFAULT_USERNAME = "19451218";
    private static final String DEFAULT_PASSWORD = "eirin1998";

    // Declare array lists to hold graph data
    ArrayList<Float> supplydata = new ArrayList<>();
    ArrayList<String> supplyname = new ArrayList<>();

    private Connection connection;
    private static final String TAG="MyDBTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piepage);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //oracle connection
        try {
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
                supplyname.add(rs.getString(2));
                supplydata.add(rs.getFloat(6));
                //rs.getArray(6);
                Log.d(TAG, ">>>"+rs.getInt(1)+ ", >>> :"+rs.getString(2));
            }
            connection.close(); //always close connection
        }

        //Error handling code
        catch(Exception e){
            Log.i(TAG,"ERROR>>>"+e.getMessage());
            StringWriter sw = new StringWriter(); // Prints the stack overflow message
            e.printStackTrace(new PrintWriter(sw));
            Log.i(TAG,sw.toString());
        }
        setUpPieChart();
    }

    //Connection
    public static Connection createConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {

        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        return createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    //Set up Chart
    private void setUpPieChart(){
        List<PieEntry> pieEntries=new ArrayList<>();

        for (int i=0;i<supplydata.size();i++){
            pieEntries.add(new PieEntry(supplydata.get(i),supplyname.get(i)));
            PieDataSet ds=new PieDataSet(pieEntries,"Products");
            ds.setColors(ColorTemplate.COLORFUL_COLORS);
            PieData data=new PieData(ds);
            PieChart chart =(PieChart) findViewById(R.id.pieChart);
            chart.setData(data);
            chart.animateY(1000);
            chart.invalidate();
        }

    }
}
