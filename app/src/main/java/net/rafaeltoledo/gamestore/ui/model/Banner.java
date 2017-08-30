package net.rafaeltoledo.gamestore.ui.model;

import com.google.firebase.storage.StorageReference;

public class Banner {

    private StorageReference image;
    private String title;

    protected Banner() {
    }

    public Banner(StorageReference image, String title) {
        this.image = image;
        this.title = title;
    }

    public StorageReference getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
