package com.moodshop.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbCartExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbCartExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Long value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Long value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Long value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Long value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Long value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Long> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Long> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Long value1, Long value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Long value1, Long value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andItemidIsNull() {
            addCriterion("itemId is null");
            return (Criteria) this;
        }

        public Criteria andItemidIsNotNull() {
            addCriterion("itemId is not null");
            return (Criteria) this;
        }

        public Criteria andItemidEqualTo(Long value) {
            addCriterion("itemId =", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotEqualTo(Long value) {
            addCriterion("itemId <>", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidGreaterThan(Long value) {
            addCriterion("itemId >", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidGreaterThanOrEqualTo(Long value) {
            addCriterion("itemId >=", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidLessThan(Long value) {
            addCriterion("itemId <", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidLessThanOrEqualTo(Long value) {
            addCriterion("itemId <=", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidIn(List<Long> values) {
            addCriterion("itemId in", values, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotIn(List<Long> values) {
            addCriterion("itemId not in", values, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidBetween(Long value1, Long value2) {
            addCriterion("itemId between", value1, value2, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotBetween(Long value1, Long value2) {
            addCriterion("itemId not between", value1, value2, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemtitleIsNull() {
            addCriterion("itemTitle is null");
            return (Criteria) this;
        }

        public Criteria andItemtitleIsNotNull() {
            addCriterion("itemTitle is not null");
            return (Criteria) this;
        }

        public Criteria andItemtitleEqualTo(String value) {
            addCriterion("itemTitle =", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleNotEqualTo(String value) {
            addCriterion("itemTitle <>", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleGreaterThan(String value) {
            addCriterion("itemTitle >", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleGreaterThanOrEqualTo(String value) {
            addCriterion("itemTitle >=", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleLessThan(String value) {
            addCriterion("itemTitle <", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleLessThanOrEqualTo(String value) {
            addCriterion("itemTitle <=", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleLike(String value) {
            addCriterion("itemTitle like", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleNotLike(String value) {
            addCriterion("itemTitle not like", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleIn(List<String> values) {
            addCriterion("itemTitle in", values, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleNotIn(List<String> values) {
            addCriterion("itemTitle not in", values, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleBetween(String value1, String value2) {
            addCriterion("itemTitle between", value1, value2, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleNotBetween(String value1, String value2) {
            addCriterion("itemTitle not between", value1, value2, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemimageIsNull() {
            addCriterion("itemImage is null");
            return (Criteria) this;
        }

        public Criteria andItemimageIsNotNull() {
            addCriterion("itemImage is not null");
            return (Criteria) this;
        }

        public Criteria andItemimageEqualTo(String value) {
            addCriterion("itemImage =", value, "itemimage");
            return (Criteria) this;
        }

        public Criteria andItemimageNotEqualTo(String value) {
            addCriterion("itemImage <>", value, "itemimage");
            return (Criteria) this;
        }

        public Criteria andItemimageGreaterThan(String value) {
            addCriterion("itemImage >", value, "itemimage");
            return (Criteria) this;
        }

        public Criteria andItemimageGreaterThanOrEqualTo(String value) {
            addCriterion("itemImage >=", value, "itemimage");
            return (Criteria) this;
        }

        public Criteria andItemimageLessThan(String value) {
            addCriterion("itemImage <", value, "itemimage");
            return (Criteria) this;
        }

        public Criteria andItemimageLessThanOrEqualTo(String value) {
            addCriterion("itemImage <=", value, "itemimage");
            return (Criteria) this;
        }

        public Criteria andItemimageLike(String value) {
            addCriterion("itemImage like", value, "itemimage");
            return (Criteria) this;
        }

        public Criteria andItemimageNotLike(String value) {
            addCriterion("itemImage not like", value, "itemimage");
            return (Criteria) this;
        }

        public Criteria andItemimageIn(List<String> values) {
            addCriterion("itemImage in", values, "itemimage");
            return (Criteria) this;
        }

        public Criteria andItemimageNotIn(List<String> values) {
            addCriterion("itemImage not in", values, "itemimage");
            return (Criteria) this;
        }

        public Criteria andItemimageBetween(String value1, String value2) {
            addCriterion("itemImage between", value1, value2, "itemimage");
            return (Criteria) this;
        }

        public Criteria andItemimageNotBetween(String value1, String value2) {
            addCriterion("itemImage not between", value1, value2, "itemimage");
            return (Criteria) this;
        }

        public Criteria andItempriceIsNull() {
            addCriterion("itemPrice is null");
            return (Criteria) this;
        }

        public Criteria andItempriceIsNotNull() {
            addCriterion("itemPrice is not null");
            return (Criteria) this;
        }

        public Criteria andItempriceEqualTo(Long value) {
            addCriterion("itemPrice =", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceNotEqualTo(Long value) {
            addCriterion("itemPrice <>", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceGreaterThan(Long value) {
            addCriterion("itemPrice >", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceGreaterThanOrEqualTo(Long value) {
            addCriterion("itemPrice >=", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceLessThan(Long value) {
            addCriterion("itemPrice <", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceLessThanOrEqualTo(Long value) {
            addCriterion("itemPrice <=", value, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceIn(List<Long> values) {
            addCriterion("itemPrice in", values, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceNotIn(List<Long> values) {
            addCriterion("itemPrice not in", values, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceBetween(Long value1, Long value2) {
            addCriterion("itemPrice between", value1, value2, "itemprice");
            return (Criteria) this;
        }

        public Criteria andItempriceNotBetween(Long value1, Long value2) {
            addCriterion("itemPrice not between", value1, value2, "itemprice");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Date value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Date value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Date value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Date value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Date value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Date> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Date> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Date value1, Date value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Date value1, Date value2) {
            addCriterion("updated not between", value1, value2, "updated");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}