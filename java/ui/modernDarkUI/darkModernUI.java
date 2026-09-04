import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class darkModernUI extends JFrame {

    // ---------- theme ----------
    private static final Color BG = new Color(13, 17, 23);
    private static final Color CARD = new Color(22, 27, 34);
    private static final Color BORDER = new Color(48, 54, 61);
    private static final Color TEXT = new Color(230, 237, 243);
    private static final Color SUBTLE = new Color(139, 148, 158);

    private static final Color TOPBTN_MIN = new Color(251, 188, 4);
    private static final Color TOPBTN_MAX = new Color(52, 199, 89);
    private static final Color TOPBTN_CLOSE = new Color(248, 81, 73);

    // ---------- color enum ----------
    public enum AllowedColor {
        blue, green, red, yellow, orange
    }

    private static Color mapColor(AllowedColor c) {
        if (c == null) return new Color(56, 139, 253);
        switch (c) {
            case green: return new Color(35, 134, 54);
            case red: return new Color(218, 54, 51);
            case yellow: return new Color(187, 128, 9);
            case orange: return new Color(251, 133, 0);
            case blue:
            default: return new Color(56, 139, 253);
        }
    }

    // ---------- callback ----------
    @FunctionalInterface
    public interface ActionFn {
        void run();
    }

    // ---------- config ----------
    public static class ConfigEnhanced {
        public String title = "Lorem Ipsum";
        public boolean showSubtitle = true;
        public String subtitle = "dolor sit amet";
        public int showLines = 5; // 0..5

        public final Line l1 = new Line();
        public final Line l2 = new Line();
        public final Line l3 = new Line();
        public final Line l4 = new Line();
        public final Line l5 = new Line();

        public static ConfigEnhanced create() {
            return new ConfigEnhanced();
        }

        public static class Line {
            public final Txt txt = new Txt();
            public final Btn button1 = new Btn();
            public final Btn button2 = new Btn();
            public final Btn button3 = new Btn();
        }

        public static class Txt {
            public boolean visible = true;
            public String value = "consetetur sadipscing elitr";
        }

        public static class Btn {
            public boolean visible = true;
            public String txt = "Button";
            public AllowedColor color = AllowedColor.blue;
            public final List<ActionFn> executeFunctions = new ArrayList<>();

            public Btn functions(ActionFn... fn) {
                executeFunctions.clear();
                if (fn != null) {
                    for (ActionFn f : fn) {
                        if (f != null) executeFunctions.add(f);
                    }
                }
                return this;
            }
        }
    }

    private final ConfigEnhanced cfg;
    private boolean isFullscreen = false;
    private Rectangle windowedBounds = new Rectangle(120, 80, 1100, 720);

    private darkModernUI(ConfigEnhanced cfg) {
        this.cfg = sanitize(cfg);

        setTitle(this.cfg.title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setBounds(windowedBounds);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        root.setBorder(BorderFactory.createLineBorder(BORDER, 1));

        JPanel topBar = createTopBar();
        JScrollPane content = createContent();

        root.add(topBar, BorderLayout.NORTH);
        root.add(content, BorderLayout.CENTER);

        setContentPane(root);
        enableWindowDragging(topBar);
    }

    private static ConfigEnhanced sanitize(ConfigEnhanced in) {
        ConfigEnhanced c = (in == null ? ConfigEnhanced.create() : in);
        if (c.title == null) c.title = "";
        if (c.subtitle == null) c.subtitle = "";
        if (c.showLines < 0) c.showLines = 0;
        if (c.showLines > 5) c.showLines = 5;
        return c;
    }

    private JPanel createTopBar() {
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(BG);
        top.setBorder(new EmptyBorder(10, 14, 10, 14));

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(cfg.title, SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(TEXT);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        center.add(title);

        if (cfg.showSubtitle) {
            JLabel subtitle = new JLabel(cfg.subtitle, SwingConstants.CENTER);
            subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            subtitle.setForeground(SUBTLE);
            subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
            center.add(Box.createVerticalStrut(2));
            center.add(subtitle);
        }

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        right.setOpaque(false);

        JButton minBtn = createTopButton("—", TOPBTN_MIN, e -> setState(Frame.ICONIFIED));
        JButton fullBtn = createTopButton("▢", TOPBTN_MAX, e -> toggleFullscreen());
        JButton closeBtn = createTopButton("✕", TOPBTN_CLOSE, e -> System.exit(0));

        right.add(minBtn);
        right.add(fullBtn);
        right.add(closeBtn);

        top.add(center, BorderLayout.CENTER);
        top.add(right, BorderLayout.EAST);
        return top;
    }

    private JScrollPane createContent() {
        JPanel list = new JPanel();
        list.setOpaque(false);
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
        list.setBorder(new EmptyBorder(14, 20, 20, 20));

        List<ConfigEnhanced.Line> lines = getLines();
        for (int i = 0; i < cfg.showLines; i++) {
            list.add(createRow(lines.get(i), i));
            list.add(Box.createVerticalStrut(12));
        }

        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getViewport().setBackground(BG);
        scroll.getVerticalScrollBar().setUnitIncrement(14);
        return scroll;
    }

    private List<ConfigEnhanced.Line> getLines() {
        List<ConfigEnhanced.Line> lines = new ArrayList<>(5);
        lines.add(cfg.l1);
        lines.add(cfg.l2);
        lines.add(cfg.l3);
        lines.add(cfg.l4);
        lines.add(cfg.l5);
        return lines;
    }

    private JPanel createRow(ConfigEnhanced.Line line, int rowIndex) {
        JPanel row = new JPanel(new BorderLayout(12, 0));
        row.setBackground(CARD);
        row.setBorder(new EmptyBorder(14, 16, 14, 16));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 86));

        JLabel left = new JLabel(line.txt.value == null ? "" : line.txt.value);
        left.setForeground(TEXT);
        left.setFont(new Font("SansSerif", Font.PLAIN, 14));
        left.setVisible(line.txt.visible);

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btns.setOpaque(false);

        addConfiguredButton(btns, line.button1, rowIndex, 1);
        addConfiguredButton(btns, line.button2, rowIndex, 2);
        addConfiguredButton(btns, line.button3, rowIndex, 3);

        row.add(left, BorderLayout.WEST);
        row.add(btns, BorderLayout.EAST);
        return row;
    }

    private void addConfiguredButton(JPanel parent, ConfigEnhanced.Btn b, int rowIndex, int buttonIndex) {
        AnimatedButton btn = new AnimatedButton(
                b.txt == null ? "" : b.txt,
                mapColor(b.color)
        );
        btn.setVisible(b.visible);
        btn.addActionListener(e -> {
            for (ActionFn fn : b.executeFunctions) {
                fn.run();
            }
            if (b.executeFunctions.isEmpty()) {
                System.out.println("No function for row " + (rowIndex + 1) + ", button " + buttonIndex);
            }
        });
        parent.add(btn);
    }

    private JButton createTopButton(String text, Color base, ActionListener action) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(base);
        btn.setBorder(new EmptyBorder(6, 11, 6, 11));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(action);
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(base.brighter()); }
            public void mouseExited(MouseEvent e) { btn.setBackground(base); }
        });
        return btn;
    }

    private void toggleFullscreen() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (!isFullscreen) {
            windowedBounds = getBounds();
            dispose();
            setUndecorated(true);
            gd.setFullScreenWindow(this);
            validate();
            isFullscreen = true;
        } else {
            gd.setFullScreenWindow(null);
            dispose();
            setUndecorated(true);
            setBounds(windowedBounds);
            setVisible(true);
            validate();
            isFullscreen = false;
        }
    }

    private void enableWindowDragging(JPanel dragArea) {
        final Point[] mouseDown = {null};

        dragArea.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) { mouseDown[0] = e.getPoint(); }
        });

        dragArea.addMouseMotionListener(new MouseMotionAdapter() {
            @Override public void mouseDragged(MouseEvent e) {
                if (!isFullscreen && mouseDown[0] != null) {
                    Point p = e.getLocationOnScreen();
                    setLocation(p.x - mouseDown[0].x, p.y - mouseDown[0].y);
                }
            }
        });
    }

    static class AnimatedButton extends JButton {
        private final Color base;
        private float hover = 0f;
        private float press = 0f;
        private Timer timer;

        AnimatedButton(String text, Color base) {
            super(text);
            this.base = base;

            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("SansSerif", Font.BOLD, 13));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBorder(new EmptyBorder(9, 16, 9, 16));

            addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { animateTo(1f, press); }
                @Override public void mouseExited(MouseEvent e)  { animateTo(0f, 0f); }
                @Override public void mousePressed(MouseEvent e) { animateTo(hover, 1f); }
                @Override public void mouseReleased(MouseEvent e){ animateTo(hover, 0f); }
            });
        }

        private void animateTo(float targetHover, float targetPress) {
            if (timer != null && timer.isRunning()) timer.stop();
            timer = new Timer(16, null);
            timer.addActionListener(e -> {
                hover += (targetHover - hover) * 0.22f;
                press += (targetPress - press) * 0.28f;
                if (Math.abs(targetHover - hover) < 0.01f && Math.abs(targetPress - press) < 0.01f) {
                    hover = targetHover;
                    press = targetPress;
                    timer.stop();
                }
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            float scale = 1f - (0.04f * press);
            int sw = (int) (w * scale), sh = (int) (h * scale);
            int sx = (w - sw) / 2, sy = (h - sh) / 2;

            Color hoverCol = mix(base, base.brighter(), hover);
            Color top = mix(hoverCol.brighter(), Color.WHITE, 0.08f * hover);
            Color bottom = mix(hoverCol.darker(), Color.BLACK, 0.20f);

            if (hover > 0.02f) {
                g2.setColor(new Color(base.getRed(), base.getGreen(), base.getBlue(), (int) (90 * hover)));
                g2.fillRoundRect(sx - 3, sy - 3, sw + 6, sh + 6, 16, 16);
            }

            g2.setPaint(new GradientPaint(0, sy, top, 0, sy + sh, bottom));
            g2.fillRoundRect(sx, sy, sw, sh, 14, 14);

            g2.setColor(new Color(255, 255, 255, (int) (40 + hover * 70)));
            g2.drawRoundRect(sx, sy, sw - 1, sh - 1, 14, 14);

            FontMetrics fm = g2.getFontMetrics();
            int tx = (w - fm.stringWidth(getText())) / 2;
            int ty = (h + fm.getAscent() - fm.getDescent()) / 2;
            g2.setColor(Color.WHITE);
            g2.drawString(getText(), tx, ty);

            g2.dispose();
        }

        private Color mix(Color a, Color b, float t) {
            t = Math.max(0f, Math.min(1f, t));
            int r = (int) (a.getRed() + (b.getRed() - a.getRed()) * t);
            int g = (int) (a.getGreen() + (b.getGreen() - a.getGreen()) * t);
            int bl = (int) (a.getBlue() + (b.getBlue() - a.getBlue()) * t);
            return new Color(r, g, bl);
        }
    }

    // ---------- public API ----------
    public static void launchDefault() {
        ConfigEnhanced cfg = ConfigEnhanced.create();
        cfg.title = "Hello World";
        cfg.showSubtitle = true;
        cfg.subtitle = "You are welcome!";
        cfg.showLines = 5;

        cfg.l1.txt.visible = true; cfg.l1.txt.value = "Lorem Ipsum dolor sit amet";
        cfg.l1.button1.visible = true; cfg.l1.button1.txt = "Option 1"; cfg.l1.button1.color = AllowedColor.blue;
        cfg.l1.button1.functions(() -> sayHello1(), () -> sayHello2(), () -> sayHello3());
        cfg.l1.button2.visible = true; cfg.l1.button2.txt = "Option 2"; cfg.l1.button2.color = AllowedColor.green;
        cfg.l1.button2.functions(() -> sayHello1(), () -> sayHello2(), () -> sayHello3());
        cfg.l1.button3.visible = true; cfg.l1.button3.txt = "Option 3"; cfg.l1.button3.color = AllowedColor.red;
        cfg.l1.button3.functions(() -> sayHello1(), () -> sayHello2(), () -> sayHello3());

        cfg.l2.txt.value = "consetetur sadipscing elitr";
        cfg.l3.txt.value = "sed diam nonumy eirmod tempor invidunt";
        cfg.l4.txt.value = "ut labore et dolore magna aliquyam erat";
        cfg.l5.txt.value = "sed diam voluptua";

        launchMyConfig(cfg);
    }

    public static void launchMyConfig(ConfigEnhanced myConfig) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); } catch (Exception ignored) {}
            new darkModernUI(myConfig).setVisible(true);
        });
    }

    public static void launchMyConfig(String configClassPath) {
        // Example: "meineConfigDatei" OR "neuerOrdner.meineConfigDatei"
        try {
            Class<?> clazz = Class.forName(configClassPath);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            if (!(instance instanceof ConfigEnhanced)) {
                throw new IllegalArgumentException("Class must extend darkModernUI.ConfigEnhanced");
            }
            launchMyConfig((ConfigEnhanced) instance);
        } catch (Exception e) {
            throw new RuntimeException("Could not load config class: " + configClassPath, e);
        }
    }

    // ---------- demo helper functions ----------
    public static void sayHello1() { System.out.println("Hello 1"); }
    public static void sayHello2() { System.out.println("Hello 2"); }
    public static void sayHello3() { System.out.println("Hello 3"); }
}
