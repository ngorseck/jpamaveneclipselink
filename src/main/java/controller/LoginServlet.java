package controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sn.isi.dao.IUser;
import sn.isi.dao.UserDao;
import sn.isi.entities.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/Login", name = "login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IUser userdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		userdao = new UserDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("index.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email").toString();
		String password = request.getParameter("password").toString();

		try {
			User user = userdao.getLogin(email, password);
			HttpSession session = request.getSession();
			session.setAttribute("user_session", user);
			response.sendRedirect("Accueil");
		} catch (Exception e) {
			request.setAttribute("mession_error", "Email ou mot de passe incorrect !");
			request.getRequestDispatcher("index.jsp")
			.forward(request, response);
		}
	}

}
