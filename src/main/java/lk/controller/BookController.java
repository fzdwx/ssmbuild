package lk.controller;

import lk.pojo.Book;
import lk.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ${提示信息}
 *
 * @author likeLove
 * @time 2020-07-31  18:27
 */
@Controller
@RequestMapping ("/book")
public class BookController {
    /* *//*@Autowired
    @Qualifier("bookServiceImpl")*/
    @Resource
    private BookService bookServiceImpl;

    /** 查询书籍 */
    @RequestMapping ("/getAllBooks")
    public String getAllBooks(Model mod) {
        List<Book> books = bookServiceImpl.queryAllBook();
        mod.addAttribute("list", books);
        return "getAllBooks";
    }

    /** 去添加书籍页面 */
    @RequestMapping ("/goAddBook")
    public String goAddBook() {
        return "addBook";
    }

    /** 添加书籍 */
    @RequestMapping ("/addBook")
    public String addBook(Book book) {
        boolean b = bookServiceImpl.addBook(book);
        if (b) {
            System.out.println("添加成功");
        }
        return "redirect:/book/getAllBooks";
    }

    /** 去修改书籍页面 */
    @RequestMapping ("/goUpdate/{id}")
    public String goUpdate(@PathVariable int id, Model model) {
        Book book = bookServiceImpl.queryBook(id);
        model.addAttribute("book", book);
        return "updateBook";
    }

    /** 修改书籍 */
    @RequestMapping ("/updateBook")
    public String updateBook(Book book) {
        boolean b = bookServiceImpl.updateBook(book);
        System.out.println(book);
        if (b) {
            System.out.println("修改成功");
        }
        return "redirect:/book/getAllBooks";
    }

    /** 删除书籍 */
    @RequestMapping ("/delBook/{id}")
    public String deleteBook(@PathVariable int id) {
        boolean b = bookServiceImpl.delBook(id);
        if (b) {
            System.out.println("删除");
        }
        return "redirect:/book/getAllBooks";
    }

    @RequestMapping ("/queryBookByQueryString")
    public String queryBookByQueryString(String bookName, Model model) {
        if ("".equals(bookName) || bookName == null) {
            model.addAttribute("error", "请输入内容后搜索");
            return "getAllBooks";
        }
        List<Book> books = bookServiceImpl.queryBookByQueryString("%" + bookName + "%");
        if (books.size() > 0) {
            model.addAttribute("list", books);
        } else {
            model.addAttribute("error", "未找到你需要的书籍");
        }
        return "getAllBooks";
    }

    @RequestMapping ("/ajax")
    public void ajax(String name, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        System.out.println(name);
        if ("like".equals(name)) {
            response.getWriter().write("验证正确");
        } else {
            response.getWriter().write("验证失败");
        }
    }

}

