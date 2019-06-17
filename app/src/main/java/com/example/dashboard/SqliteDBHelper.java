package com.example.wintecdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;    //update structure of the database
    public static final String DATABASE_NAME = "wintec.db";  //We save it into file
    public static final String TABLE_COURSES = "COURSES";//name of the table
    public static final String TABLE_STUDENT = "STUDENT";//name of the table


    public String[] arrcourses = {"Network Engineering", "Database Architecture", "Web Development", "Software Engineering"};

    public String[][] arrnetworksem1 = {{"COMP501", "Information Technology Operations", "To enable students to apply fundamental IT technical support concepts and practice, and configure and administer systems and applications to meet organisational requirements", "15"},
            {"COMP502", "Fundamentals of Programming and Problem Solving", "To enable students to apply the principles of software development to create simple working applications and use problem-solving and decision-making techniques to provide innovative and timely Information Technology outcomes", "15"},
            {"INFO501", "Professional Practice", "To enable students to apply professional, legal, and ethical principles and practices in a socially responsible manner as an emerging IT professional, and apply communication, personal and interpersonal skills to enhance effectiveness in an IT role", "15"},
            {"INFO502", "Business Systems Analysis and Design", "The student will be able to apply the fundamentals of information systems concepts and practice to support and enhance organisational processes and systems; and apply the fundamentals of interaction design concepts and practice to enhance interface design", "15"}};

    public String[][] arrnetworksem2 = {{"COMP503", "Introduction to Networks", "To enable students to apply a broad operational knowledge of networking, and associated services and technologies to meet typical organisational requirements", "15"},
            {"COMP504", "Operating Systems and System Supports", "To enable students to select, install, and configure IT hardware and systems software and use graphical (GUI) and command line interfaces (CLI) to meet organisational requirements", "15"},
            {"INFO503", "Database Principles", "To enable students to apply a broad operational knowledge of database administration to meet typical organisational data storage and retrieval requirements, and apply conceptual knowledge of cloud services and virtualisation", "15"},
            {"INFO504", "Technical Support", "To enable students to demonstrate an operational knowledge and understanding of IT service management, identify common issues related to IT security, and troubleshoot and resolve a range of common system problems", "15"}};

    public String[][] arrnetworksem3 = {{"COMP601", "Object Oriented Programming", "To enable students to gain the skills to develop software applications using Object Oriented Programming techniques. Students will enrich their programming and problem solving skills by applying classes, objects, constructors, methods and properties, inheritance and polymorphism to develop programming applications", "15"},
            {"INFO601", "Database Modelling and SQL", "To enable students to apply an indepth understanding of database modelling, database design and SQL concepts", "15"},
            {"MATH601", "Mathematics for Information Technology", "To enable students to gain mathematical skills and acquire in-depth understanding of mathematics as applied to Information Technology", "15"},
            {"COMP602", "Web Development", "To enable students to gain the in depth knowledge and skills required to be able to write programs in web programming languages that solve various web programming tasks", "15"}};

    public String[][] arrnetworksem4 = {{"INFO602", "Business, Interpersonal Communications and Technical Writing", "To enable students to develop an understanding of the principles and processes involved in effective interpersonal communication and technical writing used in managing client relationships", "15"},
            {"COMP615", "Data Centre Infrastructure", "To enable students to apply a broad operational knowledge of data centres and associated technologies to meet typical organisational requirements", "15"},
            {"INFO603", "Systems Administration", "To enable students to gain the skills and knowledge required to effectively plan, install and administer a Microsoft Windows Server", "15"},
            {"COMP604", "Routing and Switching Essentials", "To enable students to configure, troubleshoot and understand the operation of Routers, Routing Protocols, Switches and VLANs in a networking environment, and complete the Cisco Certified Network Associate 2 (CCNA2) Curriculum", "15"}};

    public String[][] arrnetworksem5 = {{"COMP701", "Advanced Networking", "To enable students to investigate and configure advanced system administration tools, advanced network virtualisation and wireless networking technologies. Students will also research emerging networking technologies", "15"},
            {"INFO702", "Cyber-Security", "To enable students to investigate computer system attacks and vulnerabilities in relation to operating systems (OS), applications, networking and websites, and investigate the security techniques for protecting a computer system from such attacks", "15"},
            {"COMP702", "Scaling Networks", "To enable students to gain an understanding of the architecture, components, and operations of routers and switches in larger and more complex networks", "15"},
            {"COMP704", "Network Security", "To enable students to gain core security knowledge and skills needed for installation, troubleshooting, and monitoring of networks to maintain the integrity, confidentiality, and availability of data and devices", "15"}};

    public String[][] arrnetworksem6 = {{"BIZM701", "Business Essentials for IT Professionals", "To enable students to develop an understanding of the common principles of business practice whilst focussing on the theoretical and practical concepts of accounting, marketing and management in order to understand the business context within which Information Technology solutions are developed", "15"},
            {"COMP705", "Connecting Networks", "To enable students to gain an understanding of Wide Area Network (WAN) technologies and network services required by converged applications in a complex network", "15"},
            {"COMP714", "Network Engineering Project", "To enable students to develop a Computer networking solution from a set of requirements documents. This module is the Network Engineering Capstone Project", "30"}};

    public String[][] arrDBsem4 = {{"INFO602", "Business, Interpersonal Communications and Technical Writing", "To enable students to develop an understanding of the principles and processes involved in effective interpersonal communication and technical writing used in managing client relationships", "15"},
            {"COMP605", "Data Structures and Algorithms", "To enable students to apply programming and analytical skills to implement and analyze common data structures for computer programs. Students will cover a wide range of computer programming algorithms", "15"},
            {"COMP606", "Web Programming", "To enable students to gain the in depth knowledge and skills required to be able to write programs in web programming languages that solve various web programming tasks", "15"},
            {"INFO604", "Database Systems", "To enable students to understand and discuss database systems, concepts, modelling, design and administration, and to apply theoretical and practical administrative tasks in a database administrator's role", "15"}};

    public String[][] arrDBsem5 = {{"INFO704", "Data Warehousing and Business Intelligence", "To enable students to examine the main components of data warehousing and apply it to business intelligence applications, enabling them to provide solutions which incorporate extracting data from different sources, storing data in a data warehouse and developing applications for business decision-making", "15"},
            {"INFO706", "Database Front-End Applications", "To enable students to understand and apply various front end applications and their interfaces with supporting databases", "15"},
            {"COMP709", "Mobile Applications Development", "To enable students to design, develop and implement mobile applications on a given platform", "15"},
            {"INFO707", "Cloud Server Databases", "To enable students to gain an in-depth knowledge of cloud server database concepts, fundamentals and essentials. They will apply practical skills to install, setup, configure, manage and maintain a cloud server database and deploy cloud database services to support database applications", "15"}};

    public String[][] arrDBsem6 = {{"BIZM701", "Business Essentials for IT Professionals", "To enable students to develop an understanding of the common principles of business practice whilst focussing on the theoretical and practical concepts of accounting, marketing and management in order to understand the business context within which Information Technology solutions are developed", "15"},
            {"INFO703", "Big Data and Analytics", "To enable students to gain the practical knowledge and skills required to store, manage and analyse large amounts of data, using appropriate algorithms", "15"},
            {"INFO712", "Database Architecture Project", "To enable students to further develop their knowledge of Database Architecture by analysing, designing and implementing a database solution. This module is the Database Architecture Capstone Project", "30"},};

    public String[][] arrWEBsem4 = {{"INFO602", "Business, Interpersonal Communications and Technical Writing", "To enable students to develop an understanding of the principles and processes involved in effective interpersonal communication and technical writing used in managing client relationships", "15"},
            {"COMP603", "The Web Environment", "To enable students to examine the working environment and applications that are used in the web industry", "15"},
            {"COMP606", "Web Programming", "To enable students to gain the in depth knowledge and skills required to be able to write programs in web programming languages that solve various web programming tasks", "15"},
            {"COMP607", "Visual Effects and Animation", "To enable students to develop the knowledge and skills required to design and develop effective graphics and animation, and to apply various visual effects for static graphics as well as create 3D models and produce 2D and 3D animation", "15"}};

    public String[][] arrWEBsem5 = {{"INFO709", "Human Computer Interaction", "To enable students to understand the supporting theories and principles of user interface design with respect to human computer interaction. They will investigate applications in human computer interaction and apply usability best practices and processes", "15"},
            {"INFO702", "Cyber-Security", "To enable students to investigate computer system attacks and vulnerabilities in relation to operating systems (OS), applications, networking and websites, and investigate the security techniques for protecting a computer system from such attacks", "15"},
            {"COMP709", "Mobile Applications Development", "To enable students to design, develop and implement mobile applications on a given platform", "15"},
            {"COMP710", "Web Application Development", "To enable students to apply practical knowledge of Model View Controller (MVC) frameworks to plan, design and implement web applications. The core focus will be on architecture design and implementation of a web application that will meet a set of functional requirements and user interface requirements, and address business models", "15"}};

    public String[][] arrWEBsem6 = {{"BIZM701", "Business Essentials for IT Professionals", "To enable students to develop an understanding of the common principles of business practice whilst focussing on the theoretical and practical concepts of accounting, marketing and management in order to understand the business context within which Information Technology solutions are developed", "15"},
            {"INFO708", "Data Visualisation", "To enable students to study and apply visual techniques that transform data into a format efficient for human perception, cognition, and communication, thus allowing for extraction of meaningful information and insight. Students will investigate data visualisation techniques, human visual systems and cognitive perception, and design, construct, and evaluate data visualisations", "15"},
            {"COMP713", "Web Application Project", "To enable students to further develop their knowledge of Web Applications by analysing, designing and implementing a web solution. This module is the Web Application Capstone Project", "30"}};

    public String[][] arrSoftwaresem4 = {{"INFO602", "Business, Interpersonal Communications and Technical Writing", "To enable students to develop an understanding of the principles and processes involved in effective interpersonal communication and technical writing used in managing client relationships", "15"},
            {"COMP603", "The Web Environment", "To enable students to examine the working environment and applications that are used in the web industry", "15"},
            {"COMP605", "Data Structures and Algorithms", "To enable students to apply programming and analytical skills to implement and analyze common data structures for computer programs. Students will cover a wide range of computer programming algorithms", "15"},
            {"MATH602", "Mathematics for Programming", "To enable students to obtain the mathematical skills to facilitate an in-depth understanding of advanced programming techniques. Students will be able to solve problems in recurrence functions, asymptotic functions, algorithmic puzzles, combinatorics and graph theory and advanced topics in probability and statistics", "15"}};

    public String[][] arrSoftwaresem5 = {{"INFO701", "Project Management", "To enable students to understand and apply the theory of project management with particular emphasis on Information Technology projects", "15"},
            {"BIZM701", "Business Essentials for IT Professionals", "To enable students to develop an understanding of the common principles of business practice whilst focussing on the theoretical and practical concepts of accounting, marketing and management in order to understand the business context within which Information Technology solutions are developed", "15"},
            {"INFO703", "Big Data and Analytics", "To enable students to gain the practical knowledge and skills required to store, manage and analyse large amounts of data, using appropriate algorithms", "15"},
            {"COMP706", "Game Development", "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", "15"}};

    public String[][] arrSoftwaresem6 = {{"INFO704", "Data Warehousing and Business Intelligence", "To enable students to examine the main components of data warehousing and apply it to business intelligence applications, enabling them to provide solutions which incorporate extracting data from different sources, storing data in a data warehouse and developing applications for business decision-making", "15"},
            {"COMP707", "Principles of Software Testing", "Students will gain comprehensive knowledge of software testing methodologies and software testing tools used in industry and apply fundamental aspects of software testing incorporating system requirements, quality assurance, testing processes, automation, testing types and testing levels. This forms the third part of the Software Engineering Capstone Project", "15"},
            {"COMP709", "Mobile Applications Development", "To enable students to design, develop and implement mobile applications on a given platform", "15"},
            {"COMP715", "Software Engineering Project", "Students will gain advanced software development skills. They will be able to provide an in depth analysis of prototyping, performance, correctness, software reusability, scalability and quality and maintenance and appropriate documentation. This module is the Software Engineering capstone project", "30"}};
    //NETWORK_ENGINEERING
    public static final String TABLE_NETWORK_ENGINEERING_SEM_1 = "NETWORK_ENGINEERING_SEM_1";
    public static final String TABLE_NETWORK_ENGINEERING_SEM_2 = "NETWORK_ENGINEERING_SEM_2";
    public static final String TABLE_NETWORK_ENGINEERING_SEM_3 = "NETWORK_ENGINEERING_SEM_3";
    public static final String TABLE_NETWORK_ENGINEERING_SEM_4 = "NETWORK_ENGINEERING_SEM_4";
    public static final String TABLE_NETWORK_ENGINEERING_SEM_5 = "NETWORK_ENGINEERING_SEM_5";
    public static final String TABLE_NETWORK_ENGINEERING_SEM_6 = "NETWORK_ENGINEERING_SEM_6";

    //DATABASE_ARCHITECTURE
    public static final String TABLE_DATABASE_ARCHITECTURE_SEM_1 = "DATABASE_ARCHITECTURE_SEM_1";
    public static final String TABLE_DATABASE_ARCHITECTURE_SEM_2 = "DATABASE_ARCHITECTURE_SEM_2";
    public static final String TABLE_DATABASE_ARCHITECTURE_SEM_3 = "DATABASE_ARCHITECTURE_SEM_3";
    public static final String TABLE_DATABASE_ARCHITECTURE_SEM_4 = "DATABASE_ARCHITECTURE_SEM_4";
    public static final String TABLE_DATABASE_ARCHITECTURE_SEM_5 = "DATABASE_ARCHITECTURE_SEM_5";
    public static final String TABLE_DATABASE_ARCHITECTURE_SEM_6 = "DATABASE_ARCHITECTURE_SEM_6";

    //WEB_DEVELOPMENT
    public static final String TABLE_WEB_DEVELOPMENT_SEM_1 = "WEB_DEVELOPMENT_SEM_1";
    public static final String TABLE_WEB_DEVELOPMENT_SEM_2 = "WEB_DEVELOPMENT_SEM_2";
    public static final String TABLE_WEB_DEVELOPMENT_SEM_3 = "WEB_DEVELOPMENT_SEM_3";
    public static final String TABLE_WEB_DEVELOPMENT_SEM_4 = "WEB_DEVELOPMENT_SEM_4";
    public static final String TABLE_WEB_DEVELOPMENT_SEM_5 = "WEB_DEVELOPMENT_SEM_5";
    public static final String TABLE_WEB_DEVELOPMENT_SEM_6 = "WEB_DEVELOPMENT_SEM_6";

    //SOFTWARE_ENGINEERING
    public static final String TABLE_SOFTWARE_ENGINEERING_SEM_1 = "SOFTWARE_ENGINEERING_SEM_1";
    public static final String TABLE_SOFTWARE_ENGINEERING_SEM_2 = "SOFTWARE_ENGINEERING_SEM_2";
    public static final String TABLE_SOFTWARE_ENGINEERING_SEM_3 = "SOFTWARE_ENGINEERING_SEM_3";
    public static final String TABLE_SOFTWARE_ENGINEERING_SEM_4 = "SOFTWARE_ENGINEERING_SEM_4";
    public static final String TABLE_SOFTWARE_ENGINEERING_SEM_5 = "SOFTWARE_ENGINEERING_SEM_5";
    public static final String TABLE_SOFTWARE_ENGINEERING_SEM_6 = "SOFTWARE_ENGINEERING_SEM_6";

    //Names of columns
    public static final String COLUMN_ID = "_id";
    public static final String STUDENT_ID = "student_id";
    public static final String FULL_NAME = "fullname";
    public static final String PASSWORD = "password";
    public static final String CONFIRM_PASSWORD = "confirmpassword";
    public static final String MODULE_CODE = "module_code";
    public static final String MODULE_TITLE = "module_title";
    public static final String PRESCRIPTION = "prescription";
    public static final String NZQA_CREDITS = "nzqa_credits";
    public static final String COURSE_NAME = "course_name";

    public String query = "CREATE TABLE " + TABLE_COURSES + "(" +         //Creating courses table
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COURSE_NAME + " TEXT UNIQUE " +
            ");";

    public String query25 = "CREATE TABLE " + TABLE_STUDENT + "(" +         //Creating student table
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            FULL_NAME + " TEXT ," +
            STUDENT_ID + " TEXT UNIQUE ," +
            PASSWORD + " TEXT ," +
            CONFIRM_PASSWORD + " TEXT " +
            ");";

    // creating network engineer table
    String query1 = "CREATE TABLE " + TABLE_NETWORK_ENGINEERING_SEM_1 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT , " +
            MODULE_TITLE + " TEXT , " +
            PRESCRIPTION + " TEXT , " +
            NZQA_CREDITS + " TEXT , " +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    String query2 = "CREATE TABLE " + TABLE_NETWORK_ENGINEERING_SEM_2 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT , " +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";
    String query3 = "CREATE TABLE " + TABLE_NETWORK_ENGINEERING_SEM_3 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT , " +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";
    String query4 = "CREATE TABLE " + TABLE_NETWORK_ENGINEERING_SEM_4 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT , " +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";
    String query5 = "CREATE TABLE " + TABLE_NETWORK_ENGINEERING_SEM_5 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";
    String query6 = "CREATE TABLE " + TABLE_NETWORK_ENGINEERING_SEM_6 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT , " +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    //creating database architecture tables
    String query7 = "CREATE TABLE " + TABLE_DATABASE_ARCHITECTURE_SEM_1 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";
    String query8 = "CREATE TABLE " + TABLE_DATABASE_ARCHITECTURE_SEM_2 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";
    String query9 = "CREATE TABLE " + TABLE_DATABASE_ARCHITECTURE_SEM_3 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";
    String query10 = "CREATE TABLE " + TABLE_DATABASE_ARCHITECTURE_SEM_4 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    String query11 = "CREATE TABLE " + TABLE_DATABASE_ARCHITECTURE_SEM_5 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";
    String query12 = "CREATE TABLE " + TABLE_DATABASE_ARCHITECTURE_SEM_6 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    //Web development
    String query13 = "CREATE TABLE " + TABLE_WEB_DEVELOPMENT_SEM_1 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    String query14 = "CREATE TABLE " + TABLE_WEB_DEVELOPMENT_SEM_2 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + "  TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    String query15 = "CREATE TABLE " + TABLE_WEB_DEVELOPMENT_SEM_3 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    String query16 = "CREATE TABLE " + TABLE_WEB_DEVELOPMENT_SEM_4 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    String query17 = "CREATE TABLE " + TABLE_WEB_DEVELOPMENT_SEM_5 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    String query18 = "CREATE TABLE " + TABLE_WEB_DEVELOPMENT_SEM_6 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    //SOFTWARE DEVELOPMENT
    String query19 = "CREATE TABLE " + TABLE_SOFTWARE_ENGINEERING_SEM_1 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    String query20 = "CREATE TABLE " + TABLE_SOFTWARE_ENGINEERING_SEM_2 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    String query21 = "CREATE TABLE " + TABLE_SOFTWARE_ENGINEERING_SEM_3 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    String query22 = "CREATE TABLE " + TABLE_SOFTWARE_ENGINEERING_SEM_4 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    String query23 = "CREATE TABLE " + TABLE_SOFTWARE_ENGINEERING_SEM_5 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    String query24 = "CREATE TABLE " + TABLE_SOFTWARE_ENGINEERING_SEM_6 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MODULE_CODE + " TEXT ," +
            MODULE_TITLE + " TEXT ," +
            PRESCRIPTION + " TEXT ," +
            NZQA_CREDITS + " TEXT ," +
            " UNIQUE(MODULE_CODE,MODULE_TITLE,PRESCRIPTION,NZQA_CREDITS) " +
            ");";

    public SqliteDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);
        db.execSQL(query7);
        db.execSQL(query8);
        db.execSQL(query9);
        db.execSQL(query10);
        db.execSQL(query11);
        db.execSQL(query12);
        db.execSQL(query13);
        db.execSQL(query14);
        db.execSQL(query15);
        db.execSQL(query16);
        db.execSQL(query17);
        db.execSQL(query18);
        db.execSQL(query19);
        db.execSQL(query20);
        db.execSQL(query21);
        db.execSQL(query22);
        db.execSQL(query23);
        db.execSQL(query24);
        db.execSQL(query25);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NETWORK_ENGINEERING_SEM_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NETWORK_ENGINEERING_SEM_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NETWORK_ENGINEERING_SEM_3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NETWORK_ENGINEERING_SEM_4);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NETWORK_ENGINEERING_SEM_5);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NETWORK_ENGINEERING_SEM_6);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATABASE_ARCHITECTURE_SEM_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATABASE_ARCHITECTURE_SEM_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATABASE_ARCHITECTURE_SEM_3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATABASE_ARCHITECTURE_SEM_4);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATABASE_ARCHITECTURE_SEM_5);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATABASE_ARCHITECTURE_SEM_6);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEB_DEVELOPMENT_SEM_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEB_DEVELOPMENT_SEM_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEB_DEVELOPMENT_SEM_3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEB_DEVELOPMENT_SEM_4);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEB_DEVELOPMENT_SEM_5);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEB_DEVELOPMENT_SEM_6);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOFTWARE_ENGINEERING_SEM_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOFTWARE_ENGINEERING_SEM_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOFTWARE_ENGINEERING_SEM_3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOFTWARE_ENGINEERING_SEM_4);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOFTWARE_ENGINEERING_SEM_5);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOFTWARE_ENGINEERING_SEM_6);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);

    }

    public void addCourses() {
        for (int i = 0; i < arrcourses.length; i++) {
            ContentValues values = new ContentValues();
            values.put(COURSE_NAME, arrcourses[i]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_COURSES, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }

    public void addNetworkSem1() {
        for (String[] entries : arrnetworksem1) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_NETWORK_ENGINEERING_SEM_1, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }

    public void addNetworkSem2() {
        for (String[] entries : arrnetworksem2) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_NETWORK_ENGINEERING_SEM_2, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }

    public void addNetworkSem3() {
        for (String[] entries : arrnetworksem3) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_NETWORK_ENGINEERING_SEM_3, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();

        }
    }

    public void addNetworkSem4() {
        for (String[] entries : arrnetworksem4) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_NETWORK_ENGINEERING_SEM_4, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }

    }

    public void addNetworkSem5() {
        for (String[] entries : arrnetworksem5) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_NETWORK_ENGINEERING_SEM_5, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }

    public void addNetworkSem6() {
        for (String[] entries : arrnetworksem6) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_NETWORK_ENGINEERING_SEM_6, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }

    public void addDBSem1() {
        for (String[] entries : arrnetworksem1) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_DATABASE_ARCHITECTURE_SEM_1, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }

    public void addDBSem2() {
        for (String[] entries : arrnetworksem2) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_DATABASE_ARCHITECTURE_SEM_2, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }

    public void addDBSem3() {
        for (String[] entries : arrnetworksem3) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_DATABASE_ARCHITECTURE_SEM_3, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }

    public void addDBSem4() {
        for (String[] entries : arrDBsem4) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_DATABASE_ARCHITECTURE_SEM_4, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }

    public void addDBSem5() {
        for (String[] entries : arrDBsem5) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_DATABASE_ARCHITECTURE_SEM_5, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }

    public void addDBSem6() {
        for (String[] entries : arrDBsem6) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_DATABASE_ARCHITECTURE_SEM_6, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }

    public void addWEBSem1() {
        for (String[] entries : arrnetworksem1) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_WEB_DEVELOPMENT_SEM_1, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }

    public void addWEBSem2() {
        for (String[] entries : arrnetworksem2) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_WEB_DEVELOPMENT_SEM_2, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }

    public void addWEBSem3() {
        for (String[] entries : arrnetworksem3) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_WEB_DEVELOPMENT_SEM_3, null, values);
            } catch (SQLiteConstraintException e) {
            }
            db.close();
        }
    }


    public void addWEBSem4() {
        for (String[] entries : arrWEBsem4) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_WEB_DEVELOPMENT_SEM_4, null, values);
            }catch (SQLiteConstraintException e) { }
            db.close();
        }

    }




    public void addWEBSem5() {
        for (String[] entries : arrWEBsem5) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_WEB_DEVELOPMENT_SEM_5, null, values);
            }catch (SQLiteConstraintException e){}
            db.close();
        }
    }

    public void addWEBSem6() {
        for (String[] entries : arrWEBsem6) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_WEB_DEVELOPMENT_SEM_6, null, values);
            }catch (SQLiteConstraintException e){}
            db.close();
        }
    }

    public void addSoftwareSem1() {
        for (String[] entries : arrnetworksem1) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_SOFTWARE_ENGINEERING_SEM_1, null, values);
            }catch (SQLiteConstraintException e){}
            db.close();
        }
    }

    public void addSoftwareSem2() {
        for (String[] entries : arrnetworksem2) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_SOFTWARE_ENGINEERING_SEM_2, null, values);
            }catch (SQLiteConstraintException e){}
            db.close();
        }
    }

    public void addSoftwareSem3() {
        for (String[] entries : arrnetworksem3) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_SOFTWARE_ENGINEERING_SEM_3, null, values);
            }catch (SQLiteConstraintException e){}
            db.close();
        }
    }

    public void addSoftwareSem4() {
        for (String[] entries : arrSoftwaresem4) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_SOFTWARE_ENGINEERING_SEM_4, null, values);
            }catch (SQLiteConstraintException e){}
            db.close();
        }
    }

    public void addSoftwareSem5() {
        for (String[] entries : arrSoftwaresem5) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_SOFTWARE_ENGINEERING_SEM_5, null, values);
            }catch (SQLiteConstraintException e){}
            db.close();
        }
    }

    public void addSoftwareSem6() {
        for (String[] entries : arrSoftwaresem6) {
            ContentValues values = new ContentValues();
            values.put(MODULE_CODE, entries[0]);
            values.put(MODULE_TITLE, entries[1]);
            values.put(PRESCRIPTION, entries[2]);
            values.put(NZQA_CREDITS, entries[3]);
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insertOrThrow(TABLE_SOFTWARE_ENGINEERING_SEM_6, null, values);
            }catch (SQLiteConstraintException e){}
            db.close();
        }
    }

  /*  public Cursor databaseToString(){
        SQLiteDatabase db=getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SOFTWARE_ENGINEERING_SEM_6+ " WHERE 1 ";    // * means all coloumns 1 means all rows
        //Cursor points to a location in your results
        Cursor c= db.rawQuery(query,null);  //pointer to a results
        return c;
    }*/
}
