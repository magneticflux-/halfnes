package com.grapeshot.halfnes;

import com.grapeshot.halfnes.ui.HeadlessUI;
import com.grapeshot.halfnes.ui.PuppetController;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * @author Mitchell Skaggs
 */
public class HeadlessNES {

    public static final int scale = 4;

    private HeadlessNES() {
    }

    public static void main(String[] args) {
        HeadlessUI ui = new HeadlessUI(HeadlessNES.class.getClassLoader().getResource("nestest.nes"), true);

        for (int i = 0; i < 100; i++) {
            ui.runFrame();
        }
        ui.getController1().pressButton(PuppetController.Button.START);
        ui.runFrame();
        ui.getController1().releaseButton(PuppetController.Button.START);
        for (int i = 0; i < 100; i++) {
            ui.runFrame();
        }

        BufferedImage image = ui.getLastFrame();

        JFrame frame = new JFrame("Display") {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(image, 0, 0, image.getWidth() * scale, image.getHeight() * scale, this);
            }
        };
        frame.setSize(256 * scale, 224 * scale);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
