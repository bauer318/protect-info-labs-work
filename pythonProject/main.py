import time
from array import array
from random import random


def set_key(par_key):
    key = {}
    for i in range(0, len(par_key)):
        key[i] = par_key[i]
    return key


def encrypt(text, par_key):
    result = ""
    key = set_key(par_key)
    for i in range(0, len(text) % len(key)):
        text += text[i]
    for i in range(0, len(text), len(key)):
        transposition = {}
        for j in range(0, len(key)):
            transposition[key[j] - 1] = text[i + j]
        for j in range(0, len(key)):
            result += transposition[j]
    return result


def decrypt(text, par_key):
    result = ""
    key = set_key(par_key)
    for i in range(0, len(text), len(key)):
        transposition = {}
        for j in range(0, len(key)):
            transposition[j] = text[i + key[j]-1]
        for j in range(0, len(key)):
            result += transposition[j]
    return result


def main():
    start_time = time.time_ns()
    key = [2, 3, 1, 4]
    text = "перестановка"
    print("Message " + text)
    encrypted_text = encrypt(text,key)
    print("Encrypted message : " + encrypted_text)
    decrypted_text = decrypt(encrypted_text,key)
    print("Decrypted message : " + decrypted_text)
    print("Time : %s nanoseconds" % (time.time_ns() - start_time))


if __name__ == '__main__':
    main()
