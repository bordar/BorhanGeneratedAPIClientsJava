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
package com.borhan.client.enums;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Borhan.
 * @date Tue, 16 Dec 14 10:44:09 -0500
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum BorhanRuleActionType implements BorhanEnumAsString {
    DRM_POLICY ("playReady.DRM_POLICY"),
    BLOCK ("1"),
    PREVIEW ("2"),
    LIMIT_FLAVORS ("3"),
    ADD_TO_STORAGE ("4");

    public String hashCode;

    BorhanRuleActionType(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public static BorhanRuleActionType get(String hashCode) {
        if (hashCode.equals("playReady.DRM_POLICY"))
        {
           return DRM_POLICY;
        }
        else 
        if (hashCode.equals("1"))
        {
           return BLOCK;
        }
        else 
        if (hashCode.equals("2"))
        {
           return PREVIEW;
        }
        else 
        if (hashCode.equals("3"))
        {
           return LIMIT_FLAVORS;
        }
        else 
        if (hashCode.equals("4"))
        {
           return ADD_TO_STORAGE;
        }
        else 
        {
           return DRM_POLICY;
        }
    }
}
