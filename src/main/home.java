/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpResponse;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Manas
 */
public class home extends javax.swing.JFrame {

    public boolean isSubMenuActive = false;
    public boolean isAdminSubMenuActive = false;
    public boolean isStudentSubMenuActive = false;
    public boolean isBookSubMenuActive = false;
    public boolean isHelpSubMenuActive = false;
    public Timer time, time2;
    public String uploadedFilePath = "";
    public static boolean photoUploaded;
    public String uploadedFileNameFromServer = "";
    public String studentUploadedFilePath = "";
    public static boolean studentPhotoUploaded;
    public String studentUploadedFileNameFromServer = "";
    public String uploadedEbookFilePath = "";
    private int reqBookIssueSelectedRow = -1;
    private boolean isNetworkAvailable;

    /**
     * Creates new form home
     */
    public home() {
    }

    public home(String name) {
        initComponents();
        subMenuPanel.setVisible(false);
        mainContentPanel.setBounds(75, 45, 965, 565);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        adminNameMain.setText(name);
        adminListTable.getTableHeader().setOpaque(false);
        adminListTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        adminListTable.getTableHeader().setBackground(new Color(51, 51, 51));
        adminListTable.getTableHeader().setForeground(new Color(255, 255, 255));
        adminListTable.getTableHeader().setBorder(new MatteBorder(2, 2, 2, 2, new Color(51, 51, 51)));
        adminListTable.getTableHeader().setPreferredSize(new Dimension(adminListTable.getTableHeader().getWidth(), 45));
        adminListScrollPane.getViewport().setBackground(new Color(255, 255, 255));
        adminListScrollPane.setBorder(new MatteBorder(0, 0, 0, 0, Color.white));

        bookListTable.getTableHeader().setOpaque(false);
        bookListTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        bookListTable.getTableHeader().setBackground(new Color(51, 51, 51));
        bookListTable.getTableHeader().setForeground(new Color(255, 255, 255));
        bookListTable.getTableHeader().setBorder(new MatteBorder(2, 2, 2, 2, new Color(51, 51, 51)));
        bookListTable.getTableHeader().setPreferredSize(new Dimension(adminListTable.getTableHeader().getWidth(), 45));
        bookListScrollPane.getViewport().setBackground(new Color(255, 255, 255));
        bookListScrollPane.setBorder(new MatteBorder(0, 0, 0, 0, Color.white));

        trackIssuedBookTable.getTableHeader().setOpaque(false);
        trackIssuedBookTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        trackIssuedBookTable.getTableHeader().setBackground(new Color(51, 51, 51));
        trackIssuedBookTable.getTableHeader().setForeground(new Color(255, 255, 255));
        trackIssuedBookTable.getTableHeader().setBorder(new MatteBorder(2, 2, 2, 2, new Color(51, 51, 51)));
        trackIssuedBookTable.getTableHeader().setPreferredSize(new Dimension(adminListTable.getTableHeader().getWidth(), 45));
        trackIssuedBookScrollPane.getViewport().setBackground(new Color(255, 255, 255));
        trackIssuedBookScrollPane.setBorder(new MatteBorder(0, 0, 0, 0, Color.white));

        bookRequestTable.getTableHeader().setOpaque(false);
        bookRequestTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        bookRequestTable.getTableHeader().setBackground(new Color(51, 51, 51));
        bookRequestTable.getTableHeader().setForeground(new Color(255, 255, 255));
        bookRequestTable.getTableHeader().setBorder(new MatteBorder(2, 2, 2, 2, new Color(51, 51, 51)));
        bookRequestTable.getTableHeader().setPreferredSize(new Dimension(adminListTable.getTableHeader().getWidth(), 45));
        bookRequestScrollPane.getViewport().setBackground(new Color(255, 255, 255));
        bookRequestScrollPane.setBorder(new MatteBorder(0, 0, 0, 0, Color.white));

        eBookListTable.getTableHeader().setOpaque(false);
        eBookListTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        eBookListTable.getTableHeader().setBackground(new Color(51, 51, 51));
        eBookListTable.getTableHeader().setForeground(new Color(255, 255, 255));
        eBookListTable.getTableHeader().setBorder(new MatteBorder(2, 2, 2, 2, new Color(51, 51, 51)));
        eBookListTable.getTableHeader().setPreferredSize(new Dimension(adminListTable.getTableHeader().getWidth(), 45));
        eBookListScrollPane.getViewport().setBackground(new Color(255, 255, 255));
        eBookListScrollPane.setBorder(new MatteBorder(0, 0, 0, 0, Color.white));

        studentListTable.getTableHeader().setOpaque(false);
        studentListTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        studentListTable.getTableHeader().setBackground(new Color(51, 51, 51));
        studentListTable.getTableHeader().setForeground(new Color(255, 255, 255));
        studentListTable.getTableHeader().setBorder(new MatteBorder(2, 2, 2, 2, new Color(51, 51, 51)));
        studentListTable.getTableHeader().setPreferredSize(new Dimension(adminListTable.getTableHeader().getWidth(), 45));
        studentListScrollPane.getViewport().setBackground(new Color(255, 255, 255));
        studentListScrollPane.setBorder(new MatteBorder(0, 0, 0, 0, Color.white));

        studenceAttendanceTable.getTableHeader().setOpaque(false);
        studenceAttendanceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        studenceAttendanceTable.getTableHeader().setBackground(new Color(51, 51, 51));
        studenceAttendanceTable.getTableHeader().setForeground(new Color(255, 255, 255));
        studenceAttendanceTable.getTableHeader().setBorder(new MatteBorder(2, 2, 2, 2, new Color(51, 51, 51)));
        studenceAttendanceTable.getTableHeader().setPreferredSize(new Dimension(adminListTable.getTableHeader().getWidth(), 45));
        studentAttendanceScrollPane.getViewport().setBackground(new Color(255, 255, 255));
        studentAttendanceScrollPane.setBorder(new MatteBorder(0, 0, 0, 0, Color.white));

        attendanceRecordTable.getTableHeader().setOpaque(false);
        attendanceRecordTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        attendanceRecordTable.getTableHeader().setBackground(new Color(51, 51, 51));
        attendanceRecordTable.getTableHeader().setForeground(new Color(255, 255, 255));
        attendanceRecordTable.getTableHeader().setBorder(new MatteBorder(2, 2, 2, 2, new Color(51, 51, 51)));
        attendanceRecordTable.getTableHeader().setPreferredSize(new Dimension(adminListTable.getTableHeader().getWidth(), 45));
        attendanceRecordScrollPanel.getViewport().setBackground(new Color(255, 255, 255));
        attendanceRecordScrollPanel.setBorder(new MatteBorder(0, 0, 0, 0, Color.white));

        time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
                String dateNow = formatter.format(date);
                dateTime.setText(dateNow);
            }
        });
        time.start();

        checkNetworkConnectivity();
        if (isNetworkAvailable) {
            topBar.setBackground(new Color(0, 195, 122));
        } else {
            topBar.setBackground(new Color(255, 79, 25));
        }

        time2 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                checkNetworkConnectivity();
                if (isNetworkAvailable) {
                    topBar.setBackground(new Color(0, 195, 122));
                } else {
                    topBar.setBackground(new Color(255, 79, 25));
                    processingFrame.setVisible(false);
                }
            }
        });
        time2.start();
    }

    private void checkNetworkConnectivity() {

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.google.com");
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    isNetworkAvailable = true;
                } catch (Exception e) {
                    isNetworkAvailable = false;
                }
            }

        };
        thread.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        adminListProfileView = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        adminProfilePhoto = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        adminProfileName = new javax.swing.JLabel();
        adminProfileEmail = new javax.swing.JLabel();
        adminProfileContact = new javax.swing.JLabel();
        adminProfileAadhar = new javax.swing.JLabel();
        adminProfileAddress = new javax.swing.JLabel();
        adminProfileUsername = new javax.swing.JLabel();
        adminProfilePassword = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        issueBookDialogPanel = new javax.swing.JDialog();
        jPanel16 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        processingFrame = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        mainMenuContainer = new javax.swing.JPanel();
        menuCloseBtnPanel = new javax.swing.JPanel();
        menuCloseBtn = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        adminMenuBtn = new javax.swing.JLabel();
        studentMenuBtn = new javax.swing.JLabel();
        bookMenuBtn = new javax.swing.JLabel();
        helpMenuBtn = new javax.swing.JLabel();
        subMenuPanel = new javax.swing.JDesktopPane();
        subMenuContainer = new javax.swing.JLayeredPane();
        adminSubMenuPanel = new javax.swing.JPanel();
        adminSubMenuContainer = new javax.swing.JPanel();
        addAdminBtn = new javax.swing.JLabel();
        updateAdminBtn = new javax.swing.JLabel();
        adminListBtn = new javax.swing.JLabel();
        helpSubMenuPanel = new javax.swing.JPanel();
        helpSubMenuContainer = new javax.swing.JPanel();
        tipOfTheDayBtn = new javax.swing.JLabel();
        shortcutsBtn = new javax.swing.JLabel();
        contactDeveloperBtn = new javax.swing.JLabel();
        bookSubMenuPanel = new javax.swing.JPanel();
        bookSubMenuContainer = new javax.swing.JPanel();
        addBookBtn = new javax.swing.JLabel();
        bookListBtn = new javax.swing.JLabel();
        trackIssuedBookBtn = new javax.swing.JLabel();
        requestListForBookIssueBtn = new javax.swing.JLabel();
        AddEBookBtn = new javax.swing.JLabel();
        eBookListBtn = new javax.swing.JLabel();
        studentSubMenuPanel = new javax.swing.JPanel();
        studentSubMenuContainer = new javax.swing.JPanel();
        addStudentBtn = new javax.swing.JLabel();
        updateStudentBtn = new javax.swing.JLabel();
        studentListBtn = new javax.swing.JLabel();
        studentAttendenceBtn = new javax.swing.JLabel();
        viewStudentAttendanceRecordBtn = new javax.swing.JLabel();
        mainContentPanel = new javax.swing.JDesktopPane();
        mainContainer = new javax.swing.JLayeredPane();
        HomeMainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        addAdminPanel = new javax.swing.JPanel();
        addAdminSubPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        addAdminName = new javax.swing.JTextField();
        addAdminEmail = new javax.swing.JTextField();
        addAdminContact = new javax.swing.JTextField();
        addAdminAadhar = new javax.swing.JTextField();
        addAdminUsername = new javax.swing.JTextField();
        addAdminPassword = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        addAdminPhoto = new javax.swing.JLabel();
        addAdminPhotoName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addAdminAddress = new javax.swing.JTextArea();
        addAdminResetBtnPanel = new javax.swing.JPanel();
        addAdminResetBtn = new javax.swing.JLabel();
        addAdminRegisterBtnPanel = new javax.swing.JPanel();
        addAdminRegisterBtn = new javax.swing.JLabel();
        bookUpdateMainPanel = new javax.swing.JPanel();
        bookUpdateSubPanel = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        bookStockBookId = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        bookStockCat = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        bookStockSubCat = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        bookStockName = new javax.swing.JTextField();
        bookStockWriter = new javax.swing.JTextField();
        bookStockPublisher = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        bookStockAddStock = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        bookStockUpdateStockPanel = new javax.swing.JPanel();
        bookStockUpdateStockBtn = new javax.swing.JLabel();
        bookStockBackPanel = new javax.swing.JPanel();
        bookStockBackBtn = new javax.swing.JLabel();
        studentListMainPanel = new javax.swing.JPanel();
        studentListSubPanel = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        studentListScrollPane = new javax.swing.JScrollPane();
        studentListTable = new javax.swing.JTable();
        studentListDeletePanel = new javax.swing.JPanel();
        studentListDeleteBtn = new javax.swing.JLabel();
        studentListSearchPanel = new javax.swing.JPanel();
        studentListSearchBtn = new javax.swing.JLabel();
        studentListSearchTextField = new javax.swing.JTextField();
        clearStudentSearchBtn = new javax.swing.JLabel();
        studentSearchMainPanel = new javax.swing.JPanel();
        studentSearchSubPanel = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        searchStudentTextField = new javax.swing.JTextField();
        studentResetBtnPanel = new javax.swing.JPanel();
        studentResetBtn = new javax.swing.JLabel();
        studentSearchBtnPanel = new javax.swing.JPanel();
        studentSearchBtn = new javax.swing.JLabel();
        eBookListMainPanel = new javax.swing.JPanel();
        eBookListSubPanel = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        eBookListScrollPane = new javax.swing.JScrollPane();
        eBookListTable = new javax.swing.JTable();
        ebookListRemovePanel = new javax.swing.JPanel();
        ebookListRemoveBtn = new javax.swing.JLabel();
        eBookMainPanel = new javax.swing.JPanel();
        eBookSubPanel = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        addEBookCat = new javax.swing.JComboBox<>();
        jLabel72 = new javax.swing.JLabel();
        addEBookSubCat = new javax.swing.JComboBox<>();
        jLabel73 = new javax.swing.JLabel();
        addEBookBookName = new javax.swing.JTextField();
        addEBookWriter = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        addEBookPublisher = new javax.swing.JTextField();
        addEBookEdition = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        addEBookDescription = new javax.swing.JTextArea();
        jPanel14 = new javax.swing.JPanel();
        addEBookAddBtn = new javax.swing.JLabel();
        addEBookLocation = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        addEBookUplodeBookBtn = new javax.swing.JLabel();
        requestBookIssueMainPanel = new javax.swing.JPanel();
        requestBookIssueSubPanel = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        bookRequestScrollPane = new javax.swing.JScrollPane();
        bookRequestTable = new javax.swing.JTable();
        booRequestCancelPanel = new javax.swing.JPanel();
        bookRequestCancelBtn = new javax.swing.JLabel();
        bookRequestAllowPanel = new javax.swing.JPanel();
        bookRequestAllowBtn = new javax.swing.JLabel();
        trackIssuedBookMainPanel = new javax.swing.JPanel();
        trackIssuedBookSubPanel = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        TrackBookListSearchPanel = new javax.swing.JPanel();
        trackBookListSearchField = new javax.swing.JTextField();
        trackBookListSearchBtn = new javax.swing.JLabel();
        trackIssuedBookScrollPane = new javax.swing.JScrollPane();
        trackIssuedBookTable = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        clearIssuedSearchBtn = new javax.swing.JLabel();
        bookListMainPanel = new javax.swing.JPanel();
        bookListSubPanel = new javax.swing.JPanel();
        bookListDeletePanel = new javax.swing.JPanel();
        bookListDeleteBtn = new javax.swing.JLabel();
        bookListUpdatePanel = new javax.swing.JPanel();
        bookListUpdateBtn = new javax.swing.JLabel();
        bookListSearchPanel = new javax.swing.JPanel();
        bookListSearchField = new javax.swing.JTextField();
        bookListSearchBtn = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        bookListScrollPane = new javax.swing.JScrollPane();
        bookListTable = new javax.swing.JTable();
        clearBookSearchBtn = new javax.swing.JLabel();
        addBookMainPanel = new javax.swing.JPanel();
        addBookSubPanel = new javax.swing.JPanel();
        addBookSubCat = new javax.swing.JComboBox<>();
        addBookCategory = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        addBookEdition = new javax.swing.JTextField();
        addBookName = new javax.swing.JTextField();
        addBookWritter = new javax.swing.JTextField();
        addBookStock = new javax.swing.JTextField();
        addBookPrice = new javax.swing.JTextField();
        addBookPublisher = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        addBookDiscription = new javax.swing.JTextArea();
        jPanel12 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        adminUpdateResultPanel = new javax.swing.JPanel();
        adminUpdateResultSubPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        updateAdminResultName = new javax.swing.JTextField();
        updateAdminResultEmail = new javax.swing.JTextField();
        updateAdminResultContact = new javax.swing.JTextField();
        updateAdminResultAadhar = new javax.swing.JTextField();
        updateAdminResultUsername = new javax.swing.JTextField();
        updateAdminResultPassword = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        updateAdminResultPhoto = new javax.swing.JLabel();
        updateAdminResultPhotoName = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        updateAdminResultAddress = new javax.swing.JTextArea();
        updateAdminResultResetBtnPanel = new javax.swing.JPanel();
        updateAdminResultResetBtn = new javax.swing.JLabel();
        updateAdminResultRegisterBtnPanel = new javax.swing.JPanel();
        updateAdminResultRegisterBtn = new javax.swing.JLabel();
        adminUpdatePanel = new javax.swing.JPanel();
        adminUpdateSubPanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        updatePanelTransaction = new javax.swing.JLayeredPane();
        searchAdminPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        updateAdminSearchField = new javax.swing.JTextField();
        updateAdminResetBtnPanel = new javax.swing.JPanel();
        updateAdminResetBtn = new javax.swing.JLabel();
        updateAdminSearchBtnPanel = new javax.swing.JPanel();
        updateAdminSearchBtn = new javax.swing.JLabel();
        adminListPanel = new javax.swing.JPanel();
        adminListSubPanel = new javax.swing.JPanel();
        adminListActionPanel = new javax.swing.JPanel();
        adminListScrollPane = new javax.swing.JScrollPane();
        adminListTable = new javax.swing.JTable();
        adminListDeleteBtnPanel = new javax.swing.JPanel();
        adminListDeleteBtn = new javax.swing.JLabel();
        adminListViewProfileBtnPanel = new javax.swing.JPanel();
        adminListViewProfileBtn = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        addStudentPanel = new javax.swing.JPanel();
        addStudentSubPanel = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        addStudentPhoto = new javax.swing.JLabel();
        addStudentPhotoName = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        addStudentCourseList = new javax.swing.JComboBox<>();
        addStudentSemester = new javax.swing.JTextField();
        addStudentAadhar = new javax.swing.JTextField();
        addStudentName = new javax.swing.JTextField();
        addStudentEmail = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        addStudentAddress = new javax.swing.JTextArea();
        addStudentEnroll = new javax.swing.JTextField();
        addStudentContact = new javax.swing.JTextField();
        addStudentResetBtnPanel = new javax.swing.JPanel();
        addStudentResetBtn = new javax.swing.JLabel();
        addStudentRegisterBtnPanel = new javax.swing.JPanel();
        addStudentRegisterBtn = new javax.swing.JLabel();
        addStudentStreamList = new javax.swing.JComboBox<>();
        studentUpdateResultPanel = new javax.swing.JPanel();
        studentUpdateResultSubPanel = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        updateStudentPhoto = new javax.swing.JLabel();
        updateStudentPhotoName = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        updateStudentCourseList = new javax.swing.JComboBox<>();
        updateStudentSemester = new javax.swing.JTextField();
        updateStudentAadhar = new javax.swing.JTextField();
        updateStudentName = new javax.swing.JTextField();
        updateStudentEmail = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        updateStudentAddress = new javax.swing.JTextArea();
        updateStudentEnroll = new javax.swing.JTextField();
        updateStudentContact = new javax.swing.JTextField();
        updateStudentResetBtnPanel = new javax.swing.JPanel();
        updateStudentResetBtn = new javax.swing.JLabel();
        updateStudentRegisterBtnPanel = new javax.swing.JPanel();
        updateStudentRegisterBtn = new javax.swing.JLabel();
        updateStudentStreamList = new javax.swing.JComboBox<>();
        studentAttendanceMainPanel = new javax.swing.JPanel();
        studentAttendanceSubPanel = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        studentAttendanceScrollPane = new javax.swing.JScrollPane();
        studenceAttendanceTable = new javax.swing.JTable();
        studenceAttendanceApprovePanel = new javax.swing.JPanel();
        studenceAttendanceApproveBtn = new javax.swing.JLabel();
        studenceAttendanceExitPanel = new javax.swing.JPanel();
        StudentAttendanceExitBtn = new javax.swing.JLabel();
        studentAttendanceRecordMainPanel = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        searchAttendBtnPanel = new javax.swing.JPanel();
        searchAttendanceBtn = new javax.swing.JLabel();
        attendanceRecordTablePanel = new javax.swing.JPanel();
        attendanceRecordScrollPanel = new javax.swing.JScrollPane();
        attendanceRecordTable = new javax.swing.JTable();
        topBar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        adminNameMain = new javax.swing.JLabel();
        dateTime = new javax.swing.JLabel();

        adminListProfileView.setAlwaysOnTop(true);
        adminListProfileView.setBackground(new java.awt.Color(0, 0, 0));
        adminListProfileView.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        adminListProfileView.setUndecorated(true);
        adminListProfileView.setResizable(false);
        adminListProfileView.setSize(new java.awt.Dimension(800, 400));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        adminProfilePhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminProfilePhoto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminProfilePhoto, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(adminProfilePhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Name");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Email ");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Contact Number");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Aadhar Number");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Address");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Username");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("Password");

        adminProfileName.setText("jLabel32");
        adminProfileName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        adminProfileName.setFocusable(false);
        adminProfileName.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        adminProfileEmail.setText("jLabel33");
        adminProfileEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        adminProfileEmail.setFocusable(false);
        adminProfileEmail.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        adminProfileContact.setText("jLabel34");
        adminProfileContact.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        adminProfileContact.setFocusable(false);
        adminProfileContact.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        adminProfileAadhar.setText("jLabel35");
        adminProfileAadhar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        adminProfileAadhar.setFocusable(false);
        adminProfileAadhar.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        adminProfileAddress.setText("jLabel36");
        adminProfileAddress.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        adminProfileAddress.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        adminProfileAddress.setFocusable(false);
        adminProfileAddress.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        adminProfileUsername.setText("jLabel37");
        adminProfileUsername.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        adminProfileUsername.setFocusable(false);
        adminProfileUsername.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        adminProfilePassword.setText("jLabel38");
        adminProfilePassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        adminProfilePassword.setFocusable(false);
        adminProfilePassword.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 153, 102));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("          Admin Profile");

        jLabel6.setBackground(new java.awt.Color(255, 0, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("X");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.setOpaque(true);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(adminProfileName, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adminProfileContact, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(adminProfileEmail, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(adminProfileUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel31)
                                .addGap(36, 36, 36)
                                .addComponent(adminProfilePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(adminProfileAadhar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(adminProfileAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {adminProfileContact, adminProfileEmail, adminProfileName});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(adminProfileName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(adminProfileEmail))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(adminProfileContact)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(adminProfileAadhar))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(0, 52, Short.MAX_VALUE))
                    .addComponent(adminProfileAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30)
                    .addComponent(adminProfileUsername)
                    .addComponent(adminProfilePassword))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {adminProfileAadhar, adminProfileContact, adminProfileEmail, adminProfileName, adminProfilePassword, adminProfileUsername});

        javax.swing.GroupLayout adminListProfileViewLayout = new javax.swing.GroupLayout(adminListProfileView.getContentPane());
        adminListProfileView.getContentPane().setLayout(adminListProfileViewLayout);
        adminListProfileViewLayout.setHorizontalGroup(
            adminListProfileViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        adminListProfileViewLayout.setVerticalGroup(
            adminListProfileViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        issueBookDialogPanel.setTitle("Issue Book");
        issueBookDialogPanel.setAlwaysOnTop(true);
        issueBookDialogPanel.setModal(true);
        issueBookDialogPanel.setUndecorated(true);
        issueBookDialogPanel.setResizable(false);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2));

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 51, 0));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("X");
        jLabel81.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel81.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel81MouseClicked(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(0, 153, 102));
        jLabel82.setText("Issue Book");

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(51, 51, 51));
        jLabel83.setText("Choose Return Date");

        jPanel17.setBackground(new java.awt.Color(51, 51, 51));
        jPanel17.setForeground(new java.awt.Color(255, 255, 255));
        jPanel17.setToolTipText("");

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("Issue Now");
        jLabel84.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel84.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel84MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel84MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel83)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel82)
                    .addComponent(jLabel81))
                .addGap(28, 28, 28)
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout issueBookDialogPanelLayout = new javax.swing.GroupLayout(issueBookDialogPanel.getContentPane());
        issueBookDialogPanel.getContentPane().setLayout(issueBookDialogPanelLayout);
        issueBookDialogPanelLayout.setHorizontalGroup(
            issueBookDialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        issueBookDialogPanelLayout.setVerticalGroup(
            issueBookDialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        processingFrame.setAlwaysOnTop(true);
        processingFrame.setUndecorated(true);
        processingFrame.setResizable(false);
        processingFrame.setType(java.awt.Window.Type.POPUP);

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/processing.gif"))); // NOI18N
        jLabel80.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout processingFrameLayout = new javax.swing.GroupLayout(processingFrame.getContentPane());
        processingFrame.getContentPane().setLayout(processingFrameLayout);
        processingFrameLayout.setHorizontalGroup(
            processingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        processingFrameLayout.setVerticalGroup(
            processingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("E-Library | Developed By Manas");
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeiconified(java.awt.event.WindowEvent evt) {
                Maximize(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        mainMenuContainer.setBackground(new java.awt.Color(17, 17, 17));
        mainMenuContainer.setPreferredSize(new java.awt.Dimension(75, 565));
        mainMenuContainer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainMenuContainerMouseClicked(evt);
            }
        });

        menuCloseBtnPanel.setBackground(new java.awt.Color(17, 17, 17));

        menuCloseBtn.setForeground(new java.awt.Color(255, 255, 255));
        menuCloseBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuCloseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Align_Justify_32px.png"))); // NOI18N
        menuCloseBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuCloseBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCloseBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuCloseBtnPanelLayout = new javax.swing.GroupLayout(menuCloseBtnPanel);
        menuCloseBtnPanel.setLayout(menuCloseBtnPanelLayout);
        menuCloseBtnPanelLayout.setHorizontalGroup(
            menuCloseBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuCloseBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        menuCloseBtnPanelLayout.setVerticalGroup(
            menuCloseBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuCloseBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(17, 17, 17));

        adminMenuBtn.setForeground(new java.awt.Color(255, 255, 255));
        adminMenuBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminMenuBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/adm.png"))); // NOI18N
        adminMenuBtn.setToolTipText("");
        adminMenuBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminMenuBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminMenuBtnMouseClicked(evt);
            }
        });

        studentMenuBtn.setForeground(new java.awt.Color(255, 255, 255));
        studentMenuBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        studentMenuBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stu.png"))); // NOI18N
        studentMenuBtn.setToolTipText("");
        studentMenuBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentMenuBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentMenuBtnMouseClicked(evt);
            }
        });

        bookMenuBtn.setForeground(new java.awt.Color(255, 255, 255));
        bookMenuBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookMenuBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book.png"))); // NOI18N
        bookMenuBtn.setToolTipText("");
        bookMenuBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bookMenuBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookMenuBtnMouseClicked(evt);
            }
        });

        helpMenuBtn.setForeground(new java.awt.Color(255, 255, 255));
        helpMenuBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        helpMenuBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ques.png"))); // NOI18N
        helpMenuBtn.setToolTipText("");
        helpMenuBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        helpMenuBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpMenuBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                helpMenuBtnMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminMenuBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(studentMenuBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bookMenuBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(helpMenuBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(adminMenuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(studentMenuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bookMenuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(helpMenuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout mainMenuContainerLayout = new javax.swing.GroupLayout(mainMenuContainer);
        mainMenuContainer.setLayout(mainMenuContainerLayout);
        mainMenuContainerLayout.setHorizontalGroup(
            mainMenuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuCloseBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainMenuContainerLayout.setVerticalGroup(
            mainMenuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuContainerLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(menuCloseBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        subMenuPanel.setPreferredSize(new java.awt.Dimension(250, 565));

        subMenuContainer.setLayout(new java.awt.CardLayout());

        adminSubMenuPanel.setBackground(new java.awt.Color(51, 51, 51));

        adminSubMenuContainer.setBackground(new java.awt.Color(51, 51, 51));

        addAdminBtn.setBackground(new java.awt.Color(45, 45, 45));
        addAdminBtn.setFont(new java.awt.Font("Tekton Pro Ext", 1, 14)); // NOI18N
        addAdminBtn.setForeground(new java.awt.Color(255, 255, 255));
        addAdminBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addAdminBtn.setText("Add Admin");
        addAdminBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addAdminBtn.setOpaque(true);
        addAdminBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addAdminBtnMouseClicked(evt);
            }
        });

        updateAdminBtn.setBackground(new java.awt.Color(45, 45, 45));
        updateAdminBtn.setFont(new java.awt.Font("Tekton Pro Ext", 1, 14)); // NOI18N
        updateAdminBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateAdminBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateAdminBtn.setText("Update Admin Details");
        updateAdminBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateAdminBtn.setOpaque(true);
        updateAdminBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateAdminBtnMouseClicked(evt);
            }
        });

        adminListBtn.setBackground(new java.awt.Color(45, 45, 45));
        adminListBtn.setFont(new java.awt.Font("Tekton Pro Ext", 1, 14)); // NOI18N
        adminListBtn.setForeground(new java.awt.Color(255, 255, 255));
        adminListBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminListBtn.setText("Admin List");
        adminListBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminListBtn.setOpaque(true);
        adminListBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminListBtnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                adminListBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout adminSubMenuContainerLayout = new javax.swing.GroupLayout(adminSubMenuContainer);
        adminSubMenuContainer.setLayout(adminSubMenuContainerLayout);
        adminSubMenuContainerLayout.setHorizontalGroup(
            adminSubMenuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addAdminBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(updateAdminBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addComponent(adminListBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        adminSubMenuContainerLayout.setVerticalGroup(
            adminSubMenuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminSubMenuContainerLayout.createSequentialGroup()
                .addComponent(addAdminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateAdminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adminListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout adminSubMenuPanelLayout = new javax.swing.GroupLayout(adminSubMenuPanel);
        adminSubMenuPanel.setLayout(adminSubMenuPanelLayout);
        adminSubMenuPanelLayout.setHorizontalGroup(
            adminSubMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminSubMenuContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        adminSubMenuPanelLayout.setVerticalGroup(
            adminSubMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminSubMenuPanelLayout.createSequentialGroup()
                .addGap(343, 343, 343)
                .addComponent(adminSubMenuContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        subMenuContainer.add(adminSubMenuPanel, "card2");

        helpSubMenuPanel.setBackground(new java.awt.Color(51, 51, 51));

        helpSubMenuContainer.setBackground(new java.awt.Color(51, 51, 51));

        tipOfTheDayBtn.setBackground(new java.awt.Color(45, 45, 45));
        tipOfTheDayBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        tipOfTheDayBtn.setForeground(new java.awt.Color(255, 255, 255));
        tipOfTheDayBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tipOfTheDayBtn.setText("Tip Of The Day");
        tipOfTheDayBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tipOfTheDayBtn.setOpaque(true);
        tipOfTheDayBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipOfTheDayBtnMouseClicked(evt);
            }
        });

        shortcutsBtn.setBackground(new java.awt.Color(45, 45, 45));
        shortcutsBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        shortcutsBtn.setForeground(new java.awt.Color(255, 255, 255));
        shortcutsBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shortcutsBtn.setText("Shortcuts");
        shortcutsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        shortcutsBtn.setOpaque(true);
        shortcutsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shortcutsBtnMouseClicked(evt);
            }
        });

        contactDeveloperBtn.setBackground(new java.awt.Color(45, 45, 45));
        contactDeveloperBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        contactDeveloperBtn.setForeground(new java.awt.Color(255, 255, 255));
        contactDeveloperBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contactDeveloperBtn.setText("Contact Developer");
        contactDeveloperBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contactDeveloperBtn.setOpaque(true);
        contactDeveloperBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contactDeveloperBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout helpSubMenuContainerLayout = new javax.swing.GroupLayout(helpSubMenuContainer);
        helpSubMenuContainer.setLayout(helpSubMenuContainerLayout);
        helpSubMenuContainerLayout.setHorizontalGroup(
            helpSubMenuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tipOfTheDayBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(shortcutsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contactDeveloperBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        helpSubMenuContainerLayout.setVerticalGroup(
            helpSubMenuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpSubMenuContainerLayout.createSequentialGroup()
                .addComponent(tipOfTheDayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(shortcutsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contactDeveloperBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout helpSubMenuPanelLayout = new javax.swing.GroupLayout(helpSubMenuPanel);
        helpSubMenuPanel.setLayout(helpSubMenuPanelLayout);
        helpSubMenuPanelLayout.setHorizontalGroup(
            helpSubMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(helpSubMenuContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        helpSubMenuPanelLayout.setVerticalGroup(
            helpSubMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, helpSubMenuPanelLayout.createSequentialGroup()
                .addGap(354, 354, 354)
                .addComponent(helpSubMenuContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        subMenuContainer.add(helpSubMenuPanel, "card5");

        bookSubMenuPanel.setBackground(new java.awt.Color(51, 51, 51));

        bookSubMenuContainer.setBackground(new java.awt.Color(51, 51, 51));

        addBookBtn.setBackground(new java.awt.Color(45, 45, 45));
        addBookBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        addBookBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBookBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addBookBtn.setText("Add Book");
        addBookBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBookBtn.setOpaque(true);
        addBookBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addBookBtnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addBookBtnMousePressed(evt);
            }
        });

        bookListBtn.setBackground(new java.awt.Color(45, 45, 45));
        bookListBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        bookListBtn.setForeground(new java.awt.Color(255, 255, 255));
        bookListBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookListBtn.setText("Book List");
        bookListBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bookListBtn.setOpaque(true);
        bookListBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookListBtnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bookListBtnMousePressed(evt);
            }
        });

        trackIssuedBookBtn.setBackground(new java.awt.Color(45, 45, 45));
        trackIssuedBookBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        trackIssuedBookBtn.setForeground(new java.awt.Color(255, 255, 255));
        trackIssuedBookBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        trackIssuedBookBtn.setText("Track Issued Book");
        trackIssuedBookBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        trackIssuedBookBtn.setOpaque(true);
        trackIssuedBookBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trackIssuedBookBtnMouseClicked(evt);
            }
        });

        requestListForBookIssueBtn.setBackground(new java.awt.Color(45, 45, 45));
        requestListForBookIssueBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        requestListForBookIssueBtn.setForeground(new java.awt.Color(255, 255, 255));
        requestListForBookIssueBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        requestListForBookIssueBtn.setText("Request List For Book Issue");
        requestListForBookIssueBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        requestListForBookIssueBtn.setOpaque(true);
        requestListForBookIssueBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestListForBookIssueBtnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                requestListForBookIssueBtnMousePressed(evt);
            }
        });

        AddEBookBtn.setBackground(new java.awt.Color(45, 45, 45));
        AddEBookBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        AddEBookBtn.setForeground(new java.awt.Color(255, 255, 255));
        AddEBookBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddEBookBtn.setText("Add E-Book");
        AddEBookBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddEBookBtn.setOpaque(true);
        AddEBookBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddEBookBtnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AddEBookBtnMousePressed(evt);
            }
        });

        eBookListBtn.setBackground(new java.awt.Color(45, 45, 45));
        eBookListBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        eBookListBtn.setForeground(new java.awt.Color(255, 255, 255));
        eBookListBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eBookListBtn.setText("E-Book List");
        eBookListBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eBookListBtn.setOpaque(true);
        eBookListBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eBookListBtnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                eBookListBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout bookSubMenuContainerLayout = new javax.swing.GroupLayout(bookSubMenuContainer);
        bookSubMenuContainer.setLayout(bookSubMenuContainerLayout);
        bookSubMenuContainerLayout.setHorizontalGroup(
            bookSubMenuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bookListBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(trackIssuedBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(requestListForBookIssueBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addComponent(AddEBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(eBookListBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bookSubMenuContainerLayout.setVerticalGroup(
            bookSubMenuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookSubMenuContainerLayout.createSequentialGroup()
                .addComponent(addBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bookListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(trackIssuedBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(requestListForBookIssueBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddEBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eBookListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout bookSubMenuPanelLayout = new javax.swing.GroupLayout(bookSubMenuPanel);
        bookSubMenuPanel.setLayout(bookSubMenuPanelLayout);
        bookSubMenuPanelLayout.setHorizontalGroup(
            bookSubMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookSubMenuContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bookSubMenuPanelLayout.setVerticalGroup(
            bookSubMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookSubMenuPanelLayout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(bookSubMenuContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        subMenuContainer.add(bookSubMenuPanel, "card4");

        studentSubMenuPanel.setBackground(new java.awt.Color(51, 51, 51));

        studentSubMenuContainer.setBackground(new java.awt.Color(51, 51, 51));

        addStudentBtn.setBackground(new java.awt.Color(45, 45, 45));
        addStudentBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        addStudentBtn.setForeground(new java.awt.Color(255, 255, 255));
        addStudentBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addStudentBtn.setText("Add Student");
        addStudentBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addStudentBtn.setOpaque(true);
        addStudentBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addStudentBtnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addStudentBtnMousePressed(evt);
            }
        });

        updateStudentBtn.setBackground(new java.awt.Color(45, 45, 45));
        updateStudentBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        updateStudentBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateStudentBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateStudentBtn.setText("Update Student");
        updateStudentBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateStudentBtn.setOpaque(true);
        updateStudentBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateStudentBtnMouseClicked(evt);
            }
        });

        studentListBtn.setBackground(new java.awt.Color(45, 45, 45));
        studentListBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        studentListBtn.setForeground(new java.awt.Color(255, 255, 255));
        studentListBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        studentListBtn.setText("Student List");
        studentListBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentListBtn.setOpaque(true);
        studentListBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentListBtnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                studentListBtnMousePressed(evt);
            }
        });

        studentAttendenceBtn.setBackground(new java.awt.Color(45, 45, 45));
        studentAttendenceBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        studentAttendenceBtn.setForeground(new java.awt.Color(255, 255, 255));
        studentAttendenceBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        studentAttendenceBtn.setText("Student Attendance");
        studentAttendenceBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentAttendenceBtn.setOpaque(true);
        studentAttendenceBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentAttendenceBtnMouseClicked(evt);
            }
        });

        viewStudentAttendanceRecordBtn.setBackground(new java.awt.Color(45, 45, 45));
        viewStudentAttendanceRecordBtn.setFont(new java.awt.Font("Tekton Pro", 1, 14)); // NOI18N
        viewStudentAttendanceRecordBtn.setForeground(new java.awt.Color(255, 255, 255));
        viewStudentAttendanceRecordBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewStudentAttendanceRecordBtn.setText("Student Attendance Record");
        viewStudentAttendanceRecordBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewStudentAttendanceRecordBtn.setOpaque(true);
        viewStudentAttendanceRecordBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewStudentAttendanceRecordBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout studentSubMenuContainerLayout = new javax.swing.GroupLayout(studentSubMenuContainer);
        studentSubMenuContainer.setLayout(studentSubMenuContainerLayout);
        studentSubMenuContainerLayout.setHorizontalGroup(
            studentSubMenuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addStudentBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(updateStudentBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(studentListBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(studentAttendenceBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(viewStudentAttendanceRecordBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        studentSubMenuContainerLayout.setVerticalGroup(
            studentSubMenuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentSubMenuContainerLayout.createSequentialGroup()
                .addComponent(addStudentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateStudentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(studentListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(studentAttendenceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewStudentAttendanceRecordBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout studentSubMenuPanelLayout = new javax.swing.GroupLayout(studentSubMenuPanel);
        studentSubMenuPanel.setLayout(studentSubMenuPanelLayout);
        studentSubMenuPanelLayout.setHorizontalGroup(
            studentSubMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentSubMenuPanelLayout.createSequentialGroup()
                .addComponent(studentSubMenuContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        studentSubMenuPanelLayout.setVerticalGroup(
            studentSubMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentSubMenuPanelLayout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(studentSubMenuContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        subMenuContainer.add(studentSubMenuPanel, "card3");

        subMenuPanel.setLayer(subMenuContainer, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout subMenuPanelLayout = new javax.swing.GroupLayout(subMenuPanel);
        subMenuPanel.setLayout(subMenuPanelLayout);
        subMenuPanelLayout.setHorizontalGroup(
            subMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subMenuContainer)
        );
        subMenuPanelLayout.setVerticalGroup(
            subMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subMenuContainer)
        );

        mainContainer.setLayout(new java.awt.CardLayout());

        HomeMainPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Developed By Manas And Suryakant Dixit");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Library Management System");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/books.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout HomeMainPanelLayout = new javax.swing.GroupLayout(HomeMainPanel);
        HomeMainPanel.setLayout(HomeMainPanelLayout);
        HomeMainPanelLayout.setHorizontalGroup(
            HomeMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        HomeMainPanelLayout.setVerticalGroup(
            HomeMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(254, 254, 254))
        );

        mainContainer.add(HomeMainPanel, "card2");

        addAdminPanel.setBackground(new java.awt.Color(255, 255, 255));
        addAdminPanel.setLayout(new java.awt.GridBagLayout());

        addAdminSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 195, 122));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ADD ADMIN");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Name :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Aadhar Number : ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Email :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Address : ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Contact No. : ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Username : ");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Password : ");

        addAdminName.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addAdminName.setForeground(new java.awt.Color(51, 51, 51));
        addAdminName.setNextFocusableComponent(addAdminEmail);

        addAdminEmail.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addAdminEmail.setForeground(new java.awt.Color(51, 51, 51));
        addAdminEmail.setNextFocusableComponent(addAdminContact);

        addAdminContact.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addAdminContact.setForeground(new java.awt.Color(51, 51, 51));
        addAdminContact.setNextFocusableComponent(addAdminAadhar);

        addAdminAadhar.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addAdminAadhar.setForeground(new java.awt.Color(51, 51, 51));
        addAdminAadhar.setNextFocusableComponent(addAdminPhoto);
        addAdminAadhar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                addAdminAadharFocusLost(evt);
            }
        });

        addAdminUsername.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addAdminUsername.setForeground(new java.awt.Color(51, 51, 51));
        addAdminUsername.setNextFocusableComponent(addAdminPassword);

        addAdminPassword.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addAdminPassword.setForeground(new java.awt.Color(51, 51, 51));
        addAdminPassword.setNextFocusableComponent(addAdminResetBtn);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        addAdminPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addAdminPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/userProfile.png"))); // NOI18N
        addAdminPhoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addAdminPhoto.setNextFocusableComponent(addAdminAddress);
        addAdminPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addAdminPhotoMouseClicked(evt);
            }
        });
        addAdminPhoto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                addAdminPhotoKeyReleased(evt);
            }
        });

        addAdminPhotoName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addAdminPhotoName.setForeground(new java.awt.Color(61, 61, 61));
        addAdminPhotoName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addAdminPhotoName.setText("Upload Photo");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addAdminPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
            .addComponent(addAdminPhotoName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(addAdminPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(addAdminPhotoName, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
        );

        addAdminAddress.setColumns(20);
        addAdminAddress.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addAdminAddress.setForeground(new java.awt.Color(51, 51, 51));
        addAdminAddress.setRows(5);
        addAdminAddress.setNextFocusableComponent(addAdminUsername);
        jScrollPane1.setViewportView(addAdminAddress);

        addAdminResetBtnPanel.setBackground(new java.awt.Color(51, 51, 51));
        addAdminResetBtnPanel.setName("addAdminResetBtnPanel"); // NOI18N

        addAdminResetBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addAdminResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        addAdminResetBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addAdminResetBtn.setText("Reset");
        addAdminResetBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addAdminResetBtn.setNextFocusableComponent(addAdminRegisterBtn);
        addAdminResetBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addAdminResetBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                addAdminResetBtnFocusLost(evt);
            }
        });
        addAdminResetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addAdminResetBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addAdminResetBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addAdminResetBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout addAdminResetBtnPanelLayout = new javax.swing.GroupLayout(addAdminResetBtnPanel);
        addAdminResetBtnPanel.setLayout(addAdminResetBtnPanelLayout);
        addAdminResetBtnPanelLayout.setHorizontalGroup(
            addAdminResetBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addAdminResetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addAdminResetBtnPanelLayout.setVerticalGroup(
            addAdminResetBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addAdminResetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        addAdminRegisterBtnPanel.setBackground(new java.awt.Color(51, 51, 51));

        addAdminRegisterBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addAdminRegisterBtn.setForeground(new java.awt.Color(255, 255, 255));
        addAdminRegisterBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addAdminRegisterBtn.setText("Register Admin");
        addAdminRegisterBtn.setToolTipText("");
        addAdminRegisterBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addAdminRegisterBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addAdminRegisterBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                addAdminRegisterBtnFocusLost(evt);
            }
        });
        addAdminRegisterBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addAdminRegisterBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addAdminRegisterBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addAdminRegisterBtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addAdminRegisterBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout addAdminRegisterBtnPanelLayout = new javax.swing.GroupLayout(addAdminRegisterBtnPanel);
        addAdminRegisterBtnPanel.setLayout(addAdminRegisterBtnPanelLayout);
        addAdminRegisterBtnPanelLayout.setHorizontalGroup(
            addAdminRegisterBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addAdminRegisterBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addAdminRegisterBtnPanelLayout.setVerticalGroup(
            addAdminRegisterBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addAdminRegisterBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout addAdminSubPanelLayout = new javax.swing.GroupLayout(addAdminSubPanel);
        addAdminSubPanel.setLayout(addAdminSubPanelLayout);
        addAdminSubPanelLayout.setHorizontalGroup(
            addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addAdminSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addAdminSubPanelLayout.createSequentialGroup()
                        .addGroup(addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addAdminResetBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addGap(27, 27, 27)
                        .addGroup(addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addAdminSubPanelLayout.createSequentialGroup()
                                .addComponent(addAdminUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15)
                                .addGap(51, 51, 51)
                                .addComponent(addAdminPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addAdminSubPanelLayout.createSequentialGroup()
                                .addGroup(addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addAdminAadhar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addAdminContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addAdminEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addAdminName, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)
                            .addComponent(addAdminRegisterBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        addAdminSubPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addAdminAadhar, addAdminContact, addAdminEmail, addAdminName});

        addAdminSubPanelLayout.setVerticalGroup(
            addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addAdminSubPanelLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addAdminSubPanelLayout.createSequentialGroup()
                        .addGroup(addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(addAdminName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(addAdminEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(addAdminContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(addAdminAadhar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(addAdminUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(addAdminPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addAdminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addAdminResetBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addAdminRegisterBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addAdminSubPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addAdminAadhar, addAdminContact, addAdminEmail, addAdminName, addAdminPassword, addAdminUsername});

        addAdminResetBtnPanel.getAccessibleContext().setAccessibleName("");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        addAdminPanel.add(addAdminSubPanel, gridBagConstraints);

        mainContainer.add(addAdminPanel, "card3");

        bookUpdateMainPanel.setBackground(new java.awt.Color(255, 255, 255));
        bookUpdateMainPanel.setLayout(new java.awt.GridBagLayout());

        bookUpdateSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel85.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 195, 122));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("BOOK STOCK UPDATE");

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel86.setText("BOOK ID");

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel87.setText("CATEGORY");

        jLabel88.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel88.setText("SUB CAT");

        bookStockSubCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookStockSubCatActionPerformed(evt);
            }
        });

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel89.setText("NAME");

        bookStockWriter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookStockWriterActionPerformed(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel90.setText("WRITER");

        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel91.setText("PUBLISHER");

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel93.setText("ADD STOCK");

        bookStockUpdateStockPanel.setBackground(new java.awt.Color(55, 55, 55));

        bookStockUpdateStockBtn.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        bookStockUpdateStockBtn.setForeground(new java.awt.Color(255, 255, 255));
        bookStockUpdateStockBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookStockUpdateStockBtn.setText("UPDATE STOCK");
        bookStockUpdateStockBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookStockUpdateStockBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookStockUpdateStockBtnMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout bookStockUpdateStockPanelLayout = new javax.swing.GroupLayout(bookStockUpdateStockPanel);
        bookStockUpdateStockPanel.setLayout(bookStockUpdateStockPanelLayout);
        bookStockUpdateStockPanelLayout.setHorizontalGroup(
            bookStockUpdateStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookStockUpdateStockBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bookStockUpdateStockPanelLayout.setVerticalGroup(
            bookStockUpdateStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookStockUpdateStockBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        bookStockBackPanel.setBackground(new java.awt.Color(55, 55, 55));

        bookStockBackBtn.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        bookStockBackBtn.setForeground(new java.awt.Color(255, 255, 255));
        bookStockBackBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookStockBackBtn.setText("BACK");
        bookStockBackBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookStockBackBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout bookStockBackPanelLayout = new javax.swing.GroupLayout(bookStockBackPanel);
        bookStockBackPanel.setLayout(bookStockBackPanelLayout);
        bookStockBackPanelLayout.setHorizontalGroup(
            bookStockBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookStockBackBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bookStockBackPanelLayout.setVerticalGroup(
            bookStockBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookStockBackBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout bookUpdateSubPanelLayout = new javax.swing.GroupLayout(bookUpdateSubPanel);
        bookUpdateSubPanel.setLayout(bookUpdateSubPanelLayout);
        bookUpdateSubPanelLayout.setHorizontalGroup(
            bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
            .addGroup(bookUpdateSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bookUpdateSubPanelLayout.createSequentialGroup()
                        .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(bookUpdateSubPanelLayout.createSequentialGroup()
                                .addComponent(bookStockBackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bookUpdateSubPanelLayout.createSequentialGroup()
                                .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookUpdateSubPanelLayout.createSequentialGroup()
                                        .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel88)
                                            .addComponent(jLabel90))
                                        .addGap(27, 27, 27))
                                    .addGroup(bookUpdateSubPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel86)
                                        .addGap(22, 22, 22)))
                                .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(bookStockBookId, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                    .addComponent(bookStockSubCat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                    .addComponent(bookStockWriter, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bookStockUpdateStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(bookUpdateSubPanelLayout.createSequentialGroup()
                                .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel87)
                                    .addComponent(jLabel89)
                                    .addComponent(jLabel91))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bookStockPublisher, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                    .addComponent(bookStockCat)
                                    .addComponent(bookStockName)))))
                    .addGroup(bookUpdateSubPanelLayout.createSequentialGroup()
                        .addComponent(jLabel93)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bookStockAddStock)))
                .addContainerGap())
        );
        bookUpdateSubPanelLayout.setVerticalGroup(
            bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookUpdateSubPanelLayout.createSequentialGroup()
                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bookStockBookId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel86)
                        .addComponent(jLabel87))
                    .addComponent(bookStockCat))
                .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bookUpdateSubPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bookStockName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bookUpdateSubPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bookStockSubCat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel89)
                            .addComponent(jLabel88))))
                .addGap(18, 18, 18)
                .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bookStockWriter, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(jLabel90)
                        .addComponent(jLabel91))
                    .addComponent(bookStockPublisher))
                .addGap(18, 18, 18)
                .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookStockAddStock, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(bookUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bookStockUpdateStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bookStockBackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 181, 0);
        bookUpdateMainPanel.add(bookUpdateSubPanel, gridBagConstraints);

        mainContainer.add(bookUpdateMainPanel, "card19");

        studentListMainPanel.setBackground(new java.awt.Color(255, 255, 255));

        studentListSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel52.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 195, 122));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("STUDENT LIST");

        studentListTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        studentListTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        studentListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        studentListTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentListTable.setRowHeight(80);
        studentListTable.setSelectionBackground(new java.awt.Color(51, 51, 51));
        studentListScrollPane.setViewportView(studentListTable);

        studentListDeletePanel.setBackground(new java.awt.Color(55, 55, 55));

        studentListDeleteBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        studentListDeleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        studentListDeleteBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        studentListDeleteBtn.setText("DELETE");
        studentListDeleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentListDeleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentListDeleteBtnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                studentListDeleteBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout studentListDeletePanelLayout = new javax.swing.GroupLayout(studentListDeletePanel);
        studentListDeletePanel.setLayout(studentListDeletePanelLayout);
        studentListDeletePanelLayout.setHorizontalGroup(
            studentListDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studentListDeleteBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        studentListDeletePanelLayout.setVerticalGroup(
            studentListDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studentListDeleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        studentListSearchPanel.setBackground(new java.awt.Color(255, 255, 255));

        studentListSearchBtn.setBackground(new java.awt.Color(255, 255, 255));
        studentListSearchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_32px_1.png"))); // NOI18N
        studentListSearchBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        studentListSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentListSearchBtnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                studentListSearchBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout studentListSearchPanelLayout = new javax.swing.GroupLayout(studentListSearchPanel);
        studentListSearchPanel.setLayout(studentListSearchPanelLayout);
        studentListSearchPanelLayout.setHorizontalGroup(
            studentListSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentListSearchPanelLayout.createSequentialGroup()
                .addComponent(studentListSearchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(studentListSearchBtn))
        );
        studentListSearchPanelLayout.setVerticalGroup(
            studentListSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studentListSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(studentListSearchTextField)
        );

        clearStudentSearchBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearStudentSearchBtn.setForeground(new java.awt.Color(255, 51, 0));
        clearStudentSearchBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clearStudentSearchBtn.setText("Clear");
        clearStudentSearchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearStudentSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearStudentSearchBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout studentListSubPanelLayout = new javax.swing.GroupLayout(studentListSubPanel);
        studentListSubPanel.setLayout(studentListSubPanelLayout);
        studentListSubPanelLayout.setHorizontalGroup(
            studentListSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studentListScrollPane)
            .addGroup(studentListSubPanelLayout.createSequentialGroup()
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                .addComponent(clearStudentSearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(studentListSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(studentListDeletePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        studentListSubPanelLayout.setVerticalGroup(
            studentListSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentListSubPanelLayout.createSequentialGroup()
                .addGroup(studentListSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studentListSearchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearStudentSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(studentListDeletePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout studentListMainPanelLayout = new javax.swing.GroupLayout(studentListMainPanel);
        studentListMainPanel.setLayout(studentListMainPanelLayout);
        studentListMainPanelLayout.setHorizontalGroup(
            studentListMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentListMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(studentListSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        studentListMainPanelLayout.setVerticalGroup(
            studentListMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentListMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(studentListSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainContainer.add(studentListMainPanel, "card18");

        studentSearchMainPanel.setBackground(new java.awt.Color(255, 255, 255));
        studentSearchMainPanel.setLayout(new java.awt.GridBagLayout());

        studentSearchSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel51.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 195, 122));
        jLabel51.setText("STUDENT AADHAR / NAME / LIB-ID");

        searchStudentTextField.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        searchStudentTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchStudentTextFieldActionPerformed(evt);
            }
        });

        studentResetBtnPanel.setBackground(new java.awt.Color(55, 55, 55));

        studentResetBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        studentResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        studentResetBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        studentResetBtn.setText("RESET");
        studentResetBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentResetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentResetBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                studentResetBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                studentResetBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout studentResetBtnPanelLayout = new javax.swing.GroupLayout(studentResetBtnPanel);
        studentResetBtnPanel.setLayout(studentResetBtnPanelLayout);
        studentResetBtnPanelLayout.setHorizontalGroup(
            studentResetBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studentResetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        studentResetBtnPanelLayout.setVerticalGroup(
            studentResetBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studentResetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        studentSearchBtnPanel.setBackground(new java.awt.Color(55, 55, 55));

        studentSearchBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        studentSearchBtn.setForeground(new java.awt.Color(255, 255, 255));
        studentSearchBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        studentSearchBtn.setText("SEARCH");
        studentSearchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentSearchBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                studentSearchBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                studentSearchBtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                studentSearchBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout studentSearchBtnPanelLayout = new javax.swing.GroupLayout(studentSearchBtnPanel);
        studentSearchBtnPanel.setLayout(studentSearchBtnPanelLayout);
        studentSearchBtnPanelLayout.setHorizontalGroup(
            studentSearchBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studentSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        studentSearchBtnPanelLayout.setVerticalGroup(
            studentSearchBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studentSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout studentSearchSubPanelLayout = new javax.swing.GroupLayout(studentSearchSubPanel);
        studentSearchSubPanel.setLayout(studentSearchSubPanelLayout);
        studentSearchSubPanelLayout.setHorizontalGroup(
            studentSearchSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
            .addGroup(studentSearchSubPanelLayout.createSequentialGroup()
                .addComponent(studentResetBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(studentSearchBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(searchStudentTextField)
        );
        studentSearchSubPanelLayout.setVerticalGroup(
            studentSearchSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentSearchSubPanelLayout.createSequentialGroup()
                .addComponent(jLabel51)
                .addGap(18, 18, 18)
                .addComponent(searchStudentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(studentSearchSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(studentResetBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentSearchBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 230;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(166, 10, 210, 10);
        studentSearchMainPanel.add(studentSearchSubPanel, gridBagConstraints);

        mainContainer.add(studentSearchMainPanel, "card9");

        eBookListMainPanel.setBackground(new java.awt.Color(255, 255, 255));

        eBookListSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel78.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(0, 195, 122));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("EBOOK LIST");

        eBookListTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        eBookListTable.setForeground(new java.awt.Color(51, 51, 51));
        eBookListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        eBookListTable.setRowHeight(50);
        eBookListTable.setSelectionBackground(new java.awt.Color(51, 51, 51));
        eBookListScrollPane.setViewportView(eBookListTable);

        ebookListRemovePanel.setBackground(new java.awt.Color(55, 55, 55));

        ebookListRemoveBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ebookListRemoveBtn.setForeground(new java.awt.Color(255, 255, 255));
        ebookListRemoveBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ebookListRemoveBtn.setText("REMOVE");
        ebookListRemoveBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ebookListRemoveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ebookListRemoveBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ebookListRemoveBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ebookListRemoveBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ebookListRemovePanelLayout = new javax.swing.GroupLayout(ebookListRemovePanel);
        ebookListRemovePanel.setLayout(ebookListRemovePanelLayout);
        ebookListRemovePanelLayout.setHorizontalGroup(
            ebookListRemovePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ebookListRemoveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ebookListRemovePanelLayout.setVerticalGroup(
            ebookListRemovePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ebookListRemoveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout eBookListSubPanelLayout = new javax.swing.GroupLayout(eBookListSubPanel);
        eBookListSubPanel.setLayout(eBookListSubPanelLayout);
        eBookListSubPanelLayout.setHorizontalGroup(
            eBookListSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(eBookListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addComponent(ebookListRemovePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        eBookListSubPanelLayout.setVerticalGroup(
            eBookListSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eBookListSubPanelLayout.createSequentialGroup()
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eBookListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(ebookListRemovePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout eBookListMainPanelLayout = new javax.swing.GroupLayout(eBookListMainPanel);
        eBookListMainPanel.setLayout(eBookListMainPanelLayout);
        eBookListMainPanelLayout.setHorizontalGroup(
            eBookListMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eBookListMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eBookListSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        eBookListMainPanelLayout.setVerticalGroup(
            eBookListMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eBookListMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eBookListSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainContainer.add(eBookListMainPanel, "card15");

        eBookMainPanel.setBackground(new java.awt.Color(255, 255, 255));
        eBookMainPanel.setLayout(new java.awt.GridBagLayout());

        eBookSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel70.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 195, 122));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("ADD EBOOK");

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel71.setText("CATEGORY");

        addEBookCat.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addEBookCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Category" }));
        addEBookCat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                addEBookCatItemStateChanged(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel72.setText("SUB CAT");

        addEBookSubCat.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addEBookSubCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Sub Category" }));
        addEBookSubCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEBookSubCatActionPerformed(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel73.setText("BOOK NAME");

        addEBookBookName.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N

        addEBookWriter.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel74.setText("WRITER");

        addEBookPublisher.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N

        addEBookEdition.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addEBookEdition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEBookEditionActionPerformed(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel75.setText("PUBLISHER");

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel76.setText("EDITION");

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel77.setText("DISCRIPTION");

        addEBookDescription.setColumns(20);
        addEBookDescription.setRows(5);
        jScrollPane6.setViewportView(addEBookDescription);

        jPanel14.setBackground(new java.awt.Color(55, 55, 55));

        addEBookAddBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addEBookAddBtn.setForeground(new java.awt.Color(255, 255, 255));
        addEBookAddBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addEBookAddBtn.setText("ADD BOOK");
        addEBookAddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addEBookAddBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addEBookAddBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addEBookAddBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addEBookAddBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addEBookAddBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        addEBookLocation.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        addEBookLocation.setForeground(new java.awt.Color(102, 102, 102));
        addEBookLocation.setText("LOCATION");

        jPanel13.setBackground(new java.awt.Color(55, 55, 55));

        addEBookUplodeBookBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addEBookUplodeBookBtn.setForeground(new java.awt.Color(255, 255, 255));
        addEBookUplodeBookBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addEBookUplodeBookBtn.setText("UPLOAD BOOK");
        addEBookUplodeBookBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addEBookUplodeBookBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addEBookUplodeBookBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addEBookUplodeBookBtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addEBookUplodeBookBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addEBookUplodeBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addEBookUplodeBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout eBookSubPanelLayout = new javax.swing.GroupLayout(eBookSubPanel);
        eBookSubPanel.setLayout(eBookSubPanelLayout);
        eBookSubPanelLayout.setHorizontalGroup(
            eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eBookSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, eBookSubPanelLayout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addGap(35, 35, 35)
                        .addComponent(addEBookCat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, eBookSubPanelLayout.createSequentialGroup()
                        .addComponent(jLabel77)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addEBookLocation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, eBookSubPanelLayout.createSequentialGroup()
                        .addGroup(eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73)
                            .addComponent(jLabel72)
                            .addComponent(jLabel75))
                        .addGap(28, 28, 28)
                        .addGroup(eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addEBookSubCat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(eBookSubPanelLayout.createSequentialGroup()
                                .addGroup(eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(addEBookPublisher, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                    .addComponent(addEBookBookName))
                                .addGap(18, 18, 18)
                                .addGroup(eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(eBookSubPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel74)
                                        .addGap(24, 24, 24)
                                        .addComponent(addEBookWriter))
                                    .addGroup(eBookSubPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel76)
                                        .addGap(20, 20, 20)
                                        .addComponent(addEBookEdition)))))))
                .addContainerGap())
        );
        eBookSubPanelLayout.setVerticalGroup(
            eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eBookSubPanelLayout.createSequentialGroup()
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(addEBookCat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addEBookSubCat, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel72))
                .addGap(18, 18, 18)
                .addGroup(eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addEBookBookName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel73))
                    .addGroup(eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addEBookWriter, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel74)))
                .addGap(18, 18, 18)
                .addGroup(eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addEBookEdition, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addEBookPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel75)
                        .addComponent(jLabel76)))
                .addGap(18, 18, 18)
                .addGroup(eBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel77)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(eBookSubPanelLayout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addEBookLocation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 220;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 12, 58, 15);
        eBookMainPanel.add(eBookSubPanel, gridBagConstraints);

        mainContainer.add(eBookMainPanel, "card14");

        requestBookIssueMainPanel.setBackground(new java.awt.Color(255, 255, 255));

        requestBookIssueSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel67.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 195, 122));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("REQUEST FOR BOOK");

        bookRequestTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bookRequestTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        bookRequestTable.setForeground(new java.awt.Color(51, 51, 51));
        bookRequestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        bookRequestTable.setRowHeight(50);
        bookRequestTable.setSelectionBackground(new java.awt.Color(51, 51, 51));
        bookRequestTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookRequestTableMouseClicked(evt);
            }
        });
        bookRequestScrollPane.setViewportView(bookRequestTable);

        booRequestCancelPanel.setBackground(new java.awt.Color(51, 51, 51));

        bookRequestCancelBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bookRequestCancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        bookRequestCancelBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookRequestCancelBtn.setText("CANCEL");
        bookRequestCancelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bookRequestCancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookRequestCancelBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookRequestCancelBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookRequestCancelBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout booRequestCancelPanelLayout = new javax.swing.GroupLayout(booRequestCancelPanel);
        booRequestCancelPanel.setLayout(booRequestCancelPanelLayout);
        booRequestCancelPanelLayout.setHorizontalGroup(
            booRequestCancelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookRequestCancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        booRequestCancelPanelLayout.setVerticalGroup(
            booRequestCancelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookRequestCancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        bookRequestAllowPanel.setBackground(new java.awt.Color(51, 51, 51));

        bookRequestAllowBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bookRequestAllowBtn.setForeground(new java.awt.Color(255, 255, 255));
        bookRequestAllowBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookRequestAllowBtn.setText("Allow");
        bookRequestAllowBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bookRequestAllowBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookRequestAllowBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookRequestAllowBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookRequestAllowBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout bookRequestAllowPanelLayout = new javax.swing.GroupLayout(bookRequestAllowPanel);
        bookRequestAllowPanel.setLayout(bookRequestAllowPanelLayout);
        bookRequestAllowPanelLayout.setHorizontalGroup(
            bookRequestAllowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookRequestAllowBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bookRequestAllowPanelLayout.setVerticalGroup(
            bookRequestAllowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookRequestAllowBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout requestBookIssueSubPanelLayout = new javax.swing.GroupLayout(requestBookIssueSubPanel);
        requestBookIssueSubPanel.setLayout(requestBookIssueSubPanelLayout);
        requestBookIssueSubPanelLayout.setHorizontalGroup(
            requestBookIssueSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(requestBookIssueSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(requestBookIssueSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bookRequestScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                    .addGroup(requestBookIssueSubPanelLayout.createSequentialGroup()
                        .addComponent(booRequestCancelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(bookRequestAllowPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        requestBookIssueSubPanelLayout.setVerticalGroup(
            requestBookIssueSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestBookIssueSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bookRequestScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(requestBookIssueSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(booRequestCancelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bookRequestAllowPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout requestBookIssueMainPanelLayout = new javax.swing.GroupLayout(requestBookIssueMainPanel);
        requestBookIssueMainPanel.setLayout(requestBookIssueMainPanelLayout);
        requestBookIssueMainPanelLayout.setHorizontalGroup(
            requestBookIssueMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(requestBookIssueSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        requestBookIssueMainPanelLayout.setVerticalGroup(
            requestBookIssueMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(requestBookIssueSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainContainer.add(requestBookIssueMainPanel, "card13");

        trackIssuedBookMainPanel.setBackground(new java.awt.Color(255, 255, 255));

        trackIssuedBookSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel65.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 195, 122));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("ISSUED BOOKS");

        TrackBookListSearchPanel.setBackground(new java.awt.Color(255, 255, 255));

        trackBookListSearchField.setToolTipText("");

        trackBookListSearchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_32px_1.png"))); // NOI18N
        trackBookListSearchBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        trackBookListSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trackBookListSearchBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout TrackBookListSearchPanelLayout = new javax.swing.GroupLayout(TrackBookListSearchPanel);
        TrackBookListSearchPanel.setLayout(TrackBookListSearchPanelLayout);
        TrackBookListSearchPanelLayout.setHorizontalGroup(
            TrackBookListSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrackBookListSearchPanelLayout.createSequentialGroup()
                .addComponent(trackBookListSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(trackBookListSearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        TrackBookListSearchPanelLayout.setVerticalGroup(
            TrackBookListSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(trackBookListSearchField)
            .addComponent(trackBookListSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        trackIssuedBookTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        trackIssuedBookTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        trackIssuedBookTable.setForeground(new java.awt.Color(51, 51, 51));
        trackIssuedBookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        trackIssuedBookTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        trackIssuedBookTable.setRowHeight(50);
        trackIssuedBookTable.setSelectionBackground(new java.awt.Color(51, 51, 51));
        trackIssuedBookScrollPane.setViewportView(trackIssuedBookTable);

        jPanel11.setBackground(new java.awt.Color(51, 51, 51));

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("Return Book");
        jLabel66.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel66MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel66MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel66MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
        );

        clearIssuedSearchBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearIssuedSearchBtn.setForeground(new java.awt.Color(255, 0, 0));
        clearIssuedSearchBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clearIssuedSearchBtn.setText("Clear");
        clearIssuedSearchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearIssuedSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearIssuedSearchBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trackIssuedBookSubPanelLayout = new javax.swing.GroupLayout(trackIssuedBookSubPanel);
        trackIssuedBookSubPanel.setLayout(trackIssuedBookSubPanelLayout);
        trackIssuedBookSubPanelLayout.setHorizontalGroup(
            trackIssuedBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trackIssuedBookSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(trackIssuedBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(trackIssuedBookSubPanelLayout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                        .addComponent(clearIssuedSearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TrackBookListSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trackIssuedBookScrollPane, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        trackIssuedBookSubPanelLayout.setVerticalGroup(
            trackIssuedBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trackIssuedBookSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(trackIssuedBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TrackBookListSearchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearIssuedSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(trackIssuedBookScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout trackIssuedBookMainPanelLayout = new javax.swing.GroupLayout(trackIssuedBookMainPanel);
        trackIssuedBookMainPanel.setLayout(trackIssuedBookMainPanelLayout);
        trackIssuedBookMainPanelLayout.setHorizontalGroup(
            trackIssuedBookMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(trackIssuedBookSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        trackIssuedBookMainPanelLayout.setVerticalGroup(
            trackIssuedBookMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(trackIssuedBookSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainContainer.add(trackIssuedBookMainPanel, "card12");

        bookListMainPanel.setBackground(new java.awt.Color(255, 255, 255));

        bookListSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        bookListDeletePanel.setBackground(new java.awt.Color(51, 51, 51));

        bookListDeleteBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bookListDeleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        bookListDeleteBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookListDeleteBtn.setText("DELETE");
        bookListDeleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bookListDeleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookListDeleteBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookListDeleteBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookListDeleteBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout bookListDeletePanelLayout = new javax.swing.GroupLayout(bookListDeletePanel);
        bookListDeletePanel.setLayout(bookListDeletePanelLayout);
        bookListDeletePanelLayout.setHorizontalGroup(
            bookListDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookListDeleteBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bookListDeletePanelLayout.setVerticalGroup(
            bookListDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookListDeleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        bookListUpdatePanel.setBackground(new java.awt.Color(51, 51, 51));

        bookListUpdateBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bookListUpdateBtn.setForeground(new java.awt.Color(255, 255, 255));
        bookListUpdateBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookListUpdateBtn.setText("UPDATE");
        bookListUpdateBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bookListUpdateBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookListUpdateBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookListUpdateBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookListUpdateBtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bookListUpdateBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout bookListUpdatePanelLayout = new javax.swing.GroupLayout(bookListUpdatePanel);
        bookListUpdatePanel.setLayout(bookListUpdatePanelLayout);
        bookListUpdatePanelLayout.setHorizontalGroup(
            bookListUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookListUpdateBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bookListUpdatePanelLayout.setVerticalGroup(
            bookListUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookListUpdateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        bookListSearchPanel.setBackground(new java.awt.Color(255, 255, 255));

        bookListSearchField.setToolTipText("");

        bookListSearchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_32px_1.png"))); // NOI18N
        bookListSearchBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bookListSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookListSearchBtnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bookListSearchBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout bookListSearchPanelLayout = new javax.swing.GroupLayout(bookListSearchPanel);
        bookListSearchPanel.setLayout(bookListSearchPanelLayout);
        bookListSearchPanelLayout.setHorizontalGroup(
            bookListSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookListSearchPanelLayout.createSequentialGroup()
                .addComponent(bookListSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(bookListSearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        bookListSearchPanelLayout.setVerticalGroup(
            bookListSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookListSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(bookListSearchField)
        );

        jLabel68.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 195, 122));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("BOOK LIST");

        bookListTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bookListTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        bookListTable.setForeground(new java.awt.Color(51, 51, 51));
        bookListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        bookListTable.setRowHeight(50);
        bookListTable.setSelectionBackground(new java.awt.Color(51, 51, 51));
        bookListScrollPane.setViewportView(bookListTable);

        clearBookSearchBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearBookSearchBtn.setForeground(new java.awt.Color(255, 0, 0));
        clearBookSearchBtn.setText("Clear Search");
        clearBookSearchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearBookSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearBookSearchBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout bookListSubPanelLayout = new javax.swing.GroupLayout(bookListSubPanel);
        bookListSubPanel.setLayout(bookListSubPanelLayout);
        bookListSubPanelLayout.setHorizontalGroup(
            bookListSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookListSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bookListSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bookListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                    .addGroup(bookListSubPanelLayout.createSequentialGroup()
                        .addComponent(bookListDeletePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(bookListUpdatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bookListSubPanelLayout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearBookSearchBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bookListSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        bookListSubPanelLayout.setVerticalGroup(
            bookListSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookListSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bookListSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bookListSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(clearBookSearchBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bookListSearchPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bookListSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bookListDeletePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookListUpdatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout bookListMainPanelLayout = new javax.swing.GroupLayout(bookListMainPanel);
        bookListMainPanel.setLayout(bookListMainPanelLayout);
        bookListMainPanelLayout.setHorizontalGroup(
            bookListMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(bookListMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bookListSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bookListMainPanelLayout.setVerticalGroup(
            bookListMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
            .addGroup(bookListMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bookListSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainContainer.add(bookListMainPanel, "card11");

        addBookMainPanel.setBackground(new java.awt.Color(255, 255, 255));
        addBookMainPanel.setLayout(new java.awt.GridBagLayout());

        addBookSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        addBookSubCat.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addBookSubCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Sub Category" }));
        addBookSubCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookSubCatActionPerformed(evt);
            }
        });

        addBookCategory.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addBookCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Category" }));
        addBookCategory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                addBookCategoryItemStateChanged(evt);
            }
        });
        addBookCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookCategoryActionPerformed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel58.setText("CATEGORY");

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel59.setText("SUB-CAT");

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel60.setText("BOOK NAME");

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel54.setText("STOCK");

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel55.setText("WRITTER");

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel56.setText("PUBLISHER");

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel57.setText("EDITION");

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel61.setText("PRICE");

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel62.setText("DISCRIPTION");

        addBookEdition.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addBookEdition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookEditionActionPerformed(evt);
            }
        });

        addBookName.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addBookName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookNameActionPerformed(evt);
            }
        });

        addBookWritter.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N

        addBookStock.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N

        addBookPrice.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N

        addBookPublisher.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N

        addBookDiscription.setColumns(20);
        addBookDiscription.setRows(5);
        jScrollPane5.setViewportView(addBookDiscription);

        jPanel12.setBackground(new java.awt.Color(51, 51, 51));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("ADD BOOK");
        jLabel63.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel63MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel63MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel63MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel63MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("RESET");
        jLabel64.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel64MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel64MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel64MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout addBookSubPanelLayout = new javax.swing.GroupLayout(addBookSubPanel);
        addBookSubPanel.setLayout(addBookSubPanelLayout);
        addBookSubPanelLayout.setHorizontalGroup(
            addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addBookSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addBookSubPanelLayout.createSequentialGroup()
                        .addGap(368, 368, 368)
                        .addComponent(jLabel61)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addBookSubPanelLayout.createSequentialGroup()
                        .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addBookSubPanelLayout.createSequentialGroup()
                                .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel54)
                                    .addComponent(jLabel56))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(addBookSubPanelLayout.createSequentialGroup()
                                        .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(addBookPublisher, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                            .addComponent(addBookStock))
                                        .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(addBookSubPanelLayout.createSequentialGroup()
                                                .addGap(110, 110, 110)
                                                .addComponent(addBookPrice))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addBookSubPanelLayout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(jLabel57)
                                                .addGap(18, 18, 18)
                                                .addComponent(addBookEdition, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(addBookSubPanelLayout.createSequentialGroup()
                                .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel60)
                                    .addComponent(jLabel59))
                                .addGap(18, 18, 18)
                                .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(addBookSubPanelLayout.createSequentialGroup()
                                        .addComponent(addBookName, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel55)
                                        .addGap(18, 18, 18)
                                        .addComponent(addBookWritter))
                                    .addComponent(addBookSubCat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(addBookSubPanelLayout.createSequentialGroup()
                                .addComponent(jLabel58)
                                .addGap(25, 25, 25)
                                .addComponent(addBookCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        addBookSubPanelLayout.setVerticalGroup(
            addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addBookSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBookCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(addBookSubCat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addBookWritter)
                    .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addBookName, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel60)
                        .addComponent(jLabel55)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel56)
                        .addComponent(jLabel57)
                        .addComponent(addBookEdition, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addBookPublisher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel54)
                        .addComponent(addBookStock, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addBookPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel61)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addBookSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 5);
        addBookMainPanel.add(addBookSubPanel, gridBagConstraints);

        jLabel53.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 195, 122));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("ADD BOOK");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 558;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 0, 5);
        addBookMainPanel.add(jLabel53, gridBagConstraints);

        mainContainer.add(addBookMainPanel, "card10");

        adminUpdateResultPanel.setBackground(new java.awt.Color(255, 255, 255));
        adminUpdateResultPanel.setLayout(new java.awt.GridBagLayout());

        adminUpdateResultSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 195, 122));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Update ADMIN");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Name :");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Aadhar Number : ");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Email :");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Address : ");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Contact No. : ");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Username : ");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Password : ");

        updateAdminResultName.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateAdminResultName.setForeground(new java.awt.Color(51, 51, 51));
        updateAdminResultName.setNextFocusableComponent(addAdminEmail);

        updateAdminResultEmail.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateAdminResultEmail.setForeground(new java.awt.Color(51, 51, 51));
        updateAdminResultEmail.setNextFocusableComponent(addAdminContact);

        updateAdminResultContact.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateAdminResultContact.setForeground(new java.awt.Color(51, 51, 51));
        updateAdminResultContact.setNextFocusableComponent(addAdminAadhar);

        updateAdminResultAadhar.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateAdminResultAadhar.setForeground(new java.awt.Color(51, 51, 51));
        updateAdminResultAadhar.setNextFocusableComponent(addAdminPhoto);

        updateAdminResultUsername.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateAdminResultUsername.setForeground(new java.awt.Color(51, 51, 51));
        updateAdminResultUsername.setNextFocusableComponent(addAdminPassword);

        updateAdminResultPassword.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateAdminResultPassword.setForeground(new java.awt.Color(51, 51, 51));
        updateAdminResultPassword.setNextFocusableComponent(addAdminResetBtn);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        updateAdminResultPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateAdminResultPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/userProfile.png"))); // NOI18N
        updateAdminResultPhoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateAdminResultPhoto.setNextFocusableComponent(addAdminAddress);
        updateAdminResultPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateAdminResultPhotoMouseClicked(evt);
            }
        });
        updateAdminResultPhoto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                updateAdminResultPhotoKeyReleased(evt);
            }
        });

        updateAdminResultPhotoName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        updateAdminResultPhotoName.setForeground(new java.awt.Color(61, 61, 61));
        updateAdminResultPhotoName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateAdminResultPhotoName.setText("Upload Photo");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateAdminResultPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
            .addComponent(updateAdminResultPhotoName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(updateAdminResultPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(updateAdminResultPhotoName, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
        );

        updateAdminResultAddress.setColumns(20);
        updateAdminResultAddress.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateAdminResultAddress.setForeground(new java.awt.Color(51, 51, 51));
        updateAdminResultAddress.setRows(5);
        updateAdminResultAddress.setNextFocusableComponent(addAdminUsername);
        jScrollPane2.setViewportView(updateAdminResultAddress);

        updateAdminResultResetBtnPanel.setBackground(new java.awt.Color(51, 51, 51));
        updateAdminResultResetBtnPanel.setName("addAdminResetBtnPanel"); // NOI18N

        updateAdminResultResetBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        updateAdminResultResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateAdminResultResetBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateAdminResultResetBtn.setText("Back");
        updateAdminResultResetBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateAdminResultResetBtn.setNextFocusableComponent(addAdminRegisterBtn);
        updateAdminResultResetBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                updateAdminResultResetBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                updateAdminResultResetBtnFocusLost(evt);
            }
        });
        updateAdminResultResetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateAdminResultResetBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateAdminResultResetBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateAdminResultResetBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout updateAdminResultResetBtnPanelLayout = new javax.swing.GroupLayout(updateAdminResultResetBtnPanel);
        updateAdminResultResetBtnPanel.setLayout(updateAdminResultResetBtnPanelLayout);
        updateAdminResultResetBtnPanelLayout.setHorizontalGroup(
            updateAdminResultResetBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateAdminResultResetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        updateAdminResultResetBtnPanelLayout.setVerticalGroup(
            updateAdminResultResetBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateAdminResultResetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        updateAdminResultRegisterBtnPanel.setBackground(new java.awt.Color(51, 51, 51));

        updateAdminResultRegisterBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        updateAdminResultRegisterBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateAdminResultRegisterBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateAdminResultRegisterBtn.setText("Update Admin");
        updateAdminResultRegisterBtn.setToolTipText("");
        updateAdminResultRegisterBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateAdminResultRegisterBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                updateAdminResultRegisterBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                updateAdminResultRegisterBtnFocusLost(evt);
            }
        });
        updateAdminResultRegisterBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateAdminResultRegisterBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateAdminResultRegisterBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateAdminResultRegisterBtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                updateAdminResultRegisterBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout updateAdminResultRegisterBtnPanelLayout = new javax.swing.GroupLayout(updateAdminResultRegisterBtnPanel);
        updateAdminResultRegisterBtnPanel.setLayout(updateAdminResultRegisterBtnPanelLayout);
        updateAdminResultRegisterBtnPanelLayout.setHorizontalGroup(
            updateAdminResultRegisterBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateAdminResultRegisterBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        updateAdminResultRegisterBtnPanelLayout.setVerticalGroup(
            updateAdminResultRegisterBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateAdminResultRegisterBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout adminUpdateResultSubPanelLayout = new javax.swing.GroupLayout(adminUpdateResultSubPanel);
        adminUpdateResultSubPanel.setLayout(adminUpdateResultSubPanelLayout);
        adminUpdateResultSubPanelLayout.setHorizontalGroup(
            adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminUpdateResultSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adminUpdateResultSubPanelLayout.createSequentialGroup()
                        .addGroup(adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(updateAdminResultResetBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel23))
                        .addGap(27, 27, 27)
                        .addGroup(adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(adminUpdateResultSubPanelLayout.createSequentialGroup()
                                .addComponent(updateAdminResultUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24)
                                .addGap(51, 51, 51)
                                .addComponent(updateAdminResultPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(adminUpdateResultSubPanelLayout.createSequentialGroup()
                                .addGroup(adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(updateAdminResultAadhar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateAdminResultContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateAdminResultEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateAdminResultName, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)
                            .addComponent(updateAdminResultRegisterBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        adminUpdateResultSubPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {updateAdminResultAadhar, updateAdminResultContact, updateAdminResultEmail, updateAdminResultName});

        adminUpdateResultSubPanelLayout.setVerticalGroup(
            adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminUpdateResultSubPanelLayout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adminUpdateResultSubPanelLayout.createSequentialGroup()
                        .addGroup(adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(updateAdminResultName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(updateAdminResultEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(updateAdminResultContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(updateAdminResultAadhar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(updateAdminResultUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(updateAdminResultPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(adminUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateAdminResultResetBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateAdminResultRegisterBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        adminUpdateResultSubPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {updateAdminResultAadhar, updateAdminResultContact, updateAdminResultEmail, updateAdminResultName, updateAdminResultPassword, updateAdminResultUsername});

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 32;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        adminUpdateResultPanel.add(adminUpdateResultSubPanel, gridBagConstraints);

        mainContainer.add(adminUpdateResultPanel, "card5");

        adminUpdatePanel.setBackground(new java.awt.Color(255, 255, 255));
        adminUpdatePanel.setLayout(new java.awt.GridBagLayout());

        adminUpdateSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 195, 122));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Update Admin");

        updatePanelTransaction.setLayout(new java.awt.CardLayout());

        searchAdminPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Enter Admin ID / Aadhar Number / Name :");

        updateAdminSearchField.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateAdminSearchField.setForeground(new java.awt.Color(51, 51, 51));
        updateAdminSearchField.setNextFocusableComponent(updateAdminResetBtn);
        updateAdminSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                updateAdminSearchFieldKeyReleased(evt);
            }
        });

        updateAdminResetBtnPanel.setBackground(new java.awt.Color(51, 51, 51));

        updateAdminResetBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        updateAdminResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateAdminResetBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateAdminResetBtn.setText("Reset");
        updateAdminResetBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateAdminResetBtn.setNextFocusableComponent(updateAdminSearchBtn);
        updateAdminResetBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                updateAdminResetBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                updateAdminResetBtnFocusLost(evt);
            }
        });
        updateAdminResetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateAdminResetBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateAdminResetBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateAdminResetBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout updateAdminResetBtnPanelLayout = new javax.swing.GroupLayout(updateAdminResetBtnPanel);
        updateAdminResetBtnPanel.setLayout(updateAdminResetBtnPanelLayout);
        updateAdminResetBtnPanelLayout.setHorizontalGroup(
            updateAdminResetBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateAdminResetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
        );
        updateAdminResetBtnPanelLayout.setVerticalGroup(
            updateAdminResetBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateAdminResetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        updateAdminSearchBtnPanel.setBackground(new java.awt.Color(51, 51, 51));

        updateAdminSearchBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        updateAdminSearchBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateAdminSearchBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateAdminSearchBtn.setText("Search Admin");
        updateAdminSearchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateAdminSearchBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                updateAdminSearchBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                updateAdminSearchBtnFocusLost(evt);
            }
        });
        updateAdminSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateAdminSearchBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateAdminSearchBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateAdminSearchBtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                updateAdminSearchBtnMousePressed(evt);
            }
        });
        updateAdminSearchBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                updateAdminSearchBtnKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                updateAdminSearchBtnKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout updateAdminSearchBtnPanelLayout = new javax.swing.GroupLayout(updateAdminSearchBtnPanel);
        updateAdminSearchBtnPanel.setLayout(updateAdminSearchBtnPanelLayout);
        updateAdminSearchBtnPanelLayout.setHorizontalGroup(
            updateAdminSearchBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateAdminSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        );
        updateAdminSearchBtnPanelLayout.setVerticalGroup(
            updateAdminSearchBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateAdminSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout searchAdminPanelLayout = new javax.swing.GroupLayout(searchAdminPanel);
        searchAdminPanel.setLayout(searchAdminPanelLayout);
        searchAdminPanelLayout.setHorizontalGroup(
            searchAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchAdminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16)
                    .addGroup(searchAdminPanelLayout.createSequentialGroup()
                        .addComponent(updateAdminResetBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateAdminSearchBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(updateAdminSearchField))
                .addContainerGap())
        );
        searchAdminPanelLayout.setVerticalGroup(
            searchAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchAdminPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(updateAdminSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(searchAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateAdminResetBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateAdminSearchBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        updatePanelTransaction.add(searchAdminPanel, "card2");

        javax.swing.GroupLayout adminUpdateSubPanelLayout = new javax.swing.GroupLayout(adminUpdateSubPanel);
        adminUpdateSubPanel.setLayout(adminUpdateSubPanelLayout);
        adminUpdateSubPanelLayout.setHorizontalGroup(
            adminUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updatePanelTransaction)
            .addGroup(adminUpdateSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        adminUpdateSubPanelLayout.setVerticalGroup(
            adminUpdateSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminUpdateSubPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updatePanelTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        adminUpdatePanel.add(adminUpdateSubPanel, new java.awt.GridBagConstraints());

        mainContainer.add(adminUpdatePanel, "card4");

        adminListPanel.setBackground(new java.awt.Color(255, 255, 255));

        adminListSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        adminListActionPanel.setBackground(new java.awt.Color(255, 255, 255));

        adminListScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        adminListScrollPane.setBorder(null);

        adminListTable.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 51, 51)));
        adminListTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        adminListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        adminListTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminListTable.setGridColor(new java.awt.Color(51, 51, 51));
        adminListTable.setRowHeight(45);
        adminListTable.setSelectionBackground(new java.awt.Color(240, 240, 240));
        adminListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminListTableMouseClicked(evt);
            }
        });
        adminListScrollPane.setViewportView(adminListTable);

        adminListDeleteBtnPanel.setBackground(new java.awt.Color(51, 51, 51));

        adminListDeleteBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        adminListDeleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        adminListDeleteBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminListDeleteBtn.setText("Delete");
        adminListDeleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminListDeleteBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adminListDeleteBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                adminListDeleteBtnFocusLost(evt);
            }
        });
        adminListDeleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminListDeleteBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adminListDeleteBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                adminListDeleteBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout adminListDeleteBtnPanelLayout = new javax.swing.GroupLayout(adminListDeleteBtnPanel);
        adminListDeleteBtnPanel.setLayout(adminListDeleteBtnPanelLayout);
        adminListDeleteBtnPanelLayout.setHorizontalGroup(
            adminListDeleteBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminListDeleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        adminListDeleteBtnPanelLayout.setVerticalGroup(
            adminListDeleteBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminListDeleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        adminListViewProfileBtnPanel.setBackground(new java.awt.Color(51, 51, 51));

        adminListViewProfileBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        adminListViewProfileBtn.setForeground(new java.awt.Color(255, 255, 255));
        adminListViewProfileBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminListViewProfileBtn.setText("View Profile");
        adminListViewProfileBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminListViewProfileBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adminListViewProfileBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                adminListViewProfileBtnFocusLost(evt);
            }
        });
        adminListViewProfileBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminListViewProfileBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adminListViewProfileBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                adminListViewProfileBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout adminListViewProfileBtnPanelLayout = new javax.swing.GroupLayout(adminListViewProfileBtnPanel);
        adminListViewProfileBtnPanel.setLayout(adminListViewProfileBtnPanelLayout);
        adminListViewProfileBtnPanelLayout.setHorizontalGroup(
            adminListViewProfileBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminListViewProfileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        adminListViewProfileBtnPanelLayout.setVerticalGroup(
            adminListViewProfileBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminListViewProfileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel69.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 195, 122));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("ADMIN LIST");

        javax.swing.GroupLayout adminListActionPanelLayout = new javax.swing.GroupLayout(adminListActionPanel);
        adminListActionPanel.setLayout(adminListActionPanelLayout);
        adminListActionPanelLayout.setHorizontalGroup(
            adminListActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminListActionPanelLayout.createSequentialGroup()
                .addComponent(adminListDeleteBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(adminListViewProfileBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(adminListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        adminListActionPanelLayout.setVerticalGroup(
            adminListActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminListActionPanelLayout.createSequentialGroup()
                .addComponent(jLabel69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(adminListActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(adminListViewProfileBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(adminListDeleteBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout adminListSubPanelLayout = new javax.swing.GroupLayout(adminListSubPanel);
        adminListSubPanel.setLayout(adminListSubPanelLayout);
        adminListSubPanelLayout.setHorizontalGroup(
            adminListSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminListSubPanelLayout.createSequentialGroup()
                .addComponent(adminListActionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        adminListSubPanelLayout.setVerticalGroup(
            adminListSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminListSubPanelLayout.createSequentialGroup()
                .addComponent(adminListActionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout adminListPanelLayout = new javax.swing.GroupLayout(adminListPanel);
        adminListPanel.setLayout(adminListPanelLayout);
        adminListPanelLayout.setHorizontalGroup(
            adminListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminListPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(adminListSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        adminListPanelLayout.setVerticalGroup(
            adminListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminListPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(adminListSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainContainer.add(adminListPanel, "card6");

        addStudentPanel.setBackground(new java.awt.Color(255, 255, 255));
        addStudentPanel.setLayout(new java.awt.GridBagLayout());

        addStudentSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel32.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 195, 122));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("ADD STUDENT");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        addStudentPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addStudentPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/userProfile.png"))); // NOI18N
        addStudentPhoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addStudentPhoto.setNextFocusableComponent(addStudentAddress);
        addStudentPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addStudentPhotoMouseClicked(evt);
            }
        });
        addStudentPhoto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addStudentPhotoKeyPressed(evt);
            }
        });

        addStudentPhotoName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addStudentPhotoName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addStudentPhotoName.setText("Upload Photo");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addStudentPhotoName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
            .addComponent(addStudentPhoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(addStudentPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(addStudentPhotoName, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
        );

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("STUDENT BRANCH");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("STUDENT SEMESTER");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("STUDENT AADHAR");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setText("STUDENT NAME");

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("EMAIL");

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("STUDENT ADDRESS");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("ENROLL NUMBER");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("CONTACT");

        addStudentCourseList.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addStudentCourseList.setForeground(new java.awt.Color(51, 51, 51));
        addStudentCourseList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT COURSE" }));
        addStudentCourseList.setNextFocusableComponent(addStudentStreamList);
        addStudentCourseList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                addStudentCourseListItemStateChanged(evt);
            }
        });

        addStudentSemester.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addStudentSemester.setNextFocusableComponent(addStudentAadhar);
        addStudentSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentSemesterActionPerformed(evt);
            }
        });

        addStudentAadhar.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addStudentAadhar.setNextFocusableComponent(addStudentName);
        addStudentAadhar.setPreferredSize(new java.awt.Dimension(59, 40));

        addStudentName.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addStudentName.setNextFocusableComponent(addStudentEmail);

        addStudentEmail.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addStudentEmail.setNextFocusableComponent(addStudentPhoto);

        addStudentAddress.setColumns(20);
        addStudentAddress.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addStudentAddress.setRows(5);
        jScrollPane3.setViewportView(addStudentAddress);

        addStudentEnroll.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addStudentEnroll.setNextFocusableComponent(addStudentContact);

        addStudentContact.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        addStudentContact.setNextFocusableComponent(addStudentResetBtn);

        addStudentResetBtnPanel.setBackground(new java.awt.Color(51, 51, 51));

        addStudentResetBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addStudentResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        addStudentResetBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addStudentResetBtn.setText("RESET");
        addStudentResetBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addStudentResetBtn.setNextFocusableComponent(addStudentRegisterBtn);
        addStudentResetBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addStudentResetBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                addStudentResetBtnFocusLost(evt);
            }
        });
        addStudentResetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addStudentResetBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addStudentResetBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addStudentResetBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout addStudentResetBtnPanelLayout = new javax.swing.GroupLayout(addStudentResetBtnPanel);
        addStudentResetBtnPanel.setLayout(addStudentResetBtnPanelLayout);
        addStudentResetBtnPanelLayout.setHorizontalGroup(
            addStudentResetBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addStudentResetBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );
        addStudentResetBtnPanelLayout.setVerticalGroup(
            addStudentResetBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addStudentResetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        addStudentRegisterBtnPanel.setBackground(new java.awt.Color(51, 51, 51));

        addStudentRegisterBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addStudentRegisterBtn.setForeground(new java.awt.Color(255, 255, 255));
        addStudentRegisterBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addStudentRegisterBtn.setText("ADD STUDENT");
        addStudentRegisterBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addStudentRegisterBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addStudentRegisterBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                addStudentRegisterBtnFocusLost(evt);
            }
        });
        addStudentRegisterBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addStudentRegisterBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addStudentRegisterBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addStudentRegisterBtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addStudentRegisterBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout addStudentRegisterBtnPanelLayout = new javax.swing.GroupLayout(addStudentRegisterBtnPanel);
        addStudentRegisterBtnPanel.setLayout(addStudentRegisterBtnPanelLayout);
        addStudentRegisterBtnPanelLayout.setHorizontalGroup(
            addStudentRegisterBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addStudentRegisterBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addStudentRegisterBtnPanelLayout.setVerticalGroup(
            addStudentRegisterBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addStudentRegisterBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        addStudentStreamList.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addStudentStreamList.setForeground(new java.awt.Color(51, 51, 51));
        addStudentStreamList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT STREAM" }));
        addStudentStreamList.setNextFocusableComponent(addStudentSemester);
        addStudentStreamList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentStreamListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addStudentSubPanelLayout = new javax.swing.GroupLayout(addStudentSubPanel);
        addStudentSubPanel.setLayout(addStudentSubPanelLayout);
        addStudentSubPanelLayout.setHorizontalGroup(
            addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addStudentSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(addStudentSubPanelLayout.createSequentialGroup()
                        .addComponent(addStudentResetBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addStudentRegisterBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addStudentSubPanelLayout.createSequentialGroup()
                        .addGroup(addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addComponent(jLabel36)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41))
                        .addGap(30, 30, 30)
                        .addGroup(addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addStudentSubPanelLayout.createSequentialGroup()
                                .addComponent(addStudentEnroll, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addStudentContact, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3)
                            .addComponent(addStudentAadhar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(addStudentSubPanelLayout.createSequentialGroup()
                                .addGroup(addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(addStudentSubPanelLayout.createSequentialGroup()
                                        .addComponent(addStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                        .addComponent(addStudentEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(addStudentSubPanelLayout.createSequentialGroup()
                                        .addGroup(addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(addStudentSemester)
                                            .addComponent(addStudentStreamList, 0, 341, Short.MAX_VALUE)
                                            .addComponent(addStudentCourseList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        addStudentSubPanelLayout.setVerticalGroup(
            addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addStudentSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addGap(16, 16, 16)
                .addGroup(addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addStudentSubPanelLayout.createSequentialGroup()
                        .addGroup(addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(addStudentCourseList, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addStudentStreamList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addStudentSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))))
                .addGap(11, 11, 11)
                .addGroup(addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(addStudentAadhar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(addStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(addStudentEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addStudentSubPanelLayout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(29, 29, 29)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(addStudentEnroll, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(addStudentContact, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addStudentSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addStudentResetBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addStudentRegisterBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        addStudentSubPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addStudentCourseList, addStudentStreamList});

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 11, 0);
        addStudentPanel.add(addStudentSubPanel, gridBagConstraints);

        mainContainer.add(addStudentPanel, "card7");

        studentUpdateResultPanel.setBackground(new java.awt.Color(255, 255, 255));
        studentUpdateResultPanel.setLayout(new java.awt.GridBagLayout());

        studentUpdateResultSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 195, 122));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Update STUDENT");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        updateStudentPhoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateStudentPhoto.setNextFocusableComponent(addStudentAddress);
        updateStudentPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateStudentPhotoMouseClicked(evt);
            }
        });
        updateStudentPhoto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                updateStudentPhotoKeyPressed(evt);
            }
        });

        updateStudentPhotoName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        updateStudentPhotoName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateStudentPhotoName.setText("Upload Photo");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateStudentPhotoName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
            .addComponent(updateStudentPhoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(updateStudentPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(updateStudentPhotoName, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
        );

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("STUDENT BRANCH");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setText("STUDENT SEMESTER");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setText("STUDENT AADHAR");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("STUDENT NAME");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("EMAIL");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setText("STUDENT ADDRESS");

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText("ENROLL NUMBER");

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("CONTACT");

        updateStudentCourseList.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        updateStudentCourseList.setForeground(new java.awt.Color(51, 51, 51));
        updateStudentCourseList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT COURSE" }));
        updateStudentCourseList.setNextFocusableComponent(addStudentStreamList);
        updateStudentCourseList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                updateStudentCourseListItemStateChanged(evt);
            }
        });

        updateStudentSemester.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateStudentSemester.setNextFocusableComponent(addStudentAadhar);
        updateStudentSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStudentSemesterActionPerformed(evt);
            }
        });

        updateStudentAadhar.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateStudentAadhar.setNextFocusableComponent(addStudentName);
        updateStudentAadhar.setPreferredSize(new java.awt.Dimension(59, 40));

        updateStudentName.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateStudentName.setNextFocusableComponent(addStudentEmail);

        updateStudentEmail.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateStudentEmail.setNextFocusableComponent(addStudentPhoto);

        updateStudentAddress.setColumns(20);
        updateStudentAddress.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateStudentAddress.setRows(5);
        jScrollPane4.setViewportView(updateStudentAddress);

        updateStudentEnroll.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateStudentEnroll.setNextFocusableComponent(addStudentContact);
        updateStudentEnroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStudentEnrollActionPerformed(evt);
            }
        });

        updateStudentContact.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        updateStudentContact.setNextFocusableComponent(addStudentResetBtn);

        updateStudentResetBtnPanel.setBackground(new java.awt.Color(51, 51, 51));

        updateStudentResetBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        updateStudentResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateStudentResetBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateStudentResetBtn.setText("BACK");
        updateStudentResetBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateStudentResetBtn.setNextFocusableComponent(addStudentRegisterBtn);
        updateStudentResetBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                updateStudentResetBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                updateStudentResetBtnFocusLost(evt);
            }
        });
        updateStudentResetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateStudentResetBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateStudentResetBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateStudentResetBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout updateStudentResetBtnPanelLayout = new javax.swing.GroupLayout(updateStudentResetBtnPanel);
        updateStudentResetBtnPanel.setLayout(updateStudentResetBtnPanelLayout);
        updateStudentResetBtnPanelLayout.setHorizontalGroup(
            updateStudentResetBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateStudentResetBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );
        updateStudentResetBtnPanelLayout.setVerticalGroup(
            updateStudentResetBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateStudentResetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        updateStudentRegisterBtnPanel.setBackground(new java.awt.Color(51, 51, 51));

        updateStudentRegisterBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        updateStudentRegisterBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateStudentRegisterBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateStudentRegisterBtn.setText("UPDATE STUDENT");
        updateStudentRegisterBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateStudentRegisterBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                updateStudentRegisterBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                updateStudentRegisterBtnFocusLost(evt);
            }
        });
        updateStudentRegisterBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateStudentRegisterBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateStudentRegisterBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateStudentRegisterBtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                updateStudentRegisterBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout updateStudentRegisterBtnPanelLayout = new javax.swing.GroupLayout(updateStudentRegisterBtnPanel);
        updateStudentRegisterBtnPanel.setLayout(updateStudentRegisterBtnPanelLayout);
        updateStudentRegisterBtnPanelLayout.setHorizontalGroup(
            updateStudentRegisterBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateStudentRegisterBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        updateStudentRegisterBtnPanelLayout.setVerticalGroup(
            updateStudentRegisterBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateStudentRegisterBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        updateStudentStreamList.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        updateStudentStreamList.setForeground(new java.awt.Color(51, 51, 51));
        updateStudentStreamList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT STREAM" }));
        updateStudentStreamList.setNextFocusableComponent(addStudentSemester);

        javax.swing.GroupLayout studentUpdateResultSubPanelLayout = new javax.swing.GroupLayout(studentUpdateResultSubPanel);
        studentUpdateResultSubPanel.setLayout(studentUpdateResultSubPanelLayout);
        studentUpdateResultSubPanelLayout.setHorizontalGroup(
            studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentUpdateResultSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(studentUpdateResultSubPanelLayout.createSequentialGroup()
                        .addComponent(updateStudentResetBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateStudentRegisterBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(studentUpdateResultSubPanelLayout.createSequentialGroup()
                        .addGroup(studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44)
                            .addComponent(jLabel43))
                        .addGap(30, 30, 30)
                        .addGroup(studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(updateStudentSemester)
                            .addComponent(updateStudentStreamList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updateStudentCourseList, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(studentUpdateResultSubPanelLayout.createSequentialGroup()
                        .addGroup(studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(jLabel45)
                            .addComponent(jLabel48)
                            .addComponent(jLabel49))
                        .addGap(37, 37, 37)
                        .addGroup(studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(studentUpdateResultSubPanelLayout.createSequentialGroup()
                                .addComponent(updateStudentEnroll, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateStudentContact, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4)
                            .addComponent(updateStudentAadhar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(studentUpdateResultSubPanelLayout.createSequentialGroup()
                                .addComponent(updateStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel47)
                                .addGap(44, 44, 44)
                                .addComponent(updateStudentEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        studentUpdateResultSubPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {updateStudentCourseList, updateStudentStreamList});

        studentUpdateResultSubPanelLayout.setVerticalGroup(
            studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentUpdateResultSubPanelLayout.createSequentialGroup()
                .addComponent(jLabel33)
                .addGap(21, 21, 21)
                .addGroup(studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(studentUpdateResultSubPanelLayout.createSequentialGroup()
                        .addGroup(studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(updateStudentCourseList, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(updateStudentStreamList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateStudentSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(updateStudentAadhar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(updateStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(updateStudentEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentUpdateResultSubPanelLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(31, 31, 31)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateStudentContact, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateStudentEnroll, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(studentUpdateResultSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateStudentResetBtnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateStudentRegisterBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        studentUpdateResultSubPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {updateStudentCourseList, updateStudentStreamList});

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 17, 0);
        studentUpdateResultPanel.add(studentUpdateResultSubPanel, gridBagConstraints);

        mainContainer.add(studentUpdateResultPanel, "card8");

        studentAttendanceMainPanel.setBackground(new java.awt.Color(255, 255, 255));

        studentAttendanceSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel79.setFont(new java.awt.Font("Felix Titling", 1, 26)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(0, 195, 122));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("ATTENDANCE REQUEST");

        studenceAttendanceTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        studenceAttendanceTable.setForeground(new java.awt.Color(51, 51, 51));
        studenceAttendanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        studenceAttendanceTable.setRowHeight(50);
        studenceAttendanceTable.setSelectionBackground(new java.awt.Color(51, 51, 51));
        studentAttendanceScrollPane.setViewportView(studenceAttendanceTable);

        studenceAttendanceApprovePanel.setBackground(new java.awt.Color(55, 55, 55));

        studenceAttendanceApproveBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        studenceAttendanceApproveBtn.setForeground(new java.awt.Color(255, 255, 255));
        studenceAttendanceApproveBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        studenceAttendanceApproveBtn.setText("APPROVE");
        studenceAttendanceApproveBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studenceAttendanceApproveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studenceAttendanceApproveBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                studenceAttendanceApproveBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                studenceAttendanceApproveBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout studenceAttendanceApprovePanelLayout = new javax.swing.GroupLayout(studenceAttendanceApprovePanel);
        studenceAttendanceApprovePanel.setLayout(studenceAttendanceApprovePanelLayout);
        studenceAttendanceApprovePanelLayout.setHorizontalGroup(
            studenceAttendanceApprovePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studenceAttendanceApproveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        studenceAttendanceApprovePanelLayout.setVerticalGroup(
            studenceAttendanceApprovePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studenceAttendanceApproveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        studenceAttendanceExitPanel.setBackground(new java.awt.Color(55, 55, 55));

        StudentAttendanceExitBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        StudentAttendanceExitBtn.setForeground(new java.awt.Color(255, 255, 255));
        StudentAttendanceExitBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StudentAttendanceExitBtn.setText("EXIT");
        StudentAttendanceExitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        StudentAttendanceExitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StudentAttendanceExitBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StudentAttendanceExitBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                StudentAttendanceExitBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout studenceAttendanceExitPanelLayout = new javax.swing.GroupLayout(studenceAttendanceExitPanel);
        studenceAttendanceExitPanel.setLayout(studenceAttendanceExitPanelLayout);
        studenceAttendanceExitPanelLayout.setHorizontalGroup(
            studenceAttendanceExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(StudentAttendanceExitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        studenceAttendanceExitPanelLayout.setVerticalGroup(
            studenceAttendanceExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(StudentAttendanceExitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout studentAttendanceSubPanelLayout = new javax.swing.GroupLayout(studentAttendanceSubPanel);
        studentAttendanceSubPanel.setLayout(studentAttendanceSubPanelLayout);
        studentAttendanceSubPanelLayout.setHorizontalGroup(
            studentAttendanceSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studentAttendanceScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addGroup(studentAttendanceSubPanelLayout.createSequentialGroup()
                .addComponent(studenceAttendanceApprovePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(studenceAttendanceExitPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(studentAttendanceSubPanelLayout.createSequentialGroup()
                .addComponent(jLabel79)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        studentAttendanceSubPanelLayout.setVerticalGroup(
            studentAttendanceSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentAttendanceSubPanelLayout.createSequentialGroup()
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentAttendanceScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(studentAttendanceSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studenceAttendanceApprovePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studenceAttendanceExitPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout studentAttendanceMainPanelLayout = new javax.swing.GroupLayout(studentAttendanceMainPanel);
        studentAttendanceMainPanel.setLayout(studentAttendanceMainPanelLayout);
        studentAttendanceMainPanelLayout.setHorizontalGroup(
            studentAttendanceMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentAttendanceMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(studentAttendanceSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        studentAttendanceMainPanelLayout.setVerticalGroup(
            studentAttendanceMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentAttendanceMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(studentAttendanceSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainContainer.add(studentAttendanceMainPanel, "card16");

        studentAttendanceRecordMainPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel94.setFont(new java.awt.Font("Felix Titling", 1, 30)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(0, 153, 102));
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setText("Search Attendance Record");

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel95.setText("From ");

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel96.setText("To");

        searchAttendBtnPanel.setBackground(new java.awt.Color(51, 51, 51));
        searchAttendBtnPanel.setVerifyInputWhenFocusTarget(false);

        searchAttendanceBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        searchAttendanceBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchAttendanceBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        searchAttendanceBtn.setText("Search Record");
        searchAttendanceBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchAttendanceBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchAttendanceBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchAttendanceBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchAttendanceBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout searchAttendBtnPanelLayout = new javax.swing.GroupLayout(searchAttendBtnPanel);
        searchAttendBtnPanel.setLayout(searchAttendBtnPanelLayout);
        searchAttendBtnPanelLayout.setHorizontalGroup(
            searchAttendBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchAttendanceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        searchAttendBtnPanelLayout.setVerticalGroup(
            searchAttendBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchAttendanceBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        attendanceRecordTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        attendanceRecordTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        attendanceRecordTable.setForeground(new java.awt.Color(51, 51, 51));
        attendanceRecordTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        attendanceRecordTable.setRowHeight(40);
        attendanceRecordTable.setSelectionBackground(new java.awt.Color(51, 51, 51));
        attendanceRecordScrollPanel.setViewportView(attendanceRecordTable);

        javax.swing.GroupLayout attendanceRecordTablePanelLayout = new javax.swing.GroupLayout(attendanceRecordTablePanel);
        attendanceRecordTablePanel.setLayout(attendanceRecordTablePanelLayout);
        attendanceRecordTablePanelLayout.setHorizontalGroup(
            attendanceRecordTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(attendanceRecordScrollPanel)
        );
        attendanceRecordTablePanelLayout.setVerticalGroup(
            attendanceRecordTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, attendanceRecordTablePanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(attendanceRecordScrollPanel))
        );

        javax.swing.GroupLayout studentAttendanceRecordMainPanelLayout = new javax.swing.GroupLayout(studentAttendanceRecordMainPanel);
        studentAttendanceRecordMainPanel.setLayout(studentAttendanceRecordMainPanelLayout);
        studentAttendanceRecordMainPanelLayout.setHorizontalGroup(
            studentAttendanceRecordMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentAttendanceRecordMainPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(studentAttendanceRecordMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentAttendanceRecordMainPanelLayout.createSequentialGroup()
                        .addComponent(attendanceRecordTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(7, 7, 7))
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(studentAttendanceRecordMainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel95)
                        .addGap(249, 249, 249)
                        .addComponent(jLabel96)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchAttendBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGap(11, 11, 11))
        );
        studentAttendanceRecordMainPanelLayout.setVerticalGroup(
            studentAttendanceRecordMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentAttendanceRecordMainPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel94)
                .addGap(10, 10, 10)
                .addGroup(studentAttendanceRecordMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentAttendanceRecordMainPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(studentAttendanceRecordMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel95)
                            .addComponent(jLabel96)))
                    .addComponent(searchAttendBtnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(attendanceRecordTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        mainContainer.add(studentAttendanceRecordMainPanel, "card19");

        mainContentPanel.setLayer(mainContainer, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout mainContentPanelLayout = new javax.swing.GroupLayout(mainContentPanel);
        mainContentPanel.setLayout(mainContentPanelLayout);
        mainContentPanelLayout.setHorizontalGroup(
            mainContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );
        mainContentPanelLayout.setVerticalGroup(
            mainContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        topBar.setBackground(new java.awt.Color(255, 79, 25));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("-");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.setPreferredSize(new java.awt.Dimension(46, 39));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        adminNameMain.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        adminNameMain.setForeground(new java.awt.Color(255, 255, 255));
        adminNameMain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminNameMain.setText("Vineet Tiwari");

        dateTime.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dateTime.setForeground(new java.awt.Color(255, 255, 255));
        dateTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateTime.setText("24/04/2019 02:51:30 PM");

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                .addComponent(adminNameMain, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTime, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(adminNameMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainMenuContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(subMenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(mainContentPanel))
                    .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainMenuContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainContentPanel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(subMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)))
        );

        getAccessibleContext().setAccessibleDescription("");

        setSize(new java.awt.Dimension(1040, 565));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mainMenuContainerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMenuContainerMouseClicked

    }//GEN-LAST:event_mainMenuContainerMouseClicked

    private void subMenuHandler() {
        int mainContentWidth = mainContentPanel.getWidth();
        int mainContentHeight = mainContentPanel.getHeight();
        int mainContentXPosition = mainContentPanel.getX();
        int mainContentYPosition = mainContentPanel.getY();
        int newXPosition = mainContentXPosition;
        int redefinedWidth = mainContentWidth;
        if (!isSubMenuActive) {
            newXPosition = mainContentXPosition + 250;
            redefinedWidth = mainContentWidth - 250;
        }
        mainContentPanel.setBounds(newXPosition, mainContentYPosition, redefinedWidth, mainContentHeight);
        subMenuPanel.setVisible(true);
        mainContentPanel.repaint();
        isSubMenuActive = true;
        menuCloseBtn.setIcon(new ImageIcon(getClass().getResource("../images/icons8_Align_Left_32px_2.png")));
    }

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        int i = JOptionPane.showConfirmDialog(this, "Do You Really Want To Exit ? ", "Confirmation Message", JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void Maximize(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Maximize
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_Maximize

    private void adminMenuBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminMenuBtnMouseClicked
        subMenuHandler();
        helpMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/ques.png")));
        bookMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/book.png")));
        studentMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/stu.png")));
        adminMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/adm2.png")));
        subMenuContainer.removeAll();
        subMenuContainer.revalidate();
        subMenuContainer.add(adminSubMenuPanel);
        subMenuContainer.repaint();
        if (!isAdminSubMenuActive) {
            addAdminBtn.setForeground(new Color(255, 255, 255));
            updateAdminBtn.setForeground(new Color(255, 255, 255));
            adminListBtn.setForeground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_adminMenuBtnMouseClicked

    private void studentMenuBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentMenuBtnMouseClicked
        subMenuHandler();
        helpMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/ques.png")));
        bookMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/book.png")));
        studentMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/stu2.png")));
        adminMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/adm.png")));
        subMenuContainer.removeAll();
        subMenuContainer.revalidate();
        subMenuContainer.add(studentSubMenuPanel);
        subMenuContainer.repaint();
        if (!isStudentSubMenuActive) {
            addStudentBtn.setForeground(new Color(255, 255, 255));
            updateStudentBtn.setForeground(new Color(255, 255, 255));
            studentListBtn.setForeground(new Color(255, 255, 255));
            studentAttendenceBtn.setForeground(new Color(255, 255, 255));
            viewStudentAttendanceRecordBtn.setForeground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_studentMenuBtnMouseClicked

    private void bookMenuBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookMenuBtnMouseClicked
        subMenuHandler();
        helpMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/ques.png")));
        bookMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/book2.png")));
        studentMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/stu.png")));
        adminMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/adm.png")));
        subMenuContainer.removeAll();
        subMenuContainer.revalidate();
        subMenuContainer.add(bookSubMenuPanel);
        subMenuContainer.repaint();
        if (!isBookSubMenuActive) {
            addBookBtn.setForeground(new Color(255, 255, 255));
            bookListBtn.setForeground(new Color(255, 255, 255));
            trackIssuedBookBtn.setForeground(new Color(255, 255, 255));
            requestListForBookIssueBtn.setForeground(new Color(255, 255, 255));
            AddEBookBtn.setForeground(new Color(255, 255, 255));
            eBookListBtn.setForeground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_bookMenuBtnMouseClicked

    private void helpMenuBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpMenuBtnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_helpMenuBtnMouseEntered

    private void helpMenuBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpMenuBtnMouseClicked
        subMenuHandler();
        helpMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/ques2.png")));
        bookMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/book.png")));
        studentMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/stu.png")));
        adminMenuBtn.setIcon(new ImageIcon(getClass().getResource("../images/adm.png")));
        subMenuContainer.removeAll();
        subMenuContainer.revalidate();
        subMenuContainer.add(helpSubMenuPanel);
        subMenuContainer.repaint();
        if (!isHelpSubMenuActive) {
            tipOfTheDayBtn.setForeground(new Color(255, 255, 255));
            shortcutsBtn.setForeground(new Color(255, 255, 255));
            contactDeveloperBtn.setForeground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_helpMenuBtnMouseClicked

    private void menuCloseBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCloseBtnMouseClicked
        if (isSubMenuActive) {
            int mainContentWidth = mainContentPanel.getWidth();
            int mainContentHeight = mainContentPanel.getHeight();
            int mainContentXPosition = mainContentPanel.getX();
            int mainContentYPosition = mainContentPanel.getY();
            int newXPosition = mainContentXPosition - 250;
            int redefinedWidth = mainContentWidth + 250;
            mainContentPanel.setBounds(newXPosition, mainContentYPosition, redefinedWidth, mainContentHeight);
            subMenuPanel.setVisible(false);
            mainContentPanel.repaint();
            isSubMenuActive = false;
            menuCloseBtn.setIcon(new ImageIcon(getClass().getResource("../images/icons8_Align_Justify_32px.png")));
        }
    }//GEN-LAST:event_menuCloseBtnMouseClicked

    private void addStudentBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStudentBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = true;
        isBookSubMenuActive = false;
        isHelpSubMenuActive = false;
        addStudentBtn.setForeground(new Color(0, 155, 202));
        updateStudentBtn.setForeground(new Color(255, 255, 255));
        studentListBtn.setForeground(new Color(255, 255, 255));
        studentAttendenceBtn.setForeground(new Color(255, 255, 255));
        viewStudentAttendanceRecordBtn.setForeground(new Color(255, 255, 255));

        if (isNetworkAvailable) {
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        ClientConfig config = new DefaultClientConfig();
                        Client client = Client.create(config);
                        WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/course/allCourse");
                        ClientResponse response = service.accept("application/json").get(ClientResponse.class);
                        System.out.println(response.getStatus());
                        if (response.getStatus() == 200) {
                            JSONArray courseArray = new JSONArray(response.getEntity(String.class));
                            for (int i = 0; i < courseArray.length(); i++) {
                                String courseData = courseArray.get(i).toString();
                                JSONObject courseObject = new JSONObject(courseData);
                                addStudentCourseList.addItem(courseObject.getString("courseName"));
                            }
                            int width = addStudentPhoto.getWidth();
                            int height = addStudentPhoto.getHeight();
                            ImageIcon icon = new ImageIcon(getClass().getResource("../images/userProfile.png"));
                            addStudentPhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
                            addStudentStreamList.setEnabled(false);
                            processingFrame.setVisible(false);
                            mainContainer.removeAll();
                            mainContainer.add(addStudentPanel);
                            mainContainer.revalidate();
                            mainContainer.repaint();
                            stop();
                        } else {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Can not process request due to unavailabilty of courses.");
                            mainContainer.removeAll();
                            mainContainer.add(HomeMainPanel);
                            mainContainer.revalidate();
                            mainContainer.repaint();
                            stop();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        stop();
                    }
                }
            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_addStudentBtnMouseClicked

    private void addAdminBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addAdminBtnMouseClicked
        isAdminSubMenuActive = true;
        isStudentSubMenuActive = false;
        isBookSubMenuActive = false;
        isHelpSubMenuActive = false;
        addAdminBtn.setForeground(new Color(0, 155, 202));
        updateAdminBtn.setForeground(new Color(255, 255, 255));
        adminListBtn.setForeground(new Color(255, 255, 255));
        mainContainer.removeAll();
        mainContainer.revalidate();
        mainContainer.add(addAdminPanel);
        addAdminSubPanel.setAlignmentX(100f);
        mainContainer.repaint();

    }//GEN-LAST:event_addAdminBtnMouseClicked

    private void updateAdminBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminBtnMouseClicked
        isAdminSubMenuActive = true;
        isStudentSubMenuActive = false;
        isBookSubMenuActive = false;
        isHelpSubMenuActive = false;
        addAdminBtn.setForeground(new Color(255, 255, 255));
        updateAdminBtn.setForeground(new Color(0, 155, 202));
        adminListBtn.setForeground(new Color(255, 255, 255));

        mainContainer.removeAll();
        mainContainer.add(adminUpdatePanel);
        mainContainer.revalidate();
        mainContainer.repaint();
    }//GEN-LAST:event_updateAdminBtnMouseClicked

    private void adminListBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminListBtnMouseClicked
        isAdminSubMenuActive = true;
        isStudentSubMenuActive = false;
        isBookSubMenuActive = false;
        isHelpSubMenuActive = false;
        addAdminBtn.setForeground(new Color(255, 255, 255));
        updateAdminBtn.setForeground(new Color(255, 255, 255));
        adminListBtn.setForeground(new Color(0, 155, 202));

        getAdminList();
    }//GEN-LAST:event_adminListBtnMouseClicked

    private void getAdminList() {
        if (isNetworkAvailable) {
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        ClientConfig config = new DefaultClientConfig();
                        Client client = Client.create(config);
                        WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/admin/allAdminList");
                        ClientResponse response = service.accept("application/json").get(ClientResponse.class);
                        if (response.getStatus() == 200) {
                            String data = response.getEntity(String.class);
                            JSONArray dataArray = new JSONArray(data);
                            Vector<String> tableHeader = new Vector<String>();
                            Vector<Vector<String>> tableContent = new Vector<Vector<String>>();
                            tableHeader.add("Admin ID");
                            tableHeader.add("Admin Name");
                            tableHeader.add("Admin Email");
                            tableHeader.add("Admin Aadhar");
                            tableHeader.add("Admin Contact");
                            tableHeader.add("Admin Photo");
                            LinkedList<String> photoList = new LinkedList<String>();
                            for (int d = 0; d < dataArray.length(); d++) {
                                String adminDetailsJson = dataArray.get(d).toString();
                                JSONObject adminDetail = new JSONObject(adminDetailsJson);
                                Vector<String> adminTableData = new Vector<String>();
                                adminTableData.add(adminDetail.getString("adminId"));
                                adminTableData.add(adminDetail.getString("adminName"));
                                adminTableData.add(adminDetail.getString("adminEmail"));
                                adminTableData.add(adminDetail.getString("adminAadhar"));
                                adminTableData.add(adminDetail.getString("adminContact"));
                                adminTableData.add(adminDetail.getString("adminPhoto"));
                                photoList.add(adminDetail.getString("adminPhoto"));
                                tableContent.add(adminTableData);
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(tableContent, tableHeader);
                            adminListTable.setModel(tableModel);
                            for (int i = 0; i < photoList.size(); i++) {
                                int loaded = 0;
                                URL url = new URL("http://payalbeautyspasalon.com/eLibraryRest/images/" + photoList.get(i));
                                BufferedImage image = null;
                                while (loaded < 1) {
                                    image = ImageIO.read(url);
                                    loaded = image.getWidth();
                                }

                                ImageIcon icon = new ImageIcon(image);

                                adminListTable.getColumnModel().getColumn(5).setCellRenderer(adminListTable.getDefaultRenderer(ImageIcon.class));
                                adminListTable.setValueAt(new ImageIcon(icon.getImage().getScaledInstance(75, 100, Image.SCALE_SMOOTH)), i, 5);
                                adminListTable.setRowHeight(100);

                            }
                            adminListTable.setSelectionBackground(new Color(71, 71, 71));
                            adminListTable.setSelectionForeground(new Color(255, 255, 255));
                            adminListActionPanel.setVisible(true);
                            processingFrame.setVisible(false);
                            mainContainer.removeAll();
                            mainContainer.add(adminListPanel);
                            mainContainer.revalidate();
                            mainContainer.repaint();
                            stop();
                        } else if (response.getStatus() == 404) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "No admin data found !");
                            mainContainer.removeAll();
                            mainContainer.add(HomeMainPanel);
                            mainContainer.revalidate();
                            mainContainer.repaint();
                            stop();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }

    private void updateStudentBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateStudentBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = true;
        isBookSubMenuActive = false;
        isHelpSubMenuActive = false;
        addStudentBtn.setForeground(new Color(255, 255, 255));
        updateStudentBtn.setForeground(new Color(0, 155, 202));
        studentListBtn.setForeground(new Color(255, 255, 255));
        studentAttendenceBtn.setForeground(new Color(255, 255, 255));
        viewStudentAttendanceRecordBtn.setForeground(new Color(255, 255, 255));

        mainContainer.removeAll();
        mainContainer.revalidate();
        mainContainer.add(studentSearchMainPanel);
        mainContainer.repaint();
    }//GEN-LAST:event_updateStudentBtnMouseClicked

    private void studentListBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentListBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = true;
        isBookSubMenuActive = false;
        isHelpSubMenuActive = false;
        addStudentBtn.setForeground(new Color(255, 255, 255));
        updateStudentBtn.setForeground(new Color(255, 255, 255));
        studentListBtn.setForeground(new Color(0, 155, 202));
        studentAttendenceBtn.setForeground(new Color(255, 255, 255));
        viewStudentAttendanceRecordBtn.setForeground(new Color(255, 255, 255));
        getStudentList();
    }//GEN-LAST:event_studentListBtnMouseClicked

    private void searchSortedStudentList() {
        if (isNetworkAvailable) {
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        String criteria = studentListSearchTextField.getText();
                        ClientConfig config = new DefaultClientConfig();
                        Client client = Client.create(config);
                        WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/student/studentDetail/" + criteria);
                        ClientResponse response = service.accept("application/json").get(ClientResponse.class);
                        if (response.getStatus() == 200) {
                            clearStudentSearchBtn.setVisible(true);
                            Vector<String> header = new Vector<String>();
                            header.add("Library ID");
                            header.add("Name");
                            header.add("Email");
                            header.add("Enroll Number");
                            header.add("Aadhar");
                            header.add("Course");
                            header.add("Stream");
                            header.add("Semester");
                            header.add("Contact");
                            header.add("Photo");
                            Vector<Vector<String>> content = new Vector<Vector<String>>();
                            String studentEntity = response.getEntity(String.class);
                            JSONArray studentArray = new JSONArray(studentEntity);
                            LinkedList<String> photoList = new LinkedList<String>();
                            for (int i = 0; i < studentArray.length(); i++) {
                                JSONObject studentObject = new JSONObject(studentArray.get(i).toString());
                                Vector<String> data = new Vector<String>();
                                data.add(studentObject.getString("studentLibId"));
                                data.add(studentObject.getString("studentName"));
                                data.add(studentObject.getString("studentEmail"));
                                data.add(studentObject.getString("studentEnrollNo"));
                                data.add(studentObject.getString("studentAadhar"));
                                data.add(studentObject.getString("studentCourse"));
                                data.add(studentObject.getString("studentStream"));
                                data.add(studentObject.getString("studentSemester"));
                                data.add(studentObject.getString("studentContact"));
                                photoList.add(studentObject.getString("studentPhoto"));
                                content.add(data);
                            }

                            DefaultTableModel tableModel = new DefaultTableModel(content, header);
                            studentListTable.setModel(tableModel);
                            for (int i = 0; i < photoList.size(); i++) {
                                int loaded = 0;
                                URL url = new URL("http://payalbeautyspasalon.com/eLibraryRest/images/student/" + photoList.get(i));
                                BufferedImage image = null;
                                while (loaded < 1) {
                                    image = ImageIO.read(url);
                                    loaded = image.getWidth();
                                }

                                ImageIcon icon = new ImageIcon(image);
                                studentListTable.getColumnModel().getColumn(9).setCellRenderer(studentListTable.getDefaultRenderer(ImageIcon.class));
                                studentListTable.setValueAt(new ImageIcon(icon.getImage().getScaledInstance(65, 90, Image.SCALE_SMOOTH)), i, 9);
                                adminListTable.setRowHeight(90);
                            }

                            processingFrame.setVisible(false);
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(studentListMainPanel);
                            mainContainer.repaint();
                            stop();
                        } else {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "No student found for search term !");
                            stop();
                        }
                    } catch (Exception uhe) {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Something went wrong  !!");
                        uhe.printStackTrace();
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }

    private void getStudentList() {
        if (isNetworkAvailable) {
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        ClientConfig config = new DefaultClientConfig();
                        Client client = Client.create(config);
                        WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/student/allStudent");
                        ClientResponse response = service.accept("application/json").get(ClientResponse.class);
                        if (response.getStatus() == 200) {
                            clearStudentSearchBtn.setVisible(false);
                            Vector<String> header = new Vector<String>();
                            header.add("Library ID");
                            header.add("Name");
                            header.add("Email");
                            header.add("Enroll Number");
                            header.add("Aadhar");
                            header.add("Course");
                            header.add("Stream");
                            header.add("Semester");
                            header.add("Contact");
                            header.add("Photo");
                            Vector<Vector<String>> content = new Vector<Vector<String>>();
                            String studentEntity = response.getEntity(String.class);
                            JSONArray studentArray = new JSONArray(studentEntity);
                            LinkedList<String> photoList = new LinkedList<String>();
                            for (int i = 0; i < studentArray.length(); i++) {
                                JSONObject studentObject = new JSONObject(studentArray.get(i).toString());
                                Vector<String> data = new Vector<String>();
                                data.add(studentObject.getString("studentLibId"));
                                data.add(studentObject.getString("studentName"));
                                data.add(studentObject.getString("studentEmail"));
                                data.add(studentObject.getString("studentEnrollNo"));
                                data.add(studentObject.getString("studentAadhar"));
                                data.add(studentObject.getString("studentCourse"));
                                data.add(studentObject.getString("studentStream"));
                                data.add(studentObject.getString("studentSemester"));
                                data.add(studentObject.getString("studentContact"));
                                photoList.add(studentObject.getString("studentPhoto"));
                                content.add(data);
                            }

                            DefaultTableModel tableModel = new DefaultTableModel(content, header);
                            studentListTable.setModel(tableModel);
                            for (int i = 0; i < photoList.size(); i++) {
                                int loaded = 0;
                                URL url = new URL("http://payalbeautyspasalon.com/eLibraryRest/images/student/" + photoList.get(i));
                                BufferedImage image = null;
                                while (loaded < 1) {
                                    image = ImageIO.read(url);
                                    loaded = image.getWidth();
                                }

                                ImageIcon icon = new ImageIcon(image);
                                studentListTable.getColumnModel().getColumn(9).setCellRenderer(studentListTable.getDefaultRenderer(ImageIcon.class));
                                studentListTable.setValueAt(new ImageIcon(icon.getImage().getScaledInstance(65, 70, Image.SCALE_SMOOTH)), i, 9);
                                adminListTable.setRowHeight(90);
                            }

                            processingFrame.setVisible(false);
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(studentListMainPanel);
                            mainContainer.repaint();
                            stop();
                        } else {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "No student found");
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(HomeMainPanel);
                            mainContainer.repaint();
                            stop();
                        }
                    } catch (Exception uhe) {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Something went wrong  !!");
                        uhe.printStackTrace();
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }
    private void studentAttendenceBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentAttendenceBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = true;
        isBookSubMenuActive = false;
        isHelpSubMenuActive = false;
        addStudentBtn.setForeground(new Color(255, 255, 255));
        updateStudentBtn.setForeground(new Color(255, 255, 255));
        studentListBtn.setForeground(new Color(255, 255, 255));
        studentAttendenceBtn.setForeground(new Color(0, 155, 202));
        viewStudentAttendanceRecordBtn.setForeground(new Color(255, 255, 255));

        loadAttendanceRequest();
    }//GEN-LAST:event_studentAttendenceBtnMouseClicked

    private void loadAttendanceRequest() {
        if (isNetworkAvailable) {
            processingFrameOpen();
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        ClientConfig config = new DefaultClientConfig();
                        Client client = Client.create(config);
                        WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/attendance/getAllRequest");
                        ClientResponse response = service.accept("application/json").get(ClientResponse.class);
                        if (response.getStatus() == 200) {
                            Vector<String> header = new Vector<String>();
                            header.add("Attendance ID");
                            header.add("Student Library ID");
                            header.add("Attendance Request Status");
                            Vector<Vector<String>> content = new Vector<Vector<String>>();
                            JSONArray jsonArray = new JSONArray(String.valueOf(response.getEntity(String.class)));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                Vector<String> data = new Vector<String>();
                                data.add(jsonObject.getString("attendId"));
                                data.add(jsonObject.getString("studentLibId"));
                                if (jsonObject.getString("attendStatus").equals("0")) {
                                    data.add("Not approved !");
                                } else if (jsonObject.getString("attendStatus").equals("1")) {
                                    data.add("Approved and in library");
                                }
                                content.add(data);
                            }

                            DefaultTableModel tableModel = new DefaultTableModel(content, header);
                            studenceAttendanceTable.setModel(tableModel);
                            processingFrame.setVisible(false);
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(studentAttendanceMainPanel);
                            mainContainer.repaint();
                            stop();
                        } else if (response.getStatus() == 201) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "No new request found");
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(HomeMainPanel);
                            mainContainer.repaint();
                            stop();
                        } else {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wrong on server side !");
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(HomeMainPanel);
                            mainContainer.repaint();
                            stop();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Something went wrong");
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }

    private void addBookBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBookBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = false;
        isBookSubMenuActive = true;
        isHelpSubMenuActive = false;
        addBookBtn.setForeground(new Color(0, 155, 202));
        bookListBtn.setForeground(new Color(255, 255, 255));
        trackIssuedBookBtn.setForeground(new Color(255, 255, 255));
        requestListForBookIssueBtn.setForeground(new Color(255, 255, 255));
        AddEBookBtn.setForeground(new Color(255, 255, 255));
        eBookListBtn.setForeground(new Color(255, 255, 255));
        if (isNetworkAvailable) {
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        ClientConfig config = new DefaultClientConfig();
                        Client client = Client.create(config);
                        WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/course/allCourse");
                        ClientResponse response = service.accept("application/json").get(ClientResponse.class);
                        if (response.getStatus() == 200) {
                            addBookCategory.removeAllItems();
                            addBookCategory.addItem("SELECT CATEGORY");
                            JSONArray jsonArray = new JSONArray(String.valueOf(response.getEntity(String.class)));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                addBookCategory.addItem(jsonObject.getString("courseName"));
                            }

                            processingFrame.setVisible(false);
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(addBookMainPanel);
                            mainContainer.repaint();
                            stop();
                        } else {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                            stop();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_addBookBtnMouseClicked

    private void bookListBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookListBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = false;
        isBookSubMenuActive = true;
        isHelpSubMenuActive = false;
        addBookBtn.setForeground(new Color(255, 255, 255));
        bookListBtn.setForeground(new Color(255, 255, 255));
        trackIssuedBookBtn.setForeground(new Color(255, 255, 255));
        bookListBtn.setForeground(new Color(0, 155, 202));

        requestListForBookIssueBtn.setForeground(new Color(255, 255, 255));
        AddEBookBtn.setForeground(new Color(255, 255, 255));
        eBookListBtn.setForeground(new Color(255, 255, 255));
        loadAllBookList();
    }//GEN-LAST:event_bookListBtnMouseClicked

    private void loadAllBookList() {
        if (isNetworkAvailable) {
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        ClientConfig config = new DefaultClientConfig();
                        Client client = Client.create(config);
                        WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/book/allBook");
                        ClientResponse response = service.accept("application/json").get(ClientResponse.class);
                        if (response.getStatus() == 200) {
                            clearBookSearchBtn.setVisible(false);
                            Vector<String> header = new Vector<String>();
                            Vector<Vector<String>> content = new Vector<Vector<String>>();
                            header.add("Id");
                            header.add("Category");
                            header.add("Sub Category");
                            header.add("Name");
                            header.add("Writer");
                            header.add("Publisher");
                            header.add("Edition");
                            header.add("Stock");
                            header.add("Price");
                            header.add("Description");
                            JSONArray jsonArray = new JSONArray(String.valueOf(response.getEntity(String.class)));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Vector<String> rowData = new Vector<String>();
                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                rowData.add(jsonObject.getString("bookId"));
                                rowData.add(jsonObject.getString("bookCategory"));
                                rowData.add(jsonObject.getString("bookSubCategory"));
                                rowData.add(jsonObject.getString("bookName"));
                                rowData.add(jsonObject.getString("bookWriter"));
                                rowData.add(jsonObject.getString("bookPublisher"));
                                rowData.add(jsonObject.getString("bookEdition"));
                                rowData.add(jsonObject.getString("bookStock"));
                                rowData.add(jsonObject.getString("bookPrice"));
                                rowData.add(jsonObject.getString("bookDescription"));
                                content.add(rowData);
                            }

                            DefaultTableModel tableModel = new DefaultTableModel(content, header);
                            bookListTable.setModel(tableModel);

                            processingFrame.setVisible(false);
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(bookListMainPanel);
                            mainContainer.repaint();
                            stop();
                        } else if (response.getStatus() == 201) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Sorry no book detail found !");
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(HomeMainPanel);
                            mainContainer.repaint();
                            stop();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }

    private void trackIssuedBookBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trackIssuedBookBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = false;
        isBookSubMenuActive = true;
        isHelpSubMenuActive = false;
        addBookBtn.setForeground(new Color(255, 255, 255));
        bookListBtn.setForeground(new Color(255, 255, 255));
        trackIssuedBookBtn.setForeground(new Color(0, 155, 202));
        requestListForBookIssueBtn.setForeground(new Color(255, 255, 255));
        AddEBookBtn.setForeground(new Color(255, 255, 255));
        eBookListBtn.setForeground(new Color(255, 255, 255));

        loadIssuedBookList();
    }//GEN-LAST:event_trackIssuedBookBtnMouseClicked

    private void loadIssuedBookList() {
        if (isNetworkAvailable) {
            processingFrameOpen();
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        ClientConfig config = new DefaultClientConfig();
                        Client client = Client.create(config);
                        WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/book/getAllIssuedBook");
                        ClientResponse response = service.accept("application/json").get(ClientResponse.class);
                        if (response.getStatus() == 200) {
                            clearIssuedSearchBtn.setVisible(false);
                            Vector<String> header = new Vector<String>();
                            header.add("Book Id");
                            header.add("Issue Id");
                            header.add("Student Library Id");
                            header.add("Issued Date");
                            header.add("Return Date");
                            Vector<Vector<String>> content = new Vector<Vector<String>>();
                            JSONArray jsonArray = new JSONArray(String.valueOf(response.getEntity(String.class)));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Vector<String> bookItem = new Vector<String>();
                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                bookItem.add(jsonObject.getString("bookId"));
                                bookItem.add(jsonObject.getString("bookIssueId"));
                                bookItem.add(jsonObject.getString("studentLibId"));
                                bookItem.add(jsonObject.getString("bookIssueDate"));
                                bookItem.add(jsonObject.getString("bookReturnDate"));
                                content.add(bookItem);
                            }
                            processingFrame.setVisible(false);
                            DefaultTableModel tableModel = new DefaultTableModel(content, header);
                            trackIssuedBookTable.setModel(tableModel);
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(trackIssuedBookMainPanel);
                            mainContainer.repaint();
                            stop();
                        } else if (response.getStatus() == 202) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "There is no issued book.");
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(HomeMainPanel);
                            mainContainer.repaint();
                            stop();
                        } else {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                            stop();
                        }
                    } catch (Exception e) {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                        e.printStackTrace();
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }

    private void requestListForBookIssueBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestListForBookIssueBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = false;
        isBookSubMenuActive = true;
        isHelpSubMenuActive = false;
        addBookBtn.setForeground(new Color(255, 255, 255));
        bookListBtn.setForeground(new Color(255, 255, 255));
        trackIssuedBookBtn.setForeground(new Color(255, 255, 255));
        requestListForBookIssueBtn.setForeground(new Color(0, 155, 202));
        AddEBookBtn.setForeground(new Color(255, 255, 255));
        eBookListBtn.setForeground(new Color(255, 255, 255));

        loadIssueBookRequestList();
    }//GEN-LAST:event_requestListForBookIssueBtnMouseClicked

    private void loadIssueBookRequestList() {
        if (isNetworkAvailable) {
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        ClientConfig config = new DefaultClientConfig();
                        Client client = Client.create(config);
                        WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/book/requestedIssueList");
                        ClientResponse response = service.accept("application/json").get(ClientResponse.class);
                        System.out.println(response.getStatus());
                        if (response.getStatus() == 200) {
                            reqBookIssueSelectedRow = 0;
                            Vector<String> header = new Vector<String>();
                            header.add("Book ID");
                            header.add("Book Issue ID");
                            header.add("Student Library ID");
                            header.add("Book Name");
                            header.add("Book Writer");
                            header.add("Apply Date");
                            Vector<Vector<String>> content = new Vector<Vector<String>>();
                            JSONArray jsonArray = new JSONArray(String.valueOf(response.getEntity(String.class)));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Vector<String> data = new Vector<String>();
                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                data.add(jsonObject.getString("bookId"));
                                data.add(jsonObject.getString("issueId"));
                                data.add(jsonObject.getString("studentLibId"));
                                data.add(jsonObject.getString("bookName"));
                                data.add(jsonObject.getString("bookWriter"));
                                data.add(jsonObject.getString("bookApplyDate"));
                                content.add(data);
                            }

                            DefaultTableModel tableModel = new DefaultTableModel(content, header);
                            bookRequestTable.setModel(tableModel);
                            processingFrame.setVisible(false);
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(requestBookIssueMainPanel);
                            mainContainer.repaint();
                            stop();
                        } else if (response.getStatus() == 201) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "No new request found for book issue.");
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(HomeMainPanel);
                            mainContainer.repaint();
                            stop();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }

    private void AddEBookBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddEBookBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = false;
        isBookSubMenuActive = true;
        isHelpSubMenuActive = false;
        addBookBtn.setForeground(new Color(255, 255, 255));
        bookListBtn.setForeground(new Color(255, 255, 255));
        trackIssuedBookBtn.setForeground(new Color(255, 255, 255));
        requestListForBookIssueBtn.setForeground(new Color(255, 255, 255));
        AddEBookBtn.setForeground(new Color(0, 155, 202));
        eBookListBtn.setForeground(new Color(255, 255, 255));
        if (isNetworkAvailable) {
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        ClientConfig config = new DefaultClientConfig();
                        Client client = Client.create(config);
                        WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/course/allCourse");
                        ClientResponse response = service.accept("application/json").get(ClientResponse.class);
                        if (response.getStatus() == 200) {
                            addEBookCat.removeAllItems();
                            addEBookCat.addItem("SELECT CATEGORY");
                            JSONArray jsonArray = new JSONArray(String.valueOf(response.getEntity(String.class)));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                addEBookCat.addItem(jsonObject.getString("courseName"));
                            }

                            processingFrame.setVisible(false);
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(eBookMainPanel);
                            mainContainer.repaint();
                            stop();
                        } else {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                            stop();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_AddEBookBtnMouseClicked

    private void eBookListBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eBookListBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = false;
        isBookSubMenuActive = true;
        isHelpSubMenuActive = false;
        addBookBtn.setForeground(new Color(255, 255, 255));
        bookListBtn.setForeground(new Color(255, 255, 255));
        trackIssuedBookBtn.setForeground(new Color(255, 255, 255));
        requestListForBookIssueBtn.setForeground(new Color(255, 255, 255));
        AddEBookBtn.setForeground(new Color(255, 255, 255));
        eBookListBtn.setForeground(new Color(0, 155, 202));

        loadEBookList();
    }//GEN-LAST:event_eBookListBtnMouseClicked

    private void loadEBookList() {
        processingFrameOpen();
        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    ClientConfig config = new DefaultClientConfig();
                    Client client = Client.create(config);
                    WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/eBook/allBook");
                    ClientResponse response = service.accept("application/json").get(ClientResponse.class);
                    if (response.getStatus() == 200) {
                        JSONArray jsonArray = new JSONArray(String.valueOf(response.getEntity(String.class)));
                        if (jsonArray.length() > 0 && jsonArray.get(0) != null) {
                            Vector<String> header = new Vector<String>();
                            header.add("E-Book ID");
                            header.add("E-Book Category");
                            header.add("E-Book Sub Category");
                            header.add("E-Book Name");
                            header.add("E-Book File Name");
                            Vector<Vector<String>> content = new Vector<Vector<String>>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject ob = new JSONObject(jsonArray.get(i).toString());
                                Vector<String> data = new Vector<String>();
                                data.add(ob.getString("ebookId"));
                                data.add(ob.getString("ebookCategory"));
                                data.add(ob.getString("ebookSubCategory"));
                                data.add(ob.getString("ebookName"));
                                data.add(ob.getString("ebookLocation"));
                                content.add(data);
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(content, header);
                            eBookListTable.setModel(tableModel);
                            processingFrame.setVisible(false);
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(eBookListMainPanel);
                            mainContainer.repaint();
                            stop();
                        } else {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "No data found !");
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(HomeMainPanel);
                            mainContainer.repaint();
                            stop();
                        }
                    } else {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Something went wrong on server side !");
                        mainContainer.removeAll();
                        mainContainer.revalidate();
                        mainContainer.add(HomeMainPanel);
                        mainContainer.repaint();
                        stop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    processingFrame.setVisible(false);
                    JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                    mainContainer.removeAll();
                    mainContainer.revalidate();
                    mainContainer.add(HomeMainPanel);
                    mainContainer.repaint();
                    stop();
                }
            }

        };
        thread.run();
    }

    private void tipOfTheDayBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipOfTheDayBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = false;
        isBookSubMenuActive = false;
        isHelpSubMenuActive = true;
        tipOfTheDayBtn.setForeground(new Color(0, 155, 202));
        shortcutsBtn.setForeground(new Color(255, 255, 255));
        contactDeveloperBtn.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_tipOfTheDayBtnMouseClicked

    private void shortcutsBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shortcutsBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = false;
        isBookSubMenuActive = false;
        isHelpSubMenuActive = true;
        tipOfTheDayBtn.setForeground(new Color(255, 255, 255));
        shortcutsBtn.setForeground(new Color(0, 155, 202));
        contactDeveloperBtn.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_shortcutsBtnMouseClicked

    private void contactDeveloperBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactDeveloperBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = false;
        isBookSubMenuActive = false;
        isHelpSubMenuActive = true;
        tipOfTheDayBtn.setForeground(new Color(255, 255, 255));
        shortcutsBtn.setForeground(new Color(255, 255, 255));
        contactDeveloperBtn.setForeground(new Color(0, 155, 202));
    }//GEN-LAST:event_contactDeveloperBtnMouseClicked

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased

    }//GEN-LAST:event_formKeyReleased

    private void addAdminResetBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addAdminResetBtnFocusGained
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_addAdminResetBtnFocusGained

    private void addAdminResetBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addAdminResetBtnFocusLost
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_addAdminResetBtnFocusLost

    private void addAdminResetBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addAdminResetBtnMouseEntered
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_addAdminResetBtnMouseEntered

    private void addAdminResetBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addAdminResetBtnMouseExited
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_addAdminResetBtnMouseExited

    private void addAdminRegisterBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addAdminRegisterBtnFocusGained
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_addAdminRegisterBtnFocusGained

    private void addAdminRegisterBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addAdminRegisterBtnFocusLost
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_addAdminRegisterBtnFocusLost

    private void addAdminRegisterBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addAdminRegisterBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_addAdminRegisterBtnMouseEntered

    private void addAdminRegisterBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addAdminRegisterBtnMouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_addAdminRegisterBtnMouseExited

    private void addAdminPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addAdminPhotoMouseClicked
        uploadPhoto(0);
    }//GEN-LAST:event_addAdminPhotoMouseClicked

    private void addAdminPhotoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addAdminPhotoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            uploadPhoto(0);
        }
    }//GEN-LAST:event_addAdminPhotoKeyReleased

    private void updateAdminResetBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_updateAdminResetBtnFocusGained
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminResetBtnFocusGained

    private void updateAdminResetBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_updateAdminResetBtnFocusLost
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminResetBtnFocusLost

    private void updateAdminResetBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminResetBtnMouseEntered
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminResetBtnMouseEntered

    private void updateAdminResetBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminResetBtnMouseExited
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminResetBtnMouseExited

    private void updateAdminSearchBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_updateAdminSearchBtnFocusGained
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminSearchBtnFocusGained

    private void updateAdminSearchBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_updateAdminSearchBtnFocusLost
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminSearchBtnFocusLost

    private void updateAdminSearchBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminSearchBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminSearchBtnMouseEntered

    private void updateAdminSearchBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminSearchBtnMouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminSearchBtnMouseExited

    private void updateAdminResultPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminResultPhotoMouseClicked
        uploadPhoto(1);
    }//GEN-LAST:event_updateAdminResultPhotoMouseClicked

    private void updateAdminResultPhotoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateAdminResultPhotoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            uploadPhoto(1);
        }
    }//GEN-LAST:event_updateAdminResultPhotoKeyReleased

    private void updateAdminResultRegisterBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_updateAdminResultRegisterBtnFocusGained
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminResultRegisterBtnFocusGained

    private void updateAdminResultRegisterBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_updateAdminResultRegisterBtnFocusLost
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminResultRegisterBtnFocusLost

    private void updateAdminResultRegisterBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminResultRegisterBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminResultRegisterBtnMouseEntered

    private void updateAdminResultRegisterBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminResultRegisterBtnMouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminResultRegisterBtnMouseExited

    private void updateAdminSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminSearchBtnMouseClicked
        searchForAdminDetail();
    }//GEN-LAST:event_updateAdminSearchBtnMouseClicked

    private void updateAdminSearchBtnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateAdminSearchBtnKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            searchForAdminDetail();
        }
    }//GEN-LAST:event_updateAdminSearchBtnKeyReleased

    private void updateAdminSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateAdminSearchFieldKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            searchForAdminDetail();
        }
    }//GEN-LAST:event_updateAdminSearchFieldKeyReleased
    public int addAdminValidation() {
        int val = 0;
        if (!addAdminName.getText().isEmpty() && !addAdminEmail.getText().isEmpty()
                && !addAdminAddress.getText().isEmpty() && !addAdminContact.getText().isEmpty()
                && !addAdminUsername.getText().isEmpty() && !addAdminPassword.getText().isEmpty()) {
            if (addAdminContact.getText().length() == 10) {
                if (addAdminAadhar.getText().length() == 12) {
                    if (uploadedFilePath != null) {
                        val = 5;
                    } else {
                        val = 4;
                    }
                } else {
                    val = 3;
                }
            } else {
                val = 2;
            }
        } else {
            val = 1;
        }
        return val;
    }
    private void addAdminRegisterBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addAdminRegisterBtnMouseClicked
        if (isNetworkAvailable) {
            int val = addAdminValidation();
            switch (val) {
                case 5:
                    String adminName = addAdminName.getText();
                    String adminEmail = addAdminEmail.getText();
                    String adminContact = addAdminContact.getText();
                    String adminAddress = addAdminAddress.getText();
                    String userName = addAdminUsername.getText();
                    String userPass = addAdminPassword.getText();
                    String adminAadhar = addAdminAadhar.getText();

                    Thread thread = new Thread() {

                        @Override
                        public void run() {
                            try {
                                File profilePhoto = new File(uploadedFilePath);
                                FileInputStream fis = new FileInputStream(profilePhoto);
                                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());

                                HttpPost httppost = new HttpPost("http://payalbeautyspasalon.com/eLibraryRest/rest/admin/addAdmin");

                                MultipartEntity entity = new MultipartEntity();

                                entity.addPart("file", new InputStreamBody(fis, profilePhoto.getName()));
                                entity.addPart("adminName", new StringBody(adminName));
                                entity.addPart("adminEmail", new StringBody(adminEmail));
                                entity.addPart("adminContact", new StringBody(adminContact));
                                entity.addPart("adminAddress", new StringBody(adminAddress));
                                entity.addPart("adminAadhar", new StringBody(adminAadhar));
                                entity.addPart("username", new StringBody(userName));
                                entity.addPart("password", new StringBody(userPass));
                                httppost.setEntity(entity);

                                HttpResponse response = httpclient.execute(httppost);

                                int statusCode = response.getStatusLine().getStatusCode();

                                if (statusCode == 200) {
                                    processingFrame.setVisible(false);
                                    JOptionPane.showMessageDialog(home.this, "Successfully Registered !");
                                    addAdminName.setText("");
                                    addAdminEmail.setText("");
                                    addAdminContact.setText("");
                                    addAdminAadhar.setText("");
                                    addAdminAddress.setText("");
                                    addAdminUsername.setText("");
                                    addAdminPassword.setText("");
                                    addAdminPhotoName.setText("");
                                    addAdminPhoto.setIcon(new ImageIcon(getClass().getResource("../images/userProfile.png")));
                                    stop();
                                } else {
                                    JOptionPane.showMessageDialog(home.this, "Something went wrong !!");
                                    processingFrame.setVisible(false);
                                    stop();
                                }
                            } catch (NoClassDefFoundError e) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Oops ! It seems your are offline. \n Please check your internet connectivity !");
                                e.printStackTrace();
                                stop();
                            } catch (Exception e) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Oops ! Something went wrong !");
                                e.printStackTrace();
                                stop();
                            }
                        }
                    };

                    thread.start();
                    break;
                case 1:
                    processingFrame.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Please fill all the fields !");
                    break;
                case 2:
                    processingFrame.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Contact number must be 10 digits !");
                    break;
                case 3:
                    processingFrame.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Aadhar number must be 12 digits !");
                    break;
                case 4:
                    processingFrame.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Please uplode your photograph !");
                    break;
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();

        }
    }//GEN-LAST:event_addAdminRegisterBtnMouseClicked

    private void addAdminAadharFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addAdminAadharFocusLost
        String adminName = addAdminName.getText();
        String adminAadhar = addAdminAadhar.getText();
        String userName = "";
        String passWord = "";
        Random random = new Random();
        int passPhrase = random.nextInt(9999);
        if (passPhrase < 1111) {
            passPhrase = passPhrase + 1000;
        }
        if (adminName.contains(" ")) {
            userName = ("MUIT-" + adminName.substring(0, adminName.indexOf(" ")) + adminAadhar.substring(0, 4)).toUpperCase();
            passWord = (adminName.substring(0, adminName.indexOf(" "))) + "@" + passPhrase;
        } else {
            userName = ("MUIT-" + adminName + (adminAadhar.substring(0, 4))).toUpperCase();
            passWord = adminName + "@" + passPhrase;
        }
        addAdminUsername.setText(userName);
        addAdminPassword.setText(passWord);

    }//GEN-LAST:event_addAdminAadharFocusLost

    private void addAdminRegisterBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addAdminRegisterBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_addAdminRegisterBtnMousePressed

    private void processingFrameOpen() {
        processingFrame.pack();
        processingFrame.setLocationRelativeTo(null);
        processingFrame.setVisible(true);
    }

    private void issueBookFinalPanelOpen() {
        Date date = new Date();
        issueReturnDatePicker.setMinSelectableDate(date);
        issueBookDialogPanel.pack();
        issueBookDialogPanel.setLocationRelativeTo(null);
        issueBookDialogPanel.setVisible(true);
    }

    private void updateAdminSearchBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminSearchBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_updateAdminSearchBtnMousePressed

    private void updateAdminSearchBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateAdminSearchBtnKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            processingFrameOpen();
        }
    }//GEN-LAST:event_updateAdminSearchBtnKeyPressed

    private void updateAdminResultRegisterBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminResultRegisterBtnMouseClicked
        if (uploadedFilePath != null) {
            if (uploadedFilePath.endsWith(uploadedFileNameFromServer)) {
                updateAdminProfileWithoutPhoto();
            } else {
                updateAdminProfileWithPhoto();
            }
        } else {
            updateAdminProfileWithoutPhoto();
        }
    }//GEN-LAST:event_updateAdminResultRegisterBtnMouseClicked

    private void updateAdminResultRegisterBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminResultRegisterBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_updateAdminResultRegisterBtnMousePressed

    private void adminListBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminListBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_adminListBtnMousePressed

    private void adminListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminListTableMouseClicked
        adminListActionPanel.setVisible(true);
    }//GEN-LAST:event_adminListTableMouseClicked

    private void adminListDeleteBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adminListDeleteBtnFocusGained
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_adminListDeleteBtnFocusGained

    private void adminListDeleteBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adminListDeleteBtnFocusLost
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_adminListDeleteBtnFocusLost

    private void adminListDeleteBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminListDeleteBtnMouseEntered
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_adminListDeleteBtnMouseEntered

    private void adminListDeleteBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminListDeleteBtnMouseExited
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_adminListDeleteBtnMouseExited

    private void adminListViewProfileBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adminListViewProfileBtnFocusGained
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_adminListViewProfileBtnFocusGained

    private void adminListViewProfileBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adminListViewProfileBtnFocusLost
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_adminListViewProfileBtnFocusLost

    private void adminListViewProfileBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminListViewProfileBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_adminListViewProfileBtnMouseEntered

    private void adminListViewProfileBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminListViewProfileBtnMouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_adminListViewProfileBtnMouseExited

    private void adminListDeleteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminListDeleteBtnMouseClicked
        if (isNetworkAvailable) {
            int wantToDelete = JOptionPane.showConfirmDialog(this, "Do you want to delete ?", "Warning !", JOptionPane.YES_NO_OPTION);
            if (wantToDelete == 0) {
                processingFrameOpen();
                int selectedRow = adminListTable.getSelectedRow();
                String adminAadhar = (String) adminListTable.getValueAt(selectedRow, 3);
                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            ClientConfig config = new DefaultClientConfig();
                            Client client = Client.create(config);
                            WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/admin/deleteAdmin");
                            ClientResponse response = service.path("/" + adminAadhar).accept("application/json").get(ClientResponse.class);
                            if (response.getStatus() == 200) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Admin was successfully deleted.");
                                getAdminList();
                                stop();
                            } else if (response.getStatus() == 201) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Some thing went wrong while deleting admin profile !!");
                                stop();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            stop();
                        }
                    }

                };
                thread.start();
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_adminListDeleteBtnMouseClicked

    private void adminListViewProfileBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminListViewProfileBtnMouseClicked
        if (isNetworkAvailable) {
            int selectedRow = adminListTable.getSelectedRow();
            String searchTerm = (String) adminListTable.getValueAt(selectedRow, 3);
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        ClientConfig config = new DefaultClientConfig();
                        Client client = Client.create(config);
                        WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/admin/searchAdmin");
                        ClientResponse response = service.path("/" + searchTerm).accept("application/json").get(ClientResponse.class);
                        if (response.getStatus() == 200) {
                            String data = response.getEntity(String.class);
                            JSONObject admin = new JSONObject(data);

                            adminProfileName.setText(admin.getString("adminName"));
                            adminProfileEmail.setText(admin.getString("adminEmail"));
                            adminProfileAadhar.setText(admin.getString("adminAadhar"));
                            adminProfileContact.setText(admin.getString("adminContact"));
                            adminProfileAddress.setText(admin.getString("adminAddress"));
                            adminProfileUsername.setText(admin.getString("adminUsername"));
                            adminProfilePassword.setText(admin.getString("adminPassword"));
                            String photoName = admin.getString("adminPhoto");
                            try {
                                int loaded = 0;

                                URL url = new URL("http://payalbeautyspasalon.com/eLibraryRest/images/" + photoName);
                                BufferedImage image = null;
                                while (loaded < 1) {
                                    image = ImageIO.read(url);
                                    loaded = image.getWidth();
                                }

                                ImageIcon icon = new ImageIcon(image);
                                adminProfilePhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(132, 142, Image.SCALE_SMOOTH)));
                            } catch (ClientHandlerException e) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Oops ! It seems you are offline. \n Please check your internet connection !");
                                e.printStackTrace();
                                stop();
                            } catch (Exception e) {
                                e.printStackTrace();
                                stop();
                            }
                            adminListProfileView.pack();
                            adminListProfileView.setLocationRelativeTo(null);
                            adminListProfileView.show();
                            stop();
                        } else if (response.getStatus() == 404) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Sorry no data found ! Please try again.");
                            stop();
                        }
                    } catch (ClientHandlerException e) {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Oops ! It seems you are offline. \n Please check your internet connection !");
                        e.printStackTrace();
                        stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_adminListViewProfileBtnMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        adminListProfileView.hide();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void addStudentResetBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addStudentResetBtnFocusGained
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_addStudentResetBtnFocusGained

    private void addStudentResetBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addStudentResetBtnFocusLost
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_addStudentResetBtnFocusLost

    private void addStudentResetBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStudentResetBtnMouseEntered
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_addStudentResetBtnMouseEntered

    private void addStudentResetBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStudentResetBtnMouseExited
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_addStudentResetBtnMouseExited

    private void addStudentRegisterBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addStudentRegisterBtnFocusGained
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_addStudentRegisterBtnFocusGained

    private void addStudentRegisterBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addStudentRegisterBtnFocusLost
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_addStudentRegisterBtnFocusLost

    private void addStudentRegisterBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStudentRegisterBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_addStudentRegisterBtnMouseEntered

    private void addStudentRegisterBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStudentRegisterBtnMouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_addStudentRegisterBtnMouseExited

    private void addStudentPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStudentPhotoMouseClicked
        uploadStudentProfile(0);
    }//GEN-LAST:event_addStudentPhotoMouseClicked

    private void addStudentBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStudentBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_addStudentBtnMousePressed

    private void addStudentCourseListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_addStudentCourseListItemStateChanged
        if (isNetworkAvailable) {
            String selectedCourse = addStudentCourseList.getSelectedItem().toString();
            System.out.println(selectedCourse);
            if (selectedCourse != "SELECT COURSE") {
                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            addStudentStreamList.setEnabled(true);
                            ClientConfig config = new DefaultClientConfig();
                            Client client = Client.create(config);
                            WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/course/findStream");
                            ClientResponse response = service.path("/" + selectedCourse).accept("application/json").get(ClientResponse.class);
                            if (response.getStatus() == 200) {
                                addStudentStreamList.removeAllItems();
                                addStudentStreamList.addItem("SELECT STREAM");
                                String streamData = response.getEntity(String.class).toString();
                                JSONArray streamObjectArray = new JSONArray(streamData);
                                for (int i = 0; i < streamObjectArray.length(); i++) {
                                    JSONObject streamObject = new JSONObject(streamObjectArray.get(i).toString());
                                    String streamName = streamObject.getString("streamName").toString();
                                    addStudentStreamList.addItem(streamName);
                                }
                            } else {
                                JOptionPane.showMessageDialog(home.this, "No stream found for selected course. \nPlease select another course.");
                                stop();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            stop();
                        }
                    }

                };
                thread.start();
            } else {
                addStudentStreamList.setEnabled(false);
                addStudentStreamList.removeAllItems();
                addStudentStreamList.addItem("SELECT STREAM");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_addStudentCourseListItemStateChanged

    private void addStudentSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentSemesterActionPerformed

    }//GEN-LAST:event_addStudentSemesterActionPerformed

    private void addStudentPhotoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addStudentPhotoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            uploadStudentProfile(0);
        }
    }//GEN-LAST:event_addStudentPhotoKeyPressed

    private void addStudentRegisterBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStudentRegisterBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_addStudentRegisterBtnMousePressed

    private void addStudentRegisterBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStudentRegisterBtnMouseClicked
        if (isNetworkAvailable) {
            if (!addStudentCourseList.getSelectedItem().toString().equals("SELECT COURSE") && !addStudentStreamList.getSelectedItem().toString().equals("SELECT STREAM")
                    && addStudentAadhar.getText().length() > 0 && addStudentSemester.getText().length() > 0 && addStudentName.getText().length() > 0
                    && addStudentEmail.getText().length() > 0 && addStudentAddress.getText().length() > 0 && addStudentEnroll.getText().length() > 0
                    && addStudentContact.getText().length() > 0) {
                if (addStudentAadhar.getText().length() == 12) {
                    if (addStudentContact.getText().length() == 10) {
                        if (studentUploadedFilePath.length() > 0) {
                            Thread thread = new Thread() {

                                @Override
                                public void run() {
                                    try {
                                        File file = new File(studentUploadedFilePath);
                                        FileInputStream fis = new FileInputStream(file);
                                        DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
                                        HttpPost post = new HttpPost("http://payalbeautyspasalon.com/eLibraryRest/rest/student/addStudent");
                                        MultipartEntity data = new MultipartEntity();
                                        data.addPart("profilePhoto", new InputStreamBody(fis, file.getName()));
                                        data.addPart("studentCourse", new StringBody(addStudentCourseList.getSelectedItem().toString()));
                                        data.addPart("studentStream", new StringBody(addStudentStreamList.getSelectedItem().toString()));
                                        data.addPart("studentAadhar", new StringBody(addStudentAadhar.getText()));
                                        data.addPart("studentSemester", new StringBody(addStudentSemester.getText()));
                                        data.addPart("studentName", new StringBody(addStudentName.getText()));
                                        data.addPart("studentEmail", new StringBody(addStudentEmail.getText()));
                                        data.addPart("studentAddress", new StringBody(addStudentAddress.getText()));
                                        data.addPart("studentEnroll", new StringBody(addStudentEnroll.getText()));
                                        data.addPart("studentContact", new StringBody(addStudentContact.getText()));
                                        post.setEntity(data);
                                        HttpResponse response = httpClient.execute(post);
                                        int responseCode = response.getStatusLine().getStatusCode();
                                        if (responseCode == 200) {
                                            processingFrame.setVisible(false);
                                            JOptionPane.showMessageDialog(home.this, "Registration of student was successfully done.");
                                            addStudentCourseList.setSelectedItem("SELECT COURSE");
                                            addStudentStreamList.removeAllItems();
                                            addStudentStreamList.addItem("SELECT STREAM");
                                            addStudentAadhar.setText("");
                                            addStudentSemester.setText("");
                                            addStudentName.setText("");
                                            addStudentEmail.setText("");
                                            addStudentAddress.setText("");
                                            addStudentEnroll.setText("");
                                            addStudentContact.setText("");
                                            studentUploadedFilePath = null;
                                            studentUploadedFileNameFromServer = null;
                                            int width = addStudentPhoto.getWidth();
                                            int height = addStudentPhoto.getHeight();
                                            ImageIcon icon = new ImageIcon(getClass().getResource("../images/userProfile.png"));
                                            addStudentPhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
                                            addStudentPhotoName.setText("Upload Photo");
                                            stop();
                                        } else if (responseCode == 202) {
                                            processingFrame.setVisible(false);
                                            JOptionPane.showMessageDialog(home.this, "Something went wrong while registration !");
                                            InputStream stream = response.getEntity().getContent();
                                            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                                            String message = "";
                                            while ((message = br.readLine()) != null) {
                                                System.out.println(message);
                                            }
                                            stop();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        stop();
                                    }
                                }

                            };
                            thread.start();
                        } else {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(this, "Please upload a student profile image !");
                        }
                    } else {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(this, "Contact number length must be of 10 digits.");
                    }
                } else {
                    processingFrame.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Aadhar length must be of 12 digits.");
                }
            } else {
                processingFrame.setVisible(false);
                JOptionPane.showMessageDialog(this, "Please fill all the fields !");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_addStudentRegisterBtnMouseClicked

    private void updateStudentPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateStudentPhotoMouseClicked
        uploadStudentProfile(1);
    }//GEN-LAST:event_updateStudentPhotoMouseClicked

    private void updateStudentPhotoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateStudentPhotoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStudentPhotoKeyPressed

    private void updateStudentCourseListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_updateStudentCourseListItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStudentCourseListItemStateChanged

    private void updateStudentSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStudentSemesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStudentSemesterActionPerformed

    private void updateStudentResetBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_updateStudentResetBtnFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStudentResetBtnFocusGained

    private void updateStudentResetBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_updateStudentResetBtnFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStudentResetBtnFocusLost

    private void updateStudentResetBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateStudentResetBtnMouseEntered
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_updateStudentResetBtnMouseEntered

    private void updateStudentResetBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateStudentResetBtnMouseExited
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_updateStudentResetBtnMouseExited

    private void updateStudentRegisterBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_updateStudentRegisterBtnFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStudentRegisterBtnFocusGained

    private void updateStudentRegisterBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_updateStudentRegisterBtnFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStudentRegisterBtnFocusLost

    private void updateStudentRegisterBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateStudentRegisterBtnMouseClicked
        if (studentUploadedFilePath != null && studentUploadedFilePath.length() > 0) {
            updateStudentProfile();
        } else {
            updateStudentProfileWithoutPhoto();
        }
    }//GEN-LAST:event_updateStudentRegisterBtnMouseClicked

    private void updateStudentRegisterBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateStudentRegisterBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_updateStudentRegisterBtnMouseEntered

    private void updateStudentRegisterBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateStudentRegisterBtnMouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_updateStudentRegisterBtnMouseExited

    private void updateStudentRegisterBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateStudentRegisterBtnMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStudentRegisterBtnMousePressed

    private void addBookEditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookEditionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addBookEditionActionPerformed

    private void addStudentStreamListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentStreamListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addStudentStreamListActionPerformed

    private void searchStudentTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchStudentTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchStudentTextFieldActionPerformed

    private void studentSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentSearchBtnMouseClicked
        searchStudentDetail();
    }//GEN-LAST:event_studentSearchBtnMouseClicked

    private void updateStudentEnrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStudentEnrollActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStudentEnrollActionPerformed

    private void studentSearchBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentSearchBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_studentSearchBtnMousePressed

    private void studentListBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentListBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_studentListBtnMousePressed

    private void studentListDeleteBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentListDeleteBtnMousePressed
        //
    }//GEN-LAST:event_studentListDeleteBtnMousePressed

    private void studentListDeleteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentListDeleteBtnMouseClicked
        if (isNetworkAvailable) {
            if (studentListTable.getSelectedRow() >= 0) {
                int wantToDelete = JOptionPane.showConfirmDialog(this, "Do you want to delete the selected student profile ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (wantToDelete == 0) {
                    int selectedRow = studentListTable.getSelectedRow();
                    String libId = studentListTable.getValueAt(selectedRow, 0).toString();
                    Thread thread = new Thread() {

                        @Override
                        public void run() {
                            try {
                                ClientConfig config = new DefaultClientConfig();
                                Client client = Client.create(config);
                                WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/student/deleteStudent/" + libId);
                                ClientResponse response = service.get(ClientResponse.class);
                                if (response.getStatus() == 200) {
                                    processingFrame.setVisible(false);
                                    JOptionPane.showMessageDialog(home.this, "Successfully deleted student profile");
                                    getStudentList();
                                    stop();
                                } else {
                                    processingFrame.setVisible(false);
                                    System.out.println(response.getStatus());
                                    stop();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                stop();
                            }
                        }

                    };
                    thread.start();
                }
            } else {
                processingFrame.setVisible(false);
                JOptionPane.showMessageDialog(this, "Please select a row !");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_studentListDeleteBtnMouseClicked

    private void studentListSearchBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentListSearchBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_studentListSearchBtnMousePressed

    private void studentListSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentListSearchBtnMouseClicked
        searchSortedStudentList();
    }//GEN-LAST:event_studentListSearchBtnMouseClicked

    private void jLabel63MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MousePressed
        processingFrameOpen();
    }//GEN-LAST:event_jLabel63MousePressed

    private void jLabel63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MouseClicked
        if (isNetworkAvailable) {
            if (!addBookCategory.getSelectedItem().toString().equals("SELECT CATEGORY") && !addBookSubCat.getSelectedItem().toString().equals("SELECT SUB CATEGORY")
                    && addBookName.getText().length() > 0 && addBookWritter.getText().length() > 0 && addBookPublisher.getText().length() > 0
                    && addBookEdition.getText().length() > 0 && addBookStock.getText().length() > 0 && addBookPrice.getText().length() > 0
                    && addBookDiscription.getText().length() > 0) {
                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
                            HttpPost post = new HttpPost("http://payalbeautyspasalon.com/eLibraryRest/rest/book/addBook");
                            MultipartEntity data = new MultipartEntity();
                            data.addPart("bookCategory", new StringBody(addBookCategory.getSelectedItem().toString()));
                            data.addPart("bookSubCategory", new StringBody(addBookSubCat.getSelectedItem().toString()));
                            data.addPart("bookName", new StringBody(addBookName.getText()));
                            data.addPart("bookWriter", new StringBody(addBookWritter.getText()));
                            data.addPart("bookPublisher", new StringBody(addBookPublisher.getText()));
                            data.addPart("bookEdition", new StringBody(addBookEdition.getText()));
                            data.addPart("bookStock", new StringBody(addBookStock.getText()));
                            data.addPart("bookPrice", new StringBody(addBookPrice.getText()));
                            data.addPart("bookDescription", new StringBody(addBookDiscription.getText()));
                            post.setEntity(data);
                            HttpResponse response = httpClient.execute(post);
                            int responseCode = response.getStatusLine().getStatusCode();
                            System.out.println(responseCode);
                            if (responseCode == 200) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Successfully added book.");
                                addBookCategory.setSelectedIndex(0);
                                addBookSubCat.removeAllItems();
                                addBookSubCat.addItem("Select Sub Category");
                                addBookName.setText("");
                                addBookDiscription.setText("");
                                addBookEdition.setText("");
                                addBookName.setText("");
                                addBookPrice.setText("");
                                addBookPublisher.setText("");
                                addBookStock.setText("");
                                addBookWritter.setText("");
                                stop();
                            } else if (responseCode == 203) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Book is already added to the database.");
                                addBookCategory.setSelectedIndex(0);
                                addBookSubCat.setSelectedIndex(0);
                                addBookName.setText("");
                                addBookWritter.setText("");
                                addBookPublisher.setText("");
                                addBookEdition.setText("");
                                addBookStock.setText("");
                                addBookPrice.setText("");
                                addBookDiscription.setText("");
                                stop();
                            } else if (responseCode == 202) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Something went wrong on server side !");
                                stop();
                            }
                        } catch (Exception e) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                            e.printStackTrace();
                            stop();
                        }
                    }

                };
                thread.start();
            } else {
                processingFrame.setVisible(false);
                JOptionPane.showMessageDialog(this, "Please fill all the fields !");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_jLabel63MouseClicked

    private void addBookBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBookBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_addBookBtnMousePressed

    private void addBookCategoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_addBookCategoryItemStateChanged
        if (isNetworkAvailable) {
            if (addBookCategory.getSelectedItem() != null) {
                String selectedCourse = addBookCategory.getSelectedItem().toString();
                if (!selectedCourse.equalsIgnoreCase("Select Category")) {
                    Thread thread = new Thread() {

                        @Override
                        public void run() {
                            try {
                                ClientConfig config = new DefaultClientConfig();
                                Client client = Client.create(config);
                                WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/course/findStream");
                                ClientResponse response = service.path("/" + selectedCourse).accept("application/json").get(ClientResponse.class);
                                if (response.getStatus() == 200) {
                                    addBookSubCat.removeAllItems();
                                    addBookSubCat.addItem("SELECT SUB CATEGORY");
                                    JSONArray jsonArray = new JSONArray(String.valueOf(response.getEntity(String.class)));
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                        addBookSubCat.addItem(jsonObject.getString("streamName"));
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(home.this, "Sorry no sub category found for selected category !");
                                    stop();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                stop();
                            }
                        }

                    };
                    thread.start();
                }
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_addBookCategoryItemStateChanged

    private void addBookCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookCategoryActionPerformed

    }//GEN-LAST:event_addBookCategoryActionPerformed

    private void addBookNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addBookNameActionPerformed

    private void addBookSubCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookSubCatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addBookSubCatActionPerformed

    private void bookListBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookListBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_bookListBtnMousePressed

    private void bookListSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookListSearchBtnMouseClicked
        if (isNetworkAvailable) {
            String searchBook = bookListSearchField.getText();
            if (searchBook.length() > 0) {
                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            ClientConfig config = new DefaultClientConfig();
                            Client client = Client.create(config);
                            WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/book/findBook");
                            ClientResponse response = service.path("/" + searchBook).accept("application/json").get(ClientResponse.class);
                            if (response.getStatus() == 200) {
                                JSONArray jsonArray = new JSONArray(String.valueOf(response.getEntity(String.class)));
                                if (jsonArray.length() > 0 && jsonArray.get(0) != null) {
                                    Vector<String> header = new Vector<String>();
                                    Vector<Vector<String>> content = new Vector<Vector<String>>();
                                    header.add("Id");
                                    header.add("Category");
                                    header.add("Sub Category");
                                    header.add("Name");
                                    header.add("Writer");
                                    header.add("Publisher");
                                    header.add("Edition");
                                    header.add("Stock");
                                    header.add("Price");
                                    header.add("Description");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        Vector<String> rowData = new Vector<String>();
                                        JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                        rowData.add(jsonObject.getString("bookId"));
                                        rowData.add(jsonObject.getString("bookCategory"));
                                        rowData.add(jsonObject.getString("bookSubCategory"));
                                        rowData.add(jsonObject.getString("bookName"));
                                        rowData.add(jsonObject.getString("bookWriter"));
                                        rowData.add(jsonObject.getString("bookPublisher"));
                                        rowData.add(jsonObject.getString("bookEdition"));
                                        rowData.add(jsonObject.getString("bookStock"));
                                        rowData.add(jsonObject.getString("bookPrice"));
                                        rowData.add(jsonObject.getString("bookDescription"));
                                        content.add(rowData);
                                    }

                                    DefaultTableModel tableModel = new DefaultTableModel(content, header);
                                    bookListTable.setModel(tableModel);
                                    clearBookSearchBtn.setVisible(true);
                                    processingFrame.setVisible(false);
                                    mainContainer.removeAll();
                                    mainContainer.revalidate();
                                    mainContainer.add(bookListMainPanel);
                                    mainContainer.repaint();
                                    stop();
                                } else {
                                    processingFrame.setVisible(false);
                                    JOptionPane.showMessageDialog(home.this, "No data found for search !");
                                    stop();
                                }
                            } else {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Something went wrong on server side !");
                                stop();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            stop();
                        }
                    }

                };
                thread.start();
            } else {
                processingFrame.setVisible(false);
                JOptionPane.showMessageDialog(this, "Please Enter some Term for Search");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_bookListSearchBtnMouseClicked

    private void bookListSearchBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookListSearchBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_bookListSearchBtnMousePressed

    private void AddEBookBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddEBookBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_AddEBookBtnMousePressed

    private void addEBookUplodeBookBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEBookUplodeBookBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_addEBookUplodeBookBtnMousePressed

    private void addEBookUplodeBookBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEBookUplodeBookBtnMouseClicked
        if (isNetworkAvailable) {
            if (!addEBookCat.getSelectedItem().toString().equals("Select Category") && !addEBookSubCat.getSelectedItem().toString().equals("Select Sub Category")
                    && addEBookBookName.getText().length() > 0 && addEBookWriter.getText().length() > 0 && addEBookPublisher.getText().length() > 0
                    && addEBookEdition.getText().length() > 0 && addEBookDescription.getText().length() > 0 && addEBookLocation.getText().length() > 0) {
                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            File file = new File(uploadedEbookFilePath);
                            FileInputStream fis = new FileInputStream(file);
                            DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                            HttpPost post = new HttpPost("http://payalbeautyspasalon.com/eLibraryRest/rest/eBook/addEBook");
                            MultipartEntity data = new MultipartEntity();
                            data.addPart("file", new InputStreamBody(fis, file.getName()));
                            data.addPart("category", new StringBody(addEBookCat.getSelectedItem().toString()));
                            data.addPart("subCategory", new StringBody(addEBookSubCat.getSelectedItem().toString()));
                            data.addPart("bookName", new StringBody(addEBookBookName.getText()));
                            data.addPart("bookWriter", new StringBody(addEBookWriter.getText()));
                            data.addPart("bookPublisher", new StringBody(addEBookPublisher.getText()));
                            data.addPart("bookEdition", new StringBody(addEBookEdition.getText()));
                            data.addPart("bookDescription", new StringBody(addEBookDescription.getText()));
                            data.addPart("bookLocation", new StringBody(addEBookLocation.getText()));
                            post.setEntity(data);
                            HttpResponse response = httpclient.execute(post);
                            int responseCode = response.getStatusLine().getStatusCode();
                            if (responseCode == 200) {
                                processingFrame.setVisible(false);
                                addEBookSubCat.removeAllItems();
                                JOptionPane.showMessageDialog(home.this, "Successfully uploded EBook.");
                                addEBookSubCat.removeAllItems();
                                addEBookSubCat.addItem("Select Sub Category");
                                addEBookCat.setSelectedIndex(0);
                                addEBookBookName.setText("");
                                addEBookWriter.setText("");
                                addEBookPublisher.setText("");
                                addEBookEdition.setText("");
                                addEBookDescription.setText("");
                                addEBookLocation.setText("");
                                stop();
                            } else if (responseCode == 201) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Somthing went wrong from server side");
                                System.out.println(response.getEntity().toString());
                                stop();
                            } else {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Somthing went wrong while uploded EBook.");
                                stop();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            stop();
                        }
                    }

                };
                thread.start();
            } else {
                processingFrame.setVisible(false);
                JOptionPane.showMessageDialog(this, "All the fields are required. Please fill all the fields !");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_addEBookUplodeBookBtnMouseClicked

    private void addEBookAddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEBookAddBtnMouseClicked
        uplodeEBookFile();
    }//GEN-LAST:event_addEBookAddBtnMouseClicked

    private void addEBookCatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_addEBookCatItemStateChanged
        if (isNetworkAvailable) {
            if (addEBookCat.getSelectedItem() != null) {
                if (!addEBookCat.getSelectedItem().toString().equalsIgnoreCase("Select Category")) {
                    String selectedCourse = addEBookCat.getSelectedItem().toString();
                    Thread thread = new Thread() {

                        @Override
                        public void run() {
                            try {
                                ClientConfig config = new DefaultClientConfig();
                                Client client = Client.create(config);
                                WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/course/findStream");
                                ClientResponse response = service.path("/" + selectedCourse).accept("application/json").get(ClientResponse.class);
                                if (response.getStatus() == 200) {
                                    addEBookSubCat.removeAllItems();
                                    addEBookSubCat.addItem("SELECT SUB CATEGORY");
                                    JSONArray jsonArray = new JSONArray(String.valueOf(response.getEntity(String.class)));
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                        addEBookSubCat.addItem(jsonObject.getString("streamName"));
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(home.this, "Sorry no sub found for selected category !");
                                    stop();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                stop();
                            }
                        }

                    };
                    thread.start();
                }
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_addEBookCatItemStateChanged

    private void addEBookSubCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEBookSubCatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addEBookSubCatActionPerformed

    private void addEBookEditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEBookEditionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addEBookEditionActionPerformed

    private void eBookListBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eBookListBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_eBookListBtnMousePressed

    private void requestListForBookIssueBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestListForBookIssueBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_requestListForBookIssueBtnMousePressed

    private void bookRequestAllowBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookRequestAllowBtnMouseClicked
        if (bookRequestTable.getSelectedRow() >= 0 && reqBookIssueSelectedRow >= 0) {
            issueBookFinalPanelOpen();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a request row");
        }
    }//GEN-LAST:event_bookRequestAllowBtnMouseClicked

    private void jLabel81MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel81MouseClicked
        issueBookDialogPanel.setVisible(false);
    }//GEN-LAST:event_jLabel81MouseClicked

    private void jLabel84MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel84MouseClicked
        if (isNetworkAvailable) {
            if (issueReturnDatePicker.getDate() != null) {
                Date date = issueReturnDatePicker.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String returnDate = sdf.format(date);
                String issueId = bookRequestTable.getValueAt(reqBookIssueSelectedRow, 1).toString();
                issueBookDialogPanel.setVisible(false);
                processingFrameOpen();
                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                            HttpPost post = new HttpPost("http://payalbeautyspasalon.com/eLibraryRest/rest/book/issueBook");
                            MultipartEntity data = new MultipartEntity();
                            data.addPart("issueId", new StringBody(issueId));
                            data.addPart("returnDate", new StringBody(returnDate));
                            post.setEntity(data);
                            HttpResponse response = httpclient.execute(post);
                            int responseCode = response.getStatusLine().getStatusCode();
                            if (responseCode == 200) {
                                reqBookIssueSelectedRow = -1;
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Book has been successfully issued !");
                                loadIssueBookRequestList();
                                stop();
                            } else {
                                processingFrame.setVisible(false);
                                stop();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            stop();
                        }
                    }

                };
                thread.start();
            } else {
                processingFrame.setVisible(false);
                issueBookDialogPanel.setVisible(false);
                JOptionPane.showMessageDialog(this, "Please select return date . ");
                issueBookFinalPanelOpen();
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_jLabel84MouseClicked

    private void bookRequestTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookRequestTableMouseClicked
        reqBookIssueSelectedRow = bookRequestTable.getSelectedRow();
    }//GEN-LAST:event_bookRequestTableMouseClicked

    private void jLabel84MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel84MousePressed
        processingFrameOpen();
    }//GEN-LAST:event_jLabel84MousePressed

    private void bookRequestCancelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookRequestCancelBtnMouseClicked
        if (isNetworkAvailable) {
            if (bookRequestTable.getSelectedRow() >= 0 && reqBookIssueSelectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Do you want to cancel request ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == 0) {
                    processingFrameOpen();
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                int selectedRow = bookRequestTable.getSelectedRow();
                                String issueId = bookRequestTable.getValueAt(selectedRow, 1).toString();
                                String bookId = bookRequestTable.getValueAt(selectedRow, 0).toString();
                                ClientConfig config = new DefaultClientConfig();
                                Client client = Client.create(config);
                                WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/book");
                                ClientResponse response = service.path("/cancelRequestIssue").path("/" + issueId).path("/" + bookId).get(ClientResponse.class);
                                if (response.getStatus() == 200) {
                                    processingFrame.setVisible(false);
                                    loadIssueBookRequestList();
                                    stop();
                                } else {
                                    processingFrame.setVisible(false);
                                    JOptionPane.showMessageDialog(home.this, "Something went wrong " + response.getEntity(String.class));
                                    stop();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a request row !");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_bookRequestCancelBtnMouseClicked

    private void bookListUpdateBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookListUpdateBtnMousePressed
        processingFrameOpen();
    }//GEN-LAST:event_bookListUpdateBtnMousePressed

    private void bookListUpdateBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookListUpdateBtnMouseClicked
        if (isNetworkAvailable) {
            bookStockBookId.setEditable(false);
            bookStockCat.setEditable(false);
            bookStockSubCat.setEditable(false);
            bookStockName.setEditable(false);
            bookStockWriter.setEditable(false);
            bookStockPublisher.setEditable(false);
            processingFrameOpen();
            Thread thread = new Thread() {
                public void run() {
                    try {
                        if (bookListTable.getSelectedRow() >= 0) {
                            int selectedRow = bookListTable.getSelectedRow();
                            String bookId = bookListTable.getValueAt(selectedRow, 0).toString();
                            ClientConfig config = new DefaultClientConfig();
                            Client client = Client.create(config);
                            WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/stock/stockUpdate");
                            ClientResponse response = service.path("/" + bookId).accept("application/json").get(ClientResponse.class);
                            if (response.getStatus() == 200) {
                                JSONArray jsonArray = new JSONArray(String.valueOf(response.getEntity(String.class)));
                                JSONObject jsonObject = new JSONObject(jsonArray.get(0).toString());
                                bookStockBookId.setText(jsonObject.getString("bookId"));
                                bookStockName.setText(jsonObject.getString("bookName"));
                                bookStockPublisher.setText(jsonObject.getString("bookPublisher"));
                                bookStockWriter.setText(jsonObject.getString("bookWriter"));
                                bookStockCat.setText(jsonObject.getString("bookCategory"));
                                bookStockSubCat.setText(jsonObject.getString("bookSubCategory"));
                                processingFrame.setVisible(false);
                                mainContainer.removeAll();
                                mainContainer.revalidate();
                                mainContainer.add(bookUpdateMainPanel);
                                mainContainer.repaint();
                                stop();
                            } else {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                                stop();
                            }
                        } else {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Please select a book row to update data");
                            stop();
                        }
                    } catch (Exception e) {
                        processingFrame.setVisible(false);
                        e.printStackTrace();
                        stop();
                    }
                }
            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_bookListUpdateBtnMouseClicked

    private void bookStockSubCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookStockSubCatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookStockSubCatActionPerformed

    private void bookStockWriterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookStockWriterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookStockWriterActionPerformed

    private void bookStockBackBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookStockBackBtnMouseClicked
        mainContainer.removeAll();
        mainContainer.revalidate();
        mainContainer.add(bookListMainPanel);
        mainContainer.repaint();
    }//GEN-LAST:event_bookStockBackBtnMouseClicked

    private void bookStockUpdateStockBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookStockUpdateStockBtnMouseClicked
        if (isNetworkAvailable) {
            if (bookStockAddStock.getText().length() > 0) {
                String bookId = bookStockBookId.getText();
                String bookStock = bookStockAddStock.getText();
                processingFrameOpen();
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
                            HttpPost post = new HttpPost("http://payalbeautyspasalon.com/eLibraryRest/rest/stock/stockUpdateNow");
                            MultipartEntity data = new MultipartEntity();
                            data.addPart("bookId", new StringBody(bookId));
                            data.addPart("stockValue", new StringBody(bookStock));
                            post.setEntity(data);
                            HttpResponse response = httpClient.execute(post);
                            int responseCode = response.getStatusLine().getStatusCode();
                            if (responseCode == 200) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Stock was sucessfully updated !");
                                mainContainer.removeAll();
                                mainContainer.revalidate();
                                mainContainer.add(bookListMainPanel);
                                mainContainer.repaint();
                                stop();
                            } else {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Something went wrong. Please try again !");
                                stop();
                            }
                        } catch (Exception e) {
                            processingFrame.setVisible(false);
                            e.printStackTrace();
                            stop();
                        }
                    }
                };
                thread.start();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter stock value before updating !");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_bookStockUpdateStockBtnMouseClicked

    private void bookListDeleteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookListDeleteBtnMouseClicked
        if (isNetworkAvailable) {
            if (bookListTable.getSelectedRow() >= 0) {
                int selectedRow = bookListTable.getSelectedRow();
                String bookId = bookListTable.getValueAt(selectedRow, 0).toString();
                processingFrameOpen();
                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            ClientConfig config = new DefaultClientConfig();
                            Client client = Client.create(config);
                            WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/book/deleteBook");
                            ClientResponse response = service.path("/" + bookId).get(ClientResponse.class);
                            if (response.getStatus() == 200) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Book was successfully deleted !");
                                loadAllBookList();
                                stop();
                            } else {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                                stop();
                            }
                        } catch (Exception e) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                            e.printStackTrace();
                            stop();
                        }
                    }

                };
                thread.start();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a book row to delete it.");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_bookListDeleteBtnMouseClicked

    private void clearBookSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearBookSearchBtnMouseClicked
        bookListSearchField.setText("");
        loadAllBookList();
    }//GEN-LAST:event_clearBookSearchBtnMouseClicked

    private void clearStudentSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearStudentSearchBtnMouseClicked
        studentListSearchTextField.setText("");
        getStudentList();
    }//GEN-LAST:event_clearStudentSearchBtnMouseClicked

    private void jLabel66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel66MouseClicked
        if (isNetworkAvailable) {
            if (trackIssuedBookTable.getSelectedRow() >= 0) {
                int selectedRow = trackIssuedBookTable.getSelectedRow();
                String issue_id = trackIssuedBookTable.getValueAt(selectedRow, 1).toString();
                String book_id = trackIssuedBookTable.getValueAt(selectedRow, 0).toString();
                processingFrameOpen();
                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            ClientConfig config = new DefaultClientConfig();
                            Client client = Client.create(config);
                            WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/book/returnBook");
                            ClientResponse response = service.path("/" + issue_id + "/" + book_id).get(ClientResponse.class);
                            if (response.getStatus() == 200) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Book has been successfully returned !");
                                loadIssuedBookList();
                                stop();
                            } else {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Something went wrong. Please try again !");
                                stop();
                            }
                        } catch (Exception e) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wrong. Please try again !");
                            e.printStackTrace();
                            stop();
                        }
                    }

                };
                thread.start();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a issued book row to make return !");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_jLabel66MouseClicked

    private void trackBookListSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trackBookListSearchBtnMouseClicked
        if (isNetworkAvailable) {
            if (trackBookListSearchField.getText().length() > 0) {
                String searchTerm = trackBookListSearchField.getText();
                processingFrameOpen();
                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            ClientConfig config = new DefaultClientConfig();
                            Client client = Client.create(config);
                            WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/book/searchIssuedBook");
                            ClientResponse response = service.path("/" + searchTerm).accept("application/json").get(ClientResponse.class);
                            if (response.getStatus() == 200) {
                                processingFrame.setVisible(false);
                                clearIssuedSearchBtn.setVisible(true);
                                Vector<String> header = new Vector<String>();
                                header.add("Book Id");
                                header.add("Issue Id");
                                header.add("Student Library Id");
                                header.add("Issued Date");
                                header.add("Return Date");
                                Vector<Vector<String>> content = new Vector<Vector<String>>();
                                JSONArray jsonArray = new JSONArray(String.valueOf(response.getEntity(String.class)));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Vector<String> bookItem = new Vector<String>();
                                    JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                    bookItem.add(jsonObject.getString("bookId"));
                                    bookItem.add(jsonObject.getString("bookIssueId"));
                                    bookItem.add(jsonObject.getString("studentLibId"));
                                    bookItem.add(jsonObject.getString("bookIssueDate"));
                                    bookItem.add(jsonObject.getString("bookReturnDate"));
                                    content.add(bookItem);
                                }
                                DefaultTableModel tableModel = new DefaultTableModel(content, header);
                                trackIssuedBookTable.setModel(tableModel);
                                mainContainer.removeAll();
                                mainContainer.revalidate();
                                mainContainer.add(trackIssuedBookMainPanel);
                                mainContainer.repaint();
                                stop();
                            } else {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "No detail found for the search term !");
                                trackBookListSearchField.setText("");
                                clearIssuedSearchBtn.setVisible(false);
                                loadIssuedBookList();
                                stop();
                            }
                        } catch (Exception e) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                            e.printStackTrace();
                            stop();
                        }
                    }

                };
                thread.start();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter some term to seach for !");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_trackBookListSearchBtnMouseClicked

    private void clearIssuedSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearIssuedSearchBtnMouseClicked
        trackBookListSearchField.setText("");
        loadIssuedBookList();
    }//GEN-LAST:event_clearIssuedSearchBtnMouseClicked

    private void studenceAttendanceApproveBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studenceAttendanceApproveBtnMouseClicked
        if (isNetworkAvailable) {
            if (studenceAttendanceTable.getSelectedRow() >= 0) {
                int selectedRow = studenceAttendanceTable.getSelectedRow();
                String attendId = studenceAttendanceTable.getValueAt(selectedRow, 0).toString();
                processingFrameOpen();

                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            ClientConfig config = new DefaultClientConfig();
                            Client client = Client.create(config);
                            WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/attendance/approveAttend");
                            ClientResponse response = service.path("/" + attendId).get(ClientResponse.class);
                            if (response.getStatus() == 200) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Student attendance has been approved !");
                                loadAttendanceRequest();
                                stop();
                            } else {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Something went wrong! Please try again.");
                                stop();
                            }
                        } catch (Exception e) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                            e.printStackTrace();
                            stop();
                        }
                    }

                };
                thread.start();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to perform any action !");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_studenceAttendanceApproveBtnMouseClicked

    private void StudentAttendanceExitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentAttendanceExitBtnMouseClicked
        if (isNetworkAvailable) {
            if (studenceAttendanceTable.getSelectedRow() >= 0) {
                int selectedRow = studenceAttendanceTable.getSelectedRow();
                String attendId = studenceAttendanceTable.getValueAt(selectedRow, 0).toString();
                processingFrameOpen();

                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            ClientConfig config = new DefaultClientConfig();
                            Client client = Client.create(config);
                            WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/attendance/exitApprove");
                            ClientResponse response = service.path("/" + attendId).get(ClientResponse.class);
                            if (response.getStatus() == 200) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Student attendance has successfully exited!");
                                loadAttendanceRequest();
                                stop();
                            } else {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Something went wrong! Please try again.");
                                stop();
                            }
                        } catch (Exception e) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                            e.printStackTrace();
                            stop();
                        }
                    }

                };
                thread.start();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to perform any action !");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }//GEN-LAST:event_StudentAttendanceExitBtnMouseClicked

    private void viewStudentAttendanceRecordBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewStudentAttendanceRecordBtnMouseClicked
        isAdminSubMenuActive = false;
        isStudentSubMenuActive = true;
        isBookSubMenuActive = false;
        isHelpSubMenuActive = false;
        addStudentBtn.setForeground(new Color(255, 255, 255));
        updateStudentBtn.setForeground(new Color(255, 255, 255));
        studentListBtn.setForeground(new Color(255, 255, 255));
        studentAttendenceBtn.setForeground(new Color(255, 255, 255));
        viewStudentAttendanceRecordBtn.setForeground(new Color(0, 155, 202));

        attendanceRecordTablePanel.setVisible(false);
        fromDate.setDateFormatString("yyyy-MM-dd");
        toDate.setDateFormatString("yyyy-MM-dd");
        toDate.setMaxSelectableDate(new Date());
        fromDate.setMaxSelectableDate(new Date());
        mainContainer.removeAll();
        mainContainer.revalidate();
        mainContainer.add(studentAttendanceRecordMainPanel);
        mainContainer.repaint();
    }//GEN-LAST:event_viewStudentAttendanceRecordBtnMouseClicked

    private void searchAttendanceBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchAttendanceBtnMouseClicked
        if (fromDate.getDate() != null) {
            if (toDate.getDate() != null) {
                Date fromDateString = fromDate.getDate();
                Date toDateString = toDate.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String newFromDate = sdf.format(fromDateString);
                String newToDate = sdf.format(toDateString);
                System.out.println(newFromDate + " => " + newToDate);
                processingFrameOpen();
                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            ClientConfig config = new DefaultClientConfig();
                            Client client = Client.create(config);
                            WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/attendance/getAttendanceRecord");
                            ClientResponse response = service.path("/" + newFromDate + "/" + newToDate).accept("application/json").get(ClientResponse.class);
                            if (response.getStatus() == 200) {
                                Vector<String> header = new Vector<String>();
                                header.add("Attendance Id");
                                header.add("Student Library Id");
                                header.add("Entry Date Time");
                                header.add("Exit Date Time");
                                Vector<Vector<String>> content = new Vector<Vector<String>>();
                                JSONArray jsonArray = new JSONArray(String.valueOf(response.getEntity(String.class)));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                    Vector<String> data = new Vector<String>();
                                    data.add(jsonObject.getString("attendId"));
                                    data.add(jsonObject.getString("studentLibId"));
                                    data.add(jsonObject.getString("entryTime"));
                                    data.add(jsonObject.getString("exitTime"));
                                    content.add(data);
                                }
                                processingFrame.setVisible(false);
                                DefaultTableModel tableModel = new DefaultTableModel(content, header);
                                attendanceRecordTable.setModel(tableModel);
                                attendanceRecordTablePanel.setVisible(true);
                                studentAttendanceRecordMainPanel.repaint();
                            } else if (response.getStatus() == 202) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Sorry no result found !");
                                attendanceRecordTablePanel.setVisible(false);
                                stop();
                            }
                        } catch (Exception e) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wong on server side !");
                            e.printStackTrace();
                            stop();
                        }
                    }

                };
                thread.start();
            } else {
                JOptionPane.showMessageDialog(this, "Please select an ending date !");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a starting date !");
        }
    }//GEN-LAST:event_searchAttendanceBtnMouseClicked

    private void searchAttendanceBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchAttendanceBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_searchAttendanceBtnMouseEntered

    private void searchAttendanceBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchAttendanceBtnMouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_searchAttendanceBtnMouseExited

    private void bookStockUpdateStockBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookStockUpdateStockBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_bookStockUpdateStockBtnMouseEntered

    private void studentSearchBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentSearchBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_studentSearchBtnMouseEntered

    private void studentSearchBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentSearchBtnMouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_studentSearchBtnMouseExited

    private void studentResetBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentResetBtnMouseEntered
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_studentResetBtnMouseEntered

    private void studentResetBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentResetBtnMouseExited
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_studentResetBtnMouseExited

    private void ebookListRemoveBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ebookListRemoveBtnMouseEntered
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_ebookListRemoveBtnMouseEntered

    private void ebookListRemoveBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ebookListRemoveBtnMouseExited
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_ebookListRemoveBtnMouseExited

    private void addEBookAddBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEBookAddBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_addEBookAddBtnMouseEntered

    private void addEBookAddBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEBookAddBtnMouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_addEBookAddBtnMouseExited

    private void addEBookUplodeBookBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEBookUplodeBookBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_addEBookUplodeBookBtnMouseEntered

    private void addEBookUplodeBookBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEBookUplodeBookBtnMouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_addEBookUplodeBookBtnMouseExited

    private void bookRequestAllowBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookRequestAllowBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_bookRequestAllowBtnMouseEntered

    private void bookRequestAllowBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookRequestAllowBtnMouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_bookRequestAllowBtnMouseExited

    private void bookRequestCancelBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookRequestCancelBtnMouseEntered
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_bookRequestCancelBtnMouseEntered

    private void bookRequestCancelBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookRequestCancelBtnMouseExited
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_bookRequestCancelBtnMouseExited

    private void jLabel66MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel66MouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_jLabel66MouseEntered

    private void jLabel66MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel66MouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_jLabel66MouseExited

    private void bookListUpdateBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookListUpdateBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_bookListUpdateBtnMouseEntered

    private void bookListUpdateBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookListUpdateBtnMouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_bookListUpdateBtnMouseExited

    private void jLabel63MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_jLabel63MouseEntered

    private void jLabel63MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_jLabel63MouseExited

    private void jLabel64MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseEntered
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_jLabel64MouseEntered

    private void jLabel64MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseExited
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_jLabel64MouseExited

    private void StudentAttendanceExitBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentAttendanceExitBtnMouseEntered
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_StudentAttendanceExitBtnMouseEntered

    private void StudentAttendanceExitBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentAttendanceExitBtnMouseExited
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_StudentAttendanceExitBtnMouseExited

    private void studenceAttendanceApproveBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studenceAttendanceApproveBtnMouseEntered
        btnSuccessFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_studenceAttendanceApproveBtnMouseEntered

    private void studenceAttendanceApproveBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studenceAttendanceApproveBtnMouseExited
        btnSuccessFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_studenceAttendanceApproveBtnMouseExited

    private void bookListDeleteBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookListDeleteBtnMouseEntered
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_bookListDeleteBtnMouseEntered

    private void bookListDeleteBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookListDeleteBtnMouseExited
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_bookListDeleteBtnMouseExited

    private void addAdminResetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addAdminResetBtnMouseClicked
        ImageIcon icon = new ImageIcon(getClass().getResource("../images/userProfile.png"));
        addAdminAadhar.setText("");
        addAdminName.setText("");
        addAdminAddress.setText("");
        addAdminContact.setText("");
        addAdminEmail.setText("");
        addAdminUsername.setText("");
        addAdminPassword.setText("");
        addAdminPhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH)));
        addAdminPhotoName.setText("Upload Photo");
    }//GEN-LAST:event_addAdminResetBtnMouseClicked

    private void ebookListRemoveBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ebookListRemoveBtnMouseClicked
        if (eBookListTable.getSelectedRow() >= 0) {
            int j = JOptionPane.showConfirmDialog(this, "Do you want to delete ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (j == 0) {
                int selectedRow = eBookListTable.getSelectedRow();
                String ebookId = eBookListTable.getValueAt(selectedRow, 0).toString();
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            ClientConfig config = new DefaultClientConfig();
                            Client client = Client.create(config);
                            WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/eBook/deleteEbook");
                            ClientResponse response = service.path("/" + ebookId).get(ClientResponse.class);
                            if (response.getStatus() == 200) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Book has been successfully deleted !");
                                loadEBookList();
                                stop();
                            } else {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Something went wrong on server side !");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                            stop();
                        }
                    }
                };
                thread.start();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to perform any action !");
        }
    }//GEN-LAST:event_ebookListRemoveBtnMouseClicked

    private void studentResetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentResetBtnMouseClicked
        searchStudentTextField.setText("");
    }//GEN-LAST:event_studentResetBtnMouseClicked

    private void jLabel64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseClicked
        addBookCategory.setSelectedIndex(0);
        addBookSubCat.removeAllItems();
        addBookSubCat.addItem("Select Sub Category");
        addBookName.setText("");
        addBookDiscription.setText("");
        addBookEdition.setText("");
        addBookName.setText("");
        addBookPrice.setText("");
        addBookPublisher.setText("");
        addBookStock.setText("");
        addBookWritter.setText("");
    }//GEN-LAST:event_jLabel64MouseClicked

    private void updateAdminResultResetBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminResultResetBtnMouseExited
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminResultResetBtnMouseExited

    private void updateAdminResultResetBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminResultResetBtnMouseEntered
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminResultResetBtnMouseEntered

    private void updateAdminResultResetBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_updateAdminResultResetBtnFocusLost
        btnDangerFocusOut(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminResultResetBtnFocusLost

    private void updateAdminResultResetBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_updateAdminResultResetBtnFocusGained
        btnDangerFoucsIn(evt.getComponent().getParent());
    }//GEN-LAST:event_updateAdminResultResetBtnFocusGained

    private void updateAdminResultResetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminResultResetBtnMouseClicked
        mainContainer.removeAll();
        mainContainer.revalidate();
        mainContainer.add(adminUpdatePanel);
        mainContainer.repaint();
    }//GEN-LAST:event_updateAdminResultResetBtnMouseClicked

    private void updateAdminResetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateAdminResetBtnMouseClicked
        updateAdminSearchField.setText("");
    }//GEN-LAST:event_updateAdminResetBtnMouseClicked

    private void updateStudentResetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateStudentResetBtnMouseClicked
        mainContainer.removeAll();
        mainContainer.revalidate();
        mainContainer.add(studentSearchMainPanel);
        mainContainer.repaint();
    }//GEN-LAST:event_updateStudentResetBtnMouseClicked

    private void addStudentResetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addStudentResetBtnMouseClicked
        addStudentCourseList.setSelectedIndex(0);
        addStudentStreamList.removeAllItems();
        addStudentStreamList.addItem("SELECT STREAM");
        ImageIcon icon = new ImageIcon(getClass().getResource("../images/userProfile.png"));
        addStudentPhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH)));
        addStudentPhotoName.setText("Upload Photo");
        addStudentName.setText("");
        addStudentEmail.setText("");
        addStudentAadhar.setText("");
        addStudentAddress.setText("");
        addStudentEnroll.setText("");
        addStudentContact.setText("");
        addStudentSemester.setText("");
    }//GEN-LAST:event_addStudentResetBtnMouseClicked
    // METHOD FOR UPLOADING EBOOK FILE 
    public void uplodeEBookFile() {
        JFileChooser jfc = new JFileChooser();
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("PDF File", "pdf");
        jfc.setFileFilter(fnef);
        int showFileChooser = jfc.showOpenDialog(null);
        if (showFileChooser == JFileChooser.APPROVE_OPTION) {
            uploadedEbookFilePath = jfc.getSelectedFile().getAbsolutePath();
            addEBookLocation.setText(uploadedEbookFilePath);
        }

    }

    // METHOD FOR SEARCHIG STUDENT DETAILS 
    private void searchStudentDetail() {
        if (isNetworkAvailable) {
            String searchTerm = searchStudentTextField.getText();
            if (searchTerm.length() > 0) {
                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            ClientConfig config = new DefaultClientConfig();
                            Client client = Client.create(config);
                            WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/student/studentDetail");
                            ClientResponse response = service.path("/" + searchTerm).accept("application/json").get(ClientResponse.class);
                            System.out.println(response.getStatus());
                            if (response.getStatus() == 200) {
                                String studentData = response.getEntity(String.class);
                                JSONArray studentArray = new JSONArray(studentData);
                                JSONObject studentDetail = new JSONObject(studentArray.get(0).toString());

                                updateStudentAadhar.setText(studentDetail.getString("studentAadhar"));
                                updateStudentSemester.setText(studentDetail.getString("studentSemester"));
                                updateStudentName.setText(studentDetail.getString("studentName"));
                                updateStudentEmail.setText(studentDetail.getString("studentEmail"));
                                updateStudentAddress.setText(studentDetail.getString("studentAddress"));
                                updateStudentEnroll.setText(studentDetail.getString("studentEnrollNo"));
                                updateStudentContact.setText(studentDetail.getString("studentContact"));
                                updateStudentPhotoName.setText(studentDetail.getString("studentPhoto"));
                                studentUploadedFileNameFromServer = studentDetail.getString("studentPhoto");

                                System.out.println(searchTerm + " = " + response.getStatus());
                                ClientConfig config1 = new DefaultClientConfig();
                                Client client1 = Client.create(config1);
                                WebResource service1 = client1.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/course/allCourse");
                                ClientResponse response1 = service1.accept("application/json").get(ClientResponse.class);
                                if (response1.getStatus() == 200) {
                                    updateStudentCourseList.removeAllItems();
                                    updateStudentCourseList.addItem("SELECT COURSE");
                                    JSONArray courseArray = new JSONArray(response1.getEntity(String.class));
                                    for (int i = 0; i < courseArray.length(); i++) {
                                        String courseData = courseArray.get(i).toString();
                                        JSONObject courseObject = new JSONObject(courseData);
                                        updateStudentCourseList.addItem(courseObject.getString("courseName"));
                                    }
                                }
                                ClientConfig config2 = new DefaultClientConfig();
                                Client client2 = Client.create(config2);
                                WebResource service2 = client2.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/course/findStream");
                                ClientResponse response2 = service2.path("/" + studentDetail.getString("studentCourse")).accept("application/json").get(ClientResponse.class);
                                if (response2.getStatus() == 200) {
                                    updateStudentStreamList.removeAllItems();
                                    updateStudentStreamList.addItem("SELECT STREAM");
                                    JSONArray streamArray = new JSONArray(response2.getEntity(String.class));
                                    for (int i = 0; i < streamArray.length(); i++) {
                                        String streamData = streamArray.get(i).toString();
                                        JSONObject streamObject = new JSONObject(streamData);
                                        updateStudentStreamList.addItem(streamObject.getString("streamName"));
                                    }
                                }
                                updateStudentCourseList.setSelectedItem(studentDetail.getString("studentCourse"));
                                updateStudentStreamList.setSelectedItem(studentDetail.getString("studentStream"));

                                int loaded = 0;
                                int height = updateStudentPhoto.getHeight();
                                int width = updateStudentPhoto.getWidth();

                                URL url = new URL("http://payalbeautyspasalon.com/eLibraryRest/images/student/" + studentUploadedFileNameFromServer);
                                BufferedImage image = null;
                                while (loaded < 1) {
                                    image = ImageIO.read(url);
                                    loaded = image.getWidth();
                                }

                                ImageIcon icon = new ImageIcon(image);
                                updateStudentPhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
                                updateStudentAadhar.setEditable(false);
                                updateStudentEnroll.setEditable(false);
                                processingFrame.setVisible(false);
                                mainContainer.removeAll();
                                mainContainer.revalidate();
                                mainContainer.add(studentUpdateResultPanel);
                                mainContainer.repaint();
                                stop();
                            } else if (response.getStatus() == 201) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Sorry no student found for this search !!");
                                stop();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            stop();
                        }
                    }

                };
                thread.start();
            } else {
                processingFrame.setVisible(false);
                JOptionPane.showMessageDialog(this, "Please enter name / aadhar / library id of student");
            }
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }

    private void updateStudentProfile() {
        if (isNetworkAvailable) {
            processingFrameOpen();
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        File profilePhoto = new File(studentUploadedFilePath);
                        FileInputStream fis = new FileInputStream(profilePhoto);
                        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                        HttpPost post = new HttpPost("http://payalbeautyspasalon.com/eLibraryRest/rest/student/updateStudentProfile");
                        MultipartEntity data = new MultipartEntity();
                        data.addPart("file", new InputStreamBody(fis, profilePhoto.getName()));
                        data.addPart("studentName", new StringBody(updateStudentName.getText()));
                        data.addPart("studentEmail", new StringBody(updateStudentEmail.getText()));
                        data.addPart("studentContact", new StringBody(updateStudentContact.getText()));
                        data.addPart("studentAddress", new StringBody(updateStudentAddress.getText()));
                        data.addPart("studentAadhar", new StringBody(updateStudentAadhar.getText()));
                        data.addPart("studentCourse", new StringBody(updateStudentCourseList.getSelectedItem().toString()));
                        data.addPart("studentStream", new StringBody(updateStudentStreamList.getSelectedItem().toString()));
                        data.addPart("studentSemester", new StringBody(updateStudentSemester.getText()));
                        data.addPart("studentEnrollNo", new StringBody(updateStudentEnroll.getText()));
                        data.addPart("oldPhoto", new StringBody(studentUploadedFileNameFromServer));
                        post.setEntity(data);
                        HttpResponse response = httpclient.execute(post);
                        int responseCode = response.getStatusLine().getStatusCode();
                        System.out.println(responseCode);
                        if (responseCode == 200) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Successfully updated student profile .");
                            int height = updateStudentPhoto.getHeight();
                            int width = updateStudentPhoto.getWidth();
                            ImageIcon icon = new ImageIcon(getClass().getResource("../images/userProfile.png"));
                            updateStudentPhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
                            updateStudentPhotoName.setText("Upload Photo");
                            updateStudentName.setText("");
                            updateStudentCourseList.removeAllItems();
                            updateStudentStreamList.removeAllItems();
                            updateStudentEmail.setText("");
                            updateStudentContact.setText("");
                            updateStudentAddress.setText("");
                            updateStudentEnroll.setText("");
                            updateStudentSemester.setText("");
                            searchStudentTextField.setText("");
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(studentSearchMainPanel);
                            mainContainer.repaint();
                            stop();
                        } else {
                            processingFrame.setVisible(false);
                            System.out.println(responseCode + " => " + response.getEntity().toString());
                            JOptionPane.showMessageDialog(home.this, "Something went wrong while updating student profile !");
                            stop();
                        }
                    } catch (NoClassDefFoundError e) {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Oops ! It seems your are offline. \n Please check your internet connectivity !");
                        e.printStackTrace();
                        stop();
                    } catch (Exception e) {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Oops ! Something went wrong !");
                        e.printStackTrace();
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }

    private void updateStudentProfileWithoutPhoto() {
        if (isNetworkAvailable) {
            processingFrameOpen();
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                        HttpPost post = new HttpPost("http://payalbeautyspasalon.com/eLibraryRest/rest/student/updateStudentProfileWithoutPhoto");
                        MultipartEntity data = new MultipartEntity();
                        data.addPart("studentName", new StringBody(updateStudentName.getText()));
                        data.addPart("studentEmail", new StringBody(updateStudentEmail.getText()));
                        data.addPart("studentContact", new StringBody(updateStudentContact.getText()));
                        data.addPart("studentAddress", new StringBody(updateStudentAddress.getText()));
                        data.addPart("studentAadhar", new StringBody(updateStudentAadhar.getText()));
                        data.addPart("studentCourse", new StringBody(updateStudentCourseList.getSelectedItem().toString()));
                        data.addPart("studentStream", new StringBody(updateStudentStreamList.getSelectedItem().toString()));
                        data.addPart("studentSemester", new StringBody(updateStudentSemester.getText()));
                        data.addPart("studentEnrollNo", new StringBody(updateStudentEnroll.getText()));
                        data.addPart("oldPhoto", new StringBody(studentUploadedFileNameFromServer));
                        post.setEntity(data);
                        HttpResponse response = httpclient.execute(post);
                        int responseCode = response.getStatusLine().getStatusCode();
                        System.out.println(responseCode);
                        if (responseCode == 200) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Successfully updated student profile .");
                            int height = updateStudentPhoto.getHeight();
                            int width = updateStudentPhoto.getWidth();
                            ImageIcon icon = new ImageIcon(getClass().getResource("../images/userProfile.png"));
                            updateStudentPhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
                            updateStudentPhotoName.setText("Upload Photo");
                            updateStudentName.setText("");
                            updateStudentCourseList.removeAllItems();
                            updateStudentStreamList.removeAllItems();
                            updateStudentEmail.setText("");
                            updateStudentContact.setText("");
                            updateStudentAddress.setText("");
                            updateStudentEnroll.setText("");
                            updateStudentSemester.setText("");
                            searchStudentTextField.setText("");
                            mainContainer.removeAll();
                            mainContainer.revalidate();
                            mainContainer.add(studentSearchMainPanel);
                            mainContainer.repaint();
                            stop();
                        } else {
                            processingFrame.setVisible(false);
                            System.out.println(responseCode + " => " + response.getEntity().toString());
                            JOptionPane.showMessageDialog(home.this, "Something went wrong while updating student profile !");
                            stop();
                        }
                    } catch (NoClassDefFoundError e) {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Oops ! It seems your are offline. \n Please check your internet connectivity !");
                        e.printStackTrace();
                        stop();
                    } catch (Exception e) {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Oops ! Something went wrong !");
                        e.printStackTrace();
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }

    // METHOD FOR UPDATING ADMIN PROFILE WITHOUT PHOTO
    private void updateAdminProfileWithoutPhoto() {
        if (isNetworkAvailable) {
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                        HttpPost post = new HttpPost("http://payalbeautyspasalon.com/eLibraryRest/rest/admin/updateAdminWithoutPhoto");
                        MultipartEntity data = new MultipartEntity();
                        data.addPart("adminName", new StringBody(updateAdminResultName.getText()));
                        data.addPart("adminEmail", new StringBody(updateAdminResultEmail.getText()));
                        data.addPart("adminContact", new StringBody(updateAdminResultContact.getText()));
                        data.addPart("adminAddress", new StringBody(updateAdminResultAddress.getText()));
                        data.addPart("adminAadhar", new StringBody(updateAdminResultAadhar.getText()));
                        data.addPart("adminUsername", new StringBody(updateAdminResultUsername.getText()));
                        data.addPart("adminPassword", new StringBody(updateAdminResultPassword.getText()));
                        post.setEntity(data);
                        HttpResponse response = httpclient.execute(post);
                        int responseCode = response.getStatusLine().getStatusCode();
                        if (responseCode == 200) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Successfully Updated Admin Profile.");
                            updateAdminSearchField.setText("");
                            mainContainer.removeAll();
                            mainContainer.add(adminUpdatePanel);
                            mainContainer.revalidate();
                            mainContainer.repaint();
                            stop();
                        } else {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wrong !");
                            stop();
                        }
                    } catch (NoClassDefFoundError e) {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Oops ! It seems your are offline. \n Please check your internet connectivity !");
                        e.printStackTrace();
                        stop();
                    } catch (Exception e) {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Oops ! Something went wrong !");
                        e.printStackTrace();
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }

    // METHOD FOR UPDATING ADMIN PROFILE WITH PHOTO
    private void updateAdminProfileWithPhoto() {
        if (isNetworkAvailable) {
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        File profilePhoto = new File(uploadedFilePath);
                        FileInputStream fis = new FileInputStream(profilePhoto);
                        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                        HttpPost post = new HttpPost("http://payalbeautyspasalon.com/eLibraryRest/rest/admin/updateAdminWithPhoto");
                        MultipartEntity data = new MultipartEntity();
                        data.addPart("file", new InputStreamBody(fis, profilePhoto.getName()));
                        data.addPart("adminName", new StringBody(updateAdminResultName.getText()));
                        data.addPart("adminEmail", new StringBody(updateAdminResultEmail.getText()));
                        data.addPart("adminContact", new StringBody(updateAdminResultContact.getText()));
                        data.addPart("adminAddress", new StringBody(updateAdminResultAddress.getText()));
                        data.addPart("adminAadhar", new StringBody(updateAdminResultAadhar.getText()));
                        data.addPart("username", new StringBody(updateAdminResultUsername.getText()));
                        data.addPart("password", new StringBody(updateAdminResultPassword.getText()));
                        data.addPart("oldPhoto", new StringBody(uploadedFileNameFromServer));
                        post.setEntity(data);
                        HttpResponse response = httpclient.execute(post);
                        int responseCode = response.getStatusLine().getStatusCode();
                        if (responseCode == 200) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Successfully updated admin profile .");
                            stop();
                        } else {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Something went wrong while updating admin profile !");
                            stop();
                        }
                    } catch (NoClassDefFoundError e) {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Oops ! It seems your are offline. \n Please check your internet connectivity !");
                        e.printStackTrace();
                        stop();
                    } catch (Exception e) {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Oops ! Something went wrong !");
                        e.printStackTrace();
                        stop();
                    }
                }

            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }

    // METHOD FOR SEARCHING ADMIN DETAILS AND DISPLAYING IT'S RESULT PANEL *********************
    private void searchForAdminDetail() {
        if (isNetworkAvailable) {
            String searchTerm = updateAdminSearchField.getText();
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        ClientConfig config = new DefaultClientConfig();
                        Client client = Client.create(config);
                        WebResource service = client.resource("http://payalbeautyspasalon.com/eLibraryRest/rest/admin/searchAdmin");
                        ClientResponse response = service.path("/" + searchTerm).accept("application/json").get(ClientResponse.class);
                        if (response.getStatus() == 200) {
                            String data = response.getEntity(String.class);
                            JSONObject admin = new JSONObject(data);

                            updateAdminResultName.setText(admin.getString("adminName"));
                            updateAdminResultEmail.setText(admin.getString("adminEmail"));
                            updateAdminResultAadhar.setText(admin.getString("adminAadhar"));
                            updateAdminResultContact.setText(admin.getString("adminContact"));
                            updateAdminResultAddress.setText(admin.getString("adminAddress"));
                            updateAdminResultUsername.setText(admin.getString("adminUsername"));
                            updateAdminResultUsername.setEditable(false);
                            updateAdminResultAadhar.setEditable(false);
                            updateAdminResultPassword.setText(admin.getString("adminPassword"));
                            String photoName = admin.getString("adminPhoto");
                            try {
                                int loaded = 0;
                                int height = updateAdminResultPhoto.getHeight();
                                int width = updateAdminResultPhoto.getWidth();

                                URL url = new URL("http://payalbeautyspasalon.com/eLibraryRest/images/" + photoName);
                                BufferedImage image = null;
                                while (loaded < 1) {
                                    image = ImageIO.read(url);
                                    loaded = image.getWidth();
                                }

                                ImageIcon icon = new ImageIcon(image);
                                updateAdminResultPhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
                                updateAdminResultPhotoName.setText(photoName);
                                uploadedFileNameFromServer = photoName;
                            } catch (ClientHandlerException e) {
                                processingFrame.setVisible(false);
                                JOptionPane.showMessageDialog(home.this, "Oops ! It seems you are offline. \n Please check your internet connection !");
                                e.printStackTrace();
                                stop();
                            } catch (Exception e) {
                                e.printStackTrace();
                                stop();
                            }
                            mainContainer.removeAll();
                            mainContainer.add(adminUpdateResultPanel);
                            mainContainer.revalidate();
                            mainContainer.repaint();
                            processingFrame.setVisible(false);
                            stop();
                        } else if (response.getStatus() == 404) {
                            processingFrame.setVisible(false);
                            JOptionPane.showMessageDialog(home.this, "Sorry no data found ! Please try again.");
                            stop();
                        }
                    } catch (ClientHandlerException e) {
                        processingFrame.setVisible(false);
                        JOptionPane.showMessageDialog(home.this, "Oops ! It seems you are offline. \n Please check your internet connection !");
                        e.printStackTrace();
                        stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                        stop();
                    }
                }
            };
            thread.start();
        } else {
            processingFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "It seems you are offline ! \nPlease connect to internet and try again .");
            mainContainer.removeAll();
            mainContainer.revalidate();
            mainContainer.add(HomeMainPanel);
            mainContainer.repaint();
        }
    }

    // METHODS FOR DIFFERENT BUTTON EFFECTS *****************
    private void btnDangerFoucsIn(Container containerName) {
        containerName.setBackground(new Color(255, 79, 25));
    }

    private void btnDangerFocusOut(Container containerName) {
        containerName.setBackground(new Color(51, 51, 51));
    }

    private void btnSuccessFoucsIn(Container containerName) {
        containerName.setBackground(new Color(0, 195, 122));
    }

    private void btnSuccessFocusOut(Container containerName) {
        containerName.setBackground(new Color(51, 51, 51));
    }

    // METHOD FOR PICKING IMAGE FROM JFILECHOOSER FOR STUDENT REGISTRATION AND UPDATION
    private void uploadStudentProfile(int k) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files (.jpg, .jpeg, .png) only", "jpg", "jpeg", "png");
        jfc.setFileFilter(filter);
        int open = jfc.showOpenDialog(null);
        if (open == JFileChooser.APPROVE_OPTION) {
            if (k == 0) {
                int height = addStudentPhoto.getHeight();
                int width = addStudentPhoto.getWidth();
                ImageIcon icon = new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
                addStudentPhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
                String photoName = "";
                if (jfc.getSelectedFile().getName().length() > 12) {
                    photoName = jfc.getSelectedFile().getName().substring(0, 4) + "..." + jfc.getSelectedFile().getName().substring(jfc.getSelectedFile().getName().indexOf("."));
                } else {
                    photoName = jfc.getSelectedFile().getName();
                }
                addStudentPhotoName.setText(photoName);
                studentUploadedFilePath = jfc.getSelectedFile().getAbsolutePath();
            } else if (k == 1) {
                int height = updateStudentPhoto.getHeight();
                int width = updateStudentPhoto.getWidth();
                ImageIcon icon = new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
                updateStudentPhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
                String photoName = "";
                if (jfc.getSelectedFile().getName().length() > 12) {
                    photoName = jfc.getSelectedFile().getName().substring(0, 4) + "..." + jfc.getSelectedFile().getName().substring(jfc.getSelectedFile().getName().indexOf("."));
                } else {
                    photoName = jfc.getSelectedFile().getName();
                }
                updateStudentPhotoName.setText(photoName);
                studentUploadedFilePath = jfc.getSelectedFile().getAbsolutePath();
            }
        }
    }

    // METHOD FOR PICKING IMAGE FROM JFILECHOOSER FOR ADMIN REGISTRATION AND UPDATION
    private void uploadPhoto(int k) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files (.jpg, .jpeg, .png)", "jpg", "jpeg", "png");
        jfc.setFileFilter(filter);
        int open = jfc.showOpenDialog(null);
        if (open == JFileChooser.APPROVE_OPTION) {
            if (k == 0) {
                int height = addAdminPhoto.getHeight();
                int width = addAdminPhoto.getWidth();
                ImageIcon icon = new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
                addAdminPhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
                String photoName = "";
                if (jfc.getSelectedFile().getName().length() > 12) {
                    photoName = jfc.getSelectedFile().getName().substring(0, 4) + "..." + jfc.getSelectedFile().getName().substring(jfc.getSelectedFile().getName().indexOf("."));
                } else {
                    photoName = jfc.getSelectedFile().getName();
                }
                addAdminPhotoName.setText(photoName);
                uploadedFilePath = jfc.getSelectedFile().getAbsolutePath();
            } else if (k == 1) {
                int height = updateAdminResultPhoto.getHeight();
                int width = updateAdminResultPhoto.getWidth();
                ImageIcon icon = new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
                updateAdminResultPhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
                String photoName = "";
                if (jfc.getSelectedFile().getName().length() > 12) {
                    photoName = jfc.getSelectedFile().getName().substring(0, 4) + "..." + jfc.getSelectedFile().getName().substring(jfc.getSelectedFile().getName().indexOf("."));
                } else {
                    photoName = jfc.getSelectedFile().getName();
                }
                updateAdminResultPhotoName.setText(photoName);
                uploadedFilePath = jfc.getSelectedFile().getAbsolutePath();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddEBookBtn;
    private javax.swing.JPanel HomeMainPanel;
    private javax.swing.JLabel StudentAttendanceExitBtn;
    private javax.swing.JPanel TrackBookListSearchPanel;
    private javax.swing.JTextField addAdminAadhar;
    private javax.swing.JTextArea addAdminAddress;
    private javax.swing.JLabel addAdminBtn;
    private javax.swing.JTextField addAdminContact;
    private javax.swing.JTextField addAdminEmail;
    private javax.swing.JTextField addAdminName;
    private javax.swing.JPanel addAdminPanel;
    private javax.swing.JTextField addAdminPassword;
    private javax.swing.JLabel addAdminPhoto;
    private static javax.swing.JLabel addAdminPhotoName;
    private javax.swing.JLabel addAdminRegisterBtn;
    private javax.swing.JPanel addAdminRegisterBtnPanel;
    private javax.swing.JLabel addAdminResetBtn;
    private javax.swing.JPanel addAdminResetBtnPanel;
    private javax.swing.JPanel addAdminSubPanel;
    private javax.swing.JTextField addAdminUsername;
    private javax.swing.JLabel addBookBtn;
    private javax.swing.JComboBox<String> addBookCategory;
    private javax.swing.JTextArea addBookDiscription;
    private javax.swing.JTextField addBookEdition;
    private javax.swing.JPanel addBookMainPanel;
    private javax.swing.JTextField addBookName;
    private javax.swing.JTextField addBookPrice;
    private javax.swing.JTextField addBookPublisher;
    private javax.swing.JTextField addBookStock;
    private javax.swing.JComboBox<String> addBookSubCat;
    private javax.swing.JPanel addBookSubPanel;
    private javax.swing.JTextField addBookWritter;
    private javax.swing.JLabel addEBookAddBtn;
    private javax.swing.JTextField addEBookBookName;
    private javax.swing.JComboBox<String> addEBookCat;
    private javax.swing.JTextArea addEBookDescription;
    private javax.swing.JTextField addEBookEdition;
    private javax.swing.JLabel addEBookLocation;
    private javax.swing.JTextField addEBookPublisher;
    private javax.swing.JComboBox<String> addEBookSubCat;
    private javax.swing.JLabel addEBookUplodeBookBtn;
    private javax.swing.JTextField addEBookWriter;
    private javax.swing.JTextField addStudentAadhar;
    private javax.swing.JTextArea addStudentAddress;
    private javax.swing.JLabel addStudentBtn;
    private javax.swing.JTextField addStudentContact;
    private javax.swing.JComboBox<String> addStudentCourseList;
    private javax.swing.JTextField addStudentEmail;
    private javax.swing.JTextField addStudentEnroll;
    private javax.swing.JTextField addStudentName;
    private javax.swing.JPanel addStudentPanel;
    private javax.swing.JLabel addStudentPhoto;
    private javax.swing.JLabel addStudentPhotoName;
    private javax.swing.JLabel addStudentRegisterBtn;
    private javax.swing.JPanel addStudentRegisterBtnPanel;
    private javax.swing.JLabel addStudentResetBtn;
    private javax.swing.JPanel addStudentResetBtnPanel;
    private javax.swing.JTextField addStudentSemester;
    private javax.swing.JComboBox<String> addStudentStreamList;
    private javax.swing.JPanel addStudentSubPanel;
    private javax.swing.JPanel adminListActionPanel;
    private javax.swing.JLabel adminListBtn;
    private javax.swing.JLabel adminListDeleteBtn;
    private javax.swing.JPanel adminListDeleteBtnPanel;
    private javax.swing.JPanel adminListPanel;
    private javax.swing.JDialog adminListProfileView;
    private javax.swing.JScrollPane adminListScrollPane;
    private javax.swing.JPanel adminListSubPanel;
    private javax.swing.JTable adminListTable;
    private javax.swing.JLabel adminListViewProfileBtn;
    private javax.swing.JPanel adminListViewProfileBtnPanel;
    private javax.swing.JLabel adminMenuBtn;
    private javax.swing.JLabel adminNameMain;
    private javax.swing.JLabel adminProfileAadhar;
    private javax.swing.JLabel adminProfileAddress;
    private javax.swing.JLabel adminProfileContact;
    private javax.swing.JLabel adminProfileEmail;
    private javax.swing.JLabel adminProfileName;
    private javax.swing.JLabel adminProfilePassword;
    private javax.swing.JLabel adminProfilePhoto;
    private javax.swing.JLabel adminProfileUsername;
    private javax.swing.JPanel adminSubMenuContainer;
    private javax.swing.JPanel adminSubMenuPanel;
    private javax.swing.JPanel adminUpdatePanel;
    private javax.swing.JPanel adminUpdateResultPanel;
    private javax.swing.JPanel adminUpdateResultSubPanel;
    private javax.swing.JPanel adminUpdateSubPanel;
    private javax.swing.JScrollPane attendanceRecordScrollPanel;
    private javax.swing.JTable attendanceRecordTable;
    private javax.swing.JPanel attendanceRecordTablePanel;
    private javax.swing.JPanel booRequestCancelPanel;
    private javax.swing.JLabel bookListBtn;
    private javax.swing.JLabel bookListDeleteBtn;
    private javax.swing.JPanel bookListDeletePanel;
    private javax.swing.JPanel bookListMainPanel;
    private javax.swing.JScrollPane bookListScrollPane;
    private javax.swing.JLabel bookListSearchBtn;
    private javax.swing.JTextField bookListSearchField;
    private javax.swing.JPanel bookListSearchPanel;
    private javax.swing.JPanel bookListSubPanel;
    private javax.swing.JTable bookListTable;
    private javax.swing.JLabel bookListUpdateBtn;
    private javax.swing.JPanel bookListUpdatePanel;
    private javax.swing.JLabel bookMenuBtn;
    private javax.swing.JLabel bookRequestAllowBtn;
    private javax.swing.JPanel bookRequestAllowPanel;
    private javax.swing.JLabel bookRequestCancelBtn;
    private javax.swing.JScrollPane bookRequestScrollPane;
    private javax.swing.JTable bookRequestTable;
    private javax.swing.JTextField bookStockAddStock;
    private javax.swing.JLabel bookStockBackBtn;
    private javax.swing.JPanel bookStockBackPanel;
    private javax.swing.JTextField bookStockBookId;
    private javax.swing.JTextField bookStockCat;
    private javax.swing.JTextField bookStockName;
    private javax.swing.JTextField bookStockPublisher;
    private javax.swing.JTextField bookStockSubCat;
    private javax.swing.JLabel bookStockUpdateStockBtn;
    private javax.swing.JPanel bookStockUpdateStockPanel;
    private javax.swing.JTextField bookStockWriter;
    private javax.swing.JPanel bookSubMenuContainer;
    private javax.swing.JPanel bookSubMenuPanel;
    private javax.swing.JPanel bookUpdateMainPanel;
    private javax.swing.JPanel bookUpdateSubPanel;
    private javax.swing.JLabel clearBookSearchBtn;
    private javax.swing.JLabel clearIssuedSearchBtn;
    private javax.swing.JLabel clearStudentSearchBtn;
    private javax.swing.JLabel contactDeveloperBtn;
    private javax.swing.JLabel dateTime;
    private javax.swing.JLabel eBookListBtn;
    private javax.swing.JPanel eBookListMainPanel;
    private javax.swing.JScrollPane eBookListScrollPane;
    private javax.swing.JPanel eBookListSubPanel;
    private javax.swing.JTable eBookListTable;
    private javax.swing.JPanel eBookMainPanel;
    private javax.swing.JPanel eBookSubPanel;
    private javax.swing.JLabel ebookListRemoveBtn;
    private javax.swing.JPanel ebookListRemovePanel;
    private javax.swing.JLabel helpMenuBtn;
    private javax.swing.JPanel helpSubMenuContainer;
    private javax.swing.JPanel helpSubMenuPanel;
    private javax.swing.JDialog issueBookDialogPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLayeredPane mainContainer;
    private javax.swing.JDesktopPane mainContentPanel;
    private javax.swing.JPanel mainMenuContainer;
    private javax.swing.JLabel menuCloseBtn;
    private javax.swing.JPanel menuCloseBtnPanel;
    private javax.swing.JFrame processingFrame;
    private javax.swing.JPanel requestBookIssueMainPanel;
    private javax.swing.JPanel requestBookIssueSubPanel;
    private javax.swing.JLabel requestListForBookIssueBtn;
    private javax.swing.JPanel searchAdminPanel;
    private javax.swing.JPanel searchAttendBtnPanel;
    private javax.swing.JLabel searchAttendanceBtn;
    private javax.swing.JTextField searchStudentTextField;
    private javax.swing.JLabel shortcutsBtn;
    private javax.swing.JLabel studenceAttendanceApproveBtn;
    private javax.swing.JPanel studenceAttendanceApprovePanel;
    private javax.swing.JPanel studenceAttendanceExitPanel;
    private javax.swing.JTable studenceAttendanceTable;
    private javax.swing.JPanel studentAttendanceMainPanel;
    private javax.swing.JPanel studentAttendanceRecordMainPanel;
    private javax.swing.JScrollPane studentAttendanceScrollPane;
    private javax.swing.JPanel studentAttendanceSubPanel;
    private javax.swing.JLabel studentAttendenceBtn;
    private javax.swing.JLabel studentListBtn;
    private javax.swing.JLabel studentListDeleteBtn;
    private javax.swing.JPanel studentListDeletePanel;
    private javax.swing.JPanel studentListMainPanel;
    private javax.swing.JScrollPane studentListScrollPane;
    private javax.swing.JLabel studentListSearchBtn;
    private javax.swing.JPanel studentListSearchPanel;
    private javax.swing.JTextField studentListSearchTextField;
    private javax.swing.JPanel studentListSubPanel;
    private javax.swing.JTable studentListTable;
    private javax.swing.JLabel studentMenuBtn;
    private javax.swing.JLabel studentResetBtn;
    private javax.swing.JPanel studentResetBtnPanel;
    private javax.swing.JLabel studentSearchBtn;
    private javax.swing.JPanel studentSearchBtnPanel;
    private javax.swing.JPanel studentSearchMainPanel;
    private javax.swing.JPanel studentSearchSubPanel;
    private javax.swing.JPanel studentSubMenuContainer;
    private javax.swing.JPanel studentSubMenuPanel;
    private javax.swing.JPanel studentUpdateResultPanel;
    private javax.swing.JPanel studentUpdateResultSubPanel;
    private javax.swing.JLayeredPane subMenuContainer;
    private javax.swing.JDesktopPane subMenuPanel;
    private javax.swing.JLabel tipOfTheDayBtn;
    private javax.swing.JPanel topBar;
    private javax.swing.JLabel trackBookListSearchBtn;
    private javax.swing.JTextField trackBookListSearchField;
    private javax.swing.JLabel trackIssuedBookBtn;
    private javax.swing.JPanel trackIssuedBookMainPanel;
    private javax.swing.JScrollPane trackIssuedBookScrollPane;
    private javax.swing.JPanel trackIssuedBookSubPanel;
    private javax.swing.JTable trackIssuedBookTable;
    private javax.swing.JLabel updateAdminBtn;
    private javax.swing.JLabel updateAdminResetBtn;
    private javax.swing.JPanel updateAdminResetBtnPanel;
    private javax.swing.JTextField updateAdminResultAadhar;
    private javax.swing.JTextArea updateAdminResultAddress;
    private javax.swing.JTextField updateAdminResultContact;
    private javax.swing.JTextField updateAdminResultEmail;
    private javax.swing.JTextField updateAdminResultName;
    private javax.swing.JTextField updateAdminResultPassword;
    private javax.swing.JLabel updateAdminResultPhoto;
    private javax.swing.JLabel updateAdminResultPhotoName;
    private javax.swing.JLabel updateAdminResultRegisterBtn;
    private javax.swing.JPanel updateAdminResultRegisterBtnPanel;
    private javax.swing.JLabel updateAdminResultResetBtn;
    private javax.swing.JPanel updateAdminResultResetBtnPanel;
    private javax.swing.JTextField updateAdminResultUsername;
    private javax.swing.JLabel updateAdminSearchBtn;
    private javax.swing.JPanel updateAdminSearchBtnPanel;
    private javax.swing.JTextField updateAdminSearchField;
    private javax.swing.JLayeredPane updatePanelTransaction;
    private javax.swing.JTextField updateStudentAadhar;
    private javax.swing.JTextArea updateStudentAddress;
    private javax.swing.JLabel updateStudentBtn;
    private javax.swing.JTextField updateStudentContact;
    private javax.swing.JComboBox<String> updateStudentCourseList;
    private javax.swing.JTextField updateStudentEmail;
    private javax.swing.JTextField updateStudentEnroll;
    private javax.swing.JTextField updateStudentName;
    private javax.swing.JLabel updateStudentPhoto;
    private javax.swing.JLabel updateStudentPhotoName;
    private javax.swing.JLabel updateStudentRegisterBtn;
    private javax.swing.JPanel updateStudentRegisterBtnPanel;
    private javax.swing.JLabel updateStudentResetBtn;
    private javax.swing.JPanel updateStudentResetBtnPanel;
    private javax.swing.JTextField updateStudentSemester;
    private javax.swing.JComboBox<String> updateStudentStreamList;
    private javax.swing.JLabel viewStudentAttendanceRecordBtn;
    // End of variables declaration//GEN-END:variables
}
