package mycom.pojo;

public class DefineAnalysisItems {
    private Integer id;

    private String name;

    private String tablename;

    private String conditions;

    private String conditionunits;

    private Byte datanumber;

    private String dataunit;

    private String female;

    private Integer creater;

    private String hold1;

    private String hold2;

    private String hold3;

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

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions == null ? null : conditions.trim();
    }

    public String getConditionunits() {
        return conditionunits;
    }

    public void setConditionunits(String conditionunits) {
        this.conditionunits = conditionunits == null ? null : conditionunits.trim();
    }

    public Byte getDatanumber() {
        return datanumber;
    }

    public void setDatanumber(Byte datanumber) {
        this.datanumber = datanumber;
    }

    public String getDataunit() {
        return dataunit;
    }

    public void setDataunit(String dataunit) {
        this.dataunit = dataunit == null ? null : dataunit.trim();
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female == null ? null : female.trim();
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
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