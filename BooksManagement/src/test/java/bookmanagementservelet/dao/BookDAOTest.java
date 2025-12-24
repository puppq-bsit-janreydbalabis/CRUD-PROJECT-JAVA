package bookmanagementservelet.dao;

import bookmanagementservelet.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BookDAOTest {

    private BookDAO bookDAO;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        bookDAO = new BookDAO();
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE books (id INT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(255), author VARCHAR(255), price DOUBLE)");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE books");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertBook() throws SQLException {
        Book book = new Book(0, "The Hobbit", "J.R.R. Tolkien", 10.00);
        bookDAO.insertBook(book);

        List<Book> books = bookDAO.selectAllBooks();
        assertEquals(1, books.size());
        assertEquals("The Hobbit", books.get(0).getTitle());
    }

    @Test
    public void testSelectBook() throws SQLException {
        Book book = new Book(0, "The Hobbit", "J.R.R. Tolkien", 10.00);
        bookDAO.insertBook(book);

        List<Book> books = bookDAO.selectAllBooks();
        int id = books.get(0).getId();

        Book selectedBook = bookDAO.selectBook(id);
        assertNotNull(selectedBook);
        assertEquals("The Hobbit", selectedBook.getTitle());
    }

    @Test
    public void testSelectAllBooks() throws SQLException {
        Book book1 = new Book(0, "The Hobbit", "J.R.R. Tolkien", 10.00);
        bookDAO.insertBook(book1);
        Book book2 = new Book(0, "The Lord of the Rings", "J.R.R. Tolkien", 20.00);
        bookDAO.insertBook(book2);

        List<Book> books = bookDAO.selectAllBooks();
        assertEquals(2, books.size());
    }

    @Test
    public void testDeleteBook() throws SQLException {
        Book book = new Book(0, "The Hobbit", "J.R.R. Tolkien", 10.00);
        bookDAO.insertBook(book);
        List<Book> books = bookDAO.selectAllBooks();
        int id = books.get(0).getId();

        boolean deleted = bookDAO.deleteBook(id);
        assertTrue(deleted);

        books = bookDAO.selectAllBooks();
        assertEquals(0, books.size());
    }

    @Test
    public void testUpdateBook() throws SQLException {
        Book book = new Book(0, "The Hobbit", "J.R.R. Tolkien", 10.00);
        bookDAO.insertBook(book);

        List<Book> books = bookDAO.selectAllBooks();
        int id = books.get(0).getId();

        Book bookToUpdate = new Book(id, "The Hobbit Updated", "J.R.R. Tolkien", 12.00);
        boolean updated = bookDAO.updateBook(bookToUpdate);
        assertTrue(updated);

        Book updatedBook = bookDAO.selectBook(id);
        assertEquals("The Hobbit Updated", updatedBook.getTitle());
        assertEquals(12.00, updatedBook.getPrice(), 0.001);
    }
}
