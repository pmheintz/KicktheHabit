package net.paulheintz.kickthehabit;

import java.util.ArrayList;
import java.util.List;

public class Smoker {
    private long startDate;
    private double pricePerPack = 5.50;
    private int cigsPerPack = 20;
    private double pricePerCig = 0;
    private String[] reasonsToQuit = new String[10];

    // Default constructor
    Smoker() {
        // Set the price per cigarette
        setPricePerCig();
        // Add default reasons to quit to array
        reasonsToQuit[0] = "On average in the US, a pack a day smoker spends over $2000 on cigarettes!";
        reasonsToQuit[1] = "In 2 weeks – 3 months of not smoking, circulation and lung function " +
                "improve and heart attack risk begins to drop.";
        reasonsToQuit[2] = "After 1 year of not smoking, risk of coronary heart disease is cut in half.";
        reasonsToQuit[3] = "In 2–5 years of not smoking, risk of cancer of mouth, throat, esophagus, " +
                "and bladder are cut in half plus stroke risk is reduced to that of a nonsmoker.";
        reasonsToQuit[4] = "After 10 years you're half as likely to die from lung cancer and the risk " +
                "of kidney or pancreatic cancer decreases.";
        reasonsToQuit[5] = "Testing";
        reasonsToQuit[6] = "Testing";
        reasonsToQuit[7] = "Testing";
        reasonsToQuit[8] = "Testing";
    }

    // Smoker class getters and setters
    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public double getPricePerPack() {
        return pricePerPack;
    }

    public void setPricePerPack(double pricePerPack) {
        this.pricePerPack = pricePerPack;
        this.setPricePerCig();
    }

    public int getCigsPerPack() {
        return cigsPerPack;
    }

    public void setCigsPerPack(int cigsPerPack) {
        this.cigsPerPack = cigsPerPack;
        this.setPricePerCig();
    }

    public double getPricePerCig() {
        return pricePerCig;
    }

    public void setPricePerCig() {
        if (pricePerPack != 0 && cigsPerPack != 0) {
            this.pricePerCig = this.pricePerPack / this.cigsPerPack;
        } else {
            this.pricePerCig = 0;
        }
    }

    public void setReasonsToQuit(String[] reasons) {
        this.reasonsToQuit = new String[reasons.length];
        System.arraycopy(reasons, 0, this.reasonsToQuit, 0, reasons.length);
    }

    public String[] getReasonsToQuit() {
        String[] copy = new String[this.reasonsToQuit.length];
        System.arraycopy(this.reasonsToQuit, 0, copy, 0, copy.length);
        return copy;
    }
}
