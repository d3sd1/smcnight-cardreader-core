package model;

public class ClientEntrance extends Entrance {
    private boolean vip;
    private boolean forceaccess;
    private ClientEntrancePricing rate;
    private Client client;

    public boolean isforceaccess() {
        return forceaccess;
    }

    public void setforceaccess(boolean forceaccess) {
        this.forceaccess = forceaccess;
    }

    public ClientEntrancePricing getRate() {
        return rate;
    }

    public void setRate(ClientEntrancePricing rate) {
        this.rate = rate;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public ClientEntrance(Client client, boolean forceaccess, boolean vip, EntranceType type, ClientEntrancePricing rate)
    {
        this.client = client;
        this.forceaccess = forceaccess;
        this.type = type;
        this.vip = vip;
        this.rate = rate;
    }
    public ClientEntrance() {
        this.rate = new ClientEntrancePricing();
    }

}
