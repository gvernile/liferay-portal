<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MSBFragmentCollection msbFragmentCollection = (MSBFragmentCollection)row.getObject();
%>

<liferay-ui:icon-menu direction="left-side" icon="<%= StringPool.BLANK %>" markupView="lexicon" message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">
	<c:if test="<%= MSBFragmentCollectionPermission.contains(permissionChecker, msbFragmentCollection, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editMSBFragmentCollectionURL">
			<portlet:param name="mvcPath" value="/edit_msb_fragment_collection.jsp" />
			<portlet:param name="msbFragmentCollectionId" value="<%= String.valueOf(msbFragmentCollection.getMsbFragmentCollectionId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editMSBFragmentCollectionURL %>"
		/>
	</c:if>

	<c:if test="<%= MSBFragmentCollectionPermission.contains(permissionChecker, msbFragmentCollection, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= MSBFragmentCollection.class.getName() %>"
			modelResourceDescription="<%= msbFragmentCollection.getName() %>"
			resourcePrimKey="<%= String.valueOf(msbFragmentCollection.getMsbFragmentCollectionId()) %>"
			var="msbMSBFragmentCollectionPermissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%= msbMSBFragmentCollectionPermissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= MSBFragmentCollectionPermission.contains(permissionChecker, msbFragmentCollection, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteMSBFragmentCollection" var="deleteMSBFragmentCollectionURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="msbFragmentCollectionId" value="<%= String.valueOf(msbFragmentCollection.getMsbFragmentCollectionId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteMSBFragmentCollectionURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>