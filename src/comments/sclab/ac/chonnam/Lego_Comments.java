package comments.sclab.ac.chonnam;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import reply.sclab.ac.chonnam.copy.MyPojo;
import webdriver.sclab.ac.chonnam.readAuthor;

public class Lego_Comments {
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
		String project = "1b7bda5d-97c2-48b3-b178-092bacc5c899";
		// String project =
		// "040b64bf-0bda-442f-8d4d-5676908fdefa";//https://ideas.lego.com/projects/90622/comments
		// String project =
		// "df74e9e6-183c-4cf5-a2f5-41e0b8626260";//https://ideas.lego.com/projects/98263/comments
		String limit1 = "101";
		String url1 = "https://ideas.lego.com/comments/project/" + project
				+ "/comments?order=oldest&from=min&style=children&max_id=&min_id=&limit=" + limit1
				+ "&id=comments-query-/comments" + "/project/" + project + "/comments";
				// String url1=
				// "https://ideas.lego.com/comments/project/7a204071-7d33-4cfd-9306-15f1cdbfb798/comments?order=oldest&from=min&style=children&max_id=&min_id=&limit=100&id=comments-query-"
				// +
				// "/comments/project/7a204071-7d33-4cfd-9306-15f1cdbfb798/comments&_=1451983441123";

		// String
		// url1="https://ideas.lego.com/comments/project/7a204071-7d33-4cfd-9306-15f1cdbfb798/comments?order=oldest&from=min&style=children&limit=2&id=comments-query-/comments"
		// + "/project/7a204071-7d33-4cfd-9306-15f1cdbfb798/comments";
		//

		String next_index = "";
		int reply = 0;
		int comment = 0;
		ArrayList<MyComments> comments_Data = new ArrayList<MyComments>();

		while (true) {

			String url = url1 + next_index;
			String json = IOUtils.toString(new URL(url));
			Gson gson = new GsonBuilder().create();
			MyPojo myPojo = gson.fromJson(json, MyPojo.class);

			if (myPojo.getResults().length == 0) {
				break;
			}

			for (int i = 0; i < myPojo.getResults().length; i++) {
				MyComments myData1 = new MyComments(myPojo.getResults()[i]);
				comments_Data.add(myData1);

				System.out.println("======" + comment + "==========");
				System.out.print("Author: ");
				System.out.println(myPojo.getResults()[i].getAuthor().getAlias());
				System.out.print("Author_id: ");
				System.out.println(myPojo.getResults()[i].getAuthor().getId());
				System.out.print("Comment: ");
				System.out.println(myPojo.getResults()[i].getComment());
				System.out.print("Created_at: ");
				System.out.println(myPojo.getResults()[i].getCreated_at());
				System.out.print("User_vote: ");
				System.out.println(myPojo.getResults()[i].getUser_vote().getCount());
				System.out.print("Replies_count: ");
				System.out.println(myPojo.getResults()[i].getReplies_count());
				reply = reply + Integer.parseInt(myPojo.getResults()[i].getReplies_count());
				comment = comment + 1;
				System.out.print(myPojo.getResults()[i].getUser_vote().getDistribution().get_minus1());
				System.out.print(myPojo.getResults()[i].getUser_vote().getDistribution().get_zero0());
				System.out.println(myPojo.getResults()[i].getUser_vote().getDistribution().get_plus1());
				if (myPojo.getResults()[i].getReplies_count().equals("0") == false) {
					// extract reply
					//String limit_reply = "101";
					String url_reply1 = "https://ideas.lego.com/comments/project/" + project + "/comments/"
							+ myPojo.getResults()[i].getId() + "/replies?order=oldest&from=min&style=flat"
							+ "&max_id=&min_id=&limit=101&id=comments-query-/comments/project/" + project + "/comments";

					String next_index_reply = "";

					// ArrayList<MyReply> replys_Data = new
					// ArrayList<MyReply>();

					while (true) {

						String url_reply = url_reply1 + next_index_reply;
						System.out.println(url_reply);
						String json_reply = IOUtils.toString(new URL(url_reply));
						Gson gson_reply = new GsonBuilder().create();
						MyPojo myPojo_reply = gson_reply.fromJson(json_reply, MyPojo.class);

						if (myPojo_reply.getResults().length == 0) {
							break;
						}

						for (int j = 0; j < myPojo_reply.getResults().length; j++) {
							MyComments myData_reply1 = new MyComments(myPojo_reply.getResults()[j]);
							comments_Data.add(myData_reply1);

							System.out.println("======" + comment + "==========");
							System.out.print("AuthorReply: ");
							System.out.println(myPojo_reply.getResults()[j].getAuthor().getAlias());
							System.out.print("Author_idReply: ");
							System.out.println(myPojo_reply.getResults()[j].getAuthor().getId());
							System.out.print("CommentReply: ");
							System.out.println(myPojo_reply.getResults()[j].getComment());
							System.out.print("Created_atReply: ");
							System.out.println(myPojo_reply.getResults()[j].getCreated_at());
							System.out.print("User_voteReply: ");
							System.out.println(myPojo_reply.getResults()[j].getUser_vote().getCount());
							System.out.print("Replies_countReply: ");
							System.out.println(myPojo_reply.getResults()[j].getReplies_count());
							comment = comment + 1;
							System.out
									.print(myPojo_reply.getResults()[j].getUser_vote().getDistribution().get_minus1());
							System.out.print(myPojo_reply.getResults()[j].getUser_vote().getDistribution().get_zero0());
							System.out
									.println(myPojo_reply.getResults()[j].getUser_vote().getDistribution().get_plus1());

						}

						next_index_reply = "&min_id="
								+ myPojo_reply.getResults()[myPojo_reply.getResults().length - 1].getId();

					}
				}
			}

			next_index = "&min_id=" + myPojo.getResults()[myPojo.getResults().length - 1].getId();
			System.out.println("Reply + Comment=" + reply + " + " + comment + " = " + (reply + comment));
		}

		Gson gson1 = new Gson();
		String json1 = gson1.toJson(comments_Data);

		System.out.println(json1);
		// Date and current time
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
		Calendar cal = Calendar.getInstance();
		String mynamefile = "CommentsLego" + project + dateFormat.format(cal.getTime()) + ".txt";
		// String
		// mynamefileTotal="InfluenceTotal_quirky"+dateFormat.format(cal.getTime())+".txt";
		try {
			FileWriter writer = new FileWriter(mynamefile);
			writer.write(json1);
			writer.close();
			System.out.println("Write file: Success");
			System.out.println("COMMENTs: ");

		} catch (Exception e) {
			// TODO: handle exception

		}
		// Crawl profile of authors
		// Create a new instance of the Firefox driver
		System.setProperty("webdriver.firefox.profile", "default");
		WebDriver driver = new FirefoxDriver();
		// Dimension n = new Dimension(20,40);
		// driver.manage().window().setSize(n);
		// driver.manage().window().setPosition(new Point(-2000, 0));
		// Wait For Page To Load
		// Put a Implicit wait, this means that any search for elements on the
		// page
		// could take the time the implicit wait is set for before throwing
		// exception
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// Navigate to URL
		driver.get("https://ideas.lego.com/");
		System.out.println("11");
		driver.findElement(By.id("legoid-login")).click();
		System.out.println("22");
		driver.findElement(By.id("modal-legoid-login")).click();
		System.out.println("33");
		// Maximize the window.
		// driver.manage().window().maximize();
		// switch to fame
		driver.switchTo().frame(0);
		System.out.println("44");
		// Enter UserName
		driver.findElement(By.id("fieldUsername")).sendKeys("quannguyen1");
		System.out.println("55");
		// Enter Password
		driver.findElement(By.id("fieldPassword")).sendKeys("Chonn@m911005");
		System.out.println("66");
		// Wait For Page To Load

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// Click on 'Sign In' button
		driver.findElement(By.id("buttonSubmitLogin")).click();
		System.out.println("77");
		Thread.sleep(5000);
		// if (name.contains(" ")) {
		// driver.navigate().back();
		// //driver.findElement(By.id("fieldUsername")).sendKeys("quanap5");
		// // Enter Password
		// driver.findElement(By.id("fieldPassword")).sendKeys("Chonn@m911005");
		// // Wait For Page To Load
		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// driver.findElement(By.id("buttonSubmitLogin")).click();
		// }
		// Get the page content:

		ArrayList<readAuthor> commentAuthor = new ArrayList<readAuthor>();
		for (int i = 0; i < comments_Data.size(); i++) {
			System.out.println("==" + i + "===" + comments_Data.get(i).getAuthor() + "==="
					+ comments_Data.get(i).getId() + "==========");
			readAuthor eachAuthor = new readAuthor(comments_Data.get(i).getAuthor(), comments_Data.get(i).getId(),
					driver);
			commentAuthor.add(eachAuthor);
		}

		Gson gson2 = new Gson();
		String json2 = gson2.toJson(commentAuthor);

		System.out.println(json2);
		String mynamefile2 = "AuthorsProfile" + project + dateFormat.format(cal.getTime()) + ".txt";
		// String
		// mynamefileTotal="InfluenceTotal_quirky"+dateFormat.format(cal.getTime())+".txt";
		try {
			FileWriter writer = new FileWriter(mynamefile2);
			writer.write(json2);
			writer.close();
			System.out.println("Write file: Success");
			System.out.print("AUTHOR-PROFILEs: ");

		} catch (Exception e) {
			// TODO: handle exception

		}

		// Close the browser.
		driver.close();

	}
}
