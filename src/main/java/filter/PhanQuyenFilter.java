package filter;
import model.TaiKhoan;import javax.servlet.*;import javax.servlet.http.*;import javax.servlet.annotation.*;import java.io.IOException;
@WebFilter("/*")public class PhanQuyenFilter implements Filter{
 @Override public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException{
 HttpServletRequest req=(HttpServletRequest)request;HttpServletResponse resp=(HttpServletResponse)response;String path=req.getServletPath();
 if(path.equals("/")||path.equals("/index.jsp")||path.equals("/dangNhap.jsp")||path.equals("/dang-nhap")||path.equals("/style.css")||path.equals("/favicon.ico")||path.equals("/loiDatabase.jsp")){chain.doFilter(request,response);return;}
 HttpSession session=req.getSession(false);TaiKhoan tk=session==null?null:(TaiKhoan)session.getAttribute("taiKhoan");
 if(tk==null){resp.sendRedirect(req.getContextPath()+"/dangNhap.jsp");return;}
 boolean ql=path.equals("/gdChinhQL.jsp")||path.equals("/gdBaoCao.jsp")||path.equals("/gdThongKeMonAn.jsp")||path.equals("/gdChiTietThongKe.jsp")||path.equals("/gdHoaDon.jsp");
 boolean kh=path.equals("/gdChinhKH.jsp")||path.equals("/gdThongTinMonAn.jsp")||path.equals("/gdThongTinChiTiet.jsp");
 if((ql&&!"QUAN_LY".equals(tk.getVaiTro()))||(kh&&!"KHACH_HANG".equals(tk.getVaiTro()))){resp.sendError(403,"Bạn không có quyền truy cập chức năng này.");return;}
 chain.doFilter(request,response);
 }
}
