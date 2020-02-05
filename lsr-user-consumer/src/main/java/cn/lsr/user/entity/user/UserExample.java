package cn.lsr.user.entity.user;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(String value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(String value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(String value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(String value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(String value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(String value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLike(String value) {
            addCriterion("uid like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotLike(String value) {
            addCriterion("uid not like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<String> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<String> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(String value1, String value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(String value1, String value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andUsernickIsNull() {
            addCriterion("usernick is null");
            return (Criteria) this;
        }

        public Criteria andUsernickIsNotNull() {
            addCriterion("usernick is not null");
            return (Criteria) this;
        }

        public Criteria andUsernickEqualTo(String value) {
            addCriterion("usernick =", value, "usernick");
            return (Criteria) this;
        }

        public Criteria andUsernickNotEqualTo(String value) {
            addCriterion("usernick <>", value, "usernick");
            return (Criteria) this;
        }

        public Criteria andUsernickGreaterThan(String value) {
            addCriterion("usernick >", value, "usernick");
            return (Criteria) this;
        }

        public Criteria andUsernickGreaterThanOrEqualTo(String value) {
            addCriterion("usernick >=", value, "usernick");
            return (Criteria) this;
        }

        public Criteria andUsernickLessThan(String value) {
            addCriterion("usernick <", value, "usernick");
            return (Criteria) this;
        }

        public Criteria andUsernickLessThanOrEqualTo(String value) {
            addCriterion("usernick <=", value, "usernick");
            return (Criteria) this;
        }

        public Criteria andUsernickLike(String value) {
            addCriterion("usernick like", value, "usernick");
            return (Criteria) this;
        }

        public Criteria andUsernickNotLike(String value) {
            addCriterion("usernick not like", value, "usernick");
            return (Criteria) this;
        }

        public Criteria andUsernickIn(List<String> values) {
            addCriterion("usernick in", values, "usernick");
            return (Criteria) this;
        }

        public Criteria andUsernickNotIn(List<String> values) {
            addCriterion("usernick not in", values, "usernick");
            return (Criteria) this;
        }

        public Criteria andUsernickBetween(String value1, String value2) {
            addCriterion("usernick between", value1, value2, "usernick");
            return (Criteria) this;
        }

        public Criteria andUsernickNotBetween(String value1, String value2) {
            addCriterion("usernick not between", value1, value2, "usernick");
            return (Criteria) this;
        }

        public Criteria andImgurlIsNull() {
            addCriterion("imgurl is null");
            return (Criteria) this;
        }

        public Criteria andImgurlIsNotNull() {
            addCriterion("imgurl is not null");
            return (Criteria) this;
        }

        public Criteria andImgurlEqualTo(String value) {
            addCriterion("imgurl =", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotEqualTo(String value) {
            addCriterion("imgurl <>", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlGreaterThan(String value) {
            addCriterion("imgurl >", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlGreaterThanOrEqualTo(String value) {
            addCriterion("imgurl >=", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLessThan(String value) {
            addCriterion("imgurl <", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLessThanOrEqualTo(String value) {
            addCriterion("imgurl <=", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLike(String value) {
            addCriterion("imgurl like", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotLike(String value) {
            addCriterion("imgurl not like", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlIn(List<String> values) {
            addCriterion("imgurl in", values, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotIn(List<String> values) {
            addCriterion("imgurl not in", values, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlBetween(String value1, String value2) {
            addCriterion("imgurl between", value1, value2, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotBetween(String value1, String value2) {
            addCriterion("imgurl not between", value1, value2, "imgurl");
            return (Criteria) this;
        }

        public Criteria andMailIsNull() {
            addCriterion("mail is null");
            return (Criteria) this;
        }

        public Criteria andMailIsNotNull() {
            addCriterion("mail is not null");
            return (Criteria) this;
        }

        public Criteria andMailEqualTo(String value) {
            addCriterion("mail =", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotEqualTo(String value) {
            addCriterion("mail <>", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailGreaterThan(String value) {
            addCriterion("mail >", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailGreaterThanOrEqualTo(String value) {
            addCriterion("mail >=", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLessThan(String value) {
            addCriterion("mail <", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLessThanOrEqualTo(String value) {
            addCriterion("mail <=", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLike(String value) {
            addCriterion("mail like", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotLike(String value) {
            addCriterion("mail not like", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailIn(List<String> values) {
            addCriterion("mail in", values, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotIn(List<String> values) {
            addCriterion("mail not in", values, "mail");
            return (Criteria) this;
        }

        public Criteria andMailBetween(String value1, String value2) {
            addCriterion("mail between", value1, value2, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotBetween(String value1, String value2) {
            addCriterion("mail not between", value1, value2, "mail");
            return (Criteria) this;
        }

        public Criteria andLockedIsNull() {
            addCriterion("locked is null");
            return (Criteria) this;
        }

        public Criteria andLockedIsNotNull() {
            addCriterion("locked is not null");
            return (Criteria) this;
        }

        public Criteria andLockedEqualTo(String value) {
            addCriterion("locked =", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotEqualTo(String value) {
            addCriterion("locked <>", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThan(String value) {
            addCriterion("locked >", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThanOrEqualTo(String value) {
            addCriterion("locked >=", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLessThan(String value) {
            addCriterion("locked <", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLessThanOrEqualTo(String value) {
            addCriterion("locked <=", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLike(String value) {
            addCriterion("locked like", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotLike(String value) {
            addCriterion("locked not like", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedIn(List<String> values) {
            addCriterion("locked in", values, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotIn(List<String> values) {
            addCriterion("locked not in", values, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedBetween(String value1, String value2) {
            addCriterion("locked between", value1, value2, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotBetween(String value1, String value2) {
            addCriterion("locked not between", value1, value2, "locked");
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