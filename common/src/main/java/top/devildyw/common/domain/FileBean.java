package top.devildyw.common.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;


import javax.persistence.*;
import java.util.Date;

/**
 * 文件实体类
 *
 * @author ma116
 */
@Data
@Table(name = "file")
@Entity
@TableName("file")
public class FileBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @TableId(type = IdType.AUTO)
    @Column(columnDefinition = "varchar(20)")
    private String fileId;

    @Column(columnDefinition = "varchar(500) comment '文件url'")
    private String fileUrl;

    @Column(columnDefinition = "bigint(10) comment '文件大小'")
    private Long fileSize;

    @Column(columnDefinition = "int(1) comment '文件状态(0-失效，1-生效)'")
    private Integer fileStatus;

    @Column(columnDefinition = "int(1) comment '存储类型'")
    private Integer storageType;

    @Column(columnDefinition = "varchar(32) comment 'md5唯一标识'")
    private String identifier;

    @Column(columnDefinition = "varchar(25) comment '创建时间'")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Column(columnDefinition = "bigint(20) comment '创建用户id'")
    private Long createUserId;

    @Column(columnDefinition = "varchar(25) comment '修改时间'")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;

    @Column(columnDefinition = "bigint(20) comment '修改用户id'")
    private Long modifyUserId;

    public FileBean() {

    }

//    public FileBean(UploadFileResult uploadFileResult) {
//        this.fileId = IdUtil.getSnowflakeNextIdStr();
//        this.fileUrl = uploadFileResult.getFileUrl();
//        this.fileSize = uploadFileResult.getFileSize();
//        this.fileStatus = 1;
//        this.storageType = uploadFileResult.getStorageType().getCode();
//        this.identifier = uploadFileResult.getIdentifier();
//        this.createTime = DateUtil.getCurrentTime();
//
//    }

//    public FileBean(String fileUrl, Long fileSize, Integer storageType, String identifier, Long userId) {
//        this.fileId = IdUtil.getSnowflakeNextIdStr();
//        this.fileUrl = fileUrl;
//        this.fileSize = fileSize;
//        this.fileStatus = 1;
//        this.storageType = storageType;
//        this.identifier = identifier;
//        this.createTime = DateUtil.getCurrentTime();
//        this.createUserId = userId;
//
//    }

}
