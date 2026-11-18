package controller;
import dao.TaiKhoanDAO;import model.TaiKhoan;import javax.servlet.*;import javax.servlet.http.*;import javax.servlet.annotation.*;import java.io.IOException;
@WebServlet("/dang-nhap") public class DangNhapServlet extends HttpServlet {
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
 req.setCharacterEncoding("UTF-8");TaiKhoan tk=new TaiKhoanDAO().dangNhap(req.getParameter("username"),req.getParameter("password"));
 if(tk==null){req.setAttribute("loi","Tài khoản hoặc mật khẩu không đúng.");req.getRequestDispatcher("/dangNhap.jsp").forward(req,resp);return;}
 HttpSession old=req.getSession(false);if(old!=null)old.invalidate();HttpSession session=req.getSession(true);session.setAttribute("taiKhoan",tk);session.setMaxInactiveInterval(30*60);
 resp.sendRedirect(req.getContextPath()+("QUAN_LY".equals(tk.getVaiTro())?"/gdChinhQL.jsp":"/gdChinhKH.jsp"));}
}
