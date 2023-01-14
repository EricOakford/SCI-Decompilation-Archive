;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	FILESEL.SC
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Author: Mark Wilden
;;;;
;;;;	Type of selector allowing user to select from a list of file names
;;;;	matching a mask
;;;;	readFiles: method returns 0 if unable to hold all file names in memory
;;;;
;;;;	MS-DOS specific

(script# FILESEL)
(include game.sh)
(use Intrface)


;;;(procedure
;;;	SortFileNames
;;;)

(class FileSelector of DSelector
	(properties
		mask		0		; file selection mask, e.g. *.* or c:\sierra\tmp\*.sg
		text		0		; array of file names matching mask
		nFiles	0		; number of files matching mask
		x maxFileName	; max length of a file name, including terminating 0
							; don't wantonly change
		sort		TRUE	; sort the file names
	)

;;;	(methods
;;;		readFiles
;;;	)

	(method (readFiles
					theMask
					&tmp
					[fileName 7]
					;[fileName (+ (/ maxFileName 2) 1)]
					i cp rc
			  )

		(if (> argc 0)
			(= mask theMask)
		)
		(if (not mask)
			(= mask {*.*})
		)
		(if text
			(Memory MDisposePtr text)
			(= text 0)
		)
		
		; Choose all "normal" files, since the attribute matching of this call
		; is severely brain-damaged.
		(for	(
					(= nFiles 0)
					(= rc (FileIO fileFindFirst mask @fileName 0))
				)
				rc
				(
					(++ nFiles)
					(= rc (FileIO fileFindNext @fileName))
				)
		)
		
		; allocate the memory for array
		(if (not (= text (Memory MNewPtr (+ (* nFiles maxFileName) 1))))
			(return 0)
		)
		
		; go through the directory again and actually add files to array
		(for	(
					(= i 0)
					(= cp text)
					(= rc (FileIO fileFindFirst mask @fileName 0))
				)
				(and rc (< i nFiles))
				(
					(++ i)
					(+= cp maxFileName)
					(= rc (FileIO fileFindNext @fileName))
				)
			(StrCpy cp @fileName)
		)
		
		; term the whole array with a null
		(StrAt text (* nFiles maxFileName) 0)
		
		(if sort
			(SortFileNames text nFiles)
		)

		(return 1)
	)

	(method (setSize &tmp [r 4])
		(super setSize:)
		(TextSize @[r 0] {M} font)
		(= nsRight (+ nsLeft (* [r 3] x)))
	)
	
	(method (dispose)
		(if text
			(Memory MDisposePtr text)
			(= text 0)
		)
		(super dispose:)
	)
)

(procedure (SortFileNames theNames theLen
									&tmp i j [temp maxFileName] swapped
											theName theNextName)
	(for ((= i (- theLen 1))) (> i 0) ((-- i))
		(= swapped FALSE)
		(for ((= j 0)) (< j i) ((++ j))
			(= theName (+ theNames (* j maxFileName)))
			(= theNextName (+ theName maxFileName))
			(if (< (StrCmp theNextName theName) 0)
				(StrCpy @temp theName)
				(StrCpy theName theNextName)
				(StrCpy theNextName @temp)
				(= swapped TRUE)
			)
		)
		(breakif (not swapped))
	)
)