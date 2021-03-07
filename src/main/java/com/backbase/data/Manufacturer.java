package com.backbase.data;

/**
 * Enum object to keep hold of the data for the Manufacturer
 */
public enum Manufacturer {
    APPLE("Apple Inc.", "1"),
    THINKING_MACHINES("Thinking Machines", "2"),
    RCA("RCA", "3"),
    NETRONICS("Netronics", "4"),
    TANDY("Tandy Corporation", "5"),
    COMMODORE("Commodore International", "6"),
    MOS("MOS Technology", "7"),
    MITS("Micro Instrumentation and Telemetry Systems", "8"),
    IMS("IMS Associates, Inc.", "9"),
    DIGITAL_EQUIPMENT("Digital Equipment Corporation", "10"),
    LINCOLN_LAB("Lincoln Laboratory", "11"),
    MOORE("Moore School of Electrical Engineering", "12"),
    IBM("IBM", "13"),
    AMIGA("Amiga Corporation", "14"),
    CANON("Canon", "15"),
    NOKIA("Nokia", "16"),
    SONY("Sony", "17"),
    OQO("OQO", "18"),
    NEXT("NeXT", "19"),
    ATARI("Atari", "20"),
    ACORN("Acorn computer", "22"),
    TIMEX("Timex Sinclair", "23"),
    NINTENDO("Nintendo", "24"),
    SINCLAIR("Sinclair Research Ltd", "25"),
    XEROX("Xerox", "26"),
    HP("Hewlett-Packard", "27"),
    ZEMMIX("Zemmix", "28"),
    ACVS("ACVS", "29"),
    SANYO("Sanyo", "30"),
    CRAY("Cray", "31"),
    EVANS_AND_SUTHERLAND("Evans & Sutherland", "32"),
    ESR("E.S.R. Inc.", "33"),
    OMRON("OMRON", "34"),
    BBN("BBN Technologies", "35"),
    LENOVO("Lenovo Group", "36"),
    ASUS("ASUS", "37"),
    AMSTRAD("Amstrad", "38"),
    SUN("Sun Microsystems", "39"),
    TEXAS("Texas Instruments", "40"),
    HTC("HTC Corporation", "41"),
    RIM("Research In Motion", "42"),
    SAMSUNG("Samsung Electronics", "43");

    Manufacturer(String manufacturerName, String manufacturerValue) {
        this.manufacturerName = manufacturerName;
        this.manufacturerValue = manufacturerValue;
    }

    private String manufacturerName;
    private String manufacturerValue;

    public String getManufacturerName() {
        return this.manufacturerName;
    }

    public String getManufacturerValue() {
        return this.manufacturerValue;
    }
}


