package controller;
import javax.servlet.*;import javax.servlet.http.*;import javax.servlet.annotation.*;import java.io.IOException;
@WebServlet("/dang-xuat")public class DangXuatServlet extends HttpServlet{
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{HttpSession s=req.getSession(false);if(s!=null)s.invalidate();resp.sendRedirect(req.getContextPath()+"/dangNhap.jsp");}
}
