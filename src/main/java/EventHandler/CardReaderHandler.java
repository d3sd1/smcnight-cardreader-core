package EventHandler;

public class CardReaderHandler
{
    
    private static CardReaderListener instance = null;
    public static boolean CARD_READER_ERROR = false;

    public static CardReaderListener getInstance()
    {
        if (instance == null)
        {
            instance = new CardReaderListener();
        }
        return instance;
    }
    public static boolean readerError()
    {
        return CARD_READER_ERROR;
    }
    public static void setReaderError()
    {
        CARD_READER_ERROR = true;
    }
    
}