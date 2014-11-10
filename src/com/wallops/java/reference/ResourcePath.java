package com.wallops.java.reference;

import java.io.File;

/**
 * This class is used for storing the location of files, and includes several utility functions. 
 * 
 * @author PureChaose
 * 
 */
public class ResourcePath {
	private String path;
	private String name;
	private String extension;
	private boolean inJar;
	private boolean directory;
	private boolean file;
	
	/**
	 * Equivalent to "src"+File.separator+"com"+File.separator+"wallops"+File.separator+"resources"+File.separator
	 */
	public static final String resourceDir = "src"+File.separator+"com"+File.separator+"wallops"+File.separator+"resources"+File.separator;
	public static final File resourceDirFile = new File(resourceDir);
	
	public ResourcePath(File path) {
		this.path = path.getAbsolutePath();
		this.extension = this.path.lastIndexOf(".")>this.path.lastIndexOf(File.separator) ? this.path.substring(this.path.lastIndexOf(".")+1) : null;
		this.inJar = this.path.contains(resourceDir);
		this.name = this.inJar ? this.path.substring(this.path.lastIndexOf(resourceDir)) : this.path;
		this.directory = path.isDirectory();
		this.file = path.isFile();
	}

	public ResourcePath(String path) {
		this(new File(path));
	}

	public boolean isInJar() {
		return inJar;
	}

	public String getExtension() {
		return extension;
	}

	public String getAbsolutePath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public boolean isDirectory() {
		return directory;
	}
	
	public boolean isFile() {
		return file;
	}
	
	@Override
	public boolean equals(Object other) {
		ResourcePath otherPath = (ResourcePath) other;
		return path.equals(otherPath.path);
	}
	
	@Override
	public int hashCode() {
		return path.hashCode();
	}

}
