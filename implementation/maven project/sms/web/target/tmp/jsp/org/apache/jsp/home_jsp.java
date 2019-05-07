package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.solent.group.project.model.ManageSystemWS;
import org.solent.group.project.model.User;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

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

      out.write('\r');
      out.write('\n');


	ManageSystemWS webserver = new ManageSystemWS();
	User userCheck = new User();

	String action = (String) request.getParameter("action");
	String school = request.getParameter("school_name");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	String account = (String) request.getParameter("access_level");
	
	if (school != null && username != null && password != null) {
		session.setAttribute("school_name", school);
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		
		//test variable for login checks on other jsps for now
		session.setAttribute("acct_type", account);
	}

	userCheck.setUsername(username);
	userCheck.setPassword(password);

	String errorMessage = "";
	Boolean allowed = false;
	
	
	if ("validateLogin".equals(action))
	{
		try {
			User validated = webserver.logIn(userCheck);
			if (validated == null)
			{
				allowed = false;
				response.sendRedirect("./accessError.html");
			}
			else {
				allowed = true;
			}
		}
		catch (Exception e){
			errorMessage = "failed to validate " + e.getMessage();
		}
	}
	else if ("createUser".equals(action))
	{
		
		
	}
	
	

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
 if(account.equals("Admin") || account.equals("Board") || account.equals("Teacher")) {
					
      out.write("\r\n");
      out.write("\t\t\t\t\t<li><a href=\"./createUser.jsp\">Create User</a></li>\r\n");
      out.write("\t\t\t\t\t");

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
      out.write("\t\t\t\r\n");
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
