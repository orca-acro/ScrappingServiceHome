import scraper.*;

public class Main {
    public static void main(String[] args) {

        String url = "https://www.newhomesource.com/plan/encore-wlh-taylor-norrison-austin-tx/1771471";
        CacheScraper cacheScraper = new CacheScraper();
        Home home = cacheScraper.scrape(url);
        System.out.println(home);

    }
}
