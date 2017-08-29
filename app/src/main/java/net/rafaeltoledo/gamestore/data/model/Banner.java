package net.rafaeltoledo.gamestore.data.model;

public class Banner {

    private final String image;
    private final String title;

    public Banner(String image, String title) {
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
