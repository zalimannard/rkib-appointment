import {reactive, readonly} from 'vue';

interface Alert {
    text: string;
    type: string;
    show: boolean;
}

const state = reactive<Alert>({
    text: '',
    type: '',
    show: false,
});

let timerId: number | null = null;

const setAlertText = (newText: string) => {
    state.text = newText;
};

const setAlertType = (newType: string) => {
    state.type = newType;
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

export {setAlertText, setAlertType, setAlertShow};

export default readonly(state);
