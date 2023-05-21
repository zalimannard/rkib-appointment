import {reactive, readonly} from 'vue';

interface Alert {
    type: string;
    text: string;
    show: boolean;
}

const state = reactive<Alert>({
    type: '',
    text: '',
    show: false,
});

let timerId: number | null = null;

const setAlertType = (newType: string) => {
    state.type = newType;
};

const setAlertText = (newText: string) => {
    state.text = newText;
};

function setAlertShow(value: boolean) {
    if (timerId) {
        clearTimeout(timerId);
        timerId = null;
    }
    if (value) {
        state.show = true;
        timerId = window.setTimeout(() => {
            state.show = false;
            timerId = null;
        }, 2000);
    } else {
        state.show = false;
    }
}

function showAlert(type: string, text: string) {
    setAlertType(type);
    setAlertText(text);
    setAlertShow(true);
}

export {setAlertType, setAlertText, setAlertShow, showAlert};

export default readonly(state);
