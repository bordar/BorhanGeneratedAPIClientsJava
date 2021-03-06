// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Borhan Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platfroms allow them to do with
// text.
//
// Copyright (C) 2006-2011  Borhan Inc.
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as
// published by the Free Software Foundation, either version 3 of the
// License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
// @ignore
// ===================================================================================================
package com.borhan.client.services;

import com.borhan.client.BorhanClient;
import com.borhan.client.BorhanServiceBase;
import com.borhan.client.types.*;
import org.w3c.dom.Element;
import com.borhan.client.utils.ParseUtils;
import com.borhan.client.BorhanParams;
import com.borhan.client.BorhanApiException;
import java.util.List;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Borhan.
 * @date Tue, 16 Dec 14 10:44:09 -0500
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Add & Manage Thumb Params    */
@SuppressWarnings("serial")
public class BorhanThumbParamsService extends BorhanServiceBase {
    public BorhanThumbParamsService(BorhanClient client) {
        this.borhanClient = client;
    }

	/**  Add new Thumb Params     */
    public BorhanThumbParams add(BorhanThumbParams thumbParams) throws BorhanApiException {
        BorhanParams kparams = new BorhanParams();
        kparams.add("thumbParams", thumbParams);
        this.borhanClient.queueServiceCall("thumbparams", "add", kparams, BorhanThumbParams.class);
        if (this.borhanClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.borhanClient.doQueue();
        return ParseUtils.parseObject(BorhanThumbParams.class, resultXmlElement);
    }

	/**  Get Thumb Params by ID     */
    public BorhanThumbParams get(int id) throws BorhanApiException {
        BorhanParams kparams = new BorhanParams();
        kparams.add("id", id);
        this.borhanClient.queueServiceCall("thumbparams", "get", kparams, BorhanThumbParams.class);
        if (this.borhanClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.borhanClient.doQueue();
        return ParseUtils.parseObject(BorhanThumbParams.class, resultXmlElement);
    }

	/**  Update Thumb Params by ID     */
    public BorhanThumbParams update(int id, BorhanThumbParams thumbParams) throws BorhanApiException {
        BorhanParams kparams = new BorhanParams();
        kparams.add("id", id);
        kparams.add("thumbParams", thumbParams);
        this.borhanClient.queueServiceCall("thumbparams", "update", kparams, BorhanThumbParams.class);
        if (this.borhanClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.borhanClient.doQueue();
        return ParseUtils.parseObject(BorhanThumbParams.class, resultXmlElement);
    }

	/**  Delete Thumb Params by ID     */
    public void delete(int id) throws BorhanApiException {
        BorhanParams kparams = new BorhanParams();
        kparams.add("id", id);
        this.borhanClient.queueServiceCall("thumbparams", "delete", kparams);
        if (this.borhanClient.isMultiRequest())
            return ;
        this.borhanClient.doQueue();
    }

    public BorhanThumbParamsListResponse list() throws BorhanApiException {
        return this.list(null);
    }

    public BorhanThumbParamsListResponse list(BorhanThumbParamsFilter filter) throws BorhanApiException {
        return this.list(filter, null);
    }

	/**  List Thumb Params by filter with paging support (By default - all system default
	  params will be listed too)     */
    public BorhanThumbParamsListResponse list(BorhanThumbParamsFilter filter, BorhanFilterPager pager) throws BorhanApiException {
        BorhanParams kparams = new BorhanParams();
        kparams.add("filter", filter);
        kparams.add("pager", pager);
        this.borhanClient.queueServiceCall("thumbparams", "list", kparams, BorhanThumbParamsListResponse.class);
        if (this.borhanClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.borhanClient.doQueue();
        return ParseUtils.parseObject(BorhanThumbParamsListResponse.class, resultXmlElement);
    }

	/**  Get Thumb Params by Conversion Profile ID     */
    public List<BorhanThumbParams> getByConversionProfileId(int conversionProfileId) throws BorhanApiException {
        BorhanParams kparams = new BorhanParams();
        kparams.add("conversionProfileId", conversionProfileId);
        this.borhanClient.queueServiceCall("thumbparams", "getByConversionProfileId", kparams, BorhanThumbParams.class);
        if (this.borhanClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.borhanClient.doQueue();
        return ParseUtils.parseArray(BorhanThumbParams.class, resultXmlElement);
    }
}
