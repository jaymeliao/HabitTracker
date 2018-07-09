import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//Code created with the help of Stack Overflow question
//https://stackoverflow.com/questions/7092067/adding-a-background-image-to-a-panel-containing-other-components?lq=1
//Question by Rajesh:
//https://stackoverflow.com/users/896500/rajesh
//Answer by aioobe:
//https://stackoverflow.com/users/276052/aioobe

public class BackgroundPanel extends JPanel {
    Image bg = new ImageIcon("background.jpg").getImage(); // image from: https://wallpapertag.com/watercolor-background
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

