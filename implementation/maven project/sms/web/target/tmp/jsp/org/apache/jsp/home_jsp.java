package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.solent.group.project.model.*;
import java.util.List;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

 private String type;

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");


	//new webserver
	ManageSystemWS webserver = new ManageSystemWS();
	User userCheck = new User();

	//get all login info from post request from login page
	String action = (String) request.getParameter("action");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	//assign the value of the session username
	if (session.getAttribute("username") == null) {
		session.setAttribute("username", username);
	}
	//assign values to login test user if the details haven't been entered before

	userCheck.setUsername(username);
	userCheck.setPassword(password);

	String errorMessage = "";

	if (session.getAttribute("allowed") == null) {
		session.setAttribute("allowed", false);
	}


	if (session.getAttribute("allowed").equals(false)) {
		try {
			//try logging in test user
			User logCheck = webserver.logIn(userCheck);
			if (logCheck != null) {
				//log user in based on account type
				type = logCheck.getType();
				if (type.equals("ADMIN")) {
					session.setAttribute("admin_acc", webserver.logAdmin(logCheck));
				} else if (type.equals("BOARD")) {
					session.setAttribute("board_acc", webserver.logBoard(logCheck));
				} else if (type.equals("TEACHER")) {
					session.setAttribute("teacher_acc", webserver.logTeacher(logCheck));
				} else if (type.equals("PARENT")) {
					session.setAttribute("parent_acc", webserver.logParent(logCheck));
				} else if (type.equals("PUPIL")) {
					session.setAttribute("pupil_acc", webserver.logPupil(logCheck));
				}
				session.setAttribute("acct_type", type);
				session.setAttribute("allowed", true);
			} else {
				//redirect to login
				response.sendRedirect("./accessError.html");
			}
		} catch (Exception e) {
			errorMessage = "failed to validate " + e.getMessage();
		}
	}

	if ("createUser".equals(action))
	{
		type = (String) session.getAttribute("acct_type");

		//take in all create account parameters
		String create_username = request.getParameter("username_c");
		String create_password = request.getParameter("password_c");
		String create_type = request.getParameter("creation_level");
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("last_name");

		if (type.equals("ADMIN")) {
			Admin admin_acc = (Admin) session.getAttribute("admin_acc");
			//create new board with selected values
			Board created_user = new Board();
			created_user.setUsername(create_username);
			created_user.setFirst_name(fname);
			created_user.setLast_name(lname);
			created_user.setPassword(create_password);
			created_user.setType(create_type);

			webserver.createBoard(created_user, admin_acc);
		} else if (type.equals("BOARD")) {
			Board board_acc = (Board) session.getAttribute("board_acc");
			if (create_type.equals("TEACHER")) {
				//create new teacher with selected values
				Teacher created_user = new Teacher();
				created_user.setUsername(create_username);
				created_user.setFirst_name(fname);
				created_user.setLast_name(lname);
				created_user.setPassword(create_password);
				created_user.setType(create_type);
				webserver.createTeacher(created_user, board_acc);

			} else if (create_type.equals("PARENT")) {
				//create new parent with selected values
				Parent created_user = new Parent();
				created_user.setUsername(create_username);
				created_user.setFirst_name(fname);
				created_user.setLast_name(lname);
				created_user.setPassword(create_password);
				created_user.setType(create_type);
				webserver.createParent(created_user, board_acc);

			} else if (create_type.equals("PUPIL")) {
				String teacher_un = request.getParameter("teacher_link");
				String parent_un = request.getParameter("parent_link");

				List<Teacher> teacherList = board_acc.getTeacherList().getTeacherList();
				List<Parent> parentList = board_acc.getParentList().getParentList();

				//loop through each teacher until it is found with the same username as the create page
				for (Teacher teacher : teacherList){
					if (teacher.getUsername().equals(teacher_un)){
						session.setAttribute("teacher_acc", teacher);
					}
				}

				//loop through each parent until it is found with the same username as the create page
				for (Parent parent : parentList){
					if (parent.getUsername().equals(parent_un)){
						session.setAttribute("parent_acc", parent);
					}
				}

				Parent parent_acc = (Parent) session.getAttribute("parent_acc");
				Teacher teacher_acc = (Teacher) session.getAttribute("teacher_acc");

				//create new pupil with selected values
				Pupil created_user = new Pupil();
				created_user.setUsername(create_username);
				created_user.setFirst_name(fname);
				created_user.setLast_name(lname);
				created_user.setPassword(create_password);
				created_user.setType(create_type);
				webserver.createPupil(created_user, teacher_acc, parent_acc);
			}
		}
	}
	session.setAttribute("acct_type", type);


      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/main.css\">\r\n");
      out.write("  <script src=\"./scripts/main.js\"></script>\r\n");
      out.write("  <title>Homepage</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body onLoad=\"main()\">\r\n");
      out.write("\t<div id=\"wrapper\">\r\n");
      out.write("\t\t<div id=\"header\">\r\n");
      out.write("\t\t\t<h1>System Homepage</h1>\r\n");
      out.write("\t\t\t<p id=\"date\"></p>\r\n");
      out.write("\t\t\t<p id=\"time\"></p>\r\n");
      out.write("\t\t</div> <!-- /header -->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"content\">\r\n");
      out.write("\t\t\t<div id=\"nav_bar\">\r\n");
      out.write("\t\t\t\t<!-- create list for nav bar -->\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"./events.jsp\">Events</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"./report.jsp\">Report</a></li>\r\n");
      out.write("\t\t\t\t\t");
  if (type != null){
							if(type.equals("ADMIN") || type.equals("BOARD")) {
					
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><a href=\"./createUser.jsp\">Create User</a></li>\r\n");
      out.write("\t\t\t\t\t");

							}
						}
					
      out.write("\r\n");
      out.write("\t\t\t\t\t<li id=\"acct_type\"> ");
      out.print( session.getAttribute("acct_type") );
      out.write(" </li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"./login.jsp?action=logout\">Logout</a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</div> <!-- /nav_bar -->\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<p> Welcome back ");
      out.print( session.getAttribute("username") );
      out.write("</p>\r\n");
      out.write("\t\t</div> <!-- /content -->\r\n");
      out.write("\t</div> <!-- /wrapper -->\r\n");
      out.write("\t\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
