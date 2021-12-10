/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.50
 * Generated at: 2021-10-25 09:07:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.xava.editors;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.DateFormat;
import org.openxava.util.Users;
import org.openxava.util.Locales;
import org.openxava.util.XavaResources;
import org.openxava.web.editors.DiscussionComment;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Collection;
import org.openxava.model.meta.MetaProperty;
import org.openxava.web.Ids;

public final class discussionEditor_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/xava/editors/../imports.jsp", Long.valueOf(1634650700537L));
    _jspx_dependants.put("/WEB-INF/openxava.tld", Long.valueOf(1634650702756L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fxava_005flabel_0026_005fkey_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fxava_005flabel_0026_005fkey_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fxava_005flabel_0026_005fkey_005fnobody.release();
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      org.openxava.web.style.Style style = null;
      style = (org.openxava.web.style.Style) _jspx_page_context.getAttribute("style", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (style == null){
        style = new org.openxava.web.style.Style();
        _jspx_page_context.setAttribute("style", style, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write("\r\n");
      out.write("\r\n");
 
String contextPath = (String) request.getAttribute("xava.contextPath");
if (contextPath == null) contextPath = request.getContextPath();
String version = org.openxava.controller.ModuleManager.getVersion();

      out.write("\t\r\n");
      out.write("<script type='text/javascript' src='");
      out.print(contextPath);
      out.write("/dwr/interface/Discussion.js?ox=");
      out.print(version);
      out.write("'></script>\t\r\n");
      out.write("\r\n");

String propertyKey = request.getParameter("propertyKey");
MetaProperty p = (MetaProperty) request.getAttribute(propertyKey);
String discussionId = (String) request.getAttribute(propertyKey + ".value");
if (discussionId == null) discussionId = UUID.randomUUID().toString().replace("-", ""); 
boolean editable="true".equals(request.getParameter("editable"));

      out.write("\r\n");
      out.write("<div class=\"ox-discussion\" id=\"xava_comments_");
      out.print(discussionId);
      out.write("\">   \r\n");

Collection<DiscussionComment> comments = new ArrayList<DiscussionComment>(DiscussionComment.findByDiscussion(discussionId));
DiscussionComment templateComment = new DiscussionComment();
templateComment.setUserName(Users.getCurrent());
comments.add(templateComment);

for (DiscussionComment comment: comments) {
		String hidden = comment.getId() == null?"style='display:none;'":"";
		String formattedTime = comment.getTime()==null?XavaResources.getString(request, "now"):DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locales.getCurrent()).format(comment.getTime());

      out.write("\r\n");
      out.write("\t<div class=\"ox-discussion-comment\" ");
      out.print(hidden);
      out.write("> \r\n");
      out.write("\t\t<div class=\"ox-discussion-comment-header\"><span class=\"ox-discussion-comment-author\">");
      out.print(comment.getUserName());
      out.write("</span> - ");
      out.print(formattedTime);
      out.write("</div>\r\n");
      out.write("\t\t<div class=\"ox-discussion-comment-content\">");
      out.print(comment.getComment());
      out.write("</div>  \r\n");
      out.write("\t</div>\r\n");

}

      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
 if (editable) { 
      out.write("\r\n");
      out.write("<textarea id=\"xava_new_comment_");
      out.print(discussionId);
      out.write("\" class=\"ox-simple-ckeditor xava-new-comment\" tabindex=\"1\"></textarea>\r\n");
      out.write("\r\n");
      out.write("<div id=\"xava_new_comment_");
      out.print(discussionId);
      out.write("_buttons\" class=\"ox-discussion-post-button\">\r\n");
      out.write("\t<input type=\"button\" tabindex=\"1\" class=\"");
      out.print(style.getButton());
      out.write("\" style=\"display: none;\" \r\n");
      out.write("\t\tonclick=\"discussionEditor.postMessage('");
      out.print(request.getParameter("application"));
      out.write("', '");
      out.print(request.getParameter("module"));
      out.write("', '");
      out.print(discussionId);
      out.write("')\" value=\"");
      if (_jspx_meth_xava_005flabel_005f0(_jspx_page_context))
        return;
      out.write("\"/>\r\n");
      out.write("\t<input type=\"button\" tabindex=\"1\" class=\"");
      out.print(style.getButton());
      out.write("\" style=\"display: none;\" \r\n");
      out.write("\t\tonclick=\"discussionEditor.cancel('");
      out.print(discussionId);
      out.write("')\" \r\n");
      out.write("\t\tvalue=\"");
      if (_jspx_meth_xava_005flabel_005f1(_jspx_page_context))
        return;
      out.write("\"/>\r\n");
      out.write("</div>\r\n");
 } 
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("<input id=\"");
      out.print(propertyKey);
      out.write("\" type=\"hidden\" name=\"");
      out.print(propertyKey);
      out.write("\" value=\"");
      out.print(discussionId);
      out.write('"');
      out.write('>');
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

  private boolean _jspx_meth_xava_005flabel_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  xava:label
    org.openxava.web.taglib.LabelTag _jspx_th_xava_005flabel_005f0 = (org.openxava.web.taglib.LabelTag) _005fjspx_005ftagPool_005fxava_005flabel_0026_005fkey_005fnobody.get(org.openxava.web.taglib.LabelTag.class);
    _jspx_th_xava_005flabel_005f0.setPageContext(_jspx_page_context);
    _jspx_th_xava_005flabel_005f0.setParent(null);
    // /xava/editors/discussionEditor.jsp(55,152) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_xava_005flabel_005f0.setKey("addComment");
    int _jspx_eval_xava_005flabel_005f0 = _jspx_th_xava_005flabel_005f0.doStartTag();
    if (_jspx_th_xava_005flabel_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fxava_005flabel_0026_005fkey_005fnobody.reuse(_jspx_th_xava_005flabel_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fxava_005flabel_0026_005fkey_005fnobody.reuse(_jspx_th_xava_005flabel_005f0);
    return false;
  }

  private boolean _jspx_meth_xava_005flabel_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  xava:label
    org.openxava.web.taglib.LabelTag _jspx_th_xava_005flabel_005f1 = (org.openxava.web.taglib.LabelTag) _005fjspx_005ftagPool_005fxava_005flabel_0026_005fkey_005fnobody.get(org.openxava.web.taglib.LabelTag.class);
    _jspx_th_xava_005flabel_005f1.setPageContext(_jspx_page_context);
    _jspx_th_xava_005flabel_005f1.setParent(null);
    // /xava/editors/discussionEditor.jsp(58,9) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_xava_005flabel_005f1.setKey("cancel");
    int _jspx_eval_xava_005flabel_005f1 = _jspx_th_xava_005flabel_005f1.doStartTag();
    if (_jspx_th_xava_005flabel_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fxava_005flabel_0026_005fkey_005fnobody.reuse(_jspx_th_xava_005flabel_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fxava_005flabel_0026_005fkey_005fnobody.reuse(_jspx_th_xava_005flabel_005f1);
    return false;
  }
}
