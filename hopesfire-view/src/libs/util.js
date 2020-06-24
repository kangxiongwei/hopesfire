let util = {};
util.title = function (title) {
    title = title ? title : 'Hopesfire';
    window.document.title = title;
};

export default util;
