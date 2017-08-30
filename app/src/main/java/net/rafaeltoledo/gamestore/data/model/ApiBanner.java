package net.rafaeltoledo.gamestore.data.model;

public class ApiBanner {

    private String image;
    private String title;

    protected ApiBanner() {
    }

    public ApiBanner(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
