package drawing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Yuliya Pedash on 18.02.2017.
 */
public class ImageWriter {
    BufferedImage image;
    String fileName;

    public ImageWriter() {

    }

    public ImageWriter(File imageFile) {
        try {
            image = ImageIO.read(imageFile);
            fileName = imageFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageWriter(String imageFilePath) {
        this(new File(imageFilePath));
    }

    protected void addTextToImage(String text, Color color, String font, int type, int size, int topX, int topY) {
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setColor(color);
        graphics2D.setFont(new Font(font, type, size));
        graphics2D.drawString(text, topX, topY);
        graphics2D.dispose();
    }

    protected void writeLine(String string) {

    }

    public void saveImage() {
        saveImage(new File(fileName));
    }

    public void saveImageAs(String newName) {
        saveImage(new File(newName));
        this.fileName = newName;
    }

    public void saveImage(File file) {
        try {
            ImageIO.write(image, getFileType(file), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawBuffImage(BufferedImage drawnImage, int x, int y) {
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        graphics2D.drawImage(drawnImage, null, x, y);
        graphics2D.dispose();
    }

    public void drawLine(int x1, int y1, int x2, int y2, int thickness, Color color) {
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(thickness));
        graphics2D.drawLine(x1, y1, x2, y2);
    }

    private String getFileType(File file) {
        String fileName = file.getName();
        int indexBeforeExtension = fileName.lastIndexOf('.');
        if (indexBeforeExtension == -1) {
            throw new RuntimeException("Invalid File Name: " + fileName);
        }
        return fileName.substring(indexBeforeExtension + 1);
    }

    protected BufferedImage GetImgOtherSize(Image image, int newHeight, int newWidth) {
        BufferedImage newImage = new BufferedImage(newHeight, newWidth, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) newImage.getGraphics();
        graphics2D.drawImage(image, 0, 0, newHeight, newWidth, null);
        graphics2D.dispose();
        return newImage;
    }


}
