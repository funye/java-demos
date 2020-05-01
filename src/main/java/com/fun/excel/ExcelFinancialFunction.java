package com.fun.excel;

/**
 * excel 金融公式
 * @author huanye
 * @date: 2017/7/3 下午5:06
 */
public class ExcelFinancialFunction {

    /**
     *  计算利率
     * @param pv 贷款金额
     * @param pmt 每月还款额
     * @param nper 还款期数
     * @param cnt 基数计算次数 默认计算200次
     * @param ina 加载因子 默认为10
     * @return
     */
    public static double rate(double pv,double pmt,double nper,int cnt,int ina){
        double rate = 1,x,jd = 0.1,side = 0.1,i = 1;
        cnt = cnt == 0 ? 200 : cnt;
        ina = ina == 0 ? 10 : ina;
        do{
            x = pv/pmt - (Math.pow(1+rate, nper)-1)/(Math.pow(rate+1, nper)*rate);
            if(x*side>0){side = -side;jd *=10;}
            rate += side/jd;
        }while(i++<cnt&&Math.abs(x)>=1/Math.pow(10, ina));
        if(i>cnt)return Double.NaN;
        return rate;
    }

    /**
     * 求pv得值
     * @param rate 每期利率
     * @param nper 还款期数
     * @param pmt 每期金额
     * @param fv 本金+利息
     * @param type 默认0，代表期为投入
     * @return pv值,保留两位小数
     */
    public static double pv(double rate,int nper,double pmt,double fv,int type) {
        type = type == 1?1:0;
        double pv = 0d;
        return (fv + (pmt *(1+rate*type)* (Math.pow(1 + rate, nper) - 1) ) / rate) / Math.pow(1 + rate, nper);
    }

    /**
     * 求fv值
     * @param rate 每期利率
     * @param nper 期数
     * @param pmt 每期投入
     * @param pv 贷款金额
     * @param type 0 期末投入，1期初投入
     * @return fv的值
     */
    public static double fv(double rate,int nper,double pmt,double pv,int type) {
        type = type == 1?1:0;
        double fv;
        if (type==0) {
            fv = pv*Math.pow(1+rate,nper) + pmt*(Math.pow(1+rate,nper)-1)/rate;
        } else {
            fv = pv*Math.pow(1+rate,nper) + pmt*(1+rate)*(Math.pow(1+rate,nper)-1)/rate;
        }
        return fv;
    }

    public static double roundDown(double v,int digist) {
        return ((int) (v * Math.pow(10, digist) / 1)) / Math.pow(10, digist);
    }
}
