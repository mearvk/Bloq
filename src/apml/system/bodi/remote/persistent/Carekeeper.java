package apml.system.bodi.remote.persistent;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * @author Max Rupplin
 */
public class Carekeeper {
    public String key;
    public String value;
    public String system = "APML";
    public String systemversion = "1.0";
    public String encryption = "OK";
    public String encryptiontype = "RSA";
    public String encryptionkeylength = "1024";
    public Boolean backup = true;
    public Boolean backuppriority = true;
    public String backupurl = "//systems/bodi/{system.id}/backups/{object.id}";
    public String id = String.valueOf(this.hashCode());
    public Integer hashcode = this.hashCode();
    public String[] encryptiontypes = {"RSA", "AES", "DES", "MD5", "Blowfish"};
    public String[] approvedciphers = {"RSA", "AES", "DES", "Blowfish"};
    public String[] trustedusers = {"Hogwarts", "Daywar", "DayWar", "Doo", "Snape", "Tryagainhoney", "Max Rupplin"};
    public Integer touchedlast;
    public Integer viewedlast;
    public String[] trust = {"Angels", "Gods and up", "Day"};
    public String[] notrust = {"Devils"};
    public String bodoglobalnamespace = "//{system.id}/{admin.fill}/{object.id}";
    public String bodiglobal = "//bodi/lookup/{object.global.id}";
    public String bodiglobalserver = "//global/bodi//";
    public Boolean databaseaware = true;
    public String JGPsensitive = "OK";
    public String author = "MAX RUPPLIN - SENIOR SCIENTOLOGIST";
    public ArrayList<String> bannedindividuals = new ArrayList<String>();
    public ArrayList<Map<String, Date>> goodbeingnesses = new ArrayList();
    public ArrayList<Map<String, Date>> evilbeingnesses = new ArrayList();
    public String sector = "SOL";
    public String planet = "Earth";
    public String alias = "";
    public Number number001;
    public Number number002;
    public BigInteger biginteger001;
    public BigInteger biginteger002;
    public BigDecimal bigdecimal001;
    public BigDecimal bigdecimal002;
    ArrayList<String> touchedby = new ArrayList();
    ArrayList<String> editions = new ArrayList();
    ArrayList<String> forwards = new ArrayList();
    ArrayList<String> backwards = new ArrayList();
    ArrayList<String> relateds = new ArrayList();
    ArrayList<String> nulls = new ArrayList();
    ArrayList<String> viewedby = new ArrayList();
    private Object object;
    private Integer timestamp;
    private Integer modifiedlast;

    /**
     * @param value
     * @return
     */
    public BigInteger allocatebiginteger(String value) {
        return new BigInteger(value);
    }

    /**
     * @param value
     * @return
     */
    public BigDecimal allocatebigdecimal(String value) {
        return new BigDecimal(value);
    }

    /**
     * Please bid carefully only numbers into this matrix [1,2,3,4,5,6, etc.]
     *
     * @param values
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    public BigInteger[][] allocatebigintegermatrix(String[][] values, int width, int height) throws Exception {
        if (values == null) throw new Exception("");

        if (width <= 0) throw new Exception("");

        if (height <= 0) throw new Exception("");

        BigInteger[][] integermatrix = new BigInteger[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                integermatrix[x][y] = new BigInteger(values[x][y]);
            }
        }

        return integermatrix;
    }

    /**
     * Please bid carefully only numbers into this matrix [1.001 or 1.002 or 1.003 or 1.004[
     *
     * @param values
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    public BigDecimal[][] allocatebigdecimalmatrix(String[][] values, int width, int height) throws Exception {
        if (values == null) throw new Exception("");

        if (width <= 0) throw new Exception("");

        if (height <= 0) throw new Exception("");

        BigDecimal[][] decimalmatrix = new BigDecimal[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                decimalmatrix[x][y] = new BigDecimal(values[x][y], MathContext.DECIMAL128);
            }
        }

        return decimalmatrix;
    }
}
