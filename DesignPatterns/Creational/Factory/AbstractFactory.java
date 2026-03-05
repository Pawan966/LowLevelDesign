package Creational.Factory;

// ─── Product Interfaces ───
 interface Button {
    void render();
    void onClick(Runnable action);
}

 interface Checkbox {
    void render();
    boolean isChecked();
    void toggle();
}

 interface TextField {
    void render();
    String getText();
    void setText(String text);
}

// ─── Windows Family ───
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("[Windows] Rendering flat Metro-style button");
    }

    @Override
    public void onClick(Runnable action) {
        System.out.println("[Windows] Button clicked — executing action");
        action.run();
    }
}

class WindowsCheckbox implements Checkbox {
    private boolean checked = false;

    @Override
    public void render() {
        System.out.println("[Windows] Rendering square checkbox: " +
                (checked ? "☑" : "☐"));
    }

    @Override
    public boolean isChecked() { return checked; }

    @Override
    public void toggle() { checked = !checked; }
}

class WindowsTextField implements TextField {
    private String text = "";

    @Override
    public void render() {
        System.out.println("[Windows] Rendering bordered text field: " + text);
    }

    @Override
    public String getText() { return text; }

    @Override
    public void setText(String text) { this.text = text; }
}

// ─── Mac Family ───
class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("[Mac] Rendering rounded Aqua-style button");
    }

    @Override
    public void onClick(Runnable action) {
        System.out.println("[Mac] Button clicked with haptic feedback");
        action.run();
    }
}

class MacCheckbox implements Checkbox {
    private boolean checked = false;

    @Override
    public void render() {
        System.out.println("[Mac] Rendering rounded checkbox: " +
                (checked ? "✅" : "⬜"));
    }

    @Override
    public boolean isChecked() { return checked; }

    @Override
    public void toggle() { checked = !checked; }
}

class MacTextField implements TextField {
    private String text = "";

    @Override
    public void render() {
        System.out.println("[Mac] Rendering borderless smooth text field: " + text);
    }

    @Override
    public String getText() { return text; }

    @Override
    public void setText(String text) { this.text = text; }
}

// ─── Abstract Factory ───
interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
    TextField createTextField();
}

// ─── Concrete Factories ───
class WindowsUIFactory implements UIFactory {
    @Override
    public Button createButton() { return new WindowsButton(); }

    @Override
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }

    @Override
    public TextField createTextField() { return new WindowsTextField(); }
}

class MacUIFactory implements UIFactory {
    @Override
    public Button createButton() { return new MacButton(); }

    @Override
    public Checkbox createCheckbox() { return new MacCheckbox(); }

    @Override
    public TextField createTextField() { return new MacTextField(); }
}

// client code
class LoginPage {

    private final Button loginButton;
    private final TextField usernameField;
    private final TextField passwordField;
    private final Checkbox rememberMeCheckbox;

    // Takes abstract factory — doesn't know Windows/Mac/Linux
    public LoginPage(UIFactory factory) {
        this.usernameField     = factory.createTextField();
        this.passwordField     = factory.createTextField();
        this.rememberMeCheckbox = factory.createCheckbox();
        this.loginButton       = factory.createButton();
    }

    public void render() {
        System.out.println("╔══════ LOGIN PAGE ══════╗");
        usernameField.setText("Enter username");
        usernameField.render();

        passwordField.setText("Enter password");
        passwordField.render();

        rememberMeCheckbox.render();

        loginButton.render();
        loginButton.onClick(() -> System.out.println("Logging in..."));
        System.out.println("╚════════════════════════╝");
    }
}

class AbstractFactory {
    public static void main(String[] args) {

        String os = "win";

        UIFactory factory = null;
        if (os.contains("win")) {
            factory = new WindowsUIFactory();
        } else if (os.contains("mac")) {
            factory = new MacUIFactory();
        }

        // LoginPage has ZERO coupling to any platform
        LoginPage loginPage = new LoginPage(factory);
        loginPage.render();
    }
}
