from scapy.all import IP, TCP, sr1
import random

target = input("Enter destination IP: ")
port = int(input("Enter TCP port: "))
flags_list = ["S", "A", "FA", "R", "SF"]
for flag in flags_list:
    print(f"\nSending TCP packet with flags: {flag}")
    packet = IP(dst=target) / TCP(
        sport=random.randint(20000, 60000),
        dport=port,
        flags=flag,
        seq=random.randint(1, 100000)
    )
    response = sr1(packet, timeout=3, verbose=0)
    if response is None:
        print("No response received")
    elif response.haslayer(TCP):
        print("TCP Response Flags:", response[TCP].flags)
    elif response.haslayer("ICMP"):
        print("ICMP Type:", response["ICMP"].type)
        print("ICMP Code:", response["ICMP"].code)
