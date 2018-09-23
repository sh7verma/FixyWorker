package com.app.fixy_worker.models;

import java.util.List;

/**
 * Created by app on 17-Nov-17.
 */

public class GooglePlaceModal {
    /**
     * html_attributions : []
     * results : [{"geometry":{"location":{"lat":30.65864179999999,"lng":76.736628},"viewport":{"northeast":{"lat":30.65999078029149,"lng":76.7379769802915},"southwest":{"lat":30.65729281970849,"lng":76.73527901970849}}},"icon":"https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png","id":"af7a834aabbdd1f4eac4ae0aad5ab2f9321b5031","name":"REMAX Regional","opening_hours":{"open_now":true,"weekday_text":[]},"photos":[{"height":4160,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/114781162446697797808/photos\">kiran sharma<\/a>"],"photo_reference":"CmRaAAAAhkWteFWPREGDe3k2nTipa_9wptuWmOaM-sSSdqqE-NS-B9M-mlwU5A94cOYf83BhAvloZw6KLrmT2E_wcqg4AUdCaFGnT_4PDTmj6vWYNX6MDHZWXZjSOE4Jwu1JXrxnEhBE1kWPsBi37SfusaxZKyhEGhR3LZ3w07Iy7bmij3BGE-8D3AiIeA","width":2340}],"place_id":"ChIJUx1oKuPrDzkRftBHt2HayVs","rating":2,"reference":"CmRRAAAAKzSZ7Sz4tyAAdxGDF5uHpAe7dhmvc0uD1rBonslPTrvee7VmL7Gr4P-dwwUVmYl-XFVqmDYMmjiUCwEko2jEZfvlwuEyvOSIJyR8lK7Kly5HtIOD41KaLBELXL_jMvqOEhDrYjuHnoNVZ9vRi2sB4JIeGhRr2yiAxwkxSj5-ETHTHJ1txnKfYQ","scope":"GOOGLE","types":["real_estate_agency","point_of_interest","establishment"],"vicinity":"National Highway 5, Sector 82, JLPL Industrial Area"},{"geometry":{"location":{"lat":30.6613112,"lng":76.7337242},"viewport":{"northeast":{"lat":30.6626601802915,"lng":76.73507318029151},"southwest":{"lat":30.6599622197085,"lng":76.73237521970849}}},"icon":"https://maps.gstatic.com/mapfiles/place_api/icons/shopping-71.png","id":"e1c7170bc7bb5a42b3fe2ede286f6ceef0d1fe53","name":"Krishna ISUZU","opening_hours":{"open_now":true,"weekday_text":[]},"photos":[{"height":3456,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/113099982358029224465/photos\">1neclick Online Pvt Ltd<\/a>"],"photo_reference":"CmRaAAAAVUQxMF3tklK8lGi-yR2WNe_Dd1-jUls-N1RFFOJtPxpmNbOECU1AwaVduSWlrjaUsJ7VQLP2YobaM-9InyuqZc6BPcLkETrhQJ_5Pg56cw3EF3rOsXERjrtEnefVPWeuEhCX8Vga2_cKvYTLkmQzma83GhTvqENNqVHgQSuUBbrpOsiheKIVzw","width":5184}],"place_id":"ChIJn8O4f_3rDzkRd1EC6XqU-UE","rating":4.3,"reference":"CmRRAAAAbmCxb8jRBzKauY51Uoumh8qFxKVB13ZMbA4MUNnIADEUbSvJxxR1bV5oCrVggDD5qxTQ9cYY8UVcjNclKevcGhZDJjHE9CwLd3lrynjgYZDKNOGbbAbudtTJBky5EVlREhB_wCJqHGxCi_Lw8Oe19azPGhS8ZYMFvlsWmZf-5iDmljI3XTyt9g","scope":"GOOGLE","types":["car_dealer","store","point_of_interest","establishment"],"vicinity":"Plot No.3, JLPL Industrial Estate, Sector 82, Aerocity- Airport Road, Sahibzada Ajit Singh Nagar"}]
     * status : OK
     */

   /* private String status;
    private List<?> html_attributions;
    private List<ResultsBean> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<?> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<?> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {*/
        /**
         * geometry : {"location":{"lat":30.65864179999999,"lng":76.736628},"viewport":{"northeast":{"lat":30.65999078029149,"lng":76.7379769802915},"southwest":{"lat":30.65729281970849,"lng":76.73527901970849}}}
         * icon : https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png
         * id : af7a834aabbdd1f4eac4ae0aad5ab2f9321b5031
         * name : REMAX Regional
         * opening_hours : {"open_now":true,"weekday_text":[]}
         * photos : [{"height":4160,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/114781162446697797808/photos\">kiran sharma<\/a>"],"photo_reference":"CmRaAAAAhkWteFWPREGDe3k2nTipa_9wptuWmOaM-sSSdqqE-NS-B9M-mlwU5A94cOYf83BhAvloZw6KLrmT2E_wcqg4AUdCaFGnT_4PDTmj6vWYNX6MDHZWXZjSOE4Jwu1JXrxnEhBE1kWPsBi37SfusaxZKyhEGhR3LZ3w07Iy7bmij3BGE-8D3AiIeA","width":2340}]
         * place_id : ChIJUx1oKuPrDzkRftBHt2HayVs
         * rating : 2
         * reference : CmRRAAAAKzSZ7Sz4tyAAdxGDF5uHpAe7dhmvc0uD1rBonslPTrvee7VmL7Gr4P-dwwUVmYl-XFVqmDYMmjiUCwEko2jEZfvlwuEyvOSIJyR8lK7Kly5HtIOD41KaLBELXL_jMvqOEhDrYjuHnoNVZ9vRi2sB4JIeGhRr2yiAxwkxSj5-ETHTHJ1txnKfYQ
         * scope : GOOGLE
         * types : ["real_estate_agency","point_of_interest","establishment"]
         * vicinity : National Highway 5, Sector 82, JLPL Industrial Area
         */
/*

        private GeometryBean geometry;
        private String icon;
        private String id;
        private String name;
        private OpeningHoursBean opening_hours;
        private String place_id;
        private int rating;
        private String reference;
        private String scope;
        private String vicinity;
        private List<PhotosBean> photos;
        private List<String> types;

        public GeometryBean getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryBean geometry) {
            this.geometry = geometry;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getid() {
            return id;
        }

        public void setid(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public OpeningHoursBean getOpening_hours() {
            return opening_hours;
        }

        public void setOpening_hours(OpeningHoursBean opening_hours) {
            this.opening_hours = opening_hours;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getVicinity() {
            return vicinity;
        }

        public void setVicinity(String vicinity) {
            this.vicinity = vicinity;
        }

        public List<PhotosBean> getPhotos() {
            return photos;
        }

        public void setPhotos(List<PhotosBean> photos) {
            this.photos = photos;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class GeometryBean {
            */
/**
             * location : {"lat":30.65864179999999,"lng":76.736628}
             * viewport : {"northeast":{"lat":30.65999078029149,"lng":76.7379769802915},"southwest":{"lat":30.65729281970849,"lng":76.73527901970849}}
             *//*


            private LocationBean location;
            private ViewportBean viewport;

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public ViewportBean getViewport() {
                return viewport;
            }

            public void setViewport(ViewportBean viewport) {
                this.viewport = viewport;
            }

            public static class LocationBean {
                */
/**
                 * lat : 30.65864179999999
                 * lng : 76.736628
                 *//*


                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class ViewportBean {
                */
/**
                 * northeast : {"lat":30.65999078029149,"lng":76.7379769802915}
                 * southwest : {"lat":30.65729281970849,"lng":76.73527901970849}
                 *//*


                private NortheastBean northeast;
                private SouthwestBean southwest;

                public NortheastBean getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBean northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBean getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBean southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBean {
                    */
/**
                     * lat : 30.65999078029149
                     * lng : 76.7379769802915
                     *//*


                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBean {
                    */
/**
                     * lat : 30.65729281970849
                     * lng : 76.73527901970849
                     *//*


                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }
        }

        public static class OpeningHoursBean {
            */
/**
             * open_now : true
             * weekday_text : []
             *//*


            private boolean open_now;
            private List<?> weekday_text;

            public boolean isOpen_now() {
                return open_now;
            }

            public void setOpen_now(boolean open_now) {
                this.open_now = open_now;
            }

            public List<?> getWeekday_text() {
                return weekday_text;
            }

            public void setWeekday_text(List<?> weekday_text) {
                this.weekday_text = weekday_text;
            }
        }

        public static class PhotosBean {
            */
/**
             * height : 4160
             * html_attributions : ["<a href=\"https://maps.google.com/maps/contrib/114781162446697797808/photos\">kiran sharma<\/a>"]
             * photo_reference : CmRaAAAAhkWteFWPREGDe3k2nTipa_9wptuWmOaM-sSSdqqE-NS-B9M-mlwU5A94cOYf83BhAvloZw6KLrmT2E_wcqg4AUdCaFGnT_4PDTmj6vWYNX6MDHZWXZjSOE4Jwu1JXrxnEhBE1kWPsBi37SfusaxZKyhEGhR3LZ3w07Iy7bmij3BGE-8D3AiIeA
             * width : 2340
             *//*


            private int height;
            private String photo_reference;
            private int width;
            private List<String> html_attributions;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getPhoto_reference() {
                return photo_reference;
            }

            public void setPhoto_reference(String photo_reference) {
                this.photo_reference = photo_reference;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public List<String> getHtml_attributions() {
                return html_attributions;
            }

            public void setHtml_attributions(List<String> html_attributions) {
                this.html_attributions = html_attributions;
            }
        }
    }
*/



    private String status;
    private List<PredictionsBean> predictions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PredictionsBean> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<PredictionsBean> predictions) {
        this.predictions = predictions;
    }

    public static class PredictionsBean {


        private String description;
        private String id;
        private String place_id;
        private String reference;
        private StructuredFormattingBean structured_formatting;
        private List<MatchedSubstringsBean> matched_substrings;
        private List<TermsBean> terms;
        private List<String> types;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public StructuredFormattingBean getStructured_formatting() {
            return structured_formatting;
        }

        public void setStructured_formatting(StructuredFormattingBean structured_formatting) {
            this.structured_formatting = structured_formatting;
        }

        public List<MatchedSubstringsBean> getMatched_substrings() {
            return matched_substrings;
        }

        public void setMatched_substrings(List<MatchedSubstringsBean> matched_substrings) {
            this.matched_substrings = matched_substrings;
        }

        public List<TermsBean> getTerms() {
            return terms;
        }

        public void setTerms(List<TermsBean> terms) {
            this.terms = terms;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class StructuredFormattingBean {


            private String main_text;
            private String secondary_text;
            private List<MainTextMatchedSubstringsBean> main_text_matched_substrings;

            public String getMain_text() {
                return main_text;
            }

            public void setMain_text(String main_text) {
                this.main_text = main_text;
            }

            public String getSecondary_text() {
                return secondary_text;
            }

            public void setSecondary_text(String secondary_text) {
                this.secondary_text = secondary_text;
            }

            public List<MainTextMatchedSubstringsBean> getMain_text_matched_substrings() {
                return main_text_matched_substrings;
            }

            public void setMain_text_matched_substrings(List<MainTextMatchedSubstringsBean> main_text_matched_substrings) {
                this.main_text_matched_substrings = main_text_matched_substrings;
            }

            public static class MainTextMatchedSubstringsBean {

                private int length;
                private int offset;

                public int getLength() {
                    return length;
                }

                public void setLength(int length) {
                    this.length = length;
                }

                public int getOffset() {
                    return offset;
                }

                public void setOffset(int offset) {
                    this.offset = offset;
                }
            }
        }

        public static class MatchedSubstringsBean {

            private int length;
            private int offset;

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }
        }

        public static class TermsBean {


            private int offset;
            private String value;

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
