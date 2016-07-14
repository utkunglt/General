package com.uk.khanhnguyen.toeicwords.models;

/**
 * Created by khanhnt on 7/14/2016.
 */
public class BookModel {

    // private variables
    private int id;
    private String title;
    private String description;
    private String author;
    private String imageUrl;

    /**
     * Instantiates a new Book model.
     */
    public BookModel() {
    }

    /**
     * Instantiates a new Book model.
     *
     * @param id          the id
     * @param title       the title
     * @param description the description
     * @param author      the author
     * @param imageUrl    the image url
     */
    public BookModel(int id, String title, String description, String author, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.imageUrl = imageUrl;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets image url.
     *
     * @param imageUrl the image url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
