package com.yan.demo.bean;

import java.util.List;

public class Circlebean {
    private String centerText;
    private String centerSize;
    private List<CirclePartBean> circlePartBeanList;

    public String getCenterText() {
        return centerText;
    }

    public void setCenterText(String centerText) {
        this.centerText = centerText;
    }

    public String getCenterSize() {
        return centerSize;
    }

    public void setCenterSize(String centerSize) {
        this.centerSize = centerSize;
    }

    public List<CirclePartBean> getCirclePartBeanList() {
        return circlePartBeanList;
    }

    public void setCirclePartBeanList(List<CirclePartBean> circlePartBeanList) {
        this.circlePartBeanList = circlePartBeanList;
    }

    public static class CirclePartBean{
       private String lineUptext;
       private String lineDowntext;
       private float  numericalValue;
       private int [] colors;

        public int[] getColors() {
            return colors;
        }

        public void setColors(int[] colors) {
            this.colors = colors;
        }

        public String getLineUptext() {
           return lineUptext;
       }

       public void setLineUptext(String lineUptext) {
           this.lineUptext = lineUptext;
       }

       public String getLineDowntext() {
           return lineDowntext;
       }

       public void setLineDowntext(String lineDowntext) {
           this.lineDowntext = lineDowntext;
       }

       public float getNumericalValue() {
           return numericalValue;
       }

       public void setNumericalValue(float numericalValue) {
           this.numericalValue = numericalValue;
       }
   }
}
