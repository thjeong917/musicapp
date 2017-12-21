import java.sql.*;

public class Query_operations {

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

    public void create_table(Connection con, PreparedStatement pst, ResultSet rs, String tname, String query) throws SQLException {
        pst.setString(2, tname);
        rs = pst.executeQuery();
        if (!rs.next()) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            System.out.println("Table [" + tname + "] created.");
        } else {
            String name = rs.getString("table_name");
            System.out.println("[" + name + "] OK");
        }

        rs.close();
    }

    public int get_artistID(String name, Connection con) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "SELECT artist_ID FROM artist WHERE Name = ?";

        pst = con.prepareStatement(query);
        pst.setString(1, name);
        rs = pst.executeQuery();

        if (!rs.next()) {
            rs.close();
            pst.close();
            return -1;
        }

        int id = rs.getInt(1);
        rs.close();
        pst.close();
        return id;
    }

    public int get_albumID(String name, String artist, Connection con) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "SELECT album_ID FROM album WHERE Title = ? AND Artist_ID = ?";

        int artist_id = get_artistID(artist, con);
        if (artist_id == -1) {
            System.out.println(  "ERROR : Artist does not exist."   );
            return -1;
        }

        pst = con.prepareStatement(query);
        pst.setString(1, name);
        pst.setInt(2, artist_id);
        rs = pst.executeQuery();

        if (!rs.next()) {
            rs.close();
            pst.close();
            return -1;
        }

        int id = rs.getInt(1);
        rs.close();
        pst.close();
        return id;
    }

    public int get_musicID(String name, String artist, Connection con) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "SELECT Music_ID FROM music WHERE Title = ? AND Artist_ID = ?";

        int artist_id = get_artistID(artist, con);
        if (artist_id == -1) {
            System.out.println(  "ERROR : Artist does not exist."   );
            return -1;
        }

        pst = con.prepareStatement(query);
        pst.setString(1, name);
        pst.setInt(2, artist_id);
        rs = pst.executeQuery();

        if (!rs.next()) {
            rs.close();
            pst.close();
            return -1;
        }

        int id = rs.getInt(1);
        rs.close();
        pst.close();
        return id;
    }

    public int get_userID(String name, Connection con) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "SELECT User_ID FROM user WHERE Login_ID = ?";

        pst = con.prepareStatement(query);
        pst.setString(1, name);
        rs = pst.executeQuery();

        if (!rs.next()) {
            rs.close();
            pst.close();
            return -1;
        }

        int id = rs.getInt(1);
        rs.close();
        pst.close();
        return id;
    }

    public int get_playlistID(String name, int usrid, Connection con) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String query = "SELECT Playlist_ID FROM playlist WHERE Name = ? AND Owner_ID = ?";

        pst = con.prepareStatement(query);
        pst.setString(1, name);
        pst.setInt(2, usrid);
        rs = pst.executeQuery();

        if (!rs.next()) {
            rs.close();
            pst.close();
            return -1;
        }

        int id = rs.getInt(1);
        rs.close();
        pst.close();
        return id;
    }

    public int get_managerID(String name) {
        return 0;
    }

//	public void insert_data(Connection con, PreparedStatement pst_check, ResultSet rs, String query_insert, String s1) throws SQLException {
//		PreparedStatement pst;
//
//		pst_check.setString(1, s1);
//		rs = pst_check.executeQuery();
//		if (!rs.next()) {
//			pst = con.prepareStatement(query_insert);
//			pst.executeUpdate(); // when success, return 1
//			System.out.println(s1 + " INSERT DONE");
//		} else {
//			String name = rs.getString("Name");
//			System.out.println(name + " exists");
//		}
//	}
//
//	public void insert_data(Connection con, PreparedStatement pst_check, ResultSet rs, String query_insert, String s1, String s2) throws SQLException {
//		PreparedStatement pst;
//
//		pst_check.setString(1, s1);
//		pst_check.setString(2, s2);
//		rs = pst_check.executeQuery();
//		if (!rs.next()) {
//			pst = con.prepareStatement(query_insert);
//			pst.executeUpdate(); // when success, return 1
//			System.out.println(s1 + " QUERY SUCCESS");
//		} else {
//			String name = rs.getString("Name");
//			System.out.println(name + " exists");
//		}
//	}

    public void insert_person(Connection con, PreparedStatement pst_check, ResultSet rs, String query_insert, String name, String sex, String login, String pw) throws SQLException {
        PreparedStatement pst;

        pst_check.setString(1, name);
        pst_check.setString(2, login);
        rs = pst_check.executeQuery();

        if (!rs.next()) {
            pst = con.prepareStatement(query_insert);
            pst.setString(1, name);
            pst.setString(2, sex);
            pst.setString(3, login);
            pst.setString(4, pw);
            pst.executeUpdate(); // when success, return 1
            System.out.println(  "[" + name + "] INSERT DONE"   );
        } else {
            System.out.println(  "[" + name + "] exists"   );
        }
    }

    public void insert_artist(Connection con, PreparedStatement pst_check, ResultSet rs, String query_insert, String name, String sex, String date) throws SQLException {
        PreparedStatement pst;

        pst_check.setString(1, name);
        rs = pst_check.executeQuery();

        if (!rs.next()) {
            pst = con.prepareStatement(query_insert);
            pst.setString(1, name);
            pst.setString(2, sex);
            pst.setString(3, date);
            pst.executeUpdate(); // when success, return 1
            System.out.println(  "[" + name + "] INSERT DONE"   );
        } else {
            System.out.println(  "[" + name + "] exists"   );
        }
    }

    public void insert_album(Connection con, PreparedStatement pst_check, ResultSet rs, String query_insert, String title, String date, String artist) throws SQLException {
        PreparedStatement pst;

        int id = get_artistID(artist, con);
        if (id == -1) {
            System.out.println(  "ERROR : Artist does not exist."   );
            return;
        }

        pst_check.setString(1, title);
        pst_check.setInt(2, id);

        rs = pst_check.executeQuery();
        if (!rs.next()) {
//			int id = get_artistID(fk, con);
//			if(id == -1){
//				System.out.println("Foreign key does not exist.");
//				return;
//			}
            pst = con.prepareStatement(query_insert);
            pst.setString(1, title);
            pst.setString(2, date);
            pst.setInt(3, id);
            pst.executeUpdate(); // when success, return 1
            System.out.println(  "[" + title + "] INSERT DONE"   );
        } else {
            System.out.println(  "[" + title + "] exists"   );
        }
    }

//	public void insert_playlist(Connection con, PreparedStatement pst_check, ResultSet rs, String query_insert, String pname, String usr) throws SQLException {
//		PreparedStatement pst;
//
//		int id = get_userID(usr, con);
//		if(id == -1){
//			System.out.println("ERROR : User does not exist.");
//			return;
//		}
//
//		pst_check.setString(1, pname);
//		pst_check.setInt(2, id);
//
//		rs = pst_check.executeQuery();
//		if (!rs.next()) {
//			pst = con.prepareStatement(query_insert);
//			pst.setString(1, pname);
//			pst.setInt(2, id);
//			pst.executeUpdate(); // when success, return 1
//			System.out.println("[" + pname + "] CREATED\n");
//		} else {
//			System.out.println("[" + pname + "] exists\n");
//		}
//	}

    public void insert_music(Connection con, PreparedStatement pst_check, ResultSet rs, String query_insert, String title, String Genre, String album, String artist) throws SQLException {
        PreparedStatement pst;

        int id = get_artistID(artist, con);
        if (id == -1) {
            System.out.println(  "ERROR : Artist does not exist."   );
            return;
        }

        int id2 = get_albumID(album, artist, con);
        if (id2 == -1) {
            System.out.println(  "ERROR : Album does not exist."   );
            return;
        }

        pst_check.setString(1, title);
        pst_check.setInt(2, id2);
        pst_check.setInt(3, id);

        rs = pst_check.executeQuery();
        if (!rs.next()) {
            pst = con.prepareStatement(query_insert);
            pst.setString(1, title);
            pst.setString(2, Genre);
            pst.setInt(3, id2);
            pst.setInt(4, id);
            pst.executeUpdate(); // when success, return 1
            System.out.println(  "[" + title + "] INSERT DONE"   );
        } else {
            System.out.println(  "[" + title + "] exists"   );
        }
    }

    public void update_music(Connection con, PreparedStatement pst, String query, String newtitle, int mid) throws SQLException {
        pst = con.prepareStatement(query);
        pst.setString(1, newtitle);
        pst.setInt(2, mid);
        pst.executeUpdate();
        System.out.println(  "MODIFIED TO [" + newtitle + "]"   );
        System.out.println();
    }

    public void update_SS(Connection con, PreparedStatement pst, String query, String newtitle, String curid) throws SQLException {
        pst = con.prepareStatement(query);
        pst.setString(1, newtitle);
        pst.setString(2, curid);
        pst.executeUpdate();
        System.out.println(  "MODIFIED TO [" + newtitle + "]"   );
        System.out.println();
    }

    public void update_II(Connection con, PreparedStatement pst, String query, int artid, int mid) throws SQLException {
        pst = con.prepareStatement(query);
        pst.setInt(1, artid);
        pst.setInt(2, mid);
        pst.executeUpdate();
        System.out.println(  "MODIFIED TO NEW ARTIST"   );
        System.out.println();
    }

    public void sync_album_artist(Connection con, PreparedStatement pst) throws SQLException {
        String sync = "UPDATE music, album SET music.Artist_ID = album.Artist_ID "
                + "WHERE music.Album_ID = album.Album_ID";
        pst = con.prepareStatement(sync);
        pst.executeUpdate();
    }

    public void delete_artist(Connection con, String query, String name) throws SQLException {
        PreparedStatement pst;

        int id = get_artistID(name, con);
        if (id == -1) {
            System.out.println(  "ERROR : Artist does not exist."   );
            return;
        }

        pst = con.prepareStatement(query);
        pst.setString(1, name);
        pst.executeUpdate();
        System.out.println(  "[" + name + "] DELETED"   );
    }

    public void delete_album(Connection con, String query, String title, String artist) throws SQLException {
        PreparedStatement pst;

        int id = get_albumID(title, artist, con);
        if (id == -1) {
            System.out.println(  "ERROR : Album does not exist."   );
            return;
        }

        pst = con.prepareStatement(query);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println(  "[" + title + "] DELETED"   );

    }

    public void delete_music(Connection con, String query, String title, String artist) throws SQLException {
        PreparedStatement pst;

        int id = get_musicID(title, artist, con);
        if (id == -1) {
            System.out.println(  "ERROR : Music does not exist."   );
            return;
        }

        pst = con.prepareStatement(query);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println(  "[" + title + "] DELETED"   );

    }

    public void delete_user(Connection con, String query, String login) throws SQLException {
        PreparedStatement pst;

        int id = get_userID(login, con);
        if (id == -1) {
            System.out.println(  "ERROR : User does not exist."   );
            return;
        }

        pst = con.prepareStatement(query);
        pst.setString(1, login);
        pst.executeUpdate();
        System.out.println(  "USER " + "[" + login + "] DELETED"   );
    }

    public void show_music(Connection con, PreparedStatement pst, ResultSet rs) throws SQLException {
//		pst = con.prepareStatement(query);
        rs = pst.executeQuery();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-30s%-30s%-30s%-30s%s", "| Title", "| Genre", "| Album", "| Artist", "|"));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        while (rs.next()) {
            String title = rs.getString(1);
            String genre = rs.getString(2);
            String album = rs.getString(3);
            String artist = rs.getString(4);

            System.out.println(String.format("%s%-28s%s%-28s%s%-28s%s%-28s%s", "| ", title, "| ", genre, "| ", album, "| ", artist, "|"));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
    }

    public void show_pl_music(Connection con, PreparedStatement pst, ResultSet rs) throws SQLException {
        rs = pst.executeQuery();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-30s%-30s%-30s%-30s%s", "| Title", "| Album", "| Artist", "| Playlist", "|"));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        while (rs.next()) {
            String title = rs.getString(1);
            String genre = rs.getString(2);
            String album = rs.getString(3);
            String artist = rs.getString(4);

            System.out.println(String.format("%s%-28s%s%-28s%s%-28s%s%-28s%s", "| ", title, "| ", genre, "| ", album, "| ", artist, "|"));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
    }

    public void make_playlist(Connection con, PreparedStatement pst_check, ResultSet rs, String query_insert, String name, String login) throws SQLException {
        PreparedStatement pst;

        int id = get_userID(login, con);
        if (id == -1) {
            System.out.println(  "ERROR : User does not exist."   );
            return;
        }

        pst_check.setString(1, name);
        pst_check.setInt(2, id);
        rs = pst_check.executeQuery();

        if (!rs.next()) {
            pst = con.prepareStatement(query_insert);
            pst.setString(1, name);
            pst.setInt(2, id);
            pst.executeUpdate(); // when success, return 1
            System.out.println(  "[" + name + "] CREATED"   );
        } else {
            System.out.println(  "[" + name + "] exists"   );
        }
    }

    public void delete_playlist(Connection con, PreparedStatement pst_check, ResultSet rs, String query_delete, String pname, String usr) throws SQLException {
        PreparedStatement pst;

        int id = get_userID(usr, con);
        if (id == -1) {
            System.out.println(  "ERROR : User does not exist."   );
            return;
        }

        pst_check.setString(1, pname);
        pst_check.setInt(2, id);

        rs = pst_check.executeQuery();
        if (rs.next()) {
            pst = con.prepareStatement(query_delete);
            pst.setString(1, pname);
            pst.setInt(2, id);
            pst.executeUpdate(); // when success, return 1
            System.out.println(  "[" + pname + "] DELETED\n"   );
        } else {
            System.out.println(  "[" + pname + "] does not exists\n"   );
        }
    }

    public void add_to_playlist(Connection con, PreparedStatement pst_check, ResultSet rs, String query_insert, String playlist, int usrid, String music, String artist) throws SQLException {
        PreparedStatement pst;

        int id = get_playlistID(playlist, usrid, con);
        if (id == -1) {
            System.out.println(  "ERROR : Playlist does not exist."   );
            return;
        }

        int id2 = get_musicID(music, artist, con);
        if (id2 == -1) {
            System.out.println(  "ERROR : Music does not exist."   );
            return;
        }

        pst_check.setInt(1, id);
        pst_check.setInt(2, id2);
        rs = pst_check.executeQuery();

        if (!rs.next()) {
            pst = con.prepareStatement(query_insert);
            pst.setInt(1, id);
            pst.setInt(2, id2);
            pst.executeUpdate(); // when success, return 1
            System.out.println(  "[" + artist + " - " + music + "] ADDED INTO " + playlist   );
            System.out.println();
        } else {
            System.out.println(  "[" + music + "] already exists in [" + playlist + "]"   );
        }
        System.out.println();
    }

    public void fav_artist(Connection con, PreparedStatement pst_check, ResultSet rs, String query_insert, String user, String artist) throws SQLException {
        PreparedStatement pst;

        int id = get_userID(user, con);
        if (id == -1) {
            System.out.println(  "ERROR : User does not exist."   );
            return;
        }

        int id2 = get_artistID(artist, con);
        if (id2 == -1) {
            System.out.println(  "ERROR : Artist does not exist."   );
            return;
        }

        pst_check.setInt(1, id);
        pst_check.setInt(2, id2);
        rs = pst_check.executeQuery();

        if (!rs.next()) {
            pst = con.prepareStatement(query_insert);
            pst.setInt(1, id);
            pst.setInt(2, id2);
            pst.executeUpdate(); // when success, return 1
            System.out.println(  "[" + artist + "] ADDED INTO favorite_artist"   );
        } else {
            System.out.println(  "[" + artist + "] already exists in favorite_artist"   );
        }
        System.out.println();
    }

    public void fav_delete(Connection con, PreparedStatement pst_check, ResultSet rs, String query_insert, String user, String artist) throws SQLException {
        PreparedStatement pst;

        int id = get_userID(user, con);
        if (id == -1) {
            System.out.println(  "ERROR : User does not exist."   );
            return;
        }

        int id2 = get_artistID(artist, con);
        if (id2 == -1) {
            System.out.println(  "ERROR : Artist does not exist."   );
            return;
        }

        pst_check.setInt(1, id);
        pst_check.setInt(2, id2);
        rs = pst_check.executeQuery();

        if (rs.next()) {
            pst = con.prepareStatement(query_insert);
            pst.setInt(1, id);
            pst.setInt(2, id2);
            pst.executeUpdate(); // when success, return 1
            System.out.println(  "[" + artist + "] DELETED\n"   );
        } else {
            System.out.println(  "[" + artist + "] does not exists in favorite_artist\n"   );
        }
    }

    public void newRelease(Connection con, PreparedStatement pst, ResultSet rs) throws SQLException {
        String query = "SELECT music.Title AS Music, album.Title AS Album, artist.Name AS Artist, album.ReleasedDate "
                + "FROM music, album, artist WHERE music.Album_ID=album.Album_ID AND album.Artist_ID=artist.Artist_ID "
                + "ORDER BY album.ReleasedDate DESC LIMIT 10";

        pst = con.prepareStatement(query);
        rs = pst.executeQuery();

        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-30s%-30s%-30s%-30s%s", "| Music", "| Album", "| Artist", "| Released", "|"));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        while (rs.next()) {
            String title = rs.getString(1);
            String album = rs.getString(2);
            String artist = rs.getString(3);
            String date = rs.getString(4);

            System.out.println(String.format("%s%-28s%s%-28s%s%-28s%s%-28s%s", "| ", title, "| ", album, "| ", artist, "| ", date, "|"));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

    }

    public void pop_artist(Connection con, PreparedStatement pst, ResultSet rs) throws SQLException {
        String query = "SELECT Name, Sex, DebutDate, COUNT(if(favorite_artist.Artist_ID=artist.Artist_ID, 1, null)) as Popularity FROM artist, favorite_artist GROUP BY artist.Artist_ID ORDER BY popularity DESC LIMIT 10";
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();

        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-30s%-30s%-30s%-30s%s", "| Name", "| Sex", "| Debut", "| Popularity", "|"));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        while (rs.next()) {
            String name = rs.getString(1);
            String sex = rs.getString(2);
            String debut = rs.getString(3);
            String pop = rs.getString(4);

            System.out.println(String.format("%s%-28s%s%-28s%s%-28s%s%-28s%s", "| ", name, "| ", sex, "| ", debut, "| ", pop, "|"));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

    }

    public void pop_music(Connection con, PreparedStatement pst, ResultSet rs) throws SQLException {
        String query = "SELECT music.Title as Music, album.Title as Album, artist.Name as Artist, COUNT(if(playlist_music.Music_ID=music.Music_ID,1,null)) as popularity FROM music, playlist_music, album, artist WHERE music.Album_ID=album.Album_ID AND album.Artist_ID=artist.Artist_ID GROUP BY music.Music_ID ORDER BY popularity DESC LIMIT 10";

        pst = con.prepareStatement(query);
        rs = pst.executeQuery();

        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-30s%-30s%-30s%-30s%s", "| Music", "| Album", "| Artist", "| Popularity", "|"));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        while (rs.next()) {
            String title = rs.getString(1);
            String album = rs.getString(2);
            String artist = rs.getString(3);
            String pop = rs.getString(4);

            System.out.println(String.format("%s%-28s%s%-28s%s%-28s%s%-28s%s", "| ", title, "| ", album, "| ", artist, "| ", pop, "|"));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

    }

//	public void usr_reg(Connection con, PreparedStatement pst_check, ResultSet rs, String query_check, String id, String pw) throws SQLException{
//		Scanner sc = new Scanner(System.in);
//		pst_check.setString(1, id);
//		rs = pst_check.executeQuery();
//		if (!rs.next()) { // register procedure
//			System.out.println("Input your information.");
//			System.out.print("Name : ");
//			String name = sc.nextLine();
//			System.out.print("Sex : ");
//			String sex = sc.nextLine();
//
//			String query2 = "INSERT INTO user(Name, Sex, JoinedDate, Login_ID, Password) VALUES(?, ?, now(), ?, ?)";
//			PreparedStatement pst2 = con.prepareStatement(query2);
//			pst2.setString(1, name);
//			pst2.setString(2, sex);
//			pst2.setString(3, id);
//			pst2.setString(4, pw);
//			pst2.executeUpdate();
//
//			System.out.println(id + " register succeeded.");
//
//		} else { // id already exists
//			System.out.println("ID exists. Try again.");
//		}
//		sc.close();
//	}

}
