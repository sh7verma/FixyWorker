package com.app.fixy_worker.models;

import java.util.List;

public class AllServiceModel extends BaseModel{

    /**
     * code : 200
     * response : [{"category_id":"1","name":"Services","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci,","sub_categories":[{"sub_category_id":"1","name":"Services","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci, "},{"sub_category_id":"2","name":"HARSH GANDHI","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci, "}]},{"category_id":"2","name":"HARSH GANDHI","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci,","sub_categories":[]},{"category_id":"3","name":"param","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci,","sub_categories":[{"sub_category_id":"3","name":"param buttar","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci, "},{"sub_category_id":"8","name":"param buttar","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci, "}]},{"category_id":"4","name":"fdsfsdfsdfdsf","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci,","sub_categories":[]},{"category_id":"6","name":"sdasdsa","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci,","sub_categories":[]},{"category_id":"7","name":"asdasdasdasds","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci,","sub_categories":[]},{"category_id":"10","name":"ldkcmlsdc","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci,","sub_categories":[]}]
     */

    private int code;
    private List<ResponseBean> response;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * category_id : 1
         * name : Services
         * description : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci,
         * sub_categories : [{"sub_category_id":"1","name":"Services","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci, "},{"sub_category_id":"2","name":"HARSH GANDHI","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci, "}]
         */

        private String category_id;
        private String name="";
        private String description;
        private List<SubCategoriesBean> sub_categories;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<SubCategoriesBean> getSub_categories() {
            return sub_categories;
        }

        public void setSub_categories(List<SubCategoriesBean> sub_categories) {
            this.sub_categories = sub_categories;
        }

        public static class SubCategoriesBean {
            /**
             * sub_category_id : 1
             * name : Services
             * description : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum sagittis orci,
             */

            private String sub_category_id;
            private String name="";
            private String description;

            public String getSub_category_id() {
                return sub_category_id;
            }

            public void setSub_category_id(String sub_category_id) {
                this.sub_category_id = sub_category_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
