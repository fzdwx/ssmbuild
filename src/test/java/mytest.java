import lk.dao.BookMapper;
import lk.pojo.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * ${提示信息}
 *
 * @author likeLove
 * @time 2020-07-31  18:41
 */
public class mytest {
    @Test
    public void name() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookMapper bd = context.getBean(BookMapper.class);
        List<Book> list = bd.queryBookByQueryString("%jav%");
        /*List<Book> list = bd.queryAllBook();*/
        for (Book book : list) {
            System.out.println(book);
        }
    }
}
/*
org.springframework.beans.factory.UnsatisfiedDependencyException：
        创建名称为'bookController'的bean时出错：未通过字段'bookService'表示依赖关系；
        嵌套的异常是org.springframework.beans.factory.BeanCreationException：创建在类路径资源[springService.xml]中定义的名称为'bookService'的bean时出错：
        设置bean属性'bookDao'时无法解析对bean'bookDao'的引用；
        嵌套的异常是org.springframework.beans.factory.BeanCreationException：
        创建文件[D：\ Java \ project \ SSMBuild \ target \ classes \ dao \ BookDao.class]中定义的名称为'bookDao'的bean时出错：
        无法解析对bean的引用设置bean属性“ sqlSessionFactory”时为“ sqlSessionFactory”；
        嵌套的异常是org.springframework.beans.factory.BeanCreationException：
        创建在类路径资源[springDao.xml]中定义的名称为'sqlSessionFactory'的bean时出错：
        设置bean属性'dataSource'时无法解析对bean'dataSource'的引用；
        嵌套的异常是org.springframework.beans.factory.BeanCreationException：
        创建在类路径资源[springDao.xml]中定义的名称为'dataSource'的bean时出错：bean的初始化失败；
        嵌套的异常是org.springframework.beans.ConversionNotSupportedException：
        无法将类型“ java.lang.String”的属性值转换为属性“ driver”的必需类型“ java.sql.Driver”；
        嵌套异常为java.lang.IllegalStateException：无法将类型“ java.lang.String”的值转换为属性“ driver”的必需类型“ java.sql.Driver”：
        未找到匹配的编辑器或转换策略*/
