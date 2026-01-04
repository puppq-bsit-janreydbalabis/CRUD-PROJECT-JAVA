package bookmanagementservelet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import bookmanagementservelet.dao.BookDAO;
import bookmanagementservelet.model.Book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class Mybookmanagement extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        bookDAO = new BookDAO(jdbcURL, jdbcUsername, jdbcPassword);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertBook(request, response);
                    break;
                case "/delete":
                    showDeleteForm(request, response);
                    break;
                case "/confirmDelete":
                    deleteBook(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateBook(request, response);
                    break;
                default:
                    listBook(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> listBook = bookDAO.selectAllBooks();
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.selectBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookDAO.selectBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-delete.jsp");
        request.setAttribute("book", book);
        dispatcher.forward(request, response);
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String priceStr = request.getParameter("price");
        float price = 0;
        try {
            price = Float.parseFloat(priceStr);
            Book newBook = new Book(0, title, author, price);
            bookDAO.insertBook(newBook);
            response.sendRedirect("list");
        } catch (NumberFormatException e) {
            Book book = new Book(0, title, author, 0);
            request.setAttribute("book", book);
            request.setAttribute("error", "Invalid price format. Please enter a valid number.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String priceStr = request.getParameter("price");
        float price = 0;
        
        try {
            price = Float.parseFloat(priceStr);
            Book book = new Book(id, title, author, price);
            bookDAO.updateBook(book);
            response.sendRedirect("list");
        } catch (NumberFormatException e) {
            Book book = new Book(id, title, author, 0);
            request.setAttribute("book", book);
            request.setAttribute("error", "Invalid price format. Please enter a valid number.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookDAO.deleteBook(id);
        response.sendRedirect("list");
    }
}