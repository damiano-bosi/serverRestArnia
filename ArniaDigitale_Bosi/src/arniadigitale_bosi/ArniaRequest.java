/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arniadigitale_bosi;

/**
 *
 * @author bosi.damiano
 */
public class ArniaRequest {
    private String arn_dataInst;
    private boolean arn_piena;
    private String arn_macAddress;
    private int arn_api_id;
    
    
    public ArniaRequest() {
    }
    
    public ArniaRequest(String arn_dataInst, boolean arn_piena, String arn_macAddress, int arn_api_id) {
        this.arn_dataInst = arn_dataInst;
        this.arn_piena = arn_piena;
        this.arn_macAddress = arn_macAddress;
        this.arn_api_id = arn_api_id;
    }
    
    public String getArn_dataInst()
    { 
        return arn_dataInst; 
    }
    public boolean isArn_piena() 
    {
        return arn_piena;
    }
    public String getArn_macAddress() 
    { 
        return arn_macAddress; 
    }
    public int getArn_api_id() 
    {
        return arn_api_id;
    }
    
    public void setArn_dataInst(String arn_dataInst) 
    { 
        this.arn_dataInst = arn_dataInst;
    }
    public void setArn_piena(boolean arn_piena)
    { 
        this.arn_piena = arn_piena;
    }
    public void setArn_macAddress(String arn_macAddress)
    { 
        this.arn_macAddress = arn_macAddress; 
    }
    public void setArn_api_id(int arn_api_id) 
    { 
        this.arn_api_id = arn_api_id; 
    }
}
