from scapy.all import IP, ICMP, sr1

target = input("Enter destination IP: ")
max_ttl = 20

print("\nTTL\tResponding IP\t\tICMP Type\tICMP Code")

for ttl in range(1, max_ttl + 1):

    packet = IP(dst=target, ttl=ttl) / ICMP()

    reply = sr1(packet, timeout=3, verbose=0)

    if reply is None:
        print(ttl, "\tNo response")
        continue

    if reply.haslayer(ICMP):
        icmp_type = reply[ICMP].type
        icmp_code = reply[ICMP].code

        print(ttl, "\t", reply.src, "\t", icmp_type,
              "\t\t", icmp_code)

        if icmp_type == 0:
            print("Destination reached!")
            break
