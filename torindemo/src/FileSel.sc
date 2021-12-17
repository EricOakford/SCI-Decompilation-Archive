;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	FILESEL.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Author: 	Mark Wilden
;;;;	Updated: Brian K. Hughes
;;;;
;;;;	Type of selector allowing user to select from a list of file names
;;;;	matching a mask.  ReadFiles method returns 0 if unable to hold all
;;;;	file names in memory.
;;;;
;;;;	MS-DOS specific
;;;;
;;;;	Classes:
;;;;		FileSelector


(script# FILESEL)
(include game.sh)
(use DSelector)
(use String)


(class FileSelector of DSelector
	(properties
		mask		0				; file selection mask, e.g. *.* or c:\sierra\*.sg
		nFiles	0				; number of files matching mask
		x			maxFileName	; max length of a file name, including terminating 0
		sort		TRUE			; sort the file names
	)

;;;	(methods
;;;		readFiles
;;;		sortFileNames
;;;	)

	(method (init)
		(self readFiles:)
		(super init: &rest)
	)

	(method (readFiles &tmp fileName rc)

		(= fileName (String new: maxFileName))
		
		; Choose all "normal" files, since the attribute matching of this call
		; is severely brain-damaged.
		(for	(
					(= nFiles 0)
					(= rc (FileIO FileFindFirst mask (fileName data?) 0))
				)
				rc
				(
					(++ nFiles)
					(= rc (FileIO FileFindNext (fileName data?)))
				)
			(self setText: (fileName data?))
		)
		
		(if sort
			(self sortFileNames:)
		)

		(fileName dispose:)
		(return TRUE)
	)

	(method (sortFileNames)
	)
)

;(procedure (SortFileNames theNames theLen
;									&tmp i j temp swapped
;											theName theNextName)
;	(= temp (String newWith: maxFileName {}))
;	(for	((= i (- theLen 1)))
;			(> i 0)
;			((-- i))
;		(= swapped FALSE)
;		(for	((= j 0))
;				(< j i)
;				((++ j))
;			(= theName (+ theNames (* j maxFileName)))
;			(= theNextName (+ theName maxFileName))
;			(if (< (theNextName compare: theName) 0)
;				(temp copy: theName)
;				(theName copy: theNextName)
;				(theNextName copy: temp)
;				(= swapped TRUE)
;			)
;		)
;		(breakif (not swapped))
;	)
;	(temp dispose:)
;)
