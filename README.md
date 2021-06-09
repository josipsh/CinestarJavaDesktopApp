# CinestarJavaDesktopApp
CinnestarApp is Java application which fetches and presents data about movies announcements from Cinestar/RSS in Croatia. 
You can view, delete and edit all data, or you can add a new movie announcement with all belonging info.
Also, you can exsport data from database into xml document.

Application automaticly create folder (CinestarAppStorage) in your Home_document_folder where will be 2 subfolders. 
First subfolder is "Assets" folder where will be stored all assset like images.
Second subfolder is chache folder where app will store all log that accour while runnig an app. 
In case that you get error, you can find more details about error in "error_codes.txt" file.

Starting application
First you need to create database. I have use MS SQL server 2019
Run MovieTheater.sql script to create database.

Check libraries, you should see library (https://jar-download.com/artifacts/com.microsoft.sqlserver/mssql-jdbc/7.0.0.jre8/source-code), if not, please include.

Run an app. A used NetBeans 8.0.2 IDE. 
