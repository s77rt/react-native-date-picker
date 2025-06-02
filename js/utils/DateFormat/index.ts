import type { DateFormat } from "./types";

const dateFormat: DateFormat = {
	dateToISO8601DateTime(date) {
		const year = date.getFullYear();
		const month = ("0" + (date.getMonth() + 1)).slice(-2);
		const day = ("0" + date.getDate()).slice(-2);
		const hours = ("0" + date.getHours()).slice(-2);
		const minutes = ("0" + date.getMinutes()).slice(-2);

		return `${year}-${month}-${day}T${hours}:${minutes}`;
	},

	dateToISO8601Date(date) {
		return [
			date.getFullYear(),
			("0" + (date.getMonth() + 1)).slice(-2),
			("0" + date.getDate()).slice(-2),
		].join("-");
	},

	dateToHHmm(date) {
		return [
			("0" + date.getHours()).slice(-2),
			("0" + date.getMinutes()).slice(-2),
		].join(":");
	},

	dateToYYYYMM(date) {
		return [
			date.getFullYear(),
			("0" + (date.getMonth() + 1)).slice(-2),
		].join("-");
	},
};

export default dateFormat;
