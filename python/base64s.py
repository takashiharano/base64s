#------------------------------------------------------------------------------
# Base64s
# Copyright 2023 Takashi Harano
# Released under the MIT license
# https://b64s.com/
#------------------------------------------------------------------------------
import base64

DEFAULT_ENCODING = 'utf-8'

#------------------------------------------------------------------------------
def encode(s, k, encoding=DEFAULT_ENCODING):
    a = s
    if type(s).__name__ == 'str':
        a = s.encode(encoding)
    kb = k.encode(DEFAULT_ENCODING)
    b = _encode(a, kb)
    b = base64.b64encode(b)
    b64 = b.decode(encoding)
    return b64

def _encode(a, k):
    al = len(a)
    kl = len(k)
    if al == 0 or kl == 0:
        return a

    p = kl - al
    if p < 0:
        p = 0

    b = []
    b.append(p)
    for i in range(al):
        b.append(a[i] ^ k[i % kl])

    j = i + 1
    for i in range(p):
        b.append(255 ^ k[j % kl])
        j += 1

    return bytearray(b)

#------------------------------------------------------------------------------
def decode(b64, k, bin=False, encoding=DEFAULT_ENCODING):
    b = base64.b64decode(b64)
    kb = k.encode(DEFAULT_ENCODING)
    d = _decode(b, kb)
    if not bin:
        d = d.decode(encoding)
    return d

def _decode(b, k):
    bl = len(b)
    kl = len(k)
    if bl == 0 or kl == 0:
        return b
    p = b[0]
    al = bl - p
    a = []
    for i in range(1, al):
        a.append(b[i] ^ k[(i - 1) % kl])
    return bytearray(a)

if __name__ == '__main__':
    print(__file__)
