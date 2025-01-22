package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrganizationResponse {

    private int count;
    private String next;
    private String previous;
    private List<Organization> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Organization> getResults() { // Этот метод возвращает список организаций
        return results;
    }

    public static class Organization {
        private int id;
        private String title;
        private String tin;
        private String description;
        @JsonProperty("is_body_empty")
        private boolean isBodyEmpty;
        @JsonProperty("region_id")
        private int regionId;
        private String form;
        @JsonProperty("format_id")
        private String formatId;
        @JsonProperty("site_url")
        private String siteUrl;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("base_year")
        private String baseYear;
        private String uuid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTin() {
            return tin;
        }

        public void setTin(String tin) {
            this.tin = tin;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isBodyEmpty() {
            return isBodyEmpty;
        }

        public void setBodyEmpty(boolean bodyEmpty) {
            isBodyEmpty = bodyEmpty;
        }

        public int getRegionId() {
            return regionId;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }

        public String getForm() {
            return form;
        }

        public void setForm(String form) {
            this.form = form;
        }

        public String getFormatId() {
            return formatId;
        }

        public void setFormatId(String formatId) {
            this.formatId = formatId;
        }

        public String getSiteUrl() {
            return siteUrl;
        }

        public void setSiteUrl(String siteUrl) {
            this.siteUrl = siteUrl;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getBaseYear() {
            return baseYear;
        }

        public void setBaseYear(String baseYear) {
            this.baseYear = baseYear;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
}
