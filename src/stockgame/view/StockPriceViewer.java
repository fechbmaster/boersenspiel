package stockgame.view;

import stockgame.core.Share;
import stockgame.core.StockPriceInfo;
import stockgame.core.StockTimer;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class StockPriceViewer extends JFrame {
    private static final int TICK_PERIOD = 1000;
    private static final int DELAY_PERIOD = 1000;
    private static Timer ticker;
    private StockPriceInfo infoProvider;
    Share[] currShareSnapshot;

   

    private class TickerTask extends TimerTask {
        public void run() {
            updateLabels();
        }

    }


    public StockPriceViewer(StockPriceInfo info) {
    	setResizable(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        infoProvider = info;
        setAlwaysOnTop(true);
        setTitle("StockGame");
        getContentPane().setLayout(null);
        
        JLabel lblAccounts = new JLabel("Spieler");
        lblAccounts.setBounds(5, 5, 46, 14);
        getContentPane().add(lblAccounts);
        
        JLabel lblShares = new JLabel("Aktien");
        lblShares.setBounds(225, 5, 46, 14);
        getContentPane().add(lblShares);
        
        JLabel lblAccLabel = new JLabel("LOADING INTERFACE");
        lblAccLabel.setVerticalAlignment(SwingConstants.TOP);
        lblAccLabel.setBounds(225, 25, 200, 275);
        getContentPane().add(lblAccLabel);
        
        JLabel lblPlayerlabel = new JLabel("PLEASE STAND BY!");
        lblPlayerlabel.setVerticalAlignment(SwingConstants.TOP);
        lblPlayerlabel.setBounds(5, 25, 200, 275);
        getContentPane().add(lblPlayerlabel);

    }

    public void start() {
        ticker = StockTimer.getInstance();
        ticker.scheduleAtFixedRate(new TickerTask(), DELAY_PERIOD, TICK_PERIOD);
    }


    private void updateLabels() {
        currShareSnapshot = infoProvider.getAllSharesAsSnapShot();
//       namen und preise und so zusammenfügen.. :o


    }
}