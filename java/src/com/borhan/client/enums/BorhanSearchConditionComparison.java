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
public enum BorhanSearchConditionComparison implements BorhanEnumAsString {
    EQUAL ("1"),
    GREATER_THAN ("2"),
    GREATER_THAN_OR_EQUAL ("3"),
    LESS_THAN ("4"),
    LESS_THAN_OR_EQUAL ("5");

    public String hashCode;

    BorhanSearchConditionComparison(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public static BorhanSearchConditionComparison get(String hashCode) {
        if (hashCode.equals("1"))
        {
           return EQUAL;
        }
        else 
        if (hashCode.equals("2"))
        {
           return GREATER_THAN;
        }
        else 
        if (hashCode.equals("3"))
        {
           return GREATER_THAN_OR_EQUAL;
        }
        else 
        if (hashCode.equals("4"))
        {
           return LESS_THAN;
        }
        else 
        if (hashCode.equals("5"))
        {
           return LESS_THAN_OR_EQUAL;
        }
        else 
        {
           return EQUAL;
        }
    }
}
