//package com.example.smalldemo.ZipFile;
//
//import com.ca.common.tools.exception.ServiceException;
//import com.ca.common.tools.utils.CommonUtils;
//import net.lingala.zip4j.core.ZipFile;
//import net.lingala.zip4j.model.ZipParameters;
//import net.lingala.zip4j.util.Zip4jConstants;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author:cuijialei
// * @Date: 2018/8/13
// * @Describe
// * 引用了外部的包
// *
// *         <dependency>
// *             <groupId>net.lingala.zip4j</groupId>
// *             <artifactId>zip4j</artifactId>
// *             <version>1.3.2</version>
// *         </dependency>
// */
//public class ZipUtil {
//
//    /**
//     * 压缩文件
//     * @param zipPath
//     * @param fileList
//     * @param password 传空表示无密码
//     * @return
//     */
//    public static void zipFile(String zipPath, List<String> fileList ,String password){
//        try {
//            //创建压缩文件
//            ZipFile zipFile = new ZipFile(zipPath);
//            ArrayList<File> files = new ArrayList<>();
//            for (String s : fileList) {
//                files.add(new File(s));
//            }
//            //设置压缩文件参数
//            ZipParameters parameters = new ZipParameters();
//            //设置压缩方法
//            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
//            //设置压缩级别
//            //DEFLATE_LEVEL_FASTEST     - Lowest compression level but higher speed of compression
//            //DEFLATE_LEVEL_FAST        - Low compression level but higher speed of compression
//            //DEFLATE_LEVEL_NORMAL  - Optimal balance between compression level/speed
//            //DEFLATE_LEVEL_MAXIMUM     - High compression level with a compromise of speed
//            //DEFLATE_LEVEL_ULTRA       - Highest compression level but low speed
//            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
//            if(!CommonUtils.isNullOrEmpty(password)){
//                //设置压缩文件加密
//                parameters.setEncryptFiles(true);
//                //设置加密方法
//                parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
//                //设置aes加密强度
//                parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
//                //设置密码
//                parameters.setPassword(password);
//            }
//            //添加文件到压缩文件
//            zipFile.addFiles(files, parameters);
//        } catch (Exception e) {
//            throw new ServiceException("CaCodeEnum.ZIP_ERROR.getCode()","CaCodeEnum.ZIP_ERROR.getChDesc()");
//        }
//    }
//
//    public static void zipFile(String zipPath, String filePath ,String password){
//        try {
//            //创建压缩文件
//            ZipFile zipFile = new ZipFile(zipPath);
//            ArrayList<File> files = new ArrayList<>();
//            files.add(new File(filePath));
//
//            //设置压缩文件参数
//            ZipParameters parameters = new ZipParameters();
//            //设置压缩方法
//            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
//            //设置压缩级别
//            //DEFLATE_LEVEL_FASTEST     - Lowest compression level but higher speed of compression
//            //DEFLATE_LEVEL_FAST        - Low compression level but higher speed of compression
//            //DEFLATE_LEVEL_NORMAL  - Optimal balance between compression level/speed
//            //DEFLATE_LEVEL_MAXIMUM     - High compression level with a compromise of speed
//            //DEFLATE_LEVEL_ULTRA       - Highest compression level but low speed
//            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
//            if(!CommonUtils.isNullOrEmpty(password)){
//                //设置压缩文件加密
//                parameters.setEncryptFiles(true);
//                //设置加密方法
//                parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
//                //设置aes加密强度
//                parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
//                //设置密码
//                parameters.setPassword(password);
//            }
//            //添加文件到压缩文件
//            zipFile.addFiles(files, parameters);
//        } catch (Exception e) {
//            throw new ServiceException("CaCodeEnum.ZIP_ERROR.getCode()","CaCodeEnum.ZIP_ERROR.getChDesc()");
//        }
//    }
//}
