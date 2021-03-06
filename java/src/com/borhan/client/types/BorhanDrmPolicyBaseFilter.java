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
import com.borhan.client.enums.BorhanDrmProviderType;
import com.borhan.client.enums.BorhanDrmPolicyStatus;
import com.borhan.client.enums.BorhanDrmLicenseScenario;
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
public abstract class BorhanDrmPolicyBaseFilter extends BorhanFilter {
    public int partnerIdEqual = Integer.MIN_VALUE;
    public String partnerIdIn;
    public String nameLike;
    public String systemNameLike;
    public BorhanDrmProviderType providerEqual;
    public String providerIn;
    public BorhanDrmPolicyStatus statusEqual;
    public String statusIn;
    public BorhanDrmLicenseScenario scenarioEqual;
    public String scenarioIn;

    public BorhanDrmPolicyBaseFilter() {
    }

    public BorhanDrmPolicyBaseFilter(Element node) throws BorhanApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("partnerIdEqual")) {
                this.partnerIdEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("partnerIdIn")) {
                this.partnerIdIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("nameLike")) {
                this.nameLike = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("systemNameLike")) {
                this.systemNameLike = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("providerEqual")) {
                this.providerEqual = BorhanDrmProviderType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("providerIn")) {
                this.providerIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("statusEqual")) {
                this.statusEqual = BorhanDrmPolicyStatus.get(ParseUtils.parseInt(txt));
                continue;
            } else if (nodeName.equals("statusIn")) {
                this.statusIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("scenarioEqual")) {
                this.scenarioEqual = BorhanDrmLicenseScenario.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("scenarioIn")) {
                this.scenarioIn = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public BorhanParams toParams() {
        BorhanParams kparams = super.toParams();
        kparams.add("objectType", "BorhanDrmPolicyBaseFilter");
        kparams.add("partnerIdEqual", this.partnerIdEqual);
        kparams.add("partnerIdIn", this.partnerIdIn);
        kparams.add("nameLike", this.nameLike);
        kparams.add("systemNameLike", this.systemNameLike);
        kparams.add("providerEqual", this.providerEqual);
        kparams.add("providerIn", this.providerIn);
        kparams.add("statusEqual", this.statusEqual);
        kparams.add("statusIn", this.statusIn);
        kparams.add("scenarioEqual", this.scenarioEqual);
        kparams.add("scenarioIn", this.scenarioIn);
        return kparams;
    }

}

