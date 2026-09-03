import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DarkModernUI extends JFrame {

    private static final Color BG = new Color(13, 17, 23);         // GitHub dark-like
    private static final Color CARD = new Color(22, 27, 34);
    private static final Color TEXT = new Color(230, 237, 243);
    private static final Color SUBTLE = new Color(139, 148, 158);
    private static final Color BTN1 = new Color(35, 134, 54);      // green
    private static final Color BTN2 = new Color(56, 139, 253);     // blue
    private static final Color BTN3 = new Color(218, 54, 51);      // red
    private static final Color CLOSE = new Color(248, 81, 73);

    public DarkModernUI() {
        setTitle("Modern Dark UI");
        setSize(980, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // eigener Close-Button oben rechts

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        root.setBorder(BorderFactory.createLineBorder(new Color(48, 54, 61), 1));

        // Topbar
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(BG);
        topBar.setBorder(new EmptyBorder(12, 18, 12, 18));

        JLabel title = new JLabel("Lorem Ipsum Dashboard", SwingConstants.CENTER);
        title.setForeground(TEXT);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));

        JButton closeBtn = new JButton("✕");
        styleTopButton(closeBtn, CLOSE);
        closeBtn.addActionListener(e -> System.exit(0));

        topBar.add(title, BorderLayout.CENTER);
        topBar.add(closeBtn, BorderLayout.EAST);

        // Content
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(10, 20, 20, 20));

        for (int i = 1; i <= 5; i++) {
            content.add(createRow("Frage " + i + ": Lorem ipsum dolor sit amet?"));
            content.add(Box.createVerticalStrut(12));
        }

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.getViewport().setBackground(BG);
        scroll.setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(14);

        root.add(topBar, BorderLayout.NORTH);
        root.add(scroll, BorderLayout.CENTER);

        setContentPane(root);
    }

    private JPanel createRow(String question) {
        JPanel row = new JPanel(new BorderLayout(12, 0));
        row.setBackground(CARD);
        row.setBorder(new EmptyBorder(14, 16, 14, 16));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel label = new JLabel(question);
        label.setForeground(TEXT);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.setOpaque(false);

        JButton b1 = createAnimatedButton("Super", BTN1);
        JButton b2 = createAnimatedButton("Okay", BTN2);
        JButton b3 = createAnimatedButton("Schlecht", BTN3);

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);

        row.add(label, BorderLayout.WEST);
        row.add(buttons, BorderLayout.EAST);
        return row;
    }

    private JButton createAnimatedButton(String text, Color base) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(base);
        btn.setBorder(new EmptyBorder(8, 14, 8, 14));
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);

        Color hover = base.brighter();
        Color normal = base;

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                animateColor(btn, normal, hover, 120);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                animateColor(btn, hover, normal, 120);
            }
        });

        return btn;
    }

    private void styleTopButton(JButton btn, Color color) {
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setBorder(new EmptyBorder(6, 10, 6, 10));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // Kleine Hover-Animation (Farb-Transition)
    private void animateColor(JButton button, Color from, Color to, int durationMs) {
        int frames = 12;
        int delay = Math.max(1, durationMs / frames);

        Timer timer = new Timer(delay, null);
        final int[] step = {0};

        timer.addActionListener((ActionEvent e) -> {
            float t = step[0] / (float) frames;
            int r = (int) (from.getRed() + t * (to.getRed() - from.getRed()));
            int g = (int) (from.getGreen() + t * (to.getGreen() - from.getGreen()));
            int b = (int) (from.getBlue() + t * (to.getBlue() - from.getBlue()));
            button.setBackground(new Color(r, g, b));

            step[0]++;
            if (step[0] > frames) {
                button.setBackground(to);
                timer.stop();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ignored) {}
            new DarkModernUI().setVisible(true);
        });
    }
}
