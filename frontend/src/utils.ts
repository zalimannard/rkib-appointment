export function fromIsoToDefault(value: string): string {
    if (!value) {
        return "";
    }
    let dateParts = [];
    dateParts = value.split("-");
    try {
        return (dateParts.length === 3 ? `${dateParts[2]}.${dateParts[1]}.${dateParts[0]}` : "");
    } catch (e) {
        return "";
    }
}

export function fromDefaultToIso(value: string): string {
    if (!value) {
        return "";
    }
    let dateParts = [];
    dateParts = value.split(".");
    try {
        return (dateParts.length === 3 ? `${dateParts[2]}-${dateParts[1]}-${dateParts[0]}` : "");
    } catch (e) {
        return "";
    }
}

export function roleStringToCode(value: string): string {
    switch (value) {
        case "Админ":
            return "ADMIN"
        case "Регистратор":
            return "REGISTRAR"
        case "Доктор":
            return "DOCTOR"
        case "Пациент":
            return "PATIENT"
        default:
            return ""
    }
}

export function roleCodeToString(value: string): string {
    switch (value) {
        case "ADMIN":
            return "Админ"
        case "REGISTRAR":
            return "Регистратор"
        case "DOCTOR":
            return "Доктор"
        case "PATIENT":
            return "Пациент"
        default:
            return ""
    }
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
