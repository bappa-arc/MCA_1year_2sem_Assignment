from scapy.all import IP, TCP, sr1, send
import random

target = input("Enter destination IP: ")
port = int(input("Enter TCP port: "))
client_isn = random.randint(10000, 999999)
syn = IP(dst=target) / TCP(
    sport=40000,
    dport=port,
    flags="S",
    seq=client_isn
)
print("\nSending SYN...")
reply = sr1(syn, timeout=3, verbose=0)
if reply is None:
    print("No response received")
elif reply.haslayer(TCP) and reply[TCP].flags & 0x12 == 0x12:
    server_isn = reply[TCP].seq
    server_ack = reply[TCP].ack
    expected_ack = (server_isn + 1) & 0xFFFFFFFF
    print("\nTCP Sequence Analysis")
    print("Client ISN =", client_isn)
    print("Server ISN =", server_isn)
    print("SYN+ACK acknowledgment =", server_ack)
    print("Expected client acknowledgment =", expected_ack)
    ack = IP(dst=target) / TCP(
        sport=40000,
        dport=port,
        flags="A",
        seq=server_ack,
        ack=expected_ack
    )
    send(ack, verbose=0)
    print("ACK segment sent successfully")
else:
    print("Received response is not SYN+ACK")
