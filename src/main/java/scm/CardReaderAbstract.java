package scm;

import APP.DNIe;
import EventHandler.CardReaderHandler;
import model.ClientEntrance;
import model.Entrance;
import model.UserEntrance;
import utils.Coding;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

public abstract class CardReaderAbstract
{

    protected TerminalFactory factory;
    protected List<CardTerminal> terminals;
    protected CardTerminal terminal;
    protected String lastVisitCert;
    protected int lastVisitRate;
    protected boolean lastVisitForceAccess;
    public boolean manualAccess = false;
    protected DNIe dni = new DNIe();

    protected void clearData()
    {
        lastVisitCert = null;
        lastVisitRate = 0;
        lastVisitForceAccess = false;
        manualAccess = false;
        if (!dni.clearCertificates()) {
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_CRASH");
        }
    }
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
        catch (NoSuchAlgorithmException  e)
        {
            e.printStackTrace();
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_CRASH");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_NOT_FOUND");
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
            e.printStackTrace();
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
            ex.printStackTrace();
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
                if (terminal.isCardPresent() && !manualAccess)
                {
                    CardReaderHandler.getInstance().changeCardReaderStatus("READING");
                    /* Cargamos el certificado cuando se esté seleccionando la tarifa, así se agiliza el proceso.*/
                    getCertificate();
                    onCertificateLoaded();
                }
                terminal.waitForCardAbsent(0);
                onFinishEntrance();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_NOT_FOUND");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_CRASH");
        }
    }

    public void getCertificate()
    {
        lastVisitCert = Coding.encodeCertificate(dni.readCertificate());
    }

    public void checkClientBans() {
        doEntrance();
    }
    public void checkClientData() {
        checkClientBans();
    }
    public abstract void checkAccess(Entrance entrance);
    public abstract void onFinishEntrance();
    public abstract void onCertificateLoaded();
    public abstract void clearExtraData();
    public abstract void doEntrance();

    public void checkPricing() {
        doEntrance();
    }
}
