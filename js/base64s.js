/*!
 * Base64s
 * Copyright 2023 Takashi Harano
 * Released under the MIT license
 * https://b64s.com/
 */
var base64s = {
  /**
   * Plain text / Byte array to Base64s encoded string
   */
  encode: function(s, k) {
    var a = ((typeof s == 'string') ? base64s.UTF8.toByteArray(s) : s);
    var x = base64s.UTF8.toByteArray(k);
    var b = base64s._encode(a, x);
    return base64s.Base64.encode(b);
  },

  _encode: function(a, k) {
    var al = a.length;
    var kl = k.length;
    if ((al == 0) || (kl == 0)) return a;
    var p = kl - al;
    if (p < 0) p = 0;
    var b = [];
    b.push(p);
    for (var i = 0; i < al; i++) {
      b.push(a[i] ^ k[i % kl]);
    }
    var n = i;
    for (i = 0; i < p; i++) {
      b.push(255 ^ k[(n + i) % kl]);
    }
    return b;
  },

  /**
   * Base64s encoded string to Byte array / Plain text
   */
  decode: function(s, k, byB) {
    var b = base64s.Base64.decode(s);
    var x = base64s.UTF8.toByteArray(k);
    var a = base64s._decode(b, x);
    if (!byB) a = base64s.UTF8.fromByteArray(a);
    return a;
  },

  _decode: function(b, k) {
    var bl = b.length;
    var kl = k.length;
    if ((bl == 0) || (kl == 0)) return b;
    var p = b[0];
    var al = bl - p;
    var a = [];
    for (var i = 1; i < al; i++) {
      a.push(b[i] ^ k[(i - 1) % kl]);
    }
    return a;
  },

  Base64: {
    encode: function(arr) {
      var len = arr.length;
      if (len == 0) return '';
      var tbl = {64: 61, 63: 47, 62: 43};
      for (var i = 0; i < 62; i++) {
        tbl[i] = (i < 26 ? i + 65 : (i < 52 ? i + 71 : i - 4));
      }
      var str = '';
      for (i = 0; i < len; i += 3) {
        str += String.fromCharCode(
          tbl[arr[i] >>> 2],
          tbl[(arr[i] & 3) << 4 | arr[i + 1] >>> 4],
          tbl[(i + 1) < len ? (arr[i + 1] & 15) << 2 | arr[i + 2] >>> 6 : 64],
          tbl[(i + 2) < len ? (arr[i + 2] & 63) : 64]
        );
      }
      return str;
    },

    decode: function(str) {
      var arr = [];
      if (str.length == 0) return arr;
      for (var i = 0; i < str.length; i++) {
        var c = str.charCodeAt(i);
        if (!(((c >= 0x30) && (c <= 0x39)) || ((c >= 0x41) && (c <= 0x5A)) || ((c >= 0x61) && (c <= 0x7A)) || (c == 0x2B) || (c == 0x2F) || (c == 0x3D))) {
          throw new Error('invalid b64 char: 0x' + c.toString(16).toUpperCase() + ' at ' + i);
        }
      }
      var tbl = {61: 64, 47: 63, 43: 62};
      for (i = 0; i < 62; i++) {
        tbl[i < 26 ? i + 65 : (i < 52 ? i + 71 : i - 4)] = i;
      }
      var buf = [];
      for (i = 0; i < str.length; i += 4) {
        for (var j = 0; j < 4; j++) {
          buf[j] = tbl[str.charCodeAt(i + j) || 0];
        }
        arr.push(
          buf[0] << 2 | (buf[1] & 63) >>> 4,
          (buf[1] & 15) << 4 | (buf[2] & 63) >>> 2,
          (buf[2] & 3) << 6 | buf[3] & 63
        );
      }
      if (buf[3] == 64) {
        arr.pop();
        if (buf[2] == 64) {
          arr.pop();
        }
      }
      return arr;
    }
  },

  UTF8: {
    toByteArray: function(s) {
      var a = [];
      if (!s) return a;
      var chs = s.match(/[\uD800-\uDBFF][\uDC00-\uDFFF]|[\s\S]/g) || [];
      for (var i = 0; i < chs.length; i++) {
        var ch = chs[i];
        var c = ch.charCodeAt(0);
        if (c <= 0x7F) {
          a.push(c);
        } else {
          var e = encodeURIComponent(ch);
          var w = e.split('%');
          for (var j = 1; j < w.length; j++) {
            a.push(('0x' + w[j]) | 0);
          }
        }
      }
      return a;
    },

    fromByteArray: function(b) {
      if (!b) return null;
      var e = '';
      for (var i = 0; i < b.length; i++) {
        e += '%' + base64s.toHex(b[i]);
      }
      return decodeURIComponent(e);
    }
  },

  toHex: function(v) {
    var hex = parseInt(v).toString(16).toUpperCase();
    if (hex.length < 2) {
      hex = ('0' + hex).slice(-2);
    }
    return hex;
  }
};
