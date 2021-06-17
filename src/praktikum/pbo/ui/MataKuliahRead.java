package praktikum.pbo.ui;

import praktikum.pbo.helper.MyConnection;
import praktikum.pbo.model.Matakuliah;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MataKuliahRead extends JFrame {
    private JPanel panel1;
    private JTable matakuliahTable;
    private JScrollPane scrollPane1;

    private void createUIComponents() {
        Matakuliah matakuliah;
        ArrayList<Matakuliah> matakuliahArrayList = new ArrayList<>();
        MyConnection myConnection = new MyConnection();
        Connection con = myConnection.getConnection();

        String selectQuery  = "Select * from matkul";
        Statement statement;
        ResultSet resultSet;

        try{
            statement = con.createStatement();
            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()){
                matakuliah = new Matakuliah(
                        resultSet.getInt("id_matkul"),
                        resultSet.getString("nama_matkul"),
                        resultSet.getString("singkatan_matkul"),
                        resultSet.getString("nama_dosen"),
                        resultSet.getString("kontak_dosen"),
                        resultSet.getBoolean("aktif")
                );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        Object[][] row = new Object[matakuliahArrayList.size()][6];

        for (int i = 0; i < matakuliahArrayList.size(); i++) {
            row[i][0] = matakuliahArrayList.get(i).getIdMakul();
            row[i][0] = matakuliahArrayList.get(i).getNamaMakul();
            row[i][0] = matakuliahArrayList.get(i).getSingkatanMakul();
            row[i][0] = matakuliahArrayList.get(i).getNamaDosen();
            row[i][0] = matakuliahArrayList.get(i).getKontakDosen();
            row[i][0] = matakuliahArrayList.get(i).isAktif();
        }
        String MataKuliahHeader[] = {"ID", "MATA KULIAH", "SINGKATAN", "DOSEN", "KONTAK", "AKTIF"};
        TableModel tableModel = new DefaultTableModel(row, MataKuliahHeader);
        matakuliahTable = new JTable(tableModel);
    }
    public static void main (String[] args) {
        MataKuliahRead mataKuliahRead = new MataKuliahRead();
        mataKuliahRead.setContentPane(new MataKuliahRead().panel1);
        mataKuliahRead.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mataKuliahRead.createUIComponents();
        mataKuliahRead.pack();
        mataKuliahRead.setLocationRelativeTo(null);
        mataKuliahRead.setVisible(true);
    }
}