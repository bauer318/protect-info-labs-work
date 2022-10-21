import time


def main():
    alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
    word_key = input("Введите слово-лозунг: ")
    if not len(word_key) == 0:
        word = input("Введите слово: ")
        if not len(word) == 0:
            print("Шифрующая таблица")
            print_alphabet_with_space_separator(alphabet)
            start_time = time.time_ns()
            print_alphabet_with_space_separator(get_second_line(alphabet, word_key.lower()))
            encrypted_word = encrypt_decrypt_word(True, word.lower(), get_second_line(alphabet, word_key), alphabet)
            print("зашифрованное слово : ", encrypted_word)
            print("дешифрованное слово : ", encrypt_decrypt_word(False, encrypted_word.lower(),
                                                                 get_second_line(alphabet, word_key), alphabet).lower())
            print("%s nanoseconds" % (time.time_ns() - start_time))
        else:
            print_error_message()
    else:
        print_error_message()


def print_error_message():
    print("Error! Incorrect input data")


def print_alphabet_with_space_separator(alphabet):
    for i in range(0, len(alphabet)):
        print(alphabet[i], end=" ")
    print()


def get_second_line(alphabet, word_key):
    result = ""
    word_key_and_alphabet = word_key + alphabet
    for i in range(0, len(word_key_and_alphabet)):
        if not result.__contains__(word_key_and_alphabet[i]):
            result += word_key_and_alphabet[i]

    return result


def encrypt_helper(alphabet, second_line, current_letter):
    current_letter_index = alphabet.index(current_letter)
    return second_line[current_letter_index]


def encrypt_decrypt_word(is_encrypt_mode, word, second_line, alphabet):
    result = ""
    for i in range(0, len(word)):
        result += encrypt_helper(alphabet, second_line, word[i]) if is_encrypt_mode \
            else encrypt_helper(second_line, alphabet, word[i])
    return result


if __name__ == '__main__':
    main()
