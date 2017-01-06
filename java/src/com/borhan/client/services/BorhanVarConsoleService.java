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
import com.borhan.client.enums.*;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Borhan.
 * @date Tue, 16 Dec 14 10:44:09 -0500
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Utility service for the Multi-publishers console    */
@SuppressWarnings("serial")
public class BorhanVarConsoleService extends BorhanServiceBase {
    public BorhanVarConsoleService(BorhanClient client) {
        this.borhanClient = client;
    }

    public BorhanPartnerUsageListResponse getPartnerUsage() throws BorhanApiException {
        return this.getPartnerUsage(null);
    }

    public BorhanPartnerUsageListResponse getPartnerUsage(BorhanPartnerFilter partnerFilter) throws BorhanApiException {
        return this.getPartnerUsage(partnerFilter, null);
    }

    public BorhanPartnerUsageListResponse getPartnerUsage(BorhanPartnerFilter partnerFilter, BorhanReportInputFilter usageFilter) throws BorhanApiException {
        return this.getPartnerUsage(partnerFilter, usageFilter, null);
    }

	/**  Function which calulates partner usage of a group of a VAR's sub-publishers     
	    */
    public BorhanPartnerUsageListResponse getPartnerUsage(BorhanPartnerFilter partnerFilter, BorhanReportInputFilter usageFilter, BorhanFilterPager pager) throws BorhanApiException {
        BorhanParams kparams = new BorhanParams();
        kparams.add("partnerFilter", partnerFilter);
        kparams.add("usageFilter", usageFilter);
        kparams.add("pager", pager);
        this.borhanClient.queueServiceCall("varconsole_varconsole", "getPartnerUsage", kparams, BorhanPartnerUsageListResponse.class);
        if (this.borhanClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.borhanClient.doQueue();
        return ParseUtils.parseObject(BorhanPartnerUsageListResponse.class, resultXmlElement);
    }

	/**  Function to change a sub-publisher's status     */
    public void updateStatus(int id, BorhanPartnerStatus status) throws BorhanApiException {
        BorhanParams kparams = new BorhanParams();
        kparams.add("id", id);
        kparams.add("status", status);
        this.borhanClient.queueServiceCall("varconsole_varconsole", "updateStatus", kparams);
        if (this.borhanClient.isMultiRequest())
            return ;
        this.borhanClient.doQueue();
    }
}