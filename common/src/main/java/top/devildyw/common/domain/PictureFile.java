package top.devildyw.common.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author MAC
 * @version 1.0
 * @description: TODO
 * @date 2022/1/1 19:06
 */
@Data
@Table(name = "picturefile")
@Entity
@TableName("picturefile")
public class PictureFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    @Column(columnDefinition = "bigint(20)")
    private Long pictureFileId;

    @Column(columnDefinition = "varchar(500) comment '文件url'")
    private String fileUrl;

    @Column(columnDefinition = "bigint(10) comment '文件大小'")
    private Long fileSize;

    @Column(columnDefinition = "int(1) comment '存储类型'")
    private Integer storageType;

    @Column(columnDefinition = "bigint(20) comment '用户id'")
    private Long userId;

    @Column(columnDefinition = "varchar(100) comment '文件名'")
    private String fileName;

    @Column(columnDefinition = "varchar(100) comment '扩展名'")
    private String extendName;

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


}
