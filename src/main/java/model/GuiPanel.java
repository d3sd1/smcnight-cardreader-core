package model;

public class GuiPanel
{
    private String bgColor;
    private String textColor;
    private String mainText;
    private String bottomText;
    private String action;
    private int timeout;

    public GuiPanel(String bgColor, String textColor, String mainText, String bottomText, String action, int timeout)
    {
        this.bgColor = bgColor;
        this.textColor = textColor;
        this.mainText = mainText;
        this.bottomText = bottomText;
        this.action = action;
        this.timeout = timeout;
    }
    public String getBgColor()
    {
        return bgColor;
    }

    public void setBgColor(String bgColor)
    {
        this.bgColor = bgColor;
    }

    public String getTextColor()
    {
        return textColor;
    }

    public void setTextColor(String textColor)
    {
        this.textColor = textColor;
    }

    public String getMainText()
    {
        return mainText;
    }

    public void setMainText(String mainText)
    {
        this.mainText = mainText;
    }

    public String getBottomText()
    {
        return bottomText;
    }

    public void setBottomText(String bottomText)
    {
        this.bottomText = bottomText;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public int getTimeout()
    {
        return timeout;
    }

    public void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }
    
}
