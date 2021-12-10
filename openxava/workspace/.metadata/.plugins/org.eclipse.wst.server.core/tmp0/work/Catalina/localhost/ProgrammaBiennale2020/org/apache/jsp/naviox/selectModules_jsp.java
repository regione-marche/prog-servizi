/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.50
 * Generated at: 2021-11-05 14:03:36 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.naviox;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Iterator;
import java.util.Collection;
import org.openxava.util.Is;
import org.openxava.util.Strings;
import org.openxava.application.meta.MetaModule;

public final class selectModules_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/WEB-INF/openxava.tld", Long.valueOf(1635857799638L));
    _jspx_dependants.put("/naviox/getModulesList.jsp", Long.valueOf(1635857799702L));
    _jspx_dependants.put("/naviox/../xava/imports.jsp", Long.valueOf(1635857798499L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fxava_005fmessage_0026_005fkey_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fxava_005fmessage_0026_005fkey_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fxava_005fmessage_0026_005fkey_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write(" \n");
      out.write("\n");
      out.write("\n");
      com.openxava.naviox.Modules modules = null;
      synchronized (session) {
        modules = (com.openxava.naviox.Modules) _jspx_page_context.getAttribute("modules", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (modules == null){
          modules = new com.openxava.naviox.Modules();
          _jspx_page_context.setAttribute("modules", modules, javax.servlet.jsp.PageContext.SESSION_SCOPE);
        }
      }
      out.write('\n');
      out.write('\n');

String searchWord = request.getParameter("searchWord");
searchWord = searchWord == null?"":Strings.removeAccents(searchWord.toLowerCase()); 
Collection modulesList = null;
boolean bookmarkModules = false;

      out.write('\n');
      com.openxava.naviox.Folders folders = null;
      synchronized (session) {
        folders = (com.openxava.naviox.Folders) _jspx_page_context.getAttribute("folders", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (folders == null){
          folders = new com.openxava.naviox.Folders();
          _jspx_page_context.setAttribute("folders", folders, javax.servlet.jsp.PageContext.SESSION_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("\r\n");
 
if ("true".equals(request.getParameter("folderModules"))) {
	modulesList = folders.getFolderModules();			
}
else if ("true".equals(request.getParameter("fixedModules"))) {
	modulesList = modules.getFixedModules();
}
else if ("true".equals(request.getParameter("bookmarkModules"))) {
	modulesList = modules.getBookmarkModules();
	bookmarkModules = true;
}
else {
	modulesList = modules.getRegularModules();
}

      out.write(' ');
      out.write('\n');

String smodulesLimit = request.getParameter("modulesLimit");
int modulesLimit = smodulesLimit == null?30:Integer.parseInt(smodulesLimit); 
int counter = 0; 
boolean loadMore = false;
for (Iterator it= modulesList.iterator(); it.hasNext();) {
	if (counter == modulesLimit) {
		loadMore = true; 
		break;
	}
	MetaModule module = (MetaModule) it.next();
	String selected = module.getName().equals(modules.getCurrent(request))?"selected":""; 
	String label = module.getLabel(request.getLocale()); 
	String description = module.getDescription(request.getLocale());
	String normalizedLabel = Strings.removeAccents(label.toLowerCase()); 
	String normalizedDescription = Strings.removeAccents(description.toLowerCase());
	if (!Is.emptyString(searchWord) && !normalizedLabel.contains(searchWord) && !normalizedDescription.contains(searchWord)) continue;
	counter++;

      out.write("\n");
      out.write("\t<a href=\"");
      out.print(modules.getModuleURI(request, module));
      out.write("?init=true\" title=\"");
      out.print(description);
      out.write('"');
      out.write('>');
      out.write(' ');
      out.write("\n");
      out.write("\t<div id=\"");
      out.print(module.getName());
      out.write("_module\" class=\"module-row ");
      out.print(selected);
      out.write("\" onclick=\"$('#");
      out.print(module.getName());
      out.write("_loading').show()\">\t\n");
      out.write("\t\t<div class=\"module-name\">\n");
      out.write("\t\t\t");
      out.print(label);
      out.write("\n");
      out.write("\t\t\t");
 if (bookmarkModules) { 
      out.write("\n");
      out.write("\t\t\t<i class=\"mdi mdi-star bookmark-decoration\"></i>\n");
      out.write("\t\t\t");
 } 
      out.write("\n");
      out.write("\t\t\t<i id=\"");
      out.print(module.getName());
      out.write("_loading\" class=\"mdi mdi-autorenew module-loading spin\" style=\"float: right; display:none;\"></i>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\t\n");
      out.write("\t</a>\n");
      out.write("\t\n");
	
}

if (loadMore) {

      out.write("\n");
      out.write("\t<a  href=\"javascript:naviox.displayAllModulesList('");
      out.print(searchWord);
      out.write("')\">\n");
      out.write("\t<div id=\"more_modules\" class=\"module-row\" onclick=\"$('#loading_more_modules').show(); $('#load_more_modules').hide();\">\n");
      out.write("\t<span id=\"loading_more_modules\" style=\"display:none;\">\n");
      out.write("\t");
      if (_jspx_meth_xava_005fmessage_005f0(_jspx_page_context))
        return;
      out.write("...\n");
      out.write("\t<i class=\"mdi mdi-autorenew module-loading spin\" style=\"float: right;\"></i>\n");
      out.write("\t</span>\n");
      out.write("\t<span id=\"load_more_modules\">\n");
      out.write("\t");
      if (_jspx_meth_xava_005fmessage_005f1(_jspx_page_context))
        return;
      out.write("...\n");
      out.write("\t</span>\t\n");
      out.write("\t</div>\t\n");
      out.write("\t</a>\n");

}

    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_xava_005fmessage_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  xava:message
    org.openxava.web.taglib.MessageTag _jspx_th_xava_005fmessage_005f0 = (org.openxava.web.taglib.MessageTag) _005fjspx_005ftagPool_005fxava_005fmessage_0026_005fkey_005fnobody.get(org.openxava.web.taglib.MessageTag.class);
    _jspx_th_xava_005fmessage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_xava_005fmessage_005f0.setParent(null);
    // /naviox/selectModules.jsp(57,1) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_xava_005fmessage_005f0.setKey("loading");
    int _jspx_eval_xava_005fmessage_005f0 = _jspx_th_xava_005fmessage_005f0.doStartTag();
    if (_jspx_th_xava_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fxava_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_xava_005fmessage_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fxava_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_xava_005fmessage_005f0);
    return false;
  }

  private boolean _jspx_meth_xava_005fmessage_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  xava:message
    org.openxava.web.taglib.MessageTag _jspx_th_xava_005fmessage_005f1 = (org.openxava.web.taglib.MessageTag) _005fjspx_005ftagPool_005fxava_005fmessage_0026_005fkey_005fnobody.get(org.openxava.web.taglib.MessageTag.class);
    _jspx_th_xava_005fmessage_005f1.setPageContext(_jspx_page_context);
    _jspx_th_xava_005fmessage_005f1.setParent(null);
    // /naviox/selectModules.jsp(61,1) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_xava_005fmessage_005f1.setKey("load_more_modules");
    int _jspx_eval_xava_005fmessage_005f1 = _jspx_th_xava_005fmessage_005f1.doStartTag();
    if (_jspx_th_xava_005fmessage_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fxava_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_xava_005fmessage_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fxava_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_xava_005fmessage_005f1);
    return false;
  }
}
