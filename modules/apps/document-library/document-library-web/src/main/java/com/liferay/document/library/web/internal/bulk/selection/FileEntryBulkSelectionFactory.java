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

package com.liferay.document.library.web.internal.bulk.selection;

import com.liferay.bulk.selection.BulkSelection;
import com.liferay.bulk.selection.BulkSelectionFactory;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.repository.RepositoryProvider;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.portal.kernel.repository.model.FileEntry",
	service = BulkSelectionFactory.class
)
public class FileEntryBulkSelectionFactory
	implements BulkSelectionFactory<FileEntry> {

	public BulkSelection<FileEntry> create(Map<String, String[]> parameterMap) {
		if (!parameterMap.containsKey("rowIdsFileEntry")) {
			throw new IllegalArgumentException();
		}

		String[] values = parameterMap.get("rowIdsFileEntry");

		if (values.length > 1) {
			return _getFileEntrySelection(values);
		}

		String value = values[0];

		if (!value.startsWith("all:")) {
			return _getFileEntrySelection(values);
		}

		if (!parameterMap.containsKey("repositoryId")) {
			throw new IllegalArgumentException();
		}

		String[] repositoryIds = parameterMap.get("repositoryId");

		long repositoryId = GetterUtil.getLong(repositoryIds[0]);

		if (repositoryId == 0) {
			throw new IllegalArgumentException();
		}

		long folderId = GetterUtil.getLong(value.substring(4));

		return new FolderFileEntryBulkSelection(
			repositoryId, folderId, _resourceBundleLoader, _language,
			_repositoryProvider, _dlAppService);
	}

	private BulkSelection<FileEntry> _getFileEntrySelection(String[] values) {
		if (values.length == 1) {
			values = StringUtil.split(values[0]);
		}

		long[] fileEntryIds = GetterUtil.getLongValues(values);

		if (fileEntryIds.length == 1) {
			return new SingleFileEntryBulkSelection(
				fileEntryIds[0], _resourceBundleLoader, _language,
				_dlAppService);
		}

		return new MultipleFileEntryBulkSelection(
			fileEntryIds, _resourceBundleLoader, _language, _dlAppService);
	}

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private Language _language;

	@Reference
	private RepositoryProvider _repositoryProvider;

	@Reference(
		target = "(bundle.symbolic.name=com.liferay.document.library.web)"
	)
	private ResourceBundleLoader _resourceBundleLoader;

}