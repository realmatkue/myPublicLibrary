public class darkModernUIConfig extends darkModernUI.ConfigEnhanced {

    public darkModernUIConfig() {
        // Header
        this.title = "Hello World";
        this.showSubtitle = true;
        this.subtitle = "You are welcome!";
        this.showLines = 5;

        // ---------- l1 ----------
        this.l1.txt.visible = true;
        this.l1.txt.value = "How are you?";

        this.l1.button1.visible = true;
        this.l1.button1.txt = "Good";
        this.l1.button1.color = darkModernUI.AllowedColor.blue;
        this.l1.button1.functions(
                () -> sayHello1(),
                () -> sayHello2(),
                () -> sayHello3()
        );

        this.l1.button2.visible = true;
        this.l1.button2.txt = "Okay";
        this.l1.button2.color = darkModernUI.AllowedColor.green;
        this.l1.button2.functions(
                () -> sayHello1(),
                () -> sayHello2(),
                () -> sayHello3()
        );

        this.l1.button3.visible = true;
        this.l1.button3.txt = "Bad";
        this.l1.button3.color = darkModernUI.AllowedColor.red;
        this.l1.button3.functions(
                () -> sayHello1(),
                () -> sayHello2(),
                () -> sayHello3()
        );

        // ---------- l2 ----------
        this.l2.txt.visible = true;
        this.l2.txt.value = "How is your sleep quality?";

        this.l2.button1.visible = true;
        this.l2.button1.txt = "Good";
        this.l2.button1.color = darkModernUI.AllowedColor.green;
        this.l2.button1.functions(() -> sayHello1());

        this.l2.button2.visible = true;
        this.l2.button2.txt = "Medium";
        this.l2.button2.color = darkModernUI.AllowedColor.yellow;
        this.l2.button2.functions(() -> sayHello2());

        this.l2.button3.visible = true;
        this.l2.button3.txt = "Bad";
        this.l2.button3.color = darkModernUI.AllowedColor.orange;
        this.l2.button3.functions(() -> sayHello3());

        // ---------- l3 ----------
        this.l3.txt.visible = true;
        this.l3.txt.value = "How is your energy level?";

        this.l3.button1.visible = true;
        this.l3.button1.txt = "High";
        this.l3.button1.color = darkModernUI.AllowedColor.blue;
        this.l3.button1.functions(() -> sayHello1());

        this.l3.button2.visible = true;
        this.l3.button2.txt = "Normal";
        this.l3.button2.color = darkModernUI.AllowedColor.green;
        this.l3.button2.functions(() -> sayHello2());

        this.l3.button3.visible = true;
        this.l3.button3.txt = "Low";
        this.l3.button3.color = darkModernUI.AllowedColor.red;
        this.l3.button3.functions(() -> sayHello3());

        // ---------- l4 ----------
        this.l4.txt.visible = true;
        this.l4.txt.value = "How is your focus today?";

        this.l4.button1.visible = true;
        this.l4.button1.txt = "Excellent";
        this.l4.button1.color = darkModernUI.AllowedColor.green;
        this.l4.button1.functions(() -> sayHello1(), () -> sayHello2());

        this.l4.button2.visible = true;
        this.l4.button2.txt = "Okay";
        this.l4.button2.color = darkModernUI.AllowedColor.blue;
        this.l4.button2.functions(() -> sayHello2());

        this.l4.button3.visible = true;
        this.l4.button3.txt = "Weak";
        this.l4.button3.color = darkModernUI.AllowedColor.orange;
        this.l4.button3.functions(() -> sayHello3());

        // ---------- l5 ----------
        this.l5.txt.visible = true;
        this.l5.txt.value = "How is your mood?";

        this.l5.button1.visible = true;
        this.l5.button1.txt = "Positive";
        this.l5.button1.color = darkModernUI.AllowedColor.green;
        this.l5.button1.functions(() -> sayHello1());

        this.l5.button2.visible = true;
        this.l5.button2.txt = "Neutral";
        this.l5.button2.color = darkModernUI.AllowedColor.yellow;
        this.l5.button2.functions(() -> sayHello2());

        this.l5.button3.visible = true;
        this.l5.button3.txt = "Negative";
        this.l5.button3.color = darkModernUI.AllowedColor.red;
        this.l5.button3.functions(() -> sayHello3());
    }

    // Funktionen
    public static void sayHello1() {
        System.out.println("✓ Button 1 clicked!");
    }

    public static void sayHello2() {
        System.out.println("✓ Button 2 clicked!");
    }

    public static void sayHello3() {
        System.out.println("✓ Button 3 clicked!");
    }
}
