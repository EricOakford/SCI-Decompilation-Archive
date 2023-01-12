;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	FILE.SC
;;;;	(c) Sierra On-Line, Inc, 1988, 1990
;;;;
;;;;	Authors: Jeff Stephenson, Mark Wilden
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
		name		0
		handle	0				;private -- the OS's handle for the open file
	)

;;;	(methods
;;;		open						;open/create file
;;;		readString				;read a string from the file
;;;		writeString				;write a string to the file
;;;		write						;write some data to the file
;;;		read						;read some data from the file
;;;		seek						;set file position
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
		;;	open:returns the File ID if file is opened successfully,NULL otherwise.

		(= handle
			(switch argc
				(0
					(FileIO fileOpen name fAppend)
				)
				(1
					(FileIO fileOpen name mode)
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

	(method (write str len &tmp i)
		;; Write len bytes to the file from str.

		;Open the file if it is not presently open.
		(if (not handle)
			(self open:)
		)

		(return (if handle (FileIO fileWrite handle str len) else 0))
	)

	(method (writeString str &tmp i)
		;; Write the string pointed to by 'str' to the file.  Multiple strings
		;; may be sent in one call.

		;Open the file if it is not presently open.
		(if (not handle)
			(self open:)
		)

		;Multiple writes accepted.
		(if handle
			(for ((= i 0)) (< i argc) ((++ i))
				(FileIO fileFPuts handle [str i])
			)
		)
	)

	(method (read str len)
		;; Read len bytes from a file into str

		(if (!= argc 2)
			(return 0)
		)

		;Open the file if it is not presently open.
		(if (not handle)
			(self open:fRead)
		)

		(return (if handle (FileIO fileRead handle str len) else 0))
	)

	(method (readString str len)
		;; Read a line (of maximum length len) from a file.

		(if (!= argc 2)
			(return 0)
		)

		;Open the file if it is not presently open.
		(if (not handle)
			(self open:fRead)
		)

		(return (if handle (FileIO fileFGets str len handle) else 0))
	)

	(method (seek offset mode &tmp theMode)
		;; Change the position in the file where the next read or write will
		;;	occur.  The new position is specified by means of the mode and offset.
		;;	if the mode is,
		;;		fileSeekBeg		the offset is relative to the beginning of the
		;;							file (default)
		;;		fileSeekCur		the offset is relative to the current position
		;;		fileSeekEnd		the offset is relative to the end of the file
		;;
		;;	the offset can be negative (for fileSeekCur and fileSeekEnd modes)
		;; the new file position is returned

		(= theMode (if (>= argc 2) mode else fileSeekBeg))

		;Open the file if it is not presently open.
		(if (not handle)
			(self open:fRead)
		)

		(return (if handle (FileIO fileSeek handle offset theMode) else 0))
	)

	(method (close)
		;; Close the file.  This makes sure that all writes which were made
		;; actually go to the disk file.

		(if handle
			(FileIO fileClose handle)
			(= handle 0)
		)
	)
	
	(method (delete)
		(if handle
			(self close:)
		)
		(return (FileIO fileUnlink name))
	)

	(method (dispose)
		(self close:)
		(super dispose:)
	)

	(method (showStr where)
		(Format where {File: %s} name)
	)
)