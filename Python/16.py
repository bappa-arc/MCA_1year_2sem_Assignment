from collections import Counter 
class TextCheck:
    def __init__(self, text):
        self.text = text

    def __len__(self):
        words = self.text.lower().split()
        freq = Counter(words)
        repetitive = 0
        
        for count in freq.values():
            if count > 1:
                repetitive += 1
                
        return repetitive

    def most_common_word(self):
        words = self.text.lower().split()
        freq = Counter(words)
        
        return freq.most_common(1)

text = input("Enter text: ")
obj = TextCheck(text)

print("Number of repetitive words:", len(obj))
print("Most Frequent Word:")
print(obj.most_common_word())
