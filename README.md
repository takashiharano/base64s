Base64s
=====================

Base64s (with "s" standing for "secure") is a derivative encoding scheme of Base64.  
The data to be encoded is XORed with the given key before encoding.  
The encrypted data is seemingly Base64 encoded characters, but it is impossible to decode in Base64.  
The detail of the mechanism is available at https://b64s.com/

## Usage
Java:
```Java
String encoded = Base64s.encode("abc", "xyz");
String decoded = Base64s.decodeString("ABkbGQ==", "xyz");
```

JavaScript:
```JavaSctipt
var encoded = base64s.encode('abc', 'xyz');
var decoded = base64s.decode('ABkbGQ==', 'xyz');
```

Python:
```Python
encoded = base64s.encode_string('abc', 'xyz')
decoded = base64s.decode_string('ABkbGQ==', 'xyz')
```

PowerShell:
```powershell
$encoded = Get-Base64sEncodedString "abc" "xyz"
$decoded = Get-Base64sDecodedString "ABkbGQ==" "xyz"
```

Visual Basic:
```Visual Basic
Dim encoded As String
Dim decoded As String
encoded = Base64s.EncodeString("abc", "xyz")
decoded = Base64s.DecodeString("ABkbGQ==", "xyz")
```

## Notice
Base64s is not intended to be used where secrecy is of any concern.
