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
package com.borhan.client.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.borhan.client.BorhanApiException;
import com.borhan.client.BorhanClient;
import com.borhan.client.enums.BorhanEntryStatus;
import com.borhan.client.enums.BorhanEntryType;
import com.borhan.client.enums.BorhanMediaType;
import com.borhan.client.enums.BorhanModerationFlagType;
import com.borhan.client.services.BorhanMediaService;
import com.borhan.client.types.BorhanBaseEntry;
import com.borhan.client.types.BorhanDataEntry;
import com.borhan.client.types.BorhanFlavorAsset;
import com.borhan.client.types.BorhanMediaEntry;
import com.borhan.client.types.BorhanMediaEntryFilter;
import com.borhan.client.types.BorhanMediaEntryFilterForPlaylist;
import com.borhan.client.types.BorhanMediaListResponse;
import com.borhan.client.types.BorhanModerationFlag;
import com.borhan.client.types.BorhanModerationFlagListResponse;
import com.borhan.client.types.BorhanUploadToken;
import com.borhan.client.types.BorhanUploadedFileTokenResource;
import com.borhan.client.IBorhanLogger;
import com.borhan.client.BorhanLogger;

public class MediaServiceTest extends BaseTest {

	private IBorhanLogger logger = BorhanLogger.getLogger(MediaServiceTest.class);
	
	/**
	 * Tests the following : 
	 * Media Service -
	 *  - add From Url
	 */
	public void testAddFromUrl() {
		if (logger.isEnabled()) 
			logger.info("Test Add From URL");
        
		String name = "test (" + new Date() + ")";
		
		try {
			startUserSession(client, borhanConfig);
			BorhanMediaEntry addedEntry = addClipFromUrl(this, client, name);
			assertNotNull(addedEntry);
			assertNotNull(addedEntry.id);
			assertEquals(name, addedEntry.name);
			assertEquals(BorhanEntryStatus.IMPORT, addedEntry.status);
		} catch (BorhanApiException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public static BorhanMediaEntry addClipFromUrl(BaseTest testContainer,
			BorhanClient client, String name) throws BorhanApiException {

		BorhanMediaEntry entry = new BorhanMediaEntry();

		entry.name = name;
		entry.type = BorhanEntryType.MEDIA_CLIP;
		entry.mediaType = BorhanMediaType.VIDEO;

		BorhanMediaService mediaService = client.getMediaService();
		BorhanMediaEntry addedEntry = mediaService.addFromUrl(entry, BorhanTestConfig.TEST_URL);

		if(addedEntry != null)
			testContainer.testIds.add(addedEntry.id);
		
		return addedEntry;
	}
	
	/**
	 * Tests the following : 
	 * Media Service - 
	 * 	- add 
	 *  - add Content
	 *  - count
	 * Upload token - 
	 *  - add
	 *  - upload
	 * Flavor asset - 
	 * 	- get by entry id
	 */
	public void testUploadTokenAddGivenFile() {
		
		if (logger.isEnabled())
			logger.info("Test upload token add");
		
		try {
			InputStream fileData = TestUtils.getTestVideo();
			int fileSize = fileData.available();
			
			startUserSession(client, borhanConfig);
			int sz = client.getMediaService().count();
			
			// Create entry
			BorhanMediaEntry entry = new BorhanMediaEntry();
			entry.name =  "test (" + new Date() + ")";
			entry.type = BorhanEntryType.MEDIA_CLIP;
			entry.mediaType = BorhanMediaType.VIDEO;
			
			entry = client.getMediaService().add(entry);
			assertNotNull(entry);
			
			testIds.add(entry.id);
			
			// Create token
			BorhanUploadToken uploadToken = new BorhanUploadToken();
			uploadToken.fileName = BorhanTestConfig.UPLOAD_VIDEO;
			uploadToken.fileSize = fileSize;
			BorhanUploadToken token = client.getUploadTokenService().add(uploadToken);
			assertNotNull(token);
			
			// Define content
			BorhanUploadedFileTokenResource resource = new BorhanUploadedFileTokenResource();
			resource.token = token.id;
			entry = client.getMediaService().addContent(entry.id, resource);
			assertNotNull(entry);
			
			// upload
			uploadToken = client.getUploadTokenService().upload(token.id, fileData, BorhanTestConfig.UPLOAD_VIDEO, fileSize, false);
			assertNotNull(uploadToken);
			
			// Test Creation
			entry = getProcessedEntry(client, entry.id, true);
			assertNotNull(entry);
			
			// Test get flavor asset by entry id.
			List<BorhanFlavorAsset> listFlavors = client.getFlavorAssetService().getByEntryId(entry.id);
			assertNotNull(listFlavors);
			assertTrue(listFlavors.size() >= 1); // Should contain at least the source
			
			// Test count
			int sz2 = client.getMediaService().count();
			assertTrue(sz + 1 == sz2);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void testUploadUnexistingFile() throws BorhanApiException {
		
		File file = new File("bin/nonExistingfile.flv");
		
		startUserSession(client, borhanConfig);
		
		// Create token
		BorhanUploadToken uploadToken = new BorhanUploadToken();
		uploadToken.fileName = file.getName();
		uploadToken.fileSize = file.length();
		BorhanUploadToken token = client.getUploadTokenService().add(uploadToken);
		assertNotNull(token);
		
		// upload
		try {
			client.getUploadTokenService().upload(token.id, file, false);
			fail();
		} catch (IllegalArgumentException e) {
			assert(e.getMessage().contains("is not readable or not a file"));
		}
	}
	
	
	/**
	 * Tests the following : 
	 * Media Service -
	 *  - add From Url
	 * http://www.borhan.org/how-update-supposed-work-api-v3
	 */
	public void testUpdate() {
		if (logger.isEnabled())
			logger.info("Test Update Entry");
		
		String name = "test (" + new Date() + ")";
		
		try {
			startUserSession(client, borhanConfig);
			BorhanMediaEntry addedEntry = BaseTest.addTestImage(this, client, name);
			assertNotNull(addedEntry);
			assertNotNull(addedEntry.id);
			
			String name2 = "test (" + new Date() + ")";
			
			BorhanMediaEntry updatedEntry = new BorhanMediaEntry();
			updatedEntry.name = name2;			
			client.getMediaService().update(addedEntry.id, updatedEntry);
			
			BorhanMediaEntry queriedEntry  = getProcessedEntry(client, addedEntry.id, true);
			assertEquals(name2, queriedEntry.name);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Tests the following : 
	 * Media Service -
	 *  - Get
	 */
	public void testBadGet() {
		if (logger.isEnabled())
			logger.info("Starting badGet test");
		
		// look for one we know doesn't exist
		BorhanMediaEntry badEntry = null;
		try {
			startUserSession(client, borhanConfig);
			BorhanMediaService mediaService = this.client.getMediaService();
			badEntry = mediaService.get("badid");
			fail();
		} catch (BorhanApiException bae) {
			// expected behavior
		}
		
		assertNull(badEntry);
	}
	
	/**
	 * Tests the following : 
	 * Media Service -
	 *  - Get
	 */
	public void testGet() {
		if (logger.isEnabled())
			logger.info("Starting get test");
		
		String name = "test (" + new Date() + ")";
		
		try {
			startUserSession(client, borhanConfig);
			BorhanMediaEntry addedEntry = BaseTest.addTestImage(this, client, name);
			BorhanMediaService mediaService = this.client.getMediaService();
			BorhanMediaEntry retrievedEntry = mediaService.get(addedEntry.id);
			
			assertNotNull(retrievedEntry);
			assertEquals(addedEntry.id, retrievedEntry.id);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}

	/**
	 * Tests the following : 
	 * Media Service -
	 *  - list
	 */
	public void testList() {

		if (logger.isEnabled())
			logger.info("Test List");

		try {
			startUserSession(client, borhanConfig);
			// add test clips
			String name1 = "test one (" + new Date() + ")";
			BorhanMediaEntry addedEntry1 = BaseTest.addTestImage(this, client, name1);
			String name2 = "test two (" + new Date() + ")";
			BorhanMediaEntry addedEntry2 = BaseTest.addTestImage(this, client, name2);

			// Make sure were updated
			getProcessedEntry(client, addedEntry1.id, true);
			getProcessedEntry(client, addedEntry2.id, true);

			BorhanMediaService mediaService = this.client.getMediaService();

			// get a list of clips starting with "test"
			BorhanMediaEntryFilter filter = new BorhanMediaEntryFilter();
			filter.mediaTypeEqual = null;
			filter.statusEqual = null;
			filter.typeEqual = null;
			filter.nameMultiLikeOr = name1 + "," + name2;

			BorhanMediaListResponse listResponse = mediaService.list(filter);
			assertEquals(listResponse.totalCount, 2);

			boolean found1 = false;
			boolean found2 = false;
			for (BorhanMediaEntry entry : listResponse.objects) {
				if (logger.isEnabled())
					logger.debug("id:" + entry.id);
				if (entry.id.equals(addedEntry1.id)) {
					found1 = true;
				} else if (entry.id.equals(addedEntry2.id)) {
					found2 = true;
				}
			}

			assertTrue(found1);
			assertTrue(found2);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Tests the following : 
	 * Media Service -
	 *  - flag
	 *  - list flags
	 */
	public void testModeration() {
		
		if (logger.isEnabled())
			logger.info("Starting moderation test");
		
		final String FLAG_COMMENTS = "This is a test flag";
		
		if (logger.isEnabled())
			logger.info("Starting addFromUrl test");
        
		String name = "test (" + new Date() + ")";
		
		try {
			
			startAdminSession(client, borhanConfig);

			BorhanMediaEntry addedEntry = BaseTest.addTestImage(this, client, name);
			//wait for the newly-added clip to process
			getProcessedEntry(client, addedEntry.id);
						
			BorhanMediaService mediaService = this.client.getMediaService();
			
			// flag the clip
			BorhanModerationFlag flag = new BorhanModerationFlag();
			flag.flaggedEntryId = addedEntry.id;
			flag.flagType = BorhanModerationFlagType.SPAM_COMMERCIALS;
			flag.comments = FLAG_COMMENTS;
			mediaService.flag(flag);
			
			// get the list of flags for this entry
			BorhanModerationFlagListResponse flagList = mediaService.listFlags(addedEntry.id);
			assertEquals(flagList.totalCount, 1);

			// check that the flag we put in is the flag we got back
			BorhanModerationFlag retFlag = (BorhanModerationFlag)flagList.objects.get(0);						
			assertEquals(retFlag.flagType, BorhanModerationFlagType.SPAM_COMMERCIALS);
			assertEquals(retFlag.comments, FLAG_COMMENTS);
			
		} catch (Exception e) {
			if (logger.isEnabled())
				logger.error("Got exception testing moderation flag", e);	
			e.printStackTrace();
			fail();
		} 
		
	}
	
	
	/**
	 * Tests the following : 
	 * Media Service -
	 *  - delete
	 */
	public void testDelete() throws BorhanApiException {
		if (logger.isEnabled())
			logger.info("Starting delete test");
		
		String name = "test (" + new Date() + ")";
		String idToDelete = "";
		
		startUserSession(client, borhanConfig);
		BorhanMediaService mediaService = this.client.getMediaService();
		
		// First delete - should succeed
		try {
			
			BorhanMediaEntry addedEntry = BaseTest.addTestImage(this, client, name);
			assertNotNull(addedEntry);
			idToDelete = addedEntry.id;
			
			// calling this makes the test wait for processing to complete
			// if you call delete while it is processing, the delete doesn't happen
			getProcessedEntry(client,idToDelete);
			mediaService.delete(idToDelete);
			
		} catch (Exception e) {
			if (logger.isEnabled())
				logger.error("Trouble deleting", e);
			fail();
		} 

		// Second delete - should fail
		BorhanMediaEntry deletedEntry = null;
		try {
			deletedEntry = mediaService.get(idToDelete);
			fail();
		} catch (BorhanApiException bae) {
			// Wanted behavior
		} 
		
		// we whacked this one, so let's not keep track of it		
		this.testIds.remove(testIds.size() - 1);
		assertNull(deletedEntry);
	}
	
	/**
	 * Tests the following : 
	 * Media Service -
	 *  - upload
	 *  - add from uploaded file
	 */
	public void testUpload() {
		if (logger.isEnabled())
			logger.info("Starting delete test");
		
		String name = "test (" + new Date() + ")";
		
		BorhanMediaEntry entry = new BorhanMediaEntry();
		try {
			startUserSession(client, borhanConfig);
			BorhanMediaService mediaService = this.client.getMediaService();

			InputStream fileData = TestUtils.getTestVideo();
			int fileSize = fileData.available();

			String result = mediaService.upload(fileData, BorhanTestConfig.UPLOAD_VIDEO, fileSize);
			if (logger.isEnabled())
				logger.debug("After upload, result:" + result);			
			entry.name = name;
			entry.type = BorhanEntryType.MEDIA_CLIP;
			entry.mediaType = BorhanMediaType.VIDEO;
			entry = mediaService.addFromUploadedFile(entry, result);
		} catch (Exception e) {
			if (logger.isEnabled())
				logger.error("Trouble uploading", e);
			fail();
		} 
		
		assertNotNull(entry.id);
		
		if (entry.id != null) {
			this.testIds.add(entry.id);
		}
	}
	
	public void testDataServe() {
		if (logger.isEnabled())
			logger.info("Starting test data serve");
		try {
			startUserSession(client, borhanConfig);
			//client.getDataService().serve();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} 
	}
	
	public void testPlaylist() {
		if (logger.isEnabled())	
			logger.info("Starting test playlist execute from filters");
		try {
			startAdminSession(client, borhanConfig);
			
			// Create entry
			BorhanMediaEntry entry = BaseTest.addTestImage(this, client, "test (" + new Date() + ")");
			
			// generate filter
			BorhanMediaEntryFilterForPlaylist filter = new BorhanMediaEntryFilterForPlaylist();
			filter.idEqual = entry.id;
			ArrayList<BorhanMediaEntryFilterForPlaylist> filters = new ArrayList<BorhanMediaEntryFilterForPlaylist>();
			filters.add(filter);
			List<BorhanBaseEntry> res = client.getPlaylistService().executeFromFilters(filters, 5);
			assertNotNull(res);
			assertEquals(1, res.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} 
	}
	
	public void testServe() throws BorhanApiException {
		String test = "bla bla bla";
		try {
			startUserSession(client, borhanConfig);
			
			// Add Entry
			BorhanDataEntry dataEntry = new BorhanDataEntry();
			dataEntry.name = "test (" + new Date() + ")";
			dataEntry.dataContent = test;
			BorhanDataEntry addedDataEntry = client.getDataService().add(dataEntry);
			
			// serve
			String serveUrl = client.getDataService().serve(addedDataEntry.id);
			URL url = new URL(serveUrl);
			String content = readContent(url);
			assertEquals(test, content);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			fail();
		} 
	}
	
	private String readContent(URL url) {
		StringBuffer sb = new StringBuffer();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null)
				sb.append(inputLine);

			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} finally {
			if(in != null)
				try {
					in.close();
				} catch (IOException e) {
					fail();
				}
		}

		return sb.toString();
	}
	
}
