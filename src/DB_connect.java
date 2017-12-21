import java.sql.*;

public class DB_connect {

    // private static PreparedStatement pst = null;
    // private static ResultSet rs = null;

    // check DB exists. If not, create new DB
    public Connection DB_setup() { // When DB exists
        Connection con = null;

        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "ynwalfc7889";

//		System.out.println(con);

        // if musicapp DB exists
        try {
            String tmpurl = url + "musicapp?useSSL=false";
            con = DriverManager.getConnection(tmpurl, username, password);

            System.out.println(con);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Database does not exist. Gonna create new DB.");
        }

        System.out.println(con);

        // if musicapp DB does not exist
        if (con == null) {
            try {
                con = DriverManager.getConnection(url, username, password);
                Statement stmt = con.createStatement();
                String query = "create database musicapp";

                int tmp = stmt.executeUpdate(query);
                if (!(tmp == 1))
                    System.out.println("Create error in DB_connect.java");

                System.out.println("tmp : " + tmp);

                stmt.close();

                con.setCatalog("musicapp");

                System.out.println("done");

            } catch (Exception e) {
                // TODO Auto-generated catch block
                // System.out.println("Error occured in DB creation.");
                //e.printStackTrace();
            }
        }

        return con;
    }

    // check initial tables. If not exist, create new table.
    public void table_setup(Connection con) throws NullPointerException, SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Query_operations oper = new Query_operations();

        String query = "SELECT table_name FROM information_schema.tables WHERE table_schema = ? AND "
                + "table_name = ?";
        String query2 = null;

        pst = con.prepareStatement(query);
        pst.setString(1, "musicapp");

        query2 = "CREATE TABLE manager( " + "Name varchar(30) not null, " + "Sex char, " + "MgrStartDate date, "
                + "Mgr_ID int NOT NULL AUTO_INCREMENT, " + "Login_ID varchar(30) not null, "
                + "Password varchar(30) not null, " + "PRIMARY KEY(Mgr_ID), " + "UNIQUE(Login_ID)" + ")";
        oper.create_table(con, pst, rs, "manager", query2);

        query2 = "CREATE TABLE user( " + "Name varchar(30) not null, " + "Sex char, " + "JoinedDate date, "
                + "User_ID int NOT NULL AUTO_INCREMENT, " + "Login_ID varchar(30) not null, "
                + "Password varchar(30) not null, " + "PRIMARY KEY(User_ID), " + "UNIQUE(Login_ID)" + ")";
        oper.create_table(con, pst, rs, "user", query2);

        query2 = "CREATE TABLE artist( " + "Name varchar(30) not null, " + "Sex char, " + "DebutDate date, "
                + "Artist_ID int NOT NULL AUTO_INCREMENT, " + "PRIMARY KEY(Artist_ID), " + "UNIQUE(Name)" + ")";
        oper.create_table(con, pst, rs, "artist", query2);

        query2 = "CREATE TABLE album( " + "Title varchar(30) not null, " + "ReleasedDate date, "
                + "Album_ID int NOT NULL AUTO_INCREMENT, " + "Artist_ID int, " + "PRIMARY KEY(Album_ID), "
                + "FOREIGN KEY(Artist_ID) REFERENCES artist(Artist_ID) ON DELETE cascade ON UPDATE cascade" + ")";
        oper.create_table(con, pst, rs, "album", query2);

        query2 = "CREATE TABLE music( " + "Title varchar(30) not null, " + "Genre varchar(10), "
                + "Music_ID int NOT NULL AUTO_INCREMENT, " + "Album_ID int, " + "Artist_ID int, "
                + "PRIMARY KEY(Music_ID), "
                + "FOREIGN KEY(Album_ID) REFERENCES album(Album_ID) ON DELETE cascade ON UPDATE cascade, "
                + "FOREIGN KEY(Artist_ID) REFERENCES artist(Artist_ID) ON DELETE cascade ON UPDATE cascade"
                + ")";
        oper.create_table(con, pst, rs, "music", query2);

        query2 = "CREATE TABLE playlist( " + "Name varchar(30) not null, "
                + "Playlist_ID int NOT NULL AUTO_INCREMENT, " + "Owner_ID int, " + "PRIMARY KEY(Playlist_ID), "
                + "FOREIGN KEY(Owner_ID) REFERENCES user(User_ID) ON DELETE cascade ON UPDATE cascade" + ")";
        oper.create_table(con, pst, rs, "playlist", query2);

        query2 = "CREATE TABLE playlist_music( " + "Playlist_ID int NOT NULL, " + "Music_ID int NOT NULL, "
                + "PRIMARY KEY(Playlist_ID, Music_ID), "
                + "FOREIGN KEY(Playlist_ID) REFERENCES playlist(Playlist_ID) ON DELETE cascade ON UPDATE cascade, "
                + "FOREIGN KEY(Music_ID) REFERENCES music(Music_ID) ON DELETE cascade ON UPDATE cascade" + ")";
        oper.create_table(con, pst, rs, "playlist_music", query2);

        query2 = "CREATE TABLE favorite_artist( " + "User_ID int NOT NULL, " + "Artist_ID int NOT NULL, "
                + "PRIMARY KEY(User_ID, Artist_ID), "
                + "FOREIGN KEY(User_ID) REFERENCES user(User_ID) ON DELETE cascade ON UPDATE cascade, "
                + "FOREIGN KEY(Artist_ID) REFERENCES artist(Artist_ID) ON DELETE cascade ON UPDATE cascade" + ")";
        oper.create_table(con, pst, rs, "favorite_artist", query2);

        pst.close();
    }

    public void data_ex(Connection con) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Query_operations oper = new Query_operations();

        String query_check;
        String query_insert;
//		String query_sub;
        PreparedStatement pst_check;

        // Manager insert
        query_check = "SELECT * FROM manager WHERE Name = ? AND Login_ID = ?";
        pst_check = con.prepareStatement(query_check);
        query_insert = "INSERT INTO manager(Name, Sex, MgrStartDate, Login_ID, Password) VALUES(?, ?, '2007-01-01', ?, ?)";
        oper.insert_person(con, pst_check, rs, query_insert, "Manager1", "M", "admin", "1234");
        oper.insert_person(con, pst_check, rs, query_insert, "Manager2", "F", "admin2", "1234");
        oper.insert_person(con, pst_check, rs, query_insert, "Manager3", "M", "admin3", "1234");

        // User insert
        query_check = "SELECT * FROM user WHERE Name = ? AND Login_ID = ?";
        pst_check = con.prepareStatement(query_check);
        query_insert = "INSERT INTO user(Name, Sex, JoinedDate, Login_ID, Password) VALUES(?, ?, '2010-01-01', ?, ?)";
        oper.insert_person(con, pst_check, rs, query_insert, "User1", "M", "user1", "1234");
        oper.insert_person(con, pst_check, rs, query_insert, "User2", "M", "user2", "1234");
        oper.insert_person(con, pst_check, rs, query_insert, "User3", "F", "user3", "1234");
        oper.insert_person(con, pst_check, rs, query_insert, "User4", "F", "user4", "1234");

        // Artist insert
        query_check = "SELECT * FROM artist WHERE Name = ?";
        pst_check = con.prepareStatement(query_check);
        query_insert = "INSERT INTO artist(Name, Sex, DebutDate) VALUES(?, ?, ?)";
        oper.insert_artist(con, pst_check, rs, query_insert, "IU", "F", "2008-09-18");
        oper.insert_artist(con, pst_check, rs, query_insert, "Red Velvet", "G", "2014-08-01");
        oper.insert_artist(con, pst_check, rs, query_insert, "Charlie Puth", "M", "2015-02-26");
        oper.insert_artist(con, pst_check, rs, query_insert, "TWICE", "G", "2015-10-20");
        oper.insert_artist(con, pst_check, rs, query_insert, "Adele", "F", "2008-01-20");
        oper.insert_artist(con, pst_check, rs, query_insert, "Maroon 5", "G", "2003-09-06");





        // Album insert
        query_check = "SELECT * FROM album WHERE Title = ? AND Artist_ID = ?";
        pst_check = con.prepareStatement(query_check);
        query_insert = "INSERT INTO album(Title, releasedDate, Artist_ID) VALUES (?, ?, ?)";
        oper.insert_album(con, pst_check, rs, query_insert, "Real", "2010-12-09", "IU");
        oper.insert_album(con, pst_check, rs, query_insert, "Modern Times", "2013-10-08", "IU");
        oper.insert_album(con, pst_check, rs, query_insert, "CHAT-SHIRE", "2015-10-23", "IU");
        oper.insert_album(con, pst_check, rs, query_insert, "Happiness", "2014-08-04", "Red Velvet");
        oper.insert_album(con, pst_check, rs, query_insert, "The Red", "2015-09-09", "Red Velvet");
        oper.insert_album(con, pst_check, rs, query_insert, "Rookie", "2017-02-01", "Red Velvet");
        oper.insert_album(con, pst_check, rs, query_insert, "Some Type of Love", "2015-05-06", "Charlie Puth");
        oper.insert_album(con, pst_check, rs, query_insert, "Nine Track Mind", "2016-11-11", "Charlie Puth");
        oper.insert_album(con, pst_check, rs, query_insert,  "PAGE TWO", "2016-04-25", "TWICE");
        oper.insert_album(con, pst_check, rs, query_insert,  "TWICEcoaster : LANE 1", "2016-10-24", "TWICE");
        oper.insert_album(con, pst_check, rs, query_insert,  "twicetagram", "2017-10-30", "TWICE");
        oper.insert_album(con, pst_check, rs, query_insert,  "19", "2008-01-27", "Adele");
        oper.insert_album(con, pst_check, rs, query_insert,  "21", "2011-01-21", "Adele");
        oper.insert_album(con, pst_check, rs, query_insert,  "25", "2015-11-20", "Adele");
        oper.insert_album(con, pst_check, rs, query_insert, "Songs About Jane", "2003-09-06", "Maroon 5");
        oper.insert_album(con, pst_check, rs, query_insert, "It Won't Be Soon Before Long", "2007-05-22", "Maroon 5");
        oper.insert_album(con, pst_check, rs, query_insert, "Hands All Over", "2011-07-14", "Maroon 5");
        oper.insert_album(con, pst_check, rs, query_insert, "Overexposed", "2012-06-25", "Maroon 5");





        // Music insert
        query_check = "SELECT * FROM music WHERE Title = ? AND Album_ID = ? AND Artist_ID = ?";
        pst_check = con.prepareStatement(query_check);
        query_insert = "INSERT INTO music(Title, Genre, Album_ID, Artist_ID) VALUES (?, ?, ?, ?)";
        oper.insert_music(con, pst_check, rs, query_insert, "Good Day", "Dance", "Real", "IU");
        oper.insert_music(con, pst_check, rs, query_insert, "Voice Mail", "Ballad", "Modern Times", "IU");
        oper.insert_music(con, pst_check, rs, query_insert, "23", "Ballad", "CHAT-SHIRE", "IU");
        oper.insert_music(con, pst_check, rs, query_insert, "Happiness", "Dance", "Happiness", "Red Velvet");
        oper.insert_music(con, pst_check, rs, query_insert, "Dumb Dumb", "Dance", "The Red", "Red Velvet");
        oper.insert_music(con, pst_check, rs, query_insert, "Rookie", "Dance", "Rookie", "Red Velvet");
        oper.insert_music(con, pst_check, rs, query_insert, "Marvin Gaye", "R&B", "Some Type of Love", "Charlie Puth");
        oper.insert_music(con, pst_check, rs, query_insert, "Some Type of Love", "R&B", "Some Type of Love", "Charlie Puth");
        oper.insert_music(con, pst_check, rs, query_insert, "One Call Away", "Pop", "Nine Track Mind", "Charlie Puth");
        oper.insert_music(con, pst_check, rs, query_insert, "We Don't Talk Anymore", "Pop", "Nine Track Mind", "Charlie Puth");
        oper.insert_music(con, pst_check, rs, query_insert, "River", "Pop", "Nine Track Mind", "Charlie Puth");
        oper.insert_music(con, pst_check, rs, query_insert, "CHEER UP", "Dance", "PAGE TWO", "TWICE");
        oper.insert_music(con, pst_check, rs, query_insert, "Touchdown", "Pop", "PAGE TWO", "TWICE");
        oper.insert_music(con, pst_check, rs, query_insert, "TT", "Dance", "TWICEcoaster : LANE 1", "TWICE");
        oper.insert_music(con, pst_check, rs, query_insert, "JELLY JELLY", "Dance", "TWICEcoaster : LANE 1", "TWICE");
        oper.insert_music(con, pst_check, rs, query_insert, "LIKEY", "Dance", "twicetagram", "TWICE");
        oper.insert_music(con, pst_check, rs, query_insert, "MISSING U", "Dance", "twicetagram", "TWICE");
        oper.insert_music(con, pst_check, rs, query_insert, "Chasing Pavements", "Ballad", "19", "Adele");
        oper.insert_music(con, pst_check, rs, query_insert, "Rolling In The Deep", "Ballad", "21", "Adele");
        oper.insert_music(con, pst_check, rs, query_insert, "Set Fire To The Rain", "Ballad", "21", "Adele");
        oper.insert_music(con, pst_check, rs, query_insert, "Someone Like You", "Ballad", "21", "Adele");
        oper.insert_music(con, pst_check, rs, query_insert, "Hello", "Ballad", "25", "Adele");
        oper.insert_music(con, pst_check, rs, query_insert, "When We Were Young", "Ballad", "25", "Adele");
        oper.insert_music(con, pst_check, rs, query_insert, "This Love", "Rock", "Songs About Jane", "Maroon 5");
        oper.insert_music(con, pst_check, rs, query_insert, "She Will Be Loved", "Rock", "Songs About Jane", "Maroon 5");
        oper.insert_music(con, pst_check, rs, query_insert, "Sunday Morning", "Rock", "Songs About Jane", "Maroon 5");
        oper.insert_music(con, pst_check, rs, query_insert, "Makes Me Wonder", "Rock", "It Won't Be Soon Before Long", "Maroon 5");
        oper.insert_music(con, pst_check, rs, query_insert, "Wake Up Call", "Rock", "It Won't Be Soon Before Long", "Maroon 5");
        oper.insert_music(con, pst_check, rs, query_insert, "Misery", "Rock", "Hands All Over", "Maroon 5");
        oper.insert_music(con, pst_check, rs, query_insert, "Give A Little More", "Rock", "Hands All Over", "Maroon 5");
        oper.insert_music(con, pst_check, rs, query_insert, "Stutter", "Rock", "Hands All Over", "Maroon 5");
        oper.insert_music(con, pst_check, rs, query_insert, "Moves Like Jagger", "Rock", "Hands All Over", "Maroon 5");
        oper.insert_music(con, pst_check, rs, query_insert, "One More Night", "Rock", "Overexposed", "Maroon 5");
        oper.insert_music(con, pst_check, rs, query_insert, "Payphone", "Rock", "Overexposed", "Maroon 5");
        oper.insert_music(con, pst_check, rs, query_insert, "Lucky Strike", "Rock", "Overexposed", "Maroon 5");




        // Create Playlist
        query_check = "SELECT * FROM playlist WHERE Name = ? AND Owner_ID = ?";
        pst_check = con.prepareStatement(query_check);
        query_insert = "INSERT INTO playlist(Name, Owner_ID) VALUES (?, ?)";
        oper.make_playlist(con, pst_check, rs, query_insert, "Playlist_1_A", "user1");
        oper.make_playlist(con, pst_check, rs, query_insert, "Playlist_1_B", "user1");
        oper.make_playlist(con, pst_check, rs, query_insert, "Playlist_2_A", "user2");
        oper.make_playlist(con, pst_check, rs, query_insert, "Playlist_2_B", "user2");
        oper.make_playlist(con, pst_check, rs, query_insert, "Playlist_3_A", "user3");
        oper.make_playlist(con, pst_check, rs, query_insert, "Playlist_4_A", "user4");


        // Add music to playlist
        query_check = "SELECT * FROM playlist_music WHERE Playlist_ID = ? AND Music_ID = ?";
        pst_check = con.prepareStatement(query_check);
        query_insert = "INSERT INTO playlist_music(Playlist_ID, Music_ID) VALUES (?, ?)";
        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_1_A", 1, "Dumb Dumb", "Red Velvet");
        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_1_A", 1, "Voice Mail", "IU");
        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_1_A", 1, "TT", "TWICE");
        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_1_A", 1, "LIKEY", "TWICE");
        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_1_A", 1, "Hello", "Adele");
        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_1_A", 1, "Rolling In The Deep", "Adele");
        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_1_A", 1, "Set Fire To The Rain", "Adele");


        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_1_B", 1, "Rookie", "Red Velvet");
        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_1_B", 1, "Dumb Dumb", "Red Velvet");
        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_1_B", 1, "Stutter", "Maroon 5");
        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_1_B", 1, "Moves Like Jagger", "Maroon 5");

        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_2_A", 2, "Rookie", "Red Velvet");
        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_2_A", 2, "Moves Like Jagger", "Maroon 5");

        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_2_B", 2, "Rookie", "Red Velvet");
        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_2_B", 2, "River", "Charlie Puth");
        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_2_B", 2, "Moves Like Jagger", "Maroon 5");

        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_3_A", 3, "Moves Like Jagger", "Maroon 5");

        oper.add_to_playlist(con, pst_check, rs, query_insert, "Playlist_4_A", 4, "Moves Like Jagger", "Maroon 5");





        // Add favorite artist
        query_check = "SELECT * FROM favorite_artist WHERE User_ID = ? AND Artist_ID = ?";
        pst_check = con.prepareStatement(query_check);
        query_insert = "INSERT INTO favorite_artist(User_ID, Artist_ID) VALUES (?, ?)";
        oper.fav_artist(con, pst_check, rs, query_insert, "user1", "IU");
        oper.fav_artist(con, pst_check, rs, query_insert, "user1", "Red Velvet");
        oper.fav_artist(con, pst_check, rs, query_insert, "user1", "Adele");
        oper.fav_artist(con, pst_check, rs, query_insert, "user1", "Maroon 5");
        oper.fav_artist(con, pst_check, rs, query_insert, "user1", "Charlie Puth");
        oper.fav_artist(con, pst_check, rs, query_insert, "user1", "TWICE");

        oper.fav_artist(con, pst_check, rs, query_insert, "user2", "Red Velvet");
        oper.fav_artist(con, pst_check, rs, query_insert, "user2", "Charlie Puth");
        oper.fav_artist(con, pst_check, rs, query_insert, "user2", "Adele");
        oper.fav_artist(con, pst_check, rs, query_insert, "user2", "Maroon 5");
        oper.fav_artist(con, pst_check, rs, query_insert, "user2", "TWICE");

        oper.fav_artist(con, pst_check, rs, query_insert, "user3", "Charlie Puth");
        oper.fav_artist(con, pst_check, rs, query_insert, "user3", "Adele");
        oper.fav_artist(con, pst_check, rs, query_insert, "user3", "Maroon 5");
        oper.fav_artist(con, pst_check, rs, query_insert, "user3", "Red Velvet");

        oper.fav_artist(con, pst_check, rs, query_insert, "user4", "Maroon 5");

        // ---------------------------------------------------------------

        pst_check.close();
//		pst.close();
    }
}
