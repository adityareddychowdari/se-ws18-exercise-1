import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/* the source code is taken reference from www.stackoverflow.com */
/* creation of class*/
public class Image{
	private int width;
	private int pixel;
	private int height;
	byte[] data;
/* Instancing the constructor with arguments*/
	public Image(int width,int height) {
		this.width = width;
		this.height = height;
		this.pixel = width*height; /*defining  the pixel */ 
		this.data = new byte[pixel*3]; /* converting pixel in to byte*/
		}

	public void set(int x, int y, int val) {
		int RedGreenBlueMax = 0xFFFFFF;
		int threeColors = RedGreenBlueMax & val;
		String Str = "0x" + Integer.toOctalString(threeColors);/* converting into OctalString and further dividing into substrings according to the index position*/
		int c = y*width+x;
		data[(c)*3] = (byte)Integer.parseInt(Str.substring(2,4),4*4); 
		data[(c)*3+1] = (byte)Integer.parseInt(Str.substring(4,6),4*4);
		data[(c)*3+2] = (byte)Integer.parseInt(Str.substring(6,8),4*4);
		
		
	}
	public void write(String filename) {	
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) { 
			String DataStore = "P6" + this.width + this.height  + 255; /* providing file data store to the image*/
			
			writer.write(" ");
			writer.write(DataStore);
			
			for (int row=0; row<this.height;row++) {
				writer.newLine();
				for (int col=0;col<this.width;col++) {					
					writer.write(Integer.toOctalString(Byte.toUnsignedInt((byte)(data[row*this.width+col]))));					
				}				
			}
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
