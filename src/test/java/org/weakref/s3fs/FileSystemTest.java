package org.weakref.s3fs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
<<<<<<< HEAD
import static org.mockito.Mockito.*;
import static org.weakref.s3fs.S3Path.forPath;
=======
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
>>>>>>> 3265534a96066e504cc70d7244476758cf0628d6

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
<<<<<<< HEAD
import java.util.Set;
=======
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Properties;
>>>>>>> 3265534a96066e504cc70d7244476758cf0628d6

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.weakref.s3fs.util.AmazonS3ClientMock;

import com.github.marschall.memoryfilesystem.MemoryFileSystemBuilder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class FileSystemTest {
	
	private FileSystem fs;
	private FileSystem fsMem;
	private S3FileSystemProvider provider;
	
	@Before
<<<<<<< HEAD
	public void setup() throws IOException {
		
		S3FileSystemProvider provider = spy(new S3FileSystemProvider());
		
		AmazonS3ClientMock clientMock = new AmazonS3ClientMock();
		S3FileSystem s3ileS3FileSystem = new S3FileSystem(provider, clientMock, "endpoint");
		doReturn(s3ileS3FileSystem).when(provider).createFileSystem(any(URI.class), anyObject(), anyObject());
				
		try {
			fs = FileSystems.getFileSystem(URI.create("s3:///"));		
		} catch(FileSystemNotFoundException e){
			fs = FileSystems.newFileSystem(URI.create("s3:///"), ImmutableMap.<String, Object>of());
=======
	public void cleanup() throws IOException{
		fsMem = MemoryFileSystemBuilder.newLinux().build("basescheme");
		// close old
		try{
			FileSystems.getFileSystem(URI.create("s3:///")).close();
		}
		catch(FileSystemNotFoundException e){}
		// create a new
		provider = spy(new S3FileSystemProvider());
		doReturn(new Properties()).when(provider).loadAmazonProperties();
		// by default with two buckets
		Path bucketA = Files.createDirectories(fsMem.getPath("/base", "bucketA"));
		Path bucketB = Files.createDirectories(fsMem.getPath("/base", "bucketB"));
		Files.createFile(bucketA.resolve("file1"));
		Files.createFile(bucketB.resolve("file2"));
		mockFileSystem(fsMem.getPath("/base"));
		//
		fs = provider.newFileSystem(URI.create("s3:///"), buildFakeEnv());
	}
	
	@After
	public void closeMemory() throws IOException{
		fsMem.close();
	}
	
	
	private void mockFileSystem(final Path memoryBucket){
		try {
			AmazonS3ClientMock clientMock = new AmazonS3ClientMock(memoryBucket);
			S3FileSystem s3ileS3FileSystem = new S3FileSystem(provider, clientMock, "endpoint");
			doReturn(s3ileS3FileSystem).when(provider).createFileSystem(any(URI.class), anyObject(), anyObject());
		} catch (IOException e) {
			throw new RuntimeException(e);
>>>>>>> 3265534a96066e504cc70d7244476758cf0628d6
		}
	}

	@Test
	public void getPath() throws IOException {
		
		assertEquals(fs.getPath("/bucket/path/to/file"),
				fs.getPath("/bucket/path/to/file"));
		assertEquals(fs.getPath("/bucket", "path", "to", "file"),
				fs.getPath("/bucket/path/to/file"));
		assertEquals(fs.getPath("bucket", "path", "to", "file"),
				fs.getPath("/bucket/path/to/file"));
		assertEquals(fs.getPath("bucket", "path", "to", "dir/"),
				fs.getPath("/bucket/path/to/dir/"));
		assertEquals(fs.getPath("bucket", "path/", "to/", "dir/"),
				fs.getPath("/bucket/path/to/dir/"));
		assertEquals(fs.getPath("/bucket//path/to//file"),
				fs.getPath("/bucket/path/to/file"));
		assertEquals(fs.getPath("path/to//file"),
				fs.getPath("path/to/file"));
	}
	
	@Test
	public void readOnlyAlwaysFalse(){
		assertTrue(!fs.isReadOnly());
	}
	
	@Test
	public void getSeparatorSlash(){
		assertEquals("/", fs.getSeparator());
		assertEquals("/", S3Path.PATH_SEPARATOR);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void getPathMatcherThrowException(){
		fs.getPathMatcher("");
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void getUserPrincipalLookupServiceThrowException(){
		fs.getUserPrincipalLookupService();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void newWatchServiceThrowException() throws Exception {
		fs.newWatchService();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getPathWithoutBucket() {
		fs.getPath("//path/to/file");
	}
	
	@Test
<<<<<<< HEAD
	public void getFileStoresReturnEmptyList(){
		Iterable<FileStore> result = fs.getFileStores();
		
		assertNotNull(result);
		assertNotNull(result.iterator());
		assertTrue(!result.iterator().hasNext());
=======
	public void getRootDirectoriesReturnBuckets() {
		
		Iterable<Path> paths = fs.getRootDirectories();
		
		assertNotNull(paths);
		
		int size = 0;
		boolean bucketNameA = false;
		boolean bucketNameB = false;
		
		for (Path path : paths) {
			String name = path.getFileName().toString();
			if (name.equals("bucketA")) {
				bucketNameA = true;
			}
			else if (name.equals("bucketB")) {
				bucketNameB = true;
			}
			size++;
		}
		
		
		assertEquals(2, size);
		assertTrue(bucketNameA);
		assertTrue(bucketNameB);
		
>>>>>>> 3265534a96066e504cc70d7244476758cf0628d6
	}
	
	@Test
	public void supportedFileAttributeViewsReturnBasic(){
		Set<String> operations = fs.supportedFileAttributeViews();
		
		assertNotNull(operations);
		assertTrue(!operations.isEmpty());
		
		for (String operation: operations){
			assertEquals("basic", operation);
		}
	}
	
	@Test
	public void getRootDirectories(){
		fs.getRootDirectories();
	}
	
	@Test
	public void close() throws IOException {
		assertTrue(fs.isOpen());
		fs.close();
		assertTrue(!fs.isOpen());
	}
	
	private Map<String, ?> buildFakeEnv(){
		return ImmutableMap.<String, Object> builder()
				.put(S3FileSystemProvider.ACCESS_KEY, "access key")
				.put(S3FileSystemProvider.SECRET_KEY, "secret key").build();
	}
}
