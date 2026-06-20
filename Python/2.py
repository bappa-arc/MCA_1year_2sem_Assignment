import datetime
coupons = {
    "Monday": "MON10 - 10%",
    "Tuesday": "TUE15 - 15%",
    "Wednesday": "WED20 - 20%",
    "Thursday": "THU10 - 10%",
    "Friday": "FRI25 - 25%",
    "Saturday": "WEEKEND30 - 30%",
    "Sunday": "SUN30 - 30%"
}

day = datetime.datetime.now().strftime("%A")
print(f"Discount for {day}: {coupons.get(day)}")
