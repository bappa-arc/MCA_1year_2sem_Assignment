class BankAccount:
    def __init__(self, name, balance):
        self.name = name
        self.balance = balance

    def deposit(self, amount):
        self.balance += amount
        print("Deposited:", amount)

    def withdraw(self, amount):
        if amount <= self.balance:
            self.balance -= amount
            print("Withdrawn:", amount)
        else:
            print("Insufficient Balance")

    def get_balance(self):
        print("Current Balance:", self.balance)

    def change_pin(self):
        old_pin = input("Enter old PIN: ")
        new_pin = input("Enter new PIN: ")
        print("PIN changed successfully")

class SavingsAccount(BankAccount):
    def __init__(self, name, balance, interest_rate, fee):
        super().__init__(name, balance)
        self.interest_rate = interest_rate
        self.fee = fee

    def add_interest(self):
        interest = self.balance * self.interest_rate / 100
        self.balance += interest
        print("Interest Added:", interest)

    def withdraw(self, amount):
        total = amount + self.fee

        if total <= self.balance:
            self.balance -= total
            print("Withdrawn:", amount)
            print("Fee Charged:", self.fee)
        else:
            print("Insufficient Balance")

name = input("Enter account holder name: ")
balance = float(input("Enter initial balance: "))
interest = float(input("Enter interest rate (%): "))
fee = float(input("Enter withdrawal fee: "))

acc = SavingsAccount(name, balance, interest, fee)

while True:
    print("\n1.Deposit")
    print("2.Withdraw")
    print("3.Add Interest")
    print("4.Check Balance")
    print("5.Change PIN")
    print("6.Exit")

    choice = int(input("Enter choice: "))

    if choice == 1:
        amt = float(input("Enter amount: "))
        acc.deposit(amt)

    elif choice == 2:
        amt = float(input("Enter amount: "))
        acc.withdraw(amt)

    elif choice == 3:
        acc.add_interest()

    elif choice == 4:
        acc.get_balance()

    elif choice == 5:
        acc.change_pin()

    elif choice == 6:
        break

    else:
        print("Invalid Choice")