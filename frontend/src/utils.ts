export function fromIsoToDefault(value: string): string {
    let dateParts = [];
    dateParts = value.split("-");
    return (dateParts.length === 3 ? `${dateParts[2]}.${dateParts[1]}.${dateParts[0]}` : "");
}

export function fromDefaultToIso(value: string): string {
    let dateParts = [];
    dateParts = value.split(".");
    return (dateParts.length === 3 ? `${dateParts[2]}.${dateParts[1]}.${dateParts[0]}` : "");
}
