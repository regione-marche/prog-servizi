/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.50
 * Generated at: 2021-11-05 14:03:42 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.xava;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Collection;
import java.util.Map;
import org.openxava.model.meta.MetaReference;
import org.openxava.view.meta.MetaPropertyView;
import org.openxava.web.Ids;
import org.openxava.web.WebEditors;
import org.openxava.web.DescriptionsLists;
import org.openxava.util.XavaPreferences;
import org.openxava.util.Is;

public final class reference_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(5);
    _jspx_dependants.put("/xava/htmlTagsEditor.jsp", Long.valueOf(1635857798381L));
    _jspx_dependants.put("/xava/imports.jsp", Long.valueOf(1635857798499L));
    _jspx_dependants.put("/xava/referenceActionsExt.jsp", Long.valueOf(1568814583120L));
    _jspx_dependants.put("/WEB-INF/openxava.tld", Long.valueOf(1635857799638L));
    _jspx_dependants.put("/xava/referenceActions.jsp", Long.valueOf(1635857798775L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fxava_005fid_0026_005fname_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fxava_005faction_0026_005faction_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fxava_005fid_0026_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fxava_005faction_0026_005faction_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fxava_005fid_0026_005fname_005fnobody.release();
    _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody.release();
    _005fjspx_005ftagPool_005fxava_005faction_0026_005faction_005fnobody.release();
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write(" \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      org.openxava.util.Messages errors = null;
      errors = (org.openxava.util.Messages) _jspx_page_context.getAttribute("errors", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (errors == null){
        errors = new org.openxava.util.Messages();
        _jspx_page_context.setAttribute("errors", errors, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write('\n');
      org.openxava.controller.ModuleContext context = null;
      synchronized (session) {
        context = (org.openxava.controller.ModuleContext) _jspx_page_context.getAttribute("context", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (context == null){
          context = new org.openxava.controller.ModuleContext();
          _jspx_page_context.setAttribute("context", context, javax.servlet.jsp.PageContext.SESSION_SCOPE);
        }
      }
      out.write('\n');
      org.openxava.web.style.Style style = null;
      style = (org.openxava.web.style.Style) _jspx_page_context.getAttribute("style", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (style == null){
        style = new org.openxava.web.style.Style();
        _jspx_page_context.setAttribute("style", style, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write('\n');
      out.write('\n');

boolean onlyEditor = "true".equalsIgnoreCase(request.getParameter("onlyEditor"));
boolean frame = "true".equalsIgnoreCase(request.getParameter("frame")); 
boolean composite = "true".equalsIgnoreCase(request.getParameter("composite"));
boolean descriptionsList = "true".equalsIgnoreCase(request.getParameter("descriptionsList"));
String viewObject = request.getParameter("viewObject");
viewObject = (viewObject == null || viewObject.equals(""))?"xava_view":viewObject;
org.openxava.view.View view = (org.openxava.view.View) context.get(request, viewObject);
String referenceKey = request.getParameter("referenceKey");
MetaReference ref = (MetaReference) request.getAttribute(referenceKey);
String refViewObject = request.getParameter("refViewObject");
if (Is.emptyString(refViewObject)) refViewObject = viewObject; 
String labelKey = "xava_label_" + referenceKey;
if (!descriptionsList) descriptionsList = view.displayAsDescriptionsList(ref);
boolean descriptionsListAndReferenceView = descriptionsList || !composite?false:view.displayAsDescriptionsListAndReferenceView(ref);
if (descriptionsListAndReferenceView) {
	composite = false;
}

      out.write('\n');
      out.write('\n');

String sfirst = request.getParameter("first"); 
boolean first="true".equals(sfirst)?true:false;

String labelClass = null;
String editorClass = null;

if (view.isAlignedByColumns()) {
	labelClass = editorClass = "ox-layout-aligned-cell";
}
else {
	editorClass = "ox-layout-not-aligned-cell";
	labelClass = first?"ox-layout-aligned-cell":"ox-layout-not-aligned-cell";
}

String preLabel="<div class='" + labelClass + " " + style.getLabel() + "'>";
String postLabel="</div>";
String preEditor="<div class='" + editorClass + " " + style.getEditorWrapper()+ "'>";
String postEditor="</div>";

      out.write('\n');
      out.write('\n');

String editableKey = referenceKey + "_EDITABLE_";
boolean editable = view.isEditable(ref.getName()); 
int labelFormat = view.getLabelFormatForReference(ref);
String labelStyle = view.getLabelStyleForReference(ref);
if (Is.empty(labelStyle)) labelStyle = XavaPreferences.getInstance().getDefaultLabelStyle();
String label = ref.getLabel(request);

      out.write('\n');
      out.write('\n');
 if (view.isFlowLayout()) { 
      out.write(" \n");
      out.write("<div class='");
      out.print(frame?"ox-flow-layout":"");
      out.write('\'');
      out.write('>');
      out.write('\n');
 } 
      out.write('\n');
      out.write('\n');
 if (!onlyEditor) { 
      out.write('\n');
      out.print(preLabel);
      out.write('\n');
 if (labelFormat == MetaPropertyView.NORMAL_LABEL) { 
      out.write("\n");
      out.write("<span id=\"");
      //  xava:id
      org.openxava.web.taglib.IdTag _jspx_th_xava_005fid_005f0 = (org.openxava.web.taglib.IdTag) _005fjspx_005ftagPool_005fxava_005fid_0026_005fname_005fnobody.get(org.openxava.web.taglib.IdTag.class);
      _jspx_th_xava_005fid_005f0.setPageContext(_jspx_page_context);
      _jspx_th_xava_005fid_005f0.setParent(null);
      // /xava/reference.jsp(55,10) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_xava_005fid_005f0.setName("label_" + view.getPropertyPrefix() + ref.getName());
      int _jspx_eval_xava_005fid_005f0 = _jspx_th_xava_005fid_005f0.doStartTag();
      if (_jspx_th_xava_005fid_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fxava_005fid_0026_005fname_005fnobody.reuse(_jspx_th_xava_005fid_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fxava_005fid_0026_005fname_005fnobody.reuse(_jspx_th_xava_005fid_005f0);
      out.write("\" class=\"");
      out.print( labelStyle);
      out.write('"');
      out.write('>');
      out.write('\n');
      out.print(label);
      out.write("\n");
      out.write("</span>\n");
 } 
      out.write('\n');
      out.print(postLabel);
      out.write('\n');
      out.print(preEditor);
      out.write('\n');
 
if (labelFormat == MetaPropertyView.SMALL_LABEL) { 

      out.write("\n");
      out.write("<span id='");
      //  xava:id
      org.openxava.web.taglib.IdTag _jspx_th_xava_005fid_005f1 = (org.openxava.web.taglib.IdTag) _005fjspx_005ftagPool_005fxava_005fid_0026_005fname_005fnobody.get(org.openxava.web.taglib.IdTag.class);
      _jspx_th_xava_005fid_005f1.setPageContext(_jspx_page_context);
      _jspx_th_xava_005fid_005f1.setParent(null);
      // /xava/reference.jsp(64,10) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_xava_005fid_005f1.setName("label_" + view.getPropertyPrefix() + ref.getName());
      int _jspx_eval_xava_005fid_005f1 = _jspx_th_xava_005fid_005f1.doStartTag();
      if (_jspx_th_xava_005fid_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fxava_005fid_0026_005fname_005fnobody.reuse(_jspx_th_xava_005fid_005f1);
        return;
      }
      _005fjspx_005ftagPool_005fxava_005fid_0026_005fname_005fnobody.reuse(_jspx_th_xava_005fid_005f1);
      out.write("' class=\"");
      out.print(style.getSmallLabel());
      out.write(' ');
      out.print(labelStyle );
      out.write('"');
      out.write('>');
      out.write('\n');
      out.print(label);
      out.write("\n");
      out.write("</span>\n");
      out.write("<br/> \n");
 } 
      out.write('\n');
      out.write('\n');
 } // !onlyEditor 
      out.write('\n');

Collection keys = ref.getMetaModelReferenced().getAllKeyPropertiesNames(); 
String keyProperty = "";
String keyProperties = "";
String propertyKey = null;
if (keys.size() == 1) {
	keyProperty = keys.iterator().next().toString();
	propertyKey = Ids.decorate(request, referenceKey + "." + keyProperty);
	if (!composite) { 
		Map values = (Map) view.getValue(ref.getName());
		values = values == null?java.util.Collections.EMPTY_MAP:values;
		Object value = values.get(keyProperty);
		String valueKey = propertyKey + ".value";
		request.setAttribute(valueKey, value);		
		String fvalue = value==null?"":value.toString();
		request.setAttribute(propertyKey + ".fvalue", fvalue);
	}
}
else {	
	propertyKey = referenceKey + DescriptionsLists.COMPOSITE_KEY_SUFFIX; 
	Map values = null; 
	if (!composite) { 
		values = (Map) view.getValue(ref.getName());
		values = values == null?java.util.Collections.EMPTY_MAP:values;
	}
	java.util.Iterator it = keys.iterator();
	StringBuffer sb = new StringBuffer();
	while (it.hasNext()) {
		String property = (String) it.next();
		if (!composite) { 
			Object value = values.get(property);
			String valueKey = Ids.decorate(request, referenceKey + "." + property) + ".value"; 
			request.setAttribute(valueKey, value);
		}
		sb.append(property);
		if (it.hasNext()) {
			sb.append(',');
		}
	}
	if (!composite) { 
		String key = ref.getMetaModelReferenced().toString(values); 
		String fvalue = key==null?"0":key;
		request.setAttribute(propertyKey + ".fvalue", fvalue);
	}
	keyProperties = sb.toString();
}

boolean throwChanged=view.throwsReferenceChanged(ref);
String script = throwChanged?
	"onchange='openxava.throwPropertyChanged(\"" + 
			request.getParameter("application") + "\", \"" + 
			request.getParameter("module") + "\", \"" +			
			propertyKey + "\")'":"";

      out.write('\n');
      out.write('\n');
 if (!composite) { 
      out.write('\n');
 String required = view.isEditable() && ref.isRequired() ? "class='" + style.getRequiredEditor() + "'":""; 
      out.write("\n");
      out.write("<span id=\"");
      //  xava:id
      org.openxava.web.taglib.IdTag _jspx_th_xava_005fid_005f2 = (org.openxava.web.taglib.IdTag) _005fjspx_005ftagPool_005fxava_005fid_0026_005fname_005fnobody.get(org.openxava.web.taglib.IdTag.class);
      _jspx_th_xava_005fid_005f2.setPageContext(_jspx_page_context);
      _jspx_th_xava_005fid_005f2.setParent(null);
      // /xava/reference.jsp(128,10) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_xava_005fid_005f2.setName("reference_editor_" + view.getPropertyPrefix() + ref.getName());
      int _jspx_eval_xava_005fid_005f2 = _jspx_th_xava_005fid_005f2.doStartTag();
      if (_jspx_th_xava_005fid_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fxava_005fid_0026_005fname_005fnobody.reuse(_jspx_th_xava_005fid_005f2);
        return;
      }
      _005fjspx_005ftagPool_005fxava_005fid_0026_005fname_005fnobody.reuse(_jspx_th_xava_005fid_005f2);
      out.write('"');
      out.write(' ');
      out.print(required);
      out.write('>');
      out.write('\n');
 } 
      out.write(' ');
      out.write('\n');
 boolean notCompositeEditorClosed = false; 
      out.write("\n");
      out.write("<input type=\"hidden\" name=\"");
      out.print(editableKey);
      out.write("\" value=\"");
      out.print(editable);
      out.write("\"/>\n");
      out.write("\n");

if (descriptionsList || descriptionsListAndReferenceView) { 	
	String descriptionProperty = view.getDescriptionPropertyInDescriptionsList(ref);
	String descriptionProperties = view.getDescriptionPropertiesInDescriptionsList(ref);
	String parameterValuesProperties=view.getParameterValuesPropertiesInDescriptionsList(ref);
	String condition = view.getConditionInDescriptionsList(ref);
	boolean orderByKey = view.isOrderByKeyInDescriptionsList(ref);
	String order = view.getOrderInDescriptionsList(ref); 
	org.openxava.tab.meta.MetaTab metaTab = ref.getMetaModelReferenced().getMetaComponent().getMetaTab();
	String filter = "";
	if (metaTab.hasFilter()) {
		filter = metaTab.getMetaFilter().getClassName(); 
	}
	if (metaTab.hasBaseCondition()) {
		if (org.openxava.util.Is.emptyString(condition)) {
			condition = metaTab.getBaseCondition();
		}
		else {
			condition = metaTab.getBaseCondition() + " AND " + condition;
		}
	}	

      out.write('\n');
      out.write('	');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "editors/descriptionsEditor.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("script", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(script), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("propertyKey", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(propertyKey), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("editable", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(editable), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("model", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(ref.getReferencedModelName()), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("keyProperty", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(keyProperty), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("keyProperties", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(keyProperties), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("descriptionProperty", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(descriptionProperty), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("descriptionProperties", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(descriptionProperties), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("parameterValuesProperties", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(parameterValuesProperties), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("condition", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(condition), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("orderByKey", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(orderByKey), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("order", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(order), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("filter", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(filter), request.getCharacterEncoding()), out, false);
      out.write('	');
      out.write('\n');
      out.write('	');

	if (descriptionsListAndReferenceView) { 
	
      out.write('\n');
      out.write('	');
      out.write('	');

String keyPropertyForAction = Ids.undecorate(propertyKey);  

if (editable && view.isCreateNewForReference(ref)) {  

      out.write('\r');
      out.write('\n');
      out.write('	');
      //  xava:action
      org.openxava.web.taglib.ActionTag _jspx_th_xava_005faction_005f0 = (org.openxava.web.taglib.ActionTag) _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody.get(org.openxava.web.taglib.ActionTag.class);
      _jspx_th_xava_005faction_005f0.setPageContext(_jspx_page_context);
      _jspx_th_xava_005faction_005f0.setParent(null);
      // /xava/referenceActions.jsp(6,1) name = action type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_xava_005faction_005f0.setAction("Reference.createNew");
      // /xava/referenceActions.jsp(6,1) name = argv type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_xava_005faction_005f0.setArgv("model="+ref.getReferencedModelName() + ",keyProperty=" + keyPropertyForAction);
      int _jspx_eval_xava_005faction_005f0 = _jspx_th_xava_005faction_005f0.doStartTag();
      if (_jspx_th_xava_005faction_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody.reuse(_jspx_th_xava_005faction_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody.reuse(_jspx_th_xava_005faction_005f0);
      out.write('\r');
      out.write('\n');

}

if (editable && view.isModifyForReference(ref)) { 

      out.write('\r');
      out.write('\n');
      out.write('	');
      //  xava:action
      org.openxava.web.taglib.ActionTag _jspx_th_xava_005faction_005f1 = (org.openxava.web.taglib.ActionTag) _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody.get(org.openxava.web.taglib.ActionTag.class);
      _jspx_th_xava_005faction_005f1.setPageContext(_jspx_page_context);
      _jspx_th_xava_005faction_005f1.setParent(null);
      // /xava/referenceActions.jsp(12,1) name = action type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_xava_005faction_005f1.setAction("Reference.modify");
      // /xava/referenceActions.jsp(12,1) name = argv type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_xava_005faction_005f1.setArgv("model="+ref.getReferencedModelName() + ",keyProperty=" + keyPropertyForAction);
      int _jspx_eval_xava_005faction_005f1 = _jspx_th_xava_005faction_005f1.doStartTag();
      if (_jspx_th_xava_005faction_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody.reuse(_jspx_th_xava_005faction_005f1);
        return;
      }
      _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody.reuse(_jspx_th_xava_005faction_005f1);
      out.write('\r');
      out.write('\n');

}

java.util.Iterator itActions = view.getActionsNamesForReference(ref, editable).iterator(); 
while (itActions.hasNext()) {
	String action = (String) itActions.next();

      out.write('\r');
      out.write('\n');
      out.write('	');
      //  xava:action
      org.openxava.web.taglib.ActionTag _jspx_th_xava_005faction_005f2 = (org.openxava.web.taglib.ActionTag) _005fjspx_005ftagPool_005fxava_005faction_0026_005faction_005fnobody.get(org.openxava.web.taglib.ActionTag.class);
      _jspx_th_xava_005faction_005f2.setPageContext(_jspx_page_context);
      _jspx_th_xava_005faction_005f2.setParent(null);
      // /xava/referenceActions.jsp(20,1) name = action type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_xava_005faction_005f2.setAction(action);
      int _jspx_eval_xava_005faction_005f2 = _jspx_th_xava_005faction_005f2.doStartTag();
      if (_jspx_th_xava_005faction_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fxava_005faction_0026_005faction_005fnobody.reuse(_jspx_th_xava_005faction_005f2);
        return;
      }
      _005fjspx_005ftagPool_005fxava_005faction_0026_005faction_005fnobody.reuse(_jspx_th_xava_005faction_005f2);
      out.write('\r');
      out.write('\n');

}

      out.write('\r');
      out.write('\n');
      out.write('\n');
      out.write('	');

		notCompositeEditorClosed = true;
	
      out.write("\n");
      out.write("\t</span>\n");
      out.write("\t\n");
      out.write("\t");
 
	String editorURL = "editors/" + WebEditors.getMetaEditorFor(ref, view.getViewName()).getUrl()
		+ "?script=" + script
		+ "&propertyKey=" + propertyKey
		+ "&viewObject=" + refViewObject 
		+ "&editable=false";
	
      out.write('\n');
      out.write('	');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, editorURL, out, false);
      out.write('	');
      out.write('\n');
      out.write('	');
 
	} 
	
      out.write('\n');

}
else {
	String editorURL = "editors/" + WebEditors.getMetaEditorFor(ref, view.getViewName()).getUrl()
		+ "?script=" + script
		+ "&propertyKey=" + propertyKey
		+ "&viewObject=" + refViewObject 
		+ "&editable=" + editable;

      out.write('\n');
      out.write('	');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, editorURL, out, false);
      out.write('\n');
	
}

      out.write('\n');
      out.write('\n');
 if (!frame) { 
      out.write('\n');
      out.write('	');

String keyPropertyForAction = Ids.undecorate(propertyKey);  

if (editable && view.isCreateNewForReference(ref)) {  

      out.write('\r');
      out.write('\n');
      out.write('	');
      //  xava:action
      org.openxava.web.taglib.ActionTag _jspx_th_xava_005faction_005f3 = (org.openxava.web.taglib.ActionTag) _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody.get(org.openxava.web.taglib.ActionTag.class);
      _jspx_th_xava_005faction_005f3.setPageContext(_jspx_page_context);
      _jspx_th_xava_005faction_005f3.setParent(null);
      // /xava/referenceActions.jsp(6,1) name = action type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_xava_005faction_005f3.setAction("Reference.createNew");
      // /xava/referenceActions.jsp(6,1) name = argv type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_xava_005faction_005f3.setArgv("model="+ref.getReferencedModelName() + ",keyProperty=" + keyPropertyForAction);
      int _jspx_eval_xava_005faction_005f3 = _jspx_th_xava_005faction_005f3.doStartTag();
      if (_jspx_th_xava_005faction_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody.reuse(_jspx_th_xava_005faction_005f3);
        return;
      }
      _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody.reuse(_jspx_th_xava_005faction_005f3);
      out.write('\r');
      out.write('\n');

}

if (editable && view.isModifyForReference(ref)) { 

      out.write('\r');
      out.write('\n');
      out.write('	');
      //  xava:action
      org.openxava.web.taglib.ActionTag _jspx_th_xava_005faction_005f4 = (org.openxava.web.taglib.ActionTag) _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody.get(org.openxava.web.taglib.ActionTag.class);
      _jspx_th_xava_005faction_005f4.setPageContext(_jspx_page_context);
      _jspx_th_xava_005faction_005f4.setParent(null);
      // /xava/referenceActions.jsp(12,1) name = action type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_xava_005faction_005f4.setAction("Reference.modify");
      // /xava/referenceActions.jsp(12,1) name = argv type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_xava_005faction_005f4.setArgv("model="+ref.getReferencedModelName() + ",keyProperty=" + keyPropertyForAction);
      int _jspx_eval_xava_005faction_005f4 = _jspx_th_xava_005faction_005f4.doStartTag();
      if (_jspx_th_xava_005faction_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody.reuse(_jspx_th_xava_005faction_005f4);
        return;
      }
      _005fjspx_005ftagPool_005fxava_005faction_0026_005fargv_005faction_005fnobody.reuse(_jspx_th_xava_005faction_005f4);
      out.write('\r');
      out.write('\n');

}

java.util.Iterator itActions = view.getActionsNamesForReference(ref, editable).iterator(); 
while (itActions.hasNext()) {
	String action = (String) itActions.next();

      out.write('\r');
      out.write('\n');
      out.write('	');
      //  xava:action
      org.openxava.web.taglib.ActionTag _jspx_th_xava_005faction_005f5 = (org.openxava.web.taglib.ActionTag) _005fjspx_005ftagPool_005fxava_005faction_0026_005faction_005fnobody.get(org.openxava.web.taglib.ActionTag.class);
      _jspx_th_xava_005faction_005f5.setPageContext(_jspx_page_context);
      _jspx_th_xava_005faction_005f5.setParent(null);
      // /xava/referenceActions.jsp(20,1) name = action type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_xava_005faction_005f5.setAction(action);
      int _jspx_eval_xava_005faction_005f5 = _jspx_th_xava_005faction_005f5.doStartTag();
      if (_jspx_th_xava_005faction_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fxava_005faction_0026_005faction_005fnobody.reuse(_jspx_th_xava_005faction_005f5);
        return;
      }
      _005fjspx_005ftagPool_005fxava_005faction_0026_005faction_005fnobody.reuse(_jspx_th_xava_005faction_005f5);
      out.write('\r');
      out.write('\n');

}

      out.write('\r');
      out.write('\n');
      out.write('\n');
      out.write('	');
      out.write('\n');
 } 
      out.write('\n');
      out.write('\n');
 if (!composite && !notCompositeEditorClosed) { 
      out.write(" \n");
      out.write("</span>\n");
 }
if (!onlyEditor) {

      out.write('	');
      out.write('\n');
      out.write('	');
      out.print(postEditor);
      out.write('\n');
}
      out.write('\n');
      out.write('\n');
 if (view.isFlowLayout()) { 
      out.write(" \n");
      out.write("</div>  \n");
 } 
      out.write('\n');
      out.write('\n');
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
}
