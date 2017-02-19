package drawing;

import entities.TVChannel;
import entities.TVProgram;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;

/**
 * Created by Yuliya Pedash on 18.02.2017.
 */
public class ImageProgramWriter extends ImageWriter {
    private static final String PATH = "src\\main\\resources\\";
    private static final String EXT = ".jpg";
    private static final int TITLE_HEIGHT = 90;
    private static final int TOP_INDENT = 10;
    private static final int BUTOM_INDENT = 10;
    private static final int LEFT_INDENT = 10;
    private static final int RIGHT_INDENT = 10;
    final int TV_PROGRAM_HEIGHT = 50;
    final int BEFORE_PROGRAM_INDENT = 30;
    final int LINE_THICKNESS = 2;
    TVChannel channel;

    public ImageProgramWriter(TVChannel channel) {
        this.channel = channel;
        image = getImgTVProgram();
        this.fileName = PATH + channel.getName() + channel.getDate() + EXT;
        saveImageAs(fileName);
    }

    public ImageProgramWriter(File imageFile, TVChannel channel) {
        super(imageFile);
        this.channel = channel;
    }

    public ImageProgramWriter(String imageFilePath, TVChannel channel) {
        super(imageFilePath);
        this.channel = channel;
    }

    public TVChannel getChannel() {
        return channel;
    }

    public void setChannel(TVChannel channel) {
        this.channel = channel;
    }

    private BufferedImage getImgTVProgram() {
        int progrNumber = channel.getPrograms().size();
        BufferedImage image = new BufferedImage(400, progrNumber * TV_PROGRAM_HEIGHT + (LINE_THICKNESS + BEFORE_PROGRAM_INDENT) * (progrNumber - 1) + TITLE_HEIGHT + TOP_INDENT + BUTOM_INDENT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, image.getWidth(), image.getHeight());
        graphics2D.dispose();
        return image;
    }

    private void writeTVProgram(int x, int y, TVProgram tvProgram, int programFontSize) {
        final int LINE_INDENT = 3;
        final int IMG_SIZE = 50;
        final int TIME_WIDTH = 40;
        final int TEXT_HEIGHT = 13;
        final int INDENT_HEIGHT = 10;
        final String TITLE_FONT = "Arial";
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        if (tvProgram.getRealtimeBegin() != null) {
            String programBegins = format.format(tvProgram.getRealtimeBegin());
            addTextToImage(programBegins, Color.black, TITLE_FONT, BOLD, programFontSize, x, y);
        }
        if (tvProgram.getImageLink() != null) {
            Image programImage = null;
            BufferedImage smallProgrImg = null;
            try {
                programImage = ImageIO.read(new URL(tvProgram.getImageLink()));
                smallProgrImg = GetImgOtherSize(programImage, IMG_SIZE, IMG_SIZE);
                x += TIME_WIDTH + INDENT_HEIGHT;
                drawBuffImage(smallProgrImg, x, y - TEXT_HEIGHT);
                x += IMG_SIZE + INDENT_HEIGHT;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                image.flush();
                smallProgrImg.flush();
            }
        }
        if (tvProgram.getTitle() != null) {
            addTextToImage(tvProgram.getTitle(), Color.black, TITLE_FONT, BOLD, programFontSize, x, y);
        }
        if (tvProgram.getSubtitle() != null) {
            y += TEXT_HEIGHT + LINE_INDENT;
            addTextToImage(tvProgram.getSubtitle(), Color.black, TITLE_FONT, PLAIN, programFontSize, x, y);
        }
    }

    public void writeAll() {
        List<TVProgram> programs = channel.getPrograms();
        String title = "Телепрограма";
        String titleFont = "Arial";
        final int TITLE_SIZE1 = 30;
        final int PROGRAM_FONT_SIZE = 15;
        final int WIDTH = image.getWidth();
        final int LINE_INDENT = 3;
        int currentX = 0;
        int currentY = 0;
        int i = 0;
        int numb = programs.size();

        currentX = WIDTH / 2 - title.length() / 4 * TITLE_SIZE1;
        currentY = TOP_INDENT + TITLE_SIZE1;
        addTextToImage(title, Color.black, titleFont, BOLD, TITLE_SIZE1, currentX, currentY);
        currentX = WIDTH / 2 - channel.getName().length() / 4 * TITLE_SIZE1;
        currentY += TITLE_SIZE1 + LINE_INDENT;
        addTextToImage(channel.getName(), Color.RED, titleFont, BOLD, TITLE_SIZE1, currentX, currentY);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(channel.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("EEEE, d MMMM, yyyy");
        String stringDate = newFormat.format(date);
        currentX = WIDTH / 2 - stringDate.length() / 4 * TITLE_SIZE1;
        currentY += TITLE_SIZE1 + LINE_INDENT;
        addTextToImage(stringDate, Color.black, titleFont, PLAIN, TITLE_SIZE1, currentX, currentY);
        currentY += TITLE_SIZE1 + LINE_INDENT;
        currentX = LEFT_INDENT;
        currentX += LINE_INDENT;
        for (TVProgram tvProgram :
                programs) {
            writeTVProgram(currentX, currentY, tvProgram, PROGRAM_FONT_SIZE);
            currentY += TV_PROGRAM_HEIGHT;
            i++;
            if (i != numb) {
                drawLine(currentX, currentY, WIDTH - RIGHT_INDENT, currentY, LINE_THICKNESS, Color.red);
                currentY += BEFORE_PROGRAM_INDENT;
            }
        }
    }
}
