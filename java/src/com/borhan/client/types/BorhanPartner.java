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
package com.borhan.client.types;

import org.w3c.dom.Element;
import com.borhan.client.BorhanParams;
import com.borhan.client.BorhanApiException;
import com.borhan.client.BorhanObjectBase;
import com.borhan.client.enums.BorhanCommercialUseType;
import com.borhan.client.enums.BorhanPartnerType;
import com.borhan.client.enums.BorhanPartnerStatus;
import com.borhan.client.enums.BorhanPartnerGroupType;
import java.util.ArrayList;
import com.borhan.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using generate.php
 * against an XML schema provided by Borhan.
 * @date Tue, 16 Dec 14 10:44:09 -0500
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class BorhanPartner extends BorhanObjectBase {
    public int id = Integer.MIN_VALUE;
    public String name;
    public String website;
    public String notificationUrl;
    public int appearInSearch = Integer.MIN_VALUE;
    public int createdAt = Integer.MIN_VALUE;
	/**  deprecated - lastName and firstName replaces this field     */
    public String adminName;
    public String adminEmail;
    public String description;
    public BorhanCommercialUseType commercialUse;
    public String landingPage;
    public String userLandingPage;
    public String contentCategories;
    public BorhanPartnerType type;
    public String phone;
    public String describeYourself;
    public boolean adultContent;
    public String defConversionProfileType;
    public int notify = Integer.MIN_VALUE;
    public BorhanPartnerStatus status;
    public int allowQuickEdit = Integer.MIN_VALUE;
    public int mergeEntryLists = Integer.MIN_VALUE;
    public String notificationsConfig;
    public int maxUploadSize = Integer.MIN_VALUE;
    public int partnerPackage = Integer.MIN_VALUE;
    public String secret;
    public String adminSecret;
    public String cmsPassword;
    public int allowMultiNotification = Integer.MIN_VALUE;
    public int adminLoginUsersQuota = Integer.MIN_VALUE;
    public String adminUserId;
	/**  firstName and lastName replace the old (deprecated) adminName     */
    public String firstName;
	/**  lastName and firstName replace the old (deprecated) adminName     */
    public String lastName;
	/**  country code (2char) - this field is optional     */
    public String country;
	/**  state code (2char) - this field is optional     */
    public String state;
    public ArrayList<BorhanKeyValue> additionalParams;
    public int publishersQuota = Integer.MIN_VALUE;
    public BorhanPartnerGroupType partnerGroupType;
    public boolean defaultEntitlementEnforcement;
    public String defaultDeliveryType;
    public String defaultEmbedCodeType;
    public ArrayList<BorhanPlayerDeliveryType> deliveryTypes;
    public ArrayList<BorhanPlayerEmbedCodeType> embedCodeTypes;
    public int templatePartnerId = Integer.MIN_VALUE;
    public boolean ignoreSeoLinks;
    public String host;
    public String cdnHost;
    public boolean isFirstLogin;
    public String logoutUrl;
    public int partnerParentId = Integer.MIN_VALUE;
    public String crmId;
    public String referenceId;

    public BorhanPartner() {
    }

    public BorhanPartner(Element node) throws BorhanApiException {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("name")) {
                this.name = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("website")) {
                this.website = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("notificationUrl")) {
                this.notificationUrl = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("appearInSearch")) {
                this.appearInSearch = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("createdAt")) {
                this.createdAt = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("adminName")) {
                this.adminName = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("adminEmail")) {
                this.adminEmail = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("description")) {
                this.description = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("commercialUse")) {
                this.commercialUse = BorhanCommercialUseType.get(ParseUtils.parseInt(txt));
                continue;
            } else if (nodeName.equals("landingPage")) {
                this.landingPage = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("userLandingPage")) {
                this.userLandingPage = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("contentCategories")) {
                this.contentCategories = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("type")) {
                this.type = BorhanPartnerType.get(ParseUtils.parseInt(txt));
                continue;
            } else if (nodeName.equals("phone")) {
                this.phone = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("describeYourself")) {
                this.describeYourself = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("adultContent")) {
                this.adultContent = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("defConversionProfileType")) {
                this.defConversionProfileType = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("notify")) {
                this.notify = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("status")) {
                this.status = BorhanPartnerStatus.get(ParseUtils.parseInt(txt));
                continue;
            } else if (nodeName.equals("allowQuickEdit")) {
                this.allowQuickEdit = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("mergeEntryLists")) {
                this.mergeEntryLists = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("notificationsConfig")) {
                this.notificationsConfig = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("maxUploadSize")) {
                this.maxUploadSize = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("partnerPackage")) {
                this.partnerPackage = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("secret")) {
                this.secret = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("adminSecret")) {
                this.adminSecret = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("cmsPassword")) {
                this.cmsPassword = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("allowMultiNotification")) {
                this.allowMultiNotification = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("adminLoginUsersQuota")) {
                this.adminLoginUsersQuota = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("adminUserId")) {
                this.adminUserId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("firstName")) {
                this.firstName = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("lastName")) {
                this.lastName = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("country")) {
                this.country = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("state")) {
                this.state = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("additionalParams")) {
                this.additionalParams = ParseUtils.parseArray(BorhanKeyValue.class, aNode);
                continue;
            } else if (nodeName.equals("publishersQuota")) {
                this.publishersQuota = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("partnerGroupType")) {
                this.partnerGroupType = BorhanPartnerGroupType.get(ParseUtils.parseInt(txt));
                continue;
            } else if (nodeName.equals("defaultEntitlementEnforcement")) {
                this.defaultEntitlementEnforcement = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("defaultDeliveryType")) {
                this.defaultDeliveryType = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("defaultEmbedCodeType")) {
                this.defaultEmbedCodeType = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("deliveryTypes")) {
                this.deliveryTypes = ParseUtils.parseArray(BorhanPlayerDeliveryType.class, aNode);
                continue;
            } else if (nodeName.equals("embedCodeTypes")) {
                this.embedCodeTypes = ParseUtils.parseArray(BorhanPlayerEmbedCodeType.class, aNode);
                continue;
            } else if (nodeName.equals("templatePartnerId")) {
                this.templatePartnerId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("ignoreSeoLinks")) {
                this.ignoreSeoLinks = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("host")) {
                this.host = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("cdnHost")) {
                this.cdnHost = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("isFirstLogin")) {
                this.isFirstLogin = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("logoutUrl")) {
                this.logoutUrl = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("partnerParentId")) {
                this.partnerParentId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("crmId")) {
                this.crmId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("referenceId")) {
                this.referenceId = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public BorhanParams toParams() {
        BorhanParams kparams = super.toParams();
        kparams.add("objectType", "BorhanPartner");
        kparams.add("name", this.name);
        kparams.add("website", this.website);
        kparams.add("notificationUrl", this.notificationUrl);
        kparams.add("appearInSearch", this.appearInSearch);
        kparams.add("adminName", this.adminName);
        kparams.add("adminEmail", this.adminEmail);
        kparams.add("description", this.description);
        kparams.add("commercialUse", this.commercialUse);
        kparams.add("landingPage", this.landingPage);
        kparams.add("userLandingPage", this.userLandingPage);
        kparams.add("contentCategories", this.contentCategories);
        kparams.add("type", this.type);
        kparams.add("phone", this.phone);
        kparams.add("describeYourself", this.describeYourself);
        kparams.add("adultContent", this.adultContent);
        kparams.add("defConversionProfileType", this.defConversionProfileType);
        kparams.add("notify", this.notify);
        kparams.add("allowQuickEdit", this.allowQuickEdit);
        kparams.add("mergeEntryLists", this.mergeEntryLists);
        kparams.add("notificationsConfig", this.notificationsConfig);
        kparams.add("maxUploadSize", this.maxUploadSize);
        kparams.add("allowMultiNotification", this.allowMultiNotification);
        kparams.add("adminUserId", this.adminUserId);
        kparams.add("firstName", this.firstName);
        kparams.add("lastName", this.lastName);
        kparams.add("country", this.country);
        kparams.add("state", this.state);
        kparams.add("additionalParams", this.additionalParams);
        kparams.add("referenceId", this.referenceId);
        return kparams;
    }

}

