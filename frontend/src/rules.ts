export const dateRule = (value: string) => {
    if (!value) return true;
    const pattern = /^(\d{2})\.(\d{2})\.(\d{4})$/;
    if (!pattern.test(value)) return "Неполная дата";
    const match = value.match(pattern);
    if (!match) return "Неправильный формат даты";
    const [, day, month, year] = match;
    const date = new Date(parseInt(year), parseInt(month) - 1, parseInt(day));
    if (
        date.getFullYear() !== parseInt(year) ||
        date.getMonth() !== parseInt(month) - 1 ||
        date.getDate() !== parseInt(day)
    ) {
        return "Некорректная дата.";
    }
    return true;
};

export const phoneRule = (value: string) => {
    if (!value) return true;
    if (value === "+7(") return true;
    const pattern = /^\+7\((\d{3})\)(\d{3})-(\d{2})-(\d{2})$/;
    if (!pattern.test(value)) return "Неполный номер";
    return true;
};

export const requiredRule = (value: string) => {
    return !!value || "Обязательное поле";
};

export const usernameRule = (value: string) => {
    if (value == null || value === '') return "Обязательное поле";
    const pattern = /^[a-zA-Z0-9]+$/;
    if (!pattern.test(value)) return "Недопустимые символы";
    return true;
};

export const passwordRule = (value: string) => {
    if (value == null || value === '') return "Обязательное поле";
    const pattern = /^[a-zA-Z0-9!@#$%^&*]+$/;
    return pattern.test(value) || "Недопустимые символы";
};
