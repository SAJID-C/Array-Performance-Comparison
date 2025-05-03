import time
import gc

def measure_time_and_sum_fill_list(size):
    lst = [0] * size
    total_sum = 0
    start_time = time.time()
    for i in range(size):
        lst[i] = i
        total_sum += lst[i]
    end_time = time.time()
    return total_sum, (end_time - start_time) * 1000  # milliseconds

def check_fixed_stack_dynamic():
    print("\n--- Performance Test: Fixed Stack-Dynamic ---")
    size = 1_000_000
    print(f"Implementation: Local list = [0] * {size} (fixed at compile-time equivalent)")
    total_sum, duration = measure_time_and_sum_fill_list(size)
    print(f"Result: Sum = {total_sum}, Time = {duration:.2f} ms")
    gc.collect()

def check_stack_dynamic(size):
    print("\n--- Performance Test: Stack-Dynamic ---")
    print(f"Implementation: Local list with runtime size = {size}")
    total_sum, duration = measure_time_and_sum_fill_list(size)
    print(f"Result: Sum = {total_sum}, Time = {duration:.2f} ms")
    gc.collect()

def check_heap_dynamic(size):
    print("\n--- Performance Test: Heap-Dynamic ---")
    print(f"Implementation: Using list.append() and pre-allocated list. Size = {size}")

    # Variant 1: append()
    print("Variant: list.append()")
    start = time.time()
    lst_append = []
    total_sum = 0
    for i in range(size):
        lst_append.append(i)
        total_sum += i
    duration = (time.time() - start) * 1000
    print(f"Result: Sum = {total_sum}, Time = {duration:.2f} ms")
    del lst_append

    # Variant 2: Pre-allocated
    print("Variant: Pre-allocated list")
    total_sum, duration = measure_time_and_sum_fill_list(size)
    print(f"Result: Sum = {total_sum}, Time = {duration:.2f} ms")
    gc.collect()

def check_fixed_heap_dynamic(size):
    print("\n--- Performance Test: Fixed Heap-Dynamic ---")
    print(f"Implementation: Pre-allocated list and tuple. Size = {size}")

    # Variant 1: List
    print("Variant: Pre-allocated list")
    total_sum, duration = measure_time_and_sum_fill_list(size)
    print(f"Result: Sum = {total_sum}, Time = {duration:.2f} ms")

    # Variant 2: Tuple
    print("Variant: Tuple")
    start = time.time()
    fixed_tuple = tuple(range(size))
    total_sum = sum(fixed_tuple)
    duration = (time.time() - start) * 1000
    print(f"Result: Sum = {total_sum}, Time = {duration:.2f} ms")
    del fixed_tuple
    gc.collect()

def check_static():
    print("\n--- Performance Test: Static ---")
    size = 1_000_000
    print(f"Implementation: Pre-allocated local list. Size = {size}")
    total_sum, duration = measure_time_and_sum_fill_list(size)
    print(f"Result: Sum = {total_sum}, Time = {duration:.2f} ms")
    gc.collect()

def get_user_input(prompt, default):
    try:
        value = input(prompt)
        return int(value)
    except ValueError:
        print("Invalid input. Using default size.")
        return default

# Main block
if __name__ == "__main__":
    print("="*60)
    print("Starting Array Category Performance Comparisons in Python")
    print("="*60)

    check_static()
    check_fixed_stack_dynamic()

    # Stack Dynamic
    size_sd = get_user_input("Enter size for Stack-Dynamic (e.g., 1000000): ", 1_000_000)
    check_stack_dynamic(size_sd)

    # Fixed Heap-Dynamic
    size_fhd = get_user_input("Enter size for Fixed Heap-Dynamic (e.g., 1000000): ", 1_000_000)
    check_fixed_heap_dynamic(size_fhd)

    # Heap-Dynamic
    check_heap_dynamic(1_000_000)

    print("\n=== All Performance Comparisons Complete ===")
