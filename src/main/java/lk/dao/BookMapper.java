package lk.dao;

import lk.pojo.Book;

import java.util.List;

/**
 * ${提示信息}
 *
 * @author likeLove
 * @time 2020-07-31  16:36
 */
public interface BookMapper {
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
     * 按给定的信息查询书籍
     * @param bookName
     * @return list
     */
    List<Book> queryBookByQueryString(String bookName);
}
