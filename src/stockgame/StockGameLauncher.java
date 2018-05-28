package stockgame;

import stockgame.core.AccountManagerImpl;
import stockgame.core.RandomStockPriceProvider;
import stockgame.ui.console.StockGameCommandProcessor;
import stockgame.view.StockPriceViewer;

public class StockGameLauncher {

    public static void main(String[] args) {

        RandomStockPriceProvider provider = new RandomStockPriceProvider();
        provider.newShare("bmw", 12345);
        provider.newShare("google", 12345);
        provider.newShare("microsoft", 12345);
        provider.newShare("handsan", 12345);
        provider.newShare("niveau", 12345);


        AccountManagerImpl accmgr = new AccountManagerImpl(provider);

        StockGameCommandProcessor cmd = new StockGameCommandProcessor(accmgr);

        StockPriceViewer viewer = new StockPriceViewer(provider);
        accmgr.startUpdate();
        viewer.start();
        try {
            cmd.process();
        } catch (Exception e) {
//            System.out.println("ERROR");                   //TODO echtes catchen
            e.printStackTrace();
        }


//	    	RandomStockPriceProvider provider = new RandomStockPriceProvider();
////	    	ConstStockPriceProvider provider = new ConstStockPriceProvider();

//	        

//	    	AccountManagerImpl accmgr = new AccountManagerImpl(provider);
//	    	

//	    	
//	    	

//	    	
//	        try {
//				CommandProcessor cmd = new CommandProcessor(accmgr);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	        
//	        System.out.println("GUGUCK");
    }
}
