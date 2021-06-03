import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class pagesClass {
    public static void authorization() throws InterruptedException {
        JFrame log = new JFrame("authorization");
        JTextField logText = new JTextField("Логин");
        JTextField paswText = new JTextField("Пароль");
        JButton bt = new JButton("Вход");
        JButton btR = new JButton("Регистрация");

        bt.setBounds(185, 300, 130, 30);
        logText.setBounds(150, 180, 200, 30);
        paswText.setBounds(150, 220, 200, 30);
        btR.setBounds(320, 40, 130, 30);

        log.add(bt);
        log.add(logText);
        log.add(paswText);
        log.add(btR);

        log.setSize(480, 600);
        log.setLayout(null);

        log.setVisible(true);

        JRootPane rootPane = SwingUtilities.getRootPane(bt);
        rootPane.setDefaultButton(bt);

       BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:/Users/Marina/Desktop/Приложение/src/zam.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel label = new JLabel(new ImageIcon(image));
        log.add(label).setBounds(0, 0, 500, 700);

        btR.addActionListener( e -> {
            log.dispose();
            registration();
        });
        
        bt.addActionListener(e -> {
            String login = logText.getText();
            String password = paswText.getText();
            if (login.equals("admin") && password.equals("admin")) {
                log.dispose();
                pageOfAdmin();
            } else if (!login.equals("") && !password.equals("")) {
                log.dispose();
                try {
                    logUser(login, password);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
    }
    public static void logUser(String login, String password) throws InterruptedException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        try {
            while(result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (counter >= 1 ) {
            pageOfUser();
        } else {
            String error = "Ошибка, неверный пароль или логин!";
            JOptionPane.showMessageDialog(null, error);
            authorization();
        }
    }

    public static void registration() {
        JFrame log = new JFrame("registration");
        log.setSize(500, 500);

        log.setLayout(null);
        log.setVisible(true);

        JTextField logText = new JTextField("Логин");
        JTextField paswText = new JTextField("Пароль");
        logText.setBounds(90, 130, 300, 30);
        paswText.setBounds(90, 180, 300, 30);

        JButton btR = new JButton("Зарегистрироваться");
        JButton btReturn = new JButton("Назад");
        btR.setBounds(90, 300, 300, 30);
        btReturn.setBounds(370, 20, 100, 30);

        log.add(logText);
        log.add(paswText);
        log.add(btR);
        log.add(btReturn);

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:/Users/Marina/Desktop/Приложение/src/reg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel label = new JLabel(new ImageIcon(image));
        log.add(label).setBounds(0, 0, 500, 500);

        DatabaseHandler dbHandler = new DatabaseHandler();

        btR.addActionListener( e -> {
            String login = logText.getText();
            String password = paswText.getText();
            User user = new User(login, password);
            dbHandler.signUpUsers(user);

            log.dispose();
            try {
                authorization();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });

        closeWindow(btReturn, log);
    }

    public static void pageOfUser() {
        JFrame pageUser = new JFrame("Main page");
        JButton button1 = new JButton("Моя страница");
        JButton button2 = new JButton("Новости");
        JButton button3 = new JButton("Мессенджер");
        JButton button4 = new JButton("Друзья");
        JButton button5 = new JButton("Сообщества");
        JButton button6 = new JButton("Музыка");
        JButton button7 = new JButton("Видео");
        JButton button8 = new JButton("Игры");
        JButton button9 = new JButton("Выход");

        button1.setBounds(250, 10, 300, 20);
        button2.setBounds(250, 120, 300, 20);
        button3.setBounds(250, 160, 300, 20);
        button4.setBounds(250, 200, 300, 20);
        button5.setBounds(250, 240, 300, 20);
        button6.setBounds(250, 280, 300, 20);
        button7.setBounds(250, 320, 300, 20);
        button8.setBounds(250, 360, 300, 20);
        button9.setBounds(250, 500, 300, 20);

        pageUser.add(button1);
        pageUser.add(button2);
        pageUser.add(button3);
        pageUser.add(button4);
        pageUser.add(button5);
        pageUser.add(button6);
        pageUser.add(button7);
        pageUser.add(button8);
        pageUser.add(button9);


        pageUser.setSize(800, 600);
        pageUser.setLayout(null);
        pageUser.setVisible(true);


        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:/Users/Marina/Desktop/Приложение/src/user.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel label = new JLabel(new ImageIcon(image));
        pageUser.add(label).setBounds(0, 0, 800, 800);


        button2.addActionListener( e -> {
            try {
                pageUser.dispose();
                showNews();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        closeWindow(button9, pageUser);
    }

    public static void pageOfAdmin() {
        JFrame pageUser = new JFrame("Admin");
        JButton button1 = new JButton("Панель управления");
        JButton button2 = new JButton("Добавить новости");
        JButton button3 = new JButton("Добавить видео");
        JButton button4 = new JButton("Добавить музыку");
        JButton button5 = new JButton("Выход");

        button1.setBounds(40, 100, 300, 20);
        button2.setBounds(40, 180, 300, 20);
        button3.setBounds(40, 220, 300, 20);
        button4.setBounds(40, 260, 300, 20);
        button5.setBounds(40, 370, 300, 20);

        pageUser.add(button1);
        pageUser.add(button2);
        pageUser.add(button3);
        pageUser.add(button4);
        pageUser.add(button5);


        pageUser.setSize(800, 600);
        pageUser.setLayout(null);
        pageUser.setVisible(true);


        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:/Users/Marina/Desktop/Приложение/src/admin.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel label = new JLabel(new ImageIcon(image));
        pageUser.add(label).setBounds(385, 0, 400, 600);
        closeWindow(button5, pageUser);
    }

    public static void closeWindow(JButton button, JFrame pageUser) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageUser.dispose();
                try {
                    authorization();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
    }

    public static void showNews() throws IOException {
        JFrame showN = new JFrame("News");
        showN.setSize(1000, 800);
        JButton button = new JButton("Назад");
        button.setBounds(20, 10, 170, 20);
        showN.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showN.dispose();
                pageOfUser();
            }
        });

        showN.setLayout(null);
        showN.setVisible(true);

        Document doc = null;

        try {
            doc = Jsoup.connect("https://riafan.ru/category/goodnews").get();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // Elements metaElements = doc.select("img");

        Elements mainHeaderElements = doc.select("div.cat-item__title");
        int y = -240;
        for(int i = 0; i <= 13; i++) {
            JLabel label = new JLabel(mainHeaderElements.get(i).text());
            showN.add(label).setBounds(20, y * 1, 800, 600);
            y = y + 50;
        }
    }
}





