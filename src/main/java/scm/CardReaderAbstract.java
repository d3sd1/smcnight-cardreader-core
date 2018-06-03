package scm;

import APP.DNIe;
import EventHandler.CardReaderHandler;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

public abstract class CardReaderAbstract
{

    protected TerminalFactory factory;
    protected List<CardTerminal> terminals;
    protected DNIe dni = new DNIe();
    protected CardTerminal terminal;

    public void initialize()
    {
        initCardReader();
        checkReaderNumber();
        loadTerminal();
        checkNoCardsInTerminal();
        initTerminal();
    }

    public void initCardReader()
    {
        CardReaderHandler.getInstance().changeCardReaderStatus("LOADED");
        try
        {
            factory = TerminalFactory.getInstance("PC/SC", null);
            terminals = factory.terminals().list();
        }
        catch (CardException e)
        {
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_NOT_FOUND");
        }
        catch (NoSuchAlgorithmException  e)
        {
            System.out.println(e);
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_CRASH");
        }
    }

    public void checkReaderNumber()
    {
        if (terminals == null || terminals.isEmpty())
        {
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_NOT_FOUND");
        }
    }

    public void loadTerminal()
    {
        try
        {
            terminal = terminals.get(0);
        }
        catch (Exception e)
        {
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_NOT_FOUND");
        }
    }

    public void checkNoCardsInTerminal()
    {
        try
        {
            if (terminal.isCardPresent())
            {
                CardReaderHandler.getInstance().changeCardReaderStatus("EXTRACT");
                terminal.waitForCardAbsent(0);
            }
        }
        catch (CardException | NullPointerException ex)
        {
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_CRASH");
        }
    }

    public void initTerminal()
    {
        try
        {
            while (true)
            {
                CardReaderHandler.getInstance().changeCardReaderStatus("WAITING");
                terminal.waitForCardPresent(0);
                if (terminal.isCardPresent())
                {
                    this.checkCards();
                }
                terminal.waitForCardAbsent(0);
            }
        }
        catch (NullPointerException e)
        {
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_NOT_FOUND");
        }
        catch (CardException e)
        {
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_CRASH");
        }
    }

    public void checkCards()
    {
        CardReaderHandler.getInstance().changeCardReaderStatus("READING");

        X509Certificate cert = dni.readCertificate();
        this.checkCards(cert);
    }

    public void checkCards(X509Certificate cert)
    {
        this.checkCards(cert, false);
    }

    public abstract void checkCards(X509Certificate cert, boolean forceAccess);
}
