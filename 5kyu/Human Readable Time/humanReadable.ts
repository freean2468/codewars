// https://www.codewars.com/kata/52685f7382004e774f0001f7/typescript

export function humanReadable(seconds: number): string {
  const s: string = String(Math.floor(seconds % 60));
  const m: string = String(Math.floor((seconds / 60) % 60));
  const h: string = String(Math.floor(seconds / (60 * 60)));
  const padding0 = (t: string): string => (t.length < 2 ? "0" + t : t);

  return `${padding0(h)}:${padding0(m)}:${padding0(s)}`;
}
