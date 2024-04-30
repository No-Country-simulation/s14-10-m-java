/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,ts}"],
  theme: {
    extend: {
      colors: {
        accent: {
          light: "#FFCD93",
          dark: "#FF9D2B",
        },
        primary: {
          dark: "#234C76",
        },
        "calme-light-blue": "#75A5FF",
        "calme-dark-blue": "#063463",
      },
      fontFamily: {
        poppins: ["Poppins", "sans-serif"],
      },
    },
  },
  plugins: [],
};
