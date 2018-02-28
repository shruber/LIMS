package mycom.pojo;

public class SampleAnalysisItems {
    private Integer id;

    private Integer sampleid;

    private String analysisitems;

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

    public String getAnalysisitems() {
        return analysisitems;
    }

    public void setAnalysisitems(String analysisitems) {
        this.analysisitems = analysisitems == null ? null : analysisitems.trim();
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