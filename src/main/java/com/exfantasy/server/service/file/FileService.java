package com.exfantasy.server.service.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exfantasy.server.cnst.ResultCode;
import com.exfantasy.server.cnst.file.CloudStorage;
import com.exfantasy.server.exception.OperationException;

/**
 * <pre>
 * 處理檔案相關 service
 * </pre>
 * 
 * @author tommy.feng
 *
 */
@Service
public class FileService {

	private final Logger logger = LoggerFactory.getLogger(FileService.class);

	// TODO 之後再處理 Profile Image
	@SuppressWarnings("unused")
	private final String PROFILE_IMAGE_NAME = "profileImage.jpg";
	
	@Autowired
    private DropboxService dropboxService;
	
	/**
     * <pre>
     * 上傳檔案到 Amazon S3 或 Dropbox
     * </pre>
     * 
     * @param multipartFile 欲上傳的檔案
     * @param pathAndName 欲儲存的路徑
     * 
     * @return {@link CloudStorage} 最後上傳的雲端空間
     */
    public CloudStorage uploadFile(MultipartFile multipartFile, String pathAndName) {
    	CloudStorage cloudStorage = null;
    	
    	String originalFileName = multipartFile.getOriginalFilename();
    	
//    	boolean uploadToAmazonS3Succeed;
//    	if (amazonS3Service.isEnable()) {
//    		try {
//    			logger.info(">>>>> Trying to upload file to Amazon S3, original file name: <{}>, Amazon S3 path and name: <{}>", originalFileName, pathAndName);
//    			
//    			uploadFileToAmazonS3(multipartFile, pathAndName);
//    			
//    			logger.info("<<<<< Upload file to Amazon S3 succeed, original file name: <{}>, Amazon S3 path and name: <{}>", originalFileName, pathAndName);
//    			
//    			cloudStorage = CloudStorage.AMAZON_S3;
//    			uploadToAmazonS3Succeed = true;
//    		}
//    		catch (Exception e) {
//    			amazonS3Service.setDisable();
//    			amazonS3Service.setErrorMsg(e.getMessage());
//    			logger.warn("~~~~~ Upload file to Amazon S3 failed, original file name: <{}>, Amazon S3 path and name: <{}>, error-msg: <{}>", originalFileName, pathAndName, e.getMessage(), e);
//    			uploadToAmazonS3Succeed = false;
//    		} 
//    	}
//    	else {
//    		logger.warn("~~~~~ Amazon S3 service is not available, error-msg: <{}>", amazonS3Service.getErrorMsg());
//    		uploadToAmazonS3Succeed = false;
//    	}
//    	
//    	if (!uploadToAmazonS3Succeed) {
			try {
				logger.info(">>>>> Trying to upload file to Dropbox, original file name: <{}>, Dropbox path and name: <{}>", originalFileName, pathAndName);
				
				uploadFileToDropbox(multipartFile, pathAndName);
				
				logger.info("<<<<< Upload file to Dropbox succeed, original file name: <{}>, Dropbox path and name: <{}>", originalFileName, pathAndName);
				
				cloudStorage = CloudStorage.DROPBOX;
				
			} catch (Exception e) {
				logger.error("~~~~~ Upload file to Dropbox failed, original file name: <{}>, Dropbox path and name: <{}>, error-msg: <{}>", originalFileName, pathAndName, e.getMessage(), e);
				throw new OperationException(ResultCode.UPLOAD_FILE_FAILED, e.getMessage());
			}
//		}
    	return cloudStorage;
    }
    
    /**
	 * <pre>
	 * 上傳檔案到 Dropbox
	 * 
	 * 路徑注意:
	 * Dropbox -> 開頭要 "/"
	 * 
	 * </pre>
	 * 
	 * @param multipartFile 欲上傳的檔案
	 * @param pathAndName 欲放在 Dropbox 的路徑
	 * 
	 * @throws Exception
	 */
	private void uploadFileToDropbox(MultipartFile multipartFile, String pathAndName) throws Exception {
		long startTime = System.currentTimeMillis();
		logger.info("----> Prepare to upload file to Dropbox, Dropbox path and name: <{}>", pathAndName);
		
		dropboxService.upload(multipartFile, pathAndName);
		
		long timeSpent = System.currentTimeMillis() - startTime;
		logger.info("<---- Upload file to Dropbox done, time-spent: <{} ms>", timeSpent);
	}
}
