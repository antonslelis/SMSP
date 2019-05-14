package org.solent.group.project.model;

/**
 *
 * @author Andre
 */

public class Invoice {

    private double price;

    private boolean paid;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
