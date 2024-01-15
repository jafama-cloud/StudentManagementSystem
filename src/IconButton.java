import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class IconButton extends JButton {
    public IconButton(String text, String imagePath, int iconWidth, int iconHeight) {
        super(text);

        // Set icon
        URL resource = getClass().getResource(imagePath);
        if (resource != null) {
            ImageIcon icon = new ImageIcon(resource);
            Image scaledImage = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledImage));
        }
    }
}
