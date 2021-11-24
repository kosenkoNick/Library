package cloud.dmytrominochkin.mvc.services.impl;

import cloud.dmytrominochkin.mvc.services.BookService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private Connection conn;

    public BookServiceImpl(String uri) {
        try {
            conn = DriverManager.getConnection(uri, "postgres", "postgres");
            System.out.println("Connection established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAllBooks() {
        String sql = "SELECT name FROM public.\"Books\"";
        List<String> list = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet set = stat.executeQuery(sql);
            while (set.next()) {
                list.add(set.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> getAllChecked() {
        String sql = "SELECT name FROM public.\"Books\" WHERE available = false";
        List<String> list = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet set = stat.executeQuery(sql);
            while (set.next()) {
                list.add(set.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
