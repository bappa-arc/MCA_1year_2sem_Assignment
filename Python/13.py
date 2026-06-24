import time

def geo_gen(a, q):
    term = a
    loop_time_total = 0.0

    while True:
        if term > 100000:
            return loop_time_total  
        loop_start = time.perf_counter()
        yield term                 
        loop_end = time.perf_counter()

        loop_time_total += (loop_end - loop_start)
        term = term * q

a = float(input("Enter the first term (a): "))
q = float(input("Enter the common ratio (q): "))

print(f"\nGeometric Progression: a={a}, q={q}")
print("Terms (stops when term > 100,000):\n")

total_start = time.perf_counter()

terms = []
gen = geo_gen(a, q)
loop_time = 0.0

try:
    while True:
        val = next(gen)
        terms.append(val)
        print(f"  {val}")
except StopIteration as e:
    loop_time = e.value  

total_end = time.perf_counter()
total_time = total_end - total_start

print(f"\nTotal terms generated   : {len(terms)}")
print(f"Last term yielded       : {terms[-1] if terms else 'None'}")
print(f"\nTotal time (seconds)    : {total_time:.6f}s")
print(f"Time inside loop (sec)  : {loop_time:.6f}s")
print(f"Time outside loop (sec) : {total_time - loop_time:.6f}s")