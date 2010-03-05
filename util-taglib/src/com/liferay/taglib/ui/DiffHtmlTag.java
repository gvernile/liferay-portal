/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="DiffHtmlTag.java.html"><b><i>View Source</i></b></a>
 *
 * @author Julio Camarero
 */
public class DiffHtmlTag extends IncludeTag {

	public void setDiffHtmlResults(String diffHtmlResults) {
		_diffHtmlResults = diffHtmlResults;
	}

	protected void cleanUp() {
		_diffHtmlResults = null;
	}

	protected String getPage() {
		return _PAGE;
	}

	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:diff-html:diffHtmlResults", _diffHtmlResults);
	}

	private static final String _PAGE = "/html/taglib/ui/diff_html/page.jsp";

	private String _diffHtmlResults;

}