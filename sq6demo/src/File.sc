;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	FILE.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1988, 1990
;;;;
;;;;	Authors: Jeff Stephenson, Mark Wilden
;;;;	Updated:	Brian K. Hughes
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
(use String)
(use System)


(class File kindof Object
	(properties
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
;;;		rename					;rename the file
;;;		copy						;copy the file
;;;		readWord					;read next 16-bit value
;;;		writeWord				;write a 16-bit value
;;;	)


	(method (dispose)
		(self close:)
		(super dispose:)
	)

	(method (close)
		;; Close the file.  This makes sure that all writes which were made
		;; actually go to the disk file.

		(if handle
			(FileIO FileClose handle)
			(= handle 0)
		)
	)

	(method (copy oldOrNew newFileName &tmp oldName newName)
		(if (> argc 1)
			(= oldName (String StrGetData oldOrNew))
			(= newName (String StrGetData newFileName))
		else
			(= oldName name)
			(= newName (String StrGetData oldOrNew))
		)
		(return
			(FileIO FileCopy oldName newName)
		)
	)
	
	(method (delete fName)
		(if handle
			(self close:)
		)
		(return 
			(FileIO FileUnlink (if argc (String StrGetData fName) else name))
		)
	)

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
					(FileIO FileOpen name fAppend)
				)
				(1
				 	(FileIO FileOpen name mode)
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

	(method (read str len)
		;; Read len bytes from a file into str

		(if (!= argc 2)
			(return 0)
		)

		;Open the file if it is not presently open.
		(if (not handle)
			(self open:fRead)
		)

		(return
			(if handle (FileIO FileRead handle (String StrGetData str) len) else 0)
		)
	)

	(method (readString str len)
		;; Read a line (of maximum length len) from a file.

		(if (!= argc 2)
			(return 0)
		)

		;Open the file if it is not presently open.
		(if (not handle)
			(self open: fRead)
		)

		(return
			(if handle (FileIO FileFGets (String StrGetData str) len handle) else	NULL)
		)
	)

	(method (readWord)
		(return (FileIO FileGetWord handle))
	)
	
	(method (rename oldOrNew newName &tmp upDate newN oldN tmpStr)
		(if (> argc 1)
			(= upDate FALSE)
			(= oldN (String StrGetData oldOrNew))
			(= newN (String StrGetData newName))
		else
			(= upDate TRUE)
			(= oldN name)
			(= newN (String StrGetData oldOrNew))
		)
		;The kernel call returns 0 on success, but we should return 1.
		(if (FileIO FileRename oldN newN)
			(return FALSE) ;failure to rename
		else
			(if upDate 
				(= tmpStr (String with: newN))
				(= name (tmpStr data?))
				(tmpStr data: 0, dispose:)
 			)
			(return TRUE)
		)
	)

	(method (seek offset mode &tmp theMode)
		; Change the position in the file where the next read or write will
		;	occur.  The new position is specified by means of the mode and offset.
		;	if the mode is,
		;		fileSeekBeg		the offset is relative to the beginning of the
		;							file (default)
		;		fileSeekCur		the offset is relative to the current position
		;		fileSeekEnd		the offset is relative to the end of the file
		;
		;	the offset can be negative (for fileSeekCur and fileSeekEnd modes)
		; the new file position is returned

		(= theMode (if (>= argc 2) mode else fileSeekBeg))

		;Open the file if it is not presently open.
		(if (not handle)
			(self open:fRead)
		)

		(return (if handle (FileIO FileSeek handle offset theMode) else 0))
	)

	(method (write str len &tmp i)
		;; Write len bytes to the file from str.

		;Open the file if it is not presently open.
		(if (not handle)
			(self open:)
		)

		(return
			(if handle (FileIO FileWrite handle (String StrGetData str) len) else 0)
		)
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
				(if (not (FileIO FileFPuts handle (String StrGetData [str i])))
					(return FALSE)
				)
			)
		)
		(return TRUE)
	)

	(method (writeWord theValue)
		(return (FileIO FilePutWord handle theValue))
	)
)