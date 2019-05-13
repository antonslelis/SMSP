package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class events_jsp extends org.apache.jasper.runtime.HttpJspBase
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


	//temp activity list for tests
	String[] activity_list = {"activity 1", "activity 2", "activity 3", "activity 4", "activity 5", "activity 6", "activity 7"
			, "activity 8", "activity 9", "activity 10", "activity 11", "activity 12", "activity 13"};


	String account = (String) session.getAttribute("acct_type");
	
	String action = (String) request.getParameter("action");
	if ("createActivity".equals(action))
	{
		//Image uploadedFile = (Image) request.getParameter("image");
		String event_name = (String) request.getParameter("name");
		String comment = (String) request.getParameter("comment");
		
		//Activity activity = new Activity();
		//activity.createActivity(event_name, comment, uploadedFile);
	}
	

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/main.css\">\r\n");
      out.write("  <script src=\"./scripts/main.js\"></script>\r\n");
      out.write("  <title>Events</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body onLoad=\"main()\">\r\n");
      out.write("\t<div id=\"wrapper\">\r\n");
      out.write("\t\t<div id=\"header\">\r\n");
      out.write("\t\t\t<h1>Events page</h1>\r\n");
      out.write("\t\t\t<p id=\"date\"></p>\r\n");
      out.write("\t\t\t<p id=\"time\"></p>\r\n");
      out.write("\t\t</div> <!-- /header -->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"content_activities\">\r\n");
      out.write("\t\t\t<div id=\"nav_bar\">\r\n");
      out.write("\t\t\t\t<!-- create list for nav bar -->\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"./events.jsp\">Events</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"./report.jsp\">Report</a></li>\r\n");
      out.write("\t\t\t\t\t");
 if(account.equals("Admin") || account.equals("Board")) {
					
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
      out.write("\t\t\t");

				if(account.equals("Teacher") || account.equals("Board") || account.equals("Admin")){
			
      out.write("\r\n");
      out.write("\t\t\t\t\t<h2><a href=\"./addOrModifyActivity.jsp?action=createActivity\">Create Activity</a></h2>\r\n");
      out.write("\t\t\t");

				}
				
				for (int i=0; i < activity_list.length; i++)
				{
			
      out.write("\r\n");
      out.write("\t\t\t\t\t<h3>");
      out.print(activity_list[i]);
      out.write("</h3>\r\n");
      out.write("\t\t\t\t\t");

						if(account.equals("Board") || account.equals("Admin")){
					
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"./addOrModifyActivity.jsp?action=modifyActivity&id=");
      out.print(i+1);
      out.write("\">Edit Activity</a><br/>\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"./invoice.jsp?id=");
      out.print(i+1);
      out.write("\">Invoice</a>\r\n");
      out.write("\t\t\t\t\t");

						}
					
      out.write("\r\n");
      out.write("\t\t\t\t\t<br/>\r\n");
      out.write("\t\t\t");

				}
			
      out.write("\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div> <!-- /content_activities -->\r\n");
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
