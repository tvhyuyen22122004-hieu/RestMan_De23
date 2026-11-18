<%@ page errorPage="loiDatabase.jsp" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ page import="model.TaiKhoan" %><%
TaiKhoan tk=(TaiKhoan)session.getAttribute("taiKhoan");response.sendRedirect(request.getContextPath()+(tk==null?"/dangNhap.jsp":("QUAN_LY".equals(tk.getVaiTro())?"/gdChinhQL.jsp":"/gdChinhKH.jsp")));
%>
