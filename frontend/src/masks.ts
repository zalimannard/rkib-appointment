export function dateMask(value: string): string {
    if (!value) return "";
    const numValue = value.replace(/\D+/g, "").split("");
    const mask = ["#", "#", ".", "#", "#", ".", "#", "#", "#", "#"];

    let formattedValue = "";
    let index = 0;
    for (const char of mask) {
        if (!numValue.length) break;

        if (char === "#") {
            formattedValue += numValue.shift();
            index++;
        } else if (index === 2 || index === 5) {
            formattedValue += char;
            index++;
        }
    }

    if (numValue.length === 0 && (formattedValue.length === 2 || formattedValue.length === 5)) {
        formattedValue += ".";
    }
    return formattedValue;
}

export function timeMask(value: string): string {
    if (!value) return "";
    const numValue = value.replace(/\D+/g, "").split("");
    const mask = ["#", "#", ":", "#", "#", ":", "#", "#"];

    let formattedValue = "";
    let index = 0;
    for (const char of mask) {
        if (!numValue.length) break;

        if (char === "#") {
            formattedValue += numValue.shift();
            index++;
        } else if (index === 2 || index === 5) {
            formattedValue += char;
            index++;
        }
    }

    if (numValue.length === 0 && (formattedValue.length === 2 || formattedValue.length === 5)) {
        formattedValue += ":";
    }
    return formattedValue;
}

export function phoneMask(value: string): string {
    if (!value) return "+7(";
    const numValue = value
        .replace(/\D+/g, "")
        .split("")
        .filter((_, i) => i > 0);
    const mask = ["#", "#", "#", ")", "#", "#", "#", "-", "#", "#", "-", "#", "#"];

    let formattedValue = "+7(";
    for (const char of mask) {
        if (!numValue.length) {
            break;
        }

        if (char === "#") {
            formattedValue += numValue.shift();
        } else {
            formattedValue += char;
        }
    }

    if (numValue.length === 0 && formattedValue.length === 6) {
        formattedValue += ")";
    }

    if (numValue.length === 0 && (formattedValue.length === 10 || formattedValue.length === 13)) {
        formattedValue += "-";
    }

    return formattedValue;
}