package praktikum.pbo.helper;

import praktikum.pbo.model.Matakuliah;

import java.sql.*;
import java.util.ArrayList;

public class MyConnection {
    private static String JOBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/";
    private static String DB_NAME = "db_java";
    private static String TIMEZONE = "?serverTimezone=Asia/Makassar";
    private static String USER = "root";
    private static String PASS = "";

    public Connection getConnection() {
        Connection con = null;

        try {
            Class.forName(JOBC_DRIVER);
            System.out.println("sedang menghubungkan ke database...");
            con = DriverManager.getConnection(URL + DB_NAME + TIMEZONE, USER, PASS);
            System.out.println("Berhasil Terkoneksi");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Matakuliah matakuliah;
        ArrayList<Matakuliah> matakuliahArrayList = new ArrayList<>();
        MyConnection myConnection = new MyConnection();
        Connection con = myConnection.getConnection();

        String querySelect = "SELECT * FROM matkul";
        Statement statement;
        ResultSet resultSet;

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(querySelect);
            while (resultSet.next()) {
                matakuliah = new Matakuliah(
                        resultSet.getInt("id_matkul"),
                        resultSet.getString("nama_matkul"),
                        resultSet.getString("singkatan_matkul"),
                        resultSet.getString("nama_dosen"),
                        resultSet.getString("kontak_dosen"),
                        resultSet.getBoolean("aktif")
                );
                matakuliahArrayList.add(matakuliah);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        for (Matakuliah value : matakuliahArrayList) {
            System.out.print(value.getIdMakul());
            System.out.print("\t");
            System.out.print(value.getNamaMakul());
            System.out.print("\t");
            System.out.print(value.getNamaDosen());
            System.out.println();
        }
    }
}
