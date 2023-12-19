package com.dailyon.reviewservice.common.aws;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
  private static final String IMG_BUCKET = "dailyon-static-dev";
  private static final String REVIEW_IMG_BUCKET_PREFIX = "review-img";
  private final AmazonS3 amazonS3;

  public String createFilePath(String img) {
    return String.format(
        "/%s/%s.%s", REVIEW_IMG_BUCKET_PREFIX, UUID.randomUUID(), img.split("\\.")[1]);
  }

  public String getPreSignedUrl(String filePath) {
    GeneratePresignedUrlRequest generatePresignedUrlRequest =
        getGeneratePreSignedUrlRequest(filePath.substring(1));
    return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
  }

  private GeneratePresignedUrlRequest getGeneratePreSignedUrlRequest(String fileName) {
    GeneratePresignedUrlRequest generatePresignedUrlRequest =
        new GeneratePresignedUrlRequest(IMG_BUCKET, fileName)
            .withMethod(HttpMethod.PUT)
            .withExpiration(getPreSignedUrlExpiration());

    generatePresignedUrlRequest.addRequestParameter(
        Headers.S3_CANNED_ACL, CannedAccessControlList.PublicRead.toString());
    return generatePresignedUrlRequest;
  }

  private Date getPreSignedUrlExpiration() {
    Date expiration = new Date();
    long expTimeMillis = expiration.getTime();
    expTimeMillis += 1000 * 60 * 5;
    expiration.setTime(expTimeMillis);
    return expiration;
  }
}
