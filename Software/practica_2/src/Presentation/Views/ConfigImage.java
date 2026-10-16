package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class extends JPanel to display an image as a background.
 */
public class ConfigImage extends JPanel {

    private BufferedImage backgroundImage;

    /**
     * Constructor that loads the image from a file.
     * @param imagePath The path of the image file.
     */
    public ConfigImage(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println("Error loading the image: " + e.getMessage());
        }
    }

    /**
     * Method to draw the image on the component.
     * @param graphics The Graphics context in which to paint.
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (backgroundImage != null) {
            Image scaledImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            graphics.drawImage(scaledImage, 0, 0, this);
        }
    }

    /**
     * Method to obtain the preferred dimension of the component.
     * @return The preferred size of the component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (backgroundImage != null) {
            return new Dimension(backgroundImage.getWidth(), backgroundImage.getHeight());
        } else {
            return super.getPreferredSize();
        }
    }
}
