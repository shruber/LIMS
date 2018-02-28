package mycom.pojo;

public class Mfr {
    private Integer id;

    private Integer sampleid;

    private Integer analyser;

    private Integer review2nd;

    private Integer review3nd;

    private Byte status;

    private String condition1value;

    private String condition2value;

    private String data1;

    private String data2;

    private String result;

    private String hold1;

    private String hold2;

    private String hold3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getReview3nd() {
        return review3nd;
    }

    public void setReview3nd(Integer review3nd) {
        this.review3nd = review3nd;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCondition1value() {
        return condition1value;
    }

    public void setCondition1value(String condition1value) {
        this.condition1value = condition1value == null ? null : condition1value.trim();
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getHold1() {
        return hold1;
    }

    public void setHold1(String hold1) {
        this.hold1 = hold1 == null ? null : hold1.trim();
    }

    public String getHold2() {
        return hold2;
    }

    public void setHold2(String hold2) {
        this.hold2 = hold2 == null ? null : hold2.trim();
    }

    public String getHold3() {
        return hold3;
    }

    public void setHold3(String hold3) {
        this.hold3 = hold3 == null ? null : hold3.trim();
    }
}