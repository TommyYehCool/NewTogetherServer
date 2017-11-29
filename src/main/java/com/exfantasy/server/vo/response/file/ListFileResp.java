package com.exfantasy.server.vo.response.file;

import java.util.Date;

import com.exfantasy.server.cnst.file.CloudStorage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <pre>
 * 檔案資訊
 * </pre>
 * 
 * @author tommy.feng
 *
 */
@Data
public class ListFileResp {
	@ApiModelProperty(notes = "儲存的雲端空間")
	private CloudStorage cloudStorage;
	
	@ApiModelProperty(notes = "儲存的路徑")
	private String pathAndName;
	
	@ApiModelProperty(notes = "檔案大小")
	private long fileSizeBytes;
	
	@ApiModelProperty(notes = "最後修改時間")
	private Date lastModified;
}
