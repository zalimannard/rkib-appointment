export function handleBackspaceForDate(event: KeyboardEvent) {
  const target = event.target as HTMLInputElement;
  if (event.key === "Backspace" && target.value.slice(-1) === ".") {
    event.preventDefault();
    target.value = target.value.slice(0, -2);
  }
}

export function handleBackspaceForPhoneNumber(event: KeyboardEvent) {
  const target = event.target as HTMLInputElement;
  if (event.key === "Backspace" && (target.value.slice(-1) === "-" || target.value.slice(-1) === ")")) {
    event.preventDefault();
    target.value = target.value.slice(0, -2);
  }
}
