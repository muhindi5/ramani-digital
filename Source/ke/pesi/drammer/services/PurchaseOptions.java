/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */

package ke.pesi.drammer.services;

/**
 *
 * @project: ramani-digital
 * @author kelly
 * @date Jun 24, 2017 3:31:15 PM
 * 
 * Provide access to the details a user selects when about to purchase a 
 * houseplan ie download format, oprional files,pricing details etc
 * 
 */
public class PurchaseOptions {
    
    private String fileDownloadFormat;
    private double planPricing;
    private double boqOptionPricing;
    private double mtrsOptionPricing;

    public PurchaseOptions(String fileDownloadFormat, double planPricing, double boqOptionPricing, double mtrsOptionPricing) {
        this.fileDownloadFormat = fileDownloadFormat;
        this.planPricing = planPricing;
        this.boqOptionPricing = boqOptionPricing;
        this.mtrsOptionPricing = mtrsOptionPricing;
    }

    public String getFileDownloadFormat() {
        return fileDownloadFormat;
    }

    public void setFileDownloadFormat(String fileDownloadFormat) {
        this.fileDownloadFormat = fileDownloadFormat;
    }

    public double getPlanPricing() {
        return planPricing;
    }

    public void setPlanPricing(double planPricing) {
        this.planPricing = planPricing;
    }

    public double getBoqOptionPricing() {
        return boqOptionPricing;
    }

    public void setBoqOptionPricing(double boqOptionPricing) {
        this.boqOptionPricing = boqOptionPricing;
    }

    public double getMtrsOptionPricing() {
        return mtrsOptionPricing;
    }

    public void setMtrsOptionPricing(double mtrsOptionPricing) {
        this.mtrsOptionPricing = mtrsOptionPricing;
    }
}
