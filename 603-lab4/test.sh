#!/bin/sh

# This script is used to test the transformer.py program
# It will first encrypt the original document and store it in encrypted.txt, then decrypt it and store the result in decrypted.txt
# diff is used to check if the encryption and decryption process are successful
python3 transformer.py original.txt instruction.txt encrypted.txt e
python3 transformer.py encrypted.txt instruction.txt decrypted.txt d
diff original.txt decrypted.txt