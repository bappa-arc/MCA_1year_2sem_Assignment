
from scapy.all import IP, UDP, Raw, ICMP, sr1

target = input("Enter destination IP: ")
start = int(input("Enter starting UDP port: "))
end = int(input("Enter ending UDP port: "))

print("\nPort\tStatus\t\tICMP Type\tICMP Code")

for port in range(start, end + 1):

    packet = IP(dst=target) / UDP(
        sport=45000,
        dport=port
    ) / Raw(load="UDP Test")

    reply = sr1(packet, timeout=3, verbose=0)

    if reply is None:
        print(port, "\tNo response")

    elif reply.haslayer(ICMP):
        icmp_type = reply[ICMP].type
        icmp_code = reply[ICMP].code

        if icmp_type == 3 and icmp_code == 3:
            status = "Port Unreachable"
        else:
            status = "ICMP Error"

        print(port, "\t", status, "\t",
              icmp_type, "\t\t", icmp_code)

    elif reply.haslayer(UDP):
        print(port, "\tUDP Response")

    else:
        print(port, "\tOther Response")
