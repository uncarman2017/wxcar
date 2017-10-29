/**
 * Created by jiaweiyu on 2017/1/12.
 */
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

//判断闰年
Date.prototype.isLeapYear = function () {
    return (0 == this.getYear() % 4 && ((this.getYear() % 100 != 0) || (this.getYear() % 400 == 0)));
}

//计算两个日期的间隔天数
function daysBetween(dateOne, dateTwo) {
    var OneMonth = dateOne.substring(5, dateOne.lastIndexOf('-'));
    var OneDay = dateOne.substring(dateOne.length, dateOne.lastIndexOf('-') + 1);
    var OneYear = dateOne.substring(0, dateOne.indexOf('-'));

    var TwoMonth = dateTwo.substring(5, dateTwo.lastIndexOf('-'));
    var TwoDay = dateTwo.substring(dateTwo.length, dateTwo.lastIndexOf('-') + 1);
    var TwoYear = dateTwo.substring(0, dateTwo.indexOf('-'));

    var cha = ((Date.parse(OneMonth + '/' + OneDay + '/' + OneYear) - Date.parse(TwoMonth + '/' + TwoDay + '/' + TwoYear)) / 86400000);
    return Math.abs(cha);
}


/**
 * 日期计算
 * strInterval:
 * s: 秒
 * n: 分钟
 * h: 小时
 * d: 天
 * w: 周
 * q: 季度
 * m: 月
 * y: 年
 * */

Date.prototype.dateAdd = function (strInterval, number) {
    var dtTmp = this;
    switch (strInterval) {
        case 's' :
            return new Date(Date.parse(dtTmp) + (number));
        case 'n' :
            return new Date(Date.parse(dtTmp) + (60000 * number));
        case 'h' :
            return new Date(Date.parse(dtTmp) + (3600000 * number));
        case 'd' :
            return new Date(Date.parse(dtTmp) + (86400000 * number));
        case 'w' :
            return new Date(Date.parse(dtTmp) + ((86400000 * 7) * number));
        case 'q' :
            return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + number * 3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'm' :
            return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'y' :
            return new Date((dtTmp.getFullYear() + number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
    }
}

Date.prototype.dateDiff = function (strInterval, dtEnd) {
    var dtStart = this;
    if (typeof dtEnd == 'string')//如果是字符串转换为日期型
    {
        dtEnd = StringToDate(dtEnd);
    }
    switch (strInterval) {
        case 's' :
            return parseInt((dtEnd - dtStart));
        case 'n' :
            return parseInt((dtEnd - dtStart) / 60000);
        case 'h' :
            return parseInt((dtEnd - dtStart) / 3600000);
        case 'd' :
            return parseInt((dtEnd - dtStart) / 86400000);
        case 'w' :
            return parseInt((dtEnd - dtStart) / (86400000 * 7));
        case 'm' :
            return (dtEnd.getMonth() + 1) + ((dtEnd.getFullYear() - dtStart.getFullYear()) * 12) - (dtStart.getMonth() + 1);
        case 'y' :
            return dtEnd.getFullYear() - dtStart.getFullYear();
    }
}

var validateHelper = {
    isNum: function (val) {
        var pattern = /^-?\d+$/;
        return pattern.test(val);
    },

    isRealNum: function (val) {//验证实数
        var pattern = /^-?\d+\.?\d*$/;
        return pattern.test(val);
    },

    isFloat: function (val) {//验证小数
        var pattern = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/;
        return pattern.test(val);
    },

    isNumOrLetter: function (val) {//只能输入数字和字母
        var pattern = /^[A-Za-z0-9]+$/;
        return pattern.test(val);
    },

    isColor: function (val) {//验证颜色
        var pattern = /^#[0-9a-fA-F]{6}$/;
        return pattern.test(val);
    },

    isNull: function (val) {//验证空
        return val.replace(/\s+/g, "").length == 0;
    },

    isLen: function (val, expectedLength) {
        return val.length == expectedLength;
    },

    isDate: function (val) {//验证时间2010-10-10
        var pattern = /^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/;
        return pattern.test(val);
    },

    isNumLetterLine: function (val) {//只能输入数字、字母、下划线
        var pattern = /^[a-zA-Z0-9_]{1,}$/;
        return pattern.test(val);
    }

};

String.prototype.lTrim = function () {
    return this.replace(/(^\s*)/g, "");
}

String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "");
}

String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}


String.prototype.left = function (len) {

    if (isNaN(len) || len == null) {
        len = this.length;
    }
    else {
        if (parseInt(len) < 0 || parseInt(len) > this.length) {
            len = this.length;
        }
    }

    return this.substr(0, len);
}


String.prototype.right = function (len) {

    if (isNaN(len) || len == null) {
        len = this.length;
    }
    else {
        if (parseInt(len) < 0 || parseInt(len) > this.length) {
            len = this.length;
        }
    }

    return this.substring(this.length - len, this.length);
}

String.prototype.mid = function (start, len) {
    return this.substr(start, len);
}

String.prototype.inStr = function (str) {

    if (str == null) {
        str = "";
    }

    return this.indexOf(str);
}

String.prototype.inStrRev = function (str) {

    if (str == null) {
        str = "";
    }

    return this.lastIndexOf(str);
}

String.prototype.lengthW = function () {
    return this.replace(/[^\x00-\xff]/g, "**").length;
}

/*
 ===========================================
 //是否是正确的IP地址
 ===========================================
 */
String.prototype.isIP = function () {

    var reSpaceCheck = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;

    if (reSpaceCheck.test(this)) {
        this.match(reSpaceCheck);
        if (RegExp.$1 <= 255 && RegExp.$1 >= 0
            && RegExp.$2 <= 255 && RegExp.$2 >= 0
            && RegExp.$3 <= 255 && RegExp.$3 >= 0
            && RegExp.$4 <= 255 && RegExp.$4 >= 0) {
            return true;
        }
        else {
            return false;
        }
    }
    else {
        return false;
    }

}


/*
 ===========================================
 //是否是正确的长日期
 ===========================================
 */
String.prototype.isLongDate = function () {
    var r = this.replace(/(^\s*)|(\s*$)/g, "").match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
    if (r == null) {
        return false;
    }
    var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
    return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4] && d.getHours() == r[5] && d.getMinutes() == r[6] && d.getSeconds() == r[7]);

}

/*
 ===========================================
 //是否是正确的短日期
 ===========================================
 */
String.prototype.isShortDate = function () {
    var r = this.replace(/(^\s*)|(\s*$)/g, "").match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (r == null) {
        return false;
    }
    var d = new Date(r[1], r[3] - 1, r[4]);
    return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
}

/*
 ===========================================
 //是否是正确的日期
 ===========================================
 */
String.prototype.isDate = function () {
    return this.isLongDate() || this.isShortDate();
}

/*
 ===========================================
 //是否是手机
 ===========================================
 */
String.prototype.isMobile = function () {
    return /^0{0,1}13[0-9]{9}$/.test(this);
}

/*
 ===========================================
 //是否是邮件
 ===========================================
 */
String.prototype.isEmail = function () {
    return /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(this);
}

/*
 ===========================================
 //是否是邮编(中国)
 ===========================================
 */

String.prototype.isZipCode = function () {
    return /^[\\d]{6}$/.test(this);
}

/*
 ===========================================
 //是否是有汉字
 ===========================================
 */
String.prototype.existChinese = function () {
//[\u4E00-\u9FA5]為漢字﹐[\uFE30-\uFFA0]為全角符號
    return /^[\x00-\xff]*$/.test(this);
}

/*
 ===========================================
 //是否是合法的文件名/目录名
 ===========================================
 */
String.prototype.isFileName = function () {
    return !/[\\\/\*\?\|:"<>]/g.test(this);
}

/*
 ===========================================
 //是否是有效链接
 ===========================================
 */
String.prototype.isUrl = function () {
    return /^http[s]?:\/\/([\w-]+\.)+[\w-]+([\w-./?%&=]*)?$/i.test(this);
}


/*
 ===========================================
 //是否是有效的身份证(中国)
 ===========================================
 */
String.prototype.isIDCard = function () {
    var iSum = 0;
    var info = "";
    var sId = this;

    var aCity = {
        11: "北京",
        12: "天津",
        13: "河北",
        14: "山西",
        15: "内蒙古",
        21: "辽宁",
        22: "吉林",
        23: "黑龙 江",
        31: "上海",
        32: "江苏",
        33: "浙江",
        34: "安徽",
        35: "福建",
        36: "江西",
        37: "山东",
        41: "河南",
        42: "湖 北",
        43: "湖南",
        44: "广东",
        45: "广西",
        46: "海南",
        50: "重庆",
        51: "四川",
        52: "贵州",
        53: "云南",
        54: "西藏",
        61: "陕西",
        62: "甘肃",
        63: "青海",
        64: "宁夏",
        65: "新疆",
        71: "台湾",
        81: "香港",
        82: "澳门",
        91: "国外"
    };

    if (!/^\d{17}(\d|x)$/i.test(sId)) {
        return false;
    }
    sId = sId.replace(/x$/i, "a");
//非法地区
    if (aCity[parseInt(sId.substr(0, 2))] == null) {
        return false;
    }

    var sBirthday = sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-" + Number(sId.substr(12, 2));

    var d = new Date(sBirthday.replace(/-/g, "/"))

//非法生日
    if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate())) {
        return false;
    }
    for (var i = 17; i >= 0; i--) {
        iSum += (Math.pow(2, i) % 11) * parseInt(sId.charAt(17 - i), 11);
    }

    if (iSum % 11 != 1) {
        return false;
    }
    return true;

}

/*
 ===========================================
 //是否是有效的电话号码(中国)
 ===========================================
 */
String.prototype.isPhoneCall = function () {
    return /(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/.test(this);
}


/*
 ===========================================
 //是否是数字
 ===========================================
 */
String.prototype.isNumeric = function (flag) {
//验证是否是数字
    if (isNaN(this)) {

        return false;
    }

    switch (flag) {

        case null:        //数字
        case "":
            return true;
        case "+":        //正数
            return /(^\+?|^\d?)\d*\.?\d+$/.test(this);
        case "-":        //负数
            return /^-\d*\.?\d+$/.test(this);
        case "i":        //整数
            return /(^-?|^\+?|\d)\d+$/.test(this);
        case "+i":        //正整数
            return /(^\d+$)|(^\+?\d+$)/.test(this);
        case "-i":        //负整数
            return /^[-]\d+$/.test(this);
        case "f":        //浮点数
            return /(^-?|^\+?|^\d?)\d*\.\d+$/.test(this);
        case "+f":        //正浮点数
            return /(^\+?|^\d?)\d*\.\d+$/.test(this);
        case "-f":        //负浮点数
            return /^[-]\d*\.\d$/.test(this);
        default:        //缺省
            return true;
    }
}

/*
 ===========================================
 //是否是颜色(#FFFFFF形式)
 ===========================================
 */
String.prototype.IsColor = function () {
    var temp = this;
    if (temp == "") return true;
    if (temp.length != 7) return false;
    return (temp.search(/\#[a-fA-F0-9]{6}/) != -1);
}

/*
 ===========================================
 //转换成全角
 ===========================================
 */
String.prototype.toCase = function () {
    var tmp = "";
    for (var i = 0; i < this.length; i++) {
        if (this.charCodeAt(i) > 0 && this.charCodeAt(i) < 255) {
            tmp += String.fromCharCode(this.charCodeAt(i) + 65248);
        }
        else {
            tmp += String.fromCharCode(this.charCodeAt(i));
        }
    }
    return tmp
}

/*
 ===========================================
 //对字符串进行Html编码
 ===========================================
 */
String.prototype.toHtmlEncode = function () {
    var str = this;

    str = str.replace(/&/g, "&amp;");
    str = str.replace(/</g, "&lt;");
    str = str.replace(/>/g, "&gt;");
    str = str.replace(/\'/g, "&apos;");
    str = str.replace(/\"/g, "&quot;");
    str = str.replace(/\n/g, "<br>");
    str = str.replace(/\ /g, "&nbsp;");
    str = str.replace(/\t/g, "&nbsp;&nbsp;&nbsp;&nbsp;");

    return str;
}

/*
 ===========================================
 //转换成日期
 ===========================================
 */
String.prototype.toDate = function () {
    try {
        return new Date(this.replace(/-/g, "\/"));
    }
    catch (e) {
        return null;
    }
}

var hastouch = "ontouchstart" in window ? true : false,
    clickEventName = hastouch ? "touchstart" : "click";

var alertTimeout = 0;
function alertEx(msg, timeout) {
    var html = $("<div id='dvAlert' class='alert alert-success' style='z-index:100;' role='alert'><b>" + msg + "</b></div>");

    var container = $(document.body);

    if (container.find("#dvAlert").size() == 0) {
        container.append(html);
    }
    var alertDlg = container.find("#dvAlert");
    alertDlg.text(msg);

    //获得窗口的高度
    var windowHeight = $(window).height();
    //获得窗口的宽度
    var windowWidth = $(window).width();
    //获得弹窗的高度
    var popHeight = alertDlg.height();
    //获得弹窗的宽度
    var popWidth = alertDlg.width();
    //获得滚动条的高度
    var scrollTop = $(document).scrollTop();
    // 获得滚动条的宽度
    var scrollLeft = $(document).scrollLeft();

    var popY = (windowHeight - popHeight) / 2 + scrollTop;
    var popX = (windowWidth - popWidth) / 2 + scrollLeft;
    alertDlg.css({position: 'absolute', 'top': popY, left: popX}).show(300);

    if (alertTimeout != 0) clearTimeout(alertTimeout);
    //alertDlg.animate({top: popY, left: popX}, 300).show(300);

    alertTimeout = window.setTimeout(
        function () {
            alertDlg.hide(300);
            alertDlg.text("");
        }, timeout
    );

}



