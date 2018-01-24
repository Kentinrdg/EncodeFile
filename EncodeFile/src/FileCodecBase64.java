
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCodecBase64 {

	public static void main(String args[]) throws Exception {

		/* Encode a file and write the encoded output to a text file. */
		FileCodecBase64 fileCodecBase64 = new FileCodecBase64();
		fileCodecBase64.encode("C:\\Users\\Kentin\\Desktop\\Cahier de charge projet SiiC.pdf",
				"C:\\Users\\Kentin\\Desktop\\Cahier de charge projet SiiC_encoded.pdf");

		/* Decode a file and write the decoded file to file system */
		fileCodecBase64.decode("C:\\Users\\Kentin\\Desktop\\Cahier de charge projet SiiC_encoded.pdf",
				"C:\\Users\\Kentin\\Desktop\\Cahier de charge projet SiiC__decoded.pdf");
	}

	/**
	 * This method converts the content of a source file into Base64 encoded data
	 * and saves that to a target file. If isChunked parameter is set to true, there
	 * is a hard wrap of the output encoded text.
	 */
	public void encode(String sourceFile, String targetFile) throws Exception {
		
		String saltStr = "123_$@lT‡Add$Up#&_123";
		byte[] saltBytes = saltStr.getBytes();

		System.out.println("saltBytes = " + saltBytes);
		byte[] base64EncodedData = java.util.Base64.getEncoder().encode(loadFileAsBytesArray(sourceFile));
		writeByteArraysToFile(targetFile, base64EncodedData);
	}

	public void decode(String sourceFile, String targetFile) throws Exception {
		byte[] decodedBytes = java.util.Base64.getDecoder().decode(loadFileAsBytesArray(sourceFile));
		writeByteArraysToFile(targetFile, decodedBytes);
	}

	/**
	 * This method loads a file from file system and returns the byte array of the
	 * content.
	 *
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public byte[] loadFileAsBytesArray(String fileName) throws Exception {

		File file = new File(fileName);
		int length = (int) file.length();
		BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));

		byte[] bytes = new byte[length];
		reader.read(bytes, 0, length);
		reader.close();
		return bytes;
	}

	/**
	 * This method writes byte array content into a file.
	 *
	 * @param fileName
	 * @param content
	 * @throws IOException
	 */
	public void writeByteArraysToFile(String fileName, byte[] content) throws IOException {
		File file = new File(fileName);
		BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
		writer.write(content);
		writer.flush();
		writer.close();
	}
}