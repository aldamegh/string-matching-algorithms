# Publication changes

Original project: January-February 2023. Publication maintenance: 9 November 2026.

Fixed KMP prefix/fallback handling, made empty/long-pattern results consistent, expanded Boyer-Moore lookup to all Java character values, and removed shared result-array state. Historical GUI and project structure are retained.

This is a curated publication copy. Tests and maintenance edits added for publication are separate from the historical coursework; original results and performance claims are not inferred from these checks.


Validation: 1,524 comparisons against Java string-search results passed, covering overlaps, repeated-character fallback, empty/long patterns, Arabic, emoji, and seeded random cases. The GUI was compiled; its visual interaction was not exercised. Java checks used JDK 21 with Java 8-compatible output where applicable; Python checks used Python 3.14. No original datasets were executed.
