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
import com.borhan.client.enums.BorhanDistributionProviderType;
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
public abstract class BorhanDistributionProvider extends BorhanObjectBase {
    public BorhanDistributionProviderType type;
    public String name;
    public boolean scheduleUpdateEnabled;
    public boolean availabilityUpdateEnabled;
    public boolean deleteInsteadUpdate;
    public int intervalBeforeSunrise = Integer.MIN_VALUE;
    public int intervalBeforeSunset = Integer.MIN_VALUE;
    public String updateRequiredEntryFields;
    public String updateRequiredMetadataXPaths;

    public BorhanDistributionProvider() {
    }

    public BorhanDistributionProvider(Element node) throws BorhanApiException {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("type")) {
                this.type = BorhanDistributionProviderType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("name")) {
                this.name = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("scheduleUpdateEnabled")) {
                this.scheduleUpdateEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("availabilityUpdateEnabled")) {
                this.availabilityUpdateEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("deleteInsteadUpdate")) {
                this.deleteInsteadUpdate = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("intervalBeforeSunrise")) {
                this.intervalBeforeSunrise = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("intervalBeforeSunset")) {
                this.intervalBeforeSunset = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("updateRequiredEntryFields")) {
                this.updateRequiredEntryFields = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("updateRequiredMetadataXPaths")) {
                this.updateRequiredMetadataXPaths = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public BorhanParams toParams() {
        BorhanParams kparams = super.toParams();
        kparams.add("objectType", "BorhanDistributionProvider");
        kparams.add("name", this.name);
        kparams.add("scheduleUpdateEnabled", this.scheduleUpdateEnabled);
        kparams.add("availabilityUpdateEnabled", this.availabilityUpdateEnabled);
        kparams.add("deleteInsteadUpdate", this.deleteInsteadUpdate);
        kparams.add("intervalBeforeSunrise", this.intervalBeforeSunrise);
        kparams.add("intervalBeforeSunset", this.intervalBeforeSunset);
        kparams.add("updateRequiredEntryFields", this.updateRequiredEntryFields);
        kparams.add("updateRequiredMetadataXPaths", this.updateRequiredMetadataXPaths);
        return kparams;
    }

}

