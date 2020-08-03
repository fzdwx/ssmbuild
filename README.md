# ssmbuild

[toc]
# 依赖：



~~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns = "http://maven.apache.org/POM/4.0.0"
         xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation = "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>SSMBuild</artifactId>
    <version>1.0-SNAPSHOT</version>
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
    <repositories>
        <repository>
            <id>nexus-aliyun</id>
            <name>Nexus aliyun</name>
            <layout>default</layout>
            <url>https://maven.aliyun.com/repository/public</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
            <releases>
                <enabled>true</enabled>
            </releases>
        </repository>
    </repositories>
    <!--依赖
         junit，数据库驱动，数据库连接池，servlet，jsp，mybatis，mybatis-spring，spring，spring mvc
    -->

    <dependencies>
        <!--Junit-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.9</version>
        </dependency>
        <!--数据库驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
        <!-- 数据库连接池 -->
        <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.2</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.9</version>
        </dependency>

        <!--Servlet - JSP -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
             <version>4.0.1</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <!--Mybatis-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.5</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.2</version>
        </dependency>

        <!--Spring-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.7.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.7.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.4</version>
        </dependency>
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.3.3</version>
        </dependency>
        <dependency>
            <groupId>org.webjars.bower</groupId>
            <artifactId>jquery</artifactId>
            <version>3.5.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-io</artifactId>
            <version>1.3.2</version>
        </dependency>
    </dependencies>
    
</project>
~~~~





# mybatis整合



## 1.数据库环境搭建

~~~sql
CREATE DATABASE ssmbuild;
USE ssmbuild;
CREATE TABLE `books`(
    `bookID` INT NOT NULL AUTO_INCREMENT COMMENT '书id',
    `bookName` VARCHAR(100) NOT NULL COMMENT '书名',
    `bookCounts` INT NOT NULL COMMENT '数量',
    `detail` VARCHAR(200) NOT NULL COMMENT '描述',
    KEY `bookID`(`bookID`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO `books`(`bookID`,`bookName`,`bookCounts`,`detail`)VALUES
(1,'Java',1,'从入门到放弃'),
(2,'MySQL',10,'从删库到跑路'),
(3,'Linux',5,'从进门到进牢')
~~~

## 2.pojo类

Book

~~~java
public class Book {
   private  int bookID;
   private String bookName;
   private int bookCounts;
   private String detail;
}
~~~



## 3.mybatisConfig.xml

~~~xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--配置数据库数据源  在spring中写-->
   <typeAliases>
        <typeAlias type="lk.pojo.Book" alias="book"/>
    </typeAliases>
    <mappers>
        <mapper resource="mapper/BooksMapper.xml"/>
    </mappers>
</configuration>
~~~



## 4.dao层：

### 	BookMapper

~~~java
public interface BookMapper {
    boolean addBook(Books books); 
    boolean delBook(int bookID);
    boolean updateBook(Books books);
    Books queryBook(int id);
    List<Books> queryAllBook();
}
~~~



### 	BookMapper.xml

>   增删改查功能

~~~xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="dao.BookDao">
    <insert id="addBook">  
        insert into books ( bookName, bookCounts, detail) values(#{bookName},#{bookCounts},#{detail});
    </insert>
    <delete id="delBook">    
        delete from books where bookID = #{bookID};
    </delete>
    <update id="updateBook">     
        update books set  bookName= #{bookName}, bookCounts =#{bookCounts}, detail = #{detail} where bookID = #{bookID};
    </update>
    <select id="queryAllBook" resultType="book">        
        select * from books;
    </select>
    <select id="queryBook" resultType="book">       
        select * from books where bookID = #{id}
    </select>
</mapper>
~~~



## 5.service

### 	Bookservice

~~~java
public interface BookService {
    boolean addBook(Books books);
    boolean delBook(int bookID);
    boolean updateBook(Books books);
    Books queryBook(int id);
    List<Books> queryAllBook();
}
~~~



### 	BookServiceImpl

~~~java
public class BookServiceImpl  implements BookService{
    //service 调用dao层
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public boolean addBook(Books books) {
        return bd.addBook(books);
    }

    @Override
    public boolean delBook(int bookID) {
        return bd.delBook(bookID);
    }

    @Override
    public boolean updateBook(Books books) {
        return bd.updateBook(books);
    }

    @Override
    public Books queryBook(int id) {
        return bd.queryBook(id);
    }

    @Override
    public List<Books> queryAl 
~~~



## 2.springService



~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 
service
@author likeLove
@time 2020 - 07 - 31  17:55
-->
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"

       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
    <!--注解驱动 -->
    <context:annotation-config/>
    <!--1.扫描service-->
    <context:component-scan base-package="lk.service"/>
    <!--2.注入spring-->
    <bean class="lk.service.BookServiceImpl" id="bookServiceImpl">
        <property name="bookMapper" ref="bookMapper"/>
    </bean>
    <!--3.事务-->
    <bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager" id="tx">
        <!--注入数据源-->
        <property name="dataSource" ref="dataSource"/>
    </bean>
    <!--4.aop事务支持-->

</beans>
~~~





# springMVC整合



## 1.spirngMvc.xml

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 

@author likeLove
@time 2020 - 07 - 31  18:15
-->
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <!--注解驱动 -->
    <context:annotation-config/>
    <!--1.mvc的注解驱动-->
    <mvc:annotation-driven/>
    <!--2.静态资源过滤-->
    <mvc:default-servlet-handler/>
    <!--3.包扫描-->
    <context:component-scan base-package="controller"/>
    <!--4.视图解析-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/pages/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
~~~





## 2.web.xml

>   1.DispatcherServlet
>
>   2.CharacterEncodingFilter
>
>   3.session-timeout

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--1.dispatchServlet-->
    <servlet>
        <servlet-name>SpirngMvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springMvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>SpirngMvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!--2.解决乱码-->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceRequestEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!--3.session过期时间-->
    <session-config><session-timeout>15</session-timeout></session-config>
</web-app>
~~~





# controller.curd：

>   1.控制层的增删改查功能

~~~java
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
}
~~~



# pages：

>   1.使用了bootstrap的样式

## 1.index.jsp:

>   1.首页

~~~jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>首页</title>
	<style type="text/css">
		a {
			text-decoration: none;
			color: black;
			font-size: 18px;
		}
		h3 {
			width: 250px;
			height: 50px;
			margin: 200px  auto;
			text-align: center;
			line-height: 50px;
			background: darkcyan;
			border-radius: 25px;
		}
	</style>
</head>
<body>
<h3><a href="${pageContext.request.contextPath}/book/getAllBooks">查询所有图书</a></h3>
</body>
</html>
~~~





## 2.getAllBooks.jsp

>   1.显示书籍列表



~~~jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>查看所有书籍</title>
	
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="page-header">
				<h1>
					<small style="font-size: 38px">书城——显示所有书籍</small>
				</h1>
                //提示消息
				<div style="float: right;"><span style="color: red;font-size: 18px;font-weight: bold">${error}</span></div>
			</div>
			<div class="row">
				<div class="col-md-8 column">
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/book/goAddBook">添加书籍</a>
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/book/getAllBooks">显示所有书籍</a>
				</div>
				
				<div class="col-md-4 column">
					<div style="float: right">
						<form action="${pageContext.request.contextPath}/book/queryBookByQueryString"
						      class="form-inline">
							<input type="text" placeholder="请输入你要查找的书的名字" name="bookName">
							<input type="submit" value="查询" class="btn btn-primary">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class=" row clearfix">
		<div class="col-md-12 column">
			<table class="table table-hover table-striped">
				<thead>
				<tr>
					<th>书籍编号</th>
					<th>书籍标题</th>
					<th>书籍数量</th>
					<th>书籍详情</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="book" items="${list}">
					<tr>
						<td>${book.bookID}</td>
						<td>${book.bookName}</td>
						<td>${book.bookCounts}</td>
						<td>${book.detail}</td>
						<td>
							<a href="${pageContext.request.contextPath}/book/goUpdate/${book.bookID}">修改</a>
							&nbsp;|&nbsp;
							<a href="${pageContext.request.contextPath}/book/delBook/${book.bookID}">删除</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>

~~~



## 3.addBooks.jsp

>   1.添加图书页面

~~~jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>添加书籍</title>
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="page-header">
				<h1>
					<small>书城——添加书籍</small>
				</h1>
			</div>
		</div>
	</div>
	<form action="${pageContext.request.contextPath}/book/addBook">
		<div class="form-group">
			<laber for="bookName">书籍标题:</laber>
			<input type="text" class="form-control" name="bookName" required/>
		</div>
		<div class="form-group">
			<laber for="bookCounts">书籍数量:</laber>
			<input type="text" class="form-control" name="bookCounts" required/>
		</div>
		<div class="form-group">
			<laber for="detail">书籍详情:</laber>
			<input type="text" class="form-control" name="detail" required/>
		</div>
		<button type="submit" class="btn btn-default">添加</button>
	</form>
</div>
</body>
</html>

~~~



## 4.updateBook.jsp

>   修改图书页面

~~~jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>添加书籍</title>
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="page-header">
				<h1>
					<small>书城——修改书籍</small>
				</h1>
			</div>
		</div>
	</div>
	<form action="${pageContext.request.contextPath}/book/updateBook">
		<input type="hidden"  name="bookID" value="${book.bookID}">
		<div class="form-group">
			<laber for="bookName">书籍标题:</laber>
			<input type="text" class="form-control" name="bookName" value="${book.bookName}" required/>
		</div>
		<div class="form-group">
			<laber for="bookCounts">书籍数量:</laber>
			<input type="text" class="form-control" name="bookCounts" value="${book.bookCounts}" required/>
		</div>
		<div class="form-group">
			<laber for="detail">书籍详情:</laber>
			<input type="text" class="form-control" name="detail" value="${book.detail}" required/>
		</div>
		<button type="submit" class="btn btn-default">修改</button>
	</form>
</div>
</body>
</html>
~~~





# 增加搜索书籍功能：

## 1.dao：

 

~~~java
  List<Book> queryBookByQueryString(String bookName);
~~~





~~~xml
  <select id="queryBookByQueryString" resultType="lk.pojo.Book" >
        select  * from books
        <where>
        <!--<choose>-->
        <!--<when test="_parameter != null ">-->bookName like #{bookName}<!--</when>-->
      <!--  </choose>-->
        </where>
    </select>
~~~





## 2.service：



~~~java
  List<Book> queryBookByQueryString(String bookName);
~~~





~~~java
@Override
public List<Book> queryBookByQueryString(String bookName) {
    return bookMapper.queryBookByQueryString(bookName);
}
~~~



## 3.controller



~~~java
@RequestMapping ("/queryBookByQueryString")
public String queryBookByQueryString(String bookName, Model model) {
    //判断用户输入是否为空
    if ("".equals(bookName) || bookName == null) {
        model.addAttribute("error", "请输入内容后搜索");
        return "getAllBooks";
    }
    List<Book> books = bookServiceImpl.queryBookByQueryString("%" + bookName + "%");
    //判断获取的list是否为空
    if (books.size() > 0) {
        model.addAttribute("list", books);
    } else {
        model.addAttribute("error", "未找到你需要的书籍");
    }
    return "getAllBooks";
}
~~~





# 增加数据库事务功能：

依赖：

~~~xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.4</version>
</dependency>
~~~





spring-service.xml



~~~xml
<!--4.aop事务支持-->
<!--配置事务通知-->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <tx:attributes>
        //指定所有方法，策略：没有事务的生成事务
        <tx:method name="*" propagation="REQUIRED"/>
    </tx:attributes>
</tx:advice>
<aop:config>
    //事务切入点
    <aop:pointcut id="bookPoint" expression="execution(* lk.service.*.*(..))*"/>
    <aop:advisor advice-ref="txAdvice" pointcut-ref="bookPoint"/>
</aop:config>
~~~



# 增加拦截器功能：验证用户是否登录



## 1.拦截器实现类



>   1.  验证session中是否存在login这个字段
>   2.  如果不存在就转发到登录页面

~~~java
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String login = (String) request.getSession().getAttribute("login");
        if ("true".equals(login)) {
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        return false;
    }
}
~~~



## 2.在springMVC中配置拦截器：

>   拦截和book有关的请求

~~~xml
<!--拦截器-->
<mvc:interceptors>
    <mvc:interceptor>
        <!--book-->
        <mvc:mapping path="/book/*" />
        <!--拦截器所在的类-->
        <bean class="lk.controller.Interceptor"></bean>
    </mvc:interceptor>
</mvc:interceptors>
~~~



## 3.登录控制器



>   处理和登录相关的请求
>
>   1.  判断用户是否输入了用户名和密码
>   2.  输入了就返回书籍列表
>   3.  没有则继续请求登录界面

~~~java
@Controller
@RequestMapping ("/user")
public class LoginController {
    @RequestMapping ("/goLogin")
    public String goLogin() {
        return "login";
    }

    @RequestMapping ("/login")
    public String login(String username, String pwd, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       if (username != null && pwd != null) {
            HttpSession session = req.getSession();
            session.setAttribute("login", "true");
            session.setAttribute("username", username);
            return "redirect:/book/getAllBooks";
        } else {
            return "redirect:/user/goLogin";
        } 

    }
}
~~~





## 4.login.jsp



~~~jsp
<form action="${pageContext.request.contextPath}/user/login" method="post">
	用户名：
	<input type="text" name="username">
	密码:
	<input type="password" name="pwd">
	<input type="submit" value="登录">
</form>
~~~





# 文件的上传和下载：

## 1.依赖：

~~~xml
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.3.3</version>
</dependency>
<dependency>
    <groupId>org.webjars.bower</groupId>
    <artifactId>jquery</artifactId>
    <version>3.5.1</version>
</dependency>
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-io</artifactId>
    <version>1.3.2</version>
</dependency>
~~~



## 2.springMVC.xml

>   配置文件上传下载的解析器

~~~xml
<!--文件上传和下载-->
<bean class = "org.springframework.web.multipart.commons.CommonsMultipartResolver" id = "multipartResolver">
    <property name = "defaultEncoding" value = "utf-8" />
    <property name = "maxUploadSize" value = "10485760" />
    <property name = "maxInMemorySize" value = "40960" />
</bean>
~~~



## 3.FileController

>   文件上传下载的控制器，页面请求里面的方法

~~~java
@Controller
@RequestMapping ("/file")
public class FileController {
    /* private static final String path = "D:\\Java\\project\\SSMBuild\\web\\upload";*/
    @RequestMapping ("/upload")
    public String fileUpload(@RequestParam ("file") CommonsMultipartFile file, HttpServletRequest req) throws Exception {
        String upLoadFileName = file.getOriginalFilename();
        if (!"".equals(upLoadFileName)) {
            System.out.println("上传了：" + upLoadFileName);
            String path = req.getServletContext().getRealPath("/upload");
            File f = new File(path);
            if (!f.exists()) {
                f.mkdir();
            }
            System.out.println("保存在了:" + f);
            InputStream is = file.getInputStream();
            FileOutputStream os = new FileOutputStream(new File(f, upLoadFileName));
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
                os.flush();
            }
            os.close();
            is.close();
        }
        return "../../index";
    }

    @RequestMapping ("/upload2")
    public String fileUpload2(@RequestParam ("file") CommonsMultipartFile file, HttpServletRequest req) throws Exception {
        String path = req.getServletContext().getRealPath("/upload");
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        file.transferTo(new File(f + "/" + file.getOriginalFilename()));
        return "../../index";
    }

    @RequestMapping ("/download")
    public String fileDownload(HttpServletRequest req, HttpServletResponse resp,@RequestParam ("fileName") String fileName) throws Exception {
        String path = req.getServletContext().getRealPath("/upload");
        System.out.println(fileName);
        resp.reset();
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("multipart/form-data");
        resp.setHeader("Content-Disposition",
                       "attachment;fileName=" + URLEncoder.encode(fileName, "utf-8"));
        File file = new File(path, fileName);
        FileInputStream is = new FileInputStream(file);
        ServletOutputStream os = resp.getOutputStream();
        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = is.read(bys)) != -1) {
            os.write(bys, 0, len);
            os.flush();
        }
        os.close();
        is.close();
        return "../../index";
    }
}

~~~



## 4.下载、上传页面：

~~~jsp
<form action="${pageContext.request.contextPath}/file/upload" enctype="multipart/form-data" method="post">
	上传文件
	<input type="file" name="file">
	<input type="submit" value="上传">
</form>
<form action="${pageContext.request.contextPath}/file/download" method="get">
	输入你要下载的文件：
	<input type="text" name="fileName">
	<input type="submit" value="下载">
</form>
~~~



