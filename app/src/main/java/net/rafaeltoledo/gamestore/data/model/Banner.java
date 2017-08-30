package net.rafaeltoledo.gamestore.data.model;

public class Banner {

    private String image;
    private String title;

    protected Banner() {
    }

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
