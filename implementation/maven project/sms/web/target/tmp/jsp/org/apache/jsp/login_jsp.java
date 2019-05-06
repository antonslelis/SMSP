package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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

	
	/*
	ManageSystemWS webserver = new ManageSystemWS();
	User newUser = new User();

	*/
	
	
	
	String school = request.getParameter("school_name");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String account = request.getParameter("access_level");


	session.setAttribute("acct_type", account);
	session.setAttribute("school_name", school);
	
	String action = (String) request.getParameter("action");
	
	//clears session when logout is pressed
	if ("logout".equals(action))
	{
		session.invalidate();
	}
	
	/*
	newUser.setUsername(username);
	newUser.setPassword(password);

	String errorMessage = "";

	if ("signUpUser".equals(action))
	{
		try {
			webserver.createUser(newUser);
		}
		catch (Exception e){
			errorMessage = "failed to create " + e.getMessage();
		}
	}
	*/

	
	//temp school list for tests
	String[] school_list = {"school 1", "school 2", "school 3"};

	


      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/login.css\">\r\n");
      out.write("  <title>Login</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<h1>Please enter your login details.</h1>\r\n");
      out.write("\r\n");
      out.write("\t<form id=\"login_form\" action=\"./home.jsp\" method=\"post\">\r\n");
      out.write("\t\t<fieldset id=\"login\">\r\n");
      out.write("\t\t\t<label id=\"school_label\">School Name:</label>\r\n");
      out.write("\t\t\t<select name=\"school_name\">\r\n");
      out.write("\t\t\t\t");

					for (int i=0; i<school_list.length; i++)
					{
						
				
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<option value=\"");
      out.print( school_list[i] );
      out.write('"');
      out.write('>');
      out.print( school_list[i] );
      out.write("</option>\r\n");
      out.write("\t\t\t\t");

					}
				
      out.write("\r\n");
      out.write("\t\t\t</select>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t<label id=\"username_label\">Username:</label>\r\n");
      out.write("\t\t\t<input type=\"text\" name=\"username\"><br/>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<label id=\"password_label\">Password:</label>\r\n");
      out.write("\t\t\t<input type=\"password\" name=\"password\"></input><br/>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<label id=\"access_label\">Access Level:</label>\r\n");
      out.write("\t\t\t<select name=\"access_level\">\r\n");
      out.write("\t\t\t\t<option value=\"Admin\">Admin</option>\r\n");
      out.write("\t\t\t\t<option value=\"Board\">Board</option>\r\n");
      out.write("\t\t\t\t<option value=\"Teacher\">Teacher</option>\r\n");
      out.write("\t\t\t\t<option value=\"Parent\">Parent</option>\r\n");
      out.write("\t\t\t</select>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"action\" value=\"validateLogin\"></input>\r\n");
      out.write("\t\t\t<p><input type=\"submit\" id=\"login_button\" value=\"Login\"></p>\r\n");
      out.write("\t\t</fieldset>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t\r\n");
      out.write("\t<form id=\"signup_form\" action=\"./signup.jsp\">\r\n");
      out.write("\t\t<fieldset id=\"signup\">\r\n");
      out.write("\t\t\t<p>New to SMS?</p>\r\n");
      out.write("\t\t\t<p><input type=\"submit\" id=\"signup_button\" value=\"Sign up Now!\"></p>\r\n");
      out.write("\t\t</fieldset>\r\n");
      out.write("\t</form>\r\n");
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
