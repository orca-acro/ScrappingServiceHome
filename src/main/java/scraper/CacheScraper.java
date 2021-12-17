package scraper;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CacheScraper implements Scraper {
    private Scraper scraper = new DefaultScraper();
    private int id = 1;
    @Override @SneakyThrows
    public Home scrape(String url) {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        Statement statement = connection.createStatement();

        String query = String.format("select count(*) as count from homes where url='%s'", url);
        ResultSet rs = statement.executeQuery(query);

        int count = rs.getInt("count");
        System.out.println(count);
        if (count > 0) {
            query = String.format("select count(*) as count from homes where url='%s'", url);
            rs = statement.executeQuery(query);
            return new Home(rs.getInt("price"), rs.getDouble("beds"), rs.getDouble("baths"),
                    rs.getDouble("garage"));
        } else {
            id = id + 1;
            Home home = scraper.scrape(url);
            statement.executeUpdate("INSERT INTO homes(id, url, price, beds, baths, garage)" +
                    " VALUE (id, url, home.price, home.beds, home.baths, home.garage)");
            return home;
        }
    }
}