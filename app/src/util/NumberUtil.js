export default {
    numberComma(source, length = 3) {
        source = String(source).split(".");
        source[0] = source[0].replace(new RegExp('(\\d)(?=(\\d{' + length + '})+$)', 'ig'), "$1,");
        return source.join(".")
    },
    isNumber(number) {
        try {
            number = eval(number);
        } catch (e) {
        }
        return typeof number === 'number';
    },

    strToNumber(number) {
        if (this.isNumber(number)) {
            return number * 1;
        }
        return number;
    },

    price(number) {
        number = this.strToNumber(number);
        try {
            number = this.numberComma(number.toFixed(2))
        } catch (e) {
        }
        return number;
    }


}
