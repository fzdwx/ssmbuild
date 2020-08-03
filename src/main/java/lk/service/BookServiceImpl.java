package lk.service;

import lk.dao.BookMapper;
import lk.pojo.Book;

import java.util.List;

/**
 * ${提示信息}
 *
 * @author likeLove
 * @time 2020-07-31  16:49
 */
public class BookServiceImpl  implements BookService{
    //service 调用dao层
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public boolean addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public boolean delBook(int bookID) {
        return bookMapper.delBook(bookID);
    }

    @Override
    public boolean updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public Book queryBook(int id) {
        return bookMapper.queryBook(id);
    }

    @Override
    public List<Book> queryAllBook() {
        return bookMapper.queryAllBook();
    }

    @Override
    public List<Book> queryBookByQueryString(String bookName) {
        return bookMapper.queryBookByQueryString(bookName);
    }
}
