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
package com.borhan.client;

import com.borhan.client.enums.BorhanEnumAsInt;

public enum EBorhanServiceFormat implements BorhanEnumAsInt{

	RESPONSE_TYPE_JSON(1),
	RESPONSE_TYPE_XML(2),
	RESPONSE_TYPE_PHP(3),
	RESPONSE_TYPE_PHP_ARRAY(4),
	RESPONSE_TYPE_PHP_OBJECT(5),
	RESPONSE_TYPE_RAW(6),
	RESPONSE_TYPE_HTML(7);
	
	private int hashCode;
	
	EBorhanServiceFormat(int hashCode) {
		this.hashCode = hashCode;
	}
    
	public int getHashCode() {
		return this.hashCode;
	}
}
