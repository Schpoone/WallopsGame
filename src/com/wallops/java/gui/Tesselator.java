package com.wallops.java.gui;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.opengl.GL11;

/**
 * This class is used to easily draw different shapes of different colors to the screen.
 * The class uses nio buffers to easily pass in the different variables associated with each vertex.
 * This method is more efficient and customizable than using the usual glBegin() and glEnd() setup.
 * 
 * @author PureChaose
 *
 */
public class Tesselator {

	/** ByteBuffer used to store reference variables in OpenGL */
	private ByteBuffer byteBuffer;

	/** same memory as ByteBuffer, but for any specifically int values */
	private IntBuffer intBuffer;

	/** same memory as ByteBuffer, but for any specifically float values */
	private FloatBuffer floatBuffer;

	/** same memory as ByteBuffer, but for any specifically short values */
	private ShortBuffer shortBuffer;

	/** used for adding values easily to vertexes, increments by 8, stored [x,y,z,u,v,color,normal,brightness] */
	private int[] rawBuffer;

	private int rawBufferIndex;
	private int vertexCount;
	private int bufferSize;

	/** the color in RGBA */
	private int color;
	private int brightness;

	/** whether this tesselator call has color */
	private boolean hasColor;
	private boolean hasBrightness;
	private boolean hasTexture;

	/** Tells LWJGL what mode to draw in */
	private int mode;
	/** a Tesselator instance for drawing shapes */
	public static final Tesselator instance = new Tesselator(2097152);


	public Tesselator(int bufferSize) {
		this.bufferSize = bufferSize;
		this.byteBuffer = ByteBuffer.allocateDirect(bufferSize*4).order(ByteOrder.nativeOrder());
		this.intBuffer = this.byteBuffer.asIntBuffer();
		this.floatBuffer = this.byteBuffer.asFloatBuffer();
		this.shortBuffer = this.byteBuffer.asShortBuffer();
		this.rawBuffer = new int[bufferSize];
	}

	/**
	 * adds a vertex with coordinates x, y, and z to the shape being defined
	 * @param x x coordinate of the new vertex
	 * @param y y coordinate of the new vertex
	 * @param z z coordinate of the new vertex
	 * @see #addVertexTex(double, double, double, float, float)
	 */
	public void addVertex(double x, double y, double z) {
		this.rawBuffer[rawBufferIndex + 0] = Float.floatToRawIntBits((float)x);
		this.rawBuffer[rawBufferIndex + 1] = Float.floatToRawIntBits((float)y);
		this.rawBuffer[rawBufferIndex + 2] = Float.floatToRawIntBits((float)z);

		if(this.hasColor) {
			this.rawBuffer[this.rawBufferIndex + 5] = Float.floatToRawIntBits((float)this.color);
		}

		if(this.hasBrightness) {
			this.rawBuffer[this.rawBufferIndex + 7] = Float.floatToRawIntBits((float)this.brightness);
		}

		this.rawBufferIndex += 8;
		this.vertexCount++;

		if (this.vertexCount % 4 == 0 && this.rawBufferIndex >= this.bufferSize - 32)
		{
			this.draw();
		}
	}
	
	/**
	 * adds a vertex with coordinates x, y, and z to the shape being defined, and with coordinates on a bound texture u and v, where u is the x coord on the texture divided by the texture's width,and v is the y and height equivalent
	 * @param x x coordinate of the new vertex
	 * @param y y coordinate of the new vertex
	 * @param z z coordinate of the new vertex
	 * @param u the x coordinate of the point (on the bound texture) associated with this vertex divided by the width of the texture
	 * @param v the y coordinate of the point (on the bound texture) associated with this vertex divided by the height of the texture
	 * @see #addVertex(double, double, double)
	 */
	public void addVertexTex(double x, double y, double z, float u, float v) {
		this.rawBuffer[rawBufferIndex + 3] = Float.floatToRawIntBits(u);
		this.rawBuffer[rawBufferIndex + 4] = Float.floatToRawIntBits(v);
		this.hasTexture = true;
		this.addVertex(x, y, z);
	}

	/**
	 * resets the tesselator for drawing a new shape
	 */
	public void reset() {
		this.vertexCount = 0;
		this.byteBuffer.clear();
		this.rawBufferIndex = 0;
		this.hasBrightness = false;
		this.hasColor = false;
		this.hasTexture = false;
	}

	/**
	 * draws the shape designated by all the vertices to the screen, and resets the tesselator for a new shape
	 */
	public void draw() {
		if(this.vertexCount > 0) {
			// set things up to pass into LWJGL
			intBuffer.clear();
			intBuffer.put(this.rawBuffer, 0, this.rawBufferIndex);
			this.byteBuffer.position(0);

			// add the things to be drawn
			if(this.hasColor) {
				this.byteBuffer.position(20);
				GL11.glColorPointer(4, true, 32, this.byteBuffer);
				GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
			}

			if(this.hasBrightness) {
				this.shortBuffer.position(14);
				GL11.glTexCoordPointer(2, 32, this.shortBuffer);
				GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
			}
			
			if(this.hasTexture) {
				this.floatBuffer.position(3);
                GL11.glTexCoordPointer(2, 32, this.floatBuffer);
                GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
			}
			this.floatBuffer.position(0);
			GL11.glVertexPointer(3, 32, this.floatBuffer);
			GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);

			// draw
			GL11.glDrawArrays(this.mode, 0, this.vertexCount);

			// disable enabled GL states
			GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
			if(this.hasColor) {
				GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
			}

			if(this.hasBrightness) {
				GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
			}
			
			if(this.hasTexture) {
				GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
			}
		}

		this.reset();
	}
	
	public void startDrawingQuads() {
		this.mode = 7;
	}
	
	public void setDrawMode(int mode) {
		this.mode = mode;
	}
}
