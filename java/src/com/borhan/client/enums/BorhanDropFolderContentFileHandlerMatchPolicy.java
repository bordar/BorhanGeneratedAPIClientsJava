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
public enum BorhanDropFolderContentFileHandlerMatchPolicy implements BorhanEnumAsInt {
    ADD_AS_NEW (1),
    MATCH_EXISTING_OR_ADD_AS_NEW (2),
    MATCH_EXISTING_OR_KEEP_IN_FOLDER (3);

    public int hashCode;

    BorhanDropFolderContentFileHandlerMatchPolicy(int hashCode) {
        this.hashCode = hashCode;
    }

    public int getHashCode() {
        return this.hashCode;
    }

    public static BorhanDropFolderContentFileHandlerMatchPolicy get(int hashCode) {
        switch(hashCode) {
            case 1: return ADD_AS_NEW;
            case 2: return MATCH_EXISTING_OR_ADD_AS_NEW;
            case 3: return MATCH_EXISTING_OR_KEEP_IN_FOLDER;
            default: return ADD_AS_NEW;
        }
    }
}
