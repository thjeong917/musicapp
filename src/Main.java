import java.sql.*;
import java.util.Scanner;

public class Main {
    // private static Statement st = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;
    private static Connection con = null;
    private static Query_operations oper = new Query_operations();
    private static String curMgrID = null;
    private static String curUsrID = null;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        // Database setup
        DB_connect dbc = new DB_connect();
        con = dbc.DB_setup();

        // Table setup
        System.out.println("Time to check tables");

//        try {
//            dbc.table_setup(con);
//        } catch (NullPointerException e) {
//            // TODO: handle exception
//            System.out.println("DB is not connected. Cannot check tables");
//        } catch (SQLException e) {
//            System.out.println("Query Error.");
////            //e.printStackTrace();
//        }
//
//        // INSERT example data
//        try {
//            dbc.data_ex(con);
//        } catch (Exception e) {
//            // TODO: handle exception
//            //e.printStackTrace();
//        }

        System.out.println(con);

        // -----------------------------------------------------------
        // Interface
        // -----------------------------------------------------------
        System.out.println();

        String main = ".##......##.########.##........######...#######..##.....##.########....########..#######.....##.....##.##.....##..######..####..######.....###....########..########.\n"
                + ".##..##..##.##.......##.......##....##.##.....##.###...###.##.............##....##.....##....###...###.##.....##.##....##..##..##....##...##.##...##.....##.##.....##\n"
                + ".##..##..##.##.......##.......##.......##.....##.####.####.##.............##....##.....##....####.####.##.....##.##........##..##........##...##..##.....##.##.....##\n"
                + ".##..##..##.######...##.......##.......##.....##.##.###.##.######.........##....##.....##....##.###.##.##.....##..######...##..##.......##.....##.########..########.\n"
                + ".##..##..##.##.......##.......##.......##.....##.##.....##.##.............##....##.....##....##.....##.##.....##.......##..##..##.......#########.##........##.......\n"
                + ".##..##..##.##.......##.......##....##.##.....##.##.....##.##.............##....##.....##....##.....##.##.....##.##....##..##..##....##.##.....##.##........##.......\n"
                + "..###..###..########.########..######...#######..##.....##.########.......##.....#######.....##.....##..#######...######..####..######..##.....##.##........##.......\n";

        System.out.println(   main  );

        // System.out.println("Welcome to MusicApp!!");

        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println();
            System.out.println(  "<Mode select>"  );
            System.out.println("1. Manager");
            System.out.println("2. User");
            System.out.println("3. Register");
            System.out.println("0. Exit");

            System.out.print(    "Input" +  " : ");
            String input = sc.nextLine();
            System.out.println();

            boolean login = false;
            boolean esc = false;

            // 1. Manager
            if (input.equals("1")) {
                while (true) {
                    if (esc) {
                        break;
                    }
                    // login
                    if (!login) {
                        System.out.println(  "<Log in>"  );
                        System.out.print("ID : ");
                        String id = sc.nextLine();
                        System.out.print("PW : ");
                        String pw = sc.nextLine();

                        String query = "SELECT * FROM manager WHERE Login_ID = ? AND Password = ?";
                        try {
                            pst = con.prepareStatement(query);
                            pst.setString(1, id);
                            pst.setString(2, pw);
                            rs = pst.executeQuery();
                            if (!rs.next()) {
                                System.out.println( "ID or Password is wrong. Please check again."  );
                                break;
                            } else {
                                System.out.println( rs.getString("Name") + " Permission Granted."  );
                                curMgrID = id;
                                login = true;
                            }
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
//                            //e.printStackTrace();
                        }
                    }
                    // -----------------------------------------------------------
                    // MANAGER MODE
                    // -----------------------------------------------------------
                    System.out.println();
                    System.out.println(  "[MANAGER MODE]"  );
                    System.out.println("Select Database to control.");
                    System.out.println("1. Music");
                    System.out.println("2. Album");
                    System.out.println("3. Artist");
                    System.out.println("4. User");
                    System.out.println("5. Manager");
                    System.out.println("0. Log out");
                    System.out.print(    "Input" +  " : ");
                    input = sc.nextLine();

                    if (input.equals("1")) { // Music
                        while (true) {
                            System.out.println();
                            System.out.println(  "[MANAGER MODE]"  );
                            System.out.println("Select command to execute.");
                            System.out.println("1. Show Music");
                            System.out.println("2. Insert Music");
                            System.out.println("3. Delete Music");
                            System.out.println("4. Modify Music");
                            System.out.println("0. Back to previous menu");
                            System.out.print(    "Input" +  " : ");
                            input = sc.nextLine();
                            System.out.println();

                            if (input.equals("1")) { // SELECT * FROM music
                                String query = "SELECT music.Title, Genre, album.Title as Album, artist.Name as Artist FROM music "
                                        + "JOIN album ON music.Album_ID = album.Album_ID "
                                        + "JOIN artist ON album.Artist_ID = artist.Artist_ID";
                                try {
                                    pst = con.prepareStatement(query);
                                    oper.show_music(con, pst, rs);
                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
//                                    //e.printStackTrace();
                                    System.out.println("Query Error in MANAGER-MU");
                                }

                            } else if (input.equals("2")) { // INSERT INTO music
                                String query_check = "SELECT * FROM music WHERE Title = ? AND Album_ID = ? AND Artist_ID = ?";
                                String query_insert = "INSERT INTO music(Title, Genre, Album_ID, Artist_ID) VALUES (?, ?, ?, ?)";
                                System.out.println(  "[INSERT Music]"  );
                                System.out.print("Title : ");
                                String title = sc.nextLine();
                                System.out.print("Genre : ");
                                String genre = sc.nextLine();
                                System.out.print("Album : ");
                                String album = sc.nextLine();
                                System.out.print("Artist : ");
                                String artist = sc.nextLine();

                                try {
                                    pst = con.prepareStatement(query_check);
                                    oper.insert_music(con, pst, rs, query_insert, title, genre, album, artist);
                                } catch (SQLException e) {
                                    // TODO: handle exception
                                    //e.printStackTrace();
                                }

                            } else if (input.equals("3")) { // DELETE FROM music
                                String query_delete = "DELETE FROM music WHERE Music_ID = ?";
                                System.out.println(  "[DELETE Music]"  );
                                System.out.print("Title : ");
                                String title = sc.nextLine();
                                System.out.print("Artist : ");
                                String artist = sc.nextLine();

                                try {
                                    oper.delete_music(con, query_delete, title, artist);
                                } catch (SQLException e) {
                                    // TODO: handle exception
                                    //e.printStackTrace();
                                }

                            } else if (input.equals("4")) { // UPDATE SET
                                // String query_check = "SELECT * FROM music
                                // WHERE Title = ? AND Artist_ID = ?";
                                String query_update = "UPDATE music SET ";
                                int mid = -1;

                                while (true) {
                                    System.out.println(  "[UPDATE Music]"  );
                                    System.out.println("Select Music to update");
                                    System.out.print("Title : ");
                                    String title = sc.nextLine();
                                    System.out.print("Artist : ");
                                    String artist = sc.nextLine();
                                    System.out.println();

                                    try {
                                        mid = oper.get_musicID(title, artist, con);
                                        if (mid == -1) {
                                            System.out.println( "ERROR : Music does not exist."  );
                                            break;
                                        }

                                    } catch (SQLException e) {
                                        // TODO Auto-generated catch block
                                        //e.printStackTrace();
                                    }

                                    System.out.println("Select attribute to update");
                                    System.out.println("1. Title");
                                    System.out.println("2. Genre");
                                    System.out.println("3. Album");
                                    System.out.println("0. Back to previous menu");
                                    System.out.print(    "Input" +  " : ");
                                    input = sc.nextLine();
                                    System.out.println();

                                    if (input.equals("1")) {
                                        System.out.println("Input new title to update");
                                        System.out.print("Title : ");
                                        String newtitle = sc.nextLine();
                                        System.out.println();
                                        query_update += "Title = ? WHERE Music_ID = ?";

                                        try {
                                            oper.update_music(con, pst, query_update, newtitle, mid);
                                        } catch (Exception e) {
                                            // TODO Auto-generated catch block
                                            //e.printStackTrace();
                                        }

                                    } else if (input.equals("2")) {
                                        System.out.println("Input new genre to update");
                                        System.out.print("Genre : ");
                                        String newtitle = sc.nextLine();
                                        System.out.println();
                                        query_update += "Genre = ? WHERE Music_ID = ?";

                                        try {
                                            oper.update_music(con, pst, query_update, newtitle, mid);
                                        } catch (Exception e) {
                                            // TODO Auto-generated catch block
                                            //e.printStackTrace();
                                        }

                                    } else if (input.equals("3")) { // update
                                        // album_id
                                        // in music
                                        System.out.println("Input new album name & artist name to update");
                                        System.out.print("Album : ");
                                        String newalbum = sc.nextLine();
                                        System.out.print("Artist : ");
                                        String newartist = sc.nextLine();
                                        query_update += "Album_ID = ? WHERE Music_ID = ?";

                                        try {
                                            int albumid = oper.get_albumID(newalbum, newartist, con);
                                            int artid = oper.get_artistID(newartist, con);
                                            oper.update_II(con, pst, query_update, albumid, mid);
                                            oper.sync_album_artist(con, pst);

                                        } catch (SQLException e) {
                                            // TODO Auto-generated catch block
                                            //e.printStackTrace();
                                        }

                                    } else if (input.equals("0")) {
                                        System.out.println("Back to previous menu....");
                                        System.out.println();
                                        break;
                                    } else {
                                        System.out.println( "Wrong input!!! Try again."  );
                                    }
                                    break;
                                }

                            } else if (input.equals("0")) {
                                System.out.println("Back to previous menu....");
                                System.out.println();
                                break;
                            } else {
                                System.out.println( "Wrong input!!! Try again."  );
                            }
                        }
                    } else if (input.equals("2")) { // Album
                        while (true) {
                            System.out.println();
                            System.out.println(  "[MANAGER MODE]"  );
                            System.out.println("Select command to execute.");
                            System.out.println("1. Show Album");
                            System.out.println("2. Insert Album");
                            System.out.println("3. Delete Album");
                            System.out.println("4. Modify Album");
                            System.out.println("0. Back to previous menu");
                            System.out.print(    "Input" +  " : ");
                            input = sc.nextLine();
                            System.out.println();

                            if (input.equals("1")) { // SELECT * FROM album
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------");
                                System.out.println(String.format("%-30s%-30s%-30s%s", "| Title", "| Released Date",
                                        "| Artist", "|"));
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------");

                                String query = "SELECT Title, ReleasedDate, artist.name as Artist from album "
                                        + "JOIN artist ON album.artist_ID = artist.artist_ID";
                                try {
                                    pst = con.prepareStatement(query);
                                    rs = pst.executeQuery();
                                    while (rs.next()) {
                                        String title = rs.getString(1);
                                        String date = rs.getString(2);
                                        String artist = rs.getString(3);

                                        System.out.println(String.format("%s%-28s%s%-28s%s%-28s%s", "| ", title, "| ",
                                                date, "| ", artist, "|"));
                                    }
                                    System.out.println(
                                            "-------------------------------------------------------------------------------------------");

                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("2")) { // INSERT INTO album
                                String query_check = "SELECT * FROM album WHERE Title = ? AND Artist_ID = ?";
                                String query_insert = "INSERT INTO album(Title, ReleasedDate, Artist_ID) VALUES (?, ?, ?)";
                                System.out.println(  "[INSERT Album]"  );
                                System.out.print("Title : ");
                                String title = sc.nextLine();
                                System.out.print("Released Date : ");
                                String date = sc.nextLine();
                                System.out.print("Artist : ");
                                String artist = sc.nextLine();

                                try {
                                    pst = con.prepareStatement(query_check);
                                    oper.insert_album(con, pst, rs, query_insert, title, date, artist);
                                } catch (SQLException e) {
                                    // TODO: handle exception
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("3")) { // DELETE FROM album
                                String query_delete = "DELETE FROM album WHERE Album_ID = ?";
                                System.out.println(  "[DELETE Album]"  );
                                System.out.print("Title : ");
                                String title = sc.nextLine();
                                System.out.print("Artist : ");
                                String artist = sc.nextLine();

                                try {
                                    oper.delete_album(con, query_delete, title, artist);
                                } catch (SQLException e) {
                                    // TODO: handle exception
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("4")) { // UPDATE album
                                // String query_check = "SELECT * FROM album
                                // WHERE Title = ? AND Artist_ID = ?";
                                String query_update = "UPDATE album SET ";
                                int mid = -1;

                                while (true) {
                                    System.out.println(  "[UPDATE Album]"  );
                                    System.out.println("Select Album to update");
                                    System.out.print("Title : ");
                                    String title = sc.nextLine();
                                    System.out.print("Artist : ");
                                    String artist = sc.nextLine();
                                    System.out.println();

                                    try {
                                        mid = oper.get_albumID(title, artist, con);
                                        if (mid == -1) {
                                            System.out.println( "ERROR : Album does not exist."  );
                                            System.out.println();
                                            break;
                                        }

                                    } catch (SQLException e) {
                                        // TODO Auto-generated catch block
                                        //e.printStackTrace();
                                    }

                                    System.out.println("Select attribute to update");
                                    System.out.println("1. Title");
                                    System.out.println("2. Released Date");
                                    System.out.println("3. Artist");
                                    System.out.println("0. Back to previous menu");
                                    System.out.print(    "Input" +  " : ");
                                    input = sc.nextLine();
                                    System.out.println();

                                    if (input.equals("1")) {
                                        System.out.println("Input new title to update");
                                        System.out.print("Title : ");
                                        String newtitle = sc.nextLine();
                                        System.out.println();
                                        query_update += "Title = ? WHERE Album_ID = ?";

                                        try {
                                            oper.update_music(con, pst, query_update, newtitle, mid);
                                        } catch (Exception e) {
                                            // TODO Auto-generated catch block
                                            //e.printStackTrace();
                                        }

                                    } else if (input.equals("2")) {
                                        System.out.println("Input new date to update");
                                        System.out.print("Released Date : ");
                                        String newtitle = sc.nextLine();
                                        System.out.println();
                                        query_update += "ReleasedDate = ? WHERE Album_ID = ?";

                                        try {
                                            oper.update_music(con, pst, query_update, newtitle, mid);
                                        } catch (SQLException e) {
                                            // TODO Auto-generated catch block
                                            System.out.println( "Wrong Date format!!"  );
                                        } catch (Exception e) {
                                            // TODO: handle exception
                                            System.out.println( "Wrong Date format!!"  );
                                        }

                                    } else if (input.equals("3")) {
                                        System.out.println("Input new artist to update");
                                        System.out.print("Artist name: ");
                                        String newtitle = sc.nextLine();
                                        System.out.println();
                                        query_update += "Artist_ID = ? WHERE Album_ID = ?";

                                        try {
                                            int artid = oper.get_artistID(newtitle, con);
                                            oper.update_II(con, pst, query_update, artid, mid);
                                            oper.sync_album_artist(con, pst);
                                        } catch (Exception e) {
                                            // TODO: handle exception
                                        }

                                    } else if (input.equals("0")) {
                                        System.out.println("Back to previous menu....");
                                        System.out.println();
                                        break;
                                    } else {
                                        System.out.println( "Wrong input!!! Try again."  );
                                    }
                                    break;
                                }

                            } else if (input.equals("0")) {
                                System.out.println("Back to previous menu....");
                                System.out.println();
                                break;
                            } else {
                                System.out.println( "Wrong input!!! Try again."  );
                            }
                        }
                    } else if (input.equals("3")) { // Artist
                        while (true) {
                            System.out.println();
                            System.out.println(  "[MANAGER MODE]"  );
                            System.out.println("Select command to execute.");
                            System.out.println("1. Show Artist");
                            System.out.println("2. Insert Artist");
                            System.out.println("3. Delete Artist");
                            System.out.println("4. Modify Artist");
                            System.out.println("0. Back to previous menu");
                            System.out.print(    "Input" +  " : ");
                            input = sc.nextLine();
                            System.out.println();

                            if (input.equals("1")) { // SELECT * FROM artist
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------");
                                System.out
                                        .println(String.format("%-30s%-30s%-30s%s", "| Name", "| Sex", "| Debut", "|"));
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------");

                                String query = "SELECT Name, Sex, DebutDate from artist";
                                try {
                                    pst = con.prepareStatement(query);
                                    rs = pst.executeQuery();
                                    while (rs.next()) {
                                        String name = rs.getString(1);
                                        String sex = rs.getString(2);
                                        String date = rs.getString(3);

                                        System.out.println(String.format("%s%-28s%s%-28s%s%-28s%s", "| ", name, "| ",
                                                sex, "| ", date, "|"));
                                    }
                                    System.out.println(
                                            "-------------------------------------------------------------------------------------------");
                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("2")) { // INSERT INTO
                                // artist
                                String query_check = "SELECT * FROM artist WHERE Name = ?";
                                String query_insert = "INSERT INTO artist(Name, Sex, DebutDate) VALUES (?, ?, ?)";
                                System.out.println(  "[INSERT Artist]"  );
                                System.out.print("Name : ");
                                String name = sc.nextLine();
                                System.out.print("Sex : ");
                                String sex = sc.nextLine();
                                System.out.print("Debut Date : ");
                                String debut = sc.nextLine();

                                try {
                                    pst = con.prepareStatement(query_check);
                                    oper.insert_artist(con, pst, rs, query_insert, name, sex, debut);
                                } catch (SQLException e) {
                                    // TODO: handle exception
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("3")) { // DELETE FROM
                                // artist
                                String query_delete = "DELETE FROM artist WHERE Name = ?";
                                System.out.println(  "[DELETE Artist]"  );
                                System.out.print("Name : ");
                                String name = sc.nextLine();

                                try {
                                    oper.delete_artist(con, query_delete, name);
                                } catch (SQLException e) {
                                    // TODO: handle exception
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("4")) { // UPDATE artist
                                String query_update = "UPDATE artist SET ";
                                int mid = -1;

                                while (true) {
                                    System.out.println(  "[UPDATE Artist]"  );
                                    System.out.println("Select Artist to update");
                                    System.out.print("Name : ");
                                    String name = sc.nextLine();
                                    System.out.println();

                                    try {
                                        mid = oper.get_artistID(name, con);
                                        if (mid == -1) {
                                            System.out.println( "ERROR : Artist does not exist."  );
                                            System.out.println();
                                            break;
                                        }

                                    } catch (SQLException e) {
                                        // TODO Auto-generated catch block
                                        //e.printStackTrace();
                                    }

                                    System.out.println("Select attribute to update");
                                    System.out.println("1. Name");
                                    System.out.println("2. Sex");
                                    System.out.println("3. Debut Date");
                                    System.out.println("0. Back to previous menu");
                                    System.out.print(    "Input" +  " : ");
                                    input = sc.nextLine();
                                    System.out.println();

                                    if (input.equals("1")) {
                                        System.out.println("Input new name to update");
                                        System.out.print("Name : ");
                                        String newtitle = sc.nextLine();
                                        System.out.println();
                                        query_update += "Name = ? WHERE Artist_ID = ?";

                                        try {
                                            oper.update_music(con, pst, query_update, newtitle, mid);
                                        } catch (Exception e) {
                                            // TODO Auto-generated catch block
                                            //e.printStackTrace();
                                        }

                                    } else if (input.equals("2")) {
                                        System.out.println("Input new Sex to update");
                                        System.out.print("Sex : ");
                                        String newtitle = sc.nextLine();
                                        System.out.println();
                                        query_update += "Sex = ? WHERE Artist_ID = ?";

                                        try {
                                            oper.update_music(con, pst, query_update, newtitle, mid);
                                        } catch (SQLException e) {
                                            // TODO Auto-generated catch block
                                            //e.printStackTrace();
                                        }

                                    } else if (input.equals("3")) {
                                        System.out.println("Input new debut date to update");
                                        System.out.print("Debut Date : ");
                                        String newtitle = sc.nextLine();
                                        System.out.println();
                                        query_update += "DebutDate = ? WHERE Artist_ID = ?";

                                        try {
                                            oper.update_music(con, pst, query_update, newtitle, mid);
                                        } catch (SQLException e) {
                                            // TODO Auto-generated catch block
                                            //e.printStackTrace();
                                        }

                                    } else if (input.equals("0")) {
                                        System.out.println("Back to previous menu....");
                                        System.out.println();
                                        break;
                                    } else {
                                        System.out.println( "Wrong input!!! Try again."  );
                                    }
                                    break;
                                }

                            } else if (input.equals("0")) {
                                System.out.println("Back to previous menu....");
                                System.out.println();
                                break;
                            } else {
                                System.out.println( "Wrong input!!! Try again."  );
                            }
                        }
                    } else if (input.equals("4")) { // User
                        while (true) {
                            System.out.println();
                            System.out.println(  "[MANAGER MODE]"  );
                            System.out.println("Select command to execute.");
                            System.out.println("1. Show User");
                            System.out.println("2. Delete User");
                            System.out.println("0. Back to previous menu");
                            System.out.print(    "Input" +  " : ");
                            input = sc.nextLine();
                            System.out.println();

                            if (input.equals("1")) { // SELECT * FROM user
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println(String.format("%-30s%-30s%-30s%-30s%-30s%s", "| Name", "| Sex",
                                        "| Joined Date", "| ID", "| Password", "|"));
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------------------------------------------------------------------");

                                String query = "SELECT Name, Sex, JoinedDate, Login_ID, Password from user";
                                try {
                                    pst = con.prepareStatement(query);
                                    rs = pst.executeQuery();
                                    while (rs.next()) {
                                        String name = rs.getString(1);
                                        String sex = rs.getString(2);
                                        String join = rs.getString(3);
                                        String login_id = rs.getString(4);
                                        String pw = rs.getString(5);

                                        System.out.println(String.format("%s%-28s%s%-28s%s%-28s%s%-28s%s%-28s%s", "| ",
                                                name, "| ", sex, "| ", join, "| ", login_id, "| ", pw, "|"));
                                    }
                                    System.out.println(
                                            "-------------------------------------------------------------------------------------------------------------------------------------------------------");

                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("2")) { // DELETE FROM user
                                String query_delete = "DELETE FROM user WHERE Login_ID = ?";
                                System.out.println(  "[DELETE User]"  );
                                System.out.print("ID : ");
                                String name = sc.nextLine();

                                try {
                                    oper.delete_user(con, query_delete, name);
                                } catch (SQLException e) {
                                    // TODO: handle exception
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("0")) {
                                System.out.println("Back to previous menu....");
                                System.out.println();
                                break;
                            } else {
                                System.out.println( "Wrong input!!! Try again."  );
                            }
                        }

                    } else if (input.equals("5")) { // Manager
                        while (true) {
                            System.out.println();
                            System.out.println(  "[MANAGER MODE]"  );
                            System.out.println("Select command to execute.");
                            System.out.println("1. Show Manager");
                            System.out.println("2. Add Manager");
                            System.out.println("3. Modify Manager Info");
                            System.out.println("4. Resign Manager");
                            System.out.println("0. Back to previous menu");
                            System.out.print(    "Input" +  " : ");
                            input = sc.nextLine();
                            System.out.println();

                            if (input.equals("1")) { // SELECT * FROM manager
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println(String.format("%-30s%-30s%-30s%-30s%-30s%s", "| Name", "| Sex",
                                        "| Manager Start Date", "| ID", "| Password", "|"));
                                System.out.println(
                                        "-------------------------------------------------------------------------------------------------------------------------------------------------------");
                                String query = "SELECT Name, Sex, MgrStartDate, Login_ID, Password from manager";
                                try {
                                    pst = con.prepareStatement(query);
                                    rs = pst.executeQuery();
                                    while (rs.next()) {
                                        String name = rs.getString(1);
                                        String sex = rs.getString(2);
                                        String join = rs.getString(3);
                                        String login_id = rs.getString(4);
                                        String pw = rs.getString(5);

                                        System.out.println(String.format("%s%-28s%s%-28s%s%-28s%s%-28s%s%-28s%s", "| ",
                                                name, "| ", sex, "| ", join, "| ", login_id, "| ", pw, "|"));
                                    }
                                    System.out.println(
                                            "-------------------------------------------------------------------------------------------------------------------------------------------------------");

                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("2")) { // INSERT INTO
                                // manager
                                String query_check = "SELECT * FROM manager WHERE Name = ? AND Login_ID = ?";
                                String query_insert = "INSERT INTO manager(Name, Sex, MgrStartDate, Login_ID, Password) VALUES (?, ?, now(), ?, ?)";
                                System.out.println(  "[ADD Manager]"  );
                                System.out.print("ID : ");
                                String login_id = sc.nextLine();
                                System.out.print("Password : ");
                                String pw = sc.nextLine();
                                System.out.print("Name : ");
                                String name = sc.nextLine();
                                System.out.print("Sex : ");
                                String sex = sc.nextLine();

                                try {
                                    pst = con.prepareStatement(query_check);
                                    oper.insert_person(con, pst, rs, query_insert, name, sex, login_id, pw);
                                } catch (SQLException e) {
                                    // TODO: handle exception
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("3")) { // UPDATE manager
                                String query_update = "UPDATE manager SET ";

                                while (true) {
                                    System.out.println(  "[UPDATE Manager Information]"  );
                                    System.out.println("Select attribute to update");
                                    System.out.println("1. Name");
                                    System.out.println("2. Sex");
                                    System.out.println("3. Password");
                                    System.out.println("0. Back to previous menu");
                                    System.out.print(    "Input" +  " : ");
                                    input = sc.nextLine();
                                    System.out.println();

                                    if (input.equals("1")) {
                                        System.out.println("Input new name to update");
                                        System.out.print("Name : ");
                                        String newtitle = sc.nextLine();
                                        System.out.println();
                                        query_update += "Name = ? WHERE Login_ID = ?";
                                        try {
                                            oper.update_SS(con, pst, query_update, newtitle, curMgrID);
                                        } catch (Exception e) {
                                            // TODO Auto-generated catch block
                                            //e.printStackTrace();
                                        }

                                    } else if (input.equals("2")) {
                                        System.out.println("Input new Sex to update");
                                        System.out.print("Sex : ");
                                        String newtitle = sc.nextLine();
                                        System.out.println();
                                        query_update += "Sex = ? WHERE Login_ID = ?";
                                        try {
                                            oper.update_SS(con, pst, query_update, newtitle, curMgrID);
                                        } catch (SQLException e) {
                                            // TODO Auto-generated catch block
                                            //e.printStackTrace();
                                        }

                                    } else if (input.equals("3")) {
                                        System.out.println("Input new password to update");
                                        System.out.print("password : ");
                                        String newtitle = sc.nextLine();
                                        System.out.println();
                                        query_update += "Password = ? WHERE Login_ID = ?";

                                        try {
                                            oper.update_SS(con, pst, query_update, newtitle, curMgrID);
                                        } catch (SQLException e) {
                                            // TODO Auto-generated catch block
                                            //e.printStackTrace();
                                        }

                                    } else if (input.equals("0")) {
                                        System.out.println("Back to previous menu....");
                                        System.out.println();
                                        break;
                                    } else {
                                        System.out.println( "Wrong input!!! Try again."  );
                                    }
//                                    break;
                                }

                            } else if (input.equals("4")) { // DELETE FROM
                                // manager -> Resign
                                // myself
                                System.out.println(  "[RESIGN Manager]"  );
                                System.out.println( "WARNING : This command deletes your information as manager."  );
                                System.out.println("Input \"RESIGN\" if you're sure with this.");
                                System.out.println("If you're not, please input anything else.");
                                System.out.print(    "Input" +  " : ");
                                String tmp = sc.nextLine();
                                if (tmp.equals("RESIGN")) {
                                    String query = "DELETE FROM manager WHERE Login_ID = ?";
                                    try {
                                        pst = con.prepareStatement(query);
                                        pst.setString(1, curMgrID);
                                        pst.executeUpdate();
                                        System.out.println( "MANAGER RESIGNED"  );
                                    } catch (SQLException e) {
                                        // TODO Auto-generated catch block
                                        //e.printStackTrace();
                                    }
                                    esc = true;
                                    break;
                                }
                            } else if (input.equals("0")) {
                                System.out.println("Back to previous menu....");
                                System.out.println();
                                break;
                            } else {
                                System.out.println( "Wrong input!!! Try again."  );
                            }

                        }
                    } else if (input.equals("0")) {
                        System.out.println("Back to previous menu....");
                        break;
                    } else {
                        System.out.println( "Wrong input!!! Try again."  );
                    }

                }

            }
            // -----------------------------------------------------------
            // USER MODE
            // -----------------------------------------------------------
            else if (input.equals("2")) {
                while (true) {
                    if (esc) {
                        break;
                    }
                    // login
                    if (!login) {
                        System.out.println( "<Log in>"  );
                        System.out.print("ID : ");
                        String id = sc.nextLine();
                        System.out.print("PW : ");
                        String pw = sc.nextLine();

                        String query = "SELECT * FROM user WHERE Login_ID = ? AND Password = ?";
                        try {
                            pst = con.prepareStatement(query);
                            pst.setString(1, id);
                            pst.setString(2, pw);
                            rs = pst.executeQuery();
                            if (!rs.next()) {
                                System.out.println( "ID or Password is wrong. Please check again."  );
                                break;
                            } else {
                                System.out.println( rs.getString("Name") + " Permission Granted."  );
                                System.out.println();
                                curUsrID = id;
                                login = true;
                            }
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            //e.printStackTrace();
                        }
                    }

                    System.out.println( "[USER MODE]"  );
                    System.out.println("1. Search Music by Title");
                    System.out.println("2. Search Music by Album");
                    System.out.println("3. Search Music by Artist");
                    System.out.println("4. Show Playlists");
                    System.out.println("5. Show Favorite Artists");
                    System.out.println("6. Modify User Information");
                    System.out.println("7. Show TOP 10");
                    System.out.println("8. Show Newly Released");
                    System.out.println("9. Delete User Account");
                    System.out.println("0. Log out");

                    System.out.print(    "Input" +  " : ");

                    input = sc.nextLine();
                    System.out.println();

                    if (input.equals("1")) { // Search by Title
                        System.out.println( "[USER MODE]"  );
                        System.out.println("Input music title to search");
                        System.out.print("Title : ");
                        String search = sc.nextLine();
                        System.out.println();

                        String query = "SELECT music.Title, Genre, album.Title as Album, artist.Name as Artist FROM music "
                                + "JOIN album ON music.Album_ID = album.Album_ID "
                                + "JOIN artist ON album.Artist_ID = artist.Artist_ID " + "WHERE music.Title = ?";
                        try {
                            pst = con.prepareStatement(query);
                            pst.setString(1, search);
                            oper.show_music(con, pst, rs);
                            System.out.println();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            //e.printStackTrace();
                        }

                    } else if (input.equals("2")) { // Search by Album
                        System.out.println( "[USER MODE]"  );
                        System.out.println("Input album title to search");
                        System.out.print("Album : ");
                        String search = sc.nextLine();
                        System.out.println();

                        String query = "SELECT music.Title, Genre, album.Title as Album, artist.Name as Artist FROM music "
                                + "JOIN album ON music.Album_ID = album.Album_ID "
                                + "JOIN artist ON album.Artist_ID = artist.Artist_ID " + "WHERE album.Title = ?";
                        try {
                            pst = con.prepareStatement(query);
                            pst.setString(1, search);
                            oper.show_music(con, pst, rs);
                            System.out.println();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            //e.printStackTrace();
                        }

                    } else if (input.equals("3")) { // Search by Artist
                        System.out.println( "[USER MODE]"  );
                        System.out.println("Input artist name to search");
                        System.out.print("Artist : ");
                        String search = sc.nextLine();
                        System.out.println();

                        String query = "SELECT music.Title, Genre, album.Title as Album, artist.Name as Artist FROM music "
                                + "JOIN album ON music.Album_ID = album.Album_ID "
                                + "JOIN artist ON album.Artist_ID = artist.Artist_ID " + "WHERE artist.Name = ?";
                        try {
                            pst = con.prepareStatement(query);
                            pst.setString(1, search);
                            oper.show_music(con, pst, rs);
                            System.out.println();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            //e.printStackTrace();
                        }

                    } else if (input.equals("4")) { // Show playlists
                        while (true) {
                            String query = "SELECT Name as Playlist FROM playlist WHERE Owner_ID = ?";
                            try {
                                int tmpid = oper.get_userID(curUsrID, con);
                                if (tmpid == -1) {
                                    System.out.println("ERROR : User does not exist.");
                                    continue;
                                }
                                pst = con.prepareStatement(query);
                                pst.setInt(1, tmpid);
                                rs = pst.executeQuery();
                                System.out.println( "<PLAYLISTS>"  );
                                System.out.println("-------------------------------");
                                System.out.println(String.format("%-30s%s", "| Playlist", "|"));
                                System.out.println("-------------------------------");

                                while (rs.next()) {
                                    String pl = rs.getString(1);
                                    System.out.println(String.format("%s%-28s%s", "| ", pl, "|"));
                                }
                                System.out.println("-------------------------------");
                                System.out.println();

                            } catch (Exception e) {
                                // TODO: handle exception
                                //e.printStackTrace();
                            }

                            System.out.println( "[USER MODE] - Playlist"  );
                            System.out.println("1. Select Playlist");
                            System.out.println("2. Create Playlist");
                            System.out.println("3. Delete Playlist");
                            System.out.println("0. Back to previous menu");
                            System.out.print(    "Input" +  " : ");

                            input = sc.nextLine();
                            System.out.println();

                            if (input.equals("1")) { // SELECT playlist - ADD or
                                // DELETE Music

                                System.out.println( "[USER MODE] - Playlist"  );
                                System.out.println("Input playlist name to use");
                                System.out.print("Name : ");
                                String pl = sc.nextLine();
                                System.out.println();
                                int tmpid = -1;
                                int curPLid = -1;
                                int musicid = -1;
                                int albumid = -1;
                                int artid = -1;

                                String query_pl = "select music.Title as Title, album.Title as Album, artist.Name as Artist, playlist.Name as Playlist from music " +
                                        "JOIN album on music.Album_ID = album.Album_ID " +
                                        "JOIN artist ON music.Artist_ID = artist.Artist_ID " +
                                        "JOIN playlist_music ON music.Music_ID = playlist_music.Music_ID " +
                                        "JOIN playlist ON playlist_music.Playlist_ID = playlist.Playlist_ID " +
                                        "WHERE playlist_music.Playlist_ID = ?;";

                                try {
                                    tmpid = oper.get_userID(curUsrID, con);
                                    if (tmpid == -1) {
                                        System.out.println( "ERROR : User does not exist."  );
                                        continue;
                                    }
                                    curPLid = oper.get_playlistID(pl, tmpid, con);
                                    if (curPLid == -1) {
                                        System.out.println( "ERROR : Playlist does not exist."  );
                                        System.out.println();
                                        continue;
                                    }

                                    pst = con.prepareStatement(query_pl);
                                    pst.setInt(1, curPLid);
                                    rs = pst.executeQuery();
                                    oper.show_pl_music(con, pst, rs);
                                    System.out.println();

                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                } // to use playlist_id

                                while (true) {
                                    System.out.println( "[USER MODE] - Playlist"  );
                                    System.out.println("1. Add Music to Playlist");
                                    System.out.println("2. Delete Music from Playlist");
                                    System.out.println("0. Back to previous menu");
                                    System.out.print(    "Input" +  " : ");

                                    input = sc.nextLine();
                                    System.out.println();

                                    if (input.equals("1")) {
                                        System.out.println( "[USER MODE] - Playlist"  );
                                        System.out.println("Input music title & artist to add");
                                        System.out.print("Title : ");
                                        String mname = sc.nextLine();
                                        System.out.print("Artist : ");
                                        String aname = sc.nextLine();
                                        System.out.println();
                                        String query_check = "SELECT * FROM playlist_music WHERE Playlist_ID = ? AND Music_ID = ?";
                                        String query_add = "INSERT INTO playlist_music(Playlist_ID, Music_ID) VALUES (?, ?)";

                                        try {
                                            // int mid = oper.get_musicID(mname,
                                            // aname, con);
                                            // if(mid == -1) {
                                            // System.out.println("ERROR : Music
                                            // does not exist.");
                                            // System.out.println();
                                            // continue;
                                            // }
                                            // pst =
                                            // con.prepareStatement(query_add);
                                            // pst.setInt(1, curPLid);
                                            // pst.setInt(2, mid);
                                            // pst.executeUpdate();
                                            // System.out.println("[" + mname +
                                            // "] INSERT DONE");
                                            int id = oper.get_userID(curUsrID, con);
                                            if (id == -1) {
                                                System.out.println("ERROR : User does not exist.");
                                                return;
                                            }
                                            pst = con.prepareStatement(query_check);
                                            oper.add_to_playlist(con, pst, rs, query_add, pl, id, mname, aname);

                                        } catch (SQLException e) {
                                            // TODO Auto-generated catch block
                                            System.out.println("Music already exists.\n");
                                        }

                                    } else if (input.equals("2")) {
                                        System.out.println( "[USER MODE] - Playlist"  );
                                        System.out.println("Input music title & artist to delete");
                                        System.out.print("Title : ");
                                        String mname = sc.nextLine();
                                        System.out.print("Artist : ");
                                        String aname = sc.nextLine();
                                        System.out.println();
                                        String query_delete = "DELETE FROM playlist_music WHERE Playlist_ID = ? AND Music_ID = ? ";

                                        try {
                                            int mid = oper.get_musicID(mname, aname, con);
                                            if (mid == -1) {
                                                System.out.println( "ERROR : Music does not exist."  );
                                                System.out.println();
                                                continue;
                                            }
                                            pst = con.prepareStatement(query_delete);
                                            pst.setInt(1, curPLid);
                                            pst.setInt(2, mid);
                                            pst.executeUpdate();
                                            System.out.println( "[" + mname + "] DELETE DONE\n"  );

                                        } catch (SQLException e) {
                                            // TODO Auto-generated catch block
                                            System.out.println( "Music is not in playlist.\n"  );
                                        }
                                    } else if (input.equals("0")) {
                                        System.out.println("Back to previous menu....");
                                        System.out.println();
                                        break;
                                    } else {
                                        System.out.println( "Wrong input!!! Try again."  );
                                        System.out.println();
                                    }
                                }

                            } else if (input.equals("2")) { // CREATE Playlist
                                System.out.println( "[USER MODE] - Playlist"  );
                                System.out.println("Input playlist name to create");
                                System.out.print("Name : ");
                                String name = sc.nextLine();
                                System.out.println();
                                String query_check = "SELECT * FROM playlist WHERE Name = ? AND Owner_ID = ?";
                                String query_pl = "INSERT INTO playlist(Name, Owner_ID) VALUES(?, ?)";

                                try {
                                    pst = con.prepareStatement(query_check);
                                    // oper.insert_playlist(con, pst, rs,
                                    // query_pl, name, curUsrID);
                                    oper.make_playlist(con, pst, rs, query_pl, name, curUsrID);
                                    System.out.println();
                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("3")) { // DELETE Playlist
                                System.out.println( "[USER MODE] - Playlist"  );
                                System.out.println("Input playlist name to delete");
                                System.out.print("Name : ");
                                String name = sc.nextLine();
                                System.out.println();
                                String query_check = "SELECT * FROM playlist WHERE Name = ? AND Owner_ID = ?";
                                String query_pl = "DELETE FROM playlist WHERE Name = ? AND Owner_ID = ?";

                                try {
                                    pst = con.prepareStatement(query_check);
                                    oper.delete_playlist(con, pst, rs, query_pl, name, curUsrID);

                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                }

                            } else if (input.equals("0")) {
                                System.out.println("Back to previous menu....");
                                System.out.println();
                                break;
                            } else {
                                System.out.println( "Wrong input!!! Try again."  );
                                System.out.println();
                            }
                        }

                    } else if (input.equals("5")) { // Show favorite artists
                        while (true) {
                            String query = "SELECT artist.Name FROM favorite_artist, artist "
                                    + "WHERE favorite_artist.Artist_ID = artist.Artist_ID AND User_ID = ?";
                            try {
                                int tmpid = oper.get_userID(curUsrID, con);
                                if (tmpid == -1) {
                                    System.out.println( "ERROR : User does not exist."  );
                                    continue;
                                }
                                pst = con.prepareStatement(query);
                                pst.setInt(1, tmpid);
                                rs = pst.executeQuery();
                                System.out.println( "<FAVORITE ARTISTS>"  );
                                System.out.println("-------------------------------");
                                System.out.println(String.format("%-30s%s", "| Artist", "|"));
                                System.out.println("-------------------------------");

                                while (rs.next()) {
                                    String pl = rs.getString(1);
                                    System.out.println(String.format("%s%-28s%s", "| ", pl, "|"));
                                }
                                System.out.println("-------------------------------");
                                System.out.println();

                            } catch (Exception e) {
                                // TODO: handle exception
                                //e.printStackTrace();
                            }

                            System.out.println( "[USER MODE] - Favorite Artist"  );
                            System.out.println("1. Add Artist");
                            System.out.println("2. Delete Artist");
                            System.out.println("0. Back to previous menu");
                            System.out.print(    "Input" +  " : ");

                            input = sc.nextLine();
                            System.out.println();

                            if (input.equals("1")) {
                                System.out.println( "[USER MODE] - Favorite Artist"  );
                                System.out.println("Input artist name to add");
                                System.out.print("Artist : ");
                                String aname = sc.nextLine();
                                System.out.println();

                                String query_check = "SELECT * FROM favorite_artist WHERE User_ID = ? AND Artist_ID = ?";
                                String query_insert = "INSERT INTO favorite_artist(User_ID, Artist_ID) VALUES (?, ?)";

                                try {
                                    pst = con.prepareStatement(query_check);
                                    oper.fav_artist(con, pst, rs, query_insert, curUsrID, aname);
                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                }

                            } else if (input.equals("2")) {
                                System.out.println( "[USER MODE] - Favorite Artist"  );
                                System.out.println("Input artist name to delete");
                                System.out.print("Artist : ");
                                String aname = sc.nextLine();
                                System.out.println();

                                String query_check = "SELECT * FROM favorite_artist WHERE User_ID = ? AND Artist_ID = ?";
                                String query_delete = "DELETE FROM favorite_artist WHERE User_ID = ? AND Artist_ID = ?";

                                try {
                                    pst = con.prepareStatement(query_check);
                                    oper.fav_delete(con, pst, rs, query_delete, curUsrID, aname);
                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("0")) {
                                System.out.println("Back to previous menu....");
                                System.out.println();
                                break;
                            } else {
                                System.out.println( "Wrong input!!! Try again."  );
                                System.out.println();
                                continue;
                            }

                        }

                    } else if (input.equals("6")) { // Modify User Info

                        String query_update = "UPDATE user SET ";

                        while (true) {
                            System.out.println( "[UPDATE User Information]"  );
                            System.out.println("Select attribute to update");
                            System.out.println("1. Name");
                            System.out.println("2. Sex");
                            System.out.println("3. Password");
                            System.out.println("0. Back to previous menu");
                            System.out.print(    "Input" +  " : ");

                            input = sc.nextLine();
                            System.out.println();

                            if (input.equals("1")) {
                                System.out.println("Input new name to update");
                                System.out.print("Name : ");
                                String newtitle = sc.nextLine();
                                System.out.println();
                                query_update += "Name = ? WHERE Login_ID = ?";
                                try {
                                    oper.update_SS(con, pst, query_update, newtitle, curUsrID);
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                }

                            } else if (input.equals("2")) {
                                System.out.println("Input new Sex to update");
                                System.out.print("Sex : ");
                                String newtitle = sc.nextLine();
                                System.out.println();
                                query_update += "Sex = ? WHERE Login_ID = ?";
                                try {
                                    oper.update_SS(con, pst, query_update, newtitle, curUsrID);
                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                }

                            } else if (input.equals("3")) {
                                System.out.println("Input new password to update");
                                System.out.print("password : ");
                                String newtitle = sc.nextLine();
                                System.out.println();
                                query_update += "Password = ? WHERE Login_ID = ?";

                                try {
                                    oper.update_SS(con, pst, query_update, newtitle, curUsrID);
                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                }

                            } else if (input.equals("0")) {
                                System.out.println("Back to previous menu....");
                                System.out.println();
                                break;
                            } else {
                                System.out.println( "Wrong input!!! Try again."  );
                                continue;
                            }
//                            break;
                        }

                    } else if (input.equals("7")) { // Show Ranks
                        while (true) {
                            System.out.println( "[Ranking]"  );
                            System.out.println("Select TOP10 to see");
                            System.out.println("1. Music");
                            System.out.println("2. Artist");
                            System.out.println("0. Back to previous menu");
                            System.out.print(    "Input" +  " : ");

                            input = sc.nextLine();
                            System.out.println();

                            if (input.equals("1")) {
                                try {
                                    System.out.println( "<MUSIC TOP10>"  );
                                    oper.pop_music(con, pst, rs);
                                    System.out.println();
                                } catch (SQLException e) {
                                    // TODO: handle exception
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("2")) {
                                try {
                                    System.out.println( "<ARTIST TOP10"  );
                                    oper.pop_artist(con, pst, rs);
                                    System.out.println();
                                } catch (SQLException e) {
                                    // TODO: handle exception
                                    //e.printStackTrace();
                                }
                            } else if (input.equals("0")) {
                                System.out.println("Back to previous menu....");
                                System.out.println();
                                break;
                            } else {
                                System.out.println( "Wrong input!!! Try again."  );
                                continue;
                            }
//                            break;
                        }

                    } else if (input.equals("8")) { // Newly Released
//                        String query = "SELECT music.Title AS Music, album.Title AS Album, artist.Name AS Artist, album.ReleasedDate "
//                                + "FROM music, album, artist WHERE music.Album_ID=album.Album_ID AND album.Artist_ID=artist.Artist_ID "
//                                + "ORDER BY album.ReleasedDate DESC LIMIT 10";
                        try {
                            System.out.println( "<NEWLY RELEASED>"  );
                            oper.newRelease(con, pst, rs);
                            System.out.println();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            //e.printStackTrace();
                        }

                    } else if (input.equals("9")) { // Delete Account
                        System.out.println(  "[DELETE Account]"  );
                        System.out.println("WARNING : This command deletes your user information as a member.");
                        System.out.println("Input \"DEACTIVATE\" if you're sure with this.");
                        System.out.println("If you're not, please input anything else.");
                        System.out.print(    "Input" +  " : ");
                        String tmp = sc.nextLine();
                        if (tmp.equals("DEACTIVATE")) {
                            String query = "DELETE FROM user WHERE Login_ID = ?";
                            try {
                                pst = con.prepareStatement(query);
                                pst.setString(1, curUsrID);
                                pst.executeUpdate();
                                System.out.println( "USER ACCOUNT DELETED"  );
                            } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                //e.printStackTrace();
                            }
                            esc = true;
                            break;
                        } else
                            System.out.println();
                    } else if (input.equals("0")) {
                        System.out.println("Back to previous menu....");
                        break;
                    } else {
                        System.out.println( "Wrong input!!! Try again.\n"  );
                    }
                }

            }
            // -----------------------------------------------------------
            // REGISTER MODE (for user)
            // -----------------------------------------------------------
            else if (input.equals("3")) {
                System.out.println( "<User Register>"  );
                System.out.print("ID : ");
                String id = sc.nextLine();
                System.out.print("PW : ");
                String pw = sc.nextLine();

                String query = "SELECT * FROM user WHERE Login_ID = ?";
                try {
                    pst = con.prepareStatement(query);
                    pst.setString(1, id);
                    rs = pst.executeQuery();
                    if (!rs.next()) { // register procedure
                        System.out.println("Input your information.");
                        System.out.print("Name : ");
                        String name = sc.nextLine();
                        System.out.print("Sex : ");
                        String sex = sc.nextLine();

                        String query2 = "INSERT INTO user(Name, Sex, JoinedDate, Login_ID, Password) VALUES(?, ?, now(), ?, ?)";
                        PreparedStatement pst2 = con.prepareStatement(query2);
                        pst2.setString(1, name);
                        pst2.setString(2, sex);
                        pst2.setString(3, id);
                        pst2.setString(4, pw);
                        pst2.executeUpdate();

                        System.out.println( id + " register succeeded."  );

                    } else { // id already exists
                        System.out.println("ID exists. Try again.");
                    }
                    // oper.usr_reg(con, pst, rs, query, id, pw);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                }

            } else if (input.equals("0")) {
                System.out.println("Exit program. Good Bye!!");
                break;
            } else {
                System.out.println( "Wrong input!!! Try again."  );
            }
        }
        sc.close();
    }
}