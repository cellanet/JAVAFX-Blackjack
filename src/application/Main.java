/*
 * Upgraded with GUI by Gia Minh
 * 11/14/2021
 * Black Jack Game with GUI, video, and sound.
 *
 */
package application;
	
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	private MenuBar menuBar;
	private Menu file, option, betOption, help;
	private Label playerLabel, dealerLabel, amountLabel1, amountLabel2 , totalLabel1, totalLabel2, dollarLabel1, dollarLabel2;
	private Button hitButton, stayButton;
	private int playerAmount, dealerAmount, betAmount;	
	private List<ImageView> playerSlot, dealerSlot;
	private Image blank;
	private TextField playerTextBox, dealerTextBox;
	private Media sound, video;
	private MediaPlayer playSound, playVideo;
	private MediaView playerWin,dealerWin;
	private final int CARDHEIGHT = 145;
	private final int CARDWIDTH = 100;
	private final int MAXCARD = 5;
	
	public void start(Stage primaryStage) {
		try {
				
				primaryStage.setTitle("Black Jack Game");
				primaryStage.setResizable(false);
				
				//create menu bar
				menuBar = new MenuBar();
				buildFile(primaryStage);
				buildOption();
				buildHelp();
				menuBar.getMenus().add(file);
				menuBar.getMenus().add(option);
				menuBar.getMenus().add(help);
				
				// initialize values for textboxes, labels, buttons, image, playerSlot, dealerSlot
				setInitial();		

				// ================================ Player's HBOX/VBOX =====================================
				HBox hboxButton = new HBox(15, hitButton, new Label(" "), stayButton);
				HBox dollarText = new HBox(5, dollarLabel1, playerTextBox);
				VBox textAndButton = new VBox(10, amountLabel1, dollarText, new Label("\n\n"),hboxButton);
				
				HBox cardSlot1 = new HBox(15, playerSlot.get(0), playerSlot.get(1), playerSlot.get(2), playerSlot.get(3), playerSlot.get(4));
				VBox totalAndCardSlot = new VBox(10, totalLabel1, cardSlot1);
			
				HBox playerRightHbox = new HBox(15,textAndButton, totalAndCardSlot);
				VBox playerVbox = new VBox(20, playerLabel, playerWin);
				HBox playerBox = new HBox(50, playerVbox, playerRightHbox);
				playerBox.setAlignment(Pos.CENTER);
				
				// ================================ Dealer's HBOX/VBOX ================================
				HBox dollarText2 = new HBox(5, dollarLabel2, dealerTextBox);
				VBox textVbox = new VBox(10, amountLabel2, dollarText2);
				
				HBox cardSlot2 = new HBox(15, dealerSlot.get(0), dealerSlot.get(1), dealerSlot.get(2), dealerSlot.get(3), dealerSlot.get(4));
				VBox totalAndCardSlot2 = new VBox(10, totalLabel2, cardSlot2);
							
				HBox dealerRightHbox = new HBox(15,textVbox, totalAndCardSlot2);
				VBox dealerVbox = new VBox(20, dealerLabel, dealerWin);
				HBox dealerBox = new HBox(50, dealerVbox, dealerRightHbox);
				dealerBox.setAlignment(Pos.CENTER);
				
				// ================================ All HBOX/VBOX ================================
				VBox vbox0 = new VBox(120, playerBox, dealerBox);
				vbox0.setAlignment(Pos.CENTER);
			
				BorderPane root = new BorderPane();
				root.setTop(menuBar);
				root.setCenter(vbox0);
				Scene scene = new Scene(root,1000,600);
				scene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	// initialize values for textboxes, labels, buttons, image, playerSlot, dealerSlot
	public void setInitial()
	{
		playerLabel = new Label("   Player");
		dealerLabel = new Label("   Dealer");
		amountLabel1 = new Label("    Amount");
		amountLabel2 = new Label("    Amount");
		totalLabel1 = new Label("Total: ");
		totalLabel2 = new Label("Total: ");
		dollarLabel1 = new Label("$");
		dollarLabel2 = new Label("$");
		hitButton = new Button("_Hit");
		stayButton = new Button("_Stay");
		
		playerAmount = 100;
		dealerAmount = 100;
		betAmount = 1;
		
		playerTextBox = new TextField(String.valueOf(playerAmount));
		playerTextBox.setMaxWidth(90);
		dealerTextBox = new TextField(String.valueOf(dealerAmount));
		dealerTextBox.setMaxWidth(90);
		
		playerTextBox.setEditable(false);
		dealerTextBox.setEditable(false);
		
		// a blank image use as a card's slot
		blank = new Image("File:src\\Blank.png");
	
		// initial all slots to be blank slots
		playerSlot = new ArrayList<>();	
		for(int i = 0; i < MAXCARD; i++)
		{
			playerSlot.add(new ImageView(blank));
			playerSlot.get(i).setFitHeight(CARDHEIGHT);
			playerSlot.get(i).setFitWidth(CARDWIDTH);
		}
		dealerSlot = new ArrayList<>();				
		for(int i = 0; i < MAXCARD; i++)
		{
			dealerSlot.add(new ImageView(blank));
			dealerSlot.get(i).setFitHeight(CARDHEIGHT);
			dealerSlot.get(i).setFitWidth(CARDWIDTH);
		}
		
		hitButton.setDisable(true);
		stayButton.setDisable(true);
		
		//sound
		String soundFile = "src\\Shuffling_cards.mp3";
		sound = new Media(new File(soundFile).toURI().toString());
		playSound = new MediaPlayer(sound);
		playSound.setCycleCount(MediaPlayer.INDEFINITE);
		
		//video
		String videoFile = "src\\BlackJack.mp4";
		video = new Media(new File(videoFile).toURI().toString());
		playVideo = new MediaPlayer(video);
		playVideo.setAutoPlay(true);
		playVideo.setCycleCount(MediaPlayer.INDEFINITE);
		
		playerWin = new MediaView(playVideo);
		playerWin.setFitHeight(200);
		playerWin.setFitWidth(200);
		playerWin.setVisible(false);
		
		dealerWin = new MediaView(playVideo);
		dealerWin.setFitHeight(200);
		dealerWin.setFitWidth(200);
		dealerWin.setVisible(false);
	}

	// reset board all card to blank slots for a new game
	public void resetBoard()
	{	
		for(int i = 0; i < MAXCARD; i++)
		{
			playerSlot.get(i).setImage(blank);
			
		}
		for(int i = 0; i < MAXCARD; i++)
		{
			dealerSlot.get(i).setImage(blank);
		}
	}
	
	//player move, if player hit stay then the dealer move automaticly.
	public void playerChoice(Player me, Player dealer, Deck theDeck,Stage primaryStage)
	{	
		hitButton.setDisable(true);
		stayButton.setDisable(true);
		
		totalLabel1.setText("Total: ");
		totalLabel2.setText("Total: ");
		
		// flip the card when clicked
		playerSlot.get(1).setOnMouseClicked(e->
		{
			playSound.stop();
			hitButton.setDisable(false);
			me.printPathCard(true, playerSlot);
			totalLabel1.setText("Total: " + String.valueOf(me.getHandSum()));
			
			// can stay if sum < 16
			if(me.getHandSum() < 16)
				stayButton.setDisable(true);
			else
				stayButton.setDisable(false);
		});
		
		// if player hit
		hitButton.setOnAction(e->
		{
			me.addCard(theDeck.dealNextCard());
			me.printPathCard(true, playerSlot);
			totalLabel1.setText("Total: " + String.valueOf(me.getHandSum()));
			if(me.getHandSum() >= 16)
				stayButton.setDisable(false);
			
			
			// if over 21 (busted) or max cards on hand (5). Dealer will move automaticly
			if(me.getHandSum() >21 ||  me.getNumOfCard() >= 5)
			{
				hitButton.setDisable(true);		
				stayButton.fire();
				stayButton.setDisable(true);
			}
		});		
		
		// if player stay, dealer will automaticly play.
		stayButton.setOnAction(e->
		{
			boolean dealerDone = false;
			while(!dealerDone)
			{
				if(!dealerDone)
				{
					if(dealer.getHandSum() < 16)
					{
						dealerDone = !dealer.addCard(theDeck.dealNextCard());
						totalLabel2.setText("Total: " + String.valueOf(dealer.getHandSum()));
					}
					else
					{
						totalLabel2.setText("Total: " + String.valueOf(dealer.getHandSum()));
						dealerDone = true;
					}	
				}
			}
			hitButton.setDisable(true);
			stayButton.setDisable(true);
			
			//show all cards
			me.printPathCard(true, playerSlot);
			dealer.printPathCard(true, dealerSlot);
			
			int mySum = me.getHandSum();
			int dealerSum = dealer.getHandSum();
			
			// win conditions, add/subtract amount bet and play video for whoever won the game.
			if( (mySum > dealerSum && mySum <= 21) || (dealerSum > 21 && mySum <= 21) )
			{
				playerAmount += betAmount;
				dealerAmount -= betAmount;
				totalLabel1.setText("Total: " + String.valueOf(me.getHandSum()) + " (won)");
				playerTextBox.setText(String.valueOf(playerAmount));
				dealerTextBox.setText(String.valueOf(dealerAmount));
				playerWin.setVisible(true);
			}
			else if( (dealerSum > mySum && dealerSum <= 21) || (mySum > 21 && dealerSum <= 21) )
			{
				playerAmount -= betAmount;
				dealerAmount += betAmount;
				totalLabel2.setText("Total: " + String.valueOf(dealer.getHandSum()) + " (won)");
				playerTextBox.setText(String.valueOf(playerAmount));
				dealerTextBox.setText(String.valueOf(dealerAmount));
				dealerWin.setVisible(true);
			}
			else
			{
				totalLabel1.setText("Total: " + String.valueOf(me.getHandSum()) + " (draw)");
				totalLabel2.setText("Total: " + String.valueOf(dealer.getHandSum()) + " (draw)");
			}

			//stop the game if one of the player ran out of money.
			if(dealerAmount <= 0)
			{
				JOptionPane.showMessageDialog(null, "You Won. Please Start A New Game.");
				primaryStage.close();
			}
			else if (playerAmount <= 0)
			{
				JOptionPane.showMessageDialog(null, "You Lost. Please Start A New Game.");
				primaryStage.close();
			}
			
		});	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//build File menu
	private void buildFile(Stage primaryStage)
	{
		file = new Menu("_File");	
		MenuItem play = new MenuItem("Deal a new game.");
		
		//start game, play shuffle sound, hide videos from dealer and player
		play.setOnAction(e->
		{		
			playSound.play();
			playerWin.setVisible(false);
			dealerWin.setVisible(false);	
			resetBoard();
			playGame(primaryStage);		
		});
		MenuItem exit = new MenuItem("E_xit");
		// exit
		exit.setOnAction(event ->
		{	
			primaryStage.close();
		});
		
		file.getItems().add(play);
		file.getItems().add(new SeparatorMenuItem());
		file.getItems().add(exit);
	}
	
	//build option menu
	private void buildOption()
	{
		option = new Menu("_Options");
		
		betOption = new Menu("Bet _Amount");
		
		MenuItem bet1 = new MenuItem("$1.00");
		MenuItem bet5 = new MenuItem("$5.00");
		MenuItem bet10 = new MenuItem("$10.00");
		
		bet1.setOnAction(e->
		{
			betAmount = 1;
		});
		bet5.setOnAction(e->
		{
			betAmount = 5;
		});
		bet10.setOnAction(e->
		{
			betAmount = 10;
		});
				
		betOption.getItems().addAll(bet1, new SeparatorMenuItem(), bet5, new SeparatorMenuItem(), bet10);
		option.getItems().add(betOption);			
	}
	
	//help menu
	public void buildHelp()
	{
		help = new Menu("H_elp");
		
		Menu about = new Menu("About...");
		MenuItem info = new MenuItem("By: Gia Minh\nNov 14th 2021");
		
		about.getItems().add(info);
		help.getItems().add(about);
	}
	
	// start the game by dealing 2 cards for each player
	public void playGame(Stage primaryStage)
	{
		Deck theDeck = new Deck(1,true);
		Player me = new Player("Minh");
		Player dealer = new Player("Dealer");
		
		me.addCard(theDeck.dealNextCard());
		me.addCard(theDeck.dealNextCard());
		me.printPathCard(false, playerSlot);
		
		dealer.addCard(theDeck.dealNextCard());	
		dealer.addCard(theDeck.dealNextCard());
		dealer.printPathCard(false, dealerSlot);
		
		// player move, then dealer move after
		playerChoice(me, dealer, theDeck, primaryStage);	
	}
}
