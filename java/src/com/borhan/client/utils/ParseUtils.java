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
package com.borhan.client.utils;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.borhan.client.IBorhanLogger;
import com.borhan.client.BorhanApiException;
import com.borhan.client.BorhanObjectFactory;
import com.borhan.client.BorhanLogger;

public final class ParseUtils {
	
	private static IBorhanLogger logger = BorhanLogger.getLogger(ParseUtils.class);
	
	public static String parseString(String txt) {
		 return txt;
	}

	public static int parseInt(String txt) {
		if (txt.length() != 0) {
			try {
				return Integer.parseInt(txt);
			} catch (NumberFormatException nfe) {
				if (logger.isEnabled())
					logger.warn("Failed to parse [" + txt + "] as int", nfe);
			}
		}
		return 0;
	}
	
	public static long parseBigint(String txt) {
		if (txt.length() != 0) {
			try {
				return Long.parseLong(txt);
			} catch (NumberFormatException nfe) {
				if (logger.isEnabled())
					logger.warn("Failed to parse [" + txt + "] as long", nfe);
			}
		}
		return 0;
	}
	
	public static double parseDouble(String txt) {
		if (txt.length() != 0) {
			try {
				return Double.parseDouble(txt);
			} catch (NumberFormatException nfe) {
				if (logger.isEnabled())
					logger.warn("Failed to parse [" + txt + "] as double", nfe);
			}
		}
		return 0;
	}
	
	public static boolean parseBool(String txt) {
		 return txt.equals("0") ? false : true;
	}
	
	public static <T> ArrayList<T> parseArray(Class<T> clz, Node aNode) throws BorhanApiException{
		ArrayList<T> tmpList = new ArrayList<T>();
		NodeList subNodeList = aNode.getChildNodes();
		for (int j = 0; j < subNodeList.getLength(); j++) {
			Node arrayNode = subNodeList.item(j);
			tmpList.add((T) BorhanObjectFactory.create((Element) arrayNode, clz));
		}
		return tmpList;
	}

	public static <T> T parseObject(Class<T> clz, Node aNode) throws BorhanApiException{
		 return (T) BorhanObjectFactory.create((Element)aNode, clz);
	}
	
}
