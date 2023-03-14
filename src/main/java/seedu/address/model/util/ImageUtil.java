package seedu.address.model.util;

import static seedu.address.model.person.Image.IMAGE_PATH;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains utility methods for {@code Image} file operations.
 */
public class ImageUtil {
    /**
     * Copies the image provided for use in BookFace.
     * @param stringPath Absolute path to image.
     * @return String file name of the new file.
     * @throws IOException when file I/O is unsuccessful.
     */
    public static String importImage(String stringPath) throws IOException, ParseException {
        try {
            Path path = Paths.get(stringPath);
            if (!Files.exists(path)) {
                throw new ParseException("Referenced file does not exist.");
            }
            assert Files.exists(path) : "File path invalid";
            String type = Files.probeContentType(path);

            if (!type.split("/")[0].equals("image")) {
                throw new ParseException("File at path is not an image");
            }

            assert type.contains("image") : "File type is not image";

            String newName = UUID.randomUUID().toString();
            Path destination = Paths.get(IMAGE_PATH + newName + ".png");

            Files.copy(path, destination, StandardCopyOption.REPLACE_EXISTING);

            return newName + ".png";
        } catch (IOException io) {
            throw io;
        }
    }

    /**
     * Delete imag egiven file name.
     * @param fileName Name of file to be deleted.
     * @throws IOException when file I/O is unsuccessful.
     */
    public static void deleteImage(String fileName) throws IOException {
        try {
            Path path = Paths.get(IMAGE_PATH + fileName);
            Files.delete(path);
        } catch (IOException io) {
            throw io;
        }
    }
}
