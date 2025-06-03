export type DateFormat = {
	/* Format date as YYYY-MM-DDTHH:mm */
	dateToISO8601DateTime: (date: Date) => string;

	/* Format date as YYYY-MM-DD */
	dateToISO8601Date: (date: Date) => string;

	/* Format date as HH:mm */
	dateToHHmm: (date: Date) => string;

	/* Format date as YYYY-MM */
	dateToYYYYMM: (date: Date) => string;
};
