;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	FILE.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Jeff Stephenson
;;;;
;;;;	The File class allows you to open and write to a file on disk. 
;;;;	This is useful for logging user input for which you have no
;;;;	response in the development or beta-test phase, writing utilities
;;;;	which allow you to position Actors on a picture and then write
;;;;	out the coordinates, etc.
;;;;
;;;;	Classes:
;;;;		File


(script#	FILE)
(include game.sh)
(use System)

(class File of Object

	(properties
		name "gamefile.sh"	;the name of the file
		handle 0					;private -- the OS's handle for the open file
	)

;;;	(methods
;;;		open						;open/create file
;;;		write						;write a string to the file
;;;		read						;read from a file
;;;		close						;close file
;;;		delete					;delete the file
;;;	)


	(method (open mode)
		;; Open the file.  'mode' is the mode in which to open the file:
		;;		mode =
		;;			fAppend		Appends to end of file.  Default if 'mode'
		;;							is not specified.
		;;			fRead			Positions at start of file.
		;;			fTrunc		Truncates the file to zero length when opened.
		;;
		;;	open: returns the File ID if file is opened successfully, NULL otherwise.

		(= handle
			(switch argc
				(0
					(FOpen name fAppend)
				)
				(1
					(FOpen name mode)
				)
				(else
					0
				)
			)
		)
		(if (== handle -1)
			(= handle 0)
		)
		(return (if handle self else NULL))
	)


	(method (write str &tmp i)
		;; Write the string pointed to by 'str' to the file.  Multiple strings
		;; may be sent in one call.

		;Open the file if it is not presently open.
		(if (not handle)
			(self open:)
		)

		;Multiple writes accepted.
		(if handle
			(for ((= i 0)) (< i argc) ((++ i))
				(FPuts handle [str i])
			)
		)
	)


	(method (read str len)
		;; Read a line (of maximum length len) from a file.

		(if (!= argc 2)
			(return 0)
		)

		;Open the file if it is not presently open.
		(if (not handle)
			(self open:fRead)
		)

		(return (if handle (FGets str len handle) else 0))
	)


	(method (close)
		;; Close the file.  This makes sure that all writes which were made
		;; actually go to the disk file.

		(if handle
			(FClose handle)
			(= handle 0)
		)
	)


	(method (dispose)
		(self close:)
		(super dispose:)
	)


	(method (showStr where)
		(Format where FILE 0 name)
	)
)

