package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import org.solent.group.project.model.*;

public final class activities_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


	private List<Activity> activityList;
	private List<Pupil> pupilList; 
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
      out.write('\r');
      out.write('\n');


	String account = (String) session.getAttribute("acct_type");
	
	String action = (String) request.getParameter("action");
    Pupil pupil_acc = (Pupil) session.getAttribute("pupil_acc");

    ActivityList activityList = pupil_acc.getPersonalActivities();
	if ("createActivity".equals(action))
	{
		String task = (String) request.getParameter("name");
		String comment = (String) request.getParameter("comment");
		String author = (String) session.getAttribute("username");
        String free = request.getParameter("free");

		try {
		    Activity activity = new Activity();
		    if (free.equals("true")) {
                activity.setFree(true);
            }
		    else{
		        activity.setFree(false);
            }
		    activity.setAuthor(author);
		    activity.setPupilComment(comment);
		    activity.setTask(task);

		    pupil_acc.createActivity(activity);

        }
		catch (Exception e) {
            String errorMessage = "failed to validate " + e.getMessage();
    }

	}



      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/main.css\">\r\n");
      out.write("  <script src=\"./scripts/main.js\"></script>\r\n");
      out.write("  <title>Activities</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body onLoad=\"main()\">\r\n");
      out.write("\t<div id=\"wrapper\">\r\n");
      out.write("\t\t<div id=\"header\">\r\n");
      out.write("\t\t\t<h1>Activities page</h1>\r\n");
      out.write("\t\t\t<p id=\"date\"></p>\r\n");
      out.write("\t\t\t<p id=\"time\"></p>\r\n");
      out.write("\t\t</div> <!-- /header -->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"content_members\">\r\n");
      out.write("\t\t\t<div id=\"nav_bar\">\r\n");
      out.write("\t\t\t\t<!-- create list for nav bar -->\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"./home.jsp\">Home</a></li>\r\n");
      out.write("\t\t\t\t\t");
 if(account.equals("BOARD")) {
					
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
      out.write("\t\t\t</div> <!-- /nav_bar -->\r\n");
      out.write("\r\n");
      out.write("\t\t\t<h3><a href=\"addOrModifyActivity.jsp?action=createActivity\">Create activity</a></h3>\r\n");
      out.write("\t\t\t");

				for (int i = 0; i < activityList.getListSize(); i++){
					Activity activity = activityList.getActivitybyId(i);
			
      out.write("\r\n");
      out.write("\t\t\t\t<h3 id=\"");
      out.print(activity);
      out.write('"');
      out.write('>');
      out.print( activity.getTask() );
      out.write("</h3>\r\n");
      out.write("\t\t\t    <h3><a href=\"addOrModifyActivity.jsp?action=modifyActivity&id=");
      out.print( i );
      out.write("\">Modify activity</a></h3>\r\n");
      out.write("\r\n");
      out.write("\t\t\t");

                if (!activity.isFree()){
            
      out.write("\r\n");
      out.write("                    <a href=\"invoice.jsp?id=");
      out.print(activity.getActId());
      out.write("\">Invoice</a>\r\n");
      out.write("            ");

                    }
                }
			
      out.write("\r\n");
      out.write("\r\n");
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
