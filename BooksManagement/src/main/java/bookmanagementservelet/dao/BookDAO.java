package bookmanagementservelet.dao;

import bookmanagementservelet.model.Book;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private boolean isTest = false;

    public BookDAO() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                // This is a sign that we are in a test environment
                isTest = true;
                jdbcURL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
                jdbcUsername = "sa";
                jdbcPassword = "";
                return;
            }
            prop.load(input);
            this.jdbcURL = prop.getProperty("jdbcURL");
            this.jdbcUsername = prop.getProperty("jdbcUsername");
            this.jdbcPassword = prop.getProperty("jdbcPassword");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String INSERT_BOOK_SQL = "INSERT INTO books (title, author, price) VALUES (?, ?, ?);";
    private static final String SELECT_BOOK_BY_ID = "SELECT id, title, author, price FROM books WHERE id = ?";
    private static final String SELECT_ALL_BOOKS = "SELECT * FROM books;";
    private static final String DELETE_BOOK_SQL = "DELETE FROM books WHERE id = ?;";
    private static final String UPDATE_BOOK_SQL = "UPDATE books SET title = ?, author = ?, price = ? WHERE id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            if(isTest) {
                Class.forName("org.h2.Driver");
            } else {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertBook(Book book) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.executeUpdate();
            
            if (isTest) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        book.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    public Book selectBook(int id) {
        Book book = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                book = new Book(id, title, author, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Book> selectAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                books.add(new Book(id, title, author, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public boolean deleteBook(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateBook(Book book) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK_SQL);) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3, book.getPrice());
            statement.setInt(4, book.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}