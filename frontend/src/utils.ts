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

export function checkFilter(fieldValue: string, filterValue: string): boolean {
    if (filterValue === "") {
        return true;
    }
    if (!fieldValue && filterValue !== "") {
        return false;
    }
    return fieldValue.toLowerCase().indexOf(filterValue.toLowerCase()) !== -1;
}