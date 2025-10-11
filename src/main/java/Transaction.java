public class Transaction {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount; //fields

    //constructor
    public Transaction (String date, String time, String description, double amount){
        this.date = date;
        this.time = time;
        this.description = description;
        this. amount = amount;
    }

    //region getters
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }
    public void setTime() {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    //endregion

    //overriding toString() to display field data into formatted String
    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%d", date, time, description,vendor, amount);
    }
}
