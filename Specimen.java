public class Specimen {
    private String imagePath;
    private String date;
    private String species;
    private double pronotumWidth;
    private double hornLength;
    private double hornWidth;
    private String name;

    // Constructor
    public Specimen(String imagePath, String date, String species, double pronotumWidth, double hornLength, double hornWidth) {
        this.imagePath = imagePath;
        this.date = date;
        this.species = species;
        this.pronotumWidth = pronotumWidth;
        this.hornLength = hornLength;
        this.hornWidth = hornWidth;
    }

    // Getters and setters
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getPronotumWidth() {
        return pronotumWidth;
    }

    public void setPronotumWidth(double pronotumWidth) {
        this.pronotumWidth = pronotumWidth;
    }

    public double getHornLength() {
        return hornLength;
    }

    public void setHornLength(double hornLength) {
        this.hornLength = hornLength;
    }

    public double getHornWidth() {
        return hornWidth;
    }

    public void setHornWidth(double hornWidth) {
        this.hornWidth = hornWidth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
