package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class LogoutControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LogoutControl() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession();
		String action = request.getParameter("action");
		
		if(action != null) {
			if(action.equalsIgnoreCase("Logout")){
				if(sessione.getAttribute("UtenteLoggato") != null){
					sessione.removeAttribute("UtenteLoggato");
				}else if(sessione.getAttribute("AdminLoggato") != null)
					sessione.removeAttribute("AdminLoggato");
				}
				
				//request.getSession().invalidate();
				response.sendRedirect("Homepage.jsp");
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
