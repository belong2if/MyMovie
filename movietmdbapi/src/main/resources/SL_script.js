document.addEventListener('DOMContentLoaded', () => {
    // 로그인 폼 요소
    const loginForm = document.getElementById('login-form');
    const loginUsername = document.getElementById('login-username');
    const loginPassword = document.getElementById('login-password');
    const loginUsernameError = document.getElementById('login-username-error');
    const loginPasswordError = document.getElementById('login-password-error');

    // 회원가입 폼 요소
    const signupForm = document.getElementById('signup-form');
    const signupUsername = document.getElementById('signup-username');
    const signupPassword = document.getElementById('signup-password');
    const signupEmail = document.getElementById('signup-email');
    const signupUsernameError = document.getElementById('signup-username-error');
    const signupPasswordError = document.getElementById('signup-password-error');
    const signupEmailError = document.getElementById('signup-email-error');

   // 유효성 검사 함수
const validateEmail = (email) => {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
};

const validatePassword = (password) => {
    return password.length >= 6;
};

const validateUsername = (username) => {
    // 아이디는 5~15자이며, 영어와 숫자로만 구성되어야 함
    const usernameRegex = /^[a-zA-Z0-9]{5,15}$/;
    return usernameRegex.test(username);
};
 

// 로그인 폼 제출 처리
loginForm.addEventListener('submit', (event) => {
    let isValid = true;
    loginUsernameError.textContent = '';
    loginPasswordError.textContent = '';

    if (!validateUsername(loginUsername.value)) {
        loginUsernameError.textContent = '유효한 아이디를 입력하세요.(5~15자/영어+숫자)';
        isValid = false;
    }

    if (!validatePassword(loginPassword.value)) {
        loginPasswordError.textContent = '비밀번호는 6자 이상이어야 합니다.';
        isValid = false;
    }

    if (!isValid) {
        event.preventDefault();
    }
});

// 회원가입 폼 제출 처리
signupForm.addEventListener('submit', (event) => {
    let isValid = true;
    signupUsernameError.textContent = '';
    signupPasswordError.textContent = '';
    signupEmailError.textContent = '';

    if (!validateUsername(signupUsername.value)) {
        signupUsernameError.textContent = '유효한 아이디를 입력하세요.(5~15자/영어+숫자)';
        isValid = false;
    }

    if (!validatePassword(signupPassword.value)) {
        signupPasswordError.textContent = '비밀번호는 6자 이상이어야 합니다.';
        isValid = false;
    }

    if (!validateEmail(signupEmail.value)) {
        signupEmailError.textContent = '유효한 이메일 주소를 입력하세요.';
        isValid = false;
    }

    if (!isValid) {
        event.preventDefault();
    }
})})
