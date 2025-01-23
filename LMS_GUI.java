import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// Perbaikan kelas Grades
class Grades {
    private HashMap<String, Integer> grades;

    public Grades() {
        grades = new HashMap<>();
        grades.put("Sistem Operasi", 85);
        grades.put("Basis Data", 90);
        grades.put("Desain dan Analisa Algoritma", 88);
        grades.put("Kecerdasan Artifisial", 87);
        grades.put("Kalkulus 2", 80);
        grades.put("Pemrograman Beriorentasi Objek", 80);
        grades.put("Rekayasa Perangkat Lunak", 92);
        grades.put("Kewirausahaan 1", 84);
    }

    // Menampilkan daftar nilai
    public String displayGrades() {
        StringBuilder result = new StringBuilder("Nilai Mahasiswa:\n");
        for (String subject : grades.keySet()) {
            result.append(subject).append(": ").append(grades.get(subject)).append("\n");
        }
        return result.toString();
    }

    // Menghitung IPK berdasarkan nilai rata-rata
    public String calculateGPA() {
        double totalGrades = 0;
        for (int grade : grades.values()) {
            totalGrades += grade;
        }

        double average = totalGrades / grades.size();
        DecimalFormat formatter = new DecimalFormat("#.00");
        return "IPK: " + formatter.format(average);
    }
}

// Kelas untuk jadwal kelas
class Schedule {
    private HashMap<String, String[]> schedule;

    public Schedule() {
        schedule = new HashMap<>();
        schedule.put("Senin", new String[] { "07.30-12.30 Sistem Operasi", "13.00-15.30 Basis Data" });
        schedule.put("Selasa",
                new String[] { "07.30-10.00 Desain dan Analisa Algoritma", "13.00-15.30 Kecerdasan Artifisial" });
        schedule.put("Rabu", new String[] { "07.30-10.00 Kalkulus 2" });
        schedule.put("Kamis",
                new String[] { "07.30-12.30 Pemrograman Berorientasi Objek", "13.00-15.30 Rekayasa Perangkat Lunak" });
        schedule.put("Jumat", new String[] { "07.30-10.00 Kewirausahaan 1" });
    }

    public String getSchedule(String day) {
        if (schedule.containsKey(day)) {
            StringBuilder sb = new StringBuilder("Jadwal untuk " + day + ":\n");
            for (String session : schedule.get(day)) {
                sb.append(session).append("\n");
            }
            return sb.toString();
        } else {
            return "Tidak ada jadwal untuk hari " + day + ".";
        }
    }
}

class ToDoList {
    private TreeMap<String, ArrayList<String>> tasks; // Key: Deadline, Value: List of Tasks
    private SimpleDateFormat dateFormat;

    public ToDoList() {
        tasks = new TreeMap<>((d1, d2) -> {
            try {
                return dateFormat.parse(d1).compareTo(dateFormat.parse(d2));
            } catch (ParseException e) {
                throw new IllegalArgumentException("Format tanggal tidak valid");
            }
        });
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void addTask(String task, String deadline) {
        if (task.isEmpty() || deadline.isEmpty()) {
            throw new IllegalArgumentException("Tugas dan deadline tidak boleh kosong.");
        }
        try {
            dateFormat.parse(deadline); // Validasi format tanggal
            tasks.putIfAbsent(deadline, new ArrayList<>()); // Tambahkan key jika belum ada
            tasks.get(deadline).add(task); // Tambahkan tugas ke list pada tanggal tersebut
        } catch (ParseException e) {
            throw new IllegalArgumentException("Format deadline harus yyyy-MM-dd.");
        }
    }

    public void removeTask(int index) {
        if (index < 0 || index >= getTotalTaskCount()) {
            throw new IndexOutOfBoundsException("Indeks tugas tidak valid.");
        }

        int count = 0;
        for (Map.Entry<String, ArrayList<String>> entry : tasks.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                if (count == index) {
                    entry.getValue().remove(i);
                    if (entry.getValue().isEmpty()) {
                        tasks.remove(entry.getKey()); // Hapus tanggal jika tidak ada tugas
                    }
                    return;
                }
                count++;
            }
        }
    }

    public String displayTasks() {
        if (tasks.isEmpty()) {
            return "Tidak ada tugas dalam daftar.";
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Map.Entry<String, ArrayList<String>> entry : tasks.entrySet()) {
            for (String task : entry.getValue()) {
                sb.append(count++).append(". ").append(task)
                        .append(" (Deadline: ").append(entry.getKey()).append(")\n");
            }
        }
        return sb.toString();
    }

    private int getTotalTaskCount() {
        int count = 0;
        for (ArrayList<String> taskList : tasks.values()) {
            count += taskList.size();
        }
        return count;
    }
}

// Kelas utama LMS_GUI
public class LMS_GUI {

    public static String[][] imageApk = {
            { "icon", "https://github.com/samuelmahesasinulingga/LMS_Java/blob/main/src/img/Logo_LMS.jpg?raw=true" },
            { "Logo UEU", "https://github.com/samuelmahesasinulingga/LMS_Java/blob/main/src/img/Logo_UEU.png?raw=true" }
    };

    public static String[] matkulsData = {
            "Sistem Operasi",
            "Basis Data",
            "Desain dan Analisa Algoritma",
            "Kecerdasan Artifisal",
            "Kalkulus 2",
            "Pemrograman Berorientasi Objek",
            "Rekayasa Perangkat Lunak",
            "Kewirausahaan 1"
    };

    public static String[] courseImage = {
            "https://github.com/samuelmahesasinulingga/LMS_Java/blob/main/src/img/SistemOperasi.png?raw=true",
            "https://github.com/samuelmahesasinulingga/LMS_Java/blob/main/src/img/BasisData.jpg?raw=true",
            "https://github.com/samuelmahesasinulingga/LMS_Java/blob/main/src/img/DAA.jpg?raw=true",
            "https://github.com/samuelmahesasinulingga/LMS_Java/blob/main/src/img/AI.jpg?raw=true",
            "https://github.com/samuelmahesasinulingga/LMS_Java/blob/main/src/img/Kalkulus.jpg?raw=true",
            "https://github.com/samuelmahesasinulingga/LMS_Java/blob/main/src/img/PBO.png?raw=true",
            "https://github.com/samuelmahesasinulingga/LMS_Java/blob/main/src/img/RPL.jpg?raw=true",
            "https://github.com/samuelmahesasinulingga/LMS_Java/blob/main/src/img/KWU.jpg?raw=true"
    };

    public static String[] descriptionsCourse = {
            "https://raw.githubusercontent.com/samuelmahesasinulingga/LMS_Java/refs/heads/main/src/documents/SistemOperasi.txt",
            "https://raw.githubusercontent.com/samuelmahesasinulingga/LMS_Java/refs/heads/main/src/documents/BasisData.txt",
            "https://raw.githubusercontent.com/samuelmahesasinulingga/LMS_Java/refs/heads/main/src/documents/DAA.txt",
            "https://raw.githubusercontent.com/samuelmahesasinulingga/LMS_Java/refs/heads/main/src/documents/AI.txt",
            "https://raw.githubusercontent.com/samuelmahesasinulingga/LMS_Java/refs/heads/main/src/documents/Kalkulus.txt",
            "https://raw.githubusercontent.com/samuelmahesasinulingga/LMS_Java/refs/heads/main/src/documents/PBO.txt",
            "https://raw.githubusercontent.com/samuelmahesasinulingga/LMS_Java/refs/heads/main/src/documents/RPL.txt",
            "https://raw.githubusercontent.com/samuelmahesasinulingga/LMS_Java/refs/heads/main/src/documents/KWU.txt",
    };

    private JFrame mainFrame;
    private JPanel menuPanel;
    private ToDoList todoList;
    private int captchaNumber1;
    private int captchaNumber2;
    private String currentUsername = "admin";
    private String currentPassword = "1234";

    public LMS_GUI() {
        todoList = new ToDoList(); // Inisialisasi ToDoList

        JPanel loginPanel = new JPanel(new GridBagLayout()); // Layout untuk menempatkan semua komponen di tengah
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margin antar komponen

        // Judul besar di atas panel login
        JLabel titleLabel = new JLabel("Learning Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Mengatur font dan ukuran
        titleLabel.setForeground(Color.BLACK); // Warna teks
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Lebar komponen melingkupi 2 kolom
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(titleLabel, gbc);

        // Gambar di bawah judul
        JLabel imageLabel = new JLabel();
        try {
            // Ambil URL dari array imageApk
            String logoUrl = imageApk[1][1]; // URL untuk Logo UEU
            Image logoImage = ImageIO.read(new URL(logoUrl));
            Image scaledImage = logoImage.getScaledInstance(220, 150, Image.SCALE_SMOOTH); // Ubah ukuran gambar
            imageLabel.setIcon(new ImageIcon(scaledImage)); // Atur gambar ke label
        } catch (IOException e) {
            imageLabel.setText("Gambar tidak ditemukan."); // Jika ada error, tampilkan pesan
            e.printStackTrace();
        }

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(imageLabel, gbc);

        // Label username
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("Username:"), gbc);

        // Input username
        JTextField userField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(userField, gbc);

        // Label password
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("Password:"), gbc);

        // Input password
        JPasswordField passField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(passField, gbc);

        // Tombol login
        JButton loginButton = new JButton("Login");
        loginButton.setBorderPainted(false);
        loginButton.setBackground(new Color(255, 255, 255));// inside the brackets your rgb color value
        loginButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Membuat tombol berada di tengah
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        // Set posisi login panel di tengah frame
        menuPanel = new JPanel();
        mainFrame = new JFrame("LMS Esa Unggul"); // Gunakan variabel instance
        try {
            // Ambil URL dari array imageApk untuk ikon frame
            String iconUrl = imageApk[0][1]; // Key untuk icon
            Image frameIcon = ImageIO.read(new URL(iconUrl));
            mainFrame.setIconImage(frameIcon); // Set icon untuk JFrame
        } catch (IOException e) {
            System.err.println("Gagal memuat ikon frame: " + e.getMessage());
        }

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 500);
        mainFrame.setLayout(new GridBagLayout()); // Frame menggunakan GridBagLayout
        mainFrame.add(loginPanel); // Menambahkan loginPanel di tengah frame
        mainFrame.setLocationRelativeTo(null); // Menempatkan frame di tengah layar
        mainFrame.setVisible(true);

        menuPanel = new JPanel();
        JLabel menuLabel = new JLabel("Daftar Courses", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 20));
        menuPanel.add(menuLabel, BorderLayout.NORTH);
        menuPanel.setLayout(new GridLayout(8, 1, 10, 10));
        JButton scheduleButton = new JButton("Jadwal Kelas");
        styleButton(scheduleButton);
        JButton courseButton = new JButton("Course");
        styleButton(courseButton);
        JButton gradesButton = new JButton("Nilai Mahasiswa");
        styleButton(gradesButton);
        JButton todoButton = new JButton("To-Do List");
        styleButton(todoButton);
        JButton profileButton = new JButton("Profil Pengguna");
        styleButton(profileButton);
        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton);

        menuPanel.add(scheduleButton);
        menuPanel.add(courseButton);
        menuPanel.add(gradesButton);
        menuPanel.add(todoButton);
        menuPanel.add(profileButton);
        menuPanel.add(logoutButton);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            // Generate soal CAPTCHA
            generateCaptcha();
            String captchaQuestion = "Berapa hasil dari: " + captchaNumber1 + " + " + captchaNumber2 + " ?";
            String userCaptchaInput = JOptionPane.showInputDialog(mainFrame, captchaQuestion, "Verifikasi CAPTCHA",
                    JOptionPane.QUESTION_MESSAGE);

            // Validasi input CAPTCHA
            if (userCaptchaInput == null) {
                JOptionPane.showMessageDialog(mainFrame, "CAPTCHA wajib diisi.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int userCaptcha = Integer.parseInt(userCaptchaInput.trim());
                if (userCaptcha != (captchaNumber1 + captchaNumber2)) {
                    JOptionPane.showMessageDialog(mainFrame, "Jawaban CAPTCHA salah.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Masukkan angka yang valid untuk CAPTCHA.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validasi Username dan Password
            if (username.equals(currentUsername) && password.equals(currentPassword)) {
                // Progress bar frame
                JDialog loadingDialog = new JDialog(mainFrame, "Memuat data", true);
                loadingDialog.setSize(350, 100);
                loadingDialog.setLocationRelativeTo(mainFrame);
                loadingDialog.setLayout(new BorderLayout());

                JPanel progressPanel = new JPanel(new BorderLayout());
                progressPanel.setBackground(new Color(50, 50, 50));

                JLabel loadingLabel = new JLabel("Memuat data, harap tunggu...", SwingConstants.CENTER);
                loadingLabel.setFont(new Font("Arial", Font.BOLD, 16));
                loadingLabel.setForeground(Color.WHITE);

                JProgressBar progressBar = new JProgressBar(0, 100);
                progressBar.setStringPainted(true);
                progressBar.setForeground(new Color(70, 130, 180));
                progressBar.setBackground(new Color(200, 200, 200));
                progressBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                progressPanel.add(loadingLabel, BorderLayout.NORTH);
                progressPanel.add(progressBar, BorderLayout.CENTER);
                loadingDialog.add(progressPanel);

                // Simulasi loading dengan Thread
                new Thread(() -> {
                    try {
                        for (int i = 0; i <= 100; i++) {
                            progressBar.setValue(i);
                            Thread.sleep(25); // Waktu delay loading
                        }

                        // Setelah selesai loading, tampilkan menu utama
                        SwingUtilities.invokeLater(() -> {
                            loadingDialog.dispose();
                            mainFrame.setContentPane(menuPanel);
                            mainFrame.revalidate();
                        });
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();

                loadingDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Username atau password salah.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        logoutButton.addActionListener(e -> {
            mainFrame.setContentPane(loginPanel);
            mainFrame.revalidate();
        });

        scheduleButton.addActionListener(e -> {
            JPanel schedulePanel = new JPanel(new BorderLayout());
            JLabel scheduleLabel = new JLabel("Jadwal Kelas", SwingConstants.CENTER);
            scheduleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            schedulePanel.add(scheduleLabel, BorderLayout.NORTH);

            Schedule schedule = new Schedule();
            JTextArea scheduleArea = new JTextArea();
            scheduleArea.setEditable(false);
            scheduleArea.setText(schedule.getSchedule("Senin") + "\n" + schedule.getSchedule("Selasa") + "\n"
                    + schedule.getSchedule("Rabu") + "\n" + schedule.getSchedule("Kamis") + "\n"
                    + schedule.getSchedule("Jumat"));

            schedulePanel.add(new JScrollPane(scheduleArea), BorderLayout.CENTER);

            JButton backButton = new JButton("Kembali");
            backButton.addActionListener(ev -> {
                mainFrame.setContentPane(menuPanel);
                mainFrame.revalidate();
            });

            schedulePanel.add(backButton, BorderLayout.SOUTH);

            mainFrame.setContentPane(schedulePanel);
            mainFrame.revalidate();
        });

        courseButton.addActionListener(e -> {
            JPanel coursePanel = new JPanel(new BorderLayout());
            JLabel courseLabel = new JLabel("Daftar Courses", SwingConstants.CENTER);
            courseLabel.setFont(new Font("Arial", Font.BOLD, 20));
            coursePanel.add(courseLabel, BorderLayout.NORTH);

            JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 10, 10));

            for (int i = 0; i < matkulsData.length; i++) {
                String courseName = matkulsData[i]; // Nama mata kuliah sesuai indeks
                JButton courseBt = new JButton(courseName);
                styleButton(courseBt);
                courseBt.setPreferredSize(new Dimension(150, 30)); // Atur ukuran tombol
                int index = i; // Simpan indeks untuk digunakan dalam listener

                courseBt.addActionListener(ev -> {
                    JPanel detailPanel = new JPanel(new BorderLayout());

                    // Header Panel
                    JPanel headerPanel = new JPanel(new BorderLayout());
                    JButton backButton = new JButton("Kembali");
                    backButton.addActionListener(backEv -> {
                        mainFrame.setContentPane(coursePanel);
                        mainFrame.revalidate();
                    });

                    JLabel judulLabel = new JLabel(courseName, SwingConstants.CENTER);
                    judulLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    headerPanel.add(backButton, BorderLayout.WEST);
                    headerPanel.add(judulLabel, BorderLayout.CENTER);
                    detailPanel.add(headerPanel, BorderLayout.NORTH);

                    // Image Panel
                    JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    JLabel courseImageLabel = new JLabel();
                    try {
                        URL imageUrl = new URL(courseImage[index]);
                        ImageIcon icon = new ImageIcon(imageUrl);
                        Image scaledImage = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                        courseImageLabel.setIcon(new ImageIcon(scaledImage));
                    } catch (Exception ex) {
                        courseImageLabel.setText("Gambar tidak ditemukan.");
                    }
                    imagePanel.add(courseImageLabel);

                    // Description Panel
                    JTextArea descriptionArea = new JTextArea();
                    descriptionArea.setLineWrap(true);
                    descriptionArea.setWrapStyleWord(true);
                    descriptionArea.setEditable(false);
                    descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
                    descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    // Membaca deskripsi dari URL
                    try {
                        URL descriptionUrl = new URL(descriptionsCourse[index]);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(descriptionUrl.openStream()));
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        reader.close();
                        descriptionArea.setText(content.toString());
                    } catch (Exception ex) {
                        descriptionArea.setText("Deskripsi tidak dapat dimuat.");
                    }

                    // Center Panel
                    JPanel centerPanel = new JPanel();
                    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
                    centerPanel.add(imagePanel);
                    centerPanel.add(descriptionArea);

                    detailPanel.add(centerPanel, BorderLayout.CENTER);

                    mainFrame.setContentPane(detailPanel);
                    mainFrame.revalidate();
                });

                buttonPanel.add(courseBt);
            }

            coursePanel.add(new JScrollPane(buttonPanel), BorderLayout.CENTER);

            // Tombol kembali
            JButton backButton = new JButton("Kembali");
            backButton.addActionListener(ev -> {
                mainFrame.setContentPane(menuPanel);
                mainFrame.revalidate();
            });

            coursePanel.add(backButton, BorderLayout.SOUTH);

            mainFrame.setContentPane(coursePanel);
            mainFrame.revalidate();
        });

        gradesButton.addActionListener(e ->

        {
            JPanel gradesPanel = new JPanel(new BorderLayout());
            JLabel gradesLabel = new JLabel("Nilai Mahasiswa", SwingConstants.CENTER);
            gradesLabel.setFont(new Font("Arial", Font.BOLD, 20));
            gradesPanel.add(gradesLabel, BorderLayout.NORTH);

            Grades grades = new Grades();
            JTextArea gradesArea = new JTextArea();
            gradesArea.setEditable(false);
            gradesArea.setText(grades.displayGrades() + "\n" + grades.calculateGPA());

            gradesPanel.add(new JScrollPane(gradesArea), BorderLayout.CENTER);

            JButton backButton = new JButton("Kembali");
            backButton.addActionListener(ev -> {
                mainFrame.setContentPane(menuPanel);
                mainFrame.revalidate();
            });

            gradesPanel.add(backButton, BorderLayout.SOUTH);

            mainFrame.setContentPane(gradesPanel);
            mainFrame.revalidate();
        });

        todoButton.addActionListener(e -> showToDoListPanel());

        mainFrame.setContentPane(loginPanel);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        profileButton.addActionListener(e -> {
            JPanel profilePanel = new JPanel(new GridLayout(3, 2, 10, 10));

            JTextField usernameField = new JTextField(currentUsername);
            JPasswordField passwordField = new JPasswordField(currentPassword);

            profilePanel.add(new JLabel("Username:"));
            profilePanel.add(usernameField);
            profilePanel.add(new JLabel("Password:"));
            profilePanel.add(passwordField);

            int option = JOptionPane.showConfirmDialog(mainFrame, profilePanel, "Edit Profil Pengguna",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                String newUsername = usernameField.getText().trim();
                String newPassword = new String(passwordField.getPassword()).trim();

                if (!newUsername.isEmpty() && !newPassword.isEmpty()) {
                    currentUsername = newUsername;
                    currentPassword = newPassword;

                    int verification = JOptionPane.showConfirmDialog(null,
                            "Apakah anda yakin ingin mengubah profile anda?", "Konfirmasi",
                            JOptionPane.YES_NO_OPTION);

                    if (verification == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(mainFrame, "Profil berhasil diperbarui!", "Sukses",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        mainFrame.setContentPane(menuPanel);
                        mainFrame.revalidate();
                    }
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Username dan password tidak boleh kosong.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false); // Hilangkan garis fokus saat diklik
        button.setBorderPainted(false); // Hilangkan border
        button.setContentAreaFilled(true); // Isi area tombol
        button.setBackground(new Color(70, 130, 180)); // Warna biru laut
        button.setForeground(Color.WHITE); // Warna teks putih
        button.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Font modern

        // Tambahkan efek hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237)); // Warna hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180)); // Warna default
            }
        });
    }

    private void generateCaptcha() {
        captchaNumber1 = (int) (Math.random() * 10) + 1; // Angka 1-10
        captchaNumber2 = (int) (Math.random() * 10) + 1; // Angka 1-10
    }

    private void showToDoListPanel() {
        JPanel todoPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("To-Do List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        todoPanel.add(titleLabel, BorderLayout.NORTH);

        JTextArea todoArea = new JTextArea();
        todoArea.setEditable(false);
        todoArea.setText(todoList.displayTasks());

        // Panel untuk input dan tombol
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        JTextField taskField = new JTextField();
        JTextField deadlineField = new JTextField();
        JButton addButton = new JButton("Tambah Tugas");
        JButton deleteButton = new JButton("Hapus Tugas");

        inputPanel.add(new JLabel("Tugas:"));
        inputPanel.add(taskField);
        inputPanel.add(new JLabel("Deadline (yyyy-MM-dd):"));
        inputPanel.add(deadlineField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        // Tombol Tambah Tugas
        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            String deadline = deadlineField.getText().trim();
            try {
                todoList.addTask(task, deadline);
                todoArea.setText(todoList.displayTasks()); // Perbarui daftar
                taskField.setText("");
                deadlineField.setText("");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Tombol Hapus Tugas
        deleteButton.addActionListener(e -> {
            String indexStr = JOptionPane.showInputDialog(mainFrame, "Masukkan nomor tugas yang ingin dihapus:");
            try {
                int index = Integer.parseInt(indexStr) - 1;
                todoList.removeTask(index);
                todoArea.setText(todoList.displayTasks());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Masukkan nomor tugas yang valid.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Nomor tugas tidak valid.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton backButton = new JButton("Kembali");
        backButton.addActionListener(ev -> {
            mainFrame.setContentPane(menuPanel);
            mainFrame.revalidate();
        });

        // Panel untuk tombol kembali
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        bottomPanel.add(backButton, BorderLayout.SOUTH);

        todoPanel.add(new JScrollPane(todoArea), BorderLayout.CENTER);
        todoPanel.add(bottomPanel, BorderLayout.SOUTH);

        mainFrame.setContentPane(todoPanel);
        mainFrame.revalidate();
    }

    public static void main(String[] args) {
        new LMS_GUI();
    }
}