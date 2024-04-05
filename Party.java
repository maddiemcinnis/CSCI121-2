public class Party {
    private int guestsExpected;
    public void setGuestsExpected(int guestsExpected) {

        this.guestsExpected = guestsExpected;
    }
    public void displayInvitation() {

        System.out.println("Please come to my party!");
}
public int getGuestsExpected() {
        return guestsExpected;
    }
}
