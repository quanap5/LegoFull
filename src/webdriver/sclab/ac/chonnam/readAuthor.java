package webdriver.sclab.ac.chonnam;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

public class readAuthor {
	String id;
	String author;

	String no_ProFollowing;
	String no_UserFollowing;
	String no_FollowER;

	String no_GatheringSupport;
	String no_Archived;
	String no_Expired;
	String no_Review;
	String no_NOTapproved;
	String no_Supporting;

	String Clutch_Power = "0";
	String _1k_Club = "0";
	String Socializer = "0";
	String Trailblazer = "0";
	String _5k_Club = "0";
	String _10k_Club = "0";
	String Autobiographer = "0";
	String Pioneer = "0";
	String Luminary = "0";

	// public readAuthor() {
	//
	// Clutch_Power = "0";
	// _10k_Club = "0";
	// _1k_Club = "0";
	// _5k_Club = "0";
	// Autobiographer = "0";
	// Pioneer = "0";
	// Luminary = "0";
	// Trailblazer = "0";
	// Socializer = "0";
	// }

	public readAuthor(String name, String id_name, WebDriver driver) throws IOException {

		no_ProFollowing = "0";
		no_UserFollowing = "0";
		no_FollowER = "0";

		no_GatheringSupport = "0";
		no_Archived = "0";
		no_Expired = "0";
		no_Review = "0";
		no_NOTapproved = "0";
		no_Supporting = "0";

		Clutch_Power = "0";
		_1k_Club = "0";
		Socializer = "0";
		Trailblazer = "0";
		_5k_Club = "0";
		_10k_Club = "0";
		Autobiographer = "0";
		Pioneer = "0";
		Luminary = "0";

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get("https://ideas.lego.com/profile/" + name + "/following");
		Document check_following1 = Jsoup.parse(driver.getPageSource());
		Elements check_following1s = check_following1.select("section#watching");

		if (check_following1s.select("a.btn btn-brand btn-alternate show-more-btn").size() > 0) {

			int total = 0;
			String offset;
			String limitString;
			int limit = 1000;
			int i = 0;
			int size = 1;
			while (size != 0) {
				offset = Integer.toString(0 + i * limit);
				limitString = Integer.toString(limit);
				Document following1 = Jsoup.connect("https://ideas.lego.com/projects/user/" + id_name
						+ "/watching?offset=" + offset + "&limit=" + limitString).timeout(0).get();
				Elements following1s = following1.select("li");
				// System.out.println(following1s.toString());
				size = following1s.size();
				total = total + following1s.size();
				i = i + 1;
			}
			System.out.print("number of PROJECT following: ");
			System.out.println(total);
			this.no_ProFollowing = Integer.toString(total);
		} else {
			Elements check_following1ss = check_following1s.select("li");
			// System.out.println(following1s.toString());
			System.out.print("number of PROJECT following: ");
			System.out.println(check_following1ss.size());
			this.no_ProFollowing = Integer.toString(check_following1ss.size());
		}

		// userFOLLOWING

		Document check_following2 = Jsoup.parse(driver.getPageSource());
		Elements check_following2s = check_following2.select("section#following");
		if (check_following2s.select("a.btn.btn-brand.btn-alternate.show-more-btn").size() > 0) {

			int total = 0;
			String offset;
			String limitString;
			int limit = 1000;
			int i = 0;
			int size = 1;
			while (size != 0) {
				offset = Integer.toString(0 + i * limit);
				limitString = Integer.toString(limit);
				Document following2 = Jsoup.connect("https://ideas.lego.com/stream/user/" + id_name
						+ "/following?offset=" + offset + "&limit=" + limitString).timeout(0).get();
				Elements following2s = following2.select("li");
				// System.out.println(following1s.toString());
				size = following2s.size();
				total = total + following2s.size();
				i = i + 1;
			}
			System.out.print("number of USER following: ");
			System.out.println(total);
			this.no_UserFollowing = Integer.toString(total);
		} else {
			Elements check_following2ss = check_following2s.select("li");
			// System.out.println(following1s.toString());
			System.out.print("number of USER following: ");
			System.out.println(check_following2ss.size());
			this.no_UserFollowing = Integer.toString(check_following2ss.size());
		}

		// FOLLOWER

		Document check_followER = Jsoup.parse(driver.getPageSource());
		Elements check_followERs = check_followER.select("section#follower");

		if (check_following2s.select("a.btn.btn-brand.btn-alternate.show-more-btn").size() > 0) {

			int total = 0;
			String offset;
			String limitString;
			int limit = 1000;
			int i = 0;
			int size = 1;
			while (size != 0) {
				offset = Integer.toString(0 + i * limit);
				limitString = Integer.toString(limit);
				Document followER = Jsoup.connect("https://ideas.lego.com/stream/user/" + id_name + "/followers?offset="
						+ offset + "&limit=" + limitString).timeout(0).get();
				Elements followERs = followER.select("li");
				// System.out.println(following1s.toString());
				size = followERs.size();
				total = total + followERs.size();
				i = i + 1;
			}
			System.out.print("number of FOLLOWER: ");
			System.out.println(total);
			this.no_FollowER = Integer.toString(total);
		} else {
			Elements check_followERss = check_followERs.select("li");
			// System.out.println(following1s.toString());
			System.out.print("number of FOLLOWER: ");
			System.out.println(check_followERss.size());
			this.no_FollowER = Integer.toString(check_followERss.size());
		}
		// PROJECT
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get("https://ideas.lego.com/profile/" + name + "/projects");
		Document projects = Jsoup.parse(driver.getPageSource());
		Elements userMains = projects.select("h2");

		for (int i = 0; i < userMains.size(); i++) {
			Element e = userMains.get(i);
			switch (e.text()) {
			case "Gathering Support":
				Element nextSib_gather = e.nextElementSibling();
				Elements nextpanels_gather = nextSib_gather.select("article.panel-tile");
				System.out.print("number of Gathering Support: ");
				System.out.println(nextpanels_gather.size());
				this.no_GatheringSupport = Integer.toString(nextpanels_gather.size());
				break;
			case "Expired":
				Element nextSib_Expired = e.nextElementSibling();
				Elements nextpanels_Expired = nextSib_Expired.select("article.panel-tile");
				System.out.print("number of Expired: ");
				System.out.println(nextpanels_Expired.size());
				this.no_Expired = Integer.toString(nextpanels_Expired.size());
				break;
			case "Archived":
				Element nextSib_Archived = e.nextElementSibling();
				Elements nextpanels_Archived = nextSib_Archived.select("article.panel-tile");
				System.out.print("number of Archived: ");
				System.out.println(nextpanels_Archived.size());
				this.no_Archived = Integer.toString(nextpanels_Archived.size());
				break;
			case "In Review":
				Element nextSib_Review = e.nextElementSibling();
				Elements nextpanels_Review = nextSib_Review.select("article.panel-tile");
				System.out.print("number of Review: ");
				System.out.println(nextpanels_Review.size());
				this.no_Review = Integer.toString(nextpanels_Review.size());
				break;
			case "Project Not Approved":
				Element nextSib_NotApproved = e.nextElementSibling();
				Elements nextpanels_NotApproved = nextSib_NotApproved.select("article.panel-tile");
				System.out.print("number of NotApproved: ");
				System.out.println(nextpanels_NotApproved.size());
				this.no_NOTapproved = Integer.toString(nextpanels_NotApproved.size());
				break;
			default:
				break;
			}

		}
		// SUPPORTING
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get("https://ideas.lego.com/profile/" + name + "/supporting");
		Document doc = Jsoup.parse(driver.getPageSource());
		// Elements secs = doc.select("section.header-user-achievement");
		// System.out.println(secs.toString());

		Elements panels = doc.select("article.panel-tile");
		System.out.print("number of supporting: ");
		System.out.println(panels.size());

		Elements secs = projects.select("section.header-user-achievement");
		this.author = name;
		this.id = id_name;
		for (final Element sec : secs) {
			final Elements heading2s = sec.select("h2");
			for (final Element heading2 : heading2s) {
				System.out.print(heading2.text() + ": ");
				System.out.println(heading2.select("span").text());
				this.Clutch_Power = heading2.select("span").text();
			}

			final Elements lis = sec.select("li");
			for (final Element li : lis) {
				if (li.attr("title").equals("10k Club")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this._10k_Club = li.select("span").text();
					if (_10k_Club.equals(null) || _10k_Club.equals("")) {
						this._10k_Club = "0";
					}
				}

				if (li.attr("title").equals("1k Club")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this._1k_Club = li.select("span").text();
					if (_1k_Club.equals(null) || _1k_Club.equals("")) {
						this._1k_Club = "0";
					}
				}

				if (li.attr("title").equals("5k Club")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this._5k_Club = li.select("span").text();
					if (_5k_Club.equals(null) || _5k_Club.equals("")) {
						this._5k_Club = "0";
					}
				}

				if (li.attr("title").equals("Pioneer")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this.Pioneer = li.select("span").text();
					if (Pioneer.equals(null) || Pioneer.equals("")) {
						this.Pioneer = "0";
					}
				}

				if (li.attr("title").equals("Luminary")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this.Luminary = li.select("span").text();
					if (Luminary.equals(null) || Luminary.equals("")) {
						this.Luminary = "0";
					}
				}

				if (li.attr("title").equals("Autobiographer")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this.Autobiographer = li.select("span").text();
					if (Autobiographer.equals(null) || Autobiographer.equals("")) {
						this.Autobiographer = "0";
					}
				}

				if (li.attr("title").equals("Trailblazer")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this.Trailblazer = li.select("span").text();
					if (Trailblazer.equals(null) || Trailblazer.equals("")) {
						this.Trailblazer = "0";
					}
				}

				if (li.attr("title").equals("Socializer")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this.Socializer = li.select("span").text();
					if (Socializer.equals(null) || Socializer.equals("")) {
						this.Socializer = "0";
					}
				}

			}
		}

	}
}