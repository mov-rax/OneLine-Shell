public class MathLib {

    public String sin(Double number) {
        return Double.toString(Math.sin(number));
    }

    public String cos(Double number) {
        return Double.toString(Math.cos(number));
    }

    public String tan(Double number) {
        return Double.toString(Math.tan(number));
    }

    public String sec(Double number) {
        return Double.toString(1 / Math.cos(number));
    }

    public String csc(Double number) {
        return Double.toString(1 / Math.sin(number));
    }

    public String arsin(Double number) {
        return Double.toString(Math.asin(number));
    }

    public String arccos(Double number) {
        return Double.toString(Math.acos(number));
    }

    public String arctan(Double number) {
        return Double.toString(Math.atan(number));
    }

    public String arcsec(Double number) {
        return Double.toString(1 / Math.acos(number));
    }

    public String arccsc(Double number) {
        return Double.toString(1 / Math.asin(number));

    }

}
