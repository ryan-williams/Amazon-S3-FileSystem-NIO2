package com.upplication.s3fs;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetBucketAclRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Owner;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.Region;
import com.amazonaws.services.s3.model.S3Object;
/**
 * Client Amazon S3
 * @see com.amazonaws.services.s3.AmazonS3Client
 */
public class AmazonS3Client {
	
	private AmazonS3 client;
	
	public AmazonS3Client(AmazonS3 client){
		this.client = client;
	}
	
	/**
	 * @see com.amazonaws.services.s3.AmazonS3Client#listBuckets()
	 */
	public List<Bucket> listBuckets() {
		return client.listBuckets();
	}
	
	/**
	 * @see com.amazonaws.services.s3.AmazonS3Client#listObjects(ListObjectsRequest)
	 */
	public ObjectListing listObjects(ListObjectsRequest request) {
		return client.listObjects(request);
	}
	
	/**
	 * @see com.amazonaws.services.s3.AmazonS3Client#getObject(String, String)
	 */
	public S3Object getObject(String bucketName, String key) {
		return client.getObject(bucketName, key);
	}
	
	/**
	 * @see com.amazonaws.services.s3.AmazonS3Client#putObject(String, String, File)
	 */
	public PutObjectResult putObject(String bucket, String key, File file) {
		return client.putObject(bucket, key, file);
	}
	
	/**
	 * @see com.amazonaws.services.s3.AmazonS3Client#putObject(String, String, java.io.InputStream, ObjectMetadata)
	 */
	public PutObjectResult putObject(String bucket, String keyName,
			InputStream inputStream, ObjectMetadata metadata) {
		return client.putObject(bucket, keyName, inputStream, metadata);
	}
	
	/**
	 * @see com.amazonaws.services.s3.AmazonS3Client#deleteObject(String, String)
	 */
	public void deleteObject(String bucket, String key) {
		client.deleteObject(bucket, key);
	}
	
	/**
	 * @see com.amazonaws.services.s3.AmazonS3Client#copyObject(String, String, String, String)
	 */
	public CopyObjectResult copyObject(String sourceBucketName, String sourceKey, String destinationBucketName,
			String destinationKey) {
		return client.copyObject(sourceBucketName, sourceKey, destinationBucketName, destinationKey);
	}
	
	/**
	 * @see com.amazonaws.services.s3.AmazonS3Client#getBucketAcl(String)
	 */
	public AccessControlList getBucketAcl(String bucket) {
		return client.getBucketAcl(bucket);
	}
	
	/**
	 * @see com.amazonaws.services.s3.AmazonS3Client#getS3AccountOwner()
	 */
	public Owner getS3AccountOwner() {
		return client.getS3AccountOwner();
	}
	
	/**
	 * @see com.amazonaws.services.s3.AmazonS3Client#setEndpoint(String)
	 */
	public void setEndpoint(String endpoint) {
		client.setEndpoint(endpoint);
	}
	
	/**
	 * @see com.amazonaws.services.s3.AmazonS3Client#getObjectAcl(String, String)
	 */
	public AccessControlList getObjectAcl(String bucketName, String key) {
		return client.getObjectAcl(bucketName, key);
	}
	
	/**
	 * @see com.amazonaws.services.s3.AmazonS3Client#getObjectMetadata(String, String)
	 */
	public ObjectMetadata getObjectMetadata(String bucketName, String key) {
		return client.getObjectMetadata(bucketName, key);
	}

    /**
     * @see com.amazonaws.services.s3.AmazonS3Client#listNextBatchOfObjects(com.amazonaws.services.s3.model.ObjectListing)
     */
    public ObjectListing listNextBatchOfObjects(ObjectListing objectListing) {
        return client.listNextBatchOfObjects(objectListing);
    }

	/**
	 * @param bucketName
	 * @return
	 * @throws AmazonClientException
	 * @throws AmazonServiceException
	 * @see com.amazonaws.services.s3.AmazonS3#doesBucketExist(java.lang.String)
	 */
	public boolean doesBucketExist(String bucketName) throws AmazonClientException, AmazonServiceException {
		return client.doesBucketExist(bucketName);
	}

	/**
	 * @param createBucketRequest
	 * @return
	 * @throws AmazonClientException
	 * @throws AmazonServiceException
	 * @see com.amazonaws.services.s3.AmazonS3#createBucket(com.amazonaws.services.s3.model.CreateBucketRequest)
	 */
	public Bucket createBucket(CreateBucketRequest createBucketRequest) throws AmazonClientException, AmazonServiceException {
		return client.createBucket(createBucketRequest);
	}

	/**
	 * @param bucketName
	 * @return
	 * @throws AmazonClientException
	 * @throws AmazonServiceException
	 * @see com.amazonaws.services.s3.AmazonS3#createBucket(java.lang.String)
	 */
	public Bucket createBucket(String bucketName) throws AmazonClientException, AmazonServiceException {
		return client.createBucket(bucketName);
	}

	/**
	 * @param bucketName
	 * @param region
	 * @return
	 * @throws AmazonClientException
	 * @throws AmazonServiceException
	 * @see com.amazonaws.services.s3.AmazonS3#createBucket(java.lang.String, com.amazonaws.services.s3.model.Region)
	 */
	public Bucket createBucket(String bucketName, Region region) throws AmazonClientException, AmazonServiceException {
		return client.createBucket(bucketName, region);
	}

	/**
	 * @param bucketName
	 * @param region
	 * @return
	 * @throws AmazonClientException
	 * @throws AmazonServiceException
	 * @see com.amazonaws.services.s3.AmazonS3#createBucket(java.lang.String, java.lang.String)
	 */
	public Bucket createBucket(String bucketName, String region) throws AmazonClientException, AmazonServiceException {
		return client.createBucket(bucketName, region);
	}

	/**
	 * @param getBucketAclRequest
	 * @return
	 * @throws AmazonClientException
	 * @throws AmazonServiceException
	 * @see com.amazonaws.services.s3.AmazonS3#getBucketAcl(com.amazonaws.services.s3.model.GetBucketAclRequest)
	 */
	public AccessControlList getBucketAcl(GetBucketAclRequest getBucketAclRequest) throws AmazonClientException, AmazonServiceException {
		return client.getBucketAcl(getBucketAclRequest);
	}
}