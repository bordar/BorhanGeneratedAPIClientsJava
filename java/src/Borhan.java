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

import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.borhan.client.BorhanApiException;
import com.borhan.client.BorhanClient;
import com.borhan.client.BorhanConfiguration;
import com.borhan.client.BorhanMultiResponse;
import com.borhan.client.enums.BorhanEntryStatus;
import com.borhan.client.enums.BorhanMediaType;
import com.borhan.client.enums.BorhanSessionType;
import com.borhan.client.services.BorhanMediaService;
import com.borhan.client.types.BorhanMediaEntry;
import com.borhan.client.types.BorhanMediaListResponse;
import com.borhan.client.types.BorhanPartner;
import com.borhan.client.types.BorhanUploadToken;
import com.borhan.client.types.BorhanUploadedFileTokenResource;

import com.borhan.client.test.BorhanTestConfig;
import com.borhan.client.test.TestUtils;

public class Borhan {
	
	private static final int WAIT_BETWEEN_TESTS = 30000;
	private static final String SERVICE_URL = "http://www.borhan.com";
	static public BorhanClient client;
	
	public static void main(String[] args) {
		
		try {

			list();
			multiRequest();
			BorhanMediaEntry entry = addEmptyEntry();
			uploadMediaFileAndAttachToEmptyEntry(entry);
			testIfEntryIsReadyForPublish(entry);
			// cleanup the sample by deleting the entry:
			deleteEntry(entry);
			System.out.println("Sample code finished successfully.");
			
		} catch (BorhanApiException e) {
			System.out.println("Example failed.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Helper function to create the Borhan client object once and then reuse a static instance.
	 * @return a singleton of <code>BorhanClient</code> used in this case 
	 * @throws BorhanApiException if failed to generate session
	 */
	private static BorhanClient getBorhanClient() throws BorhanApiException
	{
		if (client != null) {
			return client;
		}
		
		// Set Constants
		int partnerId = BorhanTestConfig.PARTNER_ID;
		String adminSecret = BorhanTestConfig.ADMIN_SECRET;
		String userId = BorhanTestConfig.USER_NAME;
		
		// Generate configuration
		BorhanConfiguration config = new BorhanConfiguration();
		config.setPartnerId(partnerId);
		config.setEndpoint(SERVICE_URL);
		
		try {
			// Create the client and open session
			client = new BorhanClient(config);
			String ks = client.generateSession(adminSecret, userId, BorhanSessionType.ADMIN, partnerId);
			client.setSessionId(ks);
		} catch(Exception ex) {
			client = null;
			throw new BorhanApiException("Failed to generate session");
		}
		
		System.out.println("Generated KS locally: [" + client.getSessionId() + "]");
		return client;
	}
	
	/** 
	 * lists all media in the account.
	 */
	private static void list() throws BorhanApiException {

		BorhanMediaListResponse list = getBorhanClient().getMediaService().list();
		if (list.totalCount > 0) {
			System.out.println("The account contains " + list.totalCount + " entries.");
			for (BorhanMediaEntry entry : list.objects) {
				System.out.println("\t \"" + entry.name + "\"");
			}
		} else {
			System.out.println("This account doesn't have any entries in it.");
		}
	}

	/**
	 * shows how to chain requests together to call a multi-request type where several requests are called in a single request.
	 */
	private static void multiRequest() throws BorhanApiException
 {
		BorhanClient client = getBorhanClient();
		client.startMultiRequest();
		client.getBaseEntryService().count();
		client.getPartnerService().getInfo();
		client.getPartnerService().getUsage(2010);
		BorhanMultiResponse multi = client.doMultiRequest();
		BorhanPartner partner = (BorhanPartner) multi.get(1);
		System.out.println("Got Admin User email: " + partner.adminEmail);

	}
	
	/** 
	 * creates an empty media entry and assigns basic metadata to it.
	 * @return the generated <code>BorhanMediaEntry</code>
	 * @throws BorhanApiException 
	 */
	private static BorhanMediaEntry addEmptyEntry() throws BorhanApiException {
		System.out.println("Creating an empty Borhan Entry (without actual media binary attached)...");
		BorhanMediaEntry entry = new BorhanMediaEntry();
		entry.name = "An Empty Borhan Entry Test";
		entry.mediaType = BorhanMediaType.VIDEO;
		BorhanMediaEntry newEntry = getBorhanClient().getMediaService().add(entry);
		System.out.println("The id of our new Video Entry is: " + newEntry.id);
		return newEntry;
	}
	
	/**
	 *  uploads a video file to Borhan and assigns it to a given Media Entry object
	 */
	private static void uploadMediaFileAndAttachToEmptyEntry(BorhanMediaEntry entry) throws BorhanApiException
	{
			BorhanClient client = getBorhanClient();			
			System.out.println("Uploading a video file...");
			
			// upload upload token
			BorhanUploadToken upToken = client.getUploadTokenService().add();
			BorhanUploadedFileTokenResource fileTokenResource = new BorhanUploadedFileTokenResource();
			
			// Connect to media entry and update name
			fileTokenResource.token = upToken.id;
			entry = client.getMediaService().addContent(entry.id, fileTokenResource);
			
			// Upload actual data
			try
			{
				InputStream fileData = TestUtils.getTestVideo();
				int fileSize = fileData.available();

				client.getUploadTokenService().upload(upToken.id, fileData, BorhanTestConfig.UPLOAD_VIDEO, fileSize);
				
				System.out.println("Uploaded a new Video file to entry: " + entry.id);
			}
			catch (FileNotFoundException e)
			{
				System.out.println("Failed to open test video file");
			}
			catch (IOException e)
			{
				System.out.println("Failed to read test video file");
			}
	}
	
	/** 
	 * periodically calls the Borhan API to check that a given video entry has finished transcoding and is ready for playback. 
	 * @param entry The <code>BorhanMediaEntry</code> we want to test
	 */
	private static void testIfEntryIsReadyForPublish(BorhanMediaEntry entry)
			throws BorhanApiException {

		System.out.println("Testing if Media Entry has finished processing and ready to be published...");
		BorhanMediaService mediaService = getBorhanClient().getMediaService();
		while (true) {
			BorhanMediaEntry retrievedEntry = mediaService.get(entry.id);
			if (retrievedEntry.status == BorhanEntryStatus.READY) {
				break;
			}
			System.out.println("Media not ready yet. Waiting 30 seconds.");
			try {
				Thread.sleep(WAIT_BETWEEN_TESTS);
			} catch (InterruptedException ie) {
			}
		}
		System.out.println("Entry id: " + entry.id + " is now ready to be published and played.");
	}

	/** 
	 * deletes a given entry
	 * @param entry the <code>BorhanMediaEntry</code> we want to delete
	 * @throws BorhanApiException
	 */
	private static void deleteEntry(BorhanMediaEntry entry)
			throws BorhanApiException {
		System.out.println("Deleting entry id: " + entry.id);
		getBorhanClient().getMediaService().delete(entry.id);
	}
}
