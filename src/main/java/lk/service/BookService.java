package lk.service;

import lk.pojo.Book;

import java.util.List;

/**
 * ${提示信息}
 *
 * @author likeLove
 * @time 2020-07-31  16:49
 */
public interface BookService {
    /**
     * 添加一本书
     *
     * @param book books
     *
     * @return boolean
     */
    boolean addBook(Book book);

    /**
     * 删除一本书
     *
     * @param bookID int
     *
     * @return boolean
     */
    boolean delBook(int bookID);

    /**
     * 修改一本书
     *
     * @param book books
     *
     * @return boolean
     */
    boolean updateBook(Book book);

    /**
     * 查询一本书
     *
     * @param id int
     *
     * @return books
     */
    Book queryBook(int id);

    /**
     * 查询所有书
     *
     * @return list
     */
    List<Book> queryAllBook();

    /**
     * 查询图书根据特定的字符
     * @param bookName
     * @return
     */
    List<Book> queryBookByQueryString(String bookName);;
}
