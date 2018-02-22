package mycom.pojo;

public class MeltFlowRate {
    private Integer id;

    private String name;

    private Integer sampleid;

    private Integer analyser;

    private Integer review2nd;

    private Integer review3rd;

    private Byte status;

    private String condition1;

    private String condition1unit;

    private String condition1value;

    private String condition2;

    private String condition2unit;

    private String condition2value;

    private String data1;

    private String data2;

    private String formula;

    private String result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSampleid() {
        return sampleid;
    }

    public void setSampleid(Integer sampleid) {
        this.sampleid = sampleid;
    }

    public Integer getAnalyser() {
        return analyser;
    }

    public void setAnalyser(Integer analyser) {
        this.analyser = analyser;
    }

    public Integer getReview2nd() {
        return review2nd;
    }

    public void setReview2nd(Integer review2nd) {
        this.review2nd = review2nd;
    }

    public Integer getReview3rd() {
        return review3rd;
    }

    public void setReview3rd(Integer review3rd) {
        this.review3rd = review3rd;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCondition1() {
        return condition1;
    }

    public void setCondition1(String condition1) {
        this.condition1 = condition1 == null ? null : condition1.trim();
    }

    public String getCondition1unit() {
        return condition1unit;
    }

    public void setCondition1unit(String condition1unit) {
        this.condition1unit = condition1unit == null ? null : condition1unit.trim();
    }

    public String getCondition1value() {
        return condition1value;
    }

    public void setCondition1value(String condition1value) {
        this.condition1value = condition1value == null ? null : condition1value.trim();
    }

    public String getCondition2() {
        return condition2;
    }

    public void setCondition2(String condition2) {
        this.condition2 = condition2 == null ? null : condition2.trim();
    }

    public String getCondition2unit() {
        return condition2unit;
    }

    public void setCondition2unit(String condition2unit) {
        this.condition2unit = condition2unit == null ? null : condition2unit.trim();
    }

    public String getCondition2value() {
        return condition2value;
    }

    public void setCondition2value(String condition2value) {
        this.condition2value = condition2value == null ? null : condition2value.trim();
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1 == null ? null : data1.trim();
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2 == null ? null : data2.trim();
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula == null ? null : formula.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }
}