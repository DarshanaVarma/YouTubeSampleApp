package com.example.demo.youtubesampleapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 12/1/2017.
 */

public class VideoDetailsPojo {
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public class Item {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("snippet")
        @Expose
        private Snippet snippet;
        @SerializedName("contentDetails")
        @Expose
        private ContentDetails contentDetails;
        @SerializedName("statistics")
        @Expose
        private Statistics statistics;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }

        public ContentDetails getContentDetails() {
            return contentDetails;
        }

        public void setContentDetails(ContentDetails contentDetails) {
            this.contentDetails = contentDetails;
        }

        public Statistics getStatistics() {
            return statistics;
        }

        public void setStatistics(Statistics statistics) {
            this.statistics = statistics;
        }

    }


    public class Statistics {

        @SerializedName("viewCount")
        @Expose
        private String viewCount;
        @SerializedName("likeCount")
        @Expose
        private String likeCount;
        @SerializedName("dislikeCount")
        @Expose
        private String dislikeCount;
        @SerializedName("favoriteCount")
        @Expose
        private String favoriteCount;
        @SerializedName("commentCount")
        @Expose
        private String commentCount;

        public String getViewCount() {
            return viewCount;
        }

        public void setViewCount(String viewCount) {
            this.viewCount = viewCount;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getDislikeCount() {
            return dislikeCount;
        }

        public void setDislikeCount(String dislikeCount) {
            this.dislikeCount = dislikeCount;
        }

        public String getFavoriteCount() {
            return favoriteCount;
        }

        public void setFavoriteCount(String favoriteCount) {
            this.favoriteCount = favoriteCount;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

    }


    public class ContentDetails {

        @SerializedName("duration")
        @Expose
        private String duration;
        @SerializedName("dimension")
        @Expose
        private String dimension;
        @SerializedName("definition")
        @Expose
        private String definition;
        @SerializedName("caption")
        @Expose
        private String caption;
        @SerializedName("licensedContent")
        @Expose
        private Boolean licensedContent;
        @SerializedName("projection")
        @Expose
        private String projection;

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDimension() {
            return dimension;
        }

        public void setDimension(String dimension) {
            this.dimension = dimension;
        }

        public String getDefinition() {
            return definition;
        }

        public void setDefinition(String definition) {
            this.definition = definition;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public Boolean getLicensedContent() {
            return licensedContent;
        }

        public void setLicensedContent(Boolean licensedContent) {
            this.licensedContent = licensedContent;
        }

        public String getProjection() {
            return projection;
        }

        public void setProjection(String projection) {
            this.projection = projection;
        }

    }

    public class Snippet {

        @SerializedName("publishedAt")
        @Expose
        private String publishedAt;
        @SerializedName("channelId")
        @Expose
        private String channelId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("thumbnails")
        @Expose
        private Thumbnails thumbnails;
        @SerializedName("channelTitle")
        @Expose
        private String channelTitle;
        @SerializedName("tags")
        @Expose
        private List<String> tags = null;
        @SerializedName("categoryId")
        @Expose
        private String categoryId;
        @SerializedName("liveBroadcastContent")
        @Expose
        private String liveBroadcastContent;


        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Thumbnails getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(Thumbnails thumbnails) {
            this.thumbnails = thumbnails;
        }

        public String getChannelTitle() {
            return channelTitle;
        }

        public void setChannelTitle(String channelTitle) {
            this.channelTitle = channelTitle;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getLiveBroadcastContent() {
            return liveBroadcastContent;
        }

        public void setLiveBroadcastContent(String liveBroadcastContent) {
            this.liveBroadcastContent = liveBroadcastContent;
        }


        public class Thumbnails {

            @SerializedName("medium")
            @Expose
            private Medium medium;
            @SerializedName("high")
            @Expose
            private High high;
            @SerializedName("maxres")
            @Expose
            private Maxres maxres;


            public Medium getMedium() {
                return medium;
            }

            public void setMedium(Medium medium) {
                this.medium = medium;
            }

            public High getHigh() {
                return high;
            }

            public void setHigh(High high) {
                this.high = high;
            }

            public Maxres getMaxres() {
                return maxres;
            }

            public void setMaxres(Maxres maxres) {
                this.maxres = maxres;
            }

            public class Medium {

                @SerializedName("url")
                @Expose
                private String url;
                @SerializedName("width")
                @Expose
                private Integer width;
                @SerializedName("height")
                @Expose
                private Integer height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public Integer getWidth() {
                    return width;
                }

                public void setWidth(Integer width) {
                    this.width = width;
                }

                public Integer getHeight() {
                    return height;
                }

                public void setHeight(Integer height) {
                    this.height = height;
                }

            }

            public class Maxres {

                @SerializedName("url")
                @Expose
                private String url;
                @SerializedName("width")
                @Expose
                private Integer width;
                @SerializedName("height")
                @Expose
                private Integer height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public Integer getWidth() {
                    return width;
                }

                public void setWidth(Integer width) {
                    this.width = width;
                }

                public Integer getHeight() {
                    return height;
                }

                public void setHeight(Integer height) {
                    this.height = height;
                }

            }

            public class High {

                @SerializedName("url")
                @Expose
                private String url;
                @SerializedName("width")
                @Expose
                private Integer width;
                @SerializedName("height")
                @Expose
                private Integer height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public Integer getWidth() {
                    return width;
                }

                public void setWidth(Integer width) {
                    this.width = width;
                }

                public Integer getHeight() {
                    return height;
                }

                public void setHeight(Integer height) {
                    this.height = height;
                }

            }

        }

    }

}
