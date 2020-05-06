package cn.pslab.wanglong.web.servlet;

import cn.pslab.wanglong.domain.Book;
import cn.pslab.wanglong.domain.PageBean;
import cn.pslab.wanglong.domain.User;
import cn.pslab.wanglong.service.BookService;
import cn.pslab.wanglong.service.FavoriteService;
import cn.pslab.wanglong.service.impl.BookServiceImpl;
import cn.pslab.wanglong.service.impl.FavoriteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book/*")
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        //接收rname 书籍名称
        String rname = request.getParameter("rname");
//        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");
        //类别id
        int cid = 0;
        //2、处理参数
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;
        //当前页码，如果不传递，则默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        //每页显示条数，如果不传递，默认为5条
        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }
        //3、调用service查询PageBean对象
        PageBean<Book> pb = bookService.pageQuery(cid, currentPage, pageSize, rname);
        //4、将PageBean对象序列化为json，返回给客户端
        writeValue(pb, response);
    }

    /**
     * 根据id查询一个旅游线路的详细信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、接收rid
        String rid = request.getParameter("rid");
        //2、调用service查询book对象
        Book book = bookService.findOne(rid);
        //3、转为json写回客户端
        writeValue(book, response);
    }

    /**
     * 判断当前用户是否收藏过该书籍
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、获取线路id
        String rid = request.getParameter("rid");
        //2、获取当前登录的用户user
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null){
            //用户未登录
            uid = 0;
        }else {
            uid = user.getUid();
        }
        //3、调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);
        //4、写回客户端
        writeValue(flag, response);
    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1、获取书籍rid
        String rid = request.getParameter("rid");
        //2、获取用户uid
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null){
            //用户未登录
            return;
        }else {
            uid = user.getUid();
        }
        //3、调用service添加
        favoriteService.add(rid, uid);

    }
}