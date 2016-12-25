/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package literals;

/**
 *
 * @author Gavin
 */
public class Element {
    public int elementID;
    public String elementName;
    public int atomicWeight;
    public float targetMantlePercent;
    public byte targetMantleExponent;
    public float targetCrustPercent;
    public byte targetCrustExponent;
    
    public Element (int elementID, String elementName, int atomicWeight, float percentInMantle, byte mantleExponent, float percentInCrust, byte crustExponent){
        this.elementID = elementID;
        this.elementName = elementName;
        this.atomicWeight = atomicWeight;
        this.targetMantlePercent = targetMantlePercent;
        this.targetMantleExponent = targetMantleExponent;
        this.targetCrustPercent = targetCrustPercent;
        this.targetCrustExponent = targetCrustExponent;
    }
}
