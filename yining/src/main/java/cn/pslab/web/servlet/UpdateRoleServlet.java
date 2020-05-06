package cn.pslab.web.servlet;

import cn.pslab.service.RoleService;
import cn.pslab.service.impl.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateRoleServlet")
public class UpdateRoleServlet extends HttpServlet {
    private RoleService roleService=new RoleServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        int rid= Integer.parseInt(request.getParameter("rid"));
        String rname=request.getParameter("rname");
        String remark=request.getParameter("remark");
        String rli= request.getParameter("rli");
        String aeq= request.getParameter("aeq");
        String ueq= request.getParameter("ueq");
        String deq= request.getParameter("deq");
        String asub_account= request.getParameter("asub_account");
        String usub_account= request.getParameter("usub_account");
        String dsub_account= request.getParameter("dsub_account");

        if(rname!=null){
            roleService.updateRname(rid,rname);
        }else if (remark!=null){
            roleService.updateRemark(rid,remark);
        }else if (rli!=null){
            int rli1 = Integer.parseInt(rli);
            roleService.updateRli(rid,rli1);
        }else if (aeq!=null){
            int aeq1 = Integer.parseInt(aeq);
            roleService.updateAeq(rid,aeq1);
        }else if (ueq!=null){
            int ueq1 = Integer.parseInt(ueq);
            roleService.updateUeq(rid,ueq1);
        }else if (deq!=null){
            int deq1 = Integer.parseInt(deq);
            roleService.updateDeq(rid,deq1);
        }else if (asub_account!=null){
            int asub_account1 = Integer.parseInt(asub_account);
            roleService.updateAsubaccount(rid,asub_account1);
        }else if (usub_account!=null){
            int usub_account1 = Integer.parseInt(usub_account);
            roleService.updateUsubaccount(rid,usub_account1);
        }else if (dsub_account!=null){
            int dsub_account1 = Integer.parseInt(dsub_account);
            roleService.updateDsubaccount(rid,dsub_account1);
        }
        System.out.println(rid+remark+rname+asub_account+aeq+ueq+usub_account);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
