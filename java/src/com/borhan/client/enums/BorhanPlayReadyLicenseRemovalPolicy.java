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
public enum BorhanPlayReadyLicenseRemovalPolicy implements BorhanEnumAsInt {
    FIXED_FROM_EXPIRATION (1),
    ENTRY_SCHEDULING_END (2),
    NONE (3);

    public int hashCode;

    BorhanPlayReadyLicenseRemovalPolicy(int hashCode) {
        this.hashCode = hashCode;
    }

    public int getHashCode() {
        return this.hashCode;
    }

    public static BorhanPlayReadyLicenseRemovalPolicy get(int hashCode) {
        switch(hashCode) {
            case 1: return FIXED_FROM_EXPIRATION;
            case 2: return ENTRY_SCHEDULING_END;
            case 3: return NONE;
            default: return FIXED_FROM_EXPIRATION;
        }
    }
}
