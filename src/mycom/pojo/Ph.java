package mycom.pojo;

public class Ph {
    private Integer id;

    private String name;

    private Integer sampleid;

    private Integer analyser;

    private Integer review2nd;

    private Integer review3rd;

    private Byte status;

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