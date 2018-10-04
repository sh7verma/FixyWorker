package com.app.fixy_worker.models;

import java.util.List;

public class AllServiceModel extends BaseModel{

    /**
     * response : {"categories":[{"id":"1","category_name":"Beauty","category_pic":"","description":"this is beauty","category_price":"20","total":"2","subcategories":[{"id":"5","category_name":"Body message","category_pic":"","description":"wetqertegbr","category_price":"20"},{"id":"6","category_name":"Waxing","category_pic":"","description":"Waxingn zxcmsdmfvbsdm","category_price":"10"}]},{"id":"4","category_name":"Plumber","category_pic":"","description":"Plumber","category_price":"35","total":"2","subcategories":[{"id":"7","category_name":"water pump","category_pic":"","description":"qwewqr","category_price":"25"},{"id":"8","category_name":"wash repair","category_pic":"","description":"yuryur","category_price":"35"}]}],"recommend_services":[],"offer_services":[{"current_active_date":"2018-10-03 04:10:18","id":"7","category_name":"water pump","category_pic":"","original_price":"25","offer_percentage":"10","offer_period":"3","offer_valid_date":"2018-10-03 09:10:37","fullname":"Norway","average_rating":"5","profile_pic":"http://fixyworker.com/fixyworker/uploads/39b3d9a0ddc7a28c1bc339e96c27392b.jpg"}]}
     * message : All service categories.
     * code : 200
     */

    private ResponseBean response;
    private String message;
    private int code;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ResponseBean {
        private List<CategoriesBean> categories;
        private List<?> recommend_services;
        private List<OfferServicesBean> offer_services;

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public List<?> getRecommend_services() {
            return recommend_services;
        }

        public void setRecommend_services(List<?> recommend_services) {
            this.recommend_services = recommend_services;
        }

        public List<OfferServicesBean> getOffer_services() {
            return offer_services;
        }

        public void setOffer_services(List<OfferServicesBean> offer_services) {
            this.offer_services = offer_services;
        }

        public static class CategoriesBean {
            /**
             * id : 1
             * category_name : Beauty
             * category_pic :
             * description : this is beauty
             * category_price : 20
             * total : 2
             * subcategories : [{"id":"5","category_name":"Body message","category_pic":"","description":"wetqertegbr","category_price":"20"},{"id":"6","category_name":"Waxing","category_pic":"","description":"Waxingn zxcmsdmfvbsdm","category_price":"10"}]
             */

            private String id;
            private String category_name;
            private String category_pic;
            private String description;
            private String category_price;
            private String total;
            private List<SubcategoriesBean> subcategories;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getCategory_pic() {
                return category_pic;
            }

            public void setCategory_pic(String category_pic) {
                this.category_pic = category_pic;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCategory_price() {
                return category_price;
            }

            public void setCategory_price(String category_price) {
                this.category_price = category_price;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public List<SubcategoriesBean> getSubcategories() {
                return subcategories;
            }

            public void setSubcategories(List<SubcategoriesBean> subcategories) {
                this.subcategories = subcategories;
            }

            public static class SubcategoriesBean {
                /**
                 * id : 5
                 * category_name : Body message
                 * category_pic :
                 * description : wetqertegbr
                 * category_price : 20
                 */

                private String id;
                private String category_name;
                private String category_pic;
                private String description;
                private String category_price;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCategory_name() {
                    return category_name;
                }

                public void setCategory_name(String category_name) {
                    this.category_name = category_name;
                }

                public String getCategory_pic() {
                    return category_pic;
                }

                public void setCategory_pic(String category_pic) {
                    this.category_pic = category_pic;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getCategory_price() {
                    return category_price;
                }

                public void setCategory_price(String category_price) {
                    this.category_price = category_price;
                }
            }
        }

        public static class OfferServicesBean {
            /**
             * current_active_date : 2018-10-03 04:10:18
             * id : 7
             * category_name : water pump
             * category_pic :
             * original_price : 25
             * offer_percentage : 10
             * offer_period : 3
             * offer_valid_date : 2018-10-03 09:10:37
             * fullname : Norway
             * average_rating : 5
             * profile_pic : http://fixyworker.com/fixyworker/uploads/39b3d9a0ddc7a28c1bc339e96c27392b.jpg
             */

            private String current_active_date;
            private String id;
            private String category_name;
            private String category_pic;
            private String original_price;
            private String offer_percentage;
            private String offer_period;
            private String offer_valid_date;
            private String fullname;
            private String average_rating;
            private String profile_pic;

            public String getCurrent_active_date() {
                return current_active_date;
            }

            public void setCurrent_active_date(String current_active_date) {
                this.current_active_date = current_active_date;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getCategory_pic() {
                return category_pic;
            }

            public void setCategory_pic(String category_pic) {
                this.category_pic = category_pic;
            }

            public String getOriginal_price() {
                return original_price;
            }

            public void setOriginal_price(String original_price) {
                this.original_price = original_price;
            }

            public String getOffer_percentage() {
                return offer_percentage;
            }

            public void setOffer_percentage(String offer_percentage) {
                this.offer_percentage = offer_percentage;
            }

            public String getOffer_period() {
                return offer_period;
            }

            public void setOffer_period(String offer_period) {
                this.offer_period = offer_period;
            }

            public String getOffer_valid_date() {
                return offer_valid_date;
            }

            public void setOffer_valid_date(String offer_valid_date) {
                this.offer_valid_date = offer_valid_date;
            }

            public String getFullname() {
                return fullname;
            }

            public void setFullname(String fullname) {
                this.fullname = fullname;
            }

            public String getAverage_rating() {
                return average_rating;
            }

            public void setAverage_rating(String average_rating) {
                this.average_rating = average_rating;
            }

            public String getProfile_pic() {
                return profile_pic;
            }

            public void setProfile_pic(String profile_pic) {
                this.profile_pic = profile_pic;
            }
        }
    }
}
