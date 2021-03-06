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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class BorhanLoggerLog4j extends Logger implements IBorhanLogger
{
	protected Logger logger;
	static protected LoggerFactory loggerFactory = new BorhanLoggerFactory();
	
	static class BorhanLoggerFactory implements LoggerFactory {
		@Override
		public Logger makeNewLoggerInstance(String name) {
			return new BorhanLoggerLog4j(name);
		}
	}
	
	// Creation & retrieval methods:
	public static IBorhanLogger get(String name)
	{
		Logger logger = LogManager.getLogger(name, loggerFactory);
		if(logger instanceof Logger){
			return (BorhanLoggerLog4j) logger;
		}
		
		return null;
	}
	
	protected BorhanLoggerLog4j(String name)
	{
		super(name);
	}

	@Override
	public boolean isEnabled() {
		return this.isInfoEnabled();
	}
}
