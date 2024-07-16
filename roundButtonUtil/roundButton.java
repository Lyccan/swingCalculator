package roundButtonUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class roundButton extends JButton {

    private ImageIcon icon;
    private Color borderColor = Color.DARK_GRAY;

    public roundButton(String label) {
        super(label);
        setOpaque(false);
    }

    public roundButton(char character) {
        super(Character.toString(character));
        setOpaque(false);
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setColor(getBackground().darker());
        } else {
            g2.setColor(getBackground());
        }
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));

        g2.setColor(getForeground());
        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        Rectangle textR = new Rectangle(0, 0, getWidth(), getHeight());
        g2.drawString(getText(), textR.x + (textR.width - fm.stringWidth(getText())) / 2,
                textR.y + ((textR.height - fm.getHeight()) / 2) + fm.getAscent());
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.setSize(d.width + 20, d.height + 20);
        return d;
    }

    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
        return shape.contains(x, y);
    }

}
